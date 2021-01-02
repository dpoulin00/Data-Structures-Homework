/***************
DJ Poulin
Data Structures - HW 5
10/16/20

The purpose of this homework was to teach us how to use the extends keyword. We were tasked with implementing a generic shape class, as well as five other specific shape
classes which each extended the generic shape class. When program is run, the user is asked for a type of shape, as well as the length of each side. The program then
returns the area and perimeter of the shape. Then, the program asks for how many of each shape to create as well as the max shape size. The program prints a list of these
shapes and then sorts them by area.
****************/
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;
import java.util.Vector;

class Shape{
  //Our shape class, which we will extend to create our specific shape classes
  //We initialize all the variables we will need for every shape here
  public double area;
  public double perimeter;
  public String shapeType;
  //Triangle/rectangle
  public double width;
  public double height;
  //Circle
  public double diameter;
  //Square
  public double length;
  //trapezoid
  public double bottom;
  public double top;
  public double side;




  static void sort(Vector<Shape> arr){
    //Sorts a vector of shapes using bubble sort
    int size = arr.size();
    int numsorted = 0;
    Shape temp;

    for(int i = 1; i < size; i++){

      for (int j = 1; j < (size - numsorted); j++){

        // if the number on the left arr[j-1] is greater than the
        // number on the right arr[j], switch the two
        if(arr.get(j-1).area > arr.get(j).area){
          temp = arr.get(j);
          arr.remove(j);
          arr.add(j,arr.get(j-1));
          arr.remove(j-1);
          arr.add(j-1,temp);
        }
      }
      // For each pass of the loop, at least one more number is sorted
      numsorted++;
    }
    System.out.printf("\n\n\nPart 3 \nShapes sorted by area.");
    for(int i = 0; i < arr.size();i++){
      System.out.printf("\n%s: \t a: %f    p: %f",arr.get(i).shapeType, arr.get(i).area, arr.get(i).perimeter);
    }
  }
}


class Triangle extends Shape{
  //our triangle class

  Triangle(double width1, double height1){
    //construct our triangle, taking in width and height and calculating area and perimeter
    width = width1;
    height = height1;
    shapeType = "Triangle";
    area = 0.5 * width * height;
    double hypotenuse = Math.sqrt(width*width + height*height);
    perimeter = width + height + hypotenuse;
  }
}

class Circle extends Shape{
  //our circle class


  Circle(double diameter1){
    //constructs our circle, takes in a diameter and calculates area and perimeter
    diameter = diameter1;
    shapeType = "Circle";
    area = 3.14 * (0.5 * diameter) * (0.5 * diameter);
    perimeter = 3.14 * diameter;
  }
}

class Square extends Shape{
  //our square class


  Square(double length1){
    //Constructs our square. Takes in length and calculates area and perimeter
    length = length1;
    shapeType = "Square";
    area = length * length;
    perimeter = length * 4;
  }

}

class Rectangle extends Shape{
  //our rectangle class

  Rectangle(double width1, double height1){
    //constructs our rectangle, takes in width and height and calculates area and perimeter
    width = width1;
    height = height1;
    shapeType = "Rectangle";
    area = width * height;
    perimeter = (2 * width) + (2 * height);
  }

}

class Trapezoid extends Shape{
  //our trapezoid class

  Trapezoid(double bottom1, double top1, double height1){
    //constructs our trapezoid, takines in bottom, top and side and calculates area and perimeter.

    bottom = bottom1;
    top = top1;
    height = height1;
    shapeType = "Trapezoid";
    area = (height * (top + bottom))/2;
    perimeter = 2 * height + top + bottom;
  }
}

class Homework5{
  //our main class. Contains methods for part 1, part 2, and part 3

  public void Part1(){
    //Handles part 1 of this homework
    //PART 1
    Scanner sc = new Scanner(System.in);
    int shapeNum;

    do{
      System.out.printf("\nPart 1\nSelect a shape\n1: Triangle\n2: Rectangle\n3: Square\n4: Circle\n5: Trapezoid\nEnter a value:");
      shapeNum = sc.nextInt();
      if(shapeNum < 0 || shapeNum >= 6){
        System.out.println("Invalid Selection. Please try again: ");
      }
    }while(shapeNum < 0 || shapeNum >= 6);


    if(shapeNum == 1){
      System.out.println("Enter the base of the triangle: ");
      int base = sc.nextInt();
      System.out.println("Enter the height of the triangle: ");
      int height = sc.nextInt();
      Triangle tri = new Triangle(base, height);
      System.out.printf("\nTriangle: Area = %f, Perimeter = %f", tri.area, tri.perimeter);
    }

    if(shapeNum == 2){
      System.out.println("Enter the base of the rectangle: ");
      int base = sc.nextInt();
      System.out.println("Enter the height of the rectangle: ");
      int height = sc.nextInt();
      Rectangle rect = new Rectangle(base, height);
      System.out.printf("\nRectangle: Area = %f, Perimeter = %f", rect.area, rect.perimeter);
    }

    if(shapeNum == 3){
      System.out.println("Enter side length of the square: ");
      int sideLength = sc.nextInt();
      Square squ = new Square(sideLength);
      System.out.printf("\nSquare: Area = %f, Perimeter = %f", squ.area, squ.perimeter);
    }


    if(shapeNum == 4){
      System.out.println("Enter diameter of the circle: ");
      int diameter = sc.nextInt();
      Circle cir = new Circle(diameter);
      System.out.printf("\nCircle: Area = %f, Perimeter = %f", cir.area, cir.perimeter);
    }

    if(shapeNum == 5){
      System.out.println("Enter the top base length of the trapezoid: ");
      int topBase = sc.nextInt();
      System.out.println("Enter the bottom base length of the trapezoid: ");
      int bottomBase = sc.nextInt();
      System.out.println("Enter the height of the trapezoid: ");
      int height = sc.nextInt();
      Trapezoid trap = new Trapezoid(bottomBase, topBase, height);
      System.out.printf("\nTrapezoid: Area = %f, Perimeter = %f", trap.area, trap.perimeter);
    }
  }


