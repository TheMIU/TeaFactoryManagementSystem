package lk.ijse.tfms.dto;

public class BuyerDTO {
   private String Buyer_ID;
   private String Name;
   private String Address;
   private String Contact;

    public BuyerDTO() {
    }

    public BuyerDTO(String buyer_ID, String name, String address, String contact) {
        Buyer_ID = buyer_ID;
        Name = name;
        Address = address;
        Contact = contact;
    }

    public String getBuyer_ID() {
        return Buyer_ID;
    }

    public void setBuyer_ID(String buyer_ID) {
        Buyer_ID = buyer_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "Buyer_ID='" + Buyer_ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact='" + Contact + '\'' +
                '}';
    }
}
