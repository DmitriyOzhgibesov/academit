package ru.academits.java.ozhgibesov.matrix;

import ru.academits.java.ozhgibesov.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("rowsCount = " + rowsCount + ". Количество строк не может быть <= 0");
        }
        if (columnsCount <= 0) {
            throw new IllegalArgumentException("columnsCount = " + columnsCount + ". Количество столбцов не может быть <= 0");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(matrix.getRow(i));
        }
    }

    public Matrix(double[][] matrixArray) {
        if (matrixArray.length == 0) {
            throw new IllegalArgumentException("Кол-во строк = " + matrixArray.length + ". Количество строк не может быть = 0");
        }

        rows = new Vector[matrixArray.length];
        int maxRowLength = getMaxRowLength(matrixArray);

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(maxRowLength, matrixArray[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("Кол-во строк = " + vectorsArray.length + ". Количество строк не может быть = 0");
        }

        rows = new Vector[vectorsArray.length];
        int maxRowLength = getMaxRowLength(vectorsArray);

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(maxRowLength);

            for (int j = 0; j < maxRowLength; j++) {
                if (j < vectorsArray[i].getSize()) {
                    rows[i].setComponent(j, vectorsArray[i].getComponent(j));
                }
            }
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("Index = " + index + ". Индекс выходит за рамки диапазона (0;" + rows.length + ")");
        }

        return new Vector(rows[index]);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new ArrayIndexOutOfBoundsException("Index = " + index + ". Индекс выходит за рамки диапазона (0;" + this.getColumnsCount() + ")");
        }

        Vector vectorColumn = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            vectorColumn.setComponent(i, rows[i].getComponent(index));
        }

        return vectorColumn;
    }

    public void setRow(int index, Vector vector) {
        if (vector.getSize() != this.getColumnsCount()) {
            throw new IllegalArgumentException("Количество элементов вектора равно " + vector.getSize() +
                    "; Количество элементов вектора дожно быть равно" + this.getColumnsCount());
        }
        if (index < 0 || index >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("Index = " + index + ". Индекс выходит за рамки диапазона (0;" + rows.length + ")");
        }

        rows[index] = new Vector(vector);
    }

    public Matrix transpose() {
        int newRowsCount = this.getColumnsCount();
        Vector[] newRows = new Vector[newRowsCount];

        for (int i = 0; i < newRowsCount; i++) {
            newRows[i] = new Vector(this.getColumn(i));
        }

        rows = newRows;
        return this;
    }

    public Matrix multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }

        return this;
    }

    public double getDeterminant() {
        if (rows.length != this.getColumnsCount()) {
            throw new UnsupportedOperationException("Невозможно найти определитель не квадратной матрицы; Размер текущей матрицы (" + this.getColumnsCount());
        }
        if (rows.length == 1) {
            return rows[0].getComponent(0);
        }

        double determinant = 0;

        for (int j = 0; j < rows.length; j++) {
            if (j % 2 == 0) {
                determinant += rows[0].getComponent(j) * getMinor(this, j).getDeterminant();
            } else {
                determinant -= rows[0].getComponent(j) * getMinor(this, j).getDeterminant();
            }
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        int vectorSize = vector.getSize();

        if (vectorSize != this.getColumnsCount()) {
            throw new IllegalArgumentException("Длина вектора = " + vectorSize +
                    ". Размерность вектора должна совпадать с количеством столбцов матрицы = " + this.getColumnsCount());
        }

        Vector result = new Vector(vectorSize);

        for (int i = 0; i < rows.length; i++) {
            result.setComponent(i, Vector.getScalarMultiplication(rows[i], vector));
        }

        return result;
    }

    public Matrix subtract(Matrix matrix) {
        if (isSizesNotEquals(this, matrix)) {
            String matrix1Size = "(" + this.getRowsCount() + ", " + this.getColumnsCount() + ");";
            String matrix2Size = "(" + matrix.getRowsCount() + ", " + matrix.getColumnsCount() + ");";
            throw new IllegalArgumentException("Размерности вычитаемых матриц должны совпадать.%n" +
                    "Размерность матрицы 1 = " + matrix1Size +
                    "%nРазмерность матрицы 2 = " + matrix2Size);
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.getRow(i));
        }

        return this;
    }

    public Matrix add(Matrix matrix) {
        if (isSizesNotEquals(this, matrix)) {
            String matrix1Size = "(" + this.getRowsCount() + ", " + this.getColumnsCount() + ");";
            String matrix2Size = "(" + matrix.getRowsCount() + ", " + matrix.getColumnsCount() + ");";
            throw new IllegalArgumentException("Размерности слагаемых матриц должны совпадать.%n" +
                    "Размерность матрицы 1 = " + matrix1Size +
                    "%nРазмерность матрицы 2 = " + matrix2Size);
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.getRow(i));
        }

        return this;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (isSizesNotEquals(matrix1, matrix2)) {
            String matrix1Size = "(" + matrix1.getRowsCount() + ", " + matrix1.getColumnsCount() + ");";
            String matrix2Size = "(" + matrix2.getRowsCount() + ", " + matrix2.getColumnsCount() + ");";
            throw new IllegalArgumentException("Размерности вычитаемых матриц должны совпадать.%n" +
                    "Размерность матрицы 1 = " + matrix1Size +
                    "%nРазмерность матрицы 2 = " + matrix2Size);
        }

        return new Matrix(matrix1.subtract(matrix2));
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (isSizesNotEquals(matrix1, matrix2)) {
            String matrix1Size = "(" + matrix1.getRowsCount() + ", " + matrix1.getColumnsCount() + ");";
            String matrix2Size = "(" + matrix2.getRowsCount() + ", " + matrix2.getColumnsCount() + ");";
            throw new IllegalArgumentException("Размерности слагаемых матриц должны совпадать.%n" +
                    "Размерность матрицы 1 = " + matrix1Size +
                    "%nРазмерность матрицы 2 = " + matrix2Size);
        }

        return new Matrix(matrix1.add(matrix2));
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Количество столбцов матрицы №1 должно равняться количеству строк матрицы №2.%n" +
                    "Количество столбцов матрицы 1 = " + matrix1.getColumnsCount() +
                    "%nКоличество строк матрицы 2 = " + matrix2.getRowsCount());
        }

        double[][] result = new double[matrix1.getRowsCount()][matrix2.getColumnsCount()];

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                result[i][j] = Vector.getScalarMultiplication(matrix1.rows[i], matrix2.getColumn(j));
            }
        }

        return new Matrix(result);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < rows.length; i++) {
            stringBuilder.append(rows[i]);

            if (i != rows.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    private int getMaxRowLength(double[][] matrix) {
        int maxRowLength = 0;

        for (double[] doubles : matrix) {
            if (doubles.length > maxRowLength) {
                maxRowLength = doubles.length;
            }
        }

        return maxRowLength;
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

    private Matrix getMinor(Matrix matrix, int columnToExcludeIndex) {
        double[][] values = new double[matrix.rows.length - 1][matrix.rows.length - 1];

        for (int i = 0; i < matrix.rows.length; i++) {
            if (i < rows.length - 1) {
                values[i] = new double[matrix.rows.length - 1];
            }

            for (int j = 0; j < matrix.rows.length; j++)
                if (i != 0 && j != columnToExcludeIndex) {
                    values[i - 1][j < columnToExcludeIndex ? j : j - 1] = matrix.rows[i].getComponent(j);
                }
        }

        return new Matrix(values);
    }

    private static boolean isSizesNotEquals(Matrix matrix1, Matrix matrix2) {
        return (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount());
    }
}