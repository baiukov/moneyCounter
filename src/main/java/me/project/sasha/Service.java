package me.project.sasha;

import org.json.JSONArray;
import org.json.JSONObject;

import static me.project.sasha.HelloApplication.webEngine;

public class Service {

    public static Storage storage = new Storage();

    private static Service instance;

    private Service() {}

    public static Service getInstance() {
        if (instance != null) return instance;
        instance = new Service();
        return instance;
    }

    public void add(String[] params) {

        if (params.length < 3) return;

        String command = params[0];
        String id = params[1];
        String time = params[2];

        String existingData = storage.getData("tasks");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("time", time);

        JSONArray array;
        if (existingData.equals("default")) {
            array = new JSONArray();
        } else {
            array = new JSONArray(storage.getData("tasks"));
        }
        array.put(jsonObject);

        storage.saveData("tasks", array.toString());
    }

    public void getTasks() {
//        storage.clear();
        String data = storage.getData("tasks");
        if ("default".equals(data)) return;
        JSONArray array = new JSONArray(data);

        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            String id = jsonObject.getString("id");
            String time = jsonObject.getString("time");
            String script = String.format("addTask('%s', '%s')", id, time);

            webEngine.executeScript(script);
        }

    }

    public void close() {
        System.exit(0);
    }
}
