package org.htw.prog2.aufgabe1.files;

import java.util.ArrayList;
import java.util.HashSet;

public class SequenceFile implements HIVFile {
    HashSet<String> segs = new HashSet<>();
    public ArrayList<String> fastxFile = new ArrayList<>();

    public void addSequence(String sequence){

    }

    public HashSet<String> getSequences(){
        HashSet<String> sequences = new HashSet<>();
        for (int i = 1; i < this.fastxFile.size(); i += 2) {
            sequences.add(this.fastxFile.get(i));
        }
        this.segs = sequences;
        return sequences;
    }

    public String getFirstSequence() {
        return this.fastxFile.get(1);
    }

    public int getNumberOfSequences() {
        return this.fastxFile.size()/2;
    }
}
