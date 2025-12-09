package org.example;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    DBConnexion dbConnexion;
    public DataRetriever(){
        dbConnexion = new DBConnexion();
    }

    public List<Category> getAllCategories() {
        String query = "select id, name from Product_category;";

        List<Category> categories = new ArrayList<>();
        try {
            Connection connection = this.dbConnexion.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public List<Product> getProductList (int page, int size){
        int offset = (page - 1) * size;
        String query =
                "select Product.id, Product.name, Product.creation_datetime, " +
                        "Product_category.id as c_id, Product_category.name as c_name " +
                        "from Product " +
                        "inner join Product_category " +
                        "on Product_category.product_id = Product.id " +
                        "limit ? offset ?;";
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = this.dbConnexion.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, size);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("c_id"),
                        rs.getString("c_name")
                );
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("creation_datetime").toInstant(),
                        category
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Product> getProductsByCriteria(String productName, String categoryName,
                                               Instant creationMin, Instant creationMax){
        List<Product> products = new ArrayList<>();

        String query = "select Product.id, Product.name, Product_category.id as c_id, Product_category.name as c_name, creation_datetime " +
                "from Product " +
                "inner join Product_category " +
                "on Product.id = Product_category.product_id " +
                "where (?::text is null or Product.name ilike ?) "+
                "and (?::text is null or Product_category.name ilike ?) "+
                "and (?::timestamp is null or Product.creation_datetime >= ?::timestamp) "+
                "and (?::timestamp is null or Product.creation_datetime <= ?::timestamp)";

        try {
            Connection connection = this.dbConnexion.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, productName);
            stmt.setString(2, productName);
            stmt.setString(3, categoryName);
            stmt.setString(4, categoryName);
            if (creationMin != null) {
                stmt.setTimestamp(5, Timestamp.from(creationMin));
                stmt.setTimestamp(6, Timestamp.from(creationMin));
            } else {
                stmt.setTimestamp(5,null);
                stmt.setTimestamp(6, null);
            }

            if (creationMax != null) {
                stmt.setTimestamp(7, Timestamp.from(creationMax));
                stmt.setTimestamp(8, Timestamp.from(creationMax));
            } else {
                stmt.setTimestamp(7, null);
                stmt.setTimestamp(8, null);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("c_id"),
                        rs.getString("c_name")
                );

                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("creation_datetime").toInstant(),
                        category
                );
                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Product> getProductsByCriteria(String productName, String
            categoryName, Instant creationMin, Instant creationMax, int page, int size){
        List<Product> products = new ArrayList<>();
        int offset = (page - 1) * size;

        String query = "select Product.id, Product.name, Product_category.id as c_id, Product_category.name as c_name, creation_datetime " +
                "from Product " +
                "inner join Product_category " +
                "on Product.id = Product_category.product_id " +
                "where (?::text is null or Product.name ilike ?) "+
                "and (?::text is null or Product_category.name ilike ?) "+
                "and (?::timestamp is null or Product.creation_datetime >= ?::timestamp) "+
                "and (?::timestamp is null or Product.creation_datetime <= ?::timestamp) " +
                "limit ? offset ?;";
        try {
            Connection connection = this.dbConnexion.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, productName);
            stmt.setString(2, productName);
            stmt.setString(3, categoryName);
            stmt.setString(4, categoryName);
            if (creationMin != null) {
                stmt.setTimestamp(5, Timestamp.from(creationMin));
                stmt.setTimestamp(6, Timestamp.from(creationMin));
            } else {
                stmt.setTimestamp(5, null);
                stmt.setTimestamp(6, null);
            }

            if (creationMax != null) {
                stmt.setTimestamp(7, Timestamp.from(creationMax));
                stmt.setTimestamp(8, Timestamp.from(creationMax));
            } else {
                stmt.setTimestamp(7, null);
                stmt.setTimestamp(8, null);
            }
            stmt.setInt(9, size);
            stmt.setInt(10, offset);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("c_id"),
                        rs.getString("c_name")
                );
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("creation_datetime").toInstant(),
                        category
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
