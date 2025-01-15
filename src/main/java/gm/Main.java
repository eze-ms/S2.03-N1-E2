package gm;

import gm.model.Address;
import gm.repository.DatabaseRepository;
import gm.service.GlassesService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("optica");

            // Crear el repositorio
            DatabaseRepository repository = new DatabaseRepository(database);

            // Crear el servicio
            GlassesService glassesService = new GlassesService(repository);

            // Crear un proveedor y asociarlo a gafas
            Address supplierAddress = new Address("Calle Mayor", 123, 3, "B", "Barcelona", "08001", "España");
            repository.insertSupplier("Juan Pérez Muñoz", "678912345", "678912346", "45567890V", supplierAddress);

            // Obtener el ObjectId del proveedor insertado
            ObjectId supplierId = repository.getSupplierId("45567890V");

            // Crear unas gafas y asociarlas a un proveedor
            glassesService.associateSupplierToGlasses("Ray-Ban", "45567890V");

            // Crear un cliente y asociarlo a las gafas
            Address clientAddress = new Address("Calle Falsa", 456, 2, "A", "Madrid", "28001", "España");
            repository.insertClient("Carlos Peña", "juan.perez@example.com", "678912345", clientAddress, new ObjectId());

            // Crear un empleado
            repository.insertEmployee("Juan López", "García", "612345678");

            // Crear una venta
            glassesService.insertSales(new ObjectId(), new ObjectId(), new ObjectId(), new Date());

            // Listar todas las ventas
            glassesService.listSales();

            // Mostrar el proveedor de unas gafas
            glassesService.showSupplierForGlasses("Ray-Ban");

            // Agregar un cliente a unas gafas
            glassesService.addClientToGlasses("Ray-Ban", "juan.perez@example.com");

            // Listar los clientes de unas gafas
            glassesService.listClientsForGlasses("Ray-Ban");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al realizar las operaciones en la base de datos.");
        }
    }
}
