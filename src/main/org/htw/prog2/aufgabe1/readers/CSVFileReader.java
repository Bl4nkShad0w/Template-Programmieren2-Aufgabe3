package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.MutationFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CSVFileReader implements MutationFileReader {
    ArrayList<String[]> mutPatData = new ArrayList<>();

    @Override
    public MutationFile readFile(String filename) throws IOException, FileFormatException {
        String[] lineArray;
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains("#")){
                    lineArray = line.split(";");
                    this.mutPatData.add(lineArray);
                }
            }
            if(!this.mutPatData.getFirst()[0].equals("\"Mutation Patterns\"") || !this.mutPatData.getFirst()[1].equals("\"Number of Sequences\"")){
                throw new FileFormatException("First line of mutation pattern CSV file must be a header");
            }
            for(int i = 2; i<this.mutPatData.getFirst().length; i++){
                if(!this.mutPatData.getFirst()[i].contains(" foldn\"")){
                    throw new FileFormatException("First line of mutation pattern CSV file must be a header");
                }
            }
            for(int i = 0; i < this.mutPatData.size(); i++){
                if(this.mutPatData.get(i).length != 10){
                    throw new FileFormatException("All lines in a CSV file must have the same number of elements");
                }
            }
        }catch (FileNotFoundException e){
            throw e;
        }catch (IOException e){
            throw e;
        }catch (FileFormatException e){
            throw e;
        }
        MutationFile drugList = new MutationFile();
        drugList.drugs.clear();
        for (int i = 2; i < mutPatData.getFirst().length; i++) {
            drugList.drugs.add(mutPatData.getFirst()[i]);
        }
        drugList.mutPatData.clear();
        drugList.mutPatData.addAll(mutPatData);
        return drugList;
    }

    @Override
    public boolean canReadFile(String filename) {
        return false;
    }

    public static List<String> parseDrugs(String line) throws FileFormatException {
        LinkedList<String> res = new LinkedList<>();
        String[] data = line.split(";");
        if(!data[0].equals("\"Mutation Patterns\"") || !data[1].equals("\"Number of Sequences\"")){
            throw new FileFormatException("Incorrect Format [1]");
        }
        for(int i = 2; i<data.length; i++){
            if(!data[i].endsWith(" foldn\"")){
                throw new FileFormatException("Incorrect Format [2]");
            }
        }
        for(int i = 2; i<data.length; i++){
            res.add((data[i].split(" foldn\"")[0]).split("\"")[1]);
        }
        return res;
    }
}