/***********************
DJ Poulin
Data Structures
Homework 10
*************************/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;

class Car{
  //Our car class. Holds the relevant attributes. Has a constructor method and a toString method
  String VINnumber;
  String Year;
  String VehicleType;
  String Manufacturer;
  int MonthAcquired;
  String MonthSold;

  public Car(String[] line){
    //our constructor method
    VINnumber = line[1];
    Year = line[2];
    Manufacturer = line[3];
    VehicleType = line[4];
    MonthAcquired = Integer.parseInt(line[0]);
  }
  public String toString(){
    //our toString method
    String car = VINnumber + " " + Year + " " + Manufacturer + " " + VehicleType;
    return car;
  }
}


class HashTable{
  //Our hash table class. Holds the Node class.
  //Holds attributes Table, Capacity, and Size
  //Has methods HashTable(), hashCode(), add(),
  //delete(), resize(), PrintHashTable(), and Month()


  //A Node class definition - see below for more instructions on this class.
  class Node{
    //Our node class. Stores a car and a boolean deleted. Has a constructor method, a delete() method,
    //and a isDeleted() method
      Car car;
      boolean deleted;

      public Node(Car car_in){
        //Constructor
        deleted = false;
        car = car_in;
      }

      public Car delete(){
        //Sets deleted to true
        deleted = true;
        return car;
      }

      public boolean isDeleted(){
        //returns the value of deleted
        return deleted;
      }

      public String toString(){
        //returns a string representing our car
        if(deleted == false){
          return car.toString();
        }
        else{
          return "deleted";
        }
      }
    }

  Node[] Table; //An array of Nodes - this is your hash table.
  int Capacity; //Capacity - the number of elements your array can hold
  int Size;//Size - the number of elements in your hash table

  public HashTable(){
    //a constructor for our hashtable. Starts with a capacity of 100
    Table = new Node[100];
    Capacity = 100;
    Size = 0;
  }
  //hashCode() - Method o convert your string to a hash value
  public long hashCode(String s){
    //returns a hashCode. We use VIN as our key and return a long,
    //which needs to be converted into a usable index
    long hash = 0;
    for(int i = 0; i<s.length(); i++){
        hash = hash * 31 + s.charAt(i);
    }
    return hash;
  }

  //add() - method to add items to your has table
  public void add(Car car){
    //Adds a car to our hashtable by storing it in a node
    Node newnode = new Node(car);
    long HashCode = hashCode(car.VINnumber);
    int HCode = (int) Math.abs(HashCode%Capacity);
    while(Table[HCode] != null && Table[HCode].deleted == false){
      HCode = HCode + 1;
      if(HCode >= Capacity){
        HCode = HCode - Capacity;
      }
    }
    if(Table[HCode] == null || Table[HCode].deleted == true){
      Table[HCode] = newnode;
      Size = Size + 1;
    }
  }

  //delete() - remove items from your hash table. This function should also return the number of indexes required to search in order to find and delete the object.
  public double delete(Car car){
    //Removes a car from our hashtable and returns the number of indices it took
    //to find that car in the list
    double indices = 1;
    long HashCode = hashCode(car.VINnumber);
    int HCode = (int) Math.abs(HashCode%Capacity);
    while(Table[HCode] != null && !Table[HCode].car.VINnumber.equals(car.VINnumber)){
      indices = indices + 1;
      HCode = HCode + 1;
      if(HCode >= Capacity){
        HCode = HCode - Capacity;
      }
    }
    if(Table[HCode] == null){
      System.out.println("why is there an empty spot");
    }
    if(Table[HCode].car.VINnumber.equals(car.VINnumber)){
      Table[HCode].deleted = true;
      Size = Size - 1;
    }
    return indices;
  }

