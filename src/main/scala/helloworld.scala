import org.apache.spark.sql.SnappyContext
import org.apache.spark.sql.streaming.SnappyStreamingContext
import org.apache.spark.{SparkContext,SparkConf}
import org.apache.spark.streaming.{Seconds}
/**
 * Created by plamb on 2/13/16.
 */
object HelloSnappy {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf(true)
      .setAppName("snappy_test_sbt")
      .setMaster("local[4]")

    val sc = new SparkContext(conf)
    val snc = SnappyContext.getOrCreate(sc)
    val snsc = SnappyStreamingContext(snc, Seconds(1))

    snc.sql("DROP TABLE IF EXISTS webhits")

    //TODO: figure out rowconverter
    snsc.sql(
      "CREATE STREAM TABLE webhits (ip string, page string, time, bigint) "+
      "USING kafka_stream options (" +
      "storagelevel 'MEMORY_AND_DISK_SER_2', "+
      "rowConverter '', "+
      "zkQuorum 'localhost:2181', "+
      "groupId 'sparkFetcher', "+
      "topics 'streamTopic:01')"
    )

  }
}
