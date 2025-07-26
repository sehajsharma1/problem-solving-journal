package leetcode.code.java;

public class FirstOccurrence {

    public static void main(String[] args) {
        String haystack = "aaa";
        String needle = "aaaa";
        System.out.println(strStr(haystack, needle));
    }

    public static int strStr(String haystack, String needle) {
        char first = needle.charAt(0);
        int max = ((haystack.length()) - needle.length());
        for (int i = 0; i <= max; i++) {
            // Look for first character.
            if (haystack.charAt(i) != first) {
                while (++i <= max && haystack.charAt(i) != first) ;
            }
            // Found first character, now look at the rest of value
            if (i <= max) {
                int j = i + 1;
                int end = j + (needle.length()) - 1;
                for (int k = 1; j < end && haystack.charAt(j) == needle.charAt(k); j++, k++) ;
                if (j == end) {
                    // Found whole string.
                    return i;
                }
            }
        }
        return -1;

    }
}
