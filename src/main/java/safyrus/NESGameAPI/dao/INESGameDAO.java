package safyrus.NESGameAPI.dao;

import java.util.List;

import safyrus.NESGameAPI.model.NESGame;

public interface INESGameDAO {
    /**
     * 
     * @return
     */
    List<NESGame> findAll();

    /**
     * 
     * @param id
     * @return
     */
    NESGame findByID(String id);

    /**
     * 
     * @param game
     */
    void save(NESGame game);

    /**
     * 
     * @param id
     * @param game
     */
    void update(String id, NESGame game);

    /**
     * 
     * @param id
     */
    void delete(String id);
}
