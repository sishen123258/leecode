package extension

import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel


case class SampleData(upm_id: String, c1: String, c2: String, c3: String)

object MyExtension {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local")
      .config("spark.testing.memory", "2147480000")
      .withExtensions(sse=>{
//        sse.injectResolutionRule()
      })
      .getOrCreate()

    spark.catalog.cacheTable("a",StorageLevel.MEMORY_AND_DISK)

    spark.createDataFrame(Array(SampleData("u1","c1","c2","c3"))).createTempView("table_a")

    val sql =
      """
        |with t1 as (
        |    select upm_id,c1,c2,c3
        |    from table_a
        | )
        |

        |SELECT uuid() as row_key,upm_id,c1 FROM t1
        |union all
        |SELECT uuid() as row_key,upm_id,c2 FROM  t1
        |union all
        |SELECT uuid() as row_key,upm_id,c3 FROM t1

        |""".stripMargin


    val logicalPlan = spark.sessionState.sqlParser.parsePlan(sql)
    val execution = spark.sessionState.executePlan(logicalPlan)


    //    println(logicalPlan)

    println("execution====================")
    println(execution)

  }


}
