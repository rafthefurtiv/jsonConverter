
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import model.Solido;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FileExecutor {

    int i = 0;
    String jsonPath;
    String jsonOutPath;

    public void execute(Properties prop){
        jsonPath = prop.getProperty("json.path");
        jsonOutPath = prop.getProperty("jsonOut.path");
        String escapeCondition = prop.getProperty("escapeCondition");
        List<String> originArray = Arrays.stream(prop.getProperty("origin").split("&")).collect(Collectors.toList());
        List<String> widthArray = Arrays.stream(prop.getProperty("width").split(",")).collect(Collectors.toList());
        List<String> heightArray = Arrays.stream(prop.getProperty("height").split(",")).collect(Collectors.toList());
        List<String> lengthArray = Arrays.stream(prop.getProperty("length").split(",")).collect(Collectors.toList());
        Boolean goOn = true;


        try{
            JSONObject jsonObject = Utils.getJsonFromFile(jsonPath);
            jsonObject.putIfAbsent("iterazioni", 0);
            Solido solido = new Solido(jsonObject);
            boolean primaEsec = true;

            while(goOn) {

                if(primaEsec){
                    primaEsec = false;
                }
                else {
                    elaboraJson(solido, originArray, widthArray, heightArray, lengthArray);
                }
                i++;
                ntopologyCall(jsonObject);

                goOn = checkIfOk(jsonObject);
            }

            Utils.salvaFile(jsonObject,"filename_output.json");

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
        NtopologyThread ntopologyThread = new NtopologyThread(jsonObject);
        try {
            ntopologyThread.start();
            ntopologyThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Boolean checkIfOk(JSONObject jsonObject) {
        // TODO IMPLEMENTARE CRITERIO DI STOP

        jsonObject.putIfAbsent("iterazioni", 0);

        Integer iterazioni = (Integer) jsonObject.get("iterazioni");
        iterazioni++;
        jsonObject.put("iterazioni", iterazioni);

        iterazioni = (Integer) jsonObject.get("iterazioni");
        if(iterazioni >= 10){
            return new Boolean(false);
        }
        if(i >= 8){
            return new Boolean(false);
        }


        Utils.getJsonArrayFromFile(jsonOutPath);
        Double result = new Double(((JSONObject)((JSONObject)Utils.getJsonArrayFromFile(jsonOutPath).get(0)).get("value")).get("val").toString());

        if(result < 10){ // TODO condizione di esempio
            return new Boolean(false);
        }


        return new Boolean(true);
    }

    private void elaboraJson(Solido solido, List<String> origin, List<String> widthArray, List<String> heightArray, List<String> lengthArray) {
        // TODO IMPLEMENTARE MODIFICHE AL JSON

        solido.trasform(origin.get(i), new Double(widthArray.get(i)), new Double(heightArray.get(i)), new Double(lengthArray.get(i)));

        solido.toVideo();
    }


}
