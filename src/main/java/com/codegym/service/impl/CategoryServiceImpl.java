package com.codegym.service.impl;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.ICategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/democustomer", "root", "123456");
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public CategoryServiceImpl() {
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from category;")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                categories.add(new Category(id,name));
            }
        } catch (SQLException e) {
        }
        return categories;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public Category findById(int id) {
       Category category = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from category where id =?;")) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                category = new Category(id,name);
            }
        } catch (SQLException e) {
        }
        return category;
    }

    @Override
    public void update(int id, Category category) {

    }

    @Override
    public void remove(int id) {

    }
}
