package ru.academits.java.ozhgibesov.matrix_main;

import ru.academits.java.ozhgibesov.matrix.Matrix;
import ru.academits.java.ozhgibesov.vector.Vector;

public class MatrixMain {
    public static void main(String[] args) {
        System.out.println("Матрица 1:");
        Matrix matrix1 = new Matrix(3, 3);
        System.out.printf("Размерность матрицы 2:%dx%d%n", matrix1.getRowsCount(), matrix1.getColumnsCount());
        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            System.out.println(matrix1.getRow(i));
        }

        double[] matrix2Row1 = {1, 2, 3, 4, 5};
        double[] matrix2Row2 = {1, 2, 3};
        double[] matrix2Row3 = {1, 2,};
        double[][] array = new double[][]{matrix2Row1, matrix2Row2, matrix2Row3};

        System.out.println("Матрица 2:");
        Matrix matrix2 = new Matrix(array);
        System.out.printf("Размерность матрицы 2:%dx%d%n", matrix2.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < matrix2.getRowsCount(); i++) {
            System.out.println(matrix2.getRow(i));
        }

        Vector vector1 = new Vector(new double[]{1, 2, 3, 0, 10, 12});
        Vector vector2 = new Vector(new double[]{4, 5, 6});
        Vector vector3 = new Vector(new double[]{7, 8, 9});
        Vector[] vectorArray = new Vector[]{vector1, vector2, vector3};

        System.out.println("Матрица 3:");
        Matrix matrix3 = new Matrix(vectorArray);

        System.out.printf("Размерность матрицы 3:%dx%d%n", matrix3.getRowsCount(), matrix3.getColumnsCount());

        for (int i = 0; i < matrix3.getRowsCount(); i++) {
            System.out.println(matrix3.getRow(i));
        }

        System.out.println("Матрица 4:");
        Matrix matrix4 = new Matrix(matrix3);
        System.out.printf("Размерность матрицы 4:%dx%d%n", matrix4.getRowsCount(), matrix4.getColumnsCount());

        for (int i = 0; i < matrix4.getRowsCount(); i++) {
            System.out.println(matrix4.getRow(i));
        }

        Vector row = matrix4.getRow(2);
        System.out.println("Вектор-строка 2 матрицы 4:");
        System.out.println(row);

        System.out.println("Вектор-столбец 2 матрицы 4:");
        Vector column = matrix4.getColumn(2);
        System.out.println(column);

        System.out.println("Результат транспонирования матрицы 4:");
        Matrix transposition = matrix4.transpose();

        System.out.printf("Размерность транспонированной матрицы 4:%dx%d%n", transposition.getRowsCount(), transposition.getColumnsCount());

        for (int i = 0; i < transposition.getRowsCount(); i++) {
            System.out.println(transposition.getRow(i));
        }

        System.out.println("Результат умножения матрицы 4 на число 2:");
        Matrix scalarMultiplication = matrix4.multiplyByScalar(2);

        for (int i = 0; i < scalarMultiplication.getRowsCount(); i++) {
            System.out.println(scalarMultiplication.getRow(i));
        }

        System.out.println("Результат умножения матрицы 6 на вектор:");
        double[] matrix5Row1 = {5, 10, 15};
        double[] matrix5Row2 = {1, 3, 9};
        double[] matrix5Row3 = {12, 24, 48};
        double[][] arrayMatrix5 = new double[][]{matrix5Row1, matrix5Row2, matrix5Row3};

        double[] matrix6Row1 = {-1, 2, 3};
        double[] matrix6Row2 = {1, -2, 3};
        double[] matrix6Row3 = {1, 2, -3};
        double[][] arrayMatrix6 = new double[][]{matrix6Row1, matrix6Row2, matrix6Row3};

        Matrix matrix5 = new Matrix(arrayMatrix5);
        Matrix matrix6 = new Matrix(arrayMatrix6);

        Vector vector = new Vector(new double[]{-1, 2, -3});

        Vector vectorMul = matrix6.multiplyByVector(vector);
        System.out.println(vectorMul);

        System.out.println("Результат сложения матрицы 5 и 6:");
        Matrix matrixSum = matrix5.add(matrix6);

        for (int i = 0; i < matrixSum.getRowsCount(); i++) {
            System.out.println(matrixSum.getRow(i));
        }

        System.out.println("Результат статического сложения матрицы 5 и 6:");
        Matrix matrixStaticSum = Matrix.getSum(matrix5, matrix6);

        for (int i = 0; i < matrixStaticSum.getRowsCount(); i++) {
            System.out.println(matrixStaticSum.getRow(i));
        }

        System.out.println("Результат вычитания матрицы 6 из матрицы 5:");
        Matrix matrixDiff = matrix5.subtract(matrix6);

        for (int i = 0; i < matrixDiff.getRowsCount(); i++) {
            System.out.println(matrixDiff.getRow(i));
        }

        System.out.println("Результат статической функции вычитания матрицы 6 из матрицы 5:");
        Matrix matrixStaticDiff = Matrix.getDifference(matrix5, matrix6);

        for (int i = 0; i < matrixStaticDiff.getRowsCount(); i++) {
            System.out.println(matrixStaticDiff.getRow(i));
        }

        System.out.println("Результат статической функции умножения матрицы 5 на 6:");
        Matrix product = Matrix.getProduct(matrix5, matrix6);

        for (int i = 0; i < product.getRowsCount(); i++) {
            System.out.println(product.getRow(i));
        }

        System.out.println("Результат работы оператора toString():" + product);

        double[] row1MatrixDeterminant = {1, 1, 2, 3, 4, 5};
        double[] row2MatrixDeterminant = {0, 2, 6, 7, 8, 9};
        double[] row3MatrixDeterminant = {1, 2, 3, 10, 11, 12};
        double[] row4MatrixDeterminant = {3, 4, 5, 4, 13, 14};
        double[] row5MatrixDeterminant = {6, 7, 8, 9, 5, 15};
        double[] row6MatrixDeterminant = {10, 11, 12, 13, 14, 6};

        double[][] arrayDeterminant = {
                row1MatrixDeterminant,
                row2MatrixDeterminant,
                row3MatrixDeterminant,
                row4MatrixDeterminant,
                row5MatrixDeterminant,
                row6MatrixDeterminant
        };

        Matrix matrixDeterminant = new Matrix(arrayDeterminant);
        System.out.println("Определитель матрицы 6: " + matrixDeterminant.getDeterminant());
    }
}