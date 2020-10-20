public class Dealer {

    public Dealer(){}

    public static int[][] deal(){

        int[] pack = {14, 15, 2, 3, 4, 5, 1, 3, 553, 52, 5, 25, 2, 5, 25, 25};
        int numberOfPlayers = 2;

        int[][] result = new int[numberOfPlayers*2][4];
        int[] cards;

        for(int i = 0; i < numberOfPlayers; i++){
            cards = new int[4];
            for(int j = 0; j < 4; j++){
                cards[j] = pack[i+(j*numberOfPlayers)];
            }
            result[i] = cards;
        }

        for(int i = numberOfPlayers*4; i < numberOfPlayers*5; i++){
            cards = new int[4];
            for(int j = 0; j < 4; j++){
                cards[j] = pack[i+(j*numberOfPlayers)];
            }
            result[i - (numberOfPlayers*3)] = cards;
        }

        return result;

    }

    public static void main(String [] args){
        deal();
    }

}
