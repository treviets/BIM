package com.redsun.service.controller;

import com.redsun.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="category")
public class CategoriesController { 
		 		
	@Autowired
    CategoryService categoryService;
	
    @RequestMapping(value="/list", method={RequestMethod.GET})
    public Object listCategories(Model model) {
    	return categoryService.getAllCategories();
    }
    @RequestMapping(value="/{categoryId}", method={RequestMethod.GET})
    public Object getCategoryData( @PathVariable("categoryId") int categoryId, Model model) {
    	
        return categoryService.getCategoryData(categoryId);
    }

}
