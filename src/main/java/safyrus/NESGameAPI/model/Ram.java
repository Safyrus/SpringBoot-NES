package safyrus.NESGameAPI.model;

import org.json.JSONObject;

public class Ram {
    public String size;
    public String id;

    public void set(JSONObject json) {
        if (json == null)
            return;
        size = json.optString("size");
        id = json.optString("id");
    }
    public JSONObject get() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("size", size);
        return json;
    }
}
