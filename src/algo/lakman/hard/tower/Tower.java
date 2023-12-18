package algo.lakman.hard.tower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Builds a tower. O(N^2) time.
 * <p>
 * See Lakman p. 580
 */
public class Tower {

    //TODO
    public List<Human> longestIncreasingSeq(List<Human> items) {
        Collections.sort(items, new HumanComparator());
        List<Human> res = new ArrayList<>();
        int index = 0;

        for (int i = 0; i < items.size() - 1; i++) {
            Human h1 = items.get(index);
            Human h2 = items.get(i + 1);
            if (h1.getWeight() <= h2.getWeight()) {
                res.add(h1);
                index++;
            } else {
                res.add(h1);
                index = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //(65, 100) (70, 150) (56, 90) (75, 190) (60, 95) (68, 110)

        ArrayList<Human> humans = new ArrayList<>() {{
            add(new Human(65, 100));
            add(new Human(70, 150));
            add(new Human(56, 90));
            add(new Human(75, 190));
            add(new Human(60, 95));
            add(new Human(68, 110));
        }};

        Tower tower = new Tower();
        var t = tower.longestIncreasingSeq(humans);
        System.out.println();
    }
}
