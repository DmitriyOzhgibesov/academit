package ru.academits.java.ozhgibesov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range[] getUnion(Range range) {
        if (Math.min(to, range.to) >= Math.max(from, range.from)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }

        return new Range[]{
                new Range(Math.min(from, range.from), Math.min(to, range.to)),
                new Range(Math.max(from, range.from), Math.max(to, range.to))
        };
    }

    public Range getIntersection(Range range) {
        double newFrom = Math.max(from, range.from);
        double newTo = Math.min(to, range.to);

        if (newTo <= newFrom) {
            return null;
        }

        return new Range(newFrom, newTo);
    }

    public Range[] getDifference(Range range) {
        if (range.from >= to || range.to <= from) {
            return new Range[]{new Range(from, to)};
        }
        if (range.to > from && range.from <= from && to > range.to) {
            return new Range[]{new Range(range.to, to)};
        }
        if (to >= range.from && from < range.from && range.to >= to) {
            return new Range[]{new Range(from, range.from)};
        }
        if (from < range.from && to > range.to) {
            return new Range[]{
                    new Range(from, range.from),
                    new Range(range.to, to)};
        }

        return new Range[0];
    }

    @Override
    public String toString() {
        return String.format("(%.2f; %.2f)", from, to);
    }
}