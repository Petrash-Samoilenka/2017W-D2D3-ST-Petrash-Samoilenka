package ts.task1;

public class TaskLoader {
  public static volatile Object Object1 = new Object();
  public static volatile Object Object2 = new Object();
    
  public static void main(String[] args) {
    System.out.println("Troubleshooting: Task #1 - Simple deadlock");
    Thread1 T1 = new Thread1();
    Thread2 T2 = new Thread2();
    T1.start();
    T2.start();
  }  
      
  private static class Thread1 extends Thread {
       public void run() {
          synchronized (Object1) {
             System.out.println("Thread 1: locked Object #1...");
             
             try { Thread.sleep(10); }
             catch (InterruptedException e) {}
             System.out.println("Thread 1: Waiting for Object #2...");
             
             synchronized (Object2) {
                System.out.println("Thread 1: locked Objects #1 & #2...");
             }
          }
       }
    }
    private static class Thread2 extends Thread {
       public void run() {
          synchronized (Object2) {
             System.out.println("Thread 2: locked Object #2...");
             
             try { Thread.sleep(10); }
             catch (InterruptedException e) {}
             System.out.println("Thread 2: Waiting for Object #1...");
             
             synchronized (Object1) {
                System.out.println("Thread 2: locked Objects #1 & #2...");
             }
          }
       }
    } 

}
