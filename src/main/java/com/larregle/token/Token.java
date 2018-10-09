package com.larregle.token;

import java.util.HashMap;
import java.util.Map;

public class Token {

    public static final String ILLEGAL = "ILLEGAL";
    public static final String EOF = "EOF";

    public static final String IDENTIFIER = "IDENTIFIER";
    public static final String INT = "INT";
    public static final String STRING = "STRING";

    public static class Operators {
        public static final String ASSIGN = "=";
        public static final String PLUS = "+";
        public static final String MINUS = "-";
        public static final String NOT = "!";
        public static final String ASTERISK = "*";
        public static final String SLASH = "/";

        public static final String EQ = "==";
        public static final String NOT_EQ = "!=";

        public static final String LT = "<";
        public static final String GT = ">";
    }

    public static class Delimiter {
        public static final String COMMA = ",";
        public static final String SEMICOLON = ";";

        public static final String LPAREN = "(";
        public static final String RPAREN = ")";
        public static final String LBRACE = "{";
        public static final String RBRACE = "}";

        public static final String LBRACKET = "[";
        public static final String RBRACKET = "]";
    }

    public static class Keywords {
        public static final String FUNCTION = "FUNCTION";
        public static final String LET      = "LET";
        public static final String TRUE     = "TRUE";
        public static final String FALSE    = "FALSE";
        public static final String IF       = "IF";
        public static final String ELSE     = "ELSE";
        public static final String RETURN   = "RETURN";
    }

    private static final Map<String, String> keywords;
    private final String type;
    private final String literal;

    static {
        keywords = new HashMap<String, String>(){{
            put("fn", Keywords.FUNCTION);
            put("let", Keywords.LET);
            put("true", Keywords.TRUE);
            put("false", Keywords.FALSE);
            put("if", Keywords.IF);
            put("else", Keywords.ELSE);
            put("return", Keywords.RETURN);
        }};
    }

    public Token(String type, String literal) {
        this.type = type;
        this.literal = literal;
    }

    public static String lookupIdentifier(String identifier) {
        return keywords.get(identifier) != null ? keywords.get(identifier) : IDENTIFIER;
    }

    public String getType() {
        return type;
    }

    public String getLiteral() {
        return literal;
    }
}
