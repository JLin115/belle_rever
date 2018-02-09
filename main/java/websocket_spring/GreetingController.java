package websocket_spring;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {
	@Resource
	private SimpMessagingTemplate messagingTemplate;

	@RequestMapping("/helloSocket")
	public String index() {
		return "/helloSocket";

	}

	@MessageMapping("/change-notice")
	public void greeting(String value) {
		this.messagingTemplate.convertAndSend("/topic/notice", value);
	}

}
