package ru.academits.java.ozhgibesov.matrix;

import ru.academits.java.ozhgibesov.vector.Vector;

public class Matrix {
    Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        rows = new Vector[rowsCount];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(matrix.getVectorRow(i));
        }
    }

    public Matrix(double[][] matrixArray) {
        rows = new Vector[matrixArray.length];
        int maxRowLength = getMaxRowLength(matrixArray);

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(maxRowLength, matrixArray[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        rows = new Vector[vectorsArray.length];
        int maxRowLength = getMaxRowLength(vectorsArray);

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(getMaxRowLength(vectorsArray));

            for (int j = 0; j < maxRowLength; j++) {
                if (j >= vectorsArray[i].getSize()) {
                    rows[i].setComponent(j, 0);
                } else {
                    rows[i].setComponent(j, vectorsArray[i].getComponent(j));
                }
            }
        }
    }

    public int[] getDimensions() {
        return new int[]{rows.length, rows[0].getSize()};
    }

    public Vector getVectorRow(int index) {
        return rows[index];
    }

    public Vector getVectorColumn(int index) {
        if (index > rows[0].getSize() - 1 || index < 0) {
            throw new IllegalArgumentException("Индекс выходит за рамки количества колонок матрицы...");
        }

        Vector vectorColumn = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            vectorColumn.setComponent(i, rows[i].getComponent(index));
        }

        return vectorColumn;
    }

    public Matrix getTransposition() {
        int newRowsCount = rows[0].getSize();
        Vector[] vectorArray = new Vector[newRowsCount];

        for (int i = 0; i < newRowsCount; i++) {
            vectorArray[i] = new Vector(this.getVectorColumn(i));
        }

        return new Matrix(vectorArray);
    }

    public Matrix getScalarMultiplication(double scalar) {
        Vector[] vectorArray = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            vectorArray[i] = new Vector(rows[i].multiplyScalar(scalar));
        }

        return new Matrix(vectorArray);
    }

    public double getDeterminant() {
        if (rows.length != rows[0].getSize()) {
            throw new IllegalArgumentException("Невозможно найти определитель не квадратной матрицы");
        }
        if (rows.length == 1 && rows[0].getSize() == 1) {
            return rows[0].getComponent(0);
        }

        double det = 0;

        for (int j = 0; j < rows.length; j++) {
            if (j % 2 == 0)
                det += rows[0].getComponent(j) * getSubmatrix(this, j).getDeterminant();
            else
                det -= rows[0].getComponent(j) * getSubmatrix(this, j).getDeterminant();
        }

        return det;
    }

    public Matrix getVectorMultiplication(Vector vector) {
        double[][] result = new double[vector.getSize()][1];

        for (int i = 0; i < rows.length; i++) {
            result[i][0] = Vector.getScalarMultiplication(rows[i], vector);
        }

        return new Matrix(result);
    }

    public Matrix getDifference(Matrix matrix) {
        Vector[] vectorArray = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            vectorArray[i] = new Vector(Vector.getDifference(rows[i],
                    matrix.rows[i]));
        }

        return new Matrix(vectorArray);
    }

    public Matrix getSum(Matrix matrix) {
        Vector[] vectorArray = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            vectorArray[i] = new Vector(Vector.getSum(rows[i], matrix.rows[i]));
        }

        return new Matrix(vectorArray);
    }

    public static Matrix Diff(Matrix matrix1, Matrix matrix2) {
        Vector[] vectorArray = new Vector[matrix1.rows.length];

        for (int i = 0; i < matrix1.rows.length; i++) {
            vectorArray[i] = new Vector(Vector.getDifference(matrix1.rows[i],
                    matrix2.rows[i]));
        }

        return new Matrix(vectorArray);
    }

    public static Matrix Sum(Matrix matrix1, Matrix matrix2) {
        Vector[] vectorArray = new Vector[matrix1.rows.length];

        for (int i = 0; i < matrix1.rows.length; i++) {
            vectorArray[i] = new Vector(Vector.getSum(matrix1.rows[i],
                    matrix2.rows[i]));
        }

        return new Matrix(vectorArray);
    }

    public static Matrix multiplicate(Matrix matrix1, Matrix matrix2) {
        double[][] result = new double[matrix1.rows.length][matrix1.rows.length];

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix1.rows.length; j++) {
                result[i][j] = Vector.getScalarMultiplication(matrix1.rows[i], matrix2
                        .getVectorColumn(j));
            }
        }

        return new Matrix(result);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < rows.length; i++) {
            if (i != rows.length - 1) {
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

    private int getMaxRowLength(Vector[] rows) {
        int maxRowLength = 0;

        for (Vector vector : rows) {
            if (vector.getSize() > maxRowLength) {
                maxRowLength = vector.getSize();
            }
        }

        return maxRowLength;
    }

    private Matrix getSubmatrix(Matrix matrix, int colToExclude) {
        double[][] values = new double[matrix.rows.length - 1][matrix.rows.length - 1];

        for (int i = 0; i < matrix.rows.length; i++) {
            if (i < rows.length - 1) {
                values[i] = new double[matrix.rows.length - 1];
            }

            for (int j = 0; j < matrix.rows.length; j++)
                if (i != 0 && j != colToExclude)
                    values[i - 1][j < colToExclude ? j : j - 1] = matrix.rows[i].getComponent(j);
        }

        return new Matrix(values);
    }
}