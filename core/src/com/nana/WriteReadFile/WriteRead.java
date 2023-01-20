package com.nana.WriteReadFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WriteRead{
private static WriteRead instance = null;
private Boolean lineOK;
private String firstLine;
private int smallest;
private BufferedReader br;
private File file;
private FileWriter myWriter;
public int[] smallestNumbers = new int[5];



/**
 * creates a file if it does not exist yet. Initializes the FileWriter
 */

public WriteRead() {
  file = new File("assets/BestTime.txt");
  try {
    if(!file.exists()){
      file.createNewFile();
    }
    myWriter = new FileWriter("assets/BestTime.txt", true);
  } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  

}

/**
 * returns the current instance that is running from
 * @return the current class that it is running from
 */

public static WriteRead getInstance() {
  if (instance == null) {
      instance = new WriteRead();
  }
  return instance;
}

/**
 * Writes to BestTime.txt file if called
 * @param bestTime takes the best time (seconds) to write to file
 * @throws IOException 
 */
public void write(int bestTime) throws IOException{

  String secondsTime = Integer.toString(bestTime);
  String newLine = secondsTime;
  
  checkFileLine();
    try {

      BufferedWriter writer = new BufferedWriter(new FileWriter("assets/BestTime.txt", true));

      if(lineOK){
      writer.write(newLine);
      startGetLinearSearch();
     
      } else{
        writer.newLine();
        writer.write(newLine);
        startGetLinearSearch();

      }
      


      writer.close();
  } catch (IOException e) {
      e.printStackTrace();
  }

      
  }

  /**
   * checks if the current file line is OK to write on. If not, make a new line and then write on that new line
   * @throws IOException if there is an error 
   */

  public void checkFileLine() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader(file));
    String lastLine = "";
    String currentLine;
    while ((currentLine = br.readLine()) != null) {
        lastLine = currentLine;
    }
    br.close();

    if (lastLine.length() > 0) {
      lineOK = false;
   

    } else {
      lineOK = true;
     

    }
  }
  

/**
 * starts the linear search algorithm to find the smallest number within the "BestTime.txt" file and return that number
 * @return the smallest # searched by a linear search algorithm
 * @throws IOException if there is an error
 */
public int startGetLinearSearch() throws IOException{
  try {
    if (file != null && file.exists()) {
      br = new BufferedReader(new FileReader(file));
      firstLine = br.readLine();
      if(firstLine != null) {
        smallest = Integer.parseInt(firstLine);
      }
      String currentLine;
      while((currentLine = br.readLine()) != null){
        int currentNumber = Integer.parseInt(currentLine);  
        if(currentNumber < smallest)
            smallest = currentNumber;
      }
      br.close();
      return smallest;
    }
  } catch (IOException e) {
    e.printStackTrace();
  }
  return -1;
}

public int getSmallest(){
  return smallest;
}

/**
 * read and write the 5 smallest number from the txt files into the smallestnumber array
 * uses the same concept as a linear search algorithm. However, it compares the next number to the previous number to check who is smallest
 * uses 2 different comparison. one comparison being the first for loop input where it first compare and write the smallest number from the txt file
 * then it compares itself to check if it is acutually the smallest number within its own array by comparing next and previous #s
 * it goes through the entire txt file and keeps comparing the next # with the previous # until the array is filled
 */
public void readWriteToArray(){
  Arrays.fill(smallestNumbers, Integer.MAX_VALUE);
  try {
      FileReader fr = new FileReader("assets/BestTime.txt");
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
          int number = Integer.parseInt(line);
          for (int i = 0; i < smallestNumbers.length; i++) {
              if (number < smallestNumbers[i]) {
                  for (int j = smallestNumbers.length - 1; j > i; j--) {
                      smallestNumbers[j] = smallestNumbers[j - 1];
                  }
                  smallestNumbers[i] = number;
                  break;
              }
          }
      }
      br.close();
      fr.close();
  } catch (IOException e) {
      e.printStackTrace();
  } catch (NumberFormatException e) {
      System.out.println("Invalid number format in file");
  }
  
}

/**
 * return the smallestNumber array
 * @return the 5 smallest numbers
 */
public int[] getSmallestNumbers(){
  readWriteToArray();
  return smallestNumbers;
}



}
