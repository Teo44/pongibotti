package tkoaly.pohinatiimi.laudekoodit;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import io.github.cdimascio.dotenv.Dotenv;

public class LaudeBot extends TelegramLongPollingBot {

    private Dotenv dotenv = Dotenv.load();
    private Registrations pongiqueue;
    
    public LaudeBot()   {
        pongiqueue = new Registrations();
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
        } else if (text.startsWith("/play ")) {
            if (pongiqueue.registerTeam(text.replace("/play ", ""))) {
                answer.setText("Team added");
            } else  {
                answer.setText("Team already exists");
            }
        } else if (text.startsWith("/unplay ")) {
            if (pongiqueue.deleteTeam(text.replace("/unplay ", "")))    {
                answer.setText("Team removed");
            } else  {
                answer.setText("Team doesn't exists");
            }
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
