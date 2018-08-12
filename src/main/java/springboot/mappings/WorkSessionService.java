package springboot.mappings;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.dbObjects.UserSession;


import static springboot.Application.dbstring;
import static springboot.Application.stepCollection;

@Controller
@RequestMapping(value = "/worksession", method = RequestMethod.GET)
public class WorkSessionService {

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String main(ModelMap model){
        model.addAttribute("message", dbstring);
        return "page";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String resultOfSession(ModelMap model, @RequestParam("user") String login){
        UserSession session = getSessionByLogin(login);
        model.addAttribute("message", session.getOverallSessionsSum()/1000 + " секунд");
        return "result";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String startSession(ModelMap model, @RequestParam("user") String login){
        UserSession session = getSessionByLogin(login);
        session.startSession();
        stepCollection.update(session.getDBQueryObject(),session.getAsDBObject());

        model.addAttribute("message",session.getOverallSessionsSum()/60000 + " минут");
        return "blank";
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public String stopSession(ModelMap model, @RequestParam("user") String login){
        UserSession session = getSessionByLogin(login);
        session.stopSession();
        stepCollection.update(session.getDBQueryObject(),session.getAsDBObject());

        model.addAttribute("message",session.getOverallSessionsSum()/60000 + " минут");
        return "blank";
    }

    private UserSession getSessionByLogin(String login){
        DBObject sessionObj;
        UserSession session;
        BasicDBObject obj = new BasicDBObject();
        obj.put("login",login);
        DBObject result =  stepCollection.findOne(obj);
        if (result!=null){
            sessionObj = result;
            session = new UserSession(sessionObj);
        }else{
            session = new UserSession(login,0,false,0);
            stepCollection.insert(session.getAsDBObject());
        }
        return session;
    }
}
