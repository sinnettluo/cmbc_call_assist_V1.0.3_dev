package com.guiji.fsagent.util;

import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class FileUtil {
    /**
     * 删除文件
     * @param fileName
     * @return
     */
    public static boolean delete(String fileName){
        File file = new File(fileName);
        return file.delete();
    }

    /**
     * 判断文件是否存在
     * @param fileName
     * @return
     */
    public static boolean isExist(String fileName){
        File file = new File(fileName);
        return file.exists();
    }

    /**
     * 删除n天前的文件
     * @param Folder
     * @param data
     */
    public static boolean deleteFilesByDay(String Folder,int data){
        Date date = new Date(System.currentTimeMillis() - data*24*60*60*1000);
        File folder = new File(Folder);
        File[] files = folder.listFiles();
        for (int i=0;i<files.length;i++){
            File file = files[i];
            if (new Date(file.lastModified()).before(date)){
                if(!file.delete()){
                    return false;
                }
            }
        }
        return true;
    }

    public MultipartFile  toMultipartFile(String fileName) throws IOException{
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        return multipartFile;

    }



}