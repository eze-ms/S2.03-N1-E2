package gm.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import gm.model.Address;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

public class DatabaseRepository {
    private final MongoDatabase database;

    public DatabaseRepository(MongoDatabase database) {
        this.database = database;
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }

    // Método para insertar gafas
    public void insertGlasses(String brand, double leftLensPower, double rightLensPower,
                              String leftLensColor, String rightLensColor, String frameType,
                              String frameColor, double price, ObjectId supplierId) {
        try {
            MongoCollection<Document> glassesCollection = this.database.getCollection("glasses");

            Document existingGlasses = glassesCollection.find(new Document("brand", brand)).first();

            if (existingGlasses == null) {
                Document glassesDoc = new Document("brand", brand)
                        .append("leftLensPower", leftLensPower)
                        .append("rightLensPower", rightLensPower)
                        .append("leftLensColor", leftLensColor)
                        .append("rightLensColor", rightLensColor)
                        .append("frameType", frameType)
                        .append("frameColor", frameColor)
                        .append("price", price)
                        .append("supplierId", supplierId);

                glassesCollection.insertOne(glassesDoc);
                System.out.println("Gafas insertadas correctamente.");
            } else {
                glassesCollection.updateOne(
                        new Document("brand", brand),
                        new Document("$set", new Document("supplierId", supplierId))
                );
                System.out.println("Gafas ya existentes, proveedor actualizado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar o actualizar las gafas.");
        }
    }

    // Método para insertar proveedor
    public void insertSupplier(String name, String phone, String fax, String nif, Address address) {
        try {
            MongoCollection<Document> suppliersCollection = database.getCollection("supplier");
            Document existingSupplier = suppliersCollection.find(new Document("NIF", nif)).first();

            if (existingSupplier != null) {
                suppliersCollection.updateOne(
                        new Document("NIF", nif),
                        new Document("$set", new Document("phone", phone)
                                .append("fax", fax)
                                .append("address", new Document("street", address.getStreet())
                                        .append("number", address.getNumber())
                                        .append("floor", address.getFloor())
                                        .append("door", address.getDoor())
                                        .append("city", address.getCity())
                                        .append("postalCode", address.getPostalCode())
                                        .append("country", address.getCountry()))
                        ));
                System.out.println("Proveedor actualizado correctamente.");
            } else {
                Document supplier = new Document("name", name)
                        .append("phone", phone)
                        .append("fax", fax)
                        .append("NIF", nif)
                        .append("address", new Document("street", address.getStreet())
                                .append("number", address.getNumber())
                                .append("floor", address.getFloor())
                                .append("door", address.getDoor())
                                .append("city", address.getCity())
                                .append("postalCode", address.getPostalCode())
                                .append("country", address.getCountry()));

                suppliersCollection.insertOne(supplier);
                System.out.println("Proveedor insertado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar o actualizar el proveedor.");
        }
    }

    // Método para insertar cliente
    public void insertClient(String name, String email, String phone, Address address, ObjectId recommenderId) {
        try {
            MongoCollection<Document> clientCollection = database.getCollection("client");

            Document existingClient = clientCollection.find(new Document("email", email)).first();

            if (existingClient != null) {
                clientCollection.updateOne(
                        new Document("email", email),
                        new Document("$set", new Document("name", name)
                                .append("phone", phone)
                                .append("address", new Document("street", address.getStreet())
                                        .append("number", address.getNumber())
                                        .append("floor", address.getFloor())
                                        .append("door", address.getDoor())
                                        .append("city", address.getCity())
                                        .append("postalCode", address.getPostalCode())
                                        .append("country", address.getCountry()))
                                .append("recommenderId", recommenderId))
                );
                System.out.println("Cliente actualizado correctamente.");
            } else {
                Document client = new Document("name", name)
                        .append("email", email)
                        .append("phone", phone)
                        .append("address", new Document("street", address.getStreet())
                                .append("number", address.getNumber())
                                .append("floor", address.getFloor())
                                .append("door", address.getDoor())
                                .append("city", address.getCity())
                                .append("postalCode", address.getPostalCode())
                                .append("country", address.getCountry()))
                        .append("registerDate", new Date())
                        .append("recommenderId", recommenderId);

                clientCollection.insertOne(client);
                System.out.println("Cliente insertado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar o actualizar el cliente.");
        }
    }

    // Método para insertar empleado
    public void insertEmployee(String name, String lastName, String phone) {
        try {
            MongoCollection<Document> employeeCollection = database.getCollection("employee");

            Document existingEmployee = employeeCollection.find(new Document("name", name)
                    .append("lastName", lastName)).first();

            if (existingEmployee != null) {
                employeeCollection.updateOne(
                        new Document("name", name).append("lastName", lastName),
                        new Document("$set", new Document("phone", phone)
                                .append("hireDate", new Date()))
                );
                System.out.println("Empleado actualizado correctamente.");
            } else {
                Document employee = new Document("name", name)
                        .append("lastName", lastName)
                        .append("phone", phone)
                        .append("hireDate", new Date());

                employeeCollection.insertOne(employee);
                System.out.println("Empleado insertado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar o actualizar el empleado.");
        }
    }

    // Método para insertar una venta con validación completa
    public void insertSales(ObjectId clientId, ObjectId glassesId, ObjectId employeeId, Date saleDate) {
        try {
            MongoCollection<Document> salesCollection = database.getCollection("sales");

            Document existingSale = salesCollection.find(new Document("clientId", clientId)
                    .append("glassesId", glassesId)
                    .append("employeeId", employeeId)
                    .append("saleDate", saleDate)).first();

            if (existingSale != null) {
                System.out.println("Venta ya registrada con los mismos datos.");
            } else {
                Document sale = new Document("clientId", clientId)
                        .append("glassesId", glassesId)
                        .append("employeeId", employeeId)
                        .append("saleDate", saleDate);

                salesCollection.insertOne(sale);
                System.out.println("Venta registrada correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar la venta.");
        }
    }

    // Método para listar las ventas
    public void listSales() {
        try {
            MongoCollection<Document> salesCollection = database.getCollection("sales");

            // Recuperar todas las ventas de la colección
            for (Document sale : salesCollection.find()) {
                ObjectId clientId = sale.getObjectId("clientId");
                ObjectId glassesId = sale.getObjectId("glassesId");
                ObjectId employeeId = sale.getObjectId("employeeId");
                Date saleDate = sale.getDate("saleDate");

                // Mostrar la venta en la consola
                System.out.println("Venta registrada:");
                System.out.println("Cliente ID: " + clientId);
                System.out.println("Gafas ID: " + glassesId);
                System.out.println("Empleado ID: " + employeeId);
                System.out.println("Fecha de venta: " + saleDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar las ventas.");
        }
    }

    // Método para obtener el _id de unas gafas por su marca
    public ObjectId getGlassesId(String brand) {
        MongoCollection<Document> glassesCollection = this.database.getCollection("glasses");
        Document glassesDoc = glassesCollection.find(new Document("brand", brand)).first();
        if (glassesDoc != null) {
            return glassesDoc.getObjectId("_id");
        }
        return null;
    }

    // Método para obtener el _id de un proveedor por su NIF
    public ObjectId getSupplierId(String nif) {
        MongoCollection<Document> suppliersCollection = this.database.getCollection("supplier");
        Document supplierDoc = suppliersCollection.find(new Document("NIF", nif)).first();
        if (supplierDoc != null) {
            return supplierDoc.getObjectId("_id");
        }
        return null;
    }

    // Método para obtener el _id de un cliente por su email
    public ObjectId getClientId(String email) {
        MongoCollection<Document> clientsCollection = this.database.getCollection("client");  // Debe ser "client", no "supplier"
        Document clientDoc = clientsCollection.find(new Document("email", email)).first();
        if (clientDoc != null) {
            return clientDoc.getObjectId("_id");
        }
        return null;
    }

}
