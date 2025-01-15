package gm.model;

import org.bson.types.ObjectId;

public class Employee {
    private final ObjectId id;
    private String name;
    private String lastName;
    private String phone;

    public Employee(String name, String lastName, String phone) {
        this.id = new ObjectId();
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
    }

    // Getters (sin setter para id)
    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
