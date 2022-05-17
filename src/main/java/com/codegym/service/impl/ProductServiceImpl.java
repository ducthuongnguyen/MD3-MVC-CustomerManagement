package com.codegym.service.impl;

import com.codegym.model.Product;
import com.codegym.service.IProductService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService {
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
    List<Product> products;
    public ProductServiceImpl() {
     products = new ArrayList<>();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select* from products;")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int categoryId = rs.getInt("categoryId");
                products.add(new Product(id, name, price, categoryId));
            }
        } catch (SQLException e) {
        }
        return products;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public void update(int id, Product product) {

    }

    @Override
    public void remove(int id) {

    }
}
