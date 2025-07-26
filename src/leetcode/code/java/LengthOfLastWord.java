package leetcode.code.java;

/*Given a string s consisting of words and spaces, return the length of the last word in the string.
A word is a maximal
substring
 consisting of non-space characters only.*/
public class LengthOfLastWord {
    public static void main(String[] args) {
        String s = "   fly me   to   the moon  ";
        System.out.println(lengthOfLastWord(s));
    }

    public static int lengthOfLastWord(String s) {
        int length = s.length();
        int count = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                ++count;
                if (i != 0 && s.charAt(i - 1) == ' ') {
                    return count;
                }
            }

        }
        return count;

    }
}
