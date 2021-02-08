package com.company;
import java.io.*;
import java.util.*;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) throws IOException{

        StatReader takeText = new StatReader(args[0]);

        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>();
        ListIterator<Map.Entry<String, Integer>> swIterator = sortedWords.listIterator();
        sortedWords = takeText.sortMap();

        double freq;
        FileWriter writer = new FileWriter(args[1], false);
        for (Map.Entry<String, Integer> item : sortedWords){
            freq =  (double) Math.round(1000 * takeText.freq(item.getKey())) / 1000;
            writer.write(item.getKey() + ";" + item.getValue() + ";" + freq + ";\n");
            writer.flush();
        }
    }
}
