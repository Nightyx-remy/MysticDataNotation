package com.mystic.mdn;

/**
 * This class represent an error thrown by the {@link MDNLexer} especially {@link MDNLexer#makeTokens()}
 */
public class MDNLexerError extends Exception {

    public MDNLexerError() {
        super();
    }

    public MDNLexerError(String message) {
        super(message);
    }

    public MDNLexerError(String message, Throwable cause) {
        super(message, cause);
    }

    public MDNLexerError(Throwable cause) {
        super(cause);
    }

}
