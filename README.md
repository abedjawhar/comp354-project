

# COMP354 PA-PK

## Setup

There is currently no setup guide for windows, however the setup process should be similar.

### Mac OS
1. [Download JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (Note: This project will not work with Java 9)
2.	[Download gradle](https://gradle.org/install/)
3.	[Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=mac)
4.	[Download git](https://git-scm.com/download/mac)
5.	Download the project
    i. open terminal
    ii. cd into the directory where you would like to download the project
    iii. run `git clone https://github.com/hrachyahakobyan/comp354-project.git`
6.	import comp354-project into IntelliJ IDEA
    i. simply open IntelliJ and import the project
7.	Ensure gradle is setup in IntelliJ:
    i. In IntelliJ go to IDEA > preferences > plugins
    ii. Search for gradle and ensure that it is checked
8.	Install the lombok
    i.	In Intellij go to IDEA > preferences > plugins
    ii. Click on Browse repositories...
    iii. Search for Lombok Plugin
    iv. Click on Install plugin
    v. Restart IntelliJ IDEA
9.	Enable annotation processing in IntelliJ
IDEA > preferences > build, execution, deployment > compiler > annotation processes > enable annotation processing.
10.	Start the application
    i. Go to view > tool windows > gradle [(What to do if you do not see the gradle option)](#gradle-toolbar-setup)
    ii.	You should see a tool bar allowing you to execute gradle task (green icon).
    iii. A small window should pop up and say Gradle Project (comp-354 project should be in the first textbox) and command line.
    iv. In the 'command line' field type `build` and select okay
    v. repeat this process for `run`, the myMoney application should then start
11.	To login use the default username `admin` and password `admin`

#### What to do if you do not see the gradle toolbar in IntelliJ <a href="gradle-toolbar-setup"></a>
1.	Create a new gradle project  file > new > gradle > next.
2.	Write a group id / artifact id
3.	Make a project name and finish.
4.	Go to project structure and click ok.
5.	Wait until everything loads, and then close IntelliJ.
6.	Relaunch IntelliJ.
7.	Reimported the comp-354 project.
