/*************
DJ Poulin
Data Structures - Homework 4 - Sorting
10/9/20
***************/
import java.util.Random;
import java.util.Scanner;

class Sort{


  public int[] bubbleSort(int[] arr ){
    //Sorts arrays via the bubble sort method we discussed in class

      int size = arr.length;
      int numsorted = 0;
      int temp = 0;
      int comparisonsBubble = 0;
      int assignmentsBubble = 0;

      for(int i = 1; i < size; i++){
        for (int j = 1; j < (size - numsorted); j++){
          comparisonsBubble++;

          // if the number on the left arr[j-1] is greater than the
          // number on the right arr[j], switch the two
          if(arr[j-1] > arr[j]){
            assignmentsBubble ++;
            temp = arr[j];
            assignmentsBubble ++;
            arr[j] = arr[j-1];
            assignmentsBubble ++;
            arr[j-1] = temp;
          }
        }
        // For each pass of the loop, at least one more number is sorted
        numsorted++;
      }
  int[] returnBubbles = new int[]{comparisonsBubble,assignmentsBubble};
  return returnBubbles;
  }

  public int[] selectionSort(int[] arr){
    //Sorts arrays via the selection sort method we discussed in class

    int pivot = arr.length - 1;
    int maxvalue = arr[pivot];
    int maxindex = pivot;
    int comparisonsSelection = 0;
    int assignmentsSelection = 0;

    for(int i = 1; i < arr.length; i++){
      for (int j = 0; j < pivot; j++){


        // if the value at arr[j] is greater than our pivot
        // switch the value with the pivots value.
        comparisonsSelection++;
        if(arr[j] > maxvalue){
          assignmentsSelection++;
          maxindex = j;
          assignmentsSelection++;
          maxvalue = arr[j];
        }
      }
      if(pivot != maxindex){
        int temp = arr[pivot];
        assignmentsSelection++;
        arr[pivot] = maxvalue;
        assignmentsSelection++;
        arr[maxindex] = temp;
        assignmentsSelection++;
        assignmentsSelection++;
      }
      pivot--;
      maxindex = pivot;
      maxvalue = arr[pivot];
    }
    int[] returnSelection = new int[]{comparisonsSelection,assignmentsSelection};
    return returnSelection;
  }

  public int[] insertionSort(int[] arr){
    //Sorts arrays by removing an element from the nth spot, and putting it where
    //it belongs among the elements in the 1st to (n-1)th spot.

    int comparisonsInsertion = 0;
    int assignmentsInsertion = 0;

    for(int i = 1; i <arr.length; i++){
      int j = i;

      //If the number we are currently sorting is greater than a number in the array,
      //we put the number we are sorting in front of that number in the array and
      //shift the other elements of the array up
      if(arr[i]<arr[i-1]){
        while(j >0 && arr[i]<arr[j-1]){
          comparisonsInsertion++;
          j--;
        }
        int temp1 = arr[i];
        assignmentsInsertion++;

        //here, we shif the other numbers up, using temp1 to store the number
        //we are sorting
        for(int shiftingIndex = i; shiftingIndex > j; shiftingIndex--){
          comparisonsInsertion++;
          assignmentsInsertion++;
          arr[shiftingIndex] = arr[shiftingIndex-1];
        }
        arr[j] = temp1;
      }
    }
    int[] returnInsertion = new int[]{comparisonsInsertion,assignmentsInsertion};
    return returnInsertion;
  }

  public void selectionSort2D(int[][] arr2d){
    //Uses selection sort to sort a 2d matrix.
    //We do this by continuously sorting the rows and columns, until selectionSort()
    //returns an assignment count of 0 for both the rows and columns.
    int sortCounter = 1;
    while(sortCounter>0){
      sortCounter = 0;
      for(int col = 0; col < arr2d.length; col++){
        int[] CandA1 = selectionSort(arr2d[col]);
        sortCounter = sortCounter + CandA1[1];
      }
      for(int row = 0; row <arr2d[0].length; row++){
        int[] currentRow = new int[arr2d.length];
        for(int col = 0; col < arr2d.length; col++){
          currentRow[col] = arr2d[col][row];
        }
        int[] CandA2 = selectionSort(currentRow);
        sortCounter = sortCounter + CandA2[1];
        for(int col = 0; col < arr2d.length; col++){
          arr2d[col][row] = currentRow[col];
        }
      }
    }
  }

  int[] randomArray(int length, int max){
    //Creates a random array of length length and with max
    //value max.
    Random ran = new Random();
    int[] arr = new int[length];
    for(int i=0; i<length;i++){
      arr[i] = ran.nextInt(max+1);
    }
    return arr;
  }

  int[][] randomArray2d(int length, int height, int max){
    //Creates a 2d array by creating an empty array and filling it with
    //arrays created by randomArray()
    int[][] arr2d = new int[length][];
    for(int i = 0; i<length;i++){
      arr2d[i] = randomArray(height, max);
    }
    return arr2d;
  }
}




class Homework4{
  public static void main(String[] args){
    //Our main method. Takes in user inputs and creates/sorts arrays accordingly.
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter an array length: ");
    int length1 = Integer.valueOf(sc.next());
    System.out.println("Enter the maximum value: ");
    int max1 = Integer.valueOf(sc.next());


    Sort s = new Sort();
    int[] arr = s.randomArray(length1, max1);


    System.out.printf("Unsorted:\n");
    for(int i = 0; i < arr.length; i++){
      System.out.printf("%d ",arr[i]);
    }
    System.out.println();

    int[] arr1 = arr.clone();
    int[] insertionCandA = s.insertionSort(arr1);
    System.out.printf("Insertion:\n");
    for(int i = 0; i < arr.length; i++){
      System.out.printf("%d ",arr1[i]);
    }
    System.out.println();


    System.out.printf("Selection:\n");
    int[] arr2 = arr.clone();
    int[] selectionCandA = s.selectionSort(arr2);
    for(int i = 0; i < arr.length; i++){
      System.out.printf("%d ",arr2[i]);
    }
    System.out.println();

    System.out.printf("Bubble:\n");
    int[] arr3 = arr.clone();
    int[] bubbleCandA = s.bubbleSort(arr3);
    for(int i = 0; i < arr.length; i++){
      System.out.printf("%d ",arr3[i]);
    }
    System.out.printf("\n\n");

    System.out.printf("\t\tComparisons\tAssignments\n");
    System.out.printf("Insertion:\t%d\t\t%d\n",insertionCandA[0], insertionCandA[1]);
    System.out.printf("Selection:\t%d\t\t%d\n",selectionCandA[0], selectionCandA[1]);
    System.out.printf("Bubble   :\t%d\t\t%d\n",bubbleCandA[0], bubbleCandA[1]);



    //Part 2
    System.out.println("Enter an array height: ");
    int height = Integer.valueOf(sc.next());
    System.out.println("Enter an array width: ");
    int width = Integer.valueOf(sc.next());
    System.out.println("Enter the maximum value: ");
    int max2 = Integer.valueOf(sc.next());

    System.out.printf("\nUnsorted:\n");
    int[][] arr2d = s.randomArray2d(width,height,max2);
    for(int col= 0; col < arr2d.length; col++){
      System.out.println();
      for(int row = 0; row < arr2d[0].length; row++){
        System.out.printf("%d\t", arr2d[col][row]);
      }
    }
    s.selectionSort2D(arr2d);
    System.out.printf("\nSorted:\n");

    for(int col= 0; col < arr2d.length; col++){
      System.out.println();
      for(int row = 0; row < arr2d[0].length; row++){
        System.out.printf("%d\t", arr2d[col][row]);
      }
    }



  }
}
