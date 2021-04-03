package com.mystic.mdn;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class is used to convert the list of {@link MDNToken} to {@link MDNDocument}
 * @see MDNLexer
 */
public class MDNParser {

    private final ArrayList<MDNToken> tokens;
    private MDNToken current = null;
    private int ptr = -1;

    /**
     * Constructor for the parser
     * @param tokens the list of tokens you want to convert
     * @throws NullPointerException if the list of tokens is null
     */
    public MDNParser(ArrayList<MDNToken> tokens) throws NullPointerException {
        if(tokens == null) throw new NullPointerException("Failed to create MDNParser!");
        this.tokens = tokens;
        advance();
    }

    /**
     * Advance to the next token
     */
    public void advance() {
        ptr++;
        if(ptr < tokens.size()) current = tokens.get(ptr);
        else current = null;
    }

    /**
     * Convert the list of tokens into {@link MDNDocument}
     * @return the {@link MDNDocument}
     * @throws MDNParseError if there is an unexpected token
     */
    public MDNDocument parse() throws MDNParseError {
        MDNDocument document = new MDNDocument();

        Stack<MDNElement> elements = new Stack<>();
        MDNElement element = null;
        MDNParam param = null;
        boolean innerParam = false;
        boolean innerParamNext = false;

        while(current != null) {
            switch (current.type) {
                case IDENTIFIER:
                    if(element != null) {
                        if(element.getName().isEmpty()) {
                            element.setName(current.value);
                        } else {
                            if(param != null) {
                                if (param.getName().isEmpty()) {
                                    param.setName(current.value);
                                } else {
                                    throw new MDNParseError("Unexpected Token, '" + current + "', duplicate 'IDENTIFIER'!");
                                }
                            } else {
                                throw new MDNParseError("Unexpected Token, '" + current + "', missing '['!");
                            }
                        }
                    } else {
                        throw new MDNParseError("Unexpected Token, '" + current + "', missing '<'!");
                    }
                    break;
                case VALUE:
                    if(element != null) {
                        if(param != null) {
                            if(innerParam) {
                                if(innerParamNext) {
                                    param.addValue(current.value);
                                    innerParamNext = false;
                                } else {
                                    throw new MDNParseError("Unexpected Token, '" + current + "', missing ','!");
                                }
                            } else {
                                throw new MDNParseError("Unexpected Token, '" + current + "', missing '('!");
                            }
                        } else {
                            throw new MDNParseError("Unexpected Token, '" + current + "', missing '['!");
                        }
                    } else {
                        throw new MDNParseError("Unexpected Token, '" + current + "', missing '<'!");
                    }
                    break;
                case L_BRACKET:
                    if(element != null) {
                        if (param == null) {
                            param = new MDNParam("");
                            element.addParam(param);
                        } else {
                            throw new MDNParseError("Unexpected Token, '" + current + "', missing '['!");
                        }
                    } else {
                        throw new MDNParseError("Unexpected Token, '" + current + "', missing '<'!");
                    }
                    break;
                case R_BRACKET:
                    if(element != null) {
                        if(param != null) {
                            if(param.getName().isEmpty()) {
                                throw new MDNParseError("Unexpected Token, '" + current + "', missing 'IDENTIFIER'!");
                            }
                            param = null;
                        } else {
                            throw new MDNParseError("Unexpected Token, '" + current + "', missing '['!");
                        }
                    } else {
                        throw new MDNParseError("Unexpected Token, '" + current + "', missing '<'!");
                    }
                    break;
                case COMMA:
                    if(element != null) {
                        if(param != null) {
                            if(param.getName().isEmpty()) {
                                throw new MDNParseError("Unexpected Token, '" + current + "', missing 'IDENTIFIER'!");
                            } else {
                                if(innerParam) {
                                    if(!innerParamNext) {
                                        innerParamNext = true;
                                    } else {
                                        throw new MDNParseError("Unexpected Token, '" + current + "', duplicate or unused ','!");
                                    }
                                } else {
                                    param = new MDNParam("");
                                    element.addParam(param);
                                }
                            }
                        } else {
                            throw new MDNParseError("Unexpected Token, '" + current + "', missing '['!");
                        }
                    } else {
                        throw new MDNParseError("Unexpected Token, '" + current + "', missing '<'!");
                    }
                    break;
                case L_PARENT:
                    if(param != null) {
                        if(!param.getName().isEmpty()) {
                            if(!innerParam) {
                                innerParam = true;
                                innerParamNext = true;
                            } else {
                                throw new MDNParseError("Unexpected Token, '" + current + "', missing '['!");
                            }
                        } else {
                            throw new MDNParseError("Unexpected Token, '" + current + "', missing 'IDENTIFIER'!");
                        }
                    } else {
                        throw new MDNParseError("Unexpected Token, '" + current + "', missing '['!");
                    }
                    break;
                case R_PARENT:
                    if(param != null) {
                        if(innerParam) {
                            innerParam = false;
                            innerParamNext = false;
                        } else {
                            throw new MDNParseError("Unexpected Token, '" + current + "', missing '('!");
                        }
                    } else {
                        throw new MDNParseError("Unexpected Token, '" + current + "', missing '['!");
                    }
                    break;
                case L_ANGLE:
                    if(element == null) {
                        element = new MDNElement("");
                        if(!elements.isEmpty()) {
                            elements.lastElement().addElement(element);
                        } else {
                            document.addElement(element);
                        }
                    } else {
                        throw new MDNParseError("Unexpected Token, '" + current + "', missing '>'!");
                    }
                    break;
                case R_ANGLE:
                    if(element != null) {
                        if(!element.getName().isEmpty()) {
                            elements.push(element);
                            element = null;
                        } else {
                            throw new MDNParseError("Unexpected Token, '" + current + "', missing 'IDENTIFIER'!");
                        }
                    }
                    break;
                case MARK_END:
                    if(element != null) {
                        element = null;
                    } else {
                        if(!elements.isEmpty()) {
                            elements.pop();
                        } else {
                            throw new MDNParseError("Unexpected Token, '" + current + "', duplicate '</>' or '/>'!");
                        }
                    }
                    break;
            }

            advance();
        }

        return document;
    }

}
