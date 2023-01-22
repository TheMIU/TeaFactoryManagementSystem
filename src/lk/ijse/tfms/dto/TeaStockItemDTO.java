package lk.ijse.tfms.dto;

public class TeaStockItemDTO {
    private String stockID;
    private String type;
    private String date;
    private double weight;
    private int qty;
    private int AvailableQty;

    public TeaStockItemDTO() {
    }

    public TeaStockItemDTO(String stockID, String type, String date, double weight, int qty, int availableQty) {
        this.stockID = stockID;
        this.type = type;
        this.date = date;
        this.weight = weight;
        this.qty = qty;
        AvailableQty = availableQty;
    }

    public TeaStockItemDTO(String stockID, String type, String date, double weight, int qty) {
        this.stockID = stockID;
        this.type = type;
        this.date = date;
        this.weight = weight;
        this.qty = qty;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "TeaStockItem{" +
                "stockID='" + stockID + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", weight=" + weight +
                ", qty=" + qty +
                ", AvailableQty=" + AvailableQty +
                '}';
    }

    public int getAvailableQty() {
        return AvailableQty;
    }

    public void setAvailableQty(int availableQty) {
        AvailableQty = availableQty;
    }
}
