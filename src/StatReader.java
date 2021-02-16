package com.company;
import java.io.*;
import java.util.*;
import java.lang.StringBuilder;
import static java.lang.Character.isLetterOrDigit;
import java.io.InputStreamReader;

public class StatReader {

    private final HashMap<String, Integer> Words = new HashMap<>();
    private int countWords;

    StatReader(String infile){
        Reader reader = null;
        try
        {
            reader = new InputStreamReader(new FileInputStream(infile));
            int intSymbol = reader.read();
            char symbol = (char) intSymbol;
            StringBuilder sb = new StringBuilder();
            countWords = 0;
            while (intSymbol != -1){
                if (isLetterOrDigit(symbol)){
                    sb.append(symbol); //составляем слово
                }
                else if (sb.length() != 0) {
                    if (Words.containsKey(sb.toString())){
                        Words.put(sb.toString(), Words.getOrDefault(sb.toString(), 0) + 1);
                    }
                    else {
                        Words.put(sb.toString().toLowerCase(), 1);
                    }
                    sb = new StringBuilder();
                    countWords++;
                }
                intSymbol = reader.read();
                symbol = (char) intSymbol;

            }
        }
        catch (IOException e)
        {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally
        {
            if (null != reader)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    List<Map.Entry<String, Integer>> sortMap(){
        List<Map.Entry<String, Integer>> toSort = new ArrayList<>(Words.entrySet());
        toSort.sort(HashMap.Entry.<String, Integer>comparingByValue().reversed());
        return toSort;
    }
    
    int getterCountWords(){
        return countWords;
    }
}
