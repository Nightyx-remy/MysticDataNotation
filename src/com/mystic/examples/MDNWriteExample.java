package com.mystic.examples;

import com.mystic.mdn.*;

/**
 * Example of how to format a {@link MDNDocument} into a string
 * @see MDN
 */
public class MDNWriteExample {

    public static void main(String[] args) {
        MDNDocument document = new MDNDocument();
        MDNElement element = new MDNElement("Entity")
                .addParam(new MDNParam("Param1").addValue("5").addValue("6"))
                .addParam(new MDNParam("Param2").addValue("100110"))
                .addElement(new MDNElement("Sub-Entity")
                        .addParam(new MDNParam("param1").addValue("Hello")))
                .addElement(new MDNElement("Sub-Entity")
                        .addParam(new MDNParam("param1").addValue("World")));
        document.addElement(element);
        element = new MDNElement("Entity2").addParam(new MDNParam("param1").addValue("res\\file1.mdn"));
        document.addElement(element);
        System.out.println(MDN.format(document));
    }

}
