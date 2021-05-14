package github.nooblong.r5shopspringboot.common.po;

import java.util.Date;

public class Order {
    private int id;
    private Date submit_datetime;
    private String address;
    private int customer_id;
    private int state;//0:等待配送 1:配送中 2: 完成
    private String tele;
    private String remark;
    private String name;
    private double price;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", submit_datetime=" + submit_datetime +
                ", address='" + address + '\'' +
                ", customer_id=" + customer_id +
                ", state=" + state +
                ", tele='" + tele + '\'' +
                ", remark='" + remark + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSubmit_datetime() {
        return submit_datetime;
    }

    public void setSubmit_datetime(Date submit_datetime) {
        this.submit_datetime = submit_datetime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
