package series.serie2.parte1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static series.serie2.parte1.Utils.verifyXML;


public class VerifyXMLTest {

    @Test
    public void verifyXML_empty_String() {
        assertTrue(verifyXML(""));
    }

    @Test
    public void verifyXML_simple_pair() {
        assertTrue(verifyXML("<CD></CD>"));
        assertTrue(verifyXML("<CD> CD 1 </CD>"));
    }

    @Test
    public void verifyXML_open_only() {
        assertFalse(verifyXML("<CD>"));
        assertFalse(verifyXML("<CD> CD1"));
        assertFalse(verifyXML("<CD> <Title> "));
        assertFalse(verifyXML("<CD>  CD1 <Title> Title1"));
        assertFalse(verifyXML("<CD>  /CD1 <Title> Title1"));

    }

    public void verifyXML_close_only() {
        assertFalse(verifyXML("</CD>"));
        assertFalse(verifyXML(" /CD1 </CD>"));
        assertFalse(verifyXML("  </Title> </CD>"));
    }

    public void verifyXML_nonValid_simplePair() {
        assertFalse(verifyXML("<CD CD 1</CD>"));
        assertFalse(verifyXML("<CD> CD 1 /CD>"));
        assertFalse(verifyXML("<CD CD 1 /CD>"));
        assertFalse(verifyXML("CD> CD 1 /CD"));
    }

    @Test
    public void verifyXML_close_and_open_inverted() {
        assertFalse(verifyXML("</CD><CD>"));
        assertFalse(verifyXML("</CD> CD1 <CD>"));
        assertFalse(verifyXML("</CD> <Title> CD1 </Title><CD>"));
    }


    @Test
    public void verifyXML_pairs() {
        assertTrue(verifyXML("<CD> <TITLE> Private Dancer </TITLE> <ARTIST> Tina Turner </ARTIST><COUNTRY> UK </COUNTRY> <COMPANY> Capitol </COMPANY><PRICE> 8.90 </PRICE><YEAR> 1983 </YEAR> </CD>"));
        assertTrue(verifyXML("<CD> <TITLE> <MainTitle>Private Dancer</MainTitle> </TITLE> <ARTIST> Tina Turner </ARTIST><COUNTRY> UK </COUNTRY> <COMPANY> Capitol </COMPANY><PRICE> 8.90 </PRICE><YEAR> 1983 </YEAR> </CD>"));
        assertFalse(verifyXML("<CD> <TITLE> MainTitle> Private Dancer</MainTitle> </TITLE> <ARTIST> Tina Turner </ARTIST><COUNTRY> UK </COUNTRY> <COMPANY> Capitol </COMPANY><PRICE> 8.90 </PRICE><YEAR> 1983 </YEAR> </CD>"));
    }
}
