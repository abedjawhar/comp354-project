# COMP354 PA-PK

## Setup

There is currently no setup guide for windows, however the setup process should be similar.

### Mac OS
1. [Download JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (Note: This project will not work with Java 9)
2. [Download gradle](https://gradle.org/install/)
3. [Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=mac)
4. [Download git](https://git-scm.com/download/mac)
5. Download the project
   1. open terminal
   2. cd into the directory where you would like to download the project
   3. run `git clone https://github.com/hrachyahakobyan/comp354-project.git`
6. import comp354-project into IntelliJ IDEA
   1. simply open IntelliJ and import the project
7. Ensure gradle is setup in IntelliJ:
   1. In IntelliJ go to IDEA > preferences > plugins
   2. Search for gradle and ensure that it is checked
8. Install the lombok
   1. In Intellij go to IDEA > preferences > plugins
   2. Click on Browse repositories...
   3. Search for Lombok Plugin
   4. Click on Install plugin
   5. Restart IntelliJ IDEA
9. Enable annotation processing in IntelliJ
IDEA > preferences > build, execution, deployment > compiler > annotation processes > enable annotation processing.
10.	Start the application
    1. Go to view > tool windows > gradle 
       * If you do not see the gradle option see the guild below
    2. You should see a tool bar allowing you to execute gradle task (green icon).
    3. A small window should pop up and say Gradle Project (comp-354 project should be in the first textbox) and command line.
    4. In the 'command line' field type `build` and select okay
    5. repeat this process for `run`, the myMoney application should then start
11.	To login use the default username `admin` and password `admin`

### Windows
The steps for installation on Windows are pretty much the same ones for Mac, with
the following exceptions.

7-8. Same steps, except plugins can be found in File->Settings->Plugins

9. Look in File->Settings->build, execution, deployment > compiler > annotation processing

#### What to do if you do not see the gradle toolbar in IntelliJ
1. Create a new gradle project  file > new > gradle > next.
2. Write a group id / artifact id
3. Make a project name and finish.
4. Go to project structure and click ok.
5. Wait until everything loads, and then close IntelliJ.
6. Relaunch IntelliJ.
7. Reimported the comp-354 project.
