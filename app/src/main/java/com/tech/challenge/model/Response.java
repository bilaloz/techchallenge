package com.tech.challenge.model;

public class Response {

    /**
     * date : 13
     * month : 01
     * marketName : Market 1
     * orderName : Sabun
     * productPrice : 14.5
     * productState : Yolda
     * productDetail : {"orderDetail":"250 ml sıvı sabun","summaryPrice":16.8}
     */

    private String date;
    private String month;
    private String marketName;
    private String orderName;
    private double productPrice;
    private String productState;
    private ProductDetailBean productDetail;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public ProductDetailBean getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetailBean productDetail) {
        this.productDetail = productDetail;
    }

    public static class ProductDetailBean {
        /**
         * orderDetail : 250 ml sıvı sabun
         * summaryPrice : 16.8
         */

        private String orderDetail;
        private double summaryPrice;

        public String getOrderDetail() {
            return orderDetail;
        }

        public void setOrderDetail(String orderDetail) {
            this.orderDetail = orderDetail;
        }

        public double getSummaryPrice() {
            return summaryPrice;
        }

        public void setSummaryPrice(double summaryPrice) {
            this.summaryPrice = summaryPrice;
        }
    }
}
