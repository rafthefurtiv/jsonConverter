import java.util.Properties;

public class JsonConverterMain {

    public static void main(String[] args) {
        Properties prop = PropertiesUtil.getProp();

        FileExecutor fileExecutor = new FileExecutor();
        fileExecutor.execute(prop);
    }

}
