package springboot.mappings;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicPages {
    @RequestMapping("/")
    public String homePage(ModelMap model){
        return "page";
    }
}
