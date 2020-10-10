package visitor;

import org.apache.spark.sql.catalyst.parser.SqlBaseBaseVisitor;
import org.apache.spark.sql.catalyst.parser.SqlBaseParser;

public class MyVisitor extends SqlBaseBaseVisitor<String> {

    @Override
    public String visitSingleStatement(SqlBaseParser.SingleStatementContext ctx) {
        System.out.println("visitSingleStatement");
        return visitChildren(ctx);
    }

}
