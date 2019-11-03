 import obj.Channel;
import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Bot extends TelegramLongPollingBot {

    private static final String TOKEN = "921151128:AAE9wBqilZRbBOj7HTe8vsNe9kW7AxTb1Tg";
    private static final Integer vid_pause = 2;
    private static final Integer pic_pause = 2;

    private static final String NAME = "BratBratokBratishkaBot";
    private static Set<Channel> listeners = new HashSet<>();

    @Override
    public void onUpdateReceived(Update update) {
        try {
            String command = update.getMessage().getText();
            if(command.equals("/start")){
                execute(new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Насипаю"));
                listeners.add(new Channel(String.valueOf(update.getMessage().getChatId())));
                System.out.println("NEW LISTENER : " +
                        update.getMessage().getFrom().toString() + " ");
            } else if (command.equals("/stop")){
                execute(new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("stop"));
                listeners.remove(new Channel(String.valueOf(update.getMessage().getChatId())));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void sleep(int mins) {
        try {
            Thread.sleep(mins * 60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void run() {
        while (true) {
            if (!listeners.isEmpty()){
                sendContent();
            }
        }
    }

    private void sendContent() {
        try {
            Pair<String, String> path = getPath();
            if (LinkUtils.isVideo(path.getValue())) {
                System.out.println("SENDING " + path.getValue() + " AND KEY IS " + path.getKey());
                listeners.forEach(e -> {
                    try {
                        execute(LinkService.createVideoMessage(path.getValue()).setChatId(e.getChatId()));
                    } catch (TelegramApiException ex) {
                        ex.printStackTrace();
                    }
                });

                FileService.deleteFile(path.getValue());
                sleep(vid_pause);
            } else {
                System.out.println("SENDING " + path.getValue() + " AND KEY IS " + path.getKey());
                listeners.forEach(e -> {
                    try {
                        execute(LinkService.createPhotoMessage(path.getValue()).setChatId(e.getChatId()));
                    } catch (TelegramApiException ex) {
                        ex.printStackTrace();
                    }
                });

                sleep(pic_pause);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendLog(e.getMessage() + " " + e.getCause());
        }
    }

    private void sendLog(String text) {
        try {
            execute(new SendMessage()
                    .setChatId(Configuration.ADMIN_ID)
                    .setText(text
                    ));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    private org.apache.commons.lang3.tuple.Pair<String, String> getPath() {
        System.out.println("GETTING PATH");
        String link = LinkService.getLink();
        String pathToFile = FileService.process(link);
        if (pathToFile == null) {
            return getPath();
        } else {
            return org.apache.commons.lang3.tuple.Pair.of(link, pathToFile);
        }
    }

    private Boolean isNight() {
        final int hour = LocalTime.now().getHour();
        return hour < 7 && hour > 2;
    }


}
