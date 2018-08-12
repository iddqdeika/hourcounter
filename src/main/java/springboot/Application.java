package springboot;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class Application {

    public static DBCollection stepCollection = null;
    public static String dbstring;

    public static void main(String[] args){
        String dataBaseName = System.getenv("MONGOHQ_URL");
        String dataBaseCollectionName = "hourcount";


        dbstring = System.getenv("MONGOHQ_URL");
        Mongo mongo = new Mongo();
        DB db = mongo.getDB(dataBaseName);

        stepCollection = db.getCollection(dataBaseCollectionName);
        if (stepCollection == null){
            stepCollection = db.createCollection(dataBaseCollectionName,null);
        }

        SpringApplication.run(Application.class);
    }

}
