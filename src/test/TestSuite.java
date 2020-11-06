package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({TestCardDeck.class,
                     TestPlayer.class,
                     TestDealer.class,
                     TestCardHand.class,
                     TestCardGame.class,
                     TestMultithreading.class})

public class TestSuite {
    // Run all tests here
}