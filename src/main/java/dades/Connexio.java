
package dades;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Connexio {

    public static void main(String[] args) {
        
        String connexio = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(connexio)) {

            MongoDatabase database = mongoClient.getDatabase("xat");
            System.out.println("Connexió a MongoDB realitzada correctament.");
            
            MongoCollection<Document> login = database.getCollection("treballador");
            
            //consultes

        } catch (Exception e) {
            System.err.println("Error en connectar-se a MongoDB: " + e);
        }
    }
}
