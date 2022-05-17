package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Customer;
import com.codegym.model.Product;
import com.codegym.service.ICategoryService;
import com.codegym.service.IProductService;
import com.codegym.service.impl.CategoryServiceImpl;
import com.codegym.service.impl.CustomerServiceImpl;
import com.codegym.service.impl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    IProductService productService = new ProductServiceImpl();
    ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        List<Product> products = productService.findAll();
        List<Category> categories = findAllCategories(products);
        request.setAttribute("productList", products);
        request.setAttribute("categories", categories);
        dispatcher.forward(request, response);
    }

    protected List<Category> findAllCategories(List<Product> products) {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Category category = categoryService.findById(products.get(i).getCategoryId());
            categories.add(category);
        }
        return categories;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

