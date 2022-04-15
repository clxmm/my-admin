package org.clxmm.autocode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.clxmm.autocode.util.redis.RedisDBChangeUtil;
import org.clxmm.autocode.util.redis.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
public class RedisUtilsTest {

    @Test
    void contextLoads() {
    }

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void test1() {
        redisUtils.hmSet("tKey", "1", "v1");
    }


    @Test
    public void test2() {
        Object tKey = redisUtils.hmGet("tKey", "1");
        System.out.println(tKey);
    }


    @Test
    public void test3() {
        redisUtils.lPush("listKey1", "测试1");
    }

    @Test
    public void test4() {
        List<Object> listKey1 = redisUtils.lRange("listKey1", 0, 10);
        listKey1.forEach(System.out::println);
    }


    @Test
    public void test5() {
        redisUtils.addSte("ketSet", "测试");
        redisUtils.addSte("ketSet", "测试2");
        redisUtils.addSte("ketSet", "测试3");
        redisUtils.addSte("ketSet", "测试");
    }


    @Test
    public void test6() {
        Set<Object> ketSet = redisUtils.getSet("ketSet");
        ketSet.forEach(System.out::println);
    }


    @Test
    public void test7() {
        redisUtils.zAdd("keyZset", "k1", 3.0);
        redisUtils.zAdd("keyZset", "k3", 2.0);
        redisUtils.zAdd("keyZset", "k2", 1.0);
    }


    @Test
    public void test8() {
        Set<Object> keyZset = redisUtils.rangeByScore("keyZset", 1.0, 3.0);
        keyZset.forEach(System.out::println);
    }


    @Test
    public void test9() {
        redisUtils.sGet("ketSet").forEach(System.out::println);
    }


    @Test
    public void test10() {
        System.out.println(redisUtils.sHaaKey("ketSet", "测试0"));
    }


    @Autowired
    RedisDBChangeUtil redisDBChangeUtil;

    @Test
    public void test11() {

        redisUtils.hmSet("tKey", "1", "v11");
        redisDBChangeUtil.setDataBase(7);
        redisUtils.hmSet("tKey", "1", "v111");

    }


    @Test
    public void testJson() {


        String
                msg = "[{\"eventId\":\"330421001-20211203084447-744\",\"createTime\":\"2021-12-03 08:45:02\",\"uploadText\":\"文件名称:01-330102004-202112030845021481700.xml 交换信息：={\\\"result\\\":false,\\\"desc\\\":{\\\"error-code\\\":3,\\\"error-msg\\\":\\\"只有病种是艾滋和HIV时，接触史才可以选择“商业，非商业”/\\\",\\\"error-des\\\":\\\"业务校验错误\\\"}} 接受时间:=2021-12-03 08:45:16 消息状态:= 验证错误\"}, {\"eventId\":\"330421002-20211203083710-977\",\"createTime\":\"2021-12-03 08:37:24\",\"uploadText\":\"文件名称:01-330102004-202112030837239461610.xml 交换信息：={\\\"result\\\":true,\\\"ids\\\":{\\\"diseaseInfo\\\":{\\\"CardCode\\\":\\\"330421002-2021-01937\\\",\\\"Id\\\":\\\"211203916246788805365760\\\",\\\"CardId\\\":\\\"916246788805365760\\\"}}}\"}]";
        JSONArray jsonArray = JSONArray.parseArray(msg);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            // 报卡 eventId
            String eventId = jsonObject.getString("eventId");
            // 获取 上传内容
            String uploadText = jsonObject.getString("uploadText");
            // 上传内容 处理
            // uploadText = uploadText.substring(uploadText.indexOf("=")
            // + 1, uploadText.length());
            uploadText = uploadText.substring(
                    uploadText.indexOf("=") + 1,
                    uploadText.indexOf(" 接受时间") == -1 ? uploadText
                            .length() : uploadText.indexOf(" 接受时间"));
            uploadText = uploadText.replace("\\", "");
            jsonObject = JSONObject.parseObject(uploadText);
            String returnResult = jsonObject.getString("result");

            if (!"true".equals(returnResult)) {
                if (jsonObject.getJSONObject("desc")!=null) {
                    String errorMsg = jsonObject.getJSONObject("desc").getString("error-msg");

                }
                String desc = jsonObject.getJSONObject("desc").getString("error-msg");

                System.out.println("error-msg" + desc);
            }

        }


        System.out.println(msg);
    }


}
