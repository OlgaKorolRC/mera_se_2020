package com.mera.gokhmak.les11;

import java.util.function.BiFunction;

public class Operation implements BiFunction<Double,Double,Double> {
    private String shortName = "";
    private BiFunction<Double, Double, Double> bf = null;

    Operation(String name) {
        this.shortName = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public Double apply(Double aDouble, Double aDouble2) {
        if (bf != null) {
            return bf.apply(aDouble, aDouble2);
        }
        return null;
    }

    public Operation setOperation(BiFunction<Double, Double, Double> op) {
        bf = op;
        return this;
    }
}
