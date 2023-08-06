import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._

object CsvGroupByExample {
  def main(args: Array[String]): Unit = {
    // Create a SparkSession
    val spark = SparkSession.builder()
      .appName("CSV GroupBy Example")
      .master("local[*]")  // Set the master URL to local mode
      .getOrCreate()

    // Read the CSV file into a DataFrame
    val csvFilePath = "/Workplace/test_files/test.csv"
    val df: DataFrame = spark.read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(csvFilePath)

    // Perform a group by operation using Spark SQL
    var resultDF = df.groupBy("Country").count()

    // Show the result
    resultDF=resultDF.filter(col("count")>1)
    
    resultDF.show()
  }
}
