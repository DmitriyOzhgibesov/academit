package ru.academits.java.ozhgibesov.matrix;

import ru.academits.java.ozhgibesov.vector.Vector;

public class Matrix {
    Vector[] rows;

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
            rows[i] = new Vector(matrix.getVectorRow(i));
        }
    }

    public Matrix(double[][] matrixArray) {
        int maxRowLength = getMaxRowLength(matrixArray);

        if (matrixArray.length == 0) {
            throw new IllegalArgumentException("Кол-во строк = " + matrixArray.length + ". Количество строк не может быть = 0");
        }
        if (maxRowLength == 0) {
            throw new IllegalArgumentException("Кол-во столбцов = " + maxRowLength + ". Количество столбцов не может быть = 0");
        }

        rows = new Vector[matrixArray.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(maxRowLength, matrixArray[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        int maxRowLength = getMaxRowLength(vectorsArray);
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("Кол-во строк = " + vectorsArray.length + ". Количество строк не может быть = 0");
        }
        if (maxRowLength == 0) {
            throw new IllegalArgumentException("Кол-во столбцов = " + maxRowLength + ". Количество столбцов не может быть = 0");
        }

        rows = new Vector[vectorsArray.length];

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
        if (index > rows.length - 1 || index < 0) {
            throw new IllegalArgumentException("Индекс = " + index + ". Индекс выходит за рамки количества строк матрицы...");
        }

        return rows[index];
    }

    public Vector getVectorColumn(int index) {
        if (index > rows[0].getSize() - 1 || index < 0) {
            throw new IllegalArgumentException("Index = " + index + ". Индекс выходит за рамки количества столбцов матрицы...");
        }

        Vector vectorColumn = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            vectorColumn.setComponent(i, rows[i].getComponent(index));
        }

        return vectorColumn;
    }

    public Matrix transpose() {
        int newRowsCount = rows[0].getSize();
        Vector[] matrixTemp = new Vector[newRowsCount];

        for (int i = 0; i < newRowsCount; i++) {
            matrixTemp[i] = new Vector(this.getVectorColumn(i));
        }

        rows = matrixTemp;
        return this;
    }

    public Matrix multiplyByScalar(double scalar) {

        for (int i = 0; i < rows.length; i++) {
            rows[i] = rows[i].multiplyByScalar(scalar);
        }

        return this;
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
                det += rows[0].getComponent(j) * getMinor(this, j).getDeterminant();
            else
                det -= rows[0].getComponent(j) * getMinor(this, j).getDeterminant();
        }

        return det;
    }

    public Matrix multiplyByVector(Vector vector) {
        int vectorSize = vector.getSize();

        if (vectorSize != rows.length) {
            throw new IllegalArgumentException("Длина вектора = " + vectorSize + ". Длина вектора должна совпадать с количеством строк матрицы");
        }

        double[][] result = new double[vectorSize][1];

        for (int i = 0; i < rows.length; i++) {
            result[i][0] = Vector.getScalarMultiplication(rows[i], vector);
        }

        return new Matrix(result);
    }


    public Matrix subtract(Matrix matrix) {
        Vector[] matrixTemp = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            matrixTemp[i] = new Vector(Vector.getDifference(rows[i], matrix.rows[i]));
        }

        rows = matrixTemp;
        return this;
    }

    public Matrix add(Matrix matrix) {
        Vector[] matrixTemp = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            matrixTemp[i] = new Vector(Vector.getSum(rows[i], matrix.rows[i]));
        }

        rows = matrixTemp;
        return this;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getDimensions()[0] != matrix2.getDimensions()[0] && matrix1.getDimensions()[1] != matrix2.getDimensions()[1]) {
            throw new IllegalArgumentException("Размерности вычитаемых матриц должны совпадать");
        }

        Vector[] matrixTemp = new Vector[matrix1.rows.length];

        for (int i = 0; i < matrix1.rows.length; i++) {
            matrixTemp[i] = new Vector(Vector.getDifference(matrix1.rows[i],
                    matrix2.rows[i]));
        }

        return new Matrix(matrixTemp);
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getDimensions()[0] != matrix2.getDimensions()[0] && matrix1.getDimensions()[1] != matrix2.getDimensions()[1]) {
            throw new IllegalArgumentException("Размерности слагаемых матриц должны совпадать");
        }

        Vector[] matrixTemp = new Vector[matrix1.rows.length];

        for (int i = 0; i < matrix1.rows.length; i++) {
            matrixTemp[i] = new Vector(Vector.getSum(matrix1.rows[i],
                    matrix2.rows[i]));
        }

        return new Matrix(matrixTemp);
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getDimensions()[1] != matrix2.getDimensions()[0]) {
            throw new IllegalArgumentException("Количество столбцов матрицы №1 должно равняться количеству строк матрицы №2");
        }

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

    private Matrix getMinor(Matrix matrix, int colToExclude) {
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