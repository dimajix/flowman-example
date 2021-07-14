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
