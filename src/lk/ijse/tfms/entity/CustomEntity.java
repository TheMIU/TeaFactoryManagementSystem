package lk.ijse.tfms.entity;

public class CustomEntity {
    //Buyers
    private String buyer_ID;
    private String buyer_name;
    private String buyer_address;
    private String buyer_contact;

    //DailyCrop
    private String suppliers_ID;
    private String suppliers_date;
    private double netWeight;

    //Employee
    private String emp_ID;
    private String emp_type;
    private String emp_name;
    private String emp_id;
    private String emp_address;
    private String emp_contact;

    //notes
    private String notes_id;
    private String notes_note;

    //Payment
    private String payment_ID;
    private String payment_date;
    private String payment_reason;
    private double payment_amount;
    private String payment_method;
    private String payment_type;

    //TeaBuyers
    private String buyer_date;

    //TeaStock
    private String tea_stock_ID;
    private String tea_stock_type;
    private String tea_stock_input_Date;
    private double tea_stock_one_bag_Weight;
    private int tea_stock_qty;
    private int tea_stock_availableQty;

    //TeaSuppliers
    private String tea_suppliers_ID;
    private String tea_suppliers_name;
    private String tea_suppliers_id;
    private String tea_suppliers_address;
    private String tea_suppliers_mobile_No;

    //Users
    private String user_type;
    private String user_name;
    private String user_password;

    //OtherStocks
    private String other_stock_ID;
    private String other_stock_Type;
    private int other_stock_qty;
    private double other_stock_price;

    // OtherSuppliers
    private String other_supplier_ID;
    private String other_supplier_Type;
    private String other_supplier_name;
    private String other_supplier_id;
    private String other_supplier_contact;

    //OtherSuppliersStocks
    private String otherSuppliersStocks_date;
    private String otherSuppliersStocks_stock_ID;
    private String otherSuppliersStocks_supplier_ID;

    public CustomEntity() {
    }

    public CustomEntity(String date, String stock_ID, String supplier_ID, String name, String supplier_Type, int qty, double price) {
        this.otherSuppliersStocks_date = date;
        this.other_stock_ID = stock_ID;
        this.suppliers_ID = supplier_ID;
        this.other_supplier_name = name;
        this.other_supplier_Type = supplier_Type;
        this.other_stock_qty = qty;
        this.other_stock_price = price;
    }

    public CustomEntity(String supID, String stockID, String date, String type, int qty, double price) {
        this.otherSuppliersStocks_supplier_ID = supID;
        this.otherSuppliersStocks_stock_ID = stockID;
        this.otherSuppliersStocks_date = date;
        this.other_stock_Type = type;
        this.other_stock_qty = qty;
        this.other_stock_price = price;
    }

    public String getBuyer_ID() {
        return buyer_ID;
    }

