import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import org.bson.Document;
import sun.lwawt.macosx.CSystemTray;

import java.util.Arrays;
import java.util.Set;

public class setupMongoConnection {

    public static void main( String args[] ) {
        String client_url = "mongodb://monger:HowIsThisAGoodMongoPassword%3F@mongo-n1.infra-dev.joveo.com:27000,mongo-n4.infra-dev.joveo.com:27017,mongo-n5.infra-dev.joveo.com:27017/admin?connectTimeoutMS=10000&authSource=admin&authMechanism=SCRAM-SHA-1";
        MongoClientURI uri = new MongoClientURI(client_url);

        // Connecting to the mongodb server using the given client uri.
        MongoClient mongo_client = new MongoClient(uri);

        // Fetching the database from the mongodb.
        //MongoDatabase db = mongo_client.listDatabaseNames();
        MongoIterable<String> a=mongo_client.listDatabaseNames();
        System.out.println(a.iterator().next());
        //connecting to mojo
        MongoDatabase database = mongo_client.getDatabase("mojo");

        System.out.println(database.listCollections().first());
        System.out.println(database.getCollection("mojo.aw-ads").find());
        MongoCollection<Document> coll = database.getCollection("agencies");
        System.out.println(coll.find());
        // Performing a read operation on the collection.
        FindIterable<Document> fi = coll.find();
        MongoCursor<Document> cursor = fi.iterator();
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }


        MongoDatabase database1 = mongo_client.getDatabase("mojo");
        System.out.println("Collection created successfully");
        for (String name : database1.listCollectionNames()) {
            System.out.println(name);
        }

}}