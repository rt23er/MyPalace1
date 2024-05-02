package com.example.service;

import com.example.entity.Image3D;
import com.example.mapper.ImageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServer {


    // 文件上传存储路径
    private static final String filePath = System.getProperty("user.dir") + "\\files\\";
    
    @Resource
    ImageMapper imageMapper;

    public String select(String userId, String groupId) {
        return  imageMapper.select(userId,groupId);
    }

    public void update(Image3D image3D) {
        imageMapper.upodate(image3D);
    }

    public void insert(Image3D image3D) {
        imageMapper.insert(image3D);
    }

    public List<Image3D> getGroupImage(String userId) {
         return imageMapper.getGroupImage(userId);
    }

    public List<String> delGroupImage(String userId) {
        List<Image3D> groupImage = getGroupImage(userId);
        List<String> imageIdList = new ArrayList<>();
        String filePathUrl = "";
        for (Image3D image3D : groupImage) {
            imageMapper.delGroupImage(image3D.getImageId());
            filePathUrl = filePath + image3D.getImageUrl().split("/")[image3D.getImageUrl().split("/").length-1];
            //删除在文件夹中的路径
            File file = new File(filePathUrl);
            if (file.exists()) {
                file.delete();
            }
            //删除数据库中的路径
            imageIdList.add(image3D.getImageUrl()+"删除成功");
        }
        return imageIdList;
    }
}
