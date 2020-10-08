import org.apache.spark.sql.SparkSession


object MySpark {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local")
      .config("spark.testing.memory", "2147480000")
      .getOrCreate()

    import spark.implicits._
    spark.read.json("myspark/src/main/resources/people.json").createTempView("person")

    spark.sql("select * from person").show()
  }

  def persons()={
    Seq(Person(1,"a"),Person(2,"b"))
  }
}


case class Person(id: Int,name:String)