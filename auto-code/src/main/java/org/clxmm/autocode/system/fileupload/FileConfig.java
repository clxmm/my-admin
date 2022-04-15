package org.clxmm.autocode.system.fileupload;

import org.clxmm.autocode.autocode.service.UploadFileService;
import org.clxmm.autocode.system.fileupload.conf.MinioConf;
import org.clxmm.autocode.system.fileupload.minio.ImgFileClient;
import org.clxmm.autocode.system.fileupload.minio.MinioFileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfig {
    @Bean
    public MinioConf minioConf() {
        return new MinioConf();
    }

    @Bean
    public MinioFileClient minioFileClient() {
        return new MinioFileClient(minioConf());
    }

    @Autowired
    UploadFileService uploadFileService;


    @Bean
    public ImgFileClient imgFileClient() {
        return new ImgFileClient(minioFileClient(), uploadFileService);
    }

}
