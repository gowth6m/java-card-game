public class Dealer {

    public Dealer(){}

    public static int[][][] deal(int[] pack, int numberOfPlayers){

        int[][][] result = new int[2][numberOfPlayers][4];
        int[] cards;

        for(int i = 0; i < numberOfPlayers; i++){
            cards = new int[4];
            for(int j = 0; j < 4; j++){
                cards[j] = pack[i+(j*numberOfPlayers)];
            }
            result[0][i] = cards;
        }

        for(int i = numberOfPlayers*4; i < numberOfPlayers*5; i++){
            cards = new int[4];
            for(int j = 0; j < 4; j++){
                cards[j] = pack[i+(j*numberOfPlayers)];
            }
            result[1][i - (numberOfPlayers*4)] = cards;
        }

        return result;

    }

}
