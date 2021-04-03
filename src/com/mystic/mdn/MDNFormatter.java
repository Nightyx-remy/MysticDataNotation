package com.mystic.mdn;

/**
 * This class is used to format a {@link MDNDocument} into a string
 */
public class MDNFormatter {

    /**
     * Convert a {@link MDNDocument} into a String
     * @param document the document you want to convert
     * @return the resulting string
     */
    public static String formatDocument(MDNDocument document) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for(MDNElement element : document.getElements()) {
            if(i != 0) builder.append("\n");
            builder.append(formatElement(element));
            i++;
        }
        return builder.toString();
    }

    private static String formatElement(MDNElement element) {
        StringBuilder builder = new StringBuilder();
        builder.append("<").append(element.getName()).append("[");
        int i = 0;
        for(MDNParam param : element.getParams()) {
            if(i != 0) builder.append(", ");
            builder.append(formatParameter(param));
            i++;
        }
        builder.append("]");
        if(element.getElements().isEmpty()) {
            builder.append("/>");
        } else {
            builder.append(">").append("\n");
            for(MDNElement child : element.getElements()) {
                for(String str : formatElement(child).split("\n")) {
                    builder.append("\t").append(str).append("\n");
                }
            }
            builder.append("</>");
        }
        return builder.toString();
    }

    private static String formatParameter(MDNParam param) {
        StringBuilder builder = new StringBuilder();
        builder.append(param.getName()).append("(");
        if(!param.isEmpty()) {
            int i = 0;
            for(String value : param.getValues()) {
                if(i != 0) builder.append(",");
                builder.append("\"").append(value).append("\"");
                i++;
            }
        }
        builder.append(")");
        return builder.toString();
    }

}
