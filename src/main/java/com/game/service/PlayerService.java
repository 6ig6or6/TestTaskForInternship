package com.game.service;

import com.game.dto.PlayerDTO;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exception.PlayerNotFoundException;
import com.game.exception.WrongIdException;
import com.game.repository.PlayerRepository;
import com.game.util.PlayerDtoChecker;
import com.game.util.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    public Player findPlayerById(Long id) {
      if (id <= 0) {
            throw new WrongIdException();
        }
      return playerRepository
              .findPlayerById(id)
              .orElseThrow(PlayerNotFoundException::new);
    }
    public void deletePlayerById(Long id) {
        Player player = findPlayerById(id); //throws PlayerNotFoundException if player with this id doesn't exist
        playerRepository.deleteById(player.getId());
    }
    public Player updatePlayer(Long id, PlayerDTO playerDto) {
        Player player = findPlayerById(id); //throws PlayerNotFoundException if player with this id doesn't exist
        Player toSave = PlayerMapper.updateFields(player, playerDto);
        return playerRepository.save(toSave);
    }
    public Player registerPlayer(PlayerDTO playerDto) {
        PlayerDtoChecker.checkPlayerFieldsForRegistration(playerDto);
        Player player = PlayerMapper.mapFromPlayerDto(playerDto);
        return playerRepository.save(player);
    }
     public List<Player> findPlayersByRequestedParam(String name, String title,
                                                     Race race, Profession profession,
                                                     Long after, Long before,
                                                     Boolean banned, Integer minExperience,
                                                     Integer maxExperience, Integer minLevel,
                                                     Integer maxLevel, Pageable pageable) {

        Date dateAfter = after != null ? new Date(after) : null;
        Date dateBefore = before != null ? new Date(before) : null;

        return playerRepository.findBy(name, title,
                                       race, profession,
                                       dateAfter, dateBefore,
                                       banned, minExperience,
                                       maxExperience, minLevel,
                                       maxLevel, pageable);
    }
    public int countPlayersByRequestedParam(String name, String title,
                                            Race race, Profession profession,
                                            Long after, Long before,
                                            Boolean banned, Integer minExperience,
                                            Integer maxExperience, Integer minLevel,
                                            Integer maxLevel) {

        Date dateAfter = after != null ? new Date(after) : null;
        Date dateBefore = before != null ? new Date(before) : null;

        return playerRepository.countBy(name, title,
                race, profession,
                dateAfter, dateBefore,
                banned, minExperience,
                maxExperience, minLevel,
                maxLevel);
    }


}
