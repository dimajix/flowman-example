# Flowman Example Project

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This is a trivial example project for [Flowman](https://flowman.io). It should serve as a template for creating your
own projects. The project is split up into three Maven modules:

* `plugin` - This contains the source code and unittests for a simple Flowman plugin.
* `spec` - This contains the Flowman project. You can find the flow in the directory
  `src/main/resources/flows`. The YAML files will still be processed by the Maven resource plugin, which allows
  additional variable substitution as part of the build process. For example, you can set the Flowman project version
  to the Maven project version.
* `dist` - Finally the dist module builds a full distribution of your project including all Flowman executables.  


## Building

With Flowman 0.19.0, prebuilt artifacts are available via Maven Central. Therefore, you do not need to build Flowman
yourself anymore. Instead, you can move on and simply build the example project via

    `mvn clean install`

Note that some unittests will read data from AWS S3, which in turn requires network access to S3. If the proxy
environment variables `http_proxy` is set, it will be used automatically for accessing S3 via the proxy. Of course
you can also build the example project and skip test exeution via

    `mvn clean install -DskipTests`


## Installing

Once you have built the example, you will find a file `flowman-example-dist-x.y.z-bin.tar.gz` in the `dist/target` 
directory. That file contains the whole application including both Flowman and the example. In order to install
the application, you simply need to unpack it at a location of your choice.

You also have to download and install [Apache Spark](https://spark.apache.org) with the same version as your
Flowman build (which would be 3.1.2 as of this writing).


## Running

When you finally want to run the application, you need to navigate to the directory where you unpacked the dist
archive. Then run

```shell
export SPARK_HOME=/your/spark/directory

bin/flowshell -f flows/weather 
```

## Using Docker

The repository will also build a Docker image as an alternative to the `tar.gz` dist file. You can start the Docker
image via

```shell
docker run --rm -ti dimajix/flowman-example:0.20.0

flowshell -f /opt/flowman/flows/weather
```


## Misc

### Using different Spark/Hadoop version

The example will use the Flowman default Hadoop/Spark version. Since version 0.19.0, Flowman provides several 
prebuilt versions for different Hadoop/Spark environments, which are available as different version numbers. You
can simply replace the version number in the `parent` section of `pom.xml` by one of the prebuilt versions:

* x.y.z-oss-spark2.4-hadoop2.6
* x.y.z-oss-spark2.4-hadoop2.7
* x.y.z-oss-spark3.0-hadoop2.7
* x.y.z-oss-spark3.0-hadoop3.2
* x.y.z-oss-spark3.1-hadoop2.7
* x.y.z-oss-spark3.1-hadoop3.2
* x.y.z-oss-spark3.2-hadoop2.7
* x.y.z-oss-spark3.2-hadoop3.3
* x.y.z-cdh6-spark2.4-hadoop3.0-bin.tar.gz
* x.y.z-cdp7-spark2.4-hadoop3.1-bin.tar.gz
