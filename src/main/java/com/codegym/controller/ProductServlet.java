package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.ICategoryService;
import com.codegym.service.IProductService;
import com.codegym.service.impl.CategoryServiceImpl;
import com.codegym.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    IProductService productService = new ProductServiceImpl();
    ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showListProduct(request, response);
        }

    }

    private void filterByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int catId = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        List<Product> products = productService.findAll();
        List<Product> searchList = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategoryId() == catId) {
                searchList.add(p);
            }
        }
        List<Category> categories1 = categoryService.findAll();
        List<Category> categories = findAllCategories(products);
        request.setAttribute("productList", searchList);
        request.setAttribute("categories", categories);
        request.setAttribute("categories1", categories1);
        dispatcher.forward(request, response);
    }

    private void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        int catId = 0;
        if (request.getParameter("id") != null) catId = Integer.parseInt(request.getParameter("id"));
        List<Category> categories;
        List<Product> products;
        List<Category> categories1 = categoryService.findAll();
        if (catId == 0) {
            products = productService.findAll();
            categories = findAllCategories(products);
        } else {
            products = productService.filterByCategory(catId);
            categories = findAllCategories(products);
        }
        request.setAttribute("productList", products);
//        request.setAttribute("productList", searchList);
        request.setAttribute("categories", categories);
        request.setAttribute("categories1", categories1);
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

