package com.mystic.mdn;

/**
 * This class is used to format and parse {@link MDNDocument}
 */
public class MDN {

    /**
     * Parse a string into a {@link MDNDocument}
     * @param src the string you want to parse
     * @return the {@link MDNDocument} created
     * @throws NullPointerException if {@code src} is null
     * @throws MDNLexerError if any error occurs during {@link MDNLexer#makeTokens()}
     * @throws MDNParseError if any error occurs during {@link MDNParser#parse()}
     */
    public static MDNDocument parse(String src) throws NullPointerException, MDNLexerError, MDNParseError {
        MDNLexer lexer = new MDNLexer(src);
        MDNParser parser = new MDNParser(lexer.makeTokens());
        return parser.parse();
    }

    /**
     * Format the {@link MDNDocument} into a string
     * @param document the document you want to format
     * @return the formatted string
     */
    public static String format(MDNDocument document) {
        return MDNFormatter.formatDocument(document);
    }

}
