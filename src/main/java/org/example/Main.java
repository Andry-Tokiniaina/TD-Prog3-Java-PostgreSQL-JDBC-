
package org.example;

import java.time.Instant;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DataRetriever dr = new DataRetriever();

        List<Category> categories = dr.getAllCategories();
        for (Category c : categories) {
            System.out.println(c);
        }
        System.out.println("*****");
        List<Product> page1 = dr.getProductList(1, 10);
        page1.forEach(System.out::println);
        System.out.println("*****");
        List<Product> page2 = dr.getProductList(1, 5);
        page2.forEach(System.out::println);
        System.out.println("*****");
        List<Product> page3 = dr.getProductList(1, 3);
        page3.forEach(System.out::println);
        System.out.println("*****");
        List<Product> page4 = dr.getProductList(2, 2);
        page4.forEach(System.out::println);
        System.out.println("*****");
        List<Product> testCriteria1 = dr.getProductsByCriteria(
                "Dell",
                null,
                null,
                null
        );
        testCriteria1.forEach(System.out::println);
        System.out.println("*****");
        List<Product> testCriteria2 = dr.getProductsByCriteria(
                null,
                "info",
                null,
                null
        );
        testCriteria2.forEach(System.out::println);
        System.out.println("*****");
        List<Product> testCriteria3 = dr.getProductsByCriteria(
                "Iphone",
                "mobile",
                null,
                null
        );
        testCriteria3.forEach(System.out::println);
        System.out.println("*****");
        List<Product> testCriteria4 = dr.getProductsByCriteria(
              null,
              null,
                Instant.parse("2024-02-01T00:00:00Z"),
                Instant.parse("2024-03-01T00:00:00Z")
        );
        testCriteria4.forEach(System.out::println);
        System.out.println("*****");
        List<Product> testCriteria5 = dr.getProductsByCriteria(
                "Samsung",
                "bureau",
                null,
                null
        );
        testCriteria5.forEach(System.out::println);
        System.out.println("*****");
        List<Product> testCriteria6 = dr.getProductsByCriteria(
                "Sony",
                "Informatique",
                null,
                null
        );
        testCriteria6.forEach(System.out::println);
        System.out.println("*****");
        List<Product> testCriteria7 = dr.getProductsByCriteria(
                null,
                "audio",
                Instant.parse("2024-01-01T00:00:00Z"),
                Instant.parse("2024-12-01T00:00:00Z")
        );
        testCriteria7.forEach(System.out::println);
        System.out.println("*****");
        List<Product> testCriteria8 = dr.getProductsByCriteria(
                null,
                null,
                null,
                null
        );
        testCriteria8.forEach(System.out::println);
        System.out.println("*****");
        List<Product> testCriteriaPage1 = dr.getProductsByCriteria(
                null,
                null,
                null,
                null,
                1,
                10
        );
        testCriteriaPage1.forEach(System.out::println);
        System.out.println("*****");
        List<Product> testCriteriaPage2 = dr.getProductsByCriteria(
                "Cell",
                null,
                null,
                null,
                5,
                2
        );
        testCriteriaPage2.forEach(System.out::println);
        System.out.println("*****");
        List<Product> testCriteriaPage3 = dr.getProductsByCriteria(
                null,
                "informatique",
                null,
                null,
                1,
                10
        );
        testCriteriaPage3.forEach(System.out::println);
    }
}

