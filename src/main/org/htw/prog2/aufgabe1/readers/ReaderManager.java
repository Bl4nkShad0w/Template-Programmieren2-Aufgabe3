package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.exceptions.NoValidReadersException;
import org.htw.prog2.aufgabe1.files.SequenceFile;

import java.io.IOException;
import java.util.ArrayList;

public class ReaderManager<T extends HIVFileReader> {
    ArrayList<T> reader = new ArrayList<>();

    public void addReader(T reader) {
        this.reader.add(reader);
    }

    public T getReaderForFile(String filename) throws NoValidReadersException, FileFormatException, IOException {
        if (filename.contains("csv")){
            for (int i = 0; i < this.reader.size(); i++) {
                if (this.reader.get(i) instanceof CSVFileReader) {
                    return this.reader.get(i);
                }
            }
        }
        if (filename.contains("fasta")){
            for (int i = 0; i < this.reader.size(); i++) {
                if (this.reader.get(i) instanceof FASTAFileReader) {
                    return this.reader.get(i);
                }
            }
        }
        if (filename.contains("fastq")){
            for (int i = 0; i < this.reader.size(); i++) {
                if (this.reader.get(i) instanceof FASTQFileReader) {
                    return this.reader.get(i);
                }
            }
        }

        throw new NoValidReadersException();
    }
}