public class Matrix {

    private final Complex[][] data;
    private final int rows;
    private final int cols;

    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
             //подумать что выводит
        }
        if (rows != cols){
            //тоже подумать
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

    public int getRows(){
        return this.rows;
    }

    public int getCols(){
        return this.cols;
    }

    @Override
    public String toString(){

    }

    public Matrix add(Matrix matr){

    }

    public Matrix mult(Matrix matr){

    }

    public Matrix divide(Matrix matr){

    }

    public Matrix transpose(){

    }

    public Matrix determinant(){

    }

    public Matrix inversion(){

    }

}
