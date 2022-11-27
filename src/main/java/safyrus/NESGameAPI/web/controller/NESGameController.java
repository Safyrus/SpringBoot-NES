package safyrus.NESGameAPI.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import safyrus.NESGameAPI.dao.INESGameDAO;
import safyrus.NESGameAPI.model.NESGame;

@RestController
public class NESGameController {
    
    @Autowired
    private INESGameDAO dao;

    /**
     * Get all NES games
     * @return all the NES game
     */
    @GetMapping(value = "/NESGames")
    public List<NESGame> getAll() {
        return dao.findAll();
    }

    /***
     * Get one NES game
     * @param id Catalog ID of the game
     * @return The NES game
     */
    @GetMapping(value = "/NESGames/{id}")
    public NESGame getById(@PathVariable String id) {
        return dao.findByID(id);
    }

    /**
     * Add a NES game
     * @param game Game to add
     */
    @PostMapping(value = "/NESGames")
    public void add(@RequestBody NESGame game) {
        dao.save(game);
    }

    /**
     * Update a NES game
     * @param id Catalog ID of the game
     * @param game Game to update
     */
    @PutMapping(value = "/NESGames/{id}")
    public void update(@PathVariable String id, @RequestBody NESGame game) {
        dao.update(id, game);
    }

    /**
     * Delete a NES game
     * @param id Catalog ID of the game
     */
    @DeleteMapping(value = "/NESGames/{id}")
    public void remove(@PathVariable String id) {
        dao.delete(id);
    }
}
