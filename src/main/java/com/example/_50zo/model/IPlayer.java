package com.example._50zo.model;

import java.util.ArrayList;

public interface IPlayer {
    void addCard(Card card);

    ArrayList<Card> getCardsPlayer();

    void removeCard(int index);

    boolean hasPlayableCard(int currentTotal);
}
