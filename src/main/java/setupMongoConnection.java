import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.DBCursor;

//import org.bson.Document;
import sun.lwawt.macosx.CSystemTray;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class setupMongoConnection {

    public static void main( String args[] ) {
        //String client_url = "mongodb://monger:HowIsThisAGoodMongoPassword%3F@mongo-n1.infra-dev.joveo.com:27000,mongo-n4.infra-dev.joveo.com:27017,mongo-n5.infra-dev.joveo.com:27017/admin?connectTimeoutMS=10000&authSource=admin&authMechanism=SCRAM-SHA-1";
        String client_url = "mongodb://monger:HowIsThisAGoodMongoPassword%3F@prodmongo-25.infra.joveo.com:27000,superprodmongo-1.infra.joveo.com:27017,superprodmongo-2.infra.joveo.com:27017,superprodmongo-3.infra.joveo.com:27017/admin?3t.connectTimeout=10000&3t.uriVersion=2&3t.defaultColor=231,52,70&3t.connection.name=aragon-prod&readPreference=primary&3t.socketTimeout=0&3t.sharded=true&3t.connectionMode=multi";


        MongoClientURI uri = new MongoClientURI(client_url);

        // Connecting to the mongodb server using the given client uri.
        MongoClient mongo_client = new MongoClient(uri);
        DB db = mongo_client.getDB("mojo");
        DBCollection dbCollection = db.getCollection("publisherStats");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("publisher", "Lead5");

        //DBCursor with the find query result
        DBCursor cursor = dbCollection.find(searchQuery);

        //Fetching the response
        String response = null;
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toString());
            }
        }
        finally {
            cursor.close();
        }
        // Fetching the database from the mongodb.
        //MongoDatabase db = mongo_client.listDatabaseNames();
        MongoIterable<String> a=mongo_client.listDatabaseNames();
        System.out.println(a.iterator().next());
        //connecting to mojo
        MongoDatabase database = mongo_client.getDatabase("mojo");

   /* //DB db= (DB) mongo_client.getDatabase("mojo");
        System.out.println(database.listCollections().first());
        //System.out.println(database.getCollection("mojo.aw-ads").find());
        MongoCollection<Document> coll = database.getCollection("agencies");

        System.out.println(coll.find());
        // Performing a read operation on the collection.
        FindIterable<Document> fi = coll.find();
        MongoCursor<Document> cursor = fi.iterator();
        try {
            while(cursor.hasNext()) {
               // System.out.println(cursor.next());
            }
        } finally {
            cursor.close();*/
        }
        /*DBCollection coll= (DBCollection) database.getCollection("agencies");
        BasicDBObject neQuery = new BasicDBObject();
        neQuery.put("name", new BasicDBObject("$ne", "joveo"));
        DBCursor cursor6 = coll.find(neQuery);
        while(cursor6.hasNext()){
            System.out.printf(String.valueOf(cursor6.next()));
        }*/


       /* MongoDatabase database1 = mongo_client.getDatabase("mojo");
        System.out.println("Collection created successfully");
        for (String name : database1.listCollectionNames()) {
            System.out.println(name);
        }*/

}