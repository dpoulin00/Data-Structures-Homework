/******************************
DJ Poulin
Data Structures
HW 6 - Doubly Linked List
10/30/2020

To run this file, use:
"javac Homework6.java"
"java Homework6 Cargo.txt Destinations.txt"

This assignment was designed to let us get familiar with linked lists. We were given two files, the first contained a set of data pertaining to train cars,
and the second contained data pertaining to destinations. This program reads in the first file and creates/organizes a doubly linked list of train cars.
It then reads in the second file and, if any train cars have a destination included in the second file, removes them.
*******************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class FreightCar{
  //Our FreightCar class. It holds a freightcar's idNumber, cargoType, and destination.
  //It has only two functions, a constructor, which takes in an int to make the
  //idNumber from as well as a string to get cargoType and destination from, and
  //a printFreightCar method, which prints out "idNumber:   destination   cargoType"

  public String idNumber;
  public String cargoType;
  public String destination;

  FreightCar(String line, int counter){
    //Constructor. Takes in an int to make the
    //idNumber from as well as a string to get cargoType and destination from

    String[] lineArray = line.split(",");
    destination = lineArray[0];
    cargoType = lineArray[1];
    if(counter < 10){
      idNumber = "00" + String.valueOf(counter);
    }
    else if(counter<100){
      idNumber = "0" + String.valueOf(counter);
    }
    else{
      idNumber = String.valueOf(counter);
    }
  }

  public void printFreightCar(){
    //prints out "idNumber:   destination   cargoType"
    if(destination.equals("Philadelphia")){
      System.out.printf("\n%s: %s\t%s",idNumber,destination,cargoType);
    }
    else{
      System.out.printf("\n%s: %s\t\t%s",idNumber,destination,cargoType);
    }
  }
}

class LinkedList{
  //LinkedList class that holds objects of the FreightCar data type

  public class Node{
    //Node class, which holds an object of the FreightCar class as data
    FreightCar data;
    Node next;
    Node prev;

    public Node(FreightCar d){
      //constructs a node from  a FreightCar
      data = d;
      prev = null;
      next = null;
    }
  }

  Node head;
  Node tail;

  public LinkedList(){
    //constructs our starting linked list, which is empty
    head = null;
    tail = null;
  }

  public void add(FreightCar d){
    //adds a FreightCar to our linked list. Groups them by destination, and
    //within that, by absentee type

    Node newnode = new Node(d);
    Node curr = head;

    // list is empty
    if (head == null){
      head = newnode;
      tail = newnode;
    }
    else if(head.next == null){
      head.next = newnode;
      newnode.prev = head;
      tail = newnode;
    }
    else{
      while(curr.next!= null && !curr.data.destination.equals(newnode.data.destination)){
        curr = curr.next;
      }
      if(curr.next == null){
        curr.next = newnode;
        newnode.prev = curr;
        tail = newnode;
      }
      else if(curr.data.destination.equals(newnode.data.destination)){
        while(curr.next!= null && curr.next.data.destination.equals(newnode.data.destination) && !curr.data.cargoType.equals(newnode.data.cargoType)){
          curr = curr.next;
        }
        if(curr.next == null){
          curr.next = newnode;
          newnode.prev = curr;
          tail = newnode;
        }
        else if(!curr.next.data.destination.equals(newnode.data.destination)){
          newnode.next = curr.next;
          newnode.prev = curr;
          curr.next = newnode;
          newnode.next.prev = newnode;
        }
        else if(curr.data.cargoType.equals(newnode.data.cargoType)){
          newnode.next = curr.next;
          newnode.prev = curr;
          curr.next.prev = newnode;
          curr.next = newnode;
        }
        else{
          System.out.println("There is an unaccounted for scenario");
        }
      }
    }

  }

  public void printList(){
    //prints our linked list by iterating through it and calling printFreightCar
    //on the freight car within each node

    Node curr = head;

    while(curr != null){
      curr.data.printFreightCar();
      curr = curr.next;
    }
  }

  public boolean remove(FreightCar val){
    //Checks to see if there is a FreightCar equal to val in the linked list.
    //If there is, removes the first one.

    Node curr = null;
    Node prev = null;

    // item is first in list
    if(head != null && head.data == val){

      //  if only one item in list
      if (head == tail){
        head = null;
        tail = null;
      }
      // list length is greater than 1
      else{
        head = head.next;
        head.prev = null;
      }
    }
    // could be in list, but not the first item
    else{

      curr = head.next;
      prev = head;

      // continue until found out or reach end of the list
      while (curr != null && curr.data != val){
        prev = curr;
        curr = curr.next;
      }
      // we haven't reached the end, found it
      if (curr != null){

        // check to see if last item of our list
        if (curr == tail){
          tail = prev;
          tail.next = null;
        }
        // found something not the last item
        // general case
        else{
          prev.next = curr.next;
          curr.next.prev = prev;
        }
      }
      else{
        // item not found
        return false;
      }
    }
    return true;
  }

  public void unload(String destination){
    //Removes all freightcars with destination destination. Does this
    //via calling remove.
    Node curr = head;
    Node temp = null;
    while(curr != null){
      temp = curr.next;
      if(curr.data.destination.equals(destination)){
        System.out.printf("Unloading %s: %s\t\t%s\n",curr.data.idNumber,curr.data.destination,curr.data.cargoType);
        this.remove(curr.data);
      }
      curr = temp;
    }
  }
}


class Homework6{
  //Our main class



  public static void main(String[] args){
    //Our main method. It expects "cargo.txt" (or a similarly formatted file)
    //as the first arg from the terminal, and "Destinations.txt" (or a similarly
    //formatted file) as the second. It then does the tasks required by the homework assignment.

    LinkedList ll = new LinkedList();

    try{
      File file = new File(args[0]);
      Scanner sc = new Scanner(file);
      int counter = -1;

      while(sc.hasNextLine()){
        counter++;
        String line = sc.nextLine();
        try{
          FreightCar fc = new FreightCar(line, counter);
          ll.add(fc);
        }
        catch(ArrayIndexOutOfBoundsException e){
          System.out.println("Line missing some data: ");
          System.out.println(line);
        }
      }

    }
    catch(FileNotFoundException e){
      System.out.println("File not found");
      System.exit(0);
    }
    ll.printList();



    //Part 2:   UNLOADING


    try{
      File file2 = new File(args[1]);
      Scanner sc2 = new Scanner(file2);
      System.out.printf("\n\n\n________________________________________________________________\nChoo choo! Train is departing!\n\n");

      while(sc2.hasNextLine()){
        String dest = sc2.nextLine();
        System.out.printf("\nNow Arriving at %s. Unloading cargo.\n", dest);
        ll.unload(dest);
        }
      }
      catch(FileNotFoundException e){
        System.out.printf("\n\nFile not found. Program now closing.");
        System.exit(0);
    }
    System.out.printf("\n\nWhat's left on the train?\n");
    ll.printList();
  }
}
