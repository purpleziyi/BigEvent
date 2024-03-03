package cc.ziyiz.controller;

import cc.ziyiz.pojo.Result;
import cc.ziyiz.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception{
        //Store file contents to local disk
        String originalFilename = file.getOriginalFilename();
        // Ensure that file names are unique to prevent file overwriting
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(new File("C:\\Users\\zanez\\Desktop\\file\\"+filename));
        String url = AliOssUtil.uploadFile(filename,file.getInputStream());
        return Result.success(url);
    }
}
