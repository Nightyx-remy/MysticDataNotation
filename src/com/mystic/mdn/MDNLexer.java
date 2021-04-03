package com.mystic.mdn;

import java.util.ArrayList;

/**
 * This class is used to convert a string into a list of tokens.
 * @see MDNToken
 */
public class MDNLexer {

    private static final String IDENTIFIER_START = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
    private static final String IDENTIFIER_BODY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789-";

    private final String src;
    private int ptr = -1;
    private int line = 0;
    private int linePos = -1;
    private char current = 0;

    /**
     * Constructor for MDNLexer
     * @param src the string you want to convert
     * @throws NullPointerException is the <code>src</code> is null
     */
    public MDNLexer(String src) throws NullPointerException {
        if(src == null) throw new NullPointerException("Failed to create MDNLexer");
        this.src = src;
        advance();
    }

    /**
     * Advance to the next character
     */
    private void advance() {
        ptr++;
        if(ptr < src.length()) current = src.charAt(ptr);
        else current = 0;
        if(current == '\n') {
            line++;
            linePos = 0;
        } else linePos++;
    }

    /**
     * Convert the src string into a list of tokens
     * @return the list of tokens created
     * @throws MDNLexerError if there is a syntax error
     */
    public ArrayList<MDNToken> makeTokens() throws MDNLexerError {
        ArrayList<MDNToken> tokens = new ArrayList<>();

        while(current != 0) {
            if(IDENTIFIER_START.indexOf(current) != -1) {
                int startPos = linePos;
                StringBuilder builder = new StringBuilder().append(current);
                advance();
                while(IDENTIFIER_BODY.indexOf(current) != -1) {
                    builder.append(current);
                    advance();
                }
                tokens.add(new MDNToken(MDNTokenType.IDENTIFIER, builder.toString(), line, startPos, linePos + 1));
            } else if(current == '"') {
                int startPos = linePos;
                advance();
                StringBuilder builder = new StringBuilder();
                boolean skip = false;
                while(true) {
                    if(current == '\\') {
                        if(skip) {
                            builder.append('\\');
                            skip = false;
                        } else skip = true;
                    } else if (current == 't') {
                        if(skip) builder.append("\t");
                        else builder.append("t");
                        skip = false;
                    } else if (current == 'r') {
                        if(skip) builder.append("\r");
                        else builder.append("r");
                        skip = false;
                    } else if (current == 'n') {
                        if(skip) builder.append("\n");
                        else builder.append("n");
                        skip = false;
                    } else if (current == 'f') {
                        if(skip) builder.append("\f");
                        else builder.append("f");
                        skip = false;
                    } else if (current == '"') {
                        if(skip) builder.append("\"");
                        else break;
                        skip = false;
                    } else if(current == 0) {
                        throw new MDNLexerError("Unexpected EOF, should be '\"' at " + line + ":" + linePos + "!");
                    } else if(current == '\n') {
                        throw new MDNLexerError("Unexpected '\n' (linebreak), at " + line + ":" + linePos + "!");
                    } else {
                        builder.append(current);
                        skip = false;
                    }
                    advance();
                }
                tokens.add(new MDNToken(MDNTokenType.VALUE, builder.toString(), line, startPos, linePos + 1));
            } if(current == '#') {
                while (current != '\n') {
                    advance();
                }
            } else if(current == ',') {
                tokens.add(new MDNToken(MDNTokenType.COMMA, "", line, linePos, linePos + 1));
            } else if(current == '(') {
                tokens.add(new MDNToken(MDNTokenType.L_PARENT, "", line, linePos, linePos + 1));
            } else if(current == ')') {
                tokens.add(new MDNToken(MDNTokenType.R_PARENT, "", line, linePos, linePos + 1));
            } else if(current == '[') {
                tokens.add(new MDNToken(MDNTokenType.L_BRACKET, "", line, linePos, linePos + 1));
            } else if(current == ']') {
                tokens.add(new MDNToken(MDNTokenType.R_BRACKET, "", line, linePos, linePos + 1));
            } else if(current == '<') {
                advance();
                if(current == '/') {
                    advance();
                    if(current == '>') tokens.add(new MDNToken(MDNTokenType.MARK_END, "", line, linePos, linePos + 1));
                    else throw new MDNLexerError("Unexpected '" + current + "', should be '>' at " + line + ":" + linePos + "!");
                } else tokens.add(new MDNToken(MDNTokenType.L_ANGLE, "", line, linePos, linePos + 1));
                continue;
            } else if(current == '/') {
                advance();
                if(current == '>') tokens.add(new MDNToken(MDNTokenType.MARK_END, "", line, linePos, linePos + 1));
                else throw new MDNLexerError("Unexpected '" + current + "', should be '>' at " + line + ":" + linePos + "!");
                continue;
            } else if(current == '>') {
                tokens.add(new MDNToken(MDNTokenType.R_ANGLE, "", line, linePos, linePos + 1));
            }
            advance();
        }

        return tokens;
    }

}
