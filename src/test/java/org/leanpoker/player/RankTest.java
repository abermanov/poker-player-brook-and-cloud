package org.leanpoker.player;

import java.io.InputStreamReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ipergenitsa on 02.04.16.
 */
public class RankTest {
    @Test
    public void getRankTest() throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(PlayerParseTest.class.getResourceAsStream("holeCards.json"));
        JsonElement jsonElement = new JsonParser().parse(inputStreamReader);
        int rank = Player.getRank(jsonElement.getAsJsonArray());
        Assert.assertEquals(8, rank);
    }
}
