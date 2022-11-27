package safyrus.NESGameAPI.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * NESGame
 */
public class NESGame {

    /**
     * 
     */
    public String name;

    /**
     * 
     */
    public String altName;

    /**
     * 
     */
    public int players;

    /**
     * 
     */
    public String developer;

    /**
     * 
     */
    public String publisher;

    /**
     * 
     */
    public String date;

    /**
     * 
     */
    public String region;

    /**
     * 
     */
    public String catalogID;

    /**
     * 
     */
    public Boolean licensed;

    /**
     * 
     */
    public String subclasse;

    /**
     * 
     */
    public List<Cartridge> cartridge;

    /**
     * 
     */
    public NESGame() {
        this.name = "";
        this.altName = "";
        this.players = 0;
        this.developer = "";
        this.publisher = "";
        this.date = "";
        this.region = "";
        this.catalogID = "";
        this.licensed = false;
        this.subclasse = "";
        this.cartridge = new ArrayList<>();
    }

    public void set(JSONObject json) {
        if (json == null)
            return;
        name = json.optString("name");
        altName = json.optString("altName");
        players = json.optInt("players");
        developer = json.optString("developer");
        publisher = json.optString("publisher");
        date = json.optString("date");
        region = json.optString("region");
        catalogID = json.optString("catalogID");
        licensed = json.optBoolean("licensed");
        subclasse = json.optString("subclasse");
        cartridge = new ArrayList<>();
        JSONArray array = json.optJSONArray("cartridge");
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                Cartridge c = new Cartridge();
                c.set(array.getJSONObject(i));
                cartridge.add(c);
            }
        }
    }

    public JSONObject get() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("altName", altName);
        json.put("players", players);
        json.put("developer", developer);
        json.put("publisher", publisher);
        json.put("date", date);
        json.put("region", region);
        json.put("catalogID", catalogID);
        json.put("licensed", licensed);
        json.put("subclasse", subclasse);
        JSONArray array = new JSONArray();
        for (Cartridge c : cartridge) {
            array.put(c.get());
        }
        json.put("cartridge", array);
        return json;
    }
}