package com.blog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Value("${path.image}")
    private String imageDir;
    @PostMapping()
    public String UploadImg(@RequestParam("file") MultipartFile file) throws IOException {
        // 名字容易重复,进行改名
        String filename = new Date().getTime() + file.getOriginalFilename();
        // 后端存储地址
        String savePath=imageDir+filename;
        // 保存文件
        file.transferTo(new File(savePath));
        return filename;
    }
    @DeleteMapping("/{img}")
    public boolean DeleteImg(@PathVariable String img){
        // 创建文件对象
        File file = new File(imageDir+img);

        if (file.isFile() && file.exists()) { // 路径为文件且不为空则进行删除
            if (file.delete()) {
                return true;
            }
        }
        return false;
    }
}
