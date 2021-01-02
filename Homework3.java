/*********************
DJ Poulin
Data Structures - Homework 3 - Connect Four
October 2nd, 2020
**********************/
import java.util.Scanner;
import java.util.Vector;

class ConnectFour{
  //This is our game class. Methods include
  //ConnectFour(), BoardBuilder(), PrintBoard(), CheckWin()
  //Also cotains the gameboard, width and height of the gameboard, and the symbol
  //for an empy spot (dash = "- ")
  public String gameBoard[][];
  public int width;
  public int height;
  public String dash = "- ";

  ConnectFour(int width1, int height1){
    //Our constructor. Given width and height, which will be used in BoardBuilder().
    //Our board will be 6 by 7
    gameBoard = new String[width1][height1 + 1];
    width = width1;
    height = height1;
  }

  void BoardBuilder(){
    //This method build our board. Given the width and height, the method creates
    //a row on the bottom numbering each column.
    //This method then assigns every other location on the boards the value of "- "
    for(int col=0;col<width;col++){
      for(int row =0;row<height;row++){
        gameBoard[col][row] = dash;
      }
    }
    for(int col=0;col<width;col++){
      gameBoard[col][height] = String.valueOf(col);
    }
}

    void PrintBoard(){
      //This method prints the gameBoard.
      for(int row =0; row<=height; row++){
        System.out.printf("\n");
        for(int col = 0; col<width; col++){
          System.out.printf(gameBoard[col][row] + '\t');
        }
      }
    }
    boolean CheckWin(Player player){
      //This method checks if Player player has won. If so, it returns true.
      //The method checks for a win by iterating through the player's moveList.
      //For each move, CheckWin() checks whether there are 3 additional moves
      // up, 3 additional moves down, 3 additional moves up+right, and 3
      // additional moves down+right. We only need to check whether the moves
      // is the start of 4 in a row, since we will be iterating through every move
      // anyways.
      //
      // The method checks if the next piece in a row is a previous move by using
      // moveList.contains(). If the next piece is in moveList, we add 1 to the counter
      // horizontal/vertical/diagonal1/diagonal2 and the continue to iterate through pieces.
      // If the next piece is not in moveList, we stop iterating in that direction and
      // move on. If any of our counters reach 4, we will later return true.
      for(int i=0;i<player.moveList.size();i++){
        int horizontal = 0;
        int vertical = 0;
        int diagonal1 = 0;
        int diagonal2 = 0;
        String currentMove = player.moveList.get(i);

        System.out.println(currentMove);

        int col = Character.getNumericValue(currentMove.charAt(0));
        int row = Character.getNumericValue(currentMove.charAt(2));

        for(int counter = 0; counter<5;counter++){
          String coordinate = String.valueOf(col + counter) + "," + String.valueOf(row);

          //System.out.println(coordinate);

          if(player.moveList.contains(coordinate) == true){
            horizontal = horizontal +1;

          }
          else{
            break;
          }
          if(horizontal >=4){
            return true;
          }

        }

        for(int counter = 0; counter<5;counter++){
          String coordinate = String.valueOf(col) + "," + String.valueOf(row+counter);

          //System.out.println(coordinate);

          if(player.moveList.contains(coordinate) == true){
            vertical = vertical +1;

          }
          else{
            break;
          }
          if(vertical >=4){
            return true;
          }

        }

        for(int counter = 0; counter<5;counter++){

          String coordinate = String.valueOf(col+counter) + "," + String.valueOf(row+counter);

          //System.out.println(coordinate);

          if(player.moveList.contains(coordinate) == true){
            diagonal1 = diagonal1 +1;

          }
          else{
            break;
          }
          if(diagonal1 >=4){
            return true;
          }

        }

        for(int counter = 0; counter<5;counter++){
          String coordinate = String.valueOf(col+counter) + "," + String.valueOf(row-counter);

          //System.out.println(coordinate);

          if(player.moveList.contains(coordinate) == true){
            diagonal2 = diagonal2 +1;

          }
          else{
            break;
          }
          if(diagonal2 >=4){
            return true;
          }

        }

    }
    return false;
  }
}



class Player{
  //Our player class. Has methods Player() and PlaceMarker(). Also contains the
  // player's marker and a list of previous moves.
  public String marker;
  public Vector<String> moveList = new Vector<String>();

  Player(String marker1){
    //Our constructor method. Assigns the player's marker to the inputted symbol
    marker = marker1;

  }

  void PlaceMarker(ConnectFour game){
    //Takes in a column using a scanner and places a piece. If the column
    //is full, we tell the player that the move is invalid and that they should
    //pick a new move. Otherwise, we place the piece as low as possible by finding
    //the first nonempty spot in the column and placing the piece above it.
    Scanner sc = new Scanner(System.in);
    System.out.println("Your move! Pick a column (only enter a single number): ");
    int placement = Integer.parseInt(sc.next());
    if(game.gameBoard[placement][0] != game.dash){
      System.out.println("Sorry, that's not valid! Try again!");
    }
    else{
      for(int rowIndex =1; rowIndex<=game.height;rowIndex++){
        if(game.gameBoard[placement][rowIndex] != game.dash){
          game.gameBoard[placement][rowIndex-1] = marker;
          String coordinate = String.valueOf(placement) + "," + String.valueOf(rowIndex-1);
          moveList.add(coordinate);

          System.out.println(coordinate);

          break;
        }
      }
    }

  }
}



class Homework3{
  //Our main class. Creates instances of our player and game classes.
  //Plays our game using a while loop.
  //Also makes sure that if no more moves are available, the game is ended,
  //and players are informed that it is a tie
  public static void main(String[] args){
    ConnectFour game = new ConnectFour(7,6);
    game.BoardBuilder();
    Player one = new Player("X ");
    Player two = new Player("O ");
    boolean win = false;
    String winner = "";


    while(win ==false){

      //During the below for loop, we make sure we have not reached tie condition. tieCounter counters
      //the number of full columns. If all columns are full, the game ends.
      //Because the board has an even number os spaces, we only need to check for
      //this after player two's turn turn.
      int tieCounter = 0;
      for(int tieIndex = 0; tieIndex < game.width; tieIndex ++){
        if(game.dash != game.gameBoard[tieIndex][0]){
          tieCounter ++;

        }
      }
      if(tieCounter==game.width){
        System.out.println("It's a tie! No more moves available!");
        break;
      }

      game.PrintBoard();
      System.out.println("Player one's turn");
      one.PlaceMarker(game);
      if(game.CheckWin(one) == true){
        winner = "one";
        break;
      }

      game.PrintBoard();
      System.out.println("Player two's turn");
      two.PlaceMarker(game);
      if(game.CheckWin(two) == true){
        winner = "two";
        break;
      }
    }

    game.PrintBoard();
    if(winner != ""){
      System.out.printf("\nPlayer %s wins!", winner);
    }


  }
}
