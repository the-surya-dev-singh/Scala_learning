import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._

object MySQLGroupByExample {
  def main(args: Array[String]): Unit = {
    // Create a SparkSession
    val spark = SparkSession.builder()
      .appName("MySQL GroupBy Example")
      .master("local[*]")  // Set the master URL to local mode
      .getOrCreate()

    // Define the MySQL connection properties
    // define library dependency in build.sbt as:
    //libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.4.1"
    //libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.33"
    val mysqlURL = "jdbc:mysql://127.0.0.1:3306/fc_facts"
    val mysqlUser = "root"
    val mysqlPassword = "root"
    val mysqlTable = "fc_balance_facts"

    // Read the MySQL table into a DataFrame
    var df = spark.read
      .format("jdbc")
      .option("url", mysqlURL)
      .option("user", mysqlUser)
      .option("password", mysqlPassword)
      .option("dbtable", mysqlTable)
      .load()

    // Perform a group by operation using Spark SQL
  

    // Show the result
  
    df=df.groupBy("account_number").agg(count("*").alias("avv"))
    df.show()
  }
}
