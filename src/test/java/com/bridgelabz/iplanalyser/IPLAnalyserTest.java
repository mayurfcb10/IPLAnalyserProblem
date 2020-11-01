package com.bridgelabz.iplanalyser;

import com.bridgelabz.iplanalyser.iplanalyser.IPLAnalyser;
import com.bridgelabz.iplanalyser.iplanalyser.IPLAnalyserException;
import com.bridgelabz.iplanalyser.iplanalyser.IPLBatsmanStats;
import com.bridgelabz.iplanalyser.iplanalyser.IPLBowlingStats;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import java.util.List;

public class IPLAnalyserTest {
    public static final String IPL_MOST_RUNS_PATH = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    public static final String IPL_MOST_WICKET_PATH = "E:\\Mayur Zope Contents\\Downloads\\IPLAnalyser\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";
    IPLAnalyser iplAnalyser;

    @Before
    public void setUp() {
        iplAnalyser = new IPLAnalyser();
    }

    @Test
    public void printWelcomeMessage() {
        iplAnalyser.welcomeMessage();
    }

    @Test
    public void givenFilePathForBatsmanshouldReturnCorrectRecords() throws IPLAnalyserException {
        try {
            int entries = iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            Assert.assertEquals(101, entries);
        } catch (IPLAnalyserException e) {
            e.getMessage();
        }
    }

    @Test
    public void givenFilePathForBowlershouldReturnCorrectRecords() throws IPLAnalyserException {
        try {
            int entries = iplAnalyser.loadIPLBowlingData(IPL_MOST_WICKET_PATH);
            Assert.assertEquals(99, entries);
        } catch (IPLAnalyserException e) {
            e.getMessage();
        }
    }

