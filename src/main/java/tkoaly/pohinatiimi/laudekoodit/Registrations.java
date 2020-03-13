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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Team t : registrations) {
            sb.append(t).append("\n");
        }
        return sb.toString();//sb.substring(0, sb.length()-1);
    }
}
