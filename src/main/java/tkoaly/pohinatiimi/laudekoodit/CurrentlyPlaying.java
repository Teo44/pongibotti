package tkoaly.pohinatiimi.laudekoodit;

import java.util.ArrayList;

/**
 *
 * @author teo
 */
public class CurrentlyPlaying {
    
    ArrayList<Team> playing = new ArrayList<>();
    
    public int getLength()  {
        return playing.size();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < playing.size(); i++) {
            sb.append("Table ");
            sb.append(String.valueOf(i+1));
            sb.append(": ");
            sb.append(playing.get(i));
            if (i != playing.size()-1) {
                sb.append("\n");
            }
        }
        
        if (sb.length() == 0) {
            sb.append("No registrations");
        }
        
        return sb.toString();
    }
    
    public boolean add(String team, Long id)    {
        if (playing.size() > 1) {
            return false;
        }
        playing.add(new Team(team, id));
        return true;
    }
    
    public int finishedPlaying(String team, Long ChatId) {
        for (int i = 0; i < 2; i++) {
            if (playing.get(i).getName().equals(team))    {
                if (playing.get(i).getChatId().equals(ChatId))  {
                    playing.remove(i);
                    return 0;
                }
                return 1;
            }
        }
        return 2;
    }
    
}
