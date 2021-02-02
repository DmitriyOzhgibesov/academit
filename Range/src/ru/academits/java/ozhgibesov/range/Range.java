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
        if (Math.min(Math.max(from, to), Math.max(range.getFrom(), range.getTo())) >=
                Math.max(Math.min(from, to), Math.min(range.getFrom(), range.getTo()))) {
            return new Range[]{new Range(Math.min(from, range.getFrom()), Math.max(to, range.getTo()))};
        }

        return new Range[]{new Range(Math.min(from, range.getFrom()), Math.min(to, range.getTo())),
                new Range(Math.max(from, range.getFrom()), Math.max(to, range.getTo()))};
    }

    public Range getIntersection(Range range) {

        if (Math.min(Math.max(from, to), Math.max(range.getFrom(), range.getTo())) >
                Math.max(Math.min(from, to), Math.min(range.getFrom(), range.getTo()))) {
            return new Range(Math.max(from, range.getFrom()), Math.min(to, range.getTo()));
        }
        return null;
    }

    public Range[] getDifference(Range range) {
        double epsilon = 0.01;

        if (from < range.getFrom() && to < range.getTo() && range.getFrom() < to) {
            return new Range[]{new Range(from, range.getFrom() - epsilon)};
        }
        if (from == range.getFrom() && to > range.getTo()) {
            return new Range[]{new Range(range.getTo() + epsilon, to)};
        }
        if (from < range.getFrom() && to == range.getTo()) {
            return new Range[]{new Range(from, range.getFrom() - epsilon)};
        }
        if (range.getFrom() < from && range.getTo() < to && from < range.getTo()) {
            return new Range[]{new Range(range.getTo() + epsilon, to)};
        }
        if (from < range.getFrom() && to > range.getTo()) {
            return new Range[]{new Range(from, range.getFrom() - epsilon), new Range(range.getTo() + epsilon, to)};
        }

        return new Range[0];
    }

    @Override
    public String toString() {
        return String.format("(%.2f;%.2f)", from, to);
    }
}