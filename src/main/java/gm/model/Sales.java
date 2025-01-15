package gm.model;

import org.bson.types.ObjectId;
import java.util.Date;

public class Sales {
    private final ObjectId id;
    private ObjectId clientId;
    private ObjectId glassesId;
    private ObjectId employeeId;
    private Date saleDate;

    public Sales(ObjectId clientId, ObjectId glassesId, ObjectId employeeId, Date saleDate) {
        this.id = new ObjectId();
        this.clientId = clientId;
        this.glassesId = glassesId;
        this.employeeId = employeeId;
        this.saleDate = saleDate;
    }

    // Getters (sin setter para id)
    public ObjectId getId() {
        return id;
    }

    public ObjectId getClientId() {
        return clientId;
    }

    public void setClientId(ObjectId clientId) {
        this.clientId = clientId;
    }

    public ObjectId getGlassesId() {
        return glassesId;
    }

    public void setGlassesId(ObjectId glassesId) {
        this.glassesId = glassesId;
    }

    public ObjectId getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(ObjectId employeeId) {
        this.employeeId = employeeId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
}
