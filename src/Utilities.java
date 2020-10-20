import java.util.ArrayList;
import java.util.List;

public final class Utilities {
    /**
     *
     * @param object - takes an object to checks if its an object of class Integer
     * @return - true if the object is an instance of the class Integer
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
     *
     * @param numberList - an array of integers
     * @return - takes an array of integers and converts it into an array of cards
     */
    public static Card[] intToCardArray(int[] numberList) {
        Card[] cardList = new Card[numberList.length];
        for(int i = 0; i < numberList.length; i++) {
            cardList[i] = new Card(numberList[i]);
        }
        return cardList;
    }

    /**
     *
     * @param numberList - an array of integers
     * @return - takes an array of integers and converts it into an arrayList of cards.
     */
    public static ArrayList intArrToCardArrList(int[] numberList) {
        ArrayList<Card> cardList = new ArrayList<>();
        for(int i=0; i < numberList.length; i++) {
            Card temp = new Card(numberList[i]);
            cardList.add(temp);
        }
        return cardList;
    }

    /**
     *
     * @param list
     * @return
     */
    public static List[] splitList(List<String> list) {
        // find size of the list and put in size
        int size = list.size();

        List<String> first = new ArrayList<>(list.subList(0, (size) / 2));
        List<String> second = new ArrayList<>(list.subList((size) / 2, size));

        // return an List of array
        return new List[] { first, second };
    }

}
