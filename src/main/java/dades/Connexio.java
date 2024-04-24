package dades;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import model.Usuari;
import org.bson.Document;

public class Connexio {
    
    static String connectionString = "mongodb://localhost:27017";
    
    static MongoClient mongoClient = MongoClients.create(connectionString);
    static MongoDatabase database = mongoClient.getDatabase("Xat");
    static MongoCollection<Document> usuaris = database.getCollection("usuaris");
    static MongoCollection<Document> missatges = database.getCollection("missatges");

    public static void inserirUsuari(Usuari u) {
        String usuari = u.getUsuari();
        String contrasenya = u.getContrasenya();
        
        try {
            if (usuaris.find(new Document("_id", usuari)).first() != null) {
                return;
            }

            Document usuariDoc = new Document("_id", usuari)
                .append("contrasenya", contrasenya);

            usuaris.insertOne(usuariDoc);
            
        } catch (Exception e) {
            System.err.println("Error a l'inserir l'usuari '" + usuari + "': " + e);
        }
    }

//    public static void mostrarColecciones(MongoDatabase database) {
//        
//        try {
//            MongoIterable<String> collectionNames = database.listCollectionNames();
//            System.out.println("Collections disponibles:");
//            for (String name : collectionNames) {
//                System.out.println("- " + name);
//            }
//        } catch (Exception e){ 
//            System.err.println("Error al mostrar colecciones: " + e);
//        }
//    }

    public static boolean verificarCreedencials(Usuari u) {
        String usuari = u.getUsuari();
        String contrasenya = u.getContrasenya();
        
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
}
