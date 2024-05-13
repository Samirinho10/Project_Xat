package dades;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import model.Missatges;
import model.Usuari;
import org.bson.Document;

public class Connexio {

    static String mongoUsuari = "grup3";
    static String mongoContrasenya = "lleó789";
    static String host = "57.129.5.24";
    static int port = 27017;
    static String connectionString = "mongodb://" + mongoUsuari + ":" + mongoContrasenya + "@" + host + ":" + port;

    static MongoClient mongoClient = MongoClients.create(connectionString);
    static MongoDatabase database = mongoClient.getDatabase("grup3");
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
                    .append("contrasenya", hashContrasenya(contrasenya));

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

            if (usuariDoc != null && usuariDoc.getString("contrasenya").equals(hashContrasenya(contrasenya))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error al verificar creedencials: " + e);
            return false;
        }
    }
    
    public static boolean existeixUsuari(Usuari u) {
        String usuari = u.getUsuari();

        try {
            Document usuariDoc = usuaris.find(new Document("_id", usuari)).first();

            return usuariDoc != null;
        } catch (Exception e) {
            System.err.println("Error al verificar si existeix l'usuari: " + e);
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
    
    public static Usuari obtenirUsuariPerId(String idUsuari) {
        try {
            Document usuariDoc = usuaris.find(Filters.eq("_id", idUsuari)).first();
            
            if (usuariDoc != null) {
                String id = usuariDoc.getString("_id");
                String contrasenya = usuariDoc.getString("contrasenya");
                //boolean estat = usuariDoc.getBoolean("estat");
                Socket sc = null;

                return new Usuari(id, contrasenya, sc);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        
        return null;
    }
    
    public static void guardarMissatges(Missatges missatge) {
        try {
            Usuari usuari = missatge.getIdUsuari();
            Document usuariDoc = usuaris.find(new Document("_id", usuari.getUsuari())).first();

            if (usuariDoc != null) {
                Document MissatgeDoc = new Document("idUsuari", usuariDoc)
                                    .append("idSala", missatge.getIdSala())
                                    .append("missatge", missatge.getMissatge())
                                    .append("data", missatge.getData());
                missatges.insertOne(MissatgeDoc);
            } else {
                System.err.println("L'usuari no existeix a la base de dades: " + usuari.getUsuari());
            }
        } catch (Exception e) {
            System.err.println("Error en guardar el missatge: " + e);
        }
    }
        
    public static void actualitzarEstatUsuari(Usuari u, boolean valorEstat) {
        try {
            String usuari = u.getUsuari();
            Document filter = new Document("_id", usuari);
            Document update = new Document("$set", new Document("estat", valorEstat));
            usuaris.updateOne(filter, update);
            System.out.println("Estat actualitzat correctament per l'usuari : " + usuari);
        } catch (Exception e) {
            System.err.println("Error en actualitzar l'estat: " + e);
        }
    }
    
    public static void posarTotsElsUsuarisInactius() {
        try {
            Document update = new Document("$set", new Document("estat", false));
            usuaris.updateMany(new Document(), update);
            System.out.println("S'han posat tots els usuaris a estat desconnectat.");
        } catch (Exception e) {
            System.err.println("Error en actualitzar l'estat de tots els usuaris: " + e);
        }
    }

    
    public static boolean veureEstatSegonsUsuari(String usuari) {
        try {
            Document usuariDoc = usuaris.find(new Document("_id", usuari)).first();

            if (usuariDoc != null) {
                Boolean estat = usuariDoc.getBoolean("estat");
                
                if (estat != null) {
                    return estat;
                }
                
            } else {
                System.err.println("Usuari no trobat a la BBDD.");
            }
        } catch (Exception e) {
            System.err.println("Error en obtenir l'estat: " + e);
        }
        
        return false;
    }


    
    
 
    //encriptar Contrasenya (hash)
    public static String hashContrasenya(String contrasenya) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(contrasenya.getBytes());
            byte[] resumen = md.digest();
            
            String contrasenyaAmbHash = new String(resumen);
            
            return contrasenyaAmbHash;
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
}
