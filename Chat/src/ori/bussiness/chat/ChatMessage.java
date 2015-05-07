package ori.bussiness.chat;

import java.io.Serializable;
/**
 * Created by lukai on 5/6/2015.
 */
public class ChatMessage implements Serializable{
    private static final long serialVersionUID = 2L;
    private String type;
    private int sender;
    private int senderAvatar;//头像
    private int receiver;
    private String content;
    private String sendTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getSenderAvatar() {
        return senderAvatar;
    }

    public void setSenderAvatar(int senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
