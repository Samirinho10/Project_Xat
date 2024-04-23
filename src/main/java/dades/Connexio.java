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

            mostrarColecciones(database);
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e);
        }

        inserirUsuari("samir", "samir");
        inserirUsuari("montserrat", "montserrat");
    }

    public static void inserirUsuari(String usuari, String contrasenya) {
        try {
            if (usuaris.find(new Document("_id", usuari)).first() != null) {
                System.out.println("L'usuari '" + usuari + "' ya existe.");
                return;
            }

            Document usuariDoc = new Document("_id", usuari)
                    .append("contrasenya", contrasenya);

            usuaris.insertOne(usuariDoc);

            System.out.println("Usuari '" + usuari + "' inserit correctament.");
        } catch (Exception e) {
            System.err.println("Error al inserir usuari '" + usuari + "': " + e);
        }
    }

    public static void mostrarColecciones(MongoDatabase database) {
        try {
            MongoIterable<String> collectionNames = database.listCollectionNames();
            System.out.println("Collections disponibles:");
            for (String name : collectionNames) {
                System.out.println("- " + name);
            }
        } catch (Exception e){ 
            System.err.println("Error al mostrar colecciones: " + e);
        }
    }

public static boolean verificarCreedencials(String usuari, String contrasenya) {
    try {
        Document usuariDoc = usuaris.find(new Document("_id", usuari)).first();
        
        if (usuariDoc != null && usuariDoc.getString("contrasenya").equals(contrasenya)) {
            return true;
        } else {
            return false;
        }
    } catch (Exception e) {
        System.err.println("Error al verificar creedencials: " + e);
        return false;
    }
}

    public static void establirConnexio() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
