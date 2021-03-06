package springboot;

import com.mongodb.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class Application {

    public static DBCollection stepCollection = null;
    public static String dbstring = null;

    public static void main(String[] args){
        String dataBaseName = System.getenv("MONGODB_URI");
        String dataBaseCollectionName = "hourcount";


        dbstring = System.getenv("MONGODB_URI");
        try {
            MongoURI uri = new MongoURI(System.getenv("MONGODB_URI"));

            DB db = uri.connectDB();

            stepCollection = db.getCollection(dataBaseCollectionName);
            if (stepCollection == null) {
                stepCollection = db.createCollection(dataBaseCollectionName, null);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        SpringApplication.run(Application.class);
    }

}
