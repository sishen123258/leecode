package extension

import org.apache.spark.sql.{SparkSession, SparkSessionExtensions}
import org.apache.spark.sql.catalyst.parser.ParserInterface

object MySparkExtension {

  def main(args: Array[String]): Unit = {

    // Type?
    type ParserBuilder = (SparkSession, ParserInterface) => ParserInterface
    type ExtensionsBuilder = SparkSessionExtensions => Unit

    // 在哪里new的？
    val parserBuilder: ParserBuilder = (_, parser) => new StrictParser(parser)
    val extBuilder: ExtensionsBuilder = { e => e.injectParser(parserBuilder)}


    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local")
      .config("spark.testing.memory", "2147480000")
//      .withExtensions(extBuilder)
      .withExtensions(extensions=>{


      })
      .getOrCreate()

    spark.read.json("myspark/src/main/resources/people.json").createTempView("person")

    spark.sql("select * from person").show()


  }
}
