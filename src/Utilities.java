import java.util.List;

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
}
