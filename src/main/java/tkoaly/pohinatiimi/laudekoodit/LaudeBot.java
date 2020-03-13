package tkoaly.pohinatiimi.laudekoodit;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import io.github.cdimascio.dotenv.Dotenv;

public class LaudeBot extends TelegramLongPollingBot {

    private Dotenv dotenv = Dotenv.load();
    private Registrations pongiqueue;
    private CurrentlyPlaying curPlaying;
    
    public LaudeBot()   {
        pongiqueue = new Registrations();
        curPlaying = new CurrentlyPlaying();
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
            if (curPlaying.getLength() < 2)  {
                curPlaying.add(text.replace("/play ", ""), chatId);
                answer.setText("A table is free, you can play right now");
            } else  {
                if (pongiqueue.registerTeam(text.replace("/play ", ""), chatId)) {
                    String answerStr = "Team '" + text.replace("/play ", "") + "' added.\nCurrent queue:\n\n";
                    answerStr += pongiqueue.toString();
                    answer.setText(answerStr);
                } else  {
                    answer.setText("Team already exists");
                }
            }
        } else if (text.startsWith("/unplay ")) {
            int remStatus = pongiqueue.deleteTeam(text.replace("/unplay ", ""), chatId);
            if ( remStatus == 0) {
                answer.setText("Team removed");
            } else if (remStatus == -1) {
                answer.setText("Bad teamname (empty)");
            } else if (remStatus == -2) {
                answer.setText("Team does not exist");
            } else if (remStatus == -3) {
                answer.setText("You can't remove others team!");
            } else {
                answer.setText("VIIIIIIIIIIIIIIIIIIIIIIIIRHE");
            }
        } else if (text.equals("/list")) {
            String queue = "";
            queue += "Currently playing: \n";
            queue += curPlaying.toString();
            queue += "\n\nCurrent queue:\n";
            queue += pongiqueue.toString();
            
            answer.setText(queue);
            
        } else if (text.startsWith("/finished "))   {
            int res = curPlaying.finishedPlaying(text.replace("/finished ", ""), chatId);
            if (res == 0) {
                Team tiimi = pongiqueue.getFirst();
                Long nextTeamID = tiimi.getChatId();
                String tiimiNimi = tiimi.getName();
                kutsuPelaamaan(tiimiNimi, nextTeamID);
                curPlaying.add(tiimiNimi, nextTeamID);
                pongiqueue.deleteTeam(tiimiNimi, nextTeamID);
                answer.setText("Team removed from play");
            } else if (res == 1)  {
                answer.setText("You can't remove another person's team");
            } else {
                answer.setText("A team with that name is not in play");
            }
        } else {
            answer.setText("Unknonwn command, have more drinks!");
        } 
        
        // Send message
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    
    public void kutsuPelaamaan(String tiimi, Long id)    {
        SendMessage answer2 = new SendMessage()
            .setChatId(id);
        answer2.setText("A table is free, you can proceed to play");
        try {
            execute(answer2);
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