    public void setBuyer_ID(String buyer_ID) {
        this.buyer_ID = buyer_ID;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getBuyer_address() {
        return buyer_address;
    }

    public void setBuyer_address(String buyer_address) {
        this.buyer_address = buyer_address;
    }

    public String getBuyer_contact() {
        return buyer_contact;
    }

    public void setBuyer_contact(String buyer_contact) {
        this.buyer_contact = buyer_contact;
    }

    public String getSuppliers_ID() {
        return suppliers_ID;
    }

    public void setSuppliers_ID(String suppliers_ID) {
        this.suppliers_ID = suppliers_ID;
    }

    public String getSuppliers_date() {
        return suppliers_date;
    }

    public void setSuppliers_date(String suppliers_date) {
        this.suppliers_date = suppliers_date;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    public String getEmp_ID() {
        return emp_ID;
    }

    public void setEmp_ID(String emp_ID) {
        this.emp_ID = emp_ID;
    }

    public String getEmp_type() {
        return emp_type;
    }

    public void setEmp_type(String emp_type) {
        this.emp_type = emp_type;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_address() {
        return emp_address;
    }

    public void setEmp_address(String emp_address) {
        this.emp_address = emp_address;
    }

    public String getEmp_contact() {
        return emp_contact;
    }

    public void setEmp_contact(String emp_contact) {
        this.emp_contact = emp_contact;
    }

    public String getNotes_id() {
        return notes_id;
    }

    public void setNotes_id(String notes_id) {
        this.notes_id = notes_id;
    }

    public String getNotes_note() {
        return notes_note;
    }

    public void setNotes_note(String notes_note) {
        this.notes_note = notes_note;
    }

    public String getPayment_ID() {
        return payment_ID;
    }

    public void setPayment_ID(String payment_ID) {
        this.payment_ID = payment_ID;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_reason() {
        return payment_reason;
    }

    public void setPayment_reason(String payment_reason) {
        this.payment_reason = payment_reason;
    }

    public double getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(double payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getBuyer_date() {
        return buyer_date;
    }

    public void setBuyer_date(String buyer_date) {
        this.buyer_date = buyer_date;
    }

    public String getTea_stock_ID() {
        return tea_stock_ID;
    }

    public void setTea_stock_ID(String tea_stock_ID) {
        this.tea_stock_ID = tea_stock_ID;
    }

    public String getTea_stock_type() {
        return tea_stock_type;
    }

    public void setTea_stock_type(String tea_stock_type) {
        this.tea_stock_type = tea_stock_type;
    }

    public String getTea_stock_input_Date() {
        return tea_stock_input_Date;
    }

    public void setTea_stock_input_Date(String tea_stock_input_Date) {
        this.tea_stock_input_Date = tea_stock_input_Date;
    }

    public double getTea_stock_one_bag_Weight() {
        return tea_stock_one_bag_Weight;
    }

    public void setTea_stock_one_bag_Weight(double tea_stock_one_bag_Weight) {
        this.tea_stock_one_bag_Weight = tea_stock_one_bag_Weight;
    }

    public int getTea_stock_qty() {
        return tea_stock_qty;
    }

    public void setTea_stock_qty(int tea_stock_qty) {
        this.tea_stock_qty = tea_stock_qty;
    }

    public int getTea_stock_availableQty() {
        return tea_stock_availableQty;
    }

    public void setTea_stock_availableQty(int tea_stock_availableQty) {
        this.tea_stock_availableQty = tea_stock_availableQty;
    }

    public String getTea_suppliers_ID() {
        return tea_suppliers_ID;
    }

    public void setTea_suppliers_ID(String tea_suppliers_ID) {
        this.tea_suppliers_ID = tea_suppliers_ID;
    }

    public String getTea_suppliers_name() {
        return tea_suppliers_name;
    }

    public void setTea_suppliers_name(String tea_suppliers_name) {
        this.tea_suppliers_name = tea_suppliers_name;
    }

    public String getTea_suppliers_id() {
        return tea_suppliers_id;
    }

    public void setTea_suppliers_id(String tea_suppliers_id) {
        this.tea_suppliers_id = tea_suppliers_id;
    }

    public String getTea_suppliers_address() {
        return tea_suppliers_address;
    }

    public void setTea_suppliers_address(String tea_suppliers_address) {
        this.tea_suppliers_address = tea_suppliers_address;
    }

    public String getTea_suppliers_mobile_No() {
        return tea_suppliers_mobile_No;
    }

    public void setTea_suppliers_mobile_No(String tea_suppliers_mobile_No) {
        this.tea_suppliers_mobile_No = tea_suppliers_mobile_No;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getOther_stock_ID() {
        return other_stock_ID;
    }

    public void setOther_stock_ID(String other_stock_ID) {
        this.other_stock_ID = other_stock_ID;
    }

    public String getOther_stock_Type() {
        return other_stock_Type;
    }

    public void setOther_stock_Type(String other_stock_Type) {
        this.other_stock_Type = other_stock_Type;
    }

    public int getOther_stock_qty() {
        return other_stock_qty;
    }

    public void setOther_stock_qty(int other_stock_qty) {
        this.other_stock_qty = other_stock_qty;
    }

    public double getOther_stock_price() {
        return other_stock_price;
    }

    public void setOther_stock_price(double other_stock_price) {
        this.other_stock_price = other_stock_price;
    }

    public String getOther_supplier_ID() {
        return other_supplier_ID;
    }

    public void setOther_supplier_ID(String other_supplier_ID) {
        this.other_supplier_ID = other_supplier_ID;
    }

    public String getOther_supplier_Type() {
        return other_supplier_Type;
    }

    public void setOther_supplier_Type(String other_supplier_Type) {
        this.other_supplier_Type = other_supplier_Type;
    }

    public String getOther_supplier_name() {
        return other_supplier_name;
    }

    public void setOther_supplier_name(String other_supplier_name) {
        this.other_supplier_name = other_supplier_name;
    }

    public String getOther_supplier_id() {
        return other_supplier_id;
    }

    public void setOther_supplier_id(String other_supplier_id) {
        this.other_supplier_id = other_supplier_id;
    }

    public String getOther_supplier_contact() {
        return other_supplier_contact;
    }

    public void setOther_supplier_contact(String other_supplier_contact) {
        this.other_supplier_contact = other_supplier_contact;
    }

    public String getOtherSuppliersStocks_date() {
        return otherSuppliersStocks_date;
    }

    public void setOtherSuppliersStocks_date(String otherSuppliersStocks_date) {
        this.otherSuppliersStocks_date = otherSuppliersStocks_date;
    }

    public String getOtherSuppliersStocks_stock_ID() {
        return otherSuppliersStocks_stock_ID;
    }

    public void setOtherSuppliersStocks_stock_ID(String otherSuppliersStocks_stock_ID) {
        this.otherSuppliersStocks_stock_ID = otherSuppliersStocks_stock_ID;
    }

    public String getOtherSuppliersStocks_supplier_ID() {
        return otherSuppliersStocks_supplier_ID;
    }

    public void setOtherSuppliersStocks_supplier_ID(String otherSuppliersStocks_supplier_ID) {
        this.otherSuppliersStocks_supplier_ID = otherSuppliersStocks_supplier_ID;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "buyer_ID='" + buyer_ID + '\'' +
                ", buyer_name='" + buyer_name + '\'' +
                ", buyer_address='" + buyer_address + '\'' +
                ", buyer_contact='" + buyer_contact + '\'' +
                ", suppliers_ID='" + suppliers_ID + '\'' +
                ", suppliers_date='" + suppliers_date + '\'' +
                ", netWeight=" + netWeight +
                ", emp_ID='" + emp_ID + '\'' +
                ", emp_type='" + emp_type + '\'' +
                ", emp_name='" + emp_name + '\'' +
                ", emp_id='" + emp_id + '\'' +
                ", emp_address='" + emp_address + '\'' +
                ", emp_contact='" + emp_contact + '\'' +
                ", notes_id='" + notes_id + '\'' +
                ", notes_note='" + notes_note + '\'' +
                ", payment_ID='" + payment_ID + '\'' +
                ", payment_date='" + payment_date + '\'' +
                ", payment_reason='" + payment_reason + '\'' +
                ", payment_amount=" + payment_amount +
                ", payment_method='" + payment_method + '\'' +
                ", payment_type='" + payment_type + '\'' +
                ", buyer_date='" + buyer_date + '\'' +
                ", tea_stock_ID='" + tea_stock_ID + '\'' +
                ", tea_stock_type='" + tea_stock_type + '\'' +
                ", tea_stock_input_Date='" + tea_stock_input_Date + '\'' +
                ", tea_stock_one_bag_Weight=" + tea_stock_one_bag_Weight +
                ", tea_stock_qty=" + tea_stock_qty +
                ", tea_stock_availableQty=" + tea_stock_availableQty +
                ", tea_suppliers_ID='" + tea_suppliers_ID + '\'' +
                ", tea_suppliers_name='" + tea_suppliers_name + '\'' +
                ", tea_suppliers_id='" + tea_suppliers_id + '\'' +
                ", tea_suppliers_address='" + tea_suppliers_address + '\'' +
                ", tea_suppliers_mobile_No='" + tea_suppliers_mobile_No + '\'' +
                ", user_type='" + user_type + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", other_stock_ID='" + other_stock_ID + '\'' +
                ", other_stock_Type='" + other_stock_Type + '\'' +
                ", other_stock_qty=" + other_stock_qty +
                ", other_stock_price=" + other_stock_price +
                ", other_supplier_ID='" + other_supplier_ID + '\'' +
                ", other_supplier_Type='" + other_supplier_Type + '\'' +
                ", other_supplier_name='" + other_supplier_name + '\'' +
                ", other_supplier_id='" + other_supplier_id + '\'' +
                ", other_supplier_contact='" + other_supplier_contact + '\'' +
                ", otherSuppliersStocks_date='" + otherSuppliersStocks_date + '\'' +
                ", otherSuppliersStocks_stock_ID='" + otherSuppliersStocks_stock_ID + '\'' +
                ", otherSuppliersStocks_supplier_ID='" + otherSuppliersStocks_supplier_ID + '\'' +
                '}';
    }
}
