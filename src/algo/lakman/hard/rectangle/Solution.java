package algo.lakman.hard.rectangle;

import algo.lakman.hard.wordslocations.Trie;

import java.util.ArrayList;

public class Solution {

    public Rectangle maxRectangle(String[] words) {
        WordGroup[] groupList = WordGroup.createWordGroups(words);
        int maxWordLength = groupList.length;
        Trie[] trieList = new Trie[maxWordLength];
        return maxRectangle(maxWordLength, groupList, trieList);
    }

    private Rectangle maxRectangle(int maxWordLength, WordGroup[] groupList, Trie[] trieList) {
        int maxSize = maxWordLength * maxWordLength;
        for (int z = maxSize; z > 0; z--) { // Начать с наибольшей площади
            for (int i = 1; i <= maxWordLength; i++) {
                if (z % i == 0) {
                    int j = z / i;
                    if (j <= maxWordLength) {
                        /*Создание прямоугольника с длинои i и высотой j (i*j z). */
                        Rectangle rectangle = makeRectangle(i, j, groupList, trieList);
                        if (rectangle != null) return rectangle;
                    }
                }
            }
        }
        return null;
    }

    private Rectangle makeRectangle(int length, int height, WordGroup[] groupList, Trie[] trieList) {
        if (groupList[length - 1] == null || groupList[height - 1] == null) return null;

        /* Создание нагруженного дерева для длины слова */
        if (trieList[height - 1] == null) {

            ArrayList<String> words = groupList[height - 1].getWords();
            trieList[height - 1] = new Trie(words);
        }
        return makePartialRectangle(length, height, new Rectangle(length), groupList, trieList);
    }


    private Rectangle makePartialRectangle(int l, int h, Rectangle rectangle, WordGroup[] groupList, Trie[] trieList) {
        if (rectangle.getHeight() == h) { // Является ли полным прямоугольником?
            if (rectangle.isComplete(l, h, groupList[h - 1])) {
                return rectangle;
            }
            return null;
        }

        /* Сравнение для проверки потенциально действительного прямоугольника */
        if (!rectangle.isPartialOK(l, trieList[h - 1])) {
            return null;
        }

         /* Перебор всех слов подходящей длины. После добавления каждого
            потенциального слова пытаемся рекурсивно построить прямоугольник. */
        for (int i = 0; i < groupList[l - 1].length(); i++) {
            /* Создание нового прямоугольника с новым словом. */
            Rectangle orgPlus = rectangle.append(groupList[l - 1].getWord(i));

            /* Попытка построения прямоугольника на базе нового неполного */
            Rectangle rect = makePartialRectangle(l, h, orgPlus, groupList, trieList);
            if (rect != null) {
                return rect;
            }
        }
        return null;
    }
}
