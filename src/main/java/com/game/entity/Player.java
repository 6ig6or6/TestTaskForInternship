package com.game.entity;

import javax.persistence.*;
import java.util.Date;

/** lombok isn't allowed by the task*/

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 12)
    private String name;
    @Column(length = 30)
    private String title;
    @Enumerated(EnumType.STRING)
    private Race race;
    @Enumerated(EnumType.STRING)
    private Profession profession;
    private int experience;
    private int level;
    private int untilNextLevel;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private Boolean banned;
    public Player() {
    }
    public Long getId() {
        return id;
    }

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

    public int getExperience() {
        return experience;
    }
    public int getLevel() {
        return level;
    }

    public int getUntilNextLevel() {
        return untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }
    public boolean isBanned() {
        return banned;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setUntilNextLevel(int untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
    public static Builder newBuilder() {
        return new Player().new Builder();
    }
    public class Builder {
    private Builder() {
    }
    public Player build() {
      return Player.this;
        }
    public Builder setName(String name) {
        Player.this.name = name;
       return this;
    }
        public Builder setTitle(String title) {
            Player.this.title = title;
            return this;
        }
        public Builder setRace(Race race) {
            Player.this.race = race;
            return this;
        }
        public Builder setProfession(Profession profession) {
            Player.this.profession = profession;
            return this;
        }
        public Builder setBirthDay(long birthday) {
            Player.this.birthday = new Date(birthday);
            return this;
        }
        public Builder setBanned(boolean banned) {
            Player.this.banned = banned;
            return this;
        }
        public Builder setExperience(int experience) {
            Player.this.experience = experience;
            return this;
        }
        public Builder setLevel(int level) {
            Player.this.level = level;
            return this;
        }
        public Builder setUntilNextLevel(int untilNextLevel) {
            Player.this.untilNextLevel = untilNextLevel;
            return this;
        }

    }
}
