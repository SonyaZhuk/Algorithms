package algo.lakman.hard.rectangle;

import java.util.ArrayList;
import java.util.HashMap;

public class WordGroup {
    private HashMap<String, Boolean> lookup = new HashMap<>();
    private ArrayList<String> group = new ArrayList<>();

    public boolean containsWord(String s) {
        return lookup.containsKey(s);
    }

    public int length() {
        return group.size();
    }

    public String getWord(int i) {
        return group.get(i);
    }

    public ArrayList<String> getWords() {
        return group;
    }

    public void addWord(String s) {
        group.add(s);
        lookup.put(s, true);
    }

    public static WordGroup[] createWordGroups(String[] list) {
        WordGroup[] groupList;
        int maxWordLength = 0;
        /* Определение длины самого длинного слова */
        for (int i = 0; i < list.length; i++) {
            if (list[i].length() > maxWordLength) {
                maxWordLength = list[i].length();
            }
        }
        /* Группировка слов из словаря в списки слов с одинаковой длиной.
         В groupList[i] хранится список слов, имеющих длину (i + 1). */
        groupList = new WordGroup[maxWordLength];
        for (int i = 0; i < list.length; i++) {
            /* We do wordLength - 1 instead of just wordLength since this is used as
               аn index аnd nо words are of length 0 */
            int wordLength = list[i].length() - 1;
            if (groupList[wordLength] == null) {
                groupList[wordLength] = new WordGroup();
            }
            groupList[wordLength].addWord(list[i]);
        }
        return groupList;
    }
}

