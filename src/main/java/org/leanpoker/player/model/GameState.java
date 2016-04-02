package org.leanpoker.player.model;

import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.DeckPlayers;

import java.util.List;

/**
 * Created by adippel on 02.04.2016.
 */
public class GameState {
    String tournament_id;
    String game_id;
    int round;
    int bet_index;
    int small_blind;
    int current_buy_in;
    int pot;
    int minimum_raise;
    int dealer;
    int orbits;
    int in_action;
    List<DeckPlayers> players;
    List<Card> cards;
}
