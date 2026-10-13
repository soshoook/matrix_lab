public class Matrix {

    private final Complex[][] data;
    private final int rows;
    private final int cols;

    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Размеры матрицы должны быть положительными");
        }

        this.rows = rows;
        this.cols = cols;
        this.data = new Complex[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new Complex(0.0, 0.0);
            }
        }
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public void set(int row, int col, Complex value) {
        this.data[row][col] = value;
    }

    public Complex get(int row, int col) {
        return this.data[row][col];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append("| ");
            for (int j = 0; j < cols; j++) {
                sb.append(this.data[i][j]);
                sb.append("\t");
            }
            sb.append(" |");
            sb.append("\n");
        }
        return sb.toString();
    }

    public Matrix add(Matrix matr) {
        if (matr.rows != this.rows || matr.cols != this.cols)
            throw new IllegalArgumentException("Размеры матриц должны совпадать");

        Matrix res = new Matrix(this.rows, this.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res.data[i][j] = this.data[i][j].add(matr.data[i][j]);
            }
        }
        return res;
    }

    public Matrix mult(Matrix matr) {
        if (this.cols != matr.rows)
            throw new IllegalArgumentException("Количество столбцов левой матрицы должно совпадать с количеством строк правой");

        Matrix res = new Matrix(this.rows, matr.cols);
        for (int i = 0; i < res.rows; i++) {
            for (int j = 0; j < res.cols; j++) {
                Complex sum = new Complex(0, 0);
                for (int k = 0; k < this.cols; k++) {
                    sum = sum.add(this.data[i][k].mult(matr.data[k][j]));
                }
                res.data[i][j] = sum;
            }
        }
        return res;
    }

    public Matrix divide(Matrix matr) {
        return this.mult(matr.inversion());
    }

    public Matrix transpose() {
        Matrix transpose = new Matrix(this.cols, this.rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transpose.data[j][i] = this.data[i][j];
            }
        }
        return transpose;
    }

    private Matrix minor(int row, int col) {
        Matrix res = new Matrix(rows - 1, cols - 1);
        int ri = 0;
        for (int i = 0; i < rows; i++) {
            if (i == row) continue;
            int rj = 0;
            for (int j = 0; j < cols; j++) {
                if (j == col) continue;
                res.data[ri][rj] = this.data[i][j];
                rj++;
            }
            ri++;
        }
        return res;
    }

    public Complex determinant() {
        if (this.rows != this.cols)
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        if (this.rows == 1)
            return this.data[0][0];

        Complex sum = new Complex(0, 0);
        for (int j = 0; j < this.cols; j++) {
            Complex term = data[0][j].mult(minor(0, j).determinant());
            if (j % 2 == 0)
                sum = sum.add(term);
            else
                sum = sum.subs(term);

        }
        return sum;
    }

    public Matrix inversion() {
        if (this.rows != this.cols)
            throw new IllegalArgumentException("Матрица должна быть квадратной");

        Complex det = determinant();
        if (det.isZero())
            throw new IllegalArgumentException("Определитель не должен быть равен нулю");

        if (rows == 1) {
            Matrix res = new Matrix(1, 1);
            res.data[0][0] = new Complex(1, 0).div(data[0][0]);
            return res;
        }

        Matrix res = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Complex cofactor = minor(i, j).determinant();
                if ((i + j) % 2 != 0) {
                    cofactor = new Complex(-1, 0).mult(cofactor);
                }
                res.data[i][j] = cofactor.div(det);
            }
        }

        return res.transpose();
    }
}
