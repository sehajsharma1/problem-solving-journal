package com.ocean.problemsolvingjournal.hr;

/*
Run-length encoding (RLE) is a very simple form of data compression in which runs of data (that is, sequences in which the same data value occurs in many consecutive data elements) are stored as a single data value and count, rather than as the original run. (Wikipedia)
Bob wrote some code to solve the following problem:

Given an input string, write a function that returns the Run Length Encoded string for the input string. For example, if the input string is AABBBCCCC, then the function should return A2B3C4.

But due to some technical problem he lost one line from his code. Can you complete the missing line?

The input string will consist of at most  uppercase English letters. Don't worry about input/output format, just fill the missing line marked by "~~Fill this line~~". If you insert any new lines or modify any other lines, your answer will not get accepted.

To restore the original code in the editor, create a new buffer by clicking on the top left icon in the editor.

Sample Input

AABBBCCCC
Sample Output
A2B3C4
*/
public class RunLengthEncoding {
    public static String encode(String source) {
        StringBuffer dest = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            int runLength = 1;
            char ch = source.charAt(i);
            while ((i + 1 < source.length()) && source.charAt(i + 1) == ch) {
                runLength++;
                i++;
            }

            dest.append(source.charAt(i));
            dest.append(runLength);
        }
        return dest.toString();
    }


    public static void main(String[] args) {
        String s = "AABBBCCCC";
        System.out.println(encode(s));

    }
}
