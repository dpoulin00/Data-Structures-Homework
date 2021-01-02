/********************
DJ Poulin
Data Structures
Homework 7: CPU Scheduling
*********************/

class LinkedList{

  public class NodeInt{

    int data;
    NodeInt next;
    NodeInt prev;

    public NodeInt(int d){
      data = d;
      prev = null;
      next = null;
    }
  }

  NodeInt head;
  NodeInt tail;

  public LinkedList(){

    head = null;
    tail = null;
  }

  public void add(int d){

    NodeInt newnode = new NodeInt(d);

    // list is empty
    if (head == null){
      head = newnode;
      tail = newnode;
    }
    else{
      tail.next = newnode;
      newnode.prev = tail;
      tail = newnode;
    }
  }

  public void printList(){

    NodeInt curr = head;

    while(curr != null){
      System.out.println(curr.data);
      curr = curr.next;
    }
  }

  public boolean remove(int val){

    NodeInt curr = null;
    NodeInt prev = null;

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
}


class Process{
  String name;
  int CPUtime;
  int priority;

  Process(String[] line){
    name = line[0];
    CPUtime = Integer.parseInt(line[1]);
    if(line.length == 3){
      priority = Integer.parseInt(line[2]);
    }
  }
}


class PriorityQueue extends LinkedList{
  int size;

  public class Node{

    Process data;
    Node next;
    Node prev;

    public Node(Process d){
      data = d;
      prev = null;
      next = null;
    }
  }

  PriorityQueue(){
    size = 0;
  }

  public void add(Process d){

    Node newnode = new Node(d);

    // list is empty
    if (head == null){
      head = newnode;
      tail = newnode;
    }
    else{
      tail.next = newnode;
      newnode.prev = tail;
      tail = newnode;
    }
  }
}


class Homework7{
  public static void main(String[] args){
    String[] hi = new String[]{"hello","1","2"};
    Process process = new Process();
  }
}
