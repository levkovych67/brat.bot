package obj;

import java.util.Objects;

public class Channel {

    private String chatId;

    public Channel(String chatId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(chatId, channel.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId);
    }
}
