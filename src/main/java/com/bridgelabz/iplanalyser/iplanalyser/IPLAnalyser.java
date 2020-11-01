package com.bridgelabz.iplanalyser.iplanalyser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.bridgelabz.iplanalyser.censusanalyser.CSVBuilderException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class IPLAnalyser {

    public void welcomeMessage() {
        System.out.println("Welcome to the IPL Analyser Problem");
    }

    List<IPLBatsmanStats> IPLCSVList = null;
    @SuppressWarnings("unchecked")
    public int loadIPLBattingData(String csvFilePath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            IPLCSVList = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader, IPLBatsmanStats.class);
            return IPLCSVList.size();
        } catch (IOException | CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.IPL_HEADER_DELIMETER_PROBLEM);
        }
    }

    @SuppressWarnings("unchecked")
    public int loadIPLBowlingData(String csvFilePath) throws IPLAnalyserException   {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            IPLCSVList = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader, IPLBatsmanStats.class);
            return IPLCSVList.size();
        } catch (IOException | CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.IPL_HEADER_DELIMETER_PROBLEM);
        }
    }
}