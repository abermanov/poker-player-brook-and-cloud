package org.leanpoker.player;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;
import org.leanpoker.player.model.Card;

public class RankTest {
    @Test
    public void getRankTest() throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(PlayerParseTest.class.getResourceAsStream("holeCards.json"));
        JsonElement jsonElement = new JsonParser().parse(inputStreamReader);
        Type listType = new TypeToken<ArrayList<Card>>() {}.getType();
        List<Card> cards = new Gson().fromJson(jsonElement, listType);
        int rank = PlayerTactic.getRank(cards);
        System.out.println(rank);
        Assert.assertEquals(8, rank);
    }
}
