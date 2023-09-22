package Assign_1;
import BasicIO.*;                           //for IO classes
import static BasicIO.Formats.*;                    //for field formats
import static java.lang.Math.*;             //for maths constants and functions

public class Puzzle
{
  private ASCIIDisplayer display;
  private ASCIIDataFile file;
  private char[][] words;                    //2-D array of character to store the 18X18 puzzle
  private String statements;                 //to store the words to be found int the puzzle
  public Puzzle()
  {
    file = new ASCIIDataFile();
    display = new ASCIIDisplayer(100,100);
    loadPuzzle();                            //loading the puzzle
    displayPuzzle();                         //displaying the puzzle
    start();                                 //calling the method tp search the words and display the direcction
    file.close();                            //closing the file
    display.close();                         //closing the displayer
  }

/*
 * method to load the puzzle from the .txt file
 */
  
  public void loadPuzzle()
  {
    words= new char[18][18];                  //creating a 2-D array in order to read the characters from the .txt file
    for(int i=0;i<2;i++)                      //loop to read the height and width of the matrix
    {
      int num = file.readInt();
    }
    for(int y=0;y<words.length;y++)           //looping through the rows and columns
    {
      for(int x=0;x<words.length;x++)
      {
        words[x][y]=file.readChar();           //reading the characters from the puzzle
      }
    }
  }
  
  /*
   * method to display the puzzle from the .txt file
   */
  
  public void displayPuzzle()
  {
    for(int y=0;y<words.length;y++)
    {
      for(int x=0;x<words.length;x++)
      {
        display.writeChar(words[x][y]);
      }
      display.newLine();
    }
  }
  
  /*
   * method to read the string from the puzzle and then comparing the strings first chara ter with the puzzle's 
   * characters, if found proceeding to chceck in 8 different directions
   */
  
  public void start()
  {
    for(;;)
    {
      if(file.isEOF())break;
      statements = file.readString();               //reading the string to search from the .txt fie
      char ch[] = statements.toCharArray();         //converting the string to a character array
      for(int y=0;y<words.length;y++)
      {
        for(int x=0;x<words.length;x++)
        {
          if(ch[0] == words[x][y])         //extracting the first letter of the string and comparing it with the puzzle
          {
            if(checkRight(x,y,statements))
              display.writeLine(statements + "found (right) at (" + x + "," + y + ")");
            if(checkLeft(x,y,statements))
              display.writeLine(statements + "found (left) at (" + x + "," + y + ")");
            if(checkUp(x,y,statements))
              display.writeLine(statements + "found (upwards) at (" + x + "," + y + ")");
            if(checkDown(x,y,statements))
              display.writeLine(statements + "found (downwards) at (" + x + "," + y + ")");
            if(checkLeftUp(x,y,statements))
              display.writeLine(statements + "found (left diagonally upwards) at (" + x + "," + y + ")");
            if(checkLeftDown(x,y,statements))
              display.writeLine(statements + "found (left diagonally downwards) at (" + x + "," + y + ")");
            if(checkRightUp(x,y,statements))
              display.writeLine(statements + "found (right diagonally upwards) at (" + x + "," + y + ")");
            if(checkRightDown(x,y,statements))
              display.writeLine(statements + "found (right diagonally downwards) at (" + x + "," + y + ")");
          }
        }
      }
    }
  }
  
  /*
   * checking in the right direction.
   * @param y=row
   * @param x=column
   * @param word= word
   */
  
  public boolean checkRight(int x,int y,String word)
  {
    int tempx = x;                                   //temporary x
    char ch[] = word.toCharArray();                  //converting the word into an array of characters
    if(!(18<word.length()+x))                        //checking whether the array is out of bound or not
      for(int k=1;k<ch.length;k++)                   //looping through the length of the character array
    {
      if(!(ch[k] == words[tempx][y]))break;          //comparing single character of the words with those in the puzzle
      tempx++;                                  //incrementing the value of temporary x,if the above condition is false
    }
    return true;
  }
  
  /*
   * checking in the left direction
   * @param y=row
   * @param x=column
   * @param word=word
   */
  
  public boolean checkLeft(int x,int y, String word)
  {
    int tempx=x;                                          //temporary x
    char ch[] = word.toCharArray();                       //converting the word into an array of characters
    if(!((x-(word.length()-1)<0)))                         //checking whether the array is out of bound or not
      for(int k=1;k<ch.length;k++)                        //looping through the length of the character array
    {
      if(!(ch[k] == words[tempx][y]))break;          //comparing single character of the words with those in the puzzle
      tempx--;                                  //decrementing the value of temporary x,if the above condition is false
    }
    return true;
  }
  
  /*
   * checking in the upward direction
   * @param y=row
   * @param x=column
   * @param word=word
   */
  
