package im.hdy.controller;

import im.hdy.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Created by hdy on 17/02/2018.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String create_room(@PathVariable() String id, HttpSession session) {
        session.setAttribute("id", id);
        return "index";
    }

}
