package ru.job4j.serialization.json;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    private static Car fromJson(String json) {
        JSONObject jsonCar = new JSONObject(json);
        JSONObject jsonEngine = jsonCar.getJSONObject("engine");
        JSONArray jsonOptions = jsonCar.getJSONArray("options");
        String[] options = new String[jsonOptions.length()];
        for (int index = 0; index < jsonOptions.length(); index++) {
            options[index] = jsonOptions.getString(index);
        }
        Engine engine = new Engine(
                jsonEngine.getDouble("volume"),
                jsonEngine.getInt("horsePower")
        );
        return new Car(
                jsonCar.getBoolean("electric"),
                jsonCar.getInt("manufacturingYear"),
                jsonCar.getString("model"),
                engine,
                options
        );
    }

    public static void main(String[] args) {
        JSONObject jsonEngine = new JSONObject("{\"volume\":2.5,\"horsePower\":181}");
        List<String> list = new ArrayList<>();
        list.add("Climate control");
        list.add("Cruise control");
        JSONArray jsonOptions = new JSONArray(list);

        Car car = new Car(false, 2020, "Toyota Camry", new Engine(2.5, 181),
                "Climate control", "Cruise control");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("electric", car.isElectric());
        jsonObject.put("manufacturingYear", car.getManufacturingYear());
        jsonObject.put("model", car.getModel());
        jsonObject.put("engine", jsonEngine);
        jsonObject.put("options", jsonOptions);
        System.out.println(jsonObject);

        String json = new JSONObject(car).toString();
        System.out.println(json);
        System.out.println(fromJson(json));
    }
}
