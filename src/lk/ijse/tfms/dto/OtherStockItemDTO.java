package lk.ijse.tfms.dto;

public class OtherStockItemDTO {
    private String date;
    private String stockID;
    private String supplierID;
    private String supplierName;
    private String type;
    private int qty;
    private double price;

    public OtherStockItemDTO() {
    }

    public OtherStockItemDTO(String date, String stockID, String supplierID, String supplierName, String type, int qty, double price) {
        this.date = date;
        this.stockID = stockID;
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.type = type;
        this.qty = qty;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "OtherStockItem{" +
                "date='" + date + '\'' +
                ", stockID='" + stockID + '\'' +
                ", supplierID='" + supplierID + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", type='" + type + '\'' +
                ", qty=" + qty +
                ", price='" + price + '\'' +
                '}';
    }
}
