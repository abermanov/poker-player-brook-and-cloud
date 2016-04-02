package org.leanpoker.player;

import java.io.InputStream;
import java.net.URI;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.leanpoker.player.model.DeckPlayer;
import org.leanpoker.player.model.GameState;

public class Player {

    static final String VERSION = "Version 2.0.1";
    private static final String PLAYER_NAME = "brook and cloud";

    public static int betRequest(JsonElement request) {
        GameState gameState = new Gson().fromJson(request, GameState.class);
        JsonObject requestAJsonObject = request.getAsJsonObject();
        int current_buy_in = requestAJsonObject.get("current_buy_in").getAsInt();
        JsonArray holeCards = getHoleCards(requestAJsonObject);
        Integer ourPreviousBet = 0;
        for (DeckPlayer deckPlayer : gameState.getPlayers()) {
            if (deckPlayer.getName().equalsIgnoreCase(PLAYER_NAME)) {
                ourPreviousBet = deckPlayer.getBet();
            }
        }
        Integer rank = getRank(holeCards.getAsJsonArray());
        if (rank > 0) {
            return current_buy_in - ourPreviousBet;
        } else {
            return 0;
        }
    }

    public static void showdown(JsonElement game) {
    }

    static JsonArray getHoleCards(JsonObject request) {
        JsonArray players = request.get("players").getAsJsonArray();
        for (JsonElement player : players) {
            if (player.getAsJsonObject().get("name").getAsString().equalsIgnoreCase(PLAYER_NAME)) {
                JsonElement hole_cards = player.getAsJsonObject().get("hole_cards");
                return hole_cards.getAsJsonArray();
            }
        }
        return new JsonArray();
    }

    static Integer getRank(JsonArray holeCards) {
        try {
            URI uri = new URIBuilder("http://rainman.leanpoker.org/rank")
                    .addParameter("cards", holeCards.toString()).build();
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(uri);
            HttpResponse execute = client.execute(request);
            InputStream content = execute.getEntity().getContent();
            String s = IOUtils.toString(content);
            JsonElement parse = new JsonParser().parse(s);
            int rank = parse.getAsJsonObject().get("rank").getAsInt();
            return rank;
        } catch (Exception e) {
            return 0;
        }
    }
}
