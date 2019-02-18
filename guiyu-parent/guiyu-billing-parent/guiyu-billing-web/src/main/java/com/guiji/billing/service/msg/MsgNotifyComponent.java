package com.guiji.billing.service.msg;

import com.guiji.billing.dto.RechargeDto;
import com.guiji.billing.entity.BillingUserAcctBean;
import com.guiji.billing.utils.IdWorker;
import com.guiji.billing.utils.ResHandler;
import com.guiji.billing.vo.UserAcctThresholdVo;
import com.guiji.notice.api.INoticeSend;
import com.guiji.notice.enm.NoticeType;
import com.guiji.notice.entity.MessageSend;
import com.guiji.utils.JsonUtils;
import com.guiji.wechat.vo.SendMsgReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Component
public class MsgNotifyComponent {

    private Logger logger = LoggerFactory.getLogger(MsgNotifyComponent.class);

    @Autowired
    private INoticeSend iNoticeSend;

    @Autowired
    private IdWorker idWorker;

    @Value("${weixin.threshold.appid}")
    String thresholdAppid;
    @Value("${weixin.threshold.templateId}")
    String thresholdTemplateId;
    @Value("${weixin.threshold.pagePath.callReordUrl}")
    String thresholdCallReordUrl;
    @Value("${weixin.threshold.pagePath.reordListUrl}")
    String thresholdReordListUrl;

    /**
     * 低于阈值，消息通知
     * @param thresholdVoList
     */
    public void notifyByThreshold(List<UserAcctThresholdVo> thresholdVoList){
        if(null != thresholdVoList && thresholdVoList.size()>0) {
            for(UserAcctThresholdVo thresholdVo: thresholdVoList) {
                String logId = idWorker.nextId();
                String threshold = thresholdVo.getThresholdValue();
                BigDecimal amount = new BigDecimal(threshold).divide(new BigDecimal(100));
                MessageSend messageSend = new MessageSend();
                try {
                    messageSend.setNoticeType(NoticeType.money_not_enough);
                    messageSend.setOrgCode(thresholdVo.getOrgCode());
                    //站内信
                    messageSend.setMailContent("尊敬的"+thresholdVo.getCompanyName()+"，您的账户余额已不足￥"+amount+"，为了不影响您的外呼任务，请及时充值。详情请登录后台费用中心进行查看，感谢您的使用。");
                    //邮箱
                    messageSend.setEmailSubject("余额不足");
                    messageSend.setEmailContent("尊敬的"+thresholdVo.getCompanyName()+"，您的账户余额已不足￥"+amount+"，为了不影响您的外呼任务，请及时充值。详情请登录后台费用中心进行查看，感谢您的使用。");
                    //短信
                    messageSend.setSmsContent("尊敬的"+thresholdVo.getCompanyName()+"，您的账户余额已不足￥"+amount+"，为了不影响您的外呼任务，请及时充值。详情请登录后台费用中心进行查看，感谢您的使用。");
                    //微信
                    messageSend.setWeixinAppId(thresholdAppid);
                    messageSend.setWeixinTemplateId(thresholdTemplateId);
                    messageSend.setWeixinPagePath(thresholdCallReordUrl);
                    HashMap<String, SendMsgReqVO.Item> map = new HashMap<>();
                    map.put("userName",new SendMsgReqVO.Item("【硅基智能】尊敬的"+thresholdVo.getCompanyName()+"，您的账户余额已不足￥"+amount+"，为了不影响您的外呼任务，请及时充值。详情请登录后台费用中心进行查看，感谢您的使用。",null));
                    messageSend.setWeixinData(map);

                    logger.info("低于阈值，消息通知，日志ID:{},入参数据:{}", logId, JsonUtils.bean2Json(messageSend));
                    //发送消息
                    ResHandler.getResObj(iNoticeSend.sendMessage(messageSend));
                    logger.info("低于阈值，消息通知，日志ID:{}", logId);
                } catch (Exception e) {
                    logger.error("低于阈值，消息通知异常，日志ID:{},入参数据:{}", logId, JsonUtils.bean2Json(messageSend), e);
                }
            }
        }
    }

    /**********充值****************/
    @Value("${weixin.recharge.appid}")
    String rechargeAppid;
    @Value("${weixin.recharge.templateId}")
    String rechargeTemplateId;
    @Value("${weixin.recharge.pagePath.callReordUrl}")
    String rechargeCallReordUrl;
    @Value("${weixin.recharge.pagePath.reordListUrl}")
    String rechargeReordListUrl;

    /**
     * 充值消息通知
     * @param acct
     * @param rechargeDto
     */
    public void notifyByRecharge(BillingUserAcctBean acct, RechargeDto rechargeDto){
        if(null != acct && null != rechargeDto) {
            String logId = idWorker.nextId();
            MessageSend messageSend = new MessageSend();
            try {
                BigDecimal amount = acct.getAvailableBalance().divide(new BigDecimal(100));
                messageSend.setNoticeType(NoticeType.recharge_to_account);
                messageSend.setOrgCode(acct.getOrgCode());
                //站内信
                messageSend.setMailContent("尊敬的"+acct.getCompanyName()+"，您的充值已成功，您的账户当前余额￥"+amount+"。");
                //邮箱
                messageSend.setEmailSubject("充值到账");
                messageSend.setEmailContent("尊敬的"+acct.getCompanyName()+"，您的充值已成功，您的账户当前余额￥"+amount+"。");
                //短信
                messageSend.setSmsContent("尊敬的"+acct.getCompanyName()+"，您的充值已成功，您的账户当前余额￥"+amount+"。");
                //微信
                messageSend.setWeixinAppId(rechargeAppid);
                messageSend.setWeixinTemplateId(rechargeTemplateId);
                messageSend.setWeixinPagePath(rechargeCallReordUrl);
                HashMap<String, SendMsgReqVO.Item> map = new HashMap<>();
                map.put("userName", new SendMsgReqVO.Item("【硅基智能】尊敬的"+acct.getCompanyName()+"，您的充值已成功，您的账户当前余额￥"+acct.getAvailableBalance()+"。", null));
                messageSend.setWeixinData(map);

                logger.info("充值消息通知，日志ID:{},入参数据:{}", logId, JsonUtils.bean2Json(messageSend));
                //发送消息
                ResHandler.getResObj(iNoticeSend.sendMessage(messageSend));
                logger.info("充值消息通知，日志ID:{}", logId);
            } catch (Exception e) {
                logger.error("充值消息通知异常，日志ID:{},入参数据:{}", logId, JsonUtils.bean2Json(messageSend), e);
            }
        }
    }

}
