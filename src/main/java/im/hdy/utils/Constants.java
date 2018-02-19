package im.hdy.utils;

import com.alibaba.fastjson.JSON;
import im.hdy.model.ClientMessage;
import im.hdy.model.Message;
import im.hdy.model.Music;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.*;

/**
 * Created by hdy on 17/02/2018.
 */
@Component
public class Constants {
    //    public static ArrayList<Music> musics = new ArrayList<>();
    public static Map<String, ArrayList<Music>> musics = new HashMap<>();
    //    public static Map<Session, String> sessionUsers = new HashMap<>();
    public static Map<String, LinkedList<Session>> users = new HashMap<>();
    //需要同步的数据
    public static Map<Session, LinkedList<Session>> syncs = new HashMap<>();


    /**
     * 添加同步
     *
     * @param master 目标房主
     * @param from   来自哪里
     */
    public static void addSync(Session master, Session from) {
        LinkedList<Session> sessions = syncs.get(master);
        if (sessions == null) {
            sessions = new LinkedList<>();
        }
        sessions.add(from);
        syncs.put(master, sessions);

    }

    /**
     * 给每一个等待同步的用户发送过去
     *
     * @param master
     * @param message
     */
    public static void sendSyncMessage(Session master, String message) {
        LinkedList<Session> sessions = syncs.get(master);
        if (sessions != null) {
            ClientMessage clientMessage = JSON.parseObject(message, ClientMessage.class);
            Iterator<Session> iterator = sessions.iterator();
            while (iterator.hasNext()) {
                Session session = iterator.next();
                System.out.println("接收到的房主的信息:" + clientMessage);
                Message serverMessage = new Message(2, false, clientMessage.getIndex(), clientMessage.getTime(), clientMessage.getAction(), clientMessage.getTimestamp());
                System.out.println("发送服务器的信息" + serverMessage);
                try {
                    session.getBasicRemote().sendText(JSON.toJSONString(serverMessage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            sessions.clear();
            syncs.put(master, sessions);
        }
    }

    /**
     * 向频道里的所有session发送状态
     */
    public static void sendStatus(String key, String message,Session self) {
        LinkedList<Session> sessions = users.get(key);
        Iterator<Session> iterator = sessions.iterator();
        ClientMessage clientMessage = JSON.parseObject(message, ClientMessage.class);
        Message serverMessage = new Message(3, false, clientMessage.getIndex(), clientMessage.getTime(), clientMessage.getAction(), clientMessage.getTimestamp());
        String jsonString = JSON.toJSONString(serverMessage);
        while (iterator.hasNext()) {
            Session session = iterator.next();
            try {
                if(!self.equals(session)){
                    session.getBasicRemote().sendText(jsonString);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取相应房间的房主
     *
     * @param key 房间名
     * @return session对象
     */
    public static Session getRoomMaster(String key) {
        LinkedList<Session> sessions = users.get(key);
        return sessions.getFirst();
    }

    /**
     * 判断是否是房主
     */
    public static boolean isRoomMaster(String key, Session session) {
        LinkedList<Session> sessions = users.get(key);
        if (session.equals(sessions.getFirst())) {
            //说明是房主
            return true;
        }
        return false;
    }

    /**
     * 获取房间内的用户
     *
     * @param key
     */
    public static Session getRoomFirstUsers(String key) {
        LinkedList<Session> sessions = users.get(key);
        return sessions.getFirst();
    }

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
