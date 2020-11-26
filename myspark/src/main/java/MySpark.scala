import org.apache.spark.sql.SparkSession


object MySpark {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local")
      .config("spark.testing.memory", "2147480000")
      .getOrCreate()

    spark.read.schema("id STRING").csv()
    import spark.implicits._
    val frame = spark.read.json("myspark/src/main/resources/people.json")

    frame.createTempView("person")

    val sql = "select * from person"
    val logicalPlan = spark.sessionState.sqlParser.parsePlan(sql)

    spark.sessionState.analyzer
    println(logicalPlan)
    println(logicalPlan.resolved)
    spark.sql(sql).show()
  }

  def persons()={
    Seq(Person(1,"a"),Person(2,"b"))
  }
}


case class Person(id: Int,name:String)