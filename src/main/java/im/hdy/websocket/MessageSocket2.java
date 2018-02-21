package im.hdy.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import im.hdy.model.Message;
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
 * 用于表单的播放
 */
@ServerEndpoint("/sync2")
@Component
public class MessageSocket2 {

    private Session session;
    private String id;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("连接上线:" + session);
        this.session = session;
    }

    @OnClose
    public void onClose() throws IOException {
        Constants.delUser(session, id);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println(message);
        dispose(message, session);
    }

    /**
     * 给当前的session对象发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给指定的session发送相应的信息
     *
     * @param message 消息内容
     * @param session 发送对象的session
     */
    public void sendMessageBySession(String message, Session session) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理不同的消息
     */
    public void dispose(String message, Session session) {
        JSONObject parse = (JSONObject) JSON.parse(message);
        Integer type = parse.getInteger("type");
        switch (type) {
            //开房间或者是加入房间
            case 0:
                Constants.addUser(session, parse.getString("id"));
                this.id = parse.getString("id");
                //从内存中读取数据.判断当前的进度是否和其他人保持一致
                if (Constants.isRoomMaster(id, session)) {
                    //如果已经是房主了.就不用同步了~
                    sendMessage(JSON.toJSONString(new Message(0, true, 0, 0l, null, null)));
                } else {
                    //如果不是房主.那么就服务器发送给房主.获取房主的播放信息
                    Session roomMaster = Constants.getRoomMaster(id);
                    //请求房主上传当前的播放记录
                    Constants.addSync(roomMaster, session);
                    sendMessageBySession(JSON.toJSONString(new Message(1, true, 0, 0l, null, null)), roomMaster);
                }
                break;
            //房主同步数据上传
            case 1:
                //这里获取到的同步信息因为会有延迟.所以需要客户端上传的时候需要上传时候的时间.
                //然后回传到其他的用户的时候.计算中间的延时.降低延时.
                Constants.sendSyncMessage(session, message);
                break;
            case 2:
                //各种状态操作时
                Constants.sendStatus(this.id, message,session,3);
                break;
            case 3:
                //拖动进度条.改变歌曲播放时
                Constants.sendStatus(this.id, message,session,4);
                break;
            default:
                break;
        }
    }

}
