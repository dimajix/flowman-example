package com.dimajix.flowman.example.spec

import java.io.File

import scala.language.postfixOps

import com.google.common.io.Resources
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import com.dimajix.flowman.execution.Lifecycle
import com.dimajix.flowman.testing.Runner
import com.dimajix.spark.testing.LocalTempDir


class TestExampleSpec extends AnyFlatSpec with Matchers with LocalTempDir {
    "The example flow spec" should "be executable" in {

        val proxyPattern = "([^:]+:\\/\\/)?([^:]*)(:(.*))?".r
        val proxy = Option(System.getenv("http_proxy"))
            .flatMap(p => proxyPattern.findFirstMatchIn(p))
        val proxyHost = proxy.flatMap(r => Option(r.group(2))).getOrElse("")
        val proxyPort = proxy.flatMap(r => Option(r.group(4))).getOrElse("-1")

        val runner = Runner.builder()
            .withProfile("test")
            .withProject(Resources.getResource("flows"))
            .withEnvironment("basedir", new File(tempDir, "weather").toString)
            .withConfig("spark.hadoop.fs.s3a.proxy.host",proxyHost)
            .withConfig("spark.hadoop.fs.s3a.proxy.port",proxyPort)
            .build()

        (2012 to 2014)
            .foreach(year => {
                runner.runJob("main", Lifecycle.BUILD, Map("year" -> year.toString)) should be (true)
            })
    }

    it should "provide test cases" in {
        val runner = Runner.builder()
            .withProject(Resources.getResource("flows"))
            .build()

        runner.runTests()
    }
}
