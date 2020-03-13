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
    /**
     * Returns 0 if deletion succeeded. Returns -1 if got badly formatted
     * (empty) team name. return -2 if team doesnt exists. return -3 if trying to delete with the wrong chatId
     * (from the wrong chat!)
     * @param name
     * @param chatId
     * @return 
     */
    public int deleteTeam(String name, Long chatId) {
        if (!isValidName(name)) {
            return -1;
            //returnVal = -1;
        }
        
        Team registeredTeam = null;
        
        for (Team registered : registrations) {
            if (registered.getName().equals(name)) {
                registeredTeam = registered;
            }
        }
        
        
        if (registeredTeam == null) {
            return -2;
            //returnVal = -2;
            //return registrations.remove(registeredTeam);
        }
        
        if (!registeredTeam.getChatId().equals(chatId)) {
            return -3;
        }
        
        if (registrations.remove(registeredTeam)) {
            return 0;
        }
        
        return -4;
    }
    
    //public boolean confirmTeamPlays(String name) {
        
    //}
    
    private boolean isValidName(String name) {
        return !(name == null || name.trim().isEmpty());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < registrations.size(); i++) {
            sb.append(String.valueOf(i+1));
            sb.append(": ");
            sb.append(registrations.get(i));
            if (i != registrations.size()-1) {
                sb.append("\n");
            }
        }
        
        if (sb.length() == 0) {
            sb.append("No registrations");
        }
        
        return sb.toString();
    }
}
