package com.example.ibooking2.object;

public class Order {
    int order_id;
    String userName;
    String roomName;
    String ordered_date;
    String create_date;  //预定将要去的日期；下单的时间

    public Order(int order_id, String userName, String roomName, String ordered_date, String create_date) {
        this.order_id = order_id;
        this.userName = userName;
        this.roomName = roomName;
        this.ordered_date = ordered_date;
        this.create_date = create_date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getOrdered_date() {
        return ordered_date;
    }

    public void setOrdered_date(String ordered_date) {
        this.ordered_date = ordered_date;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
