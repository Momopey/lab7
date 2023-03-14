rm -rf lab7/
git clone https://github.com/Momopey/lab7
cd lab7/
pwd
echo javac -cp lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
javac -cp lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar ListExamplesTests.java ListExamples.java
#java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ListExamplesTests
# sed '43s/index1/index2/' ListExamples.java > ListExamples.java
#javac -cp lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
#java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ListExamplesTests
#git add ListExamples.java;git commit -m "fixed";git push