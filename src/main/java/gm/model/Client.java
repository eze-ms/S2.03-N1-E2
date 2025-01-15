package gm.model;

import org.bson.types.ObjectId;
import java.util.Date;

public class Client {
    private final ObjectId id;
    private String name;
    private Address address;
    private String email;
    private String phone;
    private Date registerDate;
    private ObjectId recommenderId;

    public Client(ObjectId id, String name, Address address, String email, String phone, Date registerDate, ObjectId recommenderId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.registerDate = registerDate;
        this.recommenderId = recommenderId;
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public ObjectId getRecommenderId() {
        return recommenderId;
    }

    public void setRecommenderId(ObjectId recommenderId) {
        this.recommenderId = recommenderId;
    }
}
