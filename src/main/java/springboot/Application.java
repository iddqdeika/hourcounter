package springboot;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static DBCollection stepCollection = null;

    public static void main(String[] args){
        String dataBaseName = "dev";
        String dataBaseCollectionName = "hourcount";

        Mongo mongo = new Mongo();
        DB db = mongo.getDB(dataBaseName);
        stepCollection = db.getCollection(dataBaseCollectionName);
        if (stepCollection == null){
            stepCollection = db.createCollection(dataBaseCollectionName,null);
        }

        SpringApplication.run(Application.class);
    }

}
