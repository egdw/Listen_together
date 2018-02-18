package im.hdy.utils;

import im.hdy.model.Music;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by hdy on 17/02/2018.
 */
@Component
public class Constants {
    //    public static ArrayList<Music> musics = new ArrayList<>();
    public static Map<String, ArrayList<Music>> musics = new HashMap<>();
    //    public static Map<Session, String> users = new HashMap<>();
    public static Map<String, LinkedList<Session>> users = new HashMap<>();

    /**
     * 添加用户
     */
    public static void addUser(Session session, String key) {
        LinkedList<Session> user = users.get(key);
        if (user == null) {
            user = new LinkedList<>();
            user.add(session);
        } else {
            user.add(session);
        }
        users.put(key, user);
    }

    /**
     * 删除用户
     */
    public static void delUser(Session session, String key) {
        LinkedList<Session> user = users.get(key);
        if (user != null) {
            user.remove(session);
        }
        users.put(key, user);
    }

    /**
     * 添加音乐信息到内存当中
     *
     * @param key   关键词
     * @param music 音乐
     */
    public static void addMusic(String key, Music music) {
        ArrayList<Music> list = musics.get(key);
        if (list != null) {
            list.add(music);
            musics.put(key, list);
        } else {
            ArrayList<Music> musicArrayList = new ArrayList<>();
            musicArrayList.add(music);
            musics.put(key, musicArrayList);
        }
    }

    /**
     * 根据关键词获取音乐列表
     *
     * @param key 关键词
     * @return 音乐集合
     */
    public static ArrayList<Music> getMusicList(String key) {
        ArrayList<Music> music = musics.get(key);
        return music;
    }
}
