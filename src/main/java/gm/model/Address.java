package gm.model;

public class Address {
    private String street;
    private int number;
    private int floor;
    private String door;
    private String city;
    private String postalCode;
    private String country;

    public Address(String street, int number, int floor, String door, String city, String postalCode, String country) {
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.door = door;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    // Getters y Setters
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }

    public String getDoor() { return door; }
    public void setDoor(String door) { this.door = door; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}

