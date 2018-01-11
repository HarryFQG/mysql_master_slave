package com.yijiupi.login.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 文件上传工具类
 * @author fengqigui
 * @Date 2017/11/8 15:57
 */
public class UploadImageUtil {

    public static final String PIC_HOST="http://127.0.0.1:8010";

    public static String uploadSpringMvc(HttpServletRequest request, String fileName, String savePath){

        // 1.将request强转为多部件请求对象
        MultipartHttpServletRequest msr=(MultipartHttpServletRequest) request;

        // 更据文件名称获取文件对象.和是springmvc.xml的文件类型必须匹配（）
        CommonsMultipartFile cm=(CommonsMultipartFile) msr.getFile(fileName);

        // 获取文件上传流
        byte[] b=cm.getBytes();

        //文件名称在服务器有可能重复？不能
        String newFileName= UUID.randomUUID().toString().replace("-", "");

        // 获取文件的扩展名
        String originalFilename = cm.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //创建jesy服务器，进行跨服务上传
        Client client=Client.create();

        // 把文件关联到远程服务器
        WebResource resource = client.resource(UploadImageUtil.PIC_HOST+savePath+newFileName+suffix);

        // 开始上传文件
        resource.put(String.class, b);

        // ajax回调函数需要回显什么？
        // 图片需要回显：图片的完整路径名
        // 数据库保存图片相对路径
        String fullPath=UploadImageUtil.PIC_HOST+"/upload/"+newFileName+suffix;

        String relativePath=savePath+newFileName+suffix;

        String result="{\"fullPath\":\""+fullPath+"\",\"relativePath\":\""+relativePath+"\"}";

        return result;
    }

}
