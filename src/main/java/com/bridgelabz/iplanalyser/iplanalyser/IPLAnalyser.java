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

    List<IPLBatsmanStats> iplCSVList = null;
    @SuppressWarnings("unchecked")
    public int loadIPLBattingData(String csvFilePath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            iplCSVList = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader, IPLBatsmanStats.class);
            return iplCSVList.size();
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
            iplCSVList = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader, IPLBatsmanStats.class);
            return iplCSVList.size();
        } catch (IOException | CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.IPL_HEADER_DELIMETER_PROBLEM);
        }
    }

    public String getJsonString(String path) throws IPLAnalyserException {
        try (Writer writer = new FileWriter(path)) {
            checkIPLCSVList();
            Comparator<IPLBatsmanStats> iplBatsmanStatsComparator = Comparator.comparingDouble(runs -> runs.getAverage());
            return convertToJson(iplBatsmanStatsComparator, writer);
        } catch (RuntimeException | IOException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    public String getPlayersWithHighestAverages() throws IPLAnalyserException  {
        String pathForBatsmanAverage = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPLBattingAverage.json";
        try (Writer writer = new FileWriter(pathForBatsmanAverage)) {
            checkIPLCSVList();
            Comparator<IPLBatsmanStats> iplBatsmanStatsComparator = Comparator.comparingDouble(IPLBatsmanStats::getAverage);
            return convertToJson(iplBatsmanStatsComparator, writer);
        } catch (RuntimeException | IOException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    public String getPlayersWithTopStrikeRate() throws IPLAnalyserException {
        String pathForBatsmanStrikeRate = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPLStrikeRate.json";
        try (Writer writer = new FileWriter(pathForBatsmanStrikeRate)) {
            checkIPLCSVList();
            Comparator<IPLBatsmanStats> iplBatsmanStatsComparator = Comparator.comparingDouble(IPLBatsmanStats::getStrikeRate);
            return convertToJson(iplBatsmanStatsComparator, writer);
        } catch (RuntimeException | IOException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    private void descendingSorting(Comparator<IPLBatsmanStats> iplComparator) {
        for (int i = 0; i < iplCSVList.size() - 1; i++) {
            for (int j = 0; j < iplCSVList.size() - i - 1; j++) {
                IPLBatsmanStats compareList1 = iplCSVList.get(j);
                IPLBatsmanStats compareList2 = iplCSVList.get(j + 1);
                if (iplComparator.compare(compareList1, compareList2) < 0) {
                    iplCSVList.set(j, compareList2);
                    iplCSVList.set(j + 1, compareList1);
                }
            }
        }
    }

    public String getPlayersWithMaximumBoundaries() throws IPLAnalyserException {
            String pathForBatsmanWithHighestBoundaries = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPLHighestBoundaries.json";
            try (Writer writer = new FileWriter(pathForBatsmanWithHighestBoundaries)) {
                checkIPLCSVList();
                Comparator<IPLBatsmanStats> iplBatsmanStatsComparator = Comparator.comparingDouble(iplboundaries -> iplboundaries.getSixes() + iplboundaries.getFours());
                return convertToJson(iplBatsmanStatsComparator, writer);
            } catch (RuntimeException | IOException | IPLAnalyserException e) {
                throw new IPLAnalyserException(e.getMessage(),
                        IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
            }
         }

         public void checkIPLCSVList() throws IPLAnalyserException {
             if (iplCSVList == null || iplCSVList.size() == 0) {
                 throw new IPLAnalyserException("No data", IPLAnalyserException.ExceptionType.NO_SUCH_DATA);
             }
         }

         public String convertToJson(Comparator<IPLBatsmanStats> censusComparator, Writer writer) {
             this.descendingSorting(censusComparator);
             String json = new Gson().toJson(iplCSVList);
             Gson gson = new GsonBuilder().create();
             gson.toJson(iplCSVList, writer);
             return json;
         }

    public String getPlayersWithMaximumStrikeRateWithBoundaries() throws IPLAnalyserException {
        String pathForBatsmanWithHighestBoundaries = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPLHighestStrikeRatewithBoundaries.json";
        try (Writer writer = new FileWriter(pathForBatsmanWithHighestBoundaries)) {
            checkIPLCSVList();
            Comparator<IPLBatsmanStats> iplBatsmanStatsComparator = Comparator.comparingDouble(IPLBatsmanStats::getSixes).thenComparing(IPLBatsmanStats::getFours).thenComparing(IPLBatsmanStats::getAverage);
            return convertToJson(iplBatsmanStatsComparator, writer);
        } catch (RuntimeException | IOException | IPLAnalyserException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    public String getPlayersWithGreatAveragesWithBestStrikeRate() throws IPLAnalyserException {
        String pathForBatsmanWithHighestBoundaries = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPLHighestAverageWithBestStrikeRate.json";
        try (Writer writer = new FileWriter(pathForBatsmanWithHighestBoundaries)) {
            checkIPLCSVList();
            Comparator<IPLBatsmanStats> iplBatsmanStatsComparator = Comparator.comparingDouble(IPLBatsmanStats::getAverage).thenComparing(IPLBatsmanStats::getStrikeRate);
            return convertToJson(iplBatsmanStatsComparator, writer);
        } catch (RuntimeException | IOException | IPLAnalyserException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }
}