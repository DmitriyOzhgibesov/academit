package ru.academits.java.ozhgibesov.matrix;

import ru.academits.java.ozhgibesov.vector.Vector;

public class Matrix {

    Vector[] rows;
    int rowsCount;

    public Matrix(int rowsCount, int columnsCount) {
        this.rowsCount = rowsCount;
        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        this.rowsCount = matrix.getDimensions()[0];
        rows = new Vector[rowsCount];

        for (int i = 0; i < matrix.getDimensions()[0]; i++) {
            rows[i] = new Vector(matrix.getVectorRow(i));
        }
    }

    public Matrix(double[][] matrix) {
        rowsCount = matrix.length;
        rows = new Vector[rowsCount];
        int maxRowLength = getMaxRowLength(matrix);

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(maxRowLength, matrix[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        rowsCount = vectorsArray.length;
        rows = new Vector[rowsCount];
        int maxLineLength = getMaxRowLength(vectorsArray);

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(getMaxRowLength(vectorsArray));

            for (int j = 0; j < maxLineLength; j++) {
                if (j >= vectorsArray[i].getSize()) {
                    rows[i].setComponent(j, 0);
                } else {
                    rows[i].setComponent(j, vectorsArray[i].getComponent(j));
                }
            }
        }
    }

    public int[] getDimensions() {
        return new int[]{rowsCount, rows[0].getSize()};
    }

    public Vector getVectorRow(int index) {
        return rows[index];
    }

    public Vector getVectorColumn(int index) {
        Vector vectorColumn = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            vectorColumn.setComponent(i, rows[i].getComponent(index));
        }

        return vectorColumn;
    }

    public Matrix getTransposition() {
        int rowsCount = this.getDimensions()[1];
        Vector[] vectorArray = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            vectorArray[i] = new Vector(this.getVectorColumn(i));
        }

        return new Matrix(vectorArray);
    }

    public Matrix getScalarMultiplication(double scalar) {
        Vector[] vectorArray = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            vectorArray[i] = new Vector(rows[i].getScalarMultiplication(scalar));
        }

        return new Matrix(vectorArray);
    }

    public double getDeterminant() {
        if (rows.length != rows[0].getSize()) {
            throw new IllegalArgumentException("Невозможно найти определитель не квадратной матрицы");
        }
        if (rowsCount == 1 && rows[0].getSize() == 1) {
            return rows[0].getComponent(0);
        }

        double det = 0;

        for (int j = 0; j < rows.length; j++) {
            if (j % 2 == 0)
                det += rows[0].getComponent(j) * getSubmatrix(this, 0, j).getDeterminant();
            else
                det -= rows[0].getComponent(j) * getSubmatrix(this, 0, j).getDeterminant();
        }

        return det;
    }

    public Matrix getVectorMultiplication(Vector vector) {
        double[][] result = new double[vector.getSize()][1];

        for (int i = 0; i < rowsCount; i++) {
            result[i][0] = Vector.multiplicate(rows[i], vector);
        }

        return new Matrix(result);
    }

    public Matrix getDifference(Matrix matrix) {
        Vector[] vectorArray = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            vectorArray[i] = new Vector(Vector.Difference(rows[i],
                    matrix.rows[i]));
        }

        return new Matrix(vectorArray);
    }

    public Matrix getSum(Matrix matrix) {
        Vector[] vectorArray = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            vectorArray[i] = new Vector(Vector.Sum(rows[i], matrix.rows[i]));
        }

        return new Matrix(vectorArray);
    }

    public static Matrix Diff(Matrix matrix1, Matrix matrix2) {
        Vector[] vectorArray = new Vector[matrix1.rowsCount];

        for (int i = 0; i < matrix1.rowsCount; i++) {
            vectorArray[i] = new Vector(Vector.Difference(matrix1.rows[i],
                    matrix2.rows[i]));
        }

        return new Matrix(vectorArray);
    }

    public static Matrix Sum(Matrix matrix1, Matrix matrix2) {
        Vector[] vectorArray = new Vector[matrix1.rowsCount];

        for (int i = 0; i < matrix1.rowsCount; i++) {
            vectorArray[i] = new Vector(Vector.Sum(matrix1.rows[i],
                    matrix2.rows[i]));
        }

        return new Matrix(vectorArray);
    }

    public static Matrix multiplicate(Matrix matrix1, Matrix matrix2) {
        double[][] result = new double[matrix1.rowsCount][matrix1.rows.length];

        for (int i = 0; i < matrix1.rowsCount; i++) {
            for (int j = 0; j < matrix1.rows.length; j++) {
                result[i][j] = Vector.multiplicate(matrix1.rows[i], matrix2
                        .getVectorColumn(j));
            }
        }

        return new Matrix(result);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (int i = 0; i < rowsCount; i++) {
            if (i != rowsCount - 1) {
                stringBuilder.append(rows[i]);
                stringBuilder.append(", ");
            } else {
                stringBuilder.append(rows[i]);
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    private int getMaxRowLength(double[][] matrix) {
        int maxLineLength = 0;

        for (double[] doubles : matrix) {
            if (doubles.length > maxLineLength) {
                maxLineLength = doubles.length;
            }
        }

        return maxLineLength;
    }

    private int getMaxRowLength(Vector[] linesArray) {
        int maxLineLength = 0;

        for (Vector vector : linesArray) {
            if (vector.getSize() > maxLineLength) {
                maxLineLength = vector.getSize();
            }
        }

        return maxLineLength;
    }

    private Matrix getSubmatrix(Matrix matrix, int rowToExclude, int colToExclude) {
        double[][] values = new double[matrix.rowsCount - 1][matrix.rows.length - 1];

        for (int i = 0; i < matrix.rowsCount; i++) {
            if (i < rows.length - 1) {
                values[i] = new double[matrix.rowsCount - 1];
            }

            for (int j = 0; j < matrix.rows.length; j++)
                if (i != rowToExclude && j != colToExclude)
                    values[i < rowToExclude ? i : i - 1][j < colToExclude ? j : j - 1] = matrix.rows[i].getComponent(j);
        }

        return new Matrix(values);
    }
}
