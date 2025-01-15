package gm.model;

public class Supplier {
    private String name;
    private Address address;
    private String phone;
    private String fax;
    private String NIF;

    public Supplier(String name, Address address, String phone, String fax, String NIF) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.NIF = NIF;
    }

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }

    public String getNIF() { return NIF; }
    public void setNIF(String NIF) { this.NIF = NIF; }
}
