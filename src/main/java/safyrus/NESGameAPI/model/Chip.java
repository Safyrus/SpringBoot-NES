package safyrus.NESGameAPI.model;

import org.json.JSONObject;

public class Chip {
    public String type;

    public void set(JSONObject json) {
        if (json == null)
            return;
        type = json.optString("type");
    }

    public JSONObject get() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        return json;
    }
}
