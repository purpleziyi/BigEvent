package cc.ziyiz.service.impl;

import cc.ziyiz.mapper.ArticleMapper;
import cc.ziyiz.pojo.Article;
import cc.ziyiz.pojo.PageBean;
import cc.ziyiz.service.ArticleService;
import cc.ziyiz.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        // add attribute value
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id"); // get userId from ThreadLocal
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // create PageBean-obj for encapsulate data
        PageBean<Article> pb = new PageBean<>();

        /** use paging to optimize system */
        int startIndex = (pageNum - 1) * pageSize;
        // Call mapper to query the total number of records
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        long totalCount = articleMapper.count(userId, categoryId, state);

        // Call mapper to query the current page data
        List<Article> as = articleMapper.list(userId, categoryId, state, startIndex, pageSize);


        // make PageBean object properties
        pb.setTotal(totalCount);
        pb.setItems(as);
        return pb;

    }

    @Override
    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
    }


    @Override
    public Article findById(Integer id) {
        Article a = articleMapper.findById(id);
        return a;
    }
}
