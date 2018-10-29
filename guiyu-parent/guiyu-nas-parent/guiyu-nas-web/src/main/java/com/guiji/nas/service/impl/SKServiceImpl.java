/** 
 *@Copyright:Copyright (c) 2008 - 2100 
 *@Company:guojaing
 */  
package com.guiji.nas.service.impl;

import com.guiji.nas.constants.SKConstants;
import com.guiji.nas.constants.SKErrorEnum;
import com.guiji.nas.dao.SysFileMapper;
import com.guiji.nas.dao.entity.SysFile;
import com.guiji.nas.dao.entity.SysFileExample;
import com.guiji.nas.exception.SKException;
import com.guiji.nas.model.SkFileInfoReq;
import com.guiji.nas.model.SkFileInfoRsp;
import com.guiji.nas.model.SkFileQueryReq;
import com.guiji.nas.service.SKService;
import com.guiji.utils.BeanUtil;
import com.guiji.utils.StrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/** 
 * SK文件系统服务
 */
@Service
public class SKServiceImpl implements SKService {
	static Logger logger = LoggerFactory.getLogger(SKServiceImpl.class);
	@Resource  
    private FastDFSClientImpl fwc;
	@Autowired
	private SysFileMapper sysFileMapper;
	@Value("${fdfs.webServerUrl}")
    private String hostUrl;
	
	
	/**
	 * 文件上传
	 * 1、先上传
	 * 2、再更新本地表
	 * @param skFileInfoReq
	 * @param file
	 * @return SkFileInfoRsp
	 * @throws IOException 
	 */
	public SkFileInfoRsp uploadFile(SkFileInfoReq skFileInfoReq, MultipartFile file) throws IOException {
		if(file != null) {
			//1、先上传FS，然后再本地进行保存
			//2、保存文件信息到本地表
			String fileName = file.getOriginalFilename();
	        String ext = fileName.substring(fileName.lastIndexOf(".") + 1); //文件后缀
	        logger.info("开始上传附件...");
			String url = fwc.uploadFile(file);//正常上传
			logger.info("附件上传成功..."+url);
			String thumbUrl = null;	//缩略图URL
			SysFile skFile = new SysFile();
			if(skFileInfoReq != null) {
				if(SKConstants.THUMB_IMAGE_FILAG_Y.equals(skFileInfoReq.getThumbImageFlag()) && isPic(ext)) {
					//如果上传时要求生成缩略图，且上传文件为图片，那么生成缩略图
					thumbUrl = fwc.uploadImageAndCrtThumbImage(file);//小图  
				}
				skFile.setBusiId(skFileInfoReq.getBusiId()); //业务ID
				skFile.setBusiType(skFileInfoReq.getBusiType()); //文件类型（业务类型）
				skFile.setSysCode(skFileInfoReq.getSysCode()); //系统代码
			}
			skFile.setFileName(fileName); //文件名称
			skFile.setFileSize(Double.longBitsToDouble(file.getSize())); //文件大小
			skFile.setFileType(StrUtils.isNotEmpty(ext) ? ext.toUpperCase() : null); //文件后缀，存储大写
			skFile.setSkUrl(url); //文件URL
			skFile.setSkThumbImageUrl(thumbUrl); //缩略图url
			String loginId = "1";	//当前登录ID WebContextUtil.getLoginId() TODO
			skFile.setCrtUser(loginId); //创建人
			skFile.setCrtTime(new Date());	//创建时间
			skFile.setLstUpdateUser(loginId);	//最后更新人
			skFile.setLstUpdateTime(new Date()); //最后创建时间
			sysFileMapper.insert(skFile);
			SkFileInfoRsp rsp = new SkFileInfoRsp();
			//拷贝返回信息
			BeanUtil.copyProperties(skFile, rsp);
			return rsp;
		}
		return null;
	}
	
	
	/**
	 * 删除文件
	 * @param id void
	 */
	public void deleteById(String id) {
		//先根据主键查出来
		SysFile skFileInfo = sysFileMapper.selectByPrimaryKey(id);
		if(skFileInfo != null) {
			String filePath = skFileInfo.getSkUrl();
			fwc.deleteFile(filePath);
			logger.info("删除文件url:"+filePath);
			String thumbfilePath = skFileInfo.getSkThumbImageUrl();
			if(StrUtils.isNotEmpty(thumbfilePath)) {
				fwc.deleteFile(thumbfilePath);
				logger.info("删除缩略图url:"+thumbfilePath);
			}
			sysFileMapper.deleteByPrimaryKey(id);
		}else {
			logger.warn("删除附件-附件文件不存在，ID="+id);
		}
	}
	
	/**
	 * 根据条件查询文件信息列表
	 * @date:2018年6月25日 下午10:14:17 
	 * @param skFileQueryReq
	 * @return List<SkFileInfo>
	 */
	public List<SysFile> querySkFileByCondition(SkFileQueryReq skFileQueryReq){
		String id = skFileQueryReq.getId();
		String sysCode = skFileQueryReq.getSysCode();
		String busiId = skFileQueryReq.getBusiId();
		String busiType = skFileQueryReq.getBusiType();
		if(StrUtils.isEmpty(id) && StrUtils.isEmpty(busiId)) {
			logger.error("附件请求信息附件ID、业务ID不能都为空!");
    		//注册信息为空异常
    		throw new SKException(SKErrorEnum.QUERY_NULL.getErrorCode(),SKErrorEnum.QUERY_NULL.getErrorMsg());
		}
		SysFileExample example = new SysFileExample();
		SysFileExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id); //主键ID
		criteria.andSysCodeEqualTo(sysCode);	//系统码
		criteria.andBusiIdEqualTo(busiId);	//业务ID
		criteria.andBusiTypeEqualTo(busiType);	//业务附件类型
		return sysFileMapper.selectByExample(example);
	}
	
	
	/**
	 * 根据后缀校验是否是图片
	 * @date:2018年6月25日 下午9:36:45 
	 * @param extUpp
	 * @return boolean
	 */
	private boolean isPic(String extUpp) {
		String pics = "JPG,JPEG,PNG,GIF,BMP,WBMP";
		if(StrUtils.isNotEmpty(extUpp) && pics.contains(extUpp.toUpperCase())) {
			return true;
		}
		return false;
	}
}
  