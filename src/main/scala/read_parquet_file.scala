import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._
object readParquet{
    def main(args: Array[String]): Unit={
    // Create a SparkSession
    val spark = SparkSession.builder()
      .appName("Parquet File Read")
      .master("local[*]")  // Set the master URL to local mode
      .getOrCreate()
      val parquetDF: DataFrame = spark.read.parquet("/Workplace/test_file")
      parquetDF.show()

    }
}