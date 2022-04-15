package org.clxmm.autocode.system.fileupload.minio;


import io.minio.*;
import io.minio.http.Method;
import org.clxmm.autocode.system.fileupload.conf.MinioConf;
import org.springframework.stereotype.Component;

import java.io.InputStream;


public class MinioFileClient {

    /**
     * minio参数
     */
    private String ENDPOINT = "";
    private String ACCESS_KEY = "";
    private String SECRET_KEY = "";

    private String BUCKET = "default";


    /**
     * bucket权限-只读
     */
    private String READ_ONLY = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET + "/*\"]}]}";
    /**
     * bucket权限-只读
     */
    private String WRITE_ONLY = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET + "/*\"]}]}";
    /**
     * bucket权限-读写
     */
    private String READ_WRITE = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\",\"s3:AbortMultipartUpload\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET + "/*\"]}]}";


    public MinioFileClient(MinioConf minioConf) {
        this.ENDPOINT = minioConf.getUrl();
        this.ACCESS_KEY = minioConf.getAccessKey();
        this.SECRET_KEY = minioConf.getSecretKey();
    }


    /**
     * 创建桶
     *
     * @param bucket 桶
     */
    public void makeBucket(String bucket) throws Exception {
        MinioClient client = MinioClient.builder().endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
        // 判断桶是否存在
        boolean isExist = client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!isExist) {
            // 新建桶
            client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }


    /**
     * 更新桶权限策略
     *
     * @param bucket 桶
     * @param policy 权限
     */
    public void setBucketPolicy(String bucket, String policy) throws Exception {
        MinioClient client = MinioClient.builder().endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
        switch (policy) {
            case "read-only":
                client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucket).config(READ_ONLY.replace(BUCKET, bucket)).build());
                break;
            case "write-only":
                client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucket).config(WRITE_ONLY.replace(BUCKET, bucket)).build());
                break;
            case "read-write":
                client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucket).config(READ_WRITE.replace(BUCKET, bucket)).build());
                break;
            case "none":
            default:
                break;
        }
    }


    /**
     * 文件url前半段
     *
     * @param bucket 桶
     * @return 前半段
     */
    public String getObjectPrefixUrl(String bucket) {
        return String.format("%s/%s/", ENDPOINT, bucket);
    }


    /**
     * 流式上传图片
     *
     * @param bucket      桶
     * @param objectKey   文件名称，加上文件的 .类型
     * @param inputStream 文件输入流
     * @return 文件url
     */
    public String uploadInputImgStream(String bucket, String objectKey, InputStream inputStream) throws Exception {
        MinioClient client = MinioClient.builder().endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
        client.putObject(PutObjectArgs.builder().bucket(bucket).object(objectKey).stream(inputStream, inputStream.available(), -1).contentType("image/png").build());
        return getObjectPrefixUrl(bucket) + objectKey;
    }


    /**
     * 删除文件
     *
     * @param bucket    桶
     * @param objectKey 文件key
     */
    public void deleteFile(String bucket, String objectKey) throws Exception {
        MinioClient client = MinioClient.builder().endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
        client.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(objectKey).build());
    }


    /**
     * 获取文件签名url
     *
     * @param bucket    桶
     * @param objectKey 文件key
     * @param expires   签名有效时间  单位秒
     * @return 文件签名地址
     */
    public String getSignedUrl(String bucket, String objectKey, int expires) throws Exception {
        MinioClient client = MinioClient.builder().endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
        return client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucket).object(objectKey).expiry(expires).build());
    }


    /**
     * 下载文件
     *
     * @param bucket    桶
     * @param objectKey 文件key
     * @return 文件流
     */
    public InputStream download(String bucket, String objectKey) throws Exception {
        MinioClient client = MinioClient.builder().endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
        return client.getObject(GetObjectArgs.builder().bucket(bucket).object(objectKey).build());
    }


}
