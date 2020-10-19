import java.util.ArrayList;

public class Dealer {

    public Dealer(){}

    public static int[][] deal(){
        int numberOfPlayers = 2;
        int[] pack = {14, 15, 2, 3, 4, 5, 1, 3, 553, 52, 5, 25, 2, 5, 25, 25};
        int[][] result = new int[numberOfPlayers*2][4];

        for(int i = 0; i < numberOfPlayers; i++){
            int[] temp = new int[4];
            for(int j = 0; j < 4; j++){
                temp[j] = pack[i+(j*numberOfPlayers)];
            }
            result[i] = temp;
        }

        return result;
    }

    public static void main(String [] args){
        int[][] results = deal();
    }
}
