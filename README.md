# Test Automation Assignment

Test Automation Assignment on the opencart web shop. Automated tests created in order to fulfill assigned acceptance criteria points.

## Installation prerequisites

1. Install Java 11 (use https://www.techspot.com/downloads/downloadnow/5553/?evp=cc1002b8f24231deaf82043061a06561&file=6310 if no other better source is available)
2. Install chromedriver 96.0.4664.45 (https://chromedriver.storage.googleapis.com/index.html?path=96.0.4664.45/) in the default path C:\chromedriver

## Importing project

1. Install Eclipse IDE from https://www.eclipse.org/downloads/packages/release/2021-12/r/eclipse-ide-java-developers.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**NOTE:** Select Eclipse IDE for Java developers in the first popup window. Make sure that in the second window the
Java 11 + VM field is pointing to the installation path of Java 11, and select your desired installation folder.

1. Install git (https://git-scm.com/download/win). All default settings in the installation windows can be accepted.
2. Create a new folder where you would like to checkout the project (e.g. C:\assignment).
3. Open Git Bash and redirect to the folder created in Step 2 (use `pwd` to find out where you currently are and then redirect yourself to the folder from  Step 2 e.g. use cd .. to go one folder above and when you are at / type `cd c/assignment` to redirect to the folder from Step 3).
4. Type `git clone https://github.com/mreljanov/automationtask.git` to clone the necessary files from git
5. After the clone process is finished open Eclipse and open File->Import.
6. From the Maven folder in the first popup select **Existing Maven Projects**. Click on Next.
7. Click on Browse and select the automationtask inside the folder created in Step 2.
8. Make sure the checkbox is ticked next to */pom.xml* and click on Finish.
9. After the project is visible in Eclipse right click on the Acceptance.java file (under newly visible src/test/java-> automation directories) in Project Explorer and select **Run As->JUnit test**

## Running one test at a time

If you wish to run just one of the nine tests just got to the Acceptance.java file and comment out the ones you don't wish to execute (use *Ctrl+7*).

**NOTE:** Some tests have dependencies (e.g. *FileReturnTicket* depends on *FinishPurchase* being executed before it.).

## Running tests with a different user

In order to run the tests with a completely new(unregistered) user open *Include.java* file and change the *email* varaiable to an unregistered user on openchart(there is an hint in the file).

**NOTE:** If you plan on using an already registered user then the password and initialPassword variables inside the *Include.java* must be changed to match the old and new passwords. Also, *Register.java* will finish successfully but with a warning message in the console if run with an already existing user

## Running tests in headless mode

In order to run the tests in headless mode then lines 19-22 must be uncommmented in the *Base.java* file and line 23 commented. Save the changes to the file.
