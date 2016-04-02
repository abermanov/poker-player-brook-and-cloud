package org.leanpoker.player;

import java.io.InputStreamReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerParseTest {

    @Test
    public void testBetRequest() throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(PlayerParseTest.class.getResourceAsStream("request.json"));

        JsonElement jsonElement = new JsonParser().parse(inputStreamReader);

        assertEquals(999, Player.betRequest(jsonElement));
    }
}
