package com.codegym.service.impl;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements CustomerService {
    List<Customer> customers;

    public CustomerServiceImpl() {
        customers = new ArrayList<>();

    }
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

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from customers")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                customers.add(new Customer(id, name, email, address));
            }
        } catch (SQLException e) {
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public void update(int id, Customer customer) {

    }
//
//    @Override
//    public void save(Customer customer) {
//        customers.put(customer.getId(), customer);
//    }
//
//    @Override
//    public Customer findById(int id) {
//        return customers.get(id);
//    }
//
//    @Override
//    public void update(int id, Customer customer) {
//        customers.put(id, customer);
//    }

    @Override
    public void remove(int id) {
        customers.remove(id);
    }
}
