package lk.ijse.tfms.dto;

public class PaymentDTO {
    private String payment_ID;
    private String date;
    private String reason;
    private double amount;
    private String method;
    private String type;
    private String buyerID;
    private String empID;
    private String supID;
    private String name;

    public PaymentDTO() {
    }

    public PaymentDTO(String payment_ID, String date, String reason, double amount, String method, String type, String buyerID, String empID, String supID) {
        this.payment_ID = payment_ID;
        this.date = date;
        this.reason = reason;
        this.amount = amount;
        this.method = method;
        this.type = type;
        this.buyerID = buyerID;
        this.empID = empID;
        this.supID = supID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayment_ID() {
        return payment_ID;
    }

    public void setPayment_ID(String payment_ID) {
        this.payment_ID = payment_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getSupID() {
        return supID;
    }

    public void setSupID(String supID) {
        this.supID = supID;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payment_ID='" + payment_ID + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", type='" + type + '\'' +
                ", buyerID='" + buyerID + '\'' +
                ", empID='" + empID + '\'' +
                ", supID='" + supID + '\'' +
                '}';
    }
}
