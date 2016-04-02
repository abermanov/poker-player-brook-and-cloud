package org.leanpoker.player;

import java.io.InputStreamReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

public class Round_0_Test {
    @Test
    public void testBet() throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(PlayerParseTest.class.getResourceAsStream("round_0.json"));
        JsonElement jsonElement = new JsonParser().parse(inputStreamReader);
        int bet = Player.betRequest(jsonElement);
        Assert.assertEquals(1000, bet);
    }
}
