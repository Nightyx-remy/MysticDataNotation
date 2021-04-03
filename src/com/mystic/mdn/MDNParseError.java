package com.mystic.mdn;

/**
 * This class represent an error thrown by the {@link MDNParser} especially {@link MDNParser#parse()}
 */
public class MDNParseError extends Exception {

    public MDNParseError() {
        super();
    }

    public MDNParseError(String message) {
        super(message);
    }

    public MDNParseError(String message, Throwable cause) {
        super(message, cause);
    }

    public MDNParseError(Throwable cause) {
        super(cause);
    }

}
