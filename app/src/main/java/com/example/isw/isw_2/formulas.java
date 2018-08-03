package com.example.isw.isw_2;

import com.github.mikephil.charting.data.Entry;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class formulas {

            double assetValue, strikeValue, sig2, timeToExpiry, r;
            public formulas(double asset, double strike, double volatility,
                            double time, double interest) {
                assetValue = asset;
                strikeValue = strike;
                sig2 = volatility;
                timeToExpiry = time;
                r = interest;
            }

            public double getMonteCarlo() {
                Random rand = new Random();
                int numTimeSteps = 100;
                int numSimulationRuns = 5000;
                double timestep = timeToExpiry / numTimeSteps;
                double sumOfValues = 0;
                for (int j = 0; j < numSimulationRuns; j++) {
                    double asset = assetValue;
                    for (int i = 0; i < numTimeSteps; i++) {
                        asset = asset * Math.exp((r - 0.5 * sig2) * timestep + Math.sqrt(sig2 * timestep) * rand.nextGaussian());
                    }
                    sumOfValues += Math.max(asset - strikeValue, 0);
                }
                double value = sumOfValues / numSimulationRuns * Math.exp(-r * timeToExpiry);
                return value;
            }

    public ArrayList getMonteCarloGraphA() {
        ArrayList<Entry> yvalues = new ArrayList<>();
        Random rand = new Random();
        int numTimeSteps = 100;
        double timestep = timeToExpiry / numTimeSteps;
            double asset = assetValue;
            for (int i = 0; i < numTimeSteps; i++) {
                asset = asset * Math.exp((r - 0.5 * sig2) * timestep + Math.sqrt(sig2 * timestep) * rand.nextGaussian());
                yvalues.add(new Entry(i, (float) asset));
            }
        return yvalues;
    }


            public double getMonteCarlo2() {
                Random rand = new Random();
                int numTimeSteps = 100;
                int numSimulationRuns = 5000;
                double timestep = timeToExpiry / numTimeSteps;
                double sumOfValues = 0;
                for (int j = 0; j < numSimulationRuns; j++) {
                    double asset = assetValue;
                    for (int i = 0; i < numTimeSteps; i++) {
                        asset = asset * Math.exp((r - 0.5 * sig2) * timestep + Math.sqrt(sig2 * timestep) * rand.nextGaussian());
                    }
                    sumOfValues += Math.max(strikeValue - asset, 0);
                }
                double value = sumOfValues / numSimulationRuns * Math.exp(-r * timeToExpiry);
                return value;
            }



    double CNDF(double x)
            {
                int neg = (x < 0d) ? 1 : 0;
                if ( neg == 1)
                    x *= -1d;

                double k = (1d / ( 1d + 0.2316419 * x));
                double y = (((( 1.330274429 * k - 1.821255978) * k + 1.781477937) *
                        k - 0.356563782) * k + 0.319381530) * k;
                y = 1.0 - 0.398942280401 * Math.exp(-0.5 * x * x) * y;

                return (1d - neg) * y + neg * (1d - y);
            }

            public double getBlackScholesSolutionC() {
                double d1 = d1();
                double d2 = d2();
                double Nd1 = CNDF(d1);
                double Nd2 = CNDF(d2);
                double v = assetValue * Nd1 - strikeValue * Math.exp(-r * timeToExpiry) * Nd2;
                return v;
            }

            public double getBlackScholesSolutionP(){
                double d1 = d1();
                double d2 = d2();
                double Nd1 = CNDF(-d1);
                double Nd2 = CNDF(-d2);
                double v = strikeValue * Math.exp(-r * timeToExpiry) * Nd2 - assetValue * Nd1;
                return v;
            }

            public double d1() {
                double d1 = (Math.log(assetValue / strikeValue) + (r + 0.5 * sig2) * timeToExpiry) / (Math.sqrt(sig2 * timeToExpiry));
                return d1;
            }
            public double d2() {
                double d2 = (Math.log(assetValue / strikeValue) + (r - 0.5 * sig2) * timeToExpiry) / (Math.sqrt(sig2 * timeToExpiry));
                return d2;
            }


        }