package cardgame;

public class Player implements Runnable {
    private final int playerNumber;
    private final CardHand hand;
    private final CardDeck deck;
    private final GameLogger logger;
    private final CardGame game;

    /**
     * Class constructor specifying the CardGame player is part of, the player's hand, player's deck and player number.
     *
     * @param game         CardGame this player is playing in
     * @param hand         starting CardHand of the player
     * @param deck         starting CardDeck of the player
     * @param playerNumber number of the player according to the list of players in the CardGame
     */
    public Player(CardGame game, CardHand hand, CardDeck deck, int playerNumber) {
        this.game = game;
        this.hand = hand;
        this.deck = deck;
        this.playerNumber = playerNumber;
        logger = new GameLogger();
        logger.writeToFile("player", playerNumber, "player " + playerNumber + " initial hand " + hand.getStringOfCardValues());
    }

    /**
     * While there is no winning player in the CardGame this player is part of, loop of instructions below occurs.
     * <p>
     * First checks if hand is a winning hand, if not then checks if the deck belonging to the player is empty where if
     * its empty then this thread waits to gets notified. If this player's deck isn't empty then player draws and
     * discards a card then notifies the next player so if that player thread is waiting then now it knows that
     * a card has been discarded into deck hence continue running the thread.
     * <p>
     * Once there is a winning player in the CardGame this player is part of, notifies next player so next player is
     * waiting, not it can continue running end its thread. Then the end method is called to write to log files and
     * inform who won.
     */
    public void run() {
        while (game.winningPlayer.get() == 0) {
            if (hand.isWinningHand()) {
                game.winningPlayer.set(playerNumber);
                if (GameLogger.printing) {
                    System.out.println("player " + playerNumber + " wins");
                }
            } else if (deck.isEmpty()) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                drawCard();
                discardCard();
                synchronized (game.getNextPlayer(this)) {
                    game.getNextPlayer(this).notify();
                }
                logger.writeToFile("player", playerNumber, "player " + playerNumber + " current hand is " + hand.getStringOfCardValues());
                // TODO: REMOVE THIS BEFORE SUBMIT
                logger.writeToFile("player", playerNumber, "---------------------------------");
            }
        }
        synchronized (game.getNextPlayer(this)) {
            game.getNextPlayer(this).notify();
        }
        end();
    }

    /**
     * Logs the current to deck file for this player.
     * If current player is the winning player than it logs into it's text file stating has it won, else it gets the
     * winning player and logs it in text file stating winning player has won.
     * Also logs the final hand of current player.
     */
    public void end() {
        logger.writeToFile("deck", playerNumber, "deck" + playerNumber + " contents: " + deck.getStringOfCardValues());
        if (game.winningPlayer.get() == playerNumber) {
            logger.writeToFile("player", playerNumber, "player " + playerNumber + " wins");
        } else {
            logger.writeToFile("player", playerNumber, "player " + game.winningPlayer.get() + " has informed player " + playerNumber + " that player " + game.winningPlayer.get() + " has won");
        }
        logger.writeToFile("player", playerNumber, "player " + playerNumber + " exits");
        logger.writeToFile("player", playerNumber, "player " + playerNumber + " final hand: " + hand.getStringOfCardValues());
    }

    /**
     * Draws a card from the player's deck.
     */
    public synchronized void drawCard() {
        Card c = deck.pop();
        logger.writeToFile("player", playerNumber, ("player " + playerNumber + " draws a " + c.getValue() + " from deck " + playerNumber));
        hand.addCard(c);
    }

    /**
     * Discards the given card from the player's hand and puts it at the bottom of the next player's deck.
     */
    public synchronized void discardCard() {
        Card c = hand.getDiscardingCard();
        game.getNextPlayer(this).getDeck().addCard(c);
        logger.writeToFile("player", playerNumber, ("player " + playerNumber + " discards a " + c.getValue() + " to deck " + game.getNextPlayer(this).getPlayerNumber()));
        hand.removeCard(c);
    }

    /**
     * Getter method for player hand.
     *
     * @return CardDeck object containing player hand
     */
    public CardHand getHand() {
        return hand;
    }

    /**
     * Getter method for player deck.
     *
     * @return CardDeck object containing player deck
     */
    public CardDeck getDeck() {
        return deck;
    }

    /**
     * Getter method for player number.
     *
     * @return the current player number
     */
    public int getPlayerNumber() {
        return playerNumber;
    }
}
