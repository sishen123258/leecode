package extension
import org.apache.spark.sql.catalyst.dsl.expressions._
import org.apache.spark.sql.catalyst.expressions.{Literal,Expression,EqualTo}
import org.apache.spark.sql.catalyst.plans.logical._
import org.apache.spark.sql.catalyst.dsl.plans._

object MyExpression {

  def main(args: Array[String]): Unit = {

    val addExpression = Literal(1) + Literal(1)

    println(addExpression)

    println('a.attr)
    println('a==='b)


    LocalRelation('key.int,'value.string)

    // unresolved function
    val f='f.function()
    print(f)

    val logicalPlan  = table("t1").select(f)

    println(logicalPlan)

    //spark.sessionState.analyzer.execute(logicalPlan)

    // unresolved attribute
    val value = $"a".expr
    println(value.deterministic)

  }
}
