package leetcode.code.java;

/*Integer to Roman*/
public class IntegerToRoman {
    private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static void main(String[] args) {
        int num = 3899;
        System.out.println(intToRoman(num));
        System.out.println(intToRoman2(num));
    }

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int numValueLeft = num;
        if (numValueLeft >= 1000) {
            int quotient = numValueLeft / 1000;
            for (int i = 0; i < quotient; i++) {
                sb.append("M");
            }
            numValueLeft = numValueLeft - (1000 * quotient);
        }
        if (numValueLeft >= 500) {
            int diff = numValueLeft - 500;
            if (diff >= 400) {
                sb.append("CM");
                numValueLeft = numValueLeft - 900;
            } else {
                sb.append("D");
                numValueLeft = diff;
            }
        }
        if (numValueLeft >= 100) {
            int quotient = numValueLeft / 100;
            if (quotient > 3) {
                sb.append("CD");
                numValueLeft = numValueLeft - 400;
            } else {
                for (int i = 0; i < quotient; i++) {
                    sb.append("C");
                }
                numValueLeft = numValueLeft - (100 * quotient);
            }

        }
        if (numValueLeft >= 50) {
            int diff = numValueLeft - 50;
            if (diff >= 40) {
                sb.append("XC");
                numValueLeft = numValueLeft - 90;
            } else {
                sb.append("L");
                numValueLeft = diff;
            }
        }
        if (numValueLeft >= 10) {
            int quotient = numValueLeft / 10;
            if (quotient > 3) {
                sb.append("XL");
                numValueLeft = numValueLeft - 40;
            } else {
                for (int i = 0; i < quotient; i++) {
                    sb.append("X");
                }
                numValueLeft = numValueLeft - (10 * quotient);
            }

        }
        if (numValueLeft >= 5) {
            int diff = numValueLeft - 5;
            if (diff >= 4) {
                sb.append("IX");
                numValueLeft = numValueLeft - 9;
            } else {
                sb.append("V");
                numValueLeft = diff;
            }
        }
        if (numValueLeft >= 1) {
            int quotient = numValueLeft / 1;
            if (quotient > 3) {
                sb.append("IV");
                numValueLeft = numValueLeft - 4;
            } else {
                for (int i = 0; i < quotient; i++) {
                    sb.append("I");
                }
                numValueLeft = numValueLeft - (1 * quotient);
            }

        }
        return sb.toString();
    }

    public static String intToRoman2(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length && num > 0; i++) {
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
