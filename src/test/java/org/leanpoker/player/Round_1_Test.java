package org.leanpoker.player;

import java.io.InputStreamReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;

public class Round_1_Test {
    @Test
    public void testBet() throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(PlayerParseTest.class.getResourceAsStream("round_1.json"));
        JsonElement jsonElement = new JsonParser().parse(inputStreamReader);
        int i = Player.betRequest(jsonElement);
        System.out.println(i);
    }
}
