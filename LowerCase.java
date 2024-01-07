/** String processing exercise 1. */
public class LowerCase {
    public static void main(String[] args) {
        String str = args[0];
        System.out.println(lowerCase(str));
    }

    /**
     * Returns a string which is identical to the original string,
     * except that all the upper-case letters are converted to lower-case letters.
     * Non-letter characters are left as is.
     */
    public static String lowerCase(String s) {
        char rel_char;
        String new_string = "";
        for (int i = 0; i < s.length(); i++) {
            rel_char = s.charAt(i);
            if (rel_char >= 65 && rel_char <= 90) {
                rel_char = (char) (rel_char + 32);
            }
            new_string += rel_char;
        }
        // Replace the following statement with your code
        return new_string;
    }
}
