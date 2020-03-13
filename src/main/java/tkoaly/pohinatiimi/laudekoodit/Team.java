/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tkoaly.pohinatiimi.laudekoodit;

import java.util.Objects;

/**
 *
 * @author danielko
 */
public class Team {
    
    private String name;
    private Long chatId;

    public Team(String name, Long chatId) {
        this.name = name;
        this.chatId = chatId;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getChatId() {
        return this.getChatId();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Team)) return false;
        Team other = (Team) o;
        return this.name.equals(other.getName());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    
    @Override
    public String toString() {
        return this.name;
    }
}
