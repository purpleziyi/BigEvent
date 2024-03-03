package cc.ziyiz.service;

import cc.ziyiz.pojo.Category;

import java.util.List;

public interface CategoryService {
    // add category
    void add(Category category);

    // list query
    List<Category> list();

    // query classification info by ID
    Category findById(Integer id);

    // update category
    void update(Category category);

    // delete category by id
    void deleteById(Integer id);
}
