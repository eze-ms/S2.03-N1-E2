package gm.service;

import com.mongodb.client.MongoCollection;
import gm.repository.DatabaseRepository;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class GlassesService {
    private final DatabaseRepository repository;

    public GlassesService(DatabaseRepository repository) {
        this.repository = repository;
    }

    // Método del repositorio
    public ObjectId getGlassesId(String brand) {
        return repository.getGlassesId(brand);
    }

    public ObjectId getSupplierId(String nif) {
        return repository.getSupplierId(nif);
    }

    public ObjectId getClientId(String email) {
        return repository.getClientId(email);
    }

    public void listSales() {
        repository.listSales();
    }

    //! 1. Métodos relacionados con gafas
    public void showSupplierForGlasses(String brand) {
        try {
            ObjectId glassesId = getGlassesId(brand);
            if (glassesId == null) {
                return;
            }

            // Usando el getter para acceder a la base de datos
            MongoCollection<Document> glassesCollection = repository.getDatabase().getCollection("glasses");
            MongoCollection<Document> suppliersCollection = repository.getDatabase().getCollection("supplier");

            Document glasses = glassesCollection.find(new Document("_id", glassesId)).first();
            if (glasses != null) {
                ObjectId supplierId = glasses.getObjectId("supplierId");
                Document supplier = suppliersCollection.find(new Document("_id", supplierId)).first();

                if (supplier != null) {
                    System.out.println("Proveedor de las gafas:");
                    System.out.println("- Nombre: " + supplier.get("name"));
                    System.out.println("- Dirección: " + supplier.get("address").toString());
                    System.out.println("- Teléfono: " + supplier.get("phone"));
                } else {
                    System.out.println("No se encontró el proveedor asociado.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener detalles del proveedor.");
        }
    }

    public void associateSupplierToGlasses(String brand, String supplierNif) {
        try {
            // Obtener el ObjectId del proveedor con el NIF
            ObjectId supplierId = getSupplierId(supplierNif);  // método para obtener el ID del proveedor por NIF
            if (supplierId == null) {
                System.out.println("Proveedor no encontrado.");
                return;
            }

            MongoCollection<Document> glassesCollection = repository.getDatabase().getCollection("glasses");

            // Verificar si las gafas ya existen
            Document glassesDoc = glassesCollection.find(new Document("brand", brand)).first();
            if (glassesDoc == null) {
                // Si no existen, insertar nuevas gafas con el proveedor asociado
                glassesDoc = new Document("brand", brand)
                        .append("rightLensPower", -2.5)
                        .append("leftLensPower", -2.5)
                        .append("rightLensColor", "blue")
                        .append("leftLensColor", "blue")
                        .append("frameType", "metal")
                        .append("frameColor", "gold")
                        .append("price", 150)
                        .append("supplierId", supplierId);

                glassesCollection.insertOne(glassesDoc);
                System.out.println("Gafas insertadas correctamente con proveedor.");
            } else {
                // Si ya existen, solo actualizamos el proveedor
                glassesCollection.updateOne(
                        new Document("brand", brand),
                        new Document("$set", new Document("supplierId", supplierId))
                );
                System.out.println("Gafas ya existen, proveedor asociado.");
            }

            // Verificación adicional: Imprimir todas las gafas en la colección
            Document firstGlasses = glassesCollection.find().first();
            if (firstGlasses != null) {
                System.out.println("Gafas en la base de datos: " + firstGlasses.toJson());
            } else {
                System.out.println("No se encontraron gafas en la base de datos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar o asociar proveedor con las gafas.");
        }
    }

    //! 2. Métodos relacionados con clientes
    public void addClientToGlasses(String brand, String clientEmail) {
        try {
            ObjectId glassesId = getGlassesId(brand);
            ObjectId clientId = getClientId(clientEmail);

            if (glassesId == null || clientId == null) {
                return;
            }

            MongoCollection<Document> glassesCollection = repository.getDatabase().getCollection("glasses");

            glassesCollection.updateOne(
                    new Document("_id", glassesId),
                    new Document("$addToSet", new Document("clients", clientId))  // Añadimos el cliente a la lista
            );

            System.out.println("Cliente agregado a las gafas correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar cliente a gafas.");
        }
    }

    public void listClientsForGlasses(String brand) {
        try {
            ObjectId glassesId = getGlassesId(brand);
            if (glassesId == null) {
                return;
            }

            MongoCollection<Document> glassesCollection = repository.getDatabase().getCollection("glasses");
            MongoCollection<Document> clientCollection = repository.getDatabase().getCollection("client");

            Document glasses = glassesCollection.find(new Document("_id", glassesId)).first();
            if (glasses != null) {
                List<ObjectId> clientIds = glasses.getList("clients", ObjectId.class);
                if (clientIds == null) {
                    System.out.println("No hay clientes asociados con estas gafas.");
                    return;
                }

                System.out.println("Clientes que compraron las gafas:");
                for (ObjectId clientId : clientIds) {
                    Document client = clientCollection.find(new Document("_id", clientId)).first();
                    if (client != null) {
                        System.out.println("- " + client.getString("name") + " (" + client.getString("email") + ")");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar clientes relacionados con gafas.");
        }
    }

    //! 3. Métodos relacionados con ventas
    public void insertSales(ObjectId clientId, ObjectId glassesId, ObjectId employeeId, Date saleDate) {
        try {
            repository.insertSales(clientId, glassesId, employeeId, saleDate);
            System.out.println("Venta registrada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar la venta.");
        }
    }
}
