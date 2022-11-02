import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class FirstExample {
    public static void main(String[] args) {
        createAndDropCollections();
    }

    public static void createAndDropCollections() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("java_mongo");

        database.createCollection("people");

        for (String name: database.listCollectionNames()) {
            System.out.println(name);
        }

        MongoCollection collection = database.getCollection(("people"));
        collection.drop();
    }
}
