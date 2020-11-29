package series.serie3.parte1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static series.serie3.parte1.AutoCompleteUtils.loadWordsFromFile;
import static series.serie3.parte1.AutoCompleteUtils.longestWithPrefix;

public class LongestWithPrefixTest {
    static final TNode TREE = loadWordsFromFile(new TNode(), "s3p1test2.txt");
    static final TNode EMPTY_TREE = loadWordsFromFile(new TNode(), "s3p1test4.txt");

    @Test
    public void longestWithPrefix_empty() {
        assertEquals(EMPTY_TREE, longestWithPrefix(EMPTY_TREE, ""));
    }

    @Test
    public void longestWithPrefix_Has2Words() {
        TNode res = new TNode();
        res.children['o' - 'a'] = new TNode();
        res.children['n' - 'a'] = new TNode();
        TNode aux = longestWithPrefix(TREE, "ca");
        for (int i = 0; i < res.children.length; i++) {
            if (res.children[i] != null)
                assertTrue(aux.children[i] != null);
            else
                assertTrue(aux.children[i] == null);
        }
    }

    @Test
    public void longestWithPrefix_DontHavePrefix() {
        assertEquals(null, longestWithPrefix(TREE, "aed"));
    }

    @Test
    public void longestWithPrefix_EqualsWord() {
        TNode res = longestWithPrefix(TREE, "caneta");
        for (TNode tn : res.children) assertEquals(null, tn);
        assertTrue(res.isWord);
    }
}
