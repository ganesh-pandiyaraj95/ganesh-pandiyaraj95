package com.example.fusionfoodapp.Model;

public class OrderListModel {
    private int id;
    private String CustomerName;
    private String mobileNo;
    private String email;
    private String foods;
    private String total;

    public OrderListModel(int id, String customerName, String mobileNo, String email, String foods, String total) {
        this.id = id;
        CustomerName = customerName;
        this.mobileNo = mobileNo;
        this.email = email;
        this.foods = foods;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoods() {
        return foods;
    }

    public void setFoods(String foods) {
        this.foods = foods;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderListModel{" +
                "id=" + id +
                ", CustomerName='" + CustomerName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", email='" + email + '\'' +
                ", foods='" + foods + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
