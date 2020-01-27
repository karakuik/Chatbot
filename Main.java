import java.io.File;  // Import the File class
import java.io.FileWriter; //Import ability to write files
import java.io.IOException;  // Import the IOException try/catch
import java.util.*; //Import scanner and all that jazz

//Look for "Current Work" to see where I left off

class Main {
  public static void main(String[] args) {

    Introduction(); //Chatbot introduces herself to us
    text("\n\nBack in main after introduction");
    text("Here are somethings I can do, just let me know what you're in the mood for.");
    mainChatBotMenu();

  }//End of Main

  //Contains all the introductory statements. You are meeting your new virtual assistant
  static void Introduction(){

    String userInput = new String();
    String fileName = "User.txt";
    Scanner scan = new Scanner(System.in);
    File userFile = new File(fileName);
    
    //Simple Start to Introduction
    text("Hello user. My name is ChatBot.\nI was created in January of 2019");
    text("Let's see if we've met before!");
    
    
    //Check if this has been used before
    if(userFile.exists()){
      text("Here we are!");
      readFromFile(userFile);
    }
    //Hasn't been used
    else{
      text("I don't think we've met before, lets change that shall we?");
      
      //Create new file
      try{
        userFile.createNewFile();
        text("File Created! " + userFile.getName());
      }
      catch(IOException e){
        text("Oopsie woopsie, we made a widdle ewwow!");
      }
      
      //Get basic user information & write to file
      text("What is your name new User?");
      userInput = scan.nextLine();
      text("That is a nice name " + userInput);
      writeToFile(userInput, fileName);
      
      text("How old are you?");
      userInput = scan.nextLine();
      text("You are " + userInput + " years young! Good for you!");
      writeToFile(userInput, fileName);
      text("Lets get started on some of my main features okay?");
    }
  }//End of Introduction Function

  static void text(String text)
  {
    System.out.println(text);
  }//End of Text Function

  static void writeToFile(String inputToWrite, String fileName){
    try{
      FileWriter writer = new FileWriter(fileName, true);
      writer.write(inputToWrite + "\n");
      writer.close();
    }
    catch(IOException e){
      text("Ooooops sumtin wong!");
      e.printStackTrace();
    }
  }//End of Write to File

  //Read from file. Void RN, will need to change to string later.
  static void readFromFile(File file){
    try{
      Scanner reader = new Scanner(file);
      while(reader.hasNextLine()){
        String fromFile = reader.nextLine();
        text(fromFile);
      }
    }
    catch(IOException e){
      
    }
  }//End of readFromFile

  static void mainChatBotMenu(){
    Scanner scan = new Scanner(System.in);
    int choice = 0;
    
    text("Please make a choice and enter in the number below!");
    text("1. Check Tomorrow's date\n2. Check Current Time.\n3. Play Rock Paper Scissors\n4. Check for any reminders\n5. Play a math tutoring game.\n6. See a picture of me!");
    //get user input
    choice = scan.nextInt();
    
    while(choice <1 || choice >6)
    {
      text("Woahh, I can't count that answer! Try again!");
      choice = scan.nextInt();
    }
    switch(choice){
      case 1:{
        clearScreen();
        text("Tomorrow's Date");
        break;
      }
      case 2:{
        clearScreen();
        text("Check current time");
        break;
      }
      case 3:{
        clearScreen();
        text("Rock Paper Scissors");
        playRockPaperScissors();
        break;
      }
      case 4:{
        clearScreen();
        text("Look at reminders");
        reminders();
        break;
      }
      case 5:{
        clearScreen();
        text("Math tutoring game");
        break;
      }
      case 6:{
        clearScreen();
        text("Lets take a look at a picture of me!");
        pictureOfMe();
        break;
      }
      default:{
        text("uuuuuuuuuuugh");
        break;
      }

    }
    scan.close();
  }// End of Main mainChatBotMenu

  //Need to make...
  //Check Tomorrow's date
  //Check Current Time
  //Play Rock Paper Scissors
  static void playRockPaperScissors(){
    Scanner scan = new Scanner(System.in);
    int choice = 0;
    Random rand = new Random();
    int computerChoice = 0;

    text("You want to play some RPS? Let's play!");
    text("1.Play Rock\n2.Play Paper\n3.Play Scissors");
    choice = scan.nextInt();
    while(choice < 1 || choice > 3)
    {
      text("This isnt rock paper scissors spock! But maybe you can teach me later...");
      text("1.Play Rock\n2.Play Paper\n3.Play Scissors");
      choice = scan.nextInt();
      if(choice >0 || choice <=3)
        break;
    }
    
    computerChoice = rand.nextInt((3 - 1) + 1) + 1;

    RPSResults(choice, computerChoice);
    text("Returning to main menu...\n\n\n");
    mainChatBotMenu();
    scan.close();
  }//END OF P.RPS

  static void RPSResults(int choice, int computerChoice){
    //PC & CC means player choice & CompChoice respectively
    switch(choice)
    {
      case 1:{
        text("You play ROCK!");        
        break;
      }
      case 2:{
        text("You play PAPER!");
        break;
      }
      case 3:{
        text("You play SCISSORS!");
        break;
      }
      default:{
        text("Uhh im lost :I");
      }
     
    }
    
    if(computerChoice == 1)
    {
      text("I played ROCK!");
    }
    
    else if(computerChoice == 2)
    {
      text("I played PAPER!");
    }
    else{
      text("I Played SCISSORS!");
    }
    
    if(choice == computerChoice)
    {
      text("We tied!");
    }
    else if((choice == 1 && computerChoice == 3) || (choice == 2 && computerChoice == 1) || (choice == 3 && computerChoice == 2))
    {
      text("You win! Good job!");
    }
    else{
      text("Sorry you lose!");
    }

  }//END OF RPS RESULTS


  //Check and Set reminders
  static void reminders(){
    String fileName = "reminders.txt";
    Scanner scan = new Scanner(System.in);
    File remindersFile = new File(fileName);
    String inputToWrite = "I am inside of reminder I hope!";
    String stringForCounter = "";
    List<String> remindersList = new ArrayList<String>(); 
    
    int counter = 1; //Need to set this to read what line it is on

    text("Welcome to the reminder center.\n\tWould you like to check or set a reminder?");
    inputToWrite = scan.nextLine();
    //Check if add reminder, read, or complete(Delte) reminder
    if(inputToWrite.toLowerCase().equals("set")){
      text("When you are finished entering reminders, please say \"exit\"");
      while(!inputToWrite.toLowerCase().equals("exit")){
        stringForCounter = Integer.toString(counter);
        inputToWrite = scan.nextLine();
        if(inputToWrite.toLowerCase().equals("exit")){
        break;
        }
        writeToFile(stringForCounter + ". " + inputToWrite, fileName);
        counter++;
        text("DEBUGGER: Inside Still");
      }
    }

    //CURRENT WORK
    //TO-DO Make list, make for loop for list to add to file

    readFromFile(remindersFile);
    //Read from file and delete if necessary

    scan.close();
  }
  //Play a math tutoring game
  //See a picture of me!
  static void pictureOfMe(){
    File me = new File("Me.txt");
    readFromFile(me);
    mainChatBotMenu();
  }//END OF PICTUREOFME

  //A little CLS for when needed
  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  } //END OF CLS

}//End of class