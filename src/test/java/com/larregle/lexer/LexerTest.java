package com.larregle.lexer;

import com.larregle.token.Token;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LexerTest {

    @Test
    public void testLexer() {
        final String input = "let five = 5;\n" +
                "\tlet add = fn(x, y)\n";

        final Lexer lexer = new Lexer(input);
        final List<Token> tokens = Arrays.asList(
                new Token(Token.Keywords.LET, "let"),
                new Token(Token.IDENTIFIER, "five"),
                new Token(Token.Operators.ASSIGN, "="),
                new Token(Token.INT, "5"),
                new Token(Token.Delimiter.SEMICOLON, ";"),
                new Token(Token.Keywords.LET, "let"),
                new Token(Token.IDENTIFIER, "add"),
                new Token(Token.Operators.ASSIGN, "="),
                new Token(Token.Keywords.FUNCTION, "fn"),
                new Token(Token.Delimiter.LPAREN, "("),
                new Token(Token.IDENTIFIER, "x"),
                new Token(Token.Delimiter.COMMA, ","),
                new Token(Token.IDENTIFIER, "y"),
                new Token(Token.Delimiter.RPAREN, ")")
        );

        Token currentToken;

        for (final Token token : tokens) {
            currentToken = lexer.nextToken();
            Assert.assertEquals(currentToken.getType(), token.getType());
            Assert.assertEquals(currentToken.getLiteral(), token.getLiteral());
        }
    }
}
