package visitor;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.spark.sql.catalyst.parser.SqlBaseLexer;
import org.apache.spark.sql.catalyst.parser.SqlBaseParser;

public class ParserDriver {

    public static void main(String[] args) {

        String sqlText="select * from a where age>10";

        SqlBaseLexer lexer=new SqlBaseLexer(CharStreams.fromString(sqlText));

        CommonTokenStream tokens=new CommonTokenStream(lexer);

        SqlBaseParser parser=new SqlBaseParser(tokens);

        MyVisitor visitor=new MyVisitor();

        visitor.visitSingleStatement(parser.singleStatement());

    }
}
