public class RecursiveChar {

    public static String recursiveMultiplyChar(char c, int n) {
        return recursiveMultiplyCharTail(c, n, "");
    }

    public static String recursiveMultiplyCharTail(char c, int n, String acc) {
        if (n == acc.length()) {
            return acc; 
        }

        acc += String.valueOf(c);
        return recursiveMultiplyCharTail(c, n, acc);
    }


    public static String iterativeMultiplyChar(char c, int n) {
        String result = "";
        for(int i = 0; i < n; i++) {
            result += String.valueOf(c);    
        }
        return result;
    }
    public static void main(String[] args) {
        String hey = recursiveMultiplyChar('e', 3);
        String hay = iterativeMultiplyChar('a', 3);
    }
}