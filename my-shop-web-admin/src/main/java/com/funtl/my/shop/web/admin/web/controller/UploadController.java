package com.funtl.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传控制器
 * @Param dropFile Dropzone
 * @Param editorFile wangEditor
 */
@Controller
public class UploadController {
    private static final String UPLOAD_PATH="/static/upload/";

    @ResponseBody
    @RequestMapping(value="upload",method= RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropFile, HttpServletRequest request,MultipartFile editorFile){
        Map<String,Object> result=new HashMap<>();
        //前端上传的文件
        MultipartFile myFile=dropFile==null?editorFile:dropFile;
        //获取文件路径
        String fileName=myFile.getOriginalFilename();
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."));
        String path=request.getSession().getServletContext().getRealPath("/");
        //文件存放路径
        String filePath=path.substring(0,66)+"src/main/webapp"+UPLOAD_PATH;
        System.out.println(filePath);
        //判断文件是否存在，不存在则创建文件夹
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        //将文件写入目标
        file=new File(filePath, UUID.randomUUID()+fileSuffix);
        try {
            myFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Dropzone上传
        if(dropFile!=null){
            result.put("fileName",UPLOAD_PATH+file.getName());
        }
        //wangEditor上传
        else{
            /**
             *  Schema:服务端提供的协议，http或https
             *  serverName:服务器名称loclhost/ip/domain
             *  serverPort:服务器端口
             */
            String serverPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
            result.put("errno",0);
            result.put("data",new String[] {serverPath+"/static/upload/"+file.getName()});
            System.out.println(serverPath+"/static/upload/"+file.getName());
        }
        return result;
    }
}
