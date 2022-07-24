package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solido {

    private Double[] origin;
    private Double width;
    private Double height;
    private Double length;

    public Solido(JSONObject jsonObject){

        JSONArray originJson = (JSONArray) ((JSONObject)((JSONArray) jsonObject.get("inputs")).get(0)).get("value");
        origin = new Double[3];
        origin[0] = (Double) originJson.get(0);
        origin[1] = (Double) originJson.get(1);
        origin[2] = (Double) originJson.get(2);

        width = (Double)((JSONObject)((JSONArray) jsonObject.get("inputs")).get(1)).get("value");
        height = (Double)((JSONObject)((JSONArray) jsonObject.get("inputs")).get(2)).get("value");
        length = (Double)((JSONObject)((JSONArray) jsonObject.get("inputs")).get(3)).get("value");

        jsonObject.get("inputs");
    }

    public Double[] getOrigin() {
        return origin;
    }

    public void setOrigin(Double[] origin) {
        this.origin = origin;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public void trasform(String origin, Double width, Double height, Double length){
        List<String> originString = Arrays.stream(origin.split(",")).collect(Collectors.toList());
        this.origin[0] = new Double(originString.get(0));
        this.origin[1] = new Double(originString.get(1));
        this.origin[2] = new Double(originString.get(2));
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public void toVideo() {

        System.out.println("--------------------------------------------------");
        System.out.println("Try with:");
        System.out.println(origin[0] + " - "+origin[1] + " - "+origin[2]);
        System.out.println(width);
        System.out.println(height);
        System.out.println(length);
        System.out.println("--------------------------------------------------");

    }
}
