package dades;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

public class Connexio {

    private static final MongoClient mongoClient = MongoClients.create();
    private static final MongoDatabase database = mongoClient.getDatabase("Xat");
    private static final MongoCollection<Document> usuaris = database.getCollection("usuaris");
    private static final MongoCollection<Document> missatges = database.getCollection("missatges");

    public static void main(String[] args) {

        String connectionString = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("xat");
            System.out.println("Conexión a MongoDB establecida correctamente.");

            // Mostrar todas las colecciones en la base de datos
            mostrarColecciones(database);
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e);
        }

        inserirUsuari("samir", "samir");
        inserirUsuari("montserrat", "montserrat");
    }

    public static void inserirUsuari(String usuari, String contrasenya) {
        try {
            Document usuariDoc = new Document("_id", usuari)
                    .append("usuari", usuari)
                    .append("contrasenya", contrasenya);

            usuaris.insertOne(usuariDoc);

            System.out.println("Usuario '" + usuari + "' insertado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar usuario '" + usuari + "': " + e);
        }

    }

    public static void mostrarColecciones(MongoDatabase database) {
        try {
            // Mostrar todas las colecciones en la base de datos
            MongoIterable<String> collectionNames = database.listCollectionNames();
            System.out.println("Colecciones disponibles:");
            for (String name : collectionNames) {
                System.out.println("- " + name);
            }
        } catch (Exception e) {
            System.err.println("Error al mostrar colecciones: " + e);
        }
    }

    public static boolean verificarCredenciales(String usuari, String contrasenya) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void establirConnexio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
