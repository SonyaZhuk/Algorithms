package algo.strings;

public class SimpleSubString {

    private static int searchSubstring(String pat, String txt) {
        int m = pat.length();
        int n = txt.length();
        for(int i = 0; i<= n-m; i++) {
            for(int j=0; j<m; j++) {
                if(txt.charAt(i + j)!= pat.charAt(j)) break;
                if(j == m) return i;

            }
        }
        return n;
    }
}
