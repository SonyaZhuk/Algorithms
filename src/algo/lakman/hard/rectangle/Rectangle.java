package algo.lakman.hard.rectangle;

import algo.lakman.hard.wordslocations.Trie;

public class Rectangle {
    private int height, length;
    private char[][] matrix;

    public Rectangle(int length) {
        this.height = 0;
        this.length = length;
    }

    /* Построение прямоугольного массива букв с заданной длиной и высотой
       на основании заданной матрицы букв. (Предполагается, что длина
       и высота, переданные в аргументах, соответствуют размерам аргумента-массива.) */
    public Rectangle(int length, int height, char[][] letters) {
        this.height = letters.length;
        this.length = letters[0].length;
        matrix = letters;
    }

    public boolean isComplete(int l, int h, WordGroup groupList) {
        if (height == h) {
            for (int i = 0; i < l; i++) {
                String col = getColumn(i);
                if (!groupList.containsWord(col)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isPartialOK(int l, Trie trie) {
        if (height == 0) return true;
        for (int i = 0; i < l; i++) {
            String col = getColumn(i);
            if (!trie.contains(col)) {
                return false;
            }
        }
        return true;
    }

    public String getColumn(int i) {
        //TODO
        return "";
    }

    /* Создание нового объекта Rectangle из строк текущего прямоугольника с присоединением s. */
    public Rectangle append(String s) {
        //TODO
        return new Rectangle(s.length());
    }

    public char getLetter(int i, int j) {
        return matrix[i][j];
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }
}
