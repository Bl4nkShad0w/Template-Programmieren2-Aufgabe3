package org.htw.prog2.aufgabe1.files;

import java.util.ArrayList;
import java.util.LinkedList;

public class MutationFile implements HIVFile {
    public ArrayList<String> drugs = new ArrayList<>();
    public ArrayList<String[]> mutPatData = new ArrayList<>();

    public MutationFile() {

    }

    public void addDrug(String drug) {
        this.drugs.add(drug);
    }

    public LinkedList<String> getDrugs(){

        return null;
    }

    public void addMutation(Mutation variant) {

    }

    public LinkedList<Mutation> getMutations() {

        return null;
    }

    public int getNumberOfMutations() {
        return mutPatData.size()-1;
    }
}