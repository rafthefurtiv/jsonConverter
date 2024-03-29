import model.Solido;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    public static JSONObject trasformaFile(JSONObject jsonObject, Solido solido) {
        ((JSONObject)((JSONArray) jsonObject.get("inputs")).get(0)).put("value", solido.getOrigin());
        ((JSONObject)((JSONArray) jsonObject.get("inputs")).get(1)).put("value", solido.getWidth());
        ((JSONObject)((JSONArray) jsonObject.get("inputs")).get(2)).put("value", solido.getHeight());
        ((JSONObject)((JSONArray) jsonObject.get("inputs")).get(3)).put("value", solido.getLength());

        return jsonObject;
    }

    public static void salvaFile(JSONObject jsonObject, String name) {
        try (FileWriter file = new FileWriter(name)) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static JSONObject getJsonFromFile(String path) {
        File file = new File(path);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public static JSONArray getJsonArrayFromFile(String path) {
        File file = new File(path);
        JSONParser parser = new JSONParser();
        JSONArray jsonObject = null;
        try {
            jsonObject = (JSONArray) parser.parse(new FileReader(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


}
