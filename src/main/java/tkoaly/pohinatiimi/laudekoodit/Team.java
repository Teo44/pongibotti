/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tkoaly.pohinatiimi.laudekoodit;

/**
 *
 * @author danielko
 */
public class Team {
    
    private String name;

    public Team(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
