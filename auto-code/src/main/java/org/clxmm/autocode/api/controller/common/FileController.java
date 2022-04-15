package org.clxmm.autocode.api.controller.common;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.clxmm.autocode.autocode.entity.UploadFile;
import org.clxmm.autocode.autocode.service.UploadFileService;
import org.clxmm.autocode.common.Result;
import org.clxmm.autocode.system.fileupload.minio.ImgFileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "文件上传")
@RequestMapping("file")
public class FileController {

    @Autowired
    private ImgFileClient imgFileClient;


    @ApiOperation("上传图片")
    @PostMapping("uploadImg")
    public Result uploadImg(MultipartFile file) {
        String imgUrl = imgFileClient.uploadImg(file);
        Map<String,String> map = new HashMap<>();
        map.put("url",imgUrl);
        return  Result.ok(map);
    }

}
