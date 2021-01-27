package ru.academits.java.ozhgibesov.range;

public class Range {
    private double from;
    private double to;

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

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range[] getUnion(Range otherRange) {
        Range[] arrayRange = new Range[]{null};

        if (this.getFrom() <= otherRange.getFrom() && this.getTo() <= otherRange.getTo() && otherRange.getFrom() <= this.getTo()) {
            arrayRange = new Range[]{new Range(this.getFrom(), otherRange.getTo())};
        } else if (otherRange.getFrom() <= this.getFrom() && otherRange.getTo() <= this.getTo() && this.getFrom() <= otherRange.getTo()) {
            arrayRange = new Range[]{new Range(otherRange.getFrom(), this.getTo())};
        } else if (this.getFrom() <= otherRange.getFrom() && this.getTo() >= otherRange.getTo()) {
            arrayRange = new Range[]{new Range(this.getFrom(), this.getTo())};
        } else if (otherRange.getFrom() <= this.getFrom() && otherRange.getTo() >= this.getTo()) {
            arrayRange = new Range[]{new Range(otherRange.getFrom(), otherRange.getTo())};
        } else if (this.getFrom() <= otherRange.getFrom() && this.getTo() <= otherRange.getTo() && otherRange.getFrom() > this.getTo()) {
            arrayRange = new Range[2];
            arrayRange[0] = new Range(this.getFrom(), this.getTo());
            arrayRange[1] = new Range(otherRange.getFrom(), otherRange.getTo());
        } else if (otherRange.getFrom() <= this.getFrom() && otherRange.getTo() <= this.getTo() && this.getFrom() > otherRange.getTo()) {
            arrayRange = new Range[2];
            arrayRange[0] = new Range(otherRange.getFrom(), otherRange.getTo());
            arrayRange[1] = new Range(this.getFrom(), this.getTo());
        }

        return arrayRange;
    }

    public Range getIntersection(Range otherRange) {
        if (this.getFrom() <= otherRange.getFrom() && this.getTo() < otherRange.getTo() && otherRange.getFrom() < this.getTo()) {
            return new Range(otherRange.getFrom(), this.getTo());
        } else if (otherRange.getFrom() <= this.getFrom() && this.getTo() > otherRange.getTo() && this.getFrom() < otherRange.getTo()) {
            return new Range(this.getFrom(), otherRange.getTo());
        } else if (this.getFrom() <= otherRange.getFrom() && otherRange.getTo() <= this.getTo()) {
            return new Range(otherRange.getFrom(), otherRange.getTo());
        } else if (otherRange.getFrom() <= this.getFrom() && this.getTo() <= otherRange.getTo()) {
            return new Range(this.getFrom(), this.getTo());
        }

        return null;
    }

    public Range[] getSubtraction(Range otherRange) {
        Range[] arrayRange = new Range[]{null};

        if (this.getFrom() < otherRange.getFrom() && this.getTo() < otherRange.getTo() && otherRange.getFrom() < this.getTo()) {
            arrayRange = new Range[]{new Range(this.getFrom(), otherRange.getFrom())};
        } else if (otherRange.getFrom() < this.getFrom() && otherRange.getTo() < this.getTo() && this.getFrom() < otherRange.getTo()) {
            arrayRange = new Range[]{new Range(otherRange.getTo(), this.getTo())};
        } else if (this.getFrom() < otherRange.getFrom() && this.getTo() > otherRange.getTo()) {
            arrayRange = new Range[2];
            arrayRange[0] = new Range(this.getFrom(), otherRange.getFrom());
            arrayRange[1] = new Range(otherRange.getTo(), this.getTo());
        }

        return arrayRange;
    }
}