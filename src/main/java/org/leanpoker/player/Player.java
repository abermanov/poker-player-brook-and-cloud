package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player {

    static final String VERSION = "Version 1.0.1";

    public static int betRequest(JsonElement request) {
        JsonObject requestAJsonObject = request.getAsJsonObject();
        int current_buy_in = requestAJsonObject.get("current_buy_in").getAsInt();
        return current_buy_in;
    }

    public static void showdown(JsonElement game) {
    }
}
