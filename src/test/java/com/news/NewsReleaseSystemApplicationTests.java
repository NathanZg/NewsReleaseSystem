package com.news;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.news.entity.Comment;
import com.news.entity.NewsComment;
import com.news.entity.NewsData;
import com.news.entity.vo.PageVo;
import com.news.entity.vo.QueryVo;
import com.news.service.CommentService;
import com.news.service.NewsCommentService;
import com.news.service.NewsDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class NewsReleaseSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    //    @Test
//    void generator() {
//        String dbUrl = "jdbc:mysql://localhost:3306/nrs";
//        String username = "root";
//        String password = "root";
//        String projectPath = System.getProperty("user.dir");
//        String outputDir = projectPath + "\\src\\main\\java";
//        String mapperDir = projectPath + "\\src\\main\\resources\\mapper";
//        FastAutoGenerator.create(dbUrl, username, password)
//                .globalConfig(builder -> {
//                    builder.author("Sancean") // 设置作者
//                            .disableOpenDir()
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir(outputDir); // 指定输出目录
//                })
//                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
//                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
//                    if (typeCode == Types.SMALLINT) {
//                        // 自定义类型转换
//                        return DbColumnType.INTEGER;
//                    }
//                    return typeRegistry.getColumnType(metaInfo);
//
//                }))
//                .packageConfig(builder -> {
//                    builder.parent("com") // 设置父包名
//                            .moduleName("news") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperDir)); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    // 设置需要生成的表名
//                    builder.addInclude("user")
//                            .addInclude("type")
//                            .addInclude("comment")
//                            .addInclude("news_data")
//                            .addInclude("news_type")
//                            .addInclude("news_comment")
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                    builder.entityBuilder().enableLombok();
//                    builder.mapperBuilder().enableMapperAnnotation();
//                    builder.serviceBuilder()
//                            .formatServiceFileName("%sService")
//                            .formatServiceImplFileName("%sServiceImpl");
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
    @Autowired
    private CommentService commentService;
    @Autowired
    private NewsCommentService newsCommentService;
    @Autowired
    private NewsDataService newsDataService;
    @Test
    void getAll()
    {
        List<NewsComment> commentList= newsCommentService.getAll();
        for(NewsComment com:commentList)
            System.out.println("NewId:"+com.getNewsId()+" CommentId:"+com.getCommentId());
    }

    @Test
    public void Insert()
    {
//        Comment comment=new Comment();
//        comment.setUser("D");
//        comment.setCommentData("Aaaaaaaaaaaaaaaaaaaaa");
//        comment.setCommentDate(LocalDateTime.parse("2023-05-19 17:56:38", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        commentService.insert(comment);
        NewsComment newsComment=new NewsComment();
        newsComment.setNewsId(6);
        newsComment.setCommentId(13);
        newsCommentService.insert(newsComment);
    }

    @Test
    void deleteD()
    {
//        List<Integer>list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        newsCommentService.batch_delete(list);
    }

    @Test
    void update(){
        Comment comment=new Comment();
        comment.setId(7);
        comment.setUser("C");
        comment.setCommentData("Aaaaaaaaaaaaaaaaaaaaa");
        comment.setCommentDate(LocalDateTime.parse("2023-05-19 17:56:38", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        commentService.updateId(comment);
    }

    @Test
    void selectCommentByNews()
    {
        List<Comment>newsCommentList=newsCommentService.getCommentByNews(1);
        System.out.println(newsCommentList);
    }

    @Test
    void insertNewsData()
    {
        NewsData newsData=new NewsData();
        newsData.setData("asdasbhasbfus");
        newsData.setDate(LocalDateTime.parse("2023-05-02 16:25:17",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        newsData.setPublisher("诞");
        newsData.setTitle("大标题6");
        newsDataService.insertData(newsData);
    }

    @Test
    void selectNewsDataPage()
    {
        QueryVo queryVo=new QueryVo();
        queryVo.setCurrent(0L);
        queryVo.setSize(2L);
        PageVo<NewsData> newsDataPageVo=newsDataService.pageQueryByCondition(queryVo);
//        System.out.println(newsDataPageVo.getRecords());
    }
}
