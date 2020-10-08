package arrrayinit;


import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class TestArrayInit {

    public static void main(String[] args) throws IOException {

        // 新建一个CharStream，从标准输入中读取输入
        CharStream charStream= CharStreams.fromStream(System.in) ;

        //新建一个词法分析器，处理CharStream
        ArrayInitLexer lexer=new ArrayInitLexer(charStream);

        //新建词法符号的缓冲区，存储词法分析器生成的词法符号
        CommonTokenStream tokens=new CommonTokenStream(lexer);

        //新建语法分析器，分析词法符号缓冲区的数据
        ArrayInitParser parser=new ArrayInitParser(tokens);

        //针对init规则开始词法分析
        ParseTree tree = parser.init();

        //打印语法树
        System.out.println("==========");
        System.out.println(tree.toStringTree(parser));

        //add listener impl
        ParseTreeWalker walker=new ParseTreeWalker();

        ArrayInitListener listener=new ArrayInitListenerImpl();
        walker.walk(listener,tree);

        System.out.println();

    }



}
