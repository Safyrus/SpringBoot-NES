package safyrus.NESGameAPI.model;

import org.json.JSONObject;

public class Cartridge {

    /**
     * 
     */
    public String system;

    /**
     * 
     */
    public String revision;

    /**
     * 
     */
    public String sha1;

    /**
     * 
     */
    public String crc;

    /**
     * 
     */
    public String dump;

    /**
     * 
     */
    public String dateDumped;

    /**
     * 
     */
    public String dumper;

    /**
     * 
     */
    public Board board;

    public Cartridge() {
        system = "";
        revision = "";
        sha1 = "";
        crc = "";
        dump = "";
        dateDumped = "";
        dumper = "";
        board = new Board();
    }

    public void set(JSONObject json) {
        if (json == null)
            return;
        system = json.optString("system");
        revision = json.optString("revision");
        sha1 = json.optString("sha1");
        crc = json.optString("crc");
        dump = json.optString("dump");
        dateDumped = json.optString("dateDumped");
        dumper = json.optString("dumper");
        board.set(json.optJSONObject("board"));
    }

    public JSONObject get() {
        JSONObject json = new JSONObject();
        json.put("system", system);
        json.put("revision", revision);
        json.put("sha1", sha1);
        json.put("crc", crc);
        json.put("dump", dump);
        json.put("dateDumped", dateDumped);
        json.put("dumper", dumper);
        json.put("board", board.get());
        return json;
    }
}
