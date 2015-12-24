package com.wgz.ant.antinstall.bean;

/**
 * Created by qwerr on 2015/12/23.
 */
public class Detail {
    private String name;
    private String price;
    private String count;
    private String type;
    private String goodname;
    private String phone;
    private String address;
    private String date;

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setGoodsmoeny(String goodsmoeny) {
        this.goodsmoeny = goodsmoeny;
    }

    public String getGoodname() {

        return goodname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getGoodsmoeny() {
        return goodsmoeny;
    }

    private String goodsmoeny;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getCount() {
        return count;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", count='" + count + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
