import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._

object EmployeesToParquet {
  def main(args: Array[String]): Unit = {
    // Create a SparkSession
    val spark = SparkSession.builder()
      .appName("MySQL GroupBy Example")
      .master("local[*]")  // Set the master URL to local mode
      .getOrCreate()

    // Define the MySQL connection properties
    val mysqlURL = "jdbc:mysql://127.0.0.1:3306/test"
    val mysqlUser = "root"
    val mysqlPassword = "root"
    //val mysqlTable = "fc_balance_facts"

    // Read the MySQL table into a DataFrame
    var employees = spark.read
      .format("jdbc")
      .option("url", mysqlURL)
      .option("user", mysqlUser)
      .option("password", mysqlPassword)
      .option("dbtable", "employees")
      .load()
      var departments = spark.read
      .format("jdbc")
      .option("url", mysqlURL)
      .option("user", mysqlUser)
      .option("password", mysqlPassword)
      .option("dbtable", "departments")
      .load()
      var managers = spark.read
      .format("jdbc")
      .option("url", mysqlURL)
      .option("user", mysqlUser)
      .option("password", mysqlPassword)
      .option("dbtable", "managers")
      .load()
      var salaries = spark.read
      .format("jdbc")
      .option("url", mysqlURL)
      .option("user", mysqlUser)
      .option("password", mysqlPassword)
      .option("dbtable", "salaries")
      .load()
       var address = spark.read
      .format("jdbc")
      .option("url", mysqlURL)
      .option("user", mysqlUser)
      .option("password", mysqlPassword)
      .option("dbtable", "addresses")
      .load()
    
      val joinedDF = employees
      .join(managers, employees("ManagerID") === managers("ManagerID"))
      .join(departments, employees("DepartmentID") === departments("DepartmentID"))
      .join(salaries, employees("EmployeeID") === salaries("EmployeeID"))
      .join(address, employees("EmployeeID") === address("EmployeeID"))
      //joinedDF.show()

      val selected=joinedDF.select(employees("EmployeeID"),employees("name"),departments("DepartmentName"),managers("ManagerName"),salaries("salary"),concat(address("Address"),lit(", "),address("PostalCode"),lit(",  "),address("City")).alias("Address"))
    selected.show(truncate=false)
    selected.write.mode("overwrite").parquet("/Workplace/test_file/")



  
    
  }
}
