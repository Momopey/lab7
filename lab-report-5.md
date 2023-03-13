# Lab report 5 - Done Quick, Done Quicker
One of my favorite lab activities was lab 4, the Code Done Quick challenge (Despite the fact that I fell off the tournament at the first braket. [Because my commands were rendered useless by an on-the-fly class-name change <Sabotage, I say!>]). I learned a lot about bash shortcuts, vim tricks, and more. Originally, I planned to put all the commands into a bash script, publish it on github, and use curl to run it. This plan was shut down. But my deciet and trickery knows no bounds, so in this lab report, I will be creating a program which makes it appear that I am typing commands by hand, into the terminal, while really running a premade script.

I created the following bash script (cheats.sh), which condensed every command I ran into a single script:
```
git clone https://github.com/Momopey/lab7
cd lab7/
javac -cp lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java; java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ListExamplesTests
vim -c ':43s/index1/index2/ | write | quit ' ListExamples.java
javac -cp lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ListExamplesTests
git add ListExamples.java;git commit -m "fixed";git push
```
