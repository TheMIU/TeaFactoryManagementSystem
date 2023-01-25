package lk.ijse.tfms.dto;

public class OtherSupplierDTO {
    private String SupID;
    private String Sup_Type;
    private String ID;
    private String Name;
    private String Contact;

    public OtherSupplierDTO() {
    }

    public OtherSupplierDTO(String supID, String sup_Type, String ID, String name, String contact) {
        SupID = supID;
        Sup_Type = sup_Type;
        this.ID = ID;
        Name = name;
        Contact = contact;
    }

    public String getSupID() {
        return SupID;
    }

    public void setSupID(String supID) {
        SupID = supID;
    }

    public String getSup_Type() {
        return Sup_Type;
    }

    public void setSup_Type(String sup_Type) {
        Sup_Type = sup_Type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    @Override
    public String toString() {
        return "OtherSupplier{" +
                "SupID='" + SupID + '\'' +
                ", Sup_Type='" + Sup_Type + '\'' +
                ", ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Contact='" + Contact + '\'' +
                '}';
    }
}
