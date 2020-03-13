package tkoaly.pohinatiimi.laudekoodit;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import io.github.cdimascio.dotenv.Dotenv;

public class LaudeBot extends TelegramLongPollingBot {

    private Dotenv dotenv = Dotenv.load();
    private PongiQueue pongiqueue;
    
    public LaudeBot()   {
        pongiqueue = new PongiQueue();
    }

    @Override
    public void onUpdateReceived(Update update) {
        
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        
        // Initialize answer message
        SendMessage answer = new SendMessage()
            .setChatId(chatId);
        
        // Choose text for answer
        if (text.equals("Hello")) {
            answer.setText("World!");
        } else if (text.startsWith("/play")) {
            pongiqueue.add(text.replace("/play ", ""));
        } else if (text.startsWith("/unplay")) {
            
        }
        
        // Send message
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return dotenv.get("henkkabot");
    }

    @Override
    public String getBotToken() {
        return dotenv.get("BOT_TOKEN");
    }
}
