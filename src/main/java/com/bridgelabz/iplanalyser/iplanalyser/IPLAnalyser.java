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

    List<IPLBatsmanStats> iplCSVListBastman = null;
    List<IPLBowlingStats> iplCSVListBowler = null;
    @SuppressWarnings("unchecked")
    public int loadIPLBattingData(String csvFilePath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            iplCSVListBastman = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader, IPLBatsmanStats.class);
            return iplCSVListBastman.size();
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
            iplCSVListBowler = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader, IPLBowlingStats.class);
            return iplCSVListBowler.size();
        } catch (IOException | CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.IPL_HEADER_DELIMETER_PROBLEM);
        }
    }

    public String getPlayersWithHighestAverages() throws IPLAnalyserException  {
        String pathForBatsmanAverage = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPLBattingAverage.json";
        try (Writer writer = new FileWriter(pathForBatsmanAverage)) {
            checkIPLCSVList();
            Comparator<IPLBatsmanStats> iplBatsmanStatsComparator = Comparator.comparingDouble(IPLBatsmanStats::getAverage);
            return convertToJsonBastman(iplBatsmanStatsComparator, writer);
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
            return convertToJsonBastman(iplBatsmanStatsComparator, writer);
        } catch (RuntimeException | IOException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    private void descendingSorting(Comparator<IPLBatsmanStats> iplComparator) {
        for (int i = 0; i < iplCSVListBastman.size() - 1; i++) {
            for (int j = 0; j < iplCSVListBastman.size() - i - 1; j++) {
                IPLBatsmanStats compareList1 = iplCSVListBastman.get(j);
                IPLBatsmanStats compareList2 = iplCSVListBastman.get(j + 1);
                if (iplComparator.compare(compareList1, compareList2) < 0) {
                    iplCSVListBastman.set(j, compareList2);
                    iplCSVListBastman.set(j + 1, compareList1);
                }
            }
        }
    }

    public String getPlayersWithMaximumBoundaries() throws IPLAnalyserException {
            String pathForBatsmanWithHighestBoundaries = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPLHighestBoundaries.json";
            try (Writer writer = new FileWriter(pathForBatsmanWithHighestBoundaries)) {
                checkIPLCSVList();
                Comparator<IPLBatsmanStats> iplBatsmanStatsComparator = Comparator.comparingDouble(iplboundaries -> iplboundaries.getSixes() + iplboundaries.getFours());
                return convertToJsonBastman(iplBatsmanStatsComparator, writer);
            } catch (RuntimeException | IOException | IPLAnalyserException e) {
                throw new IPLAnalyserException(e.getMessage(),
                        IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
            }
         }

         public void checkIPLCSVList() throws IPLAnalyserException {
             if (iplCSVListBastman == null || iplCSVListBastman.size() == 0) {
                 throw new IPLAnalyserException("No data", IPLAnalyserException.ExceptionType.NO_SUCH_DATA);
             }
         }

         public String convertToJsonBastman(Comparator<IPLBatsmanStats> censusComparator, Writer writer) {
             this.descendingSorting(censusComparator);
             String json = new Gson().toJson(iplCSVListBastman);
             Gson gson = new GsonBuilder().create();
             gson.toJson(iplCSVListBastman, writer);
             return json;
         }

    public String getPlayersWithMaximumStrikeRateWithBoundaries() throws IPLAnalyserException {
        String pathForBatsmanWithHighestBoundaries = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPLHighestStrikeRatewithBoundaries.json";
        try (Writer writer = new FileWriter(pathForBatsmanWithHighestBoundaries)) {
            checkIPLCSVList();
            Comparator<IPLBatsmanStats> iplBatsmanStatsComparator = Comparator.comparingDouble(IPLBatsmanStats::getSixes).thenComparing(IPLBatsmanStats::getFours).thenComparing(IPLBatsmanStats::getAverage);
            return convertToJsonBastman(iplBatsmanStatsComparator, writer);
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
            return convertToJsonBastman(iplBatsmanStatsComparator, writer);
        } catch (RuntimeException | IOException | IPLAnalyserException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    public String getPlayersWithMaximumRunsAndBestAverages() throws IPLAnalyserException {
        String pathForBatsmanWithHighestBoundaries = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPLHighestRunsWithBestAverage.json";
        try (Writer writer = new FileWriter(pathForBatsmanWithHighestBoundaries)) {
            checkIPLCSVList();
            Comparator<IPLBatsmanStats> iplBatsmanStatsComparator = Comparator.comparingDouble(IPLBatsmanStats::getRuns).thenComparing(IPLBatsmanStats::getAverage);
            return convertToJsonBastman(iplBatsmanStatsComparator, writer);
        } catch (RuntimeException | IOException | IPLAnalyserException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    private void checkIPLCSVListBowler() throws IPLAnalyserException {
        if (iplCSVListBowler == null || iplCSVListBowler.size() == 0) {
            throw new IPLAnalyserException("No data", IPLAnalyserException.ExceptionType.NO_SUCH_DATA);
        }
    }

    private void descendingSortingBowler(Comparator<IPLBowlingStats> iplComparator) {
        for (int i = 0; i < iplCSVListBowler.size() - 1; i++) {
            for (int j = 0; j < iplCSVListBowler.size() - i - 1; j++) {
                IPLBowlingStats compareList1 = iplCSVListBowler.get(j);
                IPLBowlingStats compareList2 = iplCSVListBowler.get(j + 1);
                if (iplComparator.compare(compareList1, compareList2) < 0) {
                    iplCSVListBowler.set(j, compareList2);
                    iplCSVListBowler.set(j + 1, compareList1);
                }
            }
        }
    }

    public String convertToJsonBowler(Comparator<IPLBowlingStats> censusComparator, Writer writer) {
        this.descendingSortingBowler(censusComparator);
        String json = new Gson().toJson(iplCSVListBowler);
        Gson gson = new GsonBuilder().create();
        gson.toJson(iplCSVListBastman, writer);
        return json;
    }

    public String getPlayersWithHighestBowlingAverages() throws IPLAnalyserException {
        String pathForBowlingAverage = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPLBowlingAverage.json";
        try (Writer writer = new FileWriter(pathForBowlingAverage)) {
            checkIPLCSVListBowler();
            Comparator<IPLBowlingStats> iplBowlingStatsComparator = Comparator.comparingDouble(IPLBowlingStats::getAverage);
            return convertToJsonBowler(iplBowlingStatsComparator, writer);
        } catch (RuntimeException | IOException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    public String getBowlerWithHighestStrikeRate() throws IPLAnalyserException {
        String pathForBowlingAverage = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPLBowlerStrikeRate.json";
        try (Writer writer = new FileWriter(pathForBowlingAverage)) {
            checkIPLCSVListBowler();
            Comparator<IPLBowlingStats> iplBowlingStatsComparator = Comparator.comparingDouble(IPLBowlingStats::getStrikeRate);
            return convertToJsonBowler(iplBowlingStatsComparator, writer);
        } catch (RuntimeException | IOException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    public String getBowlerWithBestEconomy() throws IPLAnalyserException {
        String pathForBowlingAverage = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPLBowlerBestEconomy.json";
        try (Writer writer = new FileWriter(pathForBowlingAverage)) {
            checkIPLCSVListBowler();
            Comparator<IPLBowlingStats> iplBowlingStatsComparator = Comparator.comparingDouble(IPLBowlingStats::getEconomy);
            return convertToJsonBowler(iplBowlingStatsComparator, writer);
        } catch (RuntimeException | IOException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }
}