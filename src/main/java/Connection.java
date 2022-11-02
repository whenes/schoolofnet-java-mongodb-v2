import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Connection {
    private static MongoDatabase database = null;

    public static MongoDatabase getConnection() {
        if (database == null) {
            MongoClient client = new MongoClient("localhost", 27017);
            database = client.getDatabase("java_mongo");
        }
        return database;
    }
}
