/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tkoaly.pohinatiimi.laudekoodit;

import java.util.ArrayList;

/**
 *
 * @author danielko
 */
public class Registrations {
   
    private ArrayList<Team> registrations;

    public Registrations() {
        this.registrations = new ArrayList<>();
    }
    
    public boolean registerTeam(String name, Long chatId) {
        if (!isValidName(name)) {
            return false;
        }
        Team team = new Team(name, chatId);
        
        if (registrations.contains(team)) {
            return false;
        }
        
        return registrations.add(team);
    }
    
    public boolean deleteTeam(String name, Long chatId) {
        if (!isValidName(name)) {
            return false;
        }
        Team registeredTeam = null;
        for (Team registered : registrations) {
            if (registered.getName().equals(name)) {
                registeredTeam = registered;
            }
        }
        
        if (registeredTeam != null && registeredTeam.getChatId() == chatId) {
            return registrations.remove(registeredTeam);
        }
        
        return false;
    }
    
    //public boolean confirmTeamPlays(String name) {
        
    //}
    
    private boolean isValidName(String name) {
        return !(name == null || name.trim().isEmpty());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Team t : registrations) {
            sb.append(t).append("\n");
        }
        if (sb.length() == 0) {
            sb.append("No registrations").append("\n");
        }
        
        return sb.substring(0, sb.length()); //remove trailing newline
    }
}
