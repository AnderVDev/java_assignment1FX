package org.assignment.assignment1_fx.model;

/**
 * DataModel class represents a model for storing champion data.
 */
public class DataModel {
    Integer champion_id;
    String name, role, attack_type;
    String difficulty;

    //Constructor
    public DataModel(Integer champion_id, String name, String role, String attack_type, String difficulty) {
        this.champion_id = champion_id;
        this.name = name;
        this.role = role;
        this.attack_type = attack_type;
        this.difficulty = difficulty;
    }

    //Setters and Getters
    public Integer getChampion_id() {
        return champion_id;
    }

    public void setChampion_id(Integer champion_id) {
        this.champion_id = champion_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAttack_type() {
        return attack_type;
    }

    public void setAttack_type(String attack_type) {
        this.attack_type = attack_type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }


}
