TradeAnalysis
=============

TradeAnalysis.java and TradeAnalysisUpdated.java - Analyze a .csv file to calculate volume, average price, time gap and max price for trades from a 24-hr period given a specially formatted file.

Uses an input.csv, a list of trades with the format: <TimeStamp>,<Symbol>,<Quantity>,<Price>
Analyzes trades by calculating AverageWeightedPrice, MaxTimeGap, Volume, MaxPrice based on 3-character unique identified.
Stores data within a TreeMap for easy access, automatic sorting, and assurance of uniqueness across entries.
Outputs to a "output.csv" with the format: <symbol>,<MaxTimeGap>,<Volume>,<WeightedAveragePrice>,<MaxPrice>

CVSDiff.java - Given two .csv files formatted in the same way, compares the values (column-by-column and row-by-row), outputting a println statement whether or not there is a difference in values anywhere within the file.
