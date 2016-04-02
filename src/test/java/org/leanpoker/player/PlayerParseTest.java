package org.leanpoker.player;

import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.DeckPlayer;
import org.leanpoker.player.model.GameState;

import static org.junit.Assert.assertEquals;

public class PlayerParseTest {

    @Test
    public void testBetRequest() throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(PlayerParseTest.class.getResourceAsStream("request.json"));

        JsonElement jsonElement = new JsonParser().parse(inputStreamReader);

        assertEquals(999, Player.betRequest(jsonElement));
    }

    @Test
    public void testParse() throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(PlayerParseTest.class.getResourceAsStream("full_request.json"));
        final Gson gson = new Gson();
        JsonElement jsonElement = new JsonParser().parse(inputStreamReader);
        GameState gameState = gson.fromJson(jsonElement, GameState.class);
        for (DeckPlayer player : gameState.getPlayers()){
            if(player.getHole_cards()!=null) {
                for (Card card : player.getHole_cards()) {
                    System.out.println(card.getRank());
                    System.out.println(card.getSuit());
                }
            }

        }

    }
}
