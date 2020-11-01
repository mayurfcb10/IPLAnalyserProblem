package com.bridgelabz.iplanalyser;

import com.bridgelabz.iplanalyser.iplanalyser.IPLAnalyser;
import com.bridgelabz.iplanalyser.iplanalyser.IPLAnalyserException;
import com.bridgelabz.iplanalyser.iplanalyser.IPLBatsmanStats;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

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
            IPLBatsmanStats[] iplRuns = new Gson().fromJson(sortedIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals("MS Dhoni", iplRuns[0].getPlayer());
    }

    @Test
    public void givenIPLBatsmanData_ShouldReturnTopStrikeRate() {
        try {
            iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            String sortIPLBattingData = iplAnalyser.getPlayersWithTopStrikeRate();
            IPLBatsmanStats[] iplRuns = new Gson().fromJson(sortIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals(333.33, iplRuns[0].getStrikeRate(), 0.0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBatsmanData_ShouldReturnBatsmanWhoHitMaximumBoundaries() {
        try {
            iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            String sortIPLBattingData = iplAnalyser.getPlayersWithMaximumBoundaries();
            IPLBatsmanStats[] iplRuns = new Gson().fromJson(sortIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals("Andre Russell", iplRuns[0].getPlayer());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBatsmanData_ShouldReturnBatsmanWhoHasBestStrikeRatesWithBoundaries() {
        try {
            iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            String sortIPLBattingData = iplAnalyser.getPlayersWithMaximumStrikeRateWithBoundaries();
            IPLBatsmanStats[] iplRuns = new Gson().fromJson(sortIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals("Andre Russell", iplRuns[0].getPlayer());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBatsmanData_ShouldReturnBatsmanWhoHasBestAveragesWithGreatStrikeRate() {
        try {
            iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            String sortIPLBattingData = iplAnalyser.getPlayersWithGreatAveragesWithBestStrikeRate();
            IPLBatsmanStats[] iplRuns = new Gson().fromJson(sortIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals("MS Dhoni", iplRuns[0].getPlayer());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBatsmanData_shouldReturnBatsman_whoHasMaximumRunsWithBestAverages() {
        try {
            iplAnalyser.loadIPLBattingData(IPL_MOST_RUNS_PATH);
            String sortIPLBattingData = iplAnalyser.getPlayersWithMaximumRunsAndBestAverages();
            IPLBatsmanStats[] iplRuns = new Gson().fromJson(sortIPLBattingData, IPLBatsmanStats[].class);
            Assert.assertEquals("David Warner ", iplRuns[0].getPlayer());
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }
}

