package im.hdy.controller;

import com.alibaba.fastjson.JSON;
import im.hdy.utils.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by hdy on 17/02/2018.
 */
@RestController
public class MenuController {

    @RequestMapping("/menu")
    public String menu(){
        if(Constants.musics!=null){
            Constants.musics = new ArrayList<>();
        }
        return "success";
    }

    @RequestMapping("/getMenu")
    public String getMenu(){
        return JSON.toJSONString(Constants.musics);
    }
}
