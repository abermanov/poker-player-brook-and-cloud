package org.leanpoker.player;

import java.io.InputStream;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;

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
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.DeckPlayer;

public class PlayerTactic {
    static Collection<Card> getOurCards(Collection<DeckPlayer> deckPlayers) {
        for (DeckPlayer deckPlayer : deckPlayers) {
            if (deckPlayer.getName().equalsIgnoreCase(Player.PLAYER_NAME)) {
                return deckPlayer.getHole_cards();
            }
        }
        return Collections.emptyList();
    }

    static JsonArray getHoleCards(JsonObject request) {
        JsonArray players = request.get("players").getAsJsonArray();
        for (JsonElement player : players) {
            if (player.getAsJsonObject().get("name").getAsString().equalsIgnoreCase(Player.PLAYER_NAME)) {
                JsonElement hole_cards = player.getAsJsonObject().get("hole_cards");
                return hole_cards.getAsJsonArray();
            }
        }
        return new JsonArray();
    }

    static Integer getRank(Collection<Card> cards) {
        try {
            URI uri = new URIBuilder("http://rainman.leanpoker.org/rank")
                    .addParameter("cards", new Gson().toJson(cards)).build();
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
