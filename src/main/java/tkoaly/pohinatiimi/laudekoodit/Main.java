package tkoaly.pohinatiimi.laudekoodit;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    
    public static void main(String [] args) {
        Registrations regs = new Registrations();
        regs.registerTeam("Team X");
        regs.deleteTeam("Tiimi X");
        regs.deleteTeam("Team X");
        //regs.registerTeam("Tiimi XX");
        System.out.println(regs);
        /*
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        
        try {
            botsApi.registerBot(new LaudeBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }*/
    }
    
}
