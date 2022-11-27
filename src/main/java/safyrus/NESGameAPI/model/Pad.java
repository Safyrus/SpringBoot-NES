package safyrus.NESGameAPI.model;

import org.json.JSONObject;

public class Pad {
    public boolean v;
    public boolean h;

    public void set(JSONObject json) {
        if (json == null)
            return;
        v = json.optBoolean("v");
        h = json.optBoolean("h");
    }

    public JSONObject get() {
        JSONObject json = new JSONObject();
        json.put("v", v);
        json.put("h", h);
        return json;
    }
}
