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
}
