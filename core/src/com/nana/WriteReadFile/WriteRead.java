package com.nana.WriteReadFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteRead{

public void write(int bestTime) throws IOException{
  int playerCount = 1;
  String stringPlayerCount = Integer.toString(playerCount);
  String secondsTime = Integer.toString(bestTime);
  String newLine = stringPlayerCount + ": " + secondsTime;

  FileWriter myWriter = new FileWriter("Test.txt");
  BufferedWriter writeFile = new BufferedWriter(myWriter);
  
    writeFile.write(newLine);

    writeFile.close();
      
      
  
  
}
}


// On a permanent fixed timer (schedular)




