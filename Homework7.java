/********************
DJ Poulin
Data Structures
HW 7: CPU Scheduling
*********************/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



class Process{
  //Our process class. Simply holds the process' name, CPU time, priority, and PID
  String name;
  int CPUtime;
  int priority;
  String PID;

  Process(String[] line, int counter){
    //Constructor. Takes in an array of strings and
    //assigns attributes accordingly
    name = line[0];
    CPUtime = Integer.parseInt(line[1]);
    if(line.length == 3){
      priority = Integer.parseInt(line[2]);
    }
    else{
      priority = 1000;
    }

    if(counter < 10){
      PID = "00" + String.valueOf(counter);
    }
    else if(counter<100){
      PID = "0" + String.valueOf(counter);
    }
    else{
      PID = String.valueOf(counter);
    }
  }
}



class PriorityQueue{
  //Our priority queue class. While it does not extend a linked list, it
  //is designed based off of the linked list code we wrote in class

  public class Node{
    //our node class. Holds a process, Node next and Node prev

    Process data;
    Node next;
    Node prev;

    public Node(Process d){
      //constructor. Assigns the inputted process to our node and sets prev to null and next to null
      data = d;
      prev = null;
      next = null;
    }
  }

  Node head;
  Node tail;
  private int size;

  public PriorityQueue(){
    //Constructor. Creates an empty priority queue

    head = null;
    tail = null;
    size = 0;
  }

  public void add(Process d){
    //Adds a process into our priority list by assigning it to a node and
    //inserting the node into our list based on priority. If there is no priority,
    //we put it at the end of the list
    size++;

    Node newnode = new Node(d);

    Node curr = head;

    // list is empty
    if (head == null){
      head = newnode;
      tail = newnode;
    }
    else{
      if(newnode.data.priority == 1000){
        tail.next = newnode;
        newnode.prev = tail;
        tail = newnode;
      }
      else if(newnode.data.priority < head.data.priority){
        newnode.next = head;
        head.prev = newnode;
        head = newnode;
      }
      else{
        while(newnode.data.priority >= curr.data.priority && curr.next != null){
          curr = curr.next;
        }
        if(newnode.data.priority < curr.data.priority){
          newnode.next = curr;
          newnode.prev = curr.prev;
          curr.prev.next = newnode;
          curr.prev = newnode;
        }
        else{
          curr.next = newnode;
          newnode.prev = curr;
          tail = newnode;
        }
      }
    }
  }

  public void printQueue(){
    //Prints the queue with formatting based on the example output from
    //the assignment page

    Node curr = head;

    while(curr != null){
      System.out.printf("\nPID: %s|\t\t%s |\t%d| Priority: %d",curr.data.PID,curr.data.name,curr.data.CPUtime,curr.data.priority);
      curr = curr.next;
    }
  }

  public Process remove(){
    //removes the first item from our queue
    size--;
    Node curr = head;

    if(head != null && head.next != null){
      head = curr.next;
      head.prev = null;
    }
    else if(head != null && head.next == null){
      head = null;
    }
    if(curr != null){
      return curr.data;
    }
    else{
      return null;
    }
  }

  public int size(){
    //Returns the size our our queue
    return size;
  }

  public void fullDequeue(){
    //Dequeues our list using two CPUs. This function basically
    //does what the second part of the assignment asks for
    int cpu1Timer = 0;
    int cpu2Timer = 0;

    System.out.printf("\nExecuting Jobs.\n");
    Node curr = head;

    while(curr != null){
      if(cpu1Timer <= cpu2Timer){
        cpu1Timer += curr.data.CPUtime;
        System.out.printf("\nCPU1: Processing PID: %s|\t\t%s |\t%d|",curr.data.PID,curr.data.name,curr.data.CPUtime);
      }
      else if(cpu1Timer > cpu2Timer){
        cpu2Timer += curr.data.CPUtime;
        System.out.printf("\nCPU2: Processing PID: %s|\t\t%s |\t%d|",curr.data.PID,curr.data.name,curr.data.CPUtime);
      }
      curr = curr.next;
      remove();
      // if(cpu1Timer == cpu2Timer){
      //   cpu1Timer = ;
      //   cpu2Timer = 0;
      //
      // }
      // else if(cpu1Timer < cpu2Timer){
      //   cpu2Timer = cpu2Timer - cpu1Timer;
      // }
    }
    System.out.printf("\nFinished.");
  }
}



class Homework7{
  //our main class
  public static void main(String[] args){
    //our main function. Does what the homeworks asks us to do

    PriorityQueue pq = new PriorityQueue();
    int counter = -1;

    try{
      File file = new File("Processes.txt");
      Scanner sc = new Scanner(file);

      while(sc.hasNextLine()){
        counter++;
        String line = sc.nextLine();
        Process pro = new Process(line.split(","), counter);
        pq.add(pro);
      }
      pq.printQueue();
      pq.fullDequeue();
    }
    catch(FileNotFoundException e){
      System.out.println("Error. Cannot find file");
    }
  }
}
