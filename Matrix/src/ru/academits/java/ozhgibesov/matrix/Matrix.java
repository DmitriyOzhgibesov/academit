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
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] matrixArray) {
        if (matrixArray.length == 0) {
            throw new IllegalArgumentException("Кол-во строк = " + matrixArray.length + ". Количество строк не может быть = 0");
        }

        int columnsCount = getMaxRowLength(matrixArray);

        if (columnsCount == 0) {
            throw new IllegalArgumentException("Кол-во столбцов = " + columnsCount + ". Количество столбцов не может быть = 0");
        }

        rows = new Vector[matrixArray.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount, matrixArray[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Кол-во строк = " + vectors.length + ". Количество строк не может быть = 0");
        }

        rows = new Vector[vectors.length];
        int columnsCount = getColumnsCount(vectors);

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount);
            rows[i].add(vectors[i]);
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
            throw new IndexOutOfBoundsException("Index = " + index + ". Индекс выходит за рамки диапазона от 0 до" + (rows.length - 1) + " включительно");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Количество элементов вектора равно " + vector.getSize() +
                    "; Количество элементов вектора дожно быть равно" + getColumnsCount());
        }

        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Index = " + index + ". Индекс выходит за рамки диапазона от 0 до" + (rows.length - 1) + " включительно");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Index = " + index + ". Индекс выходит за рамки диапазона от 0 до" + (getColumnsCount() - 1) + " включительно");
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public Matrix transpose() {
        int newRowsCount = getColumnsCount();
        Vector[] newRows = new Vector[newRowsCount];

        for (int i = 0; i < newRowsCount; i++) {
            newRows[i] = getColumn(i);
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
        if (rows.length != getColumnsCount()) {
            throw new UnsupportedOperationException("Невозможно найти определитель не квадратной матрицы; Размер текущей матрицы (" +
                    getRowsCount() + ", " + getColumnsCount() + ");");
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

        if (vectorSize != getColumnsCount()) {
            throw new IllegalArgumentException("Размерность вектора = " + vectorSize +
                    ". Размерность вектора должна совпадать с количеством столбцов матрицы = " + getColumnsCount());
        }

        Vector result = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            result.setComponent(i, Vector.getScalarMultiplication(rows[i], vector));
        }

        return result;
    }

    public Matrix subtract(Matrix matrix) {
        checkMatricesSizes(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }

        return this;
    }

    public Matrix add(Matrix matrix) {
        checkMatricesSizes(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }

        return this;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizes(matrix1, matrix2);
        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizes(matrix1, matrix2);
        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Количество столбцов матрицы №1 должно равняться количеству строк матрицы №2. Количество столбцов матрицы 1 = " +
                    matrix1.getColumnsCount() + "; Количество строк матрицы 2 = " + matrix2.getRowsCount());
        }

        double[][] multiplicationResults = new double[matrix1.getRowsCount()][matrix2.getColumnsCount()];

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                multiplicationResults[i][j] = Vector.getScalarMultiplication(matrix1.rows[i], matrix2.getColumn(j));
            }
        }

        return new Matrix(multiplicationResults);
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

    private static int getMaxRowLength(double[][] matrix) {
        int maxRowLength = 0;

        for (double[] doubles : matrix) {
            if (doubles.length > maxRowLength) {
                maxRowLength = doubles.length;
            }
        }

        return maxRowLength;
    }

    private static int getColumnsCount(Vector[] vectors) {
        int maxRowLength = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > maxRowLength) {
                maxRowLength = vector.getSize();
            }
        }

        return maxRowLength;
    }

    private Matrix getMinor(Matrix matrix, int columnToExcludeIndex) {
        double[][] values = new double[matrix.rows.length - 1][matrix.rows.length - 1];

        for (int i = 1; i < matrix.rows.length; i++) {
            for (int j = 0; j < matrix.rows.length; j++) {
                if (j != columnToExcludeIndex) {
                    values[i - 1][j < columnToExcludeIndex ? j : j - 1] = matrix.rows[i].getComponent(j);
                }
            }
        }

        return new Matrix(values);
    }

    private static void checkMatricesSizes(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размерности двух матриц должны совпадать. Размерность матрицы 1 (" +
                    matrix1.getRowsCount() + ", " + matrix1.getColumnsCount() +
                    "); Размерность матрицы 2 = (" + matrix2.getRowsCount() + ", " + matrix2.getColumnsCount() + ");");
        }
    }
}