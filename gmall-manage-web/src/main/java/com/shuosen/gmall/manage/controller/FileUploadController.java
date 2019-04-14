package com.shuosen.gmall.manage.controller;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@CrossOrigin
public class FileUploadController {

    @Value("${fileServer.url}")
    String  fileUrl;

    @PostMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file")MultipartFile file  ) throws IOException, MyException {
       String imageUrl = fileUrl;
        if(file!=null){
            String configFile = this.getClass().getResource("/tracker.conf").getFile();
            ClientGlobal.init(configFile);
            TrackerClient trackerClient=new TrackerClient();
            TrackerServer trackerServer=trackerClient.getConnection();
            StorageClient storageClient=new StorageClient(trackerServer,null);
            String fileName = file.getOriginalFilename();
            String extName = StringUtils.substringAfterLast(fileName, ".");

            String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);
            for (int i = 0; i < upload_file.length; i++) {
                String path = upload_file[i];
                imageUrl+="/"+path;
            }
        }
      return imageUrl;
    }


}
