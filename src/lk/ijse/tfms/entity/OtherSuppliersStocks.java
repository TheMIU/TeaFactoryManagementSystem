package lk.ijse.tfms.entity;

public class OtherSuppliersStocks {
    private String date;
    private String stock_ID;
    private String supplier_ID;

    public OtherSuppliersStocks() {
    }

    public OtherSuppliersStocks(String date, String stock_ID, String supplier_ID) {
        this.date = date;
        this.stock_ID = stock_ID;
        this.supplier_ID = supplier_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStock_ID() {
        return stock_ID;
    }

    public void setStock_ID(String stock_ID) {
        this.stock_ID = stock_ID;
    }

    public String getSupplier_ID() {
        return supplier_ID;
    }

    public void setSupplier_ID(String supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    @Override
    public String toString() {
        return "OtherSuppliersStocks{" +
                "date='" + date + '\'' +
                ", stock_ID='" + stock_ID + '\'' +
                ", supplier_ID='" + supplier_ID + '\'' +
                '}';
    }
}
