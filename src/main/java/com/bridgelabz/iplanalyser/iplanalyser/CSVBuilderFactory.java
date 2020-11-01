package com.bridgelabz.iplanalyser.iplanalyser;

import com.bridgelabz.iplanalyser.censusanalyser.ICSVBuilder;
import com.bridgelabz.iplanalyser.censusanalyser.OpenCSVBuilder;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder();
    }
}