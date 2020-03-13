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
    
    public boolean registerTeam(String name) {
        if (name == null || name.trim().isEmpty() ) {
            return false;
        }
        
        return registrations.add(new Team(name));
    }
    
    public boolean deleteTeam(String name) {
        if (!isValidName(name)) {
            return false;
        }
        return registrations.remove(new Team(name));
    }
    
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
