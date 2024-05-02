package dades;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import model.Missatges;
import model.Usuari;
import org.bson.Document;

public class Connexio {

    static String connectionString = "mongodb://localhost:27017";

    static MongoClient mongoClient = MongoClients.create(connectionString);
    static MongoDatabase database = mongoClient.getDatabase("Xat");
    public static MongoCollection<Document> usuaris = database.getCollection("usuaris");
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
    
    public static List<String> obtenirLlistatUsuaris() {
        List<String> llistaUsuaris = new ArrayList<>();
        
        try {
            FindIterable<Document> cursor = usuaris.find();

            for (Document doc : cursor) {
                String usuari = doc.getString("_id");
                llistaUsuaris.add(usuari);
            }
        } catch (Exception e) {
            System.err.println("Error en obtenir la llista d'usuaris: " + e);
        }
        return llistaUsuaris;
    }
    
    public static void guardarMissatges(Missatges missatge) {
        Document MissatgeDoc = new Document("idMissatge", missatge.getIdMissatge())
                        .append("idUsuari", missatge.getIdUsuari())
                        .append("idSala", missatge.getIdSala())
                        .append("missatge", missatge.getMissatge())
                        .append("data", missatge.getData());
        missatges.insertOne(MissatgeDoc);
    }
}
