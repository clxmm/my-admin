package org.clxmm.autocode.system.fileupload.minio;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.autocode.autocode.entity.UploadFile;
import org.clxmm.autocode.autocode.service.UploadFileService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class ImgFileClient {

    private MinioFileClient minioFileClient;
    private UploadFileService uploadFileService;

    public ImgFileClient(MinioFileClient minioFileClient, UploadFileService uploadFileService) {
        this.minioFileClient = minioFileClient;
        this.uploadFileService = uploadFileService;
    }

    public String uploadImg(MultipartFile file) {
        String bucket = "img";
        String originalFilename = file.getOriginalFilename();
        UploadFile uploadFile = new UploadFile();
        uploadFile.setOriginalName(originalFilename);
        uploadFile.setBucket(bucket);

        String contentType = file.getContentType();
        Calendar calendar = Calendar.getInstance();
        UUID uuid = UUID.randomUUID();

        String type = originalFilename.substring(originalFilename.lastIndexOf("."));
        String raname = uuid.toString().replace("-", "") + type;
        String path = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + raname;
        uploadFile.setNewName(raname);
        uploadFile.setObjectKey(path);
        uploadFile.setContentType(contentType);

        String url = "";
        try {
            url = minioFileClient.uploadInputImgStream(bucket, path, file.getInputStream());
        } catch (Exception e) {
            uploadFile.setUpFlag(9);
            log.error("上传图片失败：" + e.getMessage());
        }
        uploadFile.setUrl(url);
        uploadFileService.save(uploadFile);

        return url;
    }

}
