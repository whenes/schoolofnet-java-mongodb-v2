import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;;
import org.bson.Document;

import javax.print.Doc;
import java.util.Collection;

import static com.mongodb.client.model.Filters.eq;

public class App {
    private static MongoDatabase connection = Connection.getConnection();;
    private static MongoCollection<Document> collection = connection.getCollection("people");
    public static void main(String[] args) {
//        createCollection("people");
//        insertOne("Teste1", 1);
//        insertOne("Teste2", 2);
//        insertOne("Teste3", 3);
//        find();
        findOneAndUpdate();
    }

    public static void findOneAndUpdate() {
        collection.findOneAndUpdate(eq("name", "Teste2"), new Document("$set", new Document("age", 34)));
        printAll();
    }

    public static void update() {
        Block<Document> showBlock = new Block<Document>() {
            @Override
            public void apply(Document document) {
                collection.updateOne(eq("name", document.get("name")), new Document("$set", new Document("age", 33)));
            }
        };
        findBy("name", "Teste1").forEach(showBlock);
        printAll();
    }

    public static void deleteBy(String key, Object value) {
        collection.deleteOne(eq(key, value));
        printAll();
    }

    public static void printBy() {
        Block<Document> showBlock = new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        };
        findBy("name", "Teste1").forEach(showBlock);
    }

    public static FindIterable<Document> findBy(String key, Object name) {
        return collection.find(eq(key, name));
    }

    public static void printAll() {
        Block<Document> showBlock = new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        };
        findAll().forEach(showBlock);
    }

    public static FindIterable<Document> findAll() {
        return collection.find();
    }

    public static void find() {
        Block<Document> showBlock = new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());

                Integer age = document.getInteger("age");
                Integer parameter = 1;
                if (age > parameter) {
                    System.out.println("Age great than " + parameter);
                }
            }
        };

        collection.find().forEach(showBlock);
    }

    public static void insertOne(String name, Integer age) {
        Person teste = new Person(name, age);
        Document document = new Document();
        document.append("name", teste.getName());
        document.append("age", teste.getAge());
        collection.insertOne(document);
    }

    public static void createCollection(String nome) {
        connection.createCollection(nome);
    }
}
