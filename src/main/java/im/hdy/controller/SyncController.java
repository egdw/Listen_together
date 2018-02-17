package im.hdy.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by hdy on 17/02/2018.
 */
@Controller
public class SyncController {

    @MessageMapping("/sync")
    @SendTo("/topic/greetings")
    public void sync(){

    }
}
