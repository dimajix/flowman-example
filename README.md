# Flowman Example Project

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This is a trivial example project for [Flowman](https://flowman.io). Iz should serve as a template for creating your
own projects. The project is split up into three Maven modules:

* `plugin` - This contains the source code and unittests for a simple Flowman plugin.
* `spec` - This contains the Flowman project. You can find the flow in the directory
  `src/main/resources/flows`. The YAML files will still be processed by the Maven resource plugin, which allows
  additional variable substition as part of the build process. For example, you can set the Flowman project version
  to the Maven project version.
* `dist` - Finally the dist module builds a full distribution of your project including all Flowman executables.  
