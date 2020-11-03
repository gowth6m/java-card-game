import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Utilities {
    /**
     * Checks if the object is an integer.
     * @param object takes an object to checks if its an object of class Integer
     * @return true if the object is an instance of the class Integer
     */
    public static boolean isInteger(Object object) {
        if(object instanceof Integer) {
            return true;
        } else {
            String string = object.toString();

            try {
                Integer.parseInt(string);
            } catch(Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Takes an array of integers and converts it into an array of cards.
     * @param numberList - an array of integers
     * @return - array of cards
     */
    public static Card[] intToCardArray(int[] numberList) {
        Card[] cardList = new Card[numberList.length];
        for(int i = 0; i < numberList.length; i++) {
            cardList[i] = new Card(numberList[i]);
        }
        return cardList;
    }

    /**
     * Takes an array of integers and converts it into an arrayList of cards.
     * @param numberList - an array of integers
     * @return - arrayList of cards.
     */
    public static ArrayList<Card> intArrToCardArrList(int[] numberList) {
        ArrayList<Card> cardList = new ArrayList<>();
        for (int j : numberList) {
            Card temp = new Card(j);
            cardList.add(temp);
        }
        return cardList;
    }

    /**
     * Takes in a list of Integer and returns an array of int
     *
     * @param integerList - takes in a list of integer
     * @return an array of int
     */
    public static int[] intArrToIntList(List<Integer> integerList) {
        int size = integerList.size();
        int[] intArray = new int[size];
        Integer[] temp = integerList.toArray(new Integer[size]);
        for (int n = 0; n < size; ++n) {
            intArray[n] = temp[n];
        }
        return intArray;
    }

    /**
     * Gives a random number from a range excluding the given list of numbers.
     * @param start start of range (inclusive)
     * @param end end of range (exclusive)
     * @param excludes numbers to exclude (numbers you do not want)
     * @return the random number within start-end but not one of excludes
     */
    public static int nextIntInRangeButExclude(int start, int end, int... excludes){
        int rangeLength = end - start - excludes.length;
        Random r = new Random();
        int randomInt = r.nextInt(rangeLength) + start;
        for (int exclude : excludes) {
            if (exclude > randomInt) {
                return randomInt;
            }
            randomInt++;
        }
        return randomInt;
    }

    public static String formatCardValues(CardDeck deck){
        return deck.getListOfCardValues().toString()
                .replace(",", " ")
                .replace("[", "")
                .replace("]", "");
    }
}
