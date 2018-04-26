package com.example.quang.project_sdo.Models;

public class CarrierDeliveryScheduleModel {
    String area, order;

    public CarrierDeliveryScheduleModel(String area, String order) {
        this.area = area;
        this.order = order;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
