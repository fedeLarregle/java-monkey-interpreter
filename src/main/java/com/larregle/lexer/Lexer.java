package main.java.com.larregle.lexer;

import main.java.com.larregle.token.Token;

public class Lexer {

    private final String input;
    private int position;
    private int nextPosition;
    private char c;

    public Lexer(String input) {
        this.input = input;
        readChar();
    }

    public Token nextToken() {
        Token token = null;

        eatWhitespace();

        switch (this.c) {
            case '=': {
                Character peek = peekChar();
                if (peek != null) {
                    if (peek == '=') {
                        readChar();
                        token = new Token(Token.Operators.ASSIGN, Character.toString(c) + Character.toString(c));
                    } else {
                        token = new Token(Token.Operators.ASSIGN, Character.toString(c));
                    }
                }
                break;
            }
            case '!': {
                Character peek = peekChar();
                if (peek != null) {
                    if (peek == '=') {
                        Character curr = c;
                        readChar();
                        token = new Token(Token.Operators.NOT_EQ, curr + Character.toString(c));
                    } else {
                        token = new Token(Token.Operators.NOT, Character.toString(c));
                    }
                }
                break;
            }
            case '+': {
                token = new Token(Token.Operators.PLUS, Character.toString(c));
                break;
            }
            case '-': {
                token = new Token(Token.Operators.MINUS, Character.toString(c));
                break;
            }
            case '/': {
                token = new Token(Token.Operators.SLASH, Character.toString(c));
                break;
            }
            case '*': {
                token = new Token(Token.Operators.ASTERISK, Character.toString(c));
                break;
            }
            case '<': {
                token = new Token(Token.Operators.LT, Character.toString(c));
                break;
            }
            case '>': {
                token = new Token(Token.Operators.GT, Character.toString(c));
                break;
            }
            case '(': {
                token = new Token(Token.Delimiter.LPAREN, Character.toString(c));
                break;
            }
            case ')': {
                token = new Token(Token.Delimiter.RPAREN, Character.toString(c));
                break;
            }
            case '[': {
                token = new Token(Token.Delimiter.LBRACKET, Character.toString(c));
                break;
            }
            case ']': {
                token = new Token(Token.Delimiter.LBRACKET, Character.toString(c));
                break;
            }
            case '{': {
                token = new Token(Token.Delimiter.LBRACE, Character.toString(c));
                break;
            }
            case '}': {
                token = new Token(Token.Delimiter.RBRACE, Character.toString(c));
                break;
            }
            case '"': {
                token = new Token(Token.STRING, readString());
                break;
            }
            case '\0': {
                token = new Token(Token.EOF, "");
                break;
            }
            default:
                if (Character.isLetter(c)) {
                    final String literal = readIdentifier();
                    return new Token(literal, Token.lookupIdentifier(literal));
                } else if (Character.isDigit(c)) {
                    return new Token(Token.INT, readNumber());
                } else {
                    token = new Token(Token.ILLEGAL, Character.toString(c));
                }
        }
        readChar();
        return token;
    }

    private void readChar() {
        if (nextPosition >= input.length()) {
            c = '\0';
        } else {
            c = input.charAt(nextPosition);
        }
        position = nextPosition;
        nextPosition++;
    }

    private void eatWhitespace() {
        while (Character.isWhitespace(c)) {
            readChar();
        }
    }

    private String readIdentifier() {
        int pos = position;
        while (Character.isLetter(c))
            readChar();
        return input.substring(pos, position);
    }

    private String readNumber() {
        int pos = position;
        while (Character.isDigit(c))
            readChar();
        return input.substring(pos, position);
    }

    private String readString() {
        int pos = position + 1;
        for (;;) {
            readChar();
            if (c == '"' || c == '\0')
                break;
        }
        return input.substring(pos, position);
    }

    private Character peekChar() {
        if (nextPosition >= input.length())
            return null;
        return input.charAt(nextPosition);
    }

}
