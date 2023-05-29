package lk.ijse.tfms.entity;

public class Buyers {
    // Data layer eke thiyana table ha property gana coding level idea ekk ganna entity classes create karnwa

    private String buyer_ID;
    private String name;
    private String address;
    private String contact;

    public Buyers() {
    }

    public Buyers(String buyerID, String name, String address, String contact) {
        this.buyer_ID = buyerID;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getBuyer_ID() {
        return buyer_ID;
    }

    public void setBuyer_ID(String buyer_ID) {
        this.buyer_ID = buyer_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Buyers{" +
                "buyerID='" + buyer_ID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
