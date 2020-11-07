package com.cardgame;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestCardDeck.class,
                     TestPlayer.class,
                     TestDealer.class,
                     TestCardHand.class,
                     TestCardGame.class,
                     TestGameLogger.class,
                     TestMultithreading.class})

public class TestSuite {
    // Runs all tests here
}