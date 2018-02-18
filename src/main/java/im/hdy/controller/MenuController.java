package im.hdy.controller;

import com.alibaba.fastjson.JSON;
import im.hdy.utils.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hdy on 17/02/2018.
 */
@RestController
public class MenuController {

    @RequestMapping("/menu")
    public String menu() {
        if (Constants.musics != null) {
            Constants.musics = new HashMap<>();
        }
        return "success";
    }

    @RequestMapping("/getMenu")
    public String getMenu(HttpSession session) {
        String id = (String) session.getAttribute("id");
        if (id != null) {
            return JSON.toJSONString(Constants.getMusicList(id));
        }
        return JSON.toJSONString(Constants.musics);
    }
}