  //resize() - Resize your hash table when there are no more available spaces
  public void resize(){
    //Doubles the capacity of our hashtable and readds all the elements
    Capacity = Capacity * 2;
    Car car1;
    int HCode;
    Node[] newTable = new Node[Capacity];
    Node[] oldTable = Table;
    Table = newTable;

    for(int i = 0; i < oldTable.length; i++){
      car1 = oldTable[i].car;
      add(car1);
      Size = Size-1;
    }
  }

  public void PrintHashTable(){
    //prints the cars currently in our hashtable
    int counter = 0;
    for(int i = 0; i < Capacity; i++){
      if(Table[i] != null && Table[i].deleted == false){
        System.out.printf("\n%d: %s",counter , Table[i]);
      }
      counter = counter + 1;
    }
  }

  public void Month(int month, String[] args){
    //Given a month, this function adds all cars that were acquired in
    //that month to our hashtable, and then removes those that were sold in that month
    int New = 0;
    int Sold = 0;
    double indices = 0;

    String[] line;
    Car currentCar;
    Car carSold;
    File Cars = new File(args[0]);
    File CarsSold = new File(args[1]);
    try{
      Scanner scAdd = new Scanner(Cars);
      Scanner scRemove = new Scanner(CarsSold);

      while(scAdd.hasNextLine()){
        line = scAdd.nextLine().split(" ");
        currentCar = new Car(line);
        if(currentCar.MonthAcquired == month){
          New = New + 1;
          if(Size == Capacity){
            resize();
          }
          add(currentCar);
        }
      }
      while(scRemove.hasNextLine()){
        line = scRemove.nextLine().split(" ");
        carSold = new Car(line);
        if(carSold.MonthAcquired == month){
          Sold = Sold + 1;
          indices = indices + delete(carSold);
        }
      }


      //PRINT STATEMENT
      double AvgIndices = indices/Sold;
      switch(month)
      {
        case 1:
          System.out.printf("\nJanuary Inventory");
          break;
        case 2:
          System.out.printf("\nFebruary Inventory");
          break;
        case 3:
          System.out.printf("\nMarch Inventory");
          break;
        case 4:
          System.out.printf("\nApril Inventory");
          break;
        case 5:
          System.out.printf("\nMay Inventory");
          break;
        case 6:
          System.out.printf("\nJune Inventory");
          break;
        case 7:
          System.out.printf("\nJuly Inventory");
          break;
        case 8:
          System.out.printf("\nAugust Inventory");
          break;
        case 9:
          System.out.printf("\nSeptember Inventory");
          break;
        case 10:
          System.out.printf("\nOctober Inventory");
          break;
        case 11:
          System.out.printf("\nNovember Inventory");
          break;
        case 12:
          System.out.printf("\nDecember Inventory");
          break;
      }
      System.out.printf("\nNew: %d, Sold: %d, Total: %d, Capacity: %d\nAvg num. of keys indexed for retreival: %f\n", New, Sold, Size, Capacity, AvgIndices);
    }
    catch(FileNotFoundException se){
      System.out.println("Couldn't open file");
    }
  }
}


class Homework10{
  //Our main class



  public static void main(String[] args){
    //Our main method. Creates a hash table and calls Month() for each month.
    //The reason I didn't use a for loop was due to how the month method
    //used to be constructed. Now, it would make more sense to use a for loop,
    //but since this works, there's no need to change it

    System.out.printf("\nWelcome to the CS 201 New/Used Car Dealership!\n");
    HashTable ht = new HashTable();
    ht.Month(1,args);
    ht.Month(2,args);
    ht.Month(3,args);
    ht.Month(4,args);
    ht.Month(5,args);
    ht.Month(6,args);
    ht.Month(7,args);
    ht.Month(8,args);
    ht.Month(9,args);
    ht.Month(10,args);
    ht.Month(11,args);
    ht.Month(12,args);
    Scanner sc_in = new Scanner(System.in);
    System.out.printf("\nPrint Inventory (y/n): \n");
    String yesno = sc_in.next();
    if(yesno.equals("y")){
      ht.PrintHashTable();
    }
  }
}
