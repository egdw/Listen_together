package im.hdy.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import im.hdy.utils.Constants;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by hdy on 17/02/2018.
 */
@ServerEndpoint("/sync")
@Component
public class MessageSocket {

    private Session session;
    private String id;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("连接上线:" + session);
        this.session = session;
        System.out.println(Constants.users.size());
    }

    @OnClose
    public void onClose() throws IOException {
        Constants.delUser(session, id);
        System.out.println(Constants.users.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println(message);
        dispose(message, session);
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 处理不同的消息
     */
    public void dispose(String message, Session session) {
        JSONObject parse = (JSONObject) JSON.parse(message);
        Integer type = parse.getInteger("type");
        switch (type) {
            case 0:
                Constants.addUser(session, parse.getString("id"));
                this.id = parse.getString("id");
                System.out.println("添加用户");
                break;
            default:
                break;
        }
    }

}
