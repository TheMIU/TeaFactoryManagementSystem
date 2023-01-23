package lk.ijse.tfms.entity;

import java.time.LocalDate;

public class TeaStock {
    private LocalDate input_Date;
    private double one_bag_Weight;
    private int qty;
    private int availableQty;

    public TeaStock() {
    }

    public TeaStock(LocalDate input_Date, double one_bag_Weight, int qty, int availableQty) {
        this.input_Date = input_Date;
        this.one_bag_Weight = one_bag_Weight;
        this.qty = qty;
        this.availableQty = availableQty;
    }

    public LocalDate getInput_Date() {
        return input_Date;
    }

    public void setInput_Date(LocalDate input_Date) {
        this.input_Date = input_Date;
    }

    public double getOne_bag_Weight() {
        return one_bag_Weight;
    }

    public void setOne_bag_Weight(double one_bag_Weight) {
        this.one_bag_Weight = one_bag_Weight;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    @Override
    public String toString() {
        return "TeaStock{" +
                "input_Date=" + input_Date +
                ", one_bag_Weight=" + one_bag_Weight +
                ", qty=" + qty +
                ", availableQty=" + availableQty +
                '}';
    }
}
