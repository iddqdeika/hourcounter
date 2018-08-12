package springboot.dbObjects;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;

public class UserSession {

    private String login;
    private int prevSessionsSum;
    private boolean sessionStarted;
    private long currentSessionStartTime;


    public UserSession(DBObject doc){
        this.login = (String) doc.get("login");
        this.prevSessionsSum = (Integer) doc.get("prevSessionsSum");
        this.sessionStarted = (Boolean) doc.get("sessionStarted");
        this.currentSessionStartTime = (Long) doc.get("currentSessionStartTime");
    }

    public UserSession(String login, int prevSessionsSum, boolean sessionStarted, int currentSessionStartTime){
        this.login = login;
        this.prevSessionsSum = prevSessionsSum;
        this.sessionStarted = sessionStarted;
        this.currentSessionStartTime = currentSessionStartTime;
    }

    public void startSession(){
        if (!sessionStarted){
            sessionStarted = true;
            currentSessionStartTime = System.currentTimeMillis();
        }
    }

    public void stopSession(){
        if (sessionStarted){
            sessionStarted = false;
            prevSessionsSum += System.currentTimeMillis() -  currentSessionStartTime;
        }
    }

    public long getOverallSessionsSum(){
        if (sessionStarted){
            return prevSessionsSum + (System.currentTimeMillis() - currentSessionStartTime);
        }else{
            return prevSessionsSum;
        }
    }

    public BasicDBObject getAsDBObject(){
        BasicDBObject obj = new BasicDBObject();

        obj.put("login", this.login);
        obj.put("prevSessionsSum", this.prevSessionsSum);
        obj.put("sessionStarted", this.sessionStarted);
        obj.put("currentSessionStartTime",currentSessionStartTime);

        return obj;
    }

    public BasicDBObject getDBQueryObject(){
        BasicDBObject obj = new BasicDBObject();

        obj.put("login", this.login);

        return obj;
    }


}
