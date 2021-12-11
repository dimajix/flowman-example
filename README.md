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

Before you can build this project, you actually need to build [Flowman](https://github.com/dimajix/flowman), so
you have all dependencies built.

Once you have Flowman artifacts in your `.m2` directory, You can simply build the example project via

    `mvn clean install`

Note that some unittests will read data from AWS S3, which in turn requires some valid AWS credentials in the
environment variables `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY`.


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
