//package org.clxmm.autocode.api.controller.es;
//
//import org.clxmm.autocode.el.entry.Blog;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
//import org.springframework.data.elasticsearch.core.query.IndexQuery;
//import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//@RestController
//@RequestMapping("es")
//public class DataTestController {
//
//    @Autowired
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//
//    @GetMapping("/addTest")
//    public String testElSearch() {
//        //该list用于添加需要存入的数据
//        List<IndexQuery> indexQueryList = new ArrayList<>();
//
//        //模拟一些数据
//
//        Blog blog = new Blog();
//        blog.setId((long) new Random().nextInt(1500));
//        blog.setMasterName("JCccc");
//        blog.setArticleNum(10);
//        blog.setCommentNum(29);
//        blog.setThumbNum(100);
//        blog.setDescription("分享不仅为了别人,也是为了自己");
//
//        //把这个数据放入indexQueryList
//        indexQueryList.add(new IndexQueryBuilder().withObject(blog).build());
//
//        //循环模拟一些数据
//        for (int i = 1; i <= 6; i++) {
//
//            Blog blog2 = new Blog();
//            blog2.setId((long) new Random().nextInt(1500));
//            blog2.setMasterName("Test");
//            blog2.setArticleNum(i * 60);
//            blog2.setCommentNum(i * 16);
//            blog2.setThumbNum(i * 500);
//            blog2.setDescription("测试添加" + i);
//            indexQueryList.add(new IndexQueryBuilder().withObject(blog2).build());
//        }
//        elasticsearchRestTemplate.bulkIndex(indexQueryList, Blog.class);
//        return "add success ";
//
//    }
//
//
//
//    @GetMapping("/getTestData")
//    public  List<Blog> getTestData(){
//
//
//        //精确查询
//        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.matchPhraseQuery("masterName", "JCccc"))
//                .build();
//
//
//        return list;
//    }
//
//}
