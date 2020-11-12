package service;

import model.Category;

import java.util.List;

public interface iCategoryService {
    Category findById(int id);
    List<Category> findAll();

}
