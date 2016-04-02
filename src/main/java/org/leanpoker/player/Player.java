package org.leanpoker.player;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.DeckPlayer;
import org.leanpoker.player.model.GameState;

public class Player {

    static final String VERSION = "Version 2.1.0";
    public static final String PLAYER_NAME = "brook and cloud";

    public static int betRequest(JsonElement request) {
        GameState gameState = new Gson().fromJson(request, GameState.class);
        Collection<Card> ourCards = PlayerTactic.getOurCards(gameState.getPlayers());
        Integer ourPreviousBet = 0;
        DeckPlayer ourPlayer = null;
        for (DeckPlayer deckPlayer : gameState.getPlayers()) {
            if (deckPlayer.getName().equalsIgnoreCase(PLAYER_NAME)) {
                ourPlayer = deckPlayer;
                ourPreviousBet = deckPlayer.getBet();
            }
        }
        int round = gameState.getRound();
        int call = gameState.getCurrent_buy_in() - ourPreviousBet;
        Integer rank = 0;
        switch (round){
            case 0:
                rank = PlayerTactic.getRank(ourCards);
                if (rank == 1) {
                    return ourPlayer.getStack();
                }
                if (gameState.getCurrent_buy_in() - ourPreviousBet < 6 * gameState.getSmall_blind()) {
                    return call;
                } else {
                    return 0;
                }
            default:
                Collection<Card> allCards = new ArrayList<>(ourCards);
                allCards.addAll(gameState.getCommunity_cards());
                rank = PlayerTactic.getRank(allCards);
                if (rank > round) {
                    return ourPlayer.getStack();
                }
                if (gameState.getCurrent_buy_in() - ourPreviousBet < 6 * gameState.getSmall_blind()) {
                    return call;
                } else {
                    return 0;
                }
        }
    }



    public static void showdown(JsonElement game) {
    }
}
