import java.util.ArrayList;

import java.util.List;

public class TestThing {
    private List<Integer> hands = new ArrayList<>();

    public List<Integer> getHands() {
        return hands;
    }

    public void removingMethod(int number, List<Integer> l) {
        for (int i=0; i < l.size(); i++) {
            if (l.get(i) == number) {
                l.remove(i);
            }
        }
    }

}
class Main {
    public static void main(String[] args) {
        // you get this from the input pack for the player hand
        int[] arr = { 1, 2, 3, 4, 5 };
        TestThing example = new TestThing();
        // you are these numbers to hand list
        for (int i : arr) {
            example.getHands().add(Integer.valueOf(i));
        }
        System.out.println(example.getHands());
        example.removingMethod(5 ,example.getHands());
        System.out.println(example.getHands());
    }
}


