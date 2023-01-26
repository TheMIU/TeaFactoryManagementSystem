package lk.ijse.tfms.entity;

public class Payment {
    private String payment_ID;
    private String date;
    private String reason;
    private double amount;
    private String method;
    private String type;
    private String buyer_ID;
    private String empID;
    private String supplier_ID;

    public Payment() {
    }

    public Payment(String payment_ID, String date, String reason, double amount, String method, String type, String buyer_ID, String empID, String supplier_ID) {
        this.payment_ID = payment_ID;
        this.date = date;
        this.reason = reason;
        this.amount = amount;
        this.method = method;
        this.type = type;
        this.buyer_ID = buyer_ID;
        this.empID = empID;
        this.supplier_ID = supplier_ID;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getBuyer_ID() {
        return buyer_ID;
    }

    public void setBuyer_ID(String buyer_ID) {
        this.buyer_ID = buyer_ID;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getSupplier_ID() {
        return supplier_ID;
    }

    public void setSupplier_ID(String supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payment_ID='" + payment_ID + '\'' +
                ", date=" + date +
                ", reason='" + reason + '\'' +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", type='" + type + '\'' +
                ", buyer_ID='" + buyer_ID + '\'' +
                ", empID='" + empID + '\'' +
                ", supplier_ID='" + supplier_ID + '\'' +
                '}';
    }
}
