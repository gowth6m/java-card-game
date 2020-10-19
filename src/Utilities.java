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

}
