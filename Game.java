import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    for (int i = 0; i < HEIGHT; i++) {
      if (i == 0 || i == 29 || i == 6 || i == 23 ) {
        Text.go(i+1, 0);
        for (int j = 0; j < WIDTH; j++) {
          System.out.print(Text.colorize(" ",BORDER_COLOR, BORDER_BACKGROUND));
        }
      } else {
        Text.go(i+1,1);
        System.out.print(Text.colorize(" ",BORDER_COLOR, BORDER_BACKGROUND));

        /*if (i > 6 && i < 23){
          Text.go(i+1,40);
          System.out.print(Text.colorize(" ",BORDER_COLOR, BORDER_BACKGROUND));
        } */


        Text.go(i+1,80);
        System.out.print(Text.colorize(" ",BORDER_COLOR, BORDER_BACKGROUND));
        }
    }
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    Text.go(startRow, startCol);
    System.out.print(s);
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    if (text.length() > width) {
      drawText(text.substring(0, width), row, col);
      if (row < height) {
        TextBox(row + 1, col, width, height, text.substring(width));
      }
    } else {
      drawText(text, row, col);
      if (text.length() < width) {
        System.out.print(" ".repeat(width - text.length()));
      }
      if (row < height) {
        TextBox(row + 1, col, width, height, " ");
      }
    }
  }


    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      return new CodeWarrior("Bob"+(int)(Math.random()*100));
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */

    //TextBox(int row, int col, int width, int height, String text)

    public static void drawParty(ArrayList<Adventurer> party,int startRow){
      /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
      //YOUR CODE HERE
      /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < party.size(); j++) {
          if (i == 0) {
            drawText(party.get(j).getName(), startRow + i, (80/(party.size()) * (j+1) - (80/party.size() - 2)   ));
          }
          else if (i == 1) {
            drawText("HP: " + colorByPercent(party.get(j).getHP(), party.get(j).getmaxHP()), startRow + i, (80/(party.size()) * (j+1) - (80/party.size() - 2)   ));
          }
          else {
            drawText(party.get(j).getSpecialName() + ": " + party.get(j).getSpecial(), startRow + i, (80/(party.size()) * (j+1) - (80/party.size() - 2)   ));
          }
        }
      }
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    int percent = (int)(((hp * 1.0) / maxHP) * 100);
    if (percent < 25){
      return Text.colorize(output, Text.RED);
    }else if (percent < 75){
      return Text.colorize(output, Text.YELLOW);
    }else{
      return Text.colorize(output, Text.WHITE);
    }

    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    // under 75% : yellow
    // otherwise : white
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen( ArrayList<Adventurer> party,   ArrayList<Adventurer> enemies){

    drawBackground();

    drawParty(party, 3);

    drawParty(enemies, 26);

  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location

      //show cursor
      Text.showCursor();
      String input = in.nextLine();

      //clear the text that was written
      Text.hideCursor();
      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    Adventurer boss = new Boss();
    enemies.add(boss);

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    Adventurer one = new Karen();
    Adventurer two = new Thug();
    Adventurer three = new DrugDealer();

    party.add(one);
    party.add(two);
    party.add(three);
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party, enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/support/quit";
    drawText(preprompt, 8, 2);
    Text.go(9,2);
    
    //keep track of starting row
    int startRow = 9;

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      if (startRow > 23) {
        startRow = 8;
      }
      //Read user input
      Text.go(startRow,2);
      input = userInput(in);

      //example debug statment
      //TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){
        String playerMove = "";
        if (party.get(whichPlayer).getHP() > 0) {
        //Process user input for the last Adventurer:
        if(input.equals("attack") || input.equals("a")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
          playerMove += party.get(whichPlayer).attack(enemies.get(whichOpponent));
        }
        else if(input.equals("special") || input.equals("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
          playerMove += party.get(whichPlayer).specialAttack(enemies.get(whichOpponent));
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
          playerMove += party.get(whichPlayer).support();
        }
        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;
        TextBox(startRow, 2, 78, 16, playerMove);
        startRow += 2;
      } else {
        TextBox(startRow, 2,78, 16, party.get(whichOpponent).getName() + " is dead");
        startRow++;
        party.remove(party.get(whichOpponent));
      }


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/support/quit: ";
          TextBox(startRow, 2, 78, 16, prompt);
          startRow++;
          
        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "Press enter to see monster's turn";
          TextBox(8, 2, 78, 18, prompt);
          startRow = 9; 


          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
      }else{
        if (enemies.get(whichOpponent).getHP() > 0) {
          //not the party turn!
          whichPlayer = (int)(Math.random()*(party.size()));

          //enemy attacks a randomly chosen person with a randomly chosen attack.z`
          //Enemy action choices go here!
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
          String enemyMove;
          int random = (int)(Math.random() * 3);
          if (random == 0) {
            enemyMove = enemies.get(whichOpponent).attack(party.get(whichPlayer));
          }
          else if (random == 1) {
            enemyMove = enemies.get(whichOpponent).specialAttack(party.get(whichPlayer));
          }
          else {
            enemyMove = enemies.get(whichOpponent).support();
          }


          TextBox(startRow, 2, 78, 16, enemyMove);
          startRow++;


          //Decide where to draw the following prompt:
          String prompt = "Press enter to see next turn";
          TextBox(startRow, 2, 78, 16, prompt);
          startRow++;
          input = userInput(in);


          whichOpponent++;
        } else {
          TextBox(startRow, 2, 78, 16, enemies.get(whichOpponent).getName() + " is dead");
        }

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        whichOpponent = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
        TextBox(startRow, 2, 78, 16, prompt);
        startRow++;
      }

      //display the updated screen after input has been processed.
      drawScreen(party, enemies);


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
