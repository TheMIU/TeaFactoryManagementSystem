package lk.ijse.tfms.dto;

public class EmployeeDTO {
    private String EmpID;
    private String Type;
    private String Name;
    private String Address;
    private String Contact;
    private String id;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String empID, String type, String name, String address, String contact, String id) {
        EmpID = empID;
        Type = type;
        Name = name;
        Address = address;
        Contact = contact;
        this.id = id;
    }

    public String getEmpID() {
        return EmpID;
    }

    public void setEmpID(String empID) {
        EmpID = empID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmpID='" + EmpID + '\'' +
                ", Type='" + Type + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact='" + Contact + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
