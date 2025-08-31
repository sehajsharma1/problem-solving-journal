package hr.code.java;

public class Encryption {

    public static void main(String[] args) {
        System.out.println(encryption("chillout"));
    }

    public static String encryption(String s) {
        String stringWithoutSpace = s.replaceAll(" ", "");
        int length = stringWithoutSpace.length();
        int column = (int) Math.ceil(Math.sqrt(length));
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < column; c++) {

            for (int j = c; j < length; j = j + column) {

                sb.append(stringWithoutSpace.charAt(j));
            }
            sb.append(" ");
        }
        return sb.toString();

    }


}
