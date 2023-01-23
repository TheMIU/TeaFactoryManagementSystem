package lk.ijse.tfms.entity;

public class TeaSuppliers {
    private String suppliers_ID;
    private String name;
    private String id;
    private String address;
    private String mobile_No;

    public TeaSuppliers() {
    }

    public TeaSuppliers(String suppliers_ID, String name, String id, String address, String mobile_No) {
        this.suppliers_ID = suppliers_ID;
        this.name = name;
        this.id = id;
        this.address = address;
        this.mobile_No = mobile_No;
    }

    public String getSuppliers_ID() {
        return suppliers_ID;
    }

    public void setSuppliers_ID(String suppliers_ID) {
        this.suppliers_ID = suppliers_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile_No() {
        return mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        this.mobile_No = mobile_No;
    }

    @Override
    public String toString() {
        return "TeaSuppliers{" +
                "suppliers_ID='" + suppliers_ID + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", mobile_No='" + mobile_No + '\'' +
                '}';
    }
}
