package series.serie3.parte1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static series.serie3.parte1.AutoCompleteUtils.countPossibleWords;
import static series.serie3.parte1.AutoCompleteUtils.loadWordsFromFile;

public class CountPossibleWordsTest {
    static final TNode TREE = loadWordsFromFile(new TNode(), "s3p1test5.txt");
    static final TNode EMPTY_TREE = loadWordsFromFile(new TNode(), "s3p1test4.txt");

    @Test
    public void countPossibleWords_EmptyTree() {
        assertEquals(0, countPossibleWords(EMPTY_TREE, "cao"));
    }

    @Test
    public void countPossibleWords_PrefixDoesntExist() {
        assertEquals(0, countPossibleWords(TREE, "zebra"));
    }

    @Test
    public void countPossibleWords_UniqueWord() {
        assertEquals(1, countPossibleWords(TREE, "cao"));
        assertEquals(1, countPossibleWords(TREE, "caneta"));
        assertEquals(1, countPossibleWords(TREE, "aed"));
        assertEquals(1, countPossibleWords(TREE, "ali"));
        assertEquals(1, countPossibleWords(TREE, "alo"));
    }

    @Test
    public void countPossibleWords_NotUniqueWord() {
        assertEquals(2, countPossibleWords(TREE, "ca"));
        assertEquals(3, countPossibleWords(TREE, "a"));
        assertEquals(2, countPossibleWords(TREE, "al"));
    }
}
