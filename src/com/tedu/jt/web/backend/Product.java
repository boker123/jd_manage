package com.tedu.jt.web.backend;

/**
 * 商品信息实体(JavaBean)
 * 符合一定规范的实体类
 *
 * @author Boker
 * @time 2019年8月9日18点28分
 */
public class Product {
    private int id;               //商品编号
    private String name;          //商品名称
    private String category;      //商品分类
    private double price;         //商品单价
    private int pnum;             //商品数量
    private String description;   //商品描述

    public Product(){}

    public Product(int id, String name, String category, double price, int pnum, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.pnum = pnum;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", pnum=" + pnum +
                ", description='" + description + '\'' +
                '}';
    }
}
