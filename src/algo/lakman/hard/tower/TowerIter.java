package algo.lakman.hard.tower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Builds a tower. O(N^2) time.
 * <p>
 * See Lakman p. 580
 */
public class TowerIter {

    public List<Human> longestIncreasingSeq(List<Human> array) {
        Collections.sort(array);

        List<ArrayList<Human>> solutions = new ArrayList<>();
        ArrayList<Human> bestSequence = null;

        /* Поиск самой длинной подпоследовательности, завершающейся на каждом элементе.*/
        for (int i = 0; i < array.size(); i++) {
            ArrayList<Human> longestAtIndex = bestSeqAtIndex(array, solutions, i);
            solutions.add(i, longestAtIndex);
            bestSequence = max(bestSequence, longestAtIndex);
        }

        return bestSequence;
    }


    /* Поиск самой длинной подпоследовательности. */
    private ArrayList<Human> bestSeqAtIndex(List<Human> array, List<ArrayList<Human>> solutions, int index) {
        Human value = array.get(index);

        ArrayList<Human> bestSequence = new ArrayList<>();
        /* Поиск самой длинной подпоследовательности, к которой можно присоединить элемент. */
        for (int i = 0; i < index; i++) {
            ArrayList<Human> solution = solutions.get(i);
            if (canAppend(solution, value)) {
                bestSequence = max(solution, bestSequence);
            }
        }

        /* Присоединение элемента. */
        ArrayList<Human> best = (ArrayList<Human>) bestSequence.clone();
        best.add(value);

        return best;
    }

    private boolean canAppend(List<Human> solution, Human value) {
        if (solution == null) return false;

        if (solution.size() == 0) return true;

        Human last = solution.get(solution.size() - 1);
        return last.isBefore(value);
    }

    private ArrayList<Human> max(ArrayList<Human> seq1, ArrayList<Human> seq2) {
        if (seq1 == null) {
            return seq2;
        } else if (seq2 == null) {
            return seq1;
        }
        return seq1.size() > seq2.size() ? seq1 : seq2;
    }
}
