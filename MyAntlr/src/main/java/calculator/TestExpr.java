package calculator;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Arrays;

public class TestExpr {

    public static void main(String[] args) throws IOException {

        String expressionString="1+2*3-(2+4)";

        CodePointCharStream charStream = CharStreams.fromString(expressionString);

        ExprLexer exprLexer=new ExprLexer(charStream);

        CommonTokenStream commonTokenStream=new CommonTokenStream(exprLexer);

        ExprParser parser=new ExprParser(commonTokenStream);

        ParseTree tree= parser.expr();
        System.out.println();
        System.out.println("tree = " + tree.getText());
        System.out.println(tree.toStringTree(parser));

    }
}
