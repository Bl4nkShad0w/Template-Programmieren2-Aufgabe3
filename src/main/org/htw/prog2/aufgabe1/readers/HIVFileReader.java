package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.HIVFile;
import org.htw.prog2.aufgabe1.files.SequenceFile;

import java.io.IOException;

public interface HIVFileReader {
    public HIVFile readFile(String filename) throws IOException, FileFormatException;
    public boolean canReadFile(String filename);

}