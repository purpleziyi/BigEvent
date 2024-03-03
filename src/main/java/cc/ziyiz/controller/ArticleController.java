package cc.ziyiz.controller;


import cc.ziyiz.pojo.Article;
import cc.ziyiz.pojo.PageBean;
import cc.ziyiz.pojo.Result;
import cc.ziyiz.service.ArticleService;
import cc.ziyiz.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // new article add
    @PostMapping
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb =  articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }

    @DeleteMapping
    public Result delete(Integer id){
        articleService.deleteById(id);
        return Result.success();
    }

    // get details of article
    @GetMapping("/detail")
    public Result<Article> detail(Integer id){
        Article a = articleService.findById(id);
        return Result.success(a);
    }

}
