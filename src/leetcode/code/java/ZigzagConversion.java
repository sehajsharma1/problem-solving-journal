package leetcode.code.java;
/*The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);*/
class ZigzagConversion {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        System.out.println(convert(s, 4));
        System.out.println(convert1(s, 4));
    }

    public static String convert(String s, int numRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }

        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < numRows && i < len; idx++)// vertically down
            {
                sb[idx].append(c[i++]);
            }
            for (int idx = numRows - 2; idx >= 1 && i < len; idx--) // obliquely up
            {
                sb[idx].append(c[i++]);
            }
        }
        for (int idx = 1; idx < sb.length; idx++) {
            sb[0].append(sb[idx]);
        }
        return sb[0].toString();
    }

    public static String convert1(String s, int numRows) {
        int len = s.length();
        if (numRows == 1 || numRows == len) {
            return s;
        }

        char[] ans = new char[len];
        int count = 0;
        int inc = 2 * (numRows - 1);

        for (int i = 0; i < numRows; i++) {
            int j = i;

            while (j < len) {
                ans[count++] = s.charAt(j);

                if (i == 0 || i == numRows - 1) {
                    j += inc;
                } else {
                    j += (inc - (2 * i));
                    if (j < len) {
                        ans[count++] = s.charAt(j);
                        j += (2 * i);
                    }
                }
            }
        }
        return new String(ans);
    }
}
