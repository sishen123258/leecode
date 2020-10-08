package extension

import org.apache.spark.sql.SparkSession

object MySparkExtension {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local")
      .config("spark.testing.memory", "2147480000")
      .withExtensions(extensions=>{
//        extensions.injectOptimizerRule()
      })
      .getOrCreate()



  }
}
