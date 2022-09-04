package com.game.controller;

import com.game.dto.PlayerDTO;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerService;
import com.game.util.PageableCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final PlayerService playerService;
    public UserController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/rest/players")
    public ResponseEntity<Player> registerPlayer(@RequestBody PlayerDTO playerDto) {
        Player registered = playerService.registerPlayer(playerDto);
        return new ResponseEntity<>(registered, HttpStatus.OK);
    }
    @GetMapping("/rest/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return new ResponseEntity<>(playerService.findPlayerById(id), HttpStatus.OK);
    }
    @GetMapping("/rest/players")
    public ResponseEntity<List<Player>> getPlayersAccordingToParams(@RequestParam(required = false) String name,
                                                                    @RequestParam(required = false) String title,
                                                                    @RequestParam(required = false) Race race,
                                                                    @RequestParam(required = false) Profession profession,
                                                                    @RequestParam(required = false) Long after,
                                                                    @RequestParam(required = false) Long before,
                                                                    @RequestParam(required = false) Boolean banned,
                                                                    @RequestParam(required = false) Integer minExperience,
                                                                    @RequestParam(required = false) Integer maxExperience,
                                                                    @RequestParam(required = false) Integer minLevel,
                                                                    @RequestParam(required = false) Integer maxLevel,
                                                                    @RequestParam(required = false) Integer pageSize,
                                                                    @RequestParam(required = false) Integer pageNumber,
                                                                    @RequestParam(required = false) PlayerOrder order) {
        Pageable pageable = PageableCreator.createPageableFromParams(pageSize, pageNumber, order);
        List<Player> players = playerService.findPlayersByRequestedParam(name, title, race,
                                                                         profession,  after,
                                                                         before, banned, minExperience, maxExperience,
                                                                         minLevel, maxLevel, pageable);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }
    @GetMapping("/rest/players/count")
    public ResponseEntity<Integer> getPlayersCount(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String title,
                                                   @RequestParam(required = false) Race race,
                                                   @RequestParam(required = false) Profession profession,
                                                   @RequestParam(required = false) Long after,
                                                   @RequestParam(required = false) Long before,
                                                   @RequestParam(required = false) Boolean banned,
                                                   @RequestParam(required = false) Integer minExperience,
                                                   @RequestParam(required = false) Integer maxExperience,
                                                   @RequestParam(required = false) Integer minLevel,
                                                   @RequestParam(required = false) Integer maxLevel) {
        int count = playerService.countPlayersByRequestedParam(name, title, race,
                                                               profession,  after,
                                                               before, banned, minExperience, maxExperience,
                                                               minLevel, maxLevel);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @PostMapping("/rest/players/{id}")
    public ResponseEntity<Player> updatePlayerById(@PathVariable Long id,
                                                   @RequestBody PlayerDTO playerDto) {
        Player updated = playerService.updatePlayer(id, playerDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @DeleteMapping("/rest/players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.ok().build();
    }
}
