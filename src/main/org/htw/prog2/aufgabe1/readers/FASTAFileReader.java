package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.HIVFile;
import org.htw.prog2.aufgabe1.files.SequenceFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class FASTAFileReader implements SequenceFileReader{
    HashSet<String> segs = new HashSet<>();
    ArrayList<String> fastaFile = new ArrayList<>();

    @Override
    public SequenceFile readFile(String filename) throws IOException, FileFormatException {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            int firstAdd = 1;
            while ((line = reader.readLine()) != null){
                if (firstAdd == 1){
                    this.fastaFile.add(line);
                    firstAdd -= 1;
                } else {
                    if (line.contains(">")) {
                        this.fastaFile.add(line);
                    } else if (this.fastaFile.getLast().contains(">")){
                        this.fastaFile.add(line);
                    }
                    else {
                        this.fastaFile.set(this.fastaFile.size()-1, this.fastaFile.getLast() + line);
                    }
                }
            }
            if(!this.fastaFile.getFirst().contains(">")){
                this.fastaFile.clear();
                throw new FileFormatException("FASTA File does not start with sequence header line.");
            }
            int counter = 0;
            while (counter < this.fastaFile.size()){
                if (this.fastaFile.get(counter).contains(">")) {
                    counter = counter + 2;
                } else {
                    this.fastaFile.clear();
                    throw new FileFormatException("Two header lines are directly following each other.");
                }
            }
            if(this.fastaFile.getLast().contains(">")){
                this.fastaFile.clear();
                throw new FileFormatException("The last line is a sequence header.");
            }
        }catch (FileNotFoundException e){
            throw e;
        }catch (IOException e){
            throw e;
        }catch (FileFormatException e){
            throw e;
        }
        SequenceFile file = new SequenceFile();
        file.fastxFile.addAll(fastaFile);
        return file;
    }

    @Override
    public boolean canReadFile(String filename) {
        if (filename.contains("fasta")) {
            return true;
        }
        return false;
    }
}