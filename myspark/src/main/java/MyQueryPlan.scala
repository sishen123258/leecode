import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.catalyst.expressions.{EmptyRow, Expression, Literal}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.unsafe.types.UTF8String


object MyQueryPlan {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local")
      .config("spark.testing.memory", "2147480000")
      .getOrCreate()



    //    val q = spark.range(10)
    spark.read.json("myspark/src/main/resources/people.json").createTempView("person")

    val q = spark.sql("select * from person")

    parseAna(spark)
    //    printPlan(q)
    val expression = Literal.create("Hello")
    val value = expression.eval(EmptyRow)
    val string = value.asInstanceOf[UTF8String].toString
    println(value)
    println(string)




  }

  private def parseAna(spark: SparkSession) = {

    val parser = spark.sessionState.sqlParser

    val plan: LogicalPlan = parser.parsePlan("select * from a")



  }

  private def printPlan(q: DataFrame) = {
    println("query plan output:", q.queryExecution.analyzed.output)
    println("withCachedData output:", q.queryExecution.withCachedData.output)
    println("optimizedPlan output:", q.queryExecution.optimizedPlan.output)
    println("sparkPlan output:", q.queryExecution.sparkPlan.output)
    println("executedPlan output:", q.queryExecution.executedPlan.output)


    println("analyzed schema:", q.queryExecution.analyzed.schema)
    println("query plan output toStructType:", q.queryExecution.analyzed.output.toStructType)
    println("analyzed:", q.queryExecution.analyzed)
    println("analyzed expressions:", q.queryExecution.analyzed.expressions)
    println("analyzed references:", q.queryExecution.analyzed.references)
    println("analyzed simpleString:", q.queryExecution.analyzed.simpleString)


  }
}
