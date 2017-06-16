## Module 11. Backend: Memory managment and garbage collection

Design and implement code that will introduce:

**Task 1:** java.lang.OutOfMemoryError: Java heap space. Create big objects continuously and make them stay in memory. Do not use arrays or collections.

**Task 2:** java.lang.OutOfMemoryError: Metaspace. Load classes continuously and make them stay in memory.

**Task 3:** java.lang.StackOverflowError. Do not use recursive methods. Use the most appropriate JVM option for the early goal achievement. Heap structure should be: 3-Eden, 3-S0, 3-S1, 1m to thread stack.

Install MAT for eclipse. Review plugin features. Investigate leak suspects. Use VisualVM to observe how change memory spaces. Notice GC curve.