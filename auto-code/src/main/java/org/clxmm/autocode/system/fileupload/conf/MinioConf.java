package org.clxmm.autocode.system.fileupload.conf;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file.minio")
@Data
public class MinioConf {
    private String url;

    private String accessKey;

    private String secretKey;
}