  public Vector<Shape> Part2(){
    //Handles part 2 of the homework
    double totalArea = 0;
    double totalPerimeter = 0;


    Random ran = new Random();
    Scanner sc = new Scanner(System.in);
    System.out.printf("\nEnter the number of shapes to create for shape.\n");

    System.out.println("Triangle: ");
    double numOfTri = sc.nextDouble();

    System.out.println("Rectangle: ");
    int numOfRec = sc.nextInt();

    System.out.println("Square: ");
    int numOfSqu = sc.nextInt();

    System.out.println("Circle: ");
    int numOfCir = sc.nextInt();

    System.out.println("Trapezoid: ");
    int numOfTra = sc.nextInt();

    Vector<Shape> shapes = new Vector<Shape>();

    System.out.println("Enter a max side length: ");
    int maxSideLength = sc.nextInt();

    for(int i = 0; i < numOfTri; i++){
      shapes.add(new Triangle(ran.nextInt(maxSideLength)+1, ran.nextInt(maxSideLength)+1));
    }

    for(int i = 0; i < numOfRec; i++){
      shapes.add(new Rectangle(ran.nextInt(maxSideLength)+1, ran.nextInt(maxSideLength)+1));
    }

    for(int i = 0; i < numOfSqu; i++){
      shapes.add(new Square(ran.nextInt(maxSideLength)+1));
    }

    for(int i = 0; i < numOfCir; i++){
      shapes.add(new Circle(ran.nextInt(maxSideLength)+1));
    }

    for(int i = 0; i < numOfTra; i++){
      shapes.add(new Trapezoid(ran.nextInt(maxSideLength)+1, ran.nextInt(maxSideLength)+1, ran.nextInt(maxSideLength)+1));
    }

    System.out.println("The following shapes were created.");
    for(int i = 0; i< shapes.size(); i++){

      if(shapes.get(i).shapeType.equals("Triangle")){
        Shape currentShape = shapes.get(i);
        System.out.printf("\n%s: \tb: %f,  h: %f  a: %f,  p: %f",currentShape.shapeType, currentShape.width, currentShape.height,currentShape.area,currentShape.perimeter);
      }
      if(shapes.get(i).shapeType.equals("Rectangle")){
        Shape currentShape = shapes.get(i);
        System.out.printf("\n%s: \tw: %f,  h: %f  a: %f,  p: %f",currentShape.shapeType, currentShape.width, currentShape.height,currentShape.area,currentShape.perimeter);
      }
      if(shapes.get(i).shapeType.equals("Square")){
        Shape currentShape = shapes.get(i);
        System.out.printf("\n%s: \tw: %f,  a: %f,  p: %f",currentShape.shapeType, currentShape.length, currentShape.area,currentShape.perimeter);
      }
      if(shapes.get(i).shapeType.equals("Circle")){
        Shape currentShape = shapes.get(i);
        System.out.printf("\n%s: \td: %f,  a: %f,  p: %f",currentShape.shapeType, currentShape.diameter, currentShape.area,currentShape.perimeter);
      }
      if(shapes.get(i).shapeType.equals("Trapezoid")){
        Shape currentShape = shapes.get(i);
        System.out.printf("\n%s: \tb1: %f,  b2: %f,  h:  %f,  a: %f,  p: %f",currentShape.shapeType, currentShape.bottom, currentShape.top, currentShape.side, currentShape.area,currentShape.perimeter);
      }


      totalArea += shapes.get(i).area;
      totalPerimeter += shapes.get(i).perimeter;
    }
    System.out.printf("\nTotal Area: %f\nTotal Perimeter: %f",totalArea,totalPerimeter);
    return shapes;


  }

  public void Part3(Vector<Shape> shapes){
    //Handles part 3 of the homework
    Shape.sort(shapes);
  }




  public static void main(String[] args){
    //our main method. Goes through parts 1, 2, and 3 of the homework assignment.

    Homework5 hw = new Homework5();
    hw.Part1();
    Vector<Shape> shapes = hw.Part2();
    hw.Part3(shapes);




  }
}
