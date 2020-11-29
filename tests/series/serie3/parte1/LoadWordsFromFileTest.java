package series.serie3.parte1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static series.serie3.parte1.AutoCompleteUtils.loadWordsFromFile;

public class LoadWordsFromFileTest {

    private static String FILE_NAME1 = "s3p1test1.txt";
    private static String FILE_NAME2 = "s3p1test2.txt";
    private static String FILE_NAME3 = "s3p1test3.txt";

    @Test
    public void loadWordsFromFile_empty() {
        TNode root = new TNode();
        loadWordsFromFile(root, "");

        for (TNode n : root.children)
            assertEquals(null, n);
    }

    @Test
    public void loadWordsFromFile_singleWord() {
        TNode root = new TNode();
        String FileName1_Word = "cao";
        loadWordsFromFile(root, FILE_NAME1);
        for (int i = 0; i < FileName1_Word.length(); i++) {
            for (int j = 0; j < root.children.length; j++) {
                if (j == (FileName1_Word.charAt(i) - 'a'))
                    assertNotEquals(null, root.children[j]);
                else
                    assertEquals(null, root.children[j]);
            }
            root = root.children[(FileName1_Word.charAt(i) - 'a')];
        }
        assertTrue(root.isWord);
    }

    @Test
    public void loadWordsFromFile_WordsWithCommonPrefix() {
        TNode root = new TNode();
        String s1 = "cao";
        String s2 = "caneta";
        String word = s1 + " " + s2;
        loadWordsFromFile(root, FILE_NAME2);
        for (String s : word.split(" ")) {
            TNode aux = root;
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < root.children.length; j++) {
                    if (j == (s.charAt(i) - 'a') || (i < s1.length() && j == (s1.charAt(i) - 'a')) || (i < s2.length() && j == (s2.charAt(i) - 'a')))
                        assertNotEquals(null, aux.children[j]);
                    else
                        assertEquals(null, aux.children[j]);
                }
                aux = aux.children[(s.charAt(i) - 'a')];
            }
            assertTrue(aux.isWord);
        }
    }

    @Test
    public void loadWordsFromFile_WordsWithoutCommonPrefix() {
        TNode root = new TNode();
        String word = "cao dado";
        loadWordsFromFile(root, FILE_NAME3);
        for (String s : word.split(" ")) {
            TNode aux = root;
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < root.children.length; j++) {
                    if (i == 0 && (j == ('c' - 'a') || j == ('d' - 'a')) || j == s.charAt(i) - 'a')
                        assertNotEquals(null, aux.children[j]);
                    else
                        assertEquals(null, aux.children[j]);
                }
                aux = aux.children[(s.charAt(i) - 'a')];
            }
            assertTrue(aux.isWord);
        }
    }

}
