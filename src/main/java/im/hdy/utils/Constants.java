package im.hdy.utils;

import im.hdy.model.Music;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hdy on 17/02/2018.
 */
@Component
public class Constants {
    public static ArrayList<Music> musics = new ArrayList<>();

    public static Map<Session, String> users = new HashMap<>();
}
