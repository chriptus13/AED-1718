package series.serie2.parte1;

public class Utils {

    public static boolean verifyXML(String str) {
        Stack<String> stack = new Stack<>();
        while (str.length() != 0) {
            if (str.indexOf('<') == -1 || str.indexOf('>') == -1 || str.indexOf('<') > str.indexOf('>')) return false;
            String k = str.substring(str.indexOf('<') + 1, str.indexOf('>'));
            str = str.substring(str.indexOf('>') + 1);
            if (k.charAt(0) != '/') stack.push(k);
            else if (!stack.isEmpty() && stack.peek().equals(k.substring(1))) stack.pop();
            else return false;
        }
        return stack.isEmpty();
    }
}
