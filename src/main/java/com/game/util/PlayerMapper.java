package com.game.util;

import com.game.dto.PlayerDTO;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;

import java.sql.Date;


public class PlayerMapper {
   public static Player mapFromPlayerDto(PlayerDTO playerDto) {
      boolean banned = playerDto.isBanned() != null ? playerDto.isBanned() : false;
      int experience = playerDto.getExperience();
      int level = countLevel(experience);
      int untilNextLevel = countUntilNextLevel(level, experience);

      return Player.newBuilder()
              .setName(playerDto.getName())
              .setTitle(playerDto.getTitle())
              .setBanned(banned)
              .setBirthDay(playerDto.getBirthday())
              .setExperience(experience)
              .setProfession(playerDto.getProfession())
              .setRace(playerDto.getRace())
              .setLevel(level)
              .setUntilNextLevel(untilNextLevel)
              .build();
   }
   public static Player updateFields(Player player, PlayerDTO playerDto) {
      String name = playerDto.getName();
      String title = playerDto.getTitle();
      Long birthday = playerDto.getBirthday();
      Integer experience = playerDto.getExperience();

      PlayerDtoChecker.checkPlayerFieldsForUpdate(birthday, experience);

      Race race = playerDto.getRace();
      Profession profession = playerDto.getProfession();
      Boolean banned = playerDto.isBanned();

      if (name != null) {
         player.setName(name);
      }
      if (title != null) {
         player.setTitle(title);
      }
      if (race != null) {
         player.setRace(race);
      }
      if (profession != null) {
         player.setProfession(profession);
      }
      if (banned != null) {
         player.setBanned(banned);
      }
      if (birthday != null) {
         player.setBirthday(new Date(birthday));
      }
      if (experience != null) {
         setExperienceLevelAndUntilNextLevel(player, experience);
      }
      return player;
   }
   private static int countLevel(int experience) {
      return (int) ((Math.sqrt(2500 + 200 * experience) - 50) / 100);
   }
   private static int countUntilNextLevel(int lvl, int exp) {
      return 50 * (lvl + 1) * (lvl + 2) - exp;
   }
   private static void setExperienceLevelAndUntilNextLevel(Player player, Integer experience) {
      player.setExperience(experience);
      int lvl = countLevel(experience);
      player.setLevel(lvl);
      int untilNextLevel = countUntilNextLevel(lvl, experience);
      player.setUntilNextLevel(untilNextLevel);
   }

}
