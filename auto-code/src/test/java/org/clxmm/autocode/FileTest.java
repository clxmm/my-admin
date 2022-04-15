package org.clxmm.autocode;

import org.clxmm.autocode.system.fileupload.minio.MinioFileClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;

@SpringBootTest
public class FileTest {


    @Autowired
    private MinioFileClient minioFileClient;

    @Test
    public void test1() throws Exception {
        FileInputStream inputStream = new FileInputStream(new File("D:\\DDdoc\\项目\\QQ截图20200911175119.png"));
        String s = minioFileClient.uploadInputImgStream("img", "20220103/test12.png", inputStream);
        System.out.println(s);
        minioFileClient.deleteFile("img", "20220103/test1.png");
    }

}
