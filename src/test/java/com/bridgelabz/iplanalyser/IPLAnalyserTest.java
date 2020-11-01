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
}

