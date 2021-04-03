package com.mystic.mdn;

/**
 * This class represent a Token, it is used in the {@link MDNLexer} and {@link MDNParser}
 */
public class MDNToken {

    public final MDNTokenType type;
    public final String value;
    public final int line, start, end;

    /**
     * Constructor for MDNToken
     *
     * @param type the type of the Token, one of {@link MDNTokenType}
     * @param value the value of the Token as a String (can be empty)
     * @param line the line of the Token
     * @param start the index of the start of this Token on the <code>line</code>
     * @param end the index of the end of this Token on the <code>line</code>
     */
    public MDNToken(MDNTokenType type, String value, int line, int start, int end) {
        this.type = type;
        this.value = value;
        this.line = line;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + type + ":" + value + " at " + line + ":(" + start + ";" + end + ")]";
    }
}