    @Test
    public void givenIPLBatsman_DataShouldReturnBatsmanWithHighestAverage() throws IPLAnalyserException {
            iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            String sortedIPLBattingData = iplAnalyser.getPlayersWithHighestAverages();
            IPLBatsmanStats[] iplBattingData = new Gson().fromJson(sortedIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals("MS Dhoni", iplBattingData[0].getPlayer());
    }

    @Test
    public void givenIPLBatsmanData_ShouldReturnTopStrikeRate() {
        try {
            iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            String sortIPLBattingData = iplAnalyser.getPlayersWithTopStrikeRate();
            IPLBatsmanStats[] iplBattingData = new Gson().fromJson(sortIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals(333.33, iplBattingData[0].getStrikeRate(), 0.0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBatsmanData_ShouldReturnBatsmanWhoHitMaximumBoundaries() {
        try {
            iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            String sortIPLBattingData = iplAnalyser.getPlayersWithMaximumBoundaries();
            IPLBatsmanStats[] iplBattingData = new Gson().fromJson(sortIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals("Andre Russell", iplBattingData[0].getPlayer());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBatsmanData_ShouldReturnBatsmanWhoHasBestStrikeRatesWithBoundaries() {
        try {
            iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            String sortIPLBattingData = iplAnalyser.getPlayersWithMaximumStrikeRateWithBoundaries();
            IPLBatsmanStats[] iplBattingData = new Gson().fromJson(sortIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals("Andre Russell", iplBattingData[0].getPlayer());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBatsmanData_ShouldReturnBatsmanWhoHasBestAveragesWithGreatStrikeRate() {
        try {
            iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            String sortIPLBattingData = iplAnalyser.getPlayersWithGreatAveragesWithBestStrikeRate();
            IPLBatsmanStats[] iplBattingData = new Gson().fromJson(sortIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals("MS Dhoni", iplBattingData[0].getPlayer());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBatsmanData_shouldReturnBatsman_whoHasMaximumRunsWithBestAverages() {
        try {
            iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            String sortIPLBattingData = iplAnalyser.getPlayersWithMaximumRunsAndBestAverages();
            IPLBatsmanStats[] iplBattingData = new Gson().fromJson(sortIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals("David Warner ", iplBattingData[0].getPlayer());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowler_DataShouldReturnBowlerWithHighestAverage() throws IPLAnalyserException {
        iplAnalyser.loadIPLBowlingData(IPL_MOST_WICKET_PATH);
        String sortedIPLBowlingData = iplAnalyser.getPlayersWithHighestBowlingAverages();
        IPLBowlingStats[] iplBowlingData = new Gson().fromJson(sortedIPLBowlingData, IPLBowlingStats[].class);
        Assert.assertEquals("Anukul Roy", iplBowlingData[iplBowlingData.length - 1].getPlayer());
    }

    @Test
    public void givenIPLBowler_DataShouldReturnBowlerWithHighestStrikeRate() throws IPLAnalyserException {
        iplAnalyser.loadIPLBowlingData(IPL_MOST_WICKET_PATH);
        String sortedIPLBowlingData = iplAnalyser.getBowlerWithHighestStrikeRate();
        IPLBowlingStats[] iplBowlingData = new Gson().fromJson(sortedIPLBowlingData, IPLBowlingStats[].class);
        Assert.assertEquals("Alzarri Joseph", iplBowlingData[iplBowlingData.length -1].getPlayer());
    }

    @Test
    public void givenIPLBowlerData_shouldReturnBowlerWithBestEconomy() throws IPLAnalyserException {
        iplAnalyser.loadIPLBowlingData(IPL_MOST_WICKET_PATH);
        String sortedIPLBowlingData = iplAnalyser.getBowlerWithBestEconomy();
        IPLBowlingStats[] iplBowlingData = new Gson().fromJson(sortedIPLBowlingData, IPLBowlingStats[].class);
        Assert.assertEquals("Shivam Dube", iplBowlingData[iplBowlingData.length - 1].getPlayer());
    }

    @Test
    public void givenIPLBowlerData_shouldReturnBowler_withBestStrikeRate_withFoursAndFifers() throws IPLAnalyserException {
        iplAnalyser.loadIPLBowlingData(IPL_MOST_WICKET_PATH);
        String sortedIPLBowlingData = iplAnalyser.getBowlerWithBestStrikeRateWithFoursAndFifers();
        IPLBowlingStats[] iplBowlingData = new Gson().fromJson(sortedIPLBowlingData, IPLBowlingStats[].class);
        Assert.assertEquals("Alzarri Joseph", iplBowlingData[iplBowlingData.length -1].getPlayer());
    }

    @Test
    public void givenIPLBowlerData_shouldReturnBowler_greatBowlingAverage_followedbyBestStrikeRate() throws IPLAnalyserException {
        iplAnalyser.loadIPLBowlingData(IPL_MOST_WICKET_PATH);
        String sortedIPLBowlingData = iplAnalyser.getBowlerWithBestAverageAndStrikeRate();
        IPLBowlingStats[] iplBowlingData = new Gson().fromJson(sortedIPLBowlingData, IPLBowlingStats[].class);
        Assert.assertEquals("Alzarri Joseph", iplBowlingData[iplBowlingData.length -1].getPlayer());
    }

    @Test
    public void givenIPLBowlerData_shouldReturn_BowlerwithMaximumWicketsAndGreatBowlingAverage() throws IPLAnalyserException {
        iplAnalyser.loadIPLBowlingData(IPL_MOST_WICKET_PATH);
        String sortedIPLBowlingData = iplAnalyser.getBowlerWithMaximumWicketsAndAverage();
        IPLBowlingStats[] iplBowlingData = new Gson().fromJson(sortedIPLBowlingData, IPLBowlingStats[].class);
        Assert.assertEquals("Imran Tahir", iplBowlingData[0].getPlayer());
    }

    @Test
    public void givenIPLBowlerData_AndBatsmanData_shouldReturn_PlayerListWithGreatAverage() throws IPLAnalyserException {
        iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
        String sortedIPLBattingData = iplAnalyser.getPlayersWithHighestAverages();
        IPLBatsmanStats[] iplBattingAverage = new Gson().fromJson(sortedIPLBattingData, IPLBatsmanStats[].class);
        iplAnalyser.loadIPLBowlingData(IPL_MOST_WICKET_PATH);
        String sortedIPLBowlingData = iplAnalyser.getPlayersWithHighestBowlingAverages();
        IPLBowlingStats[] iplBowlingAverage = new Gson().fromJson(sortedIPLBowlingData, IPLBowlingStats[].class);
        List<String> getAverageByBatAndBowl =iplAnalyser.getPLayerDataByBatAndBall(iplBattingAverage, iplBowlingAverage);
        Assert.assertEquals("Andre Russell", getAverageByBatAndBowl.get(0));
    }

    @Test
    public void givenIPLBowlerData_AndBatsmanData_shouldReturn_BestAllRounder() throws IPLAnalyserException {
        iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
        String sortedIPLBattingData = iplAnalyser.getPlayersWithMaximumRunsAndBestAverages();
        IPLBatsmanStats[] iplBattingData = new Gson().fromJson(sortedIPLBattingData, IPLBatsmanStats[].class);
        iplAnalyser.loadIPLBowlingData(IPL_MOST_WICKET_PATH);
        String sortedIPLBowlingData = iplAnalyser.getBowlerWithMaximumWicketsAndAverage();
        IPLBowlingStats[] iplBowlingData = new Gson().fromJson(sortedIPLBowlingData, IPLBowlingStats[].class);
        List<String> getDataByBatAndBowl =iplAnalyser.getPLayerDataByBatAndBall(iplBattingData, iplBowlingData);
        Assert.assertEquals("Andre Russell", getDataByBatAndBowl.get(0));
    }

    @Test
    public void givenIPLBattingData_shouldreturn_PlayerWithHighestCenturyWithBestAverage() {
        try {
            iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            String sortIPLBattingData = iplAnalyser.getPlayersWithHighestCenturyAndGreatAverages();
            IPLBatsmanStats[] iplBattingData = new Gson().fromJson(sortIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals("David Warner ", iplBattingData[0].getPlayer());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBattingData_shouldReturn_TopAverageBatsman_WithoutCenturyOrHalfCentury() throws IPLAnalyserException {
        iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
        String sortedIPLBattingDataCentury = iplAnalyser.getPlayersWithNoCenturyAndFiftiesButGreatAverages();
        IPLBatsmanStats[] iplBattingData = new Gson().fromJson(sortedIPLBattingDataCentury, IPLBatsmanStats[].class);
        Assert.assertEquals("Marcus Stoinis", iplBattingData[0].getPlayer());
    }
}

