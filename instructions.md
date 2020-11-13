# BASF QA API Automation Code Challenge

Execution Instructions
----------------------
For running your tests the only thing you should do is downloading the test project from the provided repo.

Then you should be able to run the automated tests just by typing from the directory you downloaded the tests:

* Linux/Mac:

    ./gradlew clean test aggregate (-Dcucumber.options="--tags @tag")

* Windows:

    gradlew.bat clean test aggregate (-Dcucumber.options="--tags @tag")"

(-Dcucumber.options="--tags @tag") is optional and will allow you to run a subset of tests. If not specified all the tests will be run

Available tags are:
* @positive
* @negative

After finishing, you should be able to open the report file and analyse the results

./target/site/serenity/index.html

Commannd line examples
----------------------
./gradlew clean test aggregate 

./gradlew clean test aggregate -Dcucumber.options="--tags @negative"

