package im.hdy.controller;

import com.alibaba.fastjson.JSON;
import im.hdy.model.Music;
import im.hdy.utils.Constants;
import im.hdy.utils.MusicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by hdy on 17/02/2018.
 */
@RestController
public class ParseSearch {

    @RequestMapping("/parse")
    public String parse(@RequestParam(required = true) String id) {
//        ArrayList<Music> arrays = new ArrayList<>();
        Music music = MusicUtils.get(10, id);
        music.setSongid(null);
        music.setType(null);
        music.setLink(null);
        music.setLrc(null);
        Constants.musics.add(music);
//        arrays.add(music);
        return JSON.toJSONString(Constants.musics);
    }
}
