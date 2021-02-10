package ru.academits.java.ozhgibesov.matrix_main;


import ru.academits.java.ozhgibesov.matrix.Matrix;
import ru.academits.java.ozhgibesov.vector.Vector;

public class MatrixMain {
    public static void main(String[] args) {


        System.out.println("Матрица 1:");
        Matrix matrix1 = new Matrix(3, 3);
        System.out.printf("Размерность матрицы 2:%dx%d%n", matrix1
                .getDimensions()[0], matrix1.getDimensions()[1]);
        for (int i = 0; i < matrix1.getDimensions()[0]; i++) {
            System.out.println(matrix1.getVectorRow(i));
        }

        double[] line1Matrix2 = new double[]{1, 2, 3, 4, 5};
        double[] line2Matrix2 = new double[]{1, 2, 3};
        double[] line3Matrix2 = new double[]{1, 2,};
        double[][] matrixArgument = new double[][]{line1Matrix2,
                line2Matrix2, line3Matrix2};

        System.out.println("Матрица 2:");
        Matrix matrix2 = new Matrix(matrixArgument);
        System.out.printf("Размерность матрицы 2:%dx%d%n", matrix2
                .getDimensions()[0], matrix2.getDimensions()[1]);

        for (int i = 0; i < matrix2.getDimensions()[0]; i++) {
            System.out.println(matrix2.getVectorRow(i));
        }

        Vector vector1 = new Vector(new double[]{1, 2, 3, 0, 10, 12});
        Vector vector2 = new Vector(new double[]{4, 5, 6});
        Vector vector3 = new Vector(new double[]{7, 8, 9});
        Vector[] vectorArray = new Vector[]{vector1, vector2, vector3};

        System.out.println("Матрица 3:");
        Matrix matrix3 = new Matrix(vectorArray);

        System.out.printf("Размерность матрицы 3:%dx%d%n", matrix3
                .getDimensions()[0], matrix3.getDimensions()[1]);

        for (int i = 0; i < matrix3.getDimensions()[0]; i++) {
            System.out.println(matrix3.getVectorRow(i));
        }

        System.out.println("Матрица 4:");
        Matrix matrix4 = new Matrix(matrix3);
        System.out.printf("Размерность матрицы 4:%dx%d%n", matrix4
                .getDimensions()[0], matrix4.getDimensions()[1]);

        for (int i = 0; i < matrix4.getDimensions()[0]; i++) {
            System.out.println(matrix4.getVectorRow(i));
        }

        Vector row = matrix4.getVectorRow(2);
        System.out.println("Вектор-строка 2 матрицы 4:");
        System.out.println(row);

        System.out.println("Вектор-столбец 2 матрицы 4:");
        Vector column = matrix4.getVectorColumn(2);
        System.out.println(column);

        System.out.println("Результат транспонирования матрицы 4:");
        Matrix transposition = matrix4.getTransposition();

        System.out.printf("Размерность транспонированной матрицы 4:%dx%d%n",
                transposition.getDimensions()[0],
                transposition.getDimensions()[1]);

        for (int i = 0; i < transposition.getDimensions()[0]; i++) {
            System.out.println(transposition.getVectorRow(i));
        }


        System.out.println("Результат умножения матрицы 4 на число 2:");
        Matrix scalarMultiplication = matrix4.getScalarMultiplication(2);

        for (int i = 0; i < scalarMultiplication.getDimensions()[0]; i++) {
            System.out.println(scalarMultiplication.getVectorRow(i));
        }


        System.out.println("Результат умножения матрицы 6 на вектор:");
        double[] line1Matrix5 = new double[]{5, 10, 15};
        double[] line2Matrix5 = new double[]{1, 3, 9};
        double[] line3Matrix5 = new double[]{12, 24, 48};
        double[][] matrix5Argument = new double[][]{line1Matrix5, line2Matrix5, line3Matrix5};

        double[] line1Matrix6 = new double[]{-1, 2, 3};
        double[] line2Matrix6 = new double[]{1, -2, 3};
        double[] line3Matrix6 = new double[]{1, 2, -3};
        double[][] matrix6Argument = new double[][]{line1Matrix6, line2Matrix6, line3Matrix6};

        Matrix matrix5 = new Matrix(matrix5Argument);
        Matrix matrix6 = new Matrix(matrix6Argument);

        Vector vector = new Vector(new double[]{-1, 2, -3});

        Matrix vectorMul = matrix6.getVectorMultiplication(vector);
        for (int i = 0; i < vectorMul.getDimensions()[0]; i++) {
            System.out.println(vectorMul.getVectorRow(i));
        }

        System.out.println("Результат сложения матрицы 5 и 6:");
        Matrix matrixSum = matrix5.getSum(matrix6);

        for (int i = 0; i < matrixSum.getDimensions()[0]; i++) {
            System.out.println(matrixSum.getVectorRow(i));
        }

        System.out.println("Результат статического сложения матрицы 5 и 6:");
        Matrix matrixStaticSum = Matrix.Sum(matrix5, matrix6);

        for (int i = 0; i < matrixStaticSum.getDimensions()[0]; i++) {
            System.out.println(matrixStaticSum.getVectorRow(i));
        }


        System.out.println("Результат вычитания матрицы 6 из  матрицы 5:");
        Matrix matrixDiff = matrix5.getDifference(matrix6);

        for (int i = 0; i < matrixDiff.getDimensions()[0]; i++) {
            System.out.println(matrixDiff.getVectorRow(i));
        }


        System.out.println("Результат статической функции вычитания матрицы 6 из матрицы 5:");
        Matrix matrixStaticDiff = Matrix.Diff(matrix5, matrix6);

        for (int i = 0; i < matrixStaticDiff.getDimensions()[0]; i++) {
            System.out.println(matrixStaticDiff.getVectorRow(i));
        }

        System.out.println("Результат статической функции умножения матрицы 5 на 6:");
        Matrix matrixMul = Matrix.multiplicate(matrix5, matrix6);

        for (int i = 0; i < matrixMul.getDimensions()[0]; i++) {
            System.out.println(matrixMul.getVectorRow(i));
        }

        System.out.println("Результат работы оператора toString():" + matrixMul);

        double[] line1MatrixDeterminant = new double[]{2, 4, 6};
        double[] line2MatrixDeterminant = new double[]{6, 4, 2};
        double[] line3MatrixDeterminant = new double[]{4, 2, 6};
        double[][] arrayDeterminant = new double[][]{line1MatrixDeterminant, line2MatrixDeterminant, line3MatrixDeterminant};

        Matrix matrixDeterminant = new Matrix(arrayDeterminant);
        System.out.println("Определитель матрицы 6: " + matrixDeterminant.getDeterminant());
    }
}