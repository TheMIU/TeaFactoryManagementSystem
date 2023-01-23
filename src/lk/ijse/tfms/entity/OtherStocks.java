package lk.ijse.tfms.entity;

public class OtherStocks {
    private String stock_ID;
    private String stock_Type;
    private int qty;
    private double price;

    public OtherStocks() {
    }

    public OtherStocks(String stock_ID, String stock_Type, int qty, double price) {
        this.stock_ID = stock_ID;
        this.stock_Type = stock_Type;
        this.qty = qty;
        this.price = price;
    }

    public String getStock_ID() {
        return stock_ID;
    }

    public void setStock_ID(String stock_ID) {
        this.stock_ID = stock_ID;
    }

    public String getStock_Type() {
        return stock_Type;
    }

    public void setStock_Type(String stock_Type) {
        this.stock_Type = stock_Type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OtherStocks{" +
                "stock_ID='" + stock_ID + '\'' +
                ", stock_Type='" + stock_Type + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
