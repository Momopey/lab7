rm -rf lab7/
git clone https://github.com/ucsd-cse15l-w23/lab7
cd lab7/
pwd
echo javac -cp lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
javac -cp lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar ListExamplesTests.java ListExamples.java
java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ListExamplesTests
sed '43s/index1/index2/' ListExamples.java > temp.java
cat temp.java > ListExamples.java      
rm temp.java
javac -cp lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ListExamplesTests
git add ListExamples.java;git commit -m "fixed";git push