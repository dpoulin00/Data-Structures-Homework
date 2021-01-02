/***********************
DJ Poulin
CS 201 HW 2 - Battleship
9/25/2020

This homework assignment required the implementation of the board game Battleship. Upon being run, a board is created, and ships are randomly placed.
Coordinates can then be inputted, and the game will tell the user whether that coordinate is a hit, a miss, or if it has already been guessed.
************************/

import java.util.Random;
import java.util.Scanner;

class Battleship{
  //Our battleship class. Contains methods Battleship(), BoardBuilder(), carrierPlacer(), battlShipPlacer(),
  //destroyerAndSubmarinePlacer(), patrolBoatPlacer(), shipPlacer(), PrintBoard(), guess(), and play().
  int attempts;
  int hits;
  String[][] playerBoard;
  String[][] gameBoard;
  String dash = "- ";
  String cross = "X ";
  String circle = "O ";

  Battleship(){
    //This is our constructor. It sets attempts and hits to 0,
    //and it prepares arrays of arrays for the board builder method.
    attempts = 0;
    hits = 0;
    playerBoard = new String[11][11];
    gameBoard = new String[11][11];
  }

  void BoardBuilder(){
    //This method build our board. First, it iterates through the 1st row and column,
    //giving them values of 1, 2, 3,... and A, B, C,..., respectively.
    //This method then assigns every other location on the boards the value of "- "
    gameBoard[0][0] = "";
    playerBoard[0][0] = "";
    for(int i=1;i<=10;i++){
      char b = (char) (i+64);
      String bStr = String.valueOf(b);
      gameBoard[0][i] = bStr;
      playerBoard[0][i] = bStr;
    }
    for(int i=0;i<10;i++){
      String iStr = Integer.toString(i);
      gameBoard[i+1][0] = iStr;
      playerBoard[i+1][0] = iStr;
    }
    for(int col=1;col<11;col++){
      for(int row=1;row<11;row++){
        gameBoard[col][row] = dash;
        playerBoard[col][row] = dash;
      }
    }
  }

  void carrierPlacer(){
    //This method places the carrier randomly on the board. It makes sure the random placement
    //is both on the board and not intersecting another ship. The reason chooses a random number
    //between 0 and 1 before adding 1 to the random number is so that no ship can be placed on
    //the row/column labels.
    Random ran = new Random();
    //The below int direction is used to determine whether the ship will be place vertically or horizontally.
    //If direction == 0, we place the ship vertically. If direction == 1, we place the ship horizontally.
    int direction = ran.nextInt(2);
    for(int gate=0;gate<1;direction = ran.nextInt(2)){
      int col = ran.nextInt(10)+1;
      int row = ran.nextInt(10)+1;
      if(direction==0){
        if(col + 4 <11){
          //The below if statement makes sure there is not intersection between where we wil place this ship and any other ship
          if(gameBoard[col][row] == dash & gameBoard[col +1][row] == dash & gameBoard[col +2][row] == dash & gameBoard[col +3][row] == dash & gameBoard[col +4][row] == dash){
            gameBoard[col][row] = cross;
            gameBoard[col+1][row] = cross;
            gameBoard[col+2][row] = cross;
            gameBoard[col+3][row] = cross;
            gameBoard[col+4][row] = cross;
            gate++;
          }
        }
      }
      else{
        if(row + 4 < 11){
          //The below if statement makes sure there is not intersection between where we wil place this ship and any other ship
          if(gameBoard[col][row] == dash & gameBoard[col][row +1] == dash & gameBoard[col][row +2] == dash & gameBoard[col][row +3] == dash & gameBoard[col][row +4] == dash){
            gameBoard[col][row] = cross;
            gameBoard[col][row+1] = cross;
            gameBoard[col][row+2] = cross;
            gameBoard[col][row+3] = cross;
            gameBoard[col][row+4] = cross;
            gate++;
        }
      }
    }
    }
  }

  void battleshipPlacer(){
    //This method places the battleship randomly on the board. It makes sure the random placement
    //is both on the board and not intersecting another ship. The reason chooses a random number
    //between 0 and 1 before adding 1 to the random number is so that no ship can be placed on
    //the row/column labels.
    Random ran = new Random();
    //The below int direction is used to determine whether the ship will be place vertically or horizontally.
    //If direction == 0, we place the ship vertically. If direction == 1, we place the ship horizontally.
    int direction = ran.nextInt(2);
    for(int gate=0;gate<1;direction = ran.nextInt(2)){
      int col = ran.nextInt(10)+1;
      int row = ran.nextInt(10)+1;
      if(direction==0){
        if(col + 3 <11){
          //The below if statement makes sure there is not intersection between where we wil place this ship and any other ship
          if(gameBoard[col][row] == dash & gameBoard[col +1][row] == dash & gameBoard[col +2][row] == dash & gameBoard[col +3][row] == dash){
            gameBoard[col][row] = cross;
            gameBoard[col+1][row] = cross;
            gameBoard[col+2][row] = cross;
            gameBoard[col+3][row] = cross;
            gate++;
          }
        }
      }
      else{
        if(row + 3 < 11){
          //The below if statement makes sure there is not intersection between where we wil place this ship and any other ship
          if(gameBoard[col][row] == dash & gameBoard[col][row +1] == dash & gameBoard[col][row +2] == dash & gameBoard[col][row +3] == dash){
            gameBoard[col][row] = cross;
            gameBoard[col][row+1] = cross;
            gameBoard[col][row+2] = cross;
            gameBoard[col][row+3] = cross;
            gate++;
        }
      }
    }
    }
  }


  void destroyerAndSubmarinePlacer(){
    //This method places the battleship or submarine randomly on the board. It must be called twice to place both. It makes sure the random placement
    //is both on the board and not intersecting another ship. The reason chooses a random number
    //between 0 and 1 before adding 1 to the random number is so that no ship can be placed on
    //the row/column labels.
    Random ran = new Random();
    //The below int direction is used to determine whether the ship will be place vertically or horizontally.
    //If direction == 0, we place the ship vertically. If direction == 1, we place the ship horizontally.
    int direction = ran.nextInt(2);
    for(int gate=0;gate<1;direction = ran.nextInt(2)){
      int col = ran.nextInt(10)+1;
      int row = ran.nextInt(10)+1;
      if(direction==0){
        if(col + 2 <11){
          //The below if statement makes sure there is not intersection between where we wil place this ship and any other ship
          if(gameBoard[col][row] == dash & gameBoard[col +1][row] == dash & gameBoard[col +2][row] == dash){
            gameBoard[col][row] = cross;
            gameBoard[col+1][row] = cross;
            gameBoard[col+2][row] = cross;
            gate++;
          }
        }
      }
      else{
        if(row + 2 < 11){
          //The below if statement makes sure there is not intersection between where we wil place this ship and any other ship
          if(gameBoard[col][row] == dash & gameBoard[col][row +1] == dash & gameBoard[col][row +2] == dash){
            gameBoard[col][row] = cross;
            gameBoard[col][row+1] = cross;
            gameBoard[col][row+2] = cross;
            gate++;
        }
      }
    }
    }
  }

  void patrolBoatPlacer(){
    //This method places the patrol boat randomly on the board. It makes sure the random placement
    //is both on the board and not intersecting another ship. The reason chooses a random number
    //between 0 and 1 before adding 1 to the random number is so that no ship can be placed on
    //the row/column labels.
    Random ran = new Random();
    //The below int direction is used to determine whether the ship will be place vertically or horizontally.
    //If direction == 0, we place the ship vertically. If direction == 1, we place the ship horizontally.
    int direction = ran.nextInt(2);
    for(int gate=0;gate<1;direction = ran.nextInt(2)){
      int col = ran.nextInt(10)+1;
      int row = ran.nextInt(10)+1;
      if(direction==0){
        if(col + 2 <11){
          //The below if statement makes sure there is not intersection between where we wil place this ship and any other ship
          if(gameBoard[col][row] == dash & gameBoard[col +1][row] == dash){
            gameBoard[col][row] = cross;
            gameBoard[col+1][row] = cross;
            gate++;
          }
        }
      }
      else{
        if(row + 2 < 11){
          //The below if statement makes sure there is not intersection between where we wil place this ship and any other ship
          if(gameBoard[col][row] == dash & gameBoard[col][row +1] == dash){
            gameBoard[col][row] = cross;
            gameBoard[col][row+1] = cross;
            gate++;
        }
      }
    }
    }
  }





  void shipPlacer(){
    //This method places the ships on the board by calling all the ship placer methods.
    //It calls destroyerAndSubmarinePlacer() twice to place both the destroyer and the submarine.
    carrierPlacer();
    battleshipPlacer();
    destroyerAndSubmarinePlacer();
    destroyerAndSubmarinePlacer();
    patrolBoatPlacer();
  }

  void PrintBoard(String[][] board){
    //This method can be used to print either the gameBoard or the playerBoard.
    //It will be used to print the playerBoard
    //throughout the game.
    for(int row =0; row<11; row++){
      System.out.printf("\n");
      for(int col = 0; col<11; col++){
        System.out.printf(board[col][row] + '\t');
      }
    }
    System.out.println("");
  }
  void guess(String location){
    //This method takes in a guess, and checks the indicated location of the guessed
    //to see if there is a ship that was not already hit there. It also
    //counts attempts and hits.
    attempts++;
    int row_ascii_code = location.charAt(0);
    int row = row_ascii_code - 64;
    int col = Character.getNumericValue(location.charAt(1)) +1;
    if(row >= 33 & row <= 44){
      row = row - 32;
    }

    if(gameBoard[col][row] == dash){
      System.out.println("Miss!");
      gameBoard[col][row] = circle;
      playerBoard[col][row] = circle;
    }
    else if(gameBoard[col][row] == cross){
      hits++;
      System.out.println("Hit!");
      playerBoard[col][row] = cross;
      gameBoard[col][row] = circle;
    }
    else if(gameBoard[col][row] == circle){
      System.out.println("You already guessed that!");
    }

  }
  void play(){
    //This method plays the game by calling some methods from the class.
    Scanner sc = new Scanner(System.in);
    BoardBuilder();
    shipPlacer();
    PrintBoard(playerBoard);
    while(hits < 17){
      System.out.println("Enter guess (in the form of 'E8,' 'B9,' etc.):");
      String guess = sc.next();
      guess(guess);
      PrintBoard(playerBoard);
  }
  if(hits == 17){
    System.out.println("You win!");
    System.out.printf("\nIt took you %d attempts!", attempts);
  }
  }
}

class Homework2{
  public static void main(String[] args){
    //Our main function creates an instance of the Battleship class, and then calls
    //the play() method
    Battleship game = new Battleship();
    game.play();


  }
}
