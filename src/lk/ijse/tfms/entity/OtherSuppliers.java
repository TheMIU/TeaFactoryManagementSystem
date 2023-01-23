package lk.ijse.tfms.entity;

public class OtherSuppliers {
    private String supplier_ID;
    private String supplier_Type;
    private String name;
    private String id;
    private String contact;

    public OtherSuppliers() {
    }

    public OtherSuppliers(String supplier_ID, String supplier_Type, String name, String id, String contact) {
        this.supplier_ID = supplier_ID;
        this.supplier_Type = supplier_Type;
        this.name = name;
        this.id = id;
        this.contact = contact;
    }

    public String getSupplier_ID() {
        return supplier_ID;
    }

    public void setSupplier_ID(String supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    public String getSupplier_Type() {
        return supplier_Type;
    }

    public void setSupplier_Type(String supplier_Type) {
        this.supplier_Type = supplier_Type;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "OtherSuppliers{" +
                "supplier_ID='" + supplier_ID + '\'' +
                ", supplier_Type='" + supplier_Type + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
