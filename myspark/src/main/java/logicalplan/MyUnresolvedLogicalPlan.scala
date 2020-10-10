package logicalplan

import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import org.apache.spark.sql.catalyst.parser.{ParseErrorListener, PostProcessor, SqlBaseLexer, SqlBaseParser, UpperCaseCharStream}
import org.apache.spark.sql.execution.SparkSqlParser
import org.apache.spark.sql.internal.SQLConf

object MyUnresolvedLogicalPlan {

  def parser(sqlText: String) = {

    val lexer = new SqlBaseLexer(CharStreams.fromString(sqlText))
    //    lexer.removeErrorListeners()
    //    lexer.addErrorListener(ParseErrorListener)
    //    lexer.legacy_setops_precedence_enbled = conf.setOpsPrecedenceEnforced

    val tokenStream = new CommonTokenStream(lexer)
    val parser = new SqlBaseParser(tokenStream)
        parser.addParseListener(PostProcessor)
    //    parser.removeErrorListeners()
    //    parser.addErrorListener(ParseErrorListener)
    //    parser.legacy_setops_precedence_enbled = conf.setOpsPrecedenceEnforced
    parser
  }

  def main(args: Array[String]): Unit = {

    val sparkSqlParser = new SparkSqlParser(SQLConf.get)

    val sqlText = "select count(*) from student"

    val logicalPlan = sparkSqlParser.parsePlan(sqlText);

    println(logicalPlan)
    println(logicalPlan.resolved)

    val context = parser(sqlText).singleStatement()
    println(context)

    //TODO 这里不应该是null啊？
    val logicalPlan1 = sparkSqlParser.astBuilder.visitSingleStatement(context)
    println(logicalPlan1)

  }

}
