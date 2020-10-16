package hypersrm;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class MyRM {

    public static void main(String[] args) throws IOException {

        // 新建一个CharStream，从标准输入中读取输入
        CharStream charStream= CharStreams.fromStream(System.in) ;

        //新建一个词法分析器，处理CharStream
        CommandLexer lexer=new CommandLexer(charStream);

        //新建词法符号的缓冲区，存储词法分析器生成的词法符号
        CommonTokenStream tokens=new CommonTokenStream(lexer);

        //新建语法分析器，分析词法符号缓冲区的数据
        CommandParser parser=new CommandParser(tokens);

        //针对init规则开始词法分析
        ParseTree tree = parser.stat();

        //打印语法树
//        System.out.println("==========");
//        System.out.println(tree.toStringTree(parser));
//        System.out.println("==========");

        //add listener impl
        ParseTreeWalker walker=new ParseTreeWalker();

        RmListener listener=new RmListener("tmp");
        walker.walk(listener,tree);

        String mvCommand = listener.mvCommand();
        System.out.println(mvCommand);



    }


}
