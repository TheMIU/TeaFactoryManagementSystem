package lk.ijse.tfms.dto;

public class TeaSupplierDTO {
    private String sup_id;
    private String name;
    private String id;
    private String address;
    private String mobile_num;

    public TeaSupplierDTO() {
    }

    public TeaSupplierDTO(String sup_id, String name, String id, String address, String mobile_num ) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.mobile_num = mobile_num;
        this.sup_id = sup_id;
    }

    public TeaSupplierDTO(String searchID) {
        this.sup_id = searchID;
    }

    public String getSup_id() {
        return sup_id;
    }

    public void setSup_id(String sup_id) {
        this.sup_id = sup_id;
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

    public String getMobile_num() {
        return mobile_num;
    }

    public void setMobile_num(String mobile_num) {
        this.mobile_num = mobile_num;
    }

    @Override
    public String toString() {
        return "TeaSuppliers{" +
                "sup_id=" + sup_id +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", mobile_num=" + mobile_num +
                '}';
    }
}