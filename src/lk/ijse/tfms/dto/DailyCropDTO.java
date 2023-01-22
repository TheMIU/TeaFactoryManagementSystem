package lk.ijse.tfms.dto;

public class DailyCropDTO {
    private String supID;
    private String supName;
    private String date;
    private double weight;

    public DailyCropDTO() {
    }

    public DailyCropDTO(String supID, String date, double weight) {
        this.supID = supID;
        this.date = date;
        this.weight = weight;
    }

    public String getSupID() {
        return supID;
    }

    public void setSupID(String supID) {
        this.supID = supID;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
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

    @Override
    public String toString() {
        return "DailyCrop{" +
                "supID='" + supID + '\'' +
                ", supName='" + supName + '\'' +
                ", date='" + date + '\'' +
                ", weight=" + weight +
                '}';
    }
}
