package org.beyond.service;

import org.beyond.model.Category;
import org.beyond.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryByID(Long Id){
        return categoryRepository.findByid(Id);
    }
}
