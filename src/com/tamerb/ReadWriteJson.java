package com.tamerb;


import javax.json.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.stream.Collectors;


public class ReadWriteJson {

    // json -->  yazdırma methodu
    private static void jsonYaz() {
        // https://docs.oracle.com/javaee/7/api/javax/json/JsonObject.html
        JsonObject value = Json.createObjectBuilder()
                .add("firstName", "Tamer")
                .add("lastName", "Bilici")
                .add("age", 23)
                .add("address", Json.createObjectBuilder()
                        .add("streetAddress", "21/10 Sk.")
                        .add("city", "İzmir")
                        .add("postalCode", "35000"))
                .add("phoneNumber", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("type", "ev")
                                .add("number", "212 555-1234"))
                        .add(Json.createObjectBuilder()
                                .add("type", "cep")
                                .add("number", "531 555 4567")))
                .build();

        try (FileWriter fileWriter = new FileWriter("users.json")) {
            Json.createWriter(fileWriter).write(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //json -->  okuma işlemi methodu
    private static void jsonOku() {
        try (JsonReader reader = Json.createReader(new FileReader("users.json"))) {
            JsonObject jsonObject = reader.readObject();
            String jsonContent = jsonObject.keySet().stream()
                    .map(key -> "\"" + key + "\": " + jsonObject.get(key))
                    .collect(Collectors.joining(", ", "{", "}"));
            System.out.println("Json : " + "\n" + jsonContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        jsonYaz();
        jsonOku();
    }
}

