package lk.ijse.tfms.entity;

public class TeaStock {
    private String stock_ID;
    private String type;
    private String input_Date;
    private double one_bag_Weight;
    private int qty;
    private int availableQty;

    public TeaStock() {
    }

    public TeaStock(String stock_ID, String type, String input_Date, double one_bag_Weight, int qty, int availableQty) {
        this.stock_ID = stock_ID;
        this.type = type;
        this.input_Date = input_Date;
        this.one_bag_Weight = one_bag_Weight;
        this.qty = qty;
        this.availableQty = availableQty;
    }

    public String getStock_ID() {
        return stock_ID;
    }

    public void setStock_ID(String stock_ID) {
        this.stock_ID = stock_ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInput_Date() {
        return input_Date;
    }

    public void setInput_Date(String input_Date) {
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
                "stock_ID='" + stock_ID + '\'' +
                ", type='" + type + '\'' +
                ", input_Date=" + input_Date +
                ", one_bag_Weight=" + one_bag_Weight +
                ", qty=" + qty +
                ", availableQty=" + availableQty +
                '}';
    }
}
