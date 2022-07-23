
import java.util.Properties;

import model.Solido;
import org.json.simple.JSONObject;

public class FileExecutor {

    public void execute(Properties prop){
        String jsonPath = prop.getProperty("json.path");
        String escapeCondition = prop.getProperty("escapeCondition");
        Boolean goOn = true;

        try{
            JSONObject jsonObject = Utils.getJsonFromFile(jsonPath);
            jsonObject.putIfAbsent("iterazioni", 0);
            Solido solido = new Solido(jsonObject);

            while(goOn) {

                elaboraJson(solido);

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

        Integer interazioni = (Integer) jsonObject.get("iterazioni");
        interazioni++;
        jsonObject.put("iterazioni", interazioni);

        interazioni = (Integer) jsonObject.get("iterazioni");
        if(interazioni >= 10){
            return new Boolean(false);
        }

        return new Boolean(true);
    }

    private void elaboraJson(Solido solido) {
        // TODO IMPLEMENTARE MODIFICHE AL JSON


        solido.toVideo();
    }


}
