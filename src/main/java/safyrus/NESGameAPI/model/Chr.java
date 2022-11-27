package safyrus.NESGameAPI.model;

import org.json.JSONObject;

public class Chr {
    public String name;
    public String size;
    public String sha1;
    public String crc;

    public void set(JSONObject json) {
        if (json == null)
            return;
        name = json.optString("name");
        size = json.optString("size");
        sha1 = json.optString("sha1");
        crc = json.optString("crc");
    }

    public JSONObject get() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("size", size);
        json.put("sha1", sha1);
        json.put("crc", crc);
        return json;
    }
}
