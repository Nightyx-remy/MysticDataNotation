package com.mystic.examples;

import com.mystic.mdn.*;

/**
 * Example of how to parse a {@link MDNDocument}
 * @see MDN
 */
public class MDNReadExample {

    public static void main(String[] args) throws MDNLexerError, MDNParseError {
        String src = "# This is a comment\n" +
                "<Element[param1(\"125\", \"135\"), param2(\"Hello\")]>\n" +
                "    <Sub-Element[param1(\"Value1\")]/>\n" +
                "    <Sub-Element[param2(\"Value2\")]/>\n" +
                "</>";
        MDNDocument document = MDN.parse(src);
        for(MDNElement element : document.getElements()) {
            System.out.println(element);
        }
    }

}
