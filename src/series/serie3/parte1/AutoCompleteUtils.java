package series.serie3.parte1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AutoCompleteUtils {
    public static TNode loadWordsFromFile(TNode root, String fileName) {
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            while (bf.ready()) {
                String[] words = bf.readLine().split(" ");
                for (String s : words) {
                    TNode aux = root;
                    for (int i = 0; i < s.length(); i++) {
                        if (aux.children[s.charAt(i) - 'a'] == null) {
                            TNode tn = new TNode();
                            aux.children[s.charAt(i) - 'a'] = tn;
                            aux = tn;
                        } else aux = aux.children[s.charAt(i) - 'a'];
                    }
                    aux.isWord = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    public static TNode longestWithPrefix(TNode root, String prefix) {
        TNode res = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (res.children[prefix.charAt(i) - 'a'] == null) return null;
            res = res.children[prefix.charAt(i) - 'a'];
        }
        return res;
    }

    public static int countPossibleWords(TNode root, String prefix) {
        return countPossibleWordsAux(longestWithPrefix(root, prefix));
    }

    private static int countPossibleWordsAux(TNode root) {
        if (root == null) return 0;
        int res = 0;
        if (root.isWord) res = 1;
        for (int i = 0; i < root.children.length; i++)
            res += countPossibleWordsAux(root.children[i]);
        return res;
    }
}
