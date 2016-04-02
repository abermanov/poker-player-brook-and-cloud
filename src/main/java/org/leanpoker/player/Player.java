package org.leanpoker.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.DeckPlayer;
import org.leanpoker.player.model.GameState;

public class Player {

    static final String VERSION = "Version 3.0.3";
    public static final String PLAYER_NAME = "brook and cloud";

    public static int betRequest(JsonElement request) {
        GameState gameState = new Gson().fromJson(request, GameState.class);
        List<Card> ourCards = PlayerTactic.getOurCards(gameState.getPlayers());
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
        switch (round) {
            case 0:
                if (ourCards.get(0).getRank().equalsIgnoreCase(ourCards.get(1).getRank())) {
                    return ourPlayer.getStack();
                }
                if (gameState.getCurrent_buy_in() - ourPreviousBet < 6 * gameState.getSmall_blind()) {
                    return (ourPlayer.getStack() > call) ? call : ourPlayer.getStack();
                } else {
                    return 0;
                }
            default:
                Collection<Card> allCards = new ArrayList<>(ourCards);
                allCards.addAll(gameState.getCommunity_cards());
                rank = PlayerTactic.getRank(allCards);
                // all-in if excellent cards
                if (rank > 2) {
                    return ourPlayer.getStack();
                }
                // if someone made a raise
                if (gameState.getCurrent_buy_in() - ourPreviousBet > 0) {
                    // we call if good cards or not big raise
                    if (gameState.getCurrent_buy_in() - ourPreviousBet < 4 * gameState.getSmall_blind() ||
                            rank > 1) {
                        return (ourPlayer.getStack() > call) ? call : ourPlayer.getStack();
                    }
                } else {
                    // nobody made a raise
                    if (rank > 1) {
                        return (ourPlayer.getStack() > 4 * gameState.getSmall_blind()) ? 4 * gameState.getSmall_blind() : ourPlayer.getStack();
                    }
                }
                return 0;
        }
    }



    public static void showdown(JsonElement game) {
    }
}
