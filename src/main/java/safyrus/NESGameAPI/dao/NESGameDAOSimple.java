package safyrus.NESGameAPI.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import safyrus.NESGameAPI.model.NESGame;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

@Repository
public class NESGameDAOSimple implements INESGameDAO {

    private JSONObject db;
    private String filename;

    final String ARRAY_KEY = "game";

    public void init(String jsonPath) throws FileNotFoundException {
        filename = jsonPath;
        // read JSON
        JSONTokener parser = new JSONTokener(new FileInputStream(new File(jsonPath)));
        db = new JSONObject(parser);
    }

    private void saveJSONFile(JSONObject newdb) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(newdb.toString(2));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<NESGame> findAll() {
        // json to array
        JSONArray data = db.getJSONArray(ARRAY_KEY);
        // result array
        ArrayList<NESGame> list = new ArrayList<>();

        // get all games
        for (int i = 0; i < data.length(); i++) {
            JSONObject json = data.getJSONObject(i);
            NESGame game = new NESGame();
            game.set(json);
            list.add(game);
        }

        return list;
    }

    @Override
    public NESGame findByID(String id) {
        // result array
        JSONArray data = db.getJSONArray(ARRAY_KEY);

        // for all games
        for (int i = 0; i < data.length(); i++) {
            JSONObject json = data.getJSONObject(i);
            String jsonID = json.getString("catalogID");
            // if the id match
            if (jsonID.equals(id)) {
                // json to Game
                NESGame game = new NESGame();
                game.set(json);
                // return the game
                return game;
            }
        }

        // return a fake value 
        NESGame game = new NESGame();
        game.catalogID = "ERROR: Not found";
        return game;
    }

    @Override
    public void save(NESGame game) {
        // abort if id is incorrect
        if (game.catalogID.isBlank()) {
            return;
        }

        // get game array from json
        JSONArray data = db.getJSONArray(ARRAY_KEY);

        // check if ID already exist
        for (int i = 0; i < data.length(); i++) {
            JSONObject json = data.getJSONObject(i);
            String jsonID = json.getString("catalogID");
            // if so, abort
            if (jsonID.equals(game.catalogID)) {
                return;
            }
        }

        // save new game to the json file
        data.put(game.get());
        db.put(ARRAY_KEY, data);
        saveJSONFile(db);
    }

    @Override
    public void update(String id, NESGame game) {
        // abort if id is incorrect
        if (game.catalogID.isBlank()) {
            return;
        }

        // get game array from json
        JSONArray data = db.getJSONArray(ARRAY_KEY);

        // for all games
        for (int i = 0; i < data.length(); i++) {
            JSONObject json = data.getJSONObject(i);
            String jsonID = json.getString("catalogID");
            // if the id match
            if (jsonID.equals(game.catalogID)) {
                // save the new game to the json file
                data.put(i, game.get());
                db.put(ARRAY_KEY, data);
                saveJSONFile(db);
                break;
            }
        }
    }

    @Override
    public void delete(String id) {
        // abort if id is incorrect
        if (id.isBlank()) {
            return;
        }

        // get game array from json
        JSONArray data = db.getJSONArray(ARRAY_KEY);

        // for all games
        for (int i = 0; i < data.length(); i++) {
            JSONObject json = data.getJSONObject(i);
            String jsonID = json.getString("catalogID");
            // if the id match
            if (jsonID.equals(id)) {
                // remove the game from the json file
                data.remove(i);
                db.put(ARRAY_KEY, data);
                saveJSONFile(db);
                break;
            }
        }
    }
}
