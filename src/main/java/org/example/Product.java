package org.example;

import java.sql.Timestamp;
import java.time.Instant;

public class Product {
    public int id;
    public String name;
    public Instant CreationDatetime;
    public Category category;

    public Product(int id, String name, Instant timestamp, Category category) {
        this.id = id;
        this.name = name;
        this.CreationDatetime = timestamp;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + '}';
    }
}
