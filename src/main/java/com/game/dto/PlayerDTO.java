package com.game.dto;

import com.game.entity.Profession;
import com.game.entity.Race;


public class PlayerDTO {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long birthday;
    private Boolean banned;
    private Integer experience;

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }

    public Long getBirthday() {
        return birthday;
    }

    public Boolean isBanned() {
        return banned;
    }

    public Integer getExperience() {
        return experience;
    }
    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
