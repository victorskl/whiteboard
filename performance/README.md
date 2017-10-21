Performance Test
================

- Download and extract the Apache JMeter then `bash bin/jmeter.sh` to start GUI.
- Load the test plan from `./jmeter/[whiteboardlms-dev].jmx` 
- To run the load test, use the command line as follow:
```
jmeter -n -t [jmx file] -l [results file] -e -o [Path to output folder]
```


Static Analysis
===============

Before running any of the following static analysis tools, make sure to run build the project.

```
mvn clean package
```

*Note that you can skip to Maven Plugins section for PMD, FindBugs, Checkstyle.*


PMD
---

- https://pmd.github.io/
- Download and extract the PMD and add this into your system PATH environment variable

```
pmd -d ../src/main/java -f html -R java-basic,java-design,java-unusedcode,java-typeresolution,java-imports -l java > pmdout.html
```

FindBugs
---

- http://findbugs.sourceforge.net/
- Download and extract the FindBugs then `bash bin/findbugs` to start GUI.
- `File > New Project` as shown in [findbugs_newproject.png](findbugs_newproject.png) then Analyze.
- More, [check manual](http://findbugs.sourceforge.net/manual/index.html).


Checkstyle
---

- https://github.com/checkstyle/checkstyle
- Download the Checkstyle `checkstyle-8.3-all.jar`
- More at; http://checkstyle.sourceforge.net/cmdline.html

```
java -jar checkstyle-8.3-all.jar -c /google_checks.xml ../src/main/java > checkstyle_output.txt

java -jar checkstyle-8.3-all.jar -c /google_checks.xml -f xml -o checkstyle_output.xml ../src/main/java

java -cp checkstyle-8.3-all.jar com.puppycrawl.tools.checkstyle.gui.Main ../src/main/java/whiteboard/ui/AnnouncementController.java
```

Maven Plugins
---

- However, it is easier with Maven plugins for these tools.

**PMD**
- https://maven.apache.org/plugins/maven-pmd-plugin/index.html
- https://maven.apache.org/components/plugins/maven-pmd-plugin/index.html

**FindBugs**
- https://gleclaire.github.io/findbugs-maven-plugin/index.html
- http://www.baeldung.com/intro-to-findbugs

**Checkstyle**
- https://maven.apache.org/plugins/maven-checkstyle-plugin/index.html

**JavaNCSS**
- http://www.mojohaus.org/javancss-maven-plugin/index.html
- https://github.com/codehaus/javancss

```
mvn checkstyle:checkstyle
mvn findbugs:findbugs
mvn pmd:pmd
mvn javancss:report
```

- Finally, run the Maven `site` goal to generate the reports.

```
mvn clean package site
```

- **NOTE**: FindBugs Maven plugin [requires package goal](https://stackoverflow.com/questions/19796974/maven-findbugs-not-producing-reports-in-site-lifecycle)

- The Maven site will be generated into `target/site`. The example of the current snapshot generate can be browsed in [site folder](site).


CLOC
---

- https://github.com/AlDanial/cloc
- Install `cloc`. On macOS, `brew install cloc`

```
cloc --by-file --by-percent cmb --ignore-whitespace ../src/main/java 

cloc --by-file --by-percent cmb --ignore-whitespace ../src/main/java > cloc_output.txt
```


CKJM
---

- First, download and setup [Apache Ant](https://ant.apache.org/). On macOS, `brew install ant`
- https://www.spinellis.gr/sw/ckjm/
- Download and extract `ckjm-1.9.zip`
- Modify Apache Ant `build.xml` script to reflect `ckjm-1.9.zip` extracted directory for `${CKJM_DIR}` variable.

```
ant ckjm
```

- Refer CKJM Metric Descriptions: 
    https://www.spinellis.gr/sw/ckjm/doc/metric.html