package tkoaly.pohinatiimi.laudekoodit;

import java.util.ArrayList;

/**
 *
 * @author teo
 */
public class CurrentlyPlaying {
    
    Team[] playing = new Team[2];
    
    public int getLength()  {
        int pelaajat = 0;
        if (playing[0] != null) {
            pelaajat++;
        } if (playing[1] != null) {
            pelaajat++;
        }
        return pelaajat;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            sb.append("Table ");
            sb.append(String.valueOf(i+1));
            sb.append(": ");
            sb.append(playing[i]);
            if (i != 1) {
                sb.append("\n");
            }
        }
        
        if (sb.length() == 0) {
            sb.append("No registrations");
        }
        
        return sb.toString();
    }
    
    public boolean add(String team, Long id)    {
        if (getLength() > 1) {
            return false;
        }
        if (playing[0] == null) {
            playing[0] = (new Team(team, id));
            return true;
        } else if (playing[1] == null) {
            playing[1] = (new Team(team, id));
            return true;
        }
        return false;
    }
    
    public int finishedPlaying(String team, Long ChatId) {
        for (int i = 0; i < 2; i++) {
            if (playing[i].getName().equals(team))    {
                if (playing[i].getChatId().equals(ChatId))  {
                    playing[i] = null;
                    return 0;
                }
                return 1;
            }
        }
        return 2;
    }
    
}