  public boolean checkUp(int x,int y,String word)
  {
    int tempy=y;                                            //temporary y
    char ch[] = word.toCharArray();                         //converting the word into an array of characters
    if(!(y-(word.length()-1)<0))                            //checking whether the array is out of bound or not
      for(int k=1;k<ch.length;k++)                          //looping through the length of the character array
    {
      if(!(ch[k] == words[x][tempy]))break;          //comparing single character of the words with those in the puzzle
      tempy--;                                  //decrementing the value of temporary y,if the above condition is false
    }
    return true;
  }
  
  /*
   * chekcing in the downward direction
   * @param y=row
   * @param x=column
   * @param word=word
   */
  
  public boolean checkDown(int x,int y,String word)
  {
    int tempy=y;                                             //temporary y
    char ch[] = word.toCharArray();                          //converting the word into an array of characters
    if(!(18<word.length()+y))                                //checking whether the array is out of bound or not
      for(int k=1;k<ch.length;k++)                           //looping through the length of the character array
    {
      if(!(ch[k] == words[x][tempy]))break;          //comparing single character of the words with those in the puzzle
      tempy++;                                  //incrementing the value of temporary y,if the above condition is false
    }
    return true;
  }
  
  /*
   * chekcing in the downward direction
   * @param y=row
   * @param x=column
   * @param word=word
   */
  
  public boolean checkLeftUp(int x,int y,String word)
  {
    int tempy=y;                                             //temporary y
    int tempx=x;                                             //temporary x
    char ch[] = word.toCharArray();                          //converting the word into an array of characters
    if(!( x-(word.length()-1)<0 || y-(word.length()-1)<0))     //checking whether the array is out of bound or not
      for(int k=1;k<ch.length;k++)                           //looping through the length of the character array
    {
      if(!(ch[k] == words[tempx][tempy]))break;      //comparing single character of the words with those in the puzzle
      tempx--;                                  //decrementing the value of temporary x,if the above condition is false
      tempy--;                                  //decrementing the value of temporary y,if the above condition is false
    }
    return true;
  }
  
  /*
   * chekcing in the downward direction
   * @param y=row
   * @param x=column
   * @param word=word
   */
  
  public boolean checkLeftDown(int x,int y,String word)
  {
    int tempy=y;                                            //temporary y
    int tempx=x;                                            //temporary x
    char ch[] = word.toCharArray();                         //converting the word into an array of characters
    if(!(x-(word.length()-1)<0 || 18<word.length()+y))      //checking whether the array is out of bound or not
      for(int k=1;k<ch.length;k++)                          //looping through the length of the character array
    {
      if(!(ch[k] == words[tempx][tempy]))break;      //comparing single character of the words with those in the puzzle
      tempx--;                                  //decrementing the value of temporary x,if the above condition is false
      tempy++;                                  //incrementing the value of temporary y,if the above condition is false
    }
    return true;
  }
  
  /*
   * chekcing in the downward direction
   * @param y=row
   * @param x=column
   * @param word=word
   */
  
  public boolean checkRightUp(int x,int y,String word)
  {
    int tempy=y;                                           //temporary y
    int tempx=x;                                           //temporary x
    char ch[] = word.toCharArray();                        //converting the word into an array of characters
    if(!(18<word.length()+x || y-(word.length()-1)<0))    //checking whether the array is out of bound or not
      for(int k=1;k<ch.length;k++)                         //looping through the length of the character array
    {
      if(!(ch[k] == words[tempx][tempy]))break;      //comparing single character of the words with those in the puzzle
      tempx++;                                  //incrementing the value of temporary x,if the above condition is false
      tempy--;                                  //decrementing the value of temporary y,if the above condition is false
    }
    return true;
  }
  
  /*
   * chekcing in the downward direction
   * @param y=row
   * @param x=column
   * @param word=word
   */
  
  public boolean checkRightDown(int x,int y,String word)
  {
    int tempy=y;                                           //temporaray y
    int tempx=x;                                           //temporary x
    char ch[] = word.toCharArray();                        //converting the word into an array of characters
    if(!(18<word.length()+x || 18<word.length()+y))        //checking whether the array is out of bound or not
      for(int k=1;k<ch.length;k++)                         //looping through the length of the character array
    {
      if(!(ch[k] == words[tempx][tempy]))break;      //comparing single character of the words with those in the puzzle
      tempx++;                                  //incrementing the value of temporary x,if the above condition is false
      tempy++;                                  //incrementing the value of temporary y,if the above condition is false
    }
    return true;
  }
  
  /*
   * MAIN METHOD
   */
  
  public static void main(String args[])
  {
    Puzzle p=new Puzzle();
  }
  }
 