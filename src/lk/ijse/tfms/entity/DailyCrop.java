package lk.ijse.tfms.entity;

import java.time.LocalDate;
import java.util.Date;


public class DailyCrop {
    private String suppliers_ID;
    private String date;
    private double netWeight;

    public DailyCrop() {
    }

    public DailyCrop(String suppliers_ID, String date, double netWeight) {
        this.suppliers_ID = suppliers_ID;
        this.date = date;
        this.netWeight = netWeight;
    }

    public String getSuppliers_ID() {
        return suppliers_ID;
    }

    public void setSuppliers_ID(String suppliers_ID) {
        this.suppliers_ID = suppliers_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }


}
