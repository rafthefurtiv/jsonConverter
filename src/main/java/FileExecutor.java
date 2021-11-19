
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FileExecutor {

    public void execute(Properties prop){
        String jsonPath = prop.getProperty("json.path");
        Boolean goOn = true;
        try{
            File file = new File(jsonPath);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file.getAbsolutePath()));

            jsonObject.putIfAbsent("iterazioni", 0);

            while(goOn) {
                elaboraJson(jsonObject);

                ntopologyCall(jsonObject);

                goOn = checkIfOk(jsonObject);
            }

            salvaFile(jsonObject);

        }
        catch (NullPointerException npe){
            npe.printStackTrace();
            System.out.println("Path non specificato.");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void ntopologyCall(JSONObject jsonObject) {
        // TODO CHIAMARE PROGRAMMA ESTERNO
    }

    private void salvaFile(JSONObject jsonObject) {
        try (FileWriter file = new FileWriter("filename_output.json")) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean checkIfOk(JSONObject jsonObject) {
        // TODO IMPLEMENTARE CRITERIO DI STOP

        jsonObject.putIfAbsent("iterazioni", 0);

        Integer interazioni = (Integer) jsonObject.get("iterazioni");
        interazioni++;
        jsonObject.put("iterazioni", interazioni);

        interazioni = (Integer) jsonObject.get("iterazioni");
        if(interazioni >= 10){
            return new Boolean(false);
        }

        return new Boolean(true);
    }

    private void elaboraJson(JSONObject jsonObject) {
        // TODO IMPLEMENTARE MODIFICHE AL JSON
        System.out.println(jsonObject.toJSONString());
    }


}
