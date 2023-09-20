package algo.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * String tasks.
 * <p>
 * See Lakman p. 197
 */
public class Strings {

    //1.1, p 198 O(n^2), O(1) memory
    public boolean isUnique(String s) {
        //for ASCII
        if (s.length() > 128) return false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            for (int j = 0; j < s.length(); j++) {
                if (i != j && ch == s.charAt(j)) return false;
            }
        }
        return true;
    }

    //1.1, p 198 O(n) -> O(1), O(1) memory
    public boolean isUniqueChars(String str) {
        if (str.length() > 128) return false;

        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) return false;
            char_set[val] = true;
        }
        return true;
    }

    //    ----------------------------------------------------------
    //1.2 p 200
    public boolean isPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        return sortString(s1).equals(sortString(s2));
    }

    private  String sortString(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);
    }

//    ----------------------------------------------------------

    // 1.3 p 200
    public String replaceSpaces(String s) {
        String s1 = s.replaceAll("\\s", "20%");
        return s.length() > s1.length() ? s1 : s;
    }

    // 1.3 p 200
    public String replaceSpaces1(String s) {
        StringBuilder builder = new StringBuilder(s);
        for (int i = builder.length() - 1; i >= 0; i--) {
            char ch = builder.charAt(i);
            if (ch == ' ') builder.replace(i, i + 1, "20%");
        }
        return builder.toString();
    }

    // 1.3 p 200
    public String replaceSpaces2(String s) {
        char[] arr = s.toCharArray();
        int k = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') k++;
        }
        if (k == 0) return s;


        int newLength = arr.length + k * 2;
        char[] arr1 = new char[newLength];

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == ' ') {
                arr1[newLength - 1] = '0';
                arr1[newLength - 2] = '2';
                arr1[newLength - 3] = '%';
                newLength = newLength - 3;
            } else {
                arr1[newLength - 1] = arr[i];
                newLength = newLength - 1;
            }
        }
        return String.valueOf(arr1);
    }

//    ----------------------------------------------------------

    // 1.4 p 202 O(n)
    public boolean isPalindrom(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) map.put(ch, map.get(ch) + 1);
            map.putIfAbsent(ch, 1);
        }
        long count = map.values().stream().filter(i -> i % 2 != 0).count();
        return count <= 1;
    }
//    ----------------------------------------------------------

    // 1.5 p 206 O(n)
    public boolean isModified(String s1, String s2) {
        if (s1.length() == s2.length()) {
            return oneEditReplace(s1, s2);
        } else if (s1.length() == s2.length() + 1) {
            return oneEditInsert(s1, s2);
        } else if (s1.length() == s2.length() - 1) {
            return oneEditInsert(s2, s1);
        }
        return false;
    }

    private boolean oneEditReplace(String s1, String s2) {
        boolean flag = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (!flag) flag = true;
                else return false;
            }
        }
        return flag;
    }

    private boolean oneEditInsert(String s1, String s2) {
        boolean flag = false;
        for (int i = 0; i < s2.length(); i++) {
            if (!flag && s2.charAt(i) != s1.charAt(i)) {
                flag = true;
                if (s2.charAt(i) != s1.charAt(i + 1)) return false;
            } else if (flag && s2.charAt(i) != s1.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

//    private static boolean oneEditInsert1(String s1, String s2) {
//        int index1 = 0;
//        int index2 = 0;
//        while (index2 < s2.length() && index1 < s1.length()) {
//            if (s1.charAt(index1) != s2.charAt(index2)) {
//                if (index1 != index2) return false;
//                index2++;
//            } else {
//                index1++;
//                index2++;
//            }
//        }
//        return true;
//    }

//    ----------------------------------------------------------

    // 1.6 p 208 StringBuilder удваивает capacity, можно (нужно) задавать ему емкость при инициализации
    public String compressedString(String s) {
        int count = 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) count++;
            else {
                builder.append(count).append(s.charAt(i - 1));
                count = 1;
            }
            if (i == s.length() - 1) builder.append(count).append(s.charAt(i));
        }
        return builder.toString();
    }

    //    ----------------------------------------------------------

    //p 210, O(N^2)
    public int[][] rotate(int[][] matrix, int n) {
        for (int layer = 0; layer < n / 2; ++layer) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; ++i) {
                int offset = i - first;
                // Сохранить верхнюю сторону
                int top = matrix[first][i];

                //левая сторона -> верхняя сторона
                matrix[first][i] = matrix[last - offset][first];
                // нижняя сторона ->левая сторона
                matrix[last - offset][first] = matrix[last][last - offset];
                // правая сторона -> нижняя сторона
                matrix[last][last - offset] = matrix[i][last];
                // верхняя сторона -> правая сторона
                matrix[i][last] = top;
            }
        }
        return matrix;
    }

//    ----------------------------------------------------------
    //p 211, O(N)
    public int[][] setZeros(int[][] matrix) {
        boolean[] rows = new boolean[matrix.length];
        boolean[] columns = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }

        for (int i = 0; i < rows.length; i++) {
            if (rows[i]) setRowNull(matrix, i);
        }

        for (int i = 0; i < columns.length; i++) {
            if (columns[i]) setColumnNull(matrix, i);
        }

        return matrix;
    }
    private void setRowNull(int[][] matrix, int row) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] = 0;
        }
    }
    private static void setColumnNull(int[][] matrix, int column) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][column] = 0;
        }
    }
//    ----------------------------------------------------------
    //p 214, O(N)
    public boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length() || s1.length() == 0) return false;
        return ((s1 + s1).contains(s2));
    }
}