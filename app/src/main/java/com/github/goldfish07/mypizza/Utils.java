package com.github.goldfish07.mypizza;

import static com.github.goldfish07.mypizza.Constants.JSON_KEY_IS_VEG_SIZE;
import static com.github.goldfish07.mypizza.Constants.JSON_KEY_PIZZA_CRUST;
import static com.github.goldfish07.mypizza.Constants.JSON_KEY_PIZZA_NAME;
import static com.github.goldfish07.mypizza.Constants.JSON_KEY_PIZZA_PRICE;
import static com.github.goldfish07.mypizza.Constants.JSON_KEY_PIZZA_SIZE;

import android.content.Context;

import com.github.goldfish07.mypizza.model.MyPizza;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Utils {

    public static void saveJson(Context context, ArrayList<MyPizza> str) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < str.size(); i++) {
            JSONObject obj = new JSONObject();
            try {
                obj.put(JSON_KEY_PIZZA_NAME, str.get(i).getName());
                obj.put(JSON_KEY_PIZZA_CRUST, str.get(i).getCrust());
                obj.put(JSON_KEY_PIZZA_SIZE, str.get(i).getSize());
                obj.put(JSON_KEY_IS_VEG_SIZE, str.get(i).isVeg());
                obj.put(JSON_KEY_PIZZA_PRICE, str.get(i).getPrice());
                jsonArray.put(obj);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        context.getSharedPreferences("json", 0).edit().putString("json", jsonArray.toString()).apply();
    }

    public static void removeJsonServer(Context context) {
        context.getSharedPreferences("json", 0).edit().remove("json").apply();
    }

    public static ArrayList<MyPizza> getJsonServer(Context context) {
        String json = context.getSharedPreferences("json", 0).getString("json", "");
        ArrayList<MyPizza> server = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String pizzaName = obj.getString(JSON_KEY_PIZZA_NAME);
                String pizzaCrust = obj.getString(JSON_KEY_PIZZA_CRUST);
                String pizzaSize = obj.getString(JSON_KEY_PIZZA_SIZE);
                boolean pizzaIsVeg = obj.getBoolean(JSON_KEY_IS_VEG_SIZE);
                int pizzaPrice = obj.getInt(JSON_KEY_PIZZA_PRICE);

                server.add(new MyPizza(pizzaName, pizzaCrust, pizzaSize, pizzaIsVeg, pizzaPrice));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return server;
    }

    public static boolean isServerDBAvailable(Context context) {
        return !context.getSharedPreferences("json", 0).getString("json", "").isEmpty();
    }

}
