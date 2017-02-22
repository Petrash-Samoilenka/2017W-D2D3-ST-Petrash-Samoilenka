##Module 1. Java: Troubleshooting

**Task 1 (Simple Deadlock)**: Implement java application to reproduce deadlock below.
Collect thread dumps.

![alt text](img/simple-deadlock.png "Simple Deadlock")

Results of the task:

 - Java source code
 - Collected thread dumps

**Task 5 (Memory Leak)**: Implement java (Java 1.6) application that reads the attached text data file line by line. The application should take 3 first characters (using substring() method) of every line and put all the 3-character lines to ArrayList.

 -	Analyze memory consumption.
 -	Collect heap dump (when the file has been read and ArrayList is fully populated but the application is still running).
 -	Identify memory leak if any.
 -	Fix memory leak if any.

Results of the task:

 -	Java source code (with/without memory leak)
 -	Leak suspect report (from Eclipse MAT)
 -	Your findings about the memory problem
