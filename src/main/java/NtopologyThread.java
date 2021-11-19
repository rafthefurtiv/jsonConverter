import org.json.simple.JSONObject;

public class NtopologyThread extends Thread{

    JSONObject jsonObject;

    public NtopologyThread (JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {
        super.run();

        try {
            // TODO IMPLEMENTAZIONE CHIAMATA AD NTOPOLOGY

            sleep(500);
            System.out.println("Chiamata esterna terminata");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
