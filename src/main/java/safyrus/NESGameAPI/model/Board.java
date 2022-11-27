package safyrus.NESGameAPI.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Board {
    public String type;
    public int mapper;
    public String pcb;
    public Chip cic;
    public Pad pad;
    public List<Prg> prg;
    public List<Chr> chr;
    public List<Chip> chip;
    public List<Ram> vram;

    public Board() {
        type = "";
        mapper = 0;
        pcb = "";
        cic = new Chip();
        pad = new Pad();
        prg = new ArrayList<>();
        chr = new ArrayList<>();
        chip = new ArrayList<>();
        vram = new ArrayList<>();
    }

    public void set(JSONObject json) {
        if (json == null)
            return;
        type = json.optString("type");
        mapper = json.optInt("mapper");
        pcb = json.optString("pcb");
        cic.set(json.optJSONObject("cic"));
        pad.set(json.optJSONObject("pad"));

        // prg
        JSONArray array = json.optJSONArray("prg");
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                Prg p = new Prg();
                p.set(array.getJSONObject(i));
                prg.add(p);
            }
        }

        // chr
        array = json.optJSONArray("chr");
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                Chr c = new Chr();
                c.set(array.getJSONObject(i));
                chr.add(c);
            }
        }

        // chip
        array = json.optJSONArray("chip");
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                Chip c = new Chip();
                c.set(array.getJSONObject(i));
                chip.add(c);
            }
        }

        // vram
        array = json.optJSONArray("vram");
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                Ram r = new Ram();
                r.set(array.getJSONObject(i));
                vram.add(r);
            }
        }
    }

    public Object get() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("mapper", mapper);
        json.put("pcb", pcb);
        json.put("cic", cic.get());
        json.put("pad", pad.get());

        // prg
        JSONArray array = new JSONArray();
        for (Prg p : prg) {
            array.put(p.get());
        }
        json.put("prg", array);

        // chr
        array = new JSONArray();
        for (Chr c : chr) {
            array.put(c.get());
        }
        json.put("chr", array);

        // chip
        array = new JSONArray();
        for (Chip c : chip) {
            array.put(c.get());
        }
        json.put("chip", array);

        // vram
        array = new JSONArray();
        for (Ram r : vram) {
            array.put(r.get());
        }
        json.put("vram", array);

        return json;
    }
}
