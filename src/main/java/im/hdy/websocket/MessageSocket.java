package im.hdy.websocket;

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

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        Constants.users.put(session, null);
    }

    @OnClose
    public void onClose() throws IOException {
        Constants.users.remove(this.session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println(message);
    }

}