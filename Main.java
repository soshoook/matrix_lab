public static void main(String[] args){
    int choice = printMenu();
    operation(choice);
}

static Scanner sc = new Scanner(System.in);

public static int printMenu(){
    System.out.println("=== МАТРИЧНЫЙ КАЛЬКУЛЯТОР ===");
    System.out.println(
            "  1. Сложение матриц\n" +
                    "  2. Умножение матриц\n" +
                    "  3. Деление матриц\n" +
                    "  4. Транспонирование матрицы\n" +
                    "  5. Определитель матрицы\n"
    );
    System.out.println("=============================");
    System.out.println("Введите номер операции, которую хотите выполнить: ");
    int choice = sc.nextInt();
    return choice;
}

public static Complex readComplex(Scanner sc){
    System.out.println("Введите действительную часть числа: ");
    double re = sc.nextDouble();
    System.out.println("Введите мнимую часть числа: ");
    double im = sc.nextDouble();

    Complex complex = new Complex(re, im);
    return complex;
}

public static Matrix readScanner(Scanner sc){
    System.out.println("=== СОЗДАНИЕ МАТРИЦЫ ===");

    System.out.println("Введите количество строк:");
    int rows = sc.nextInt();
    System.out.println("Введите количество столбцов:");
    int cols = sc.nextInt();

    Matrix matrix = new Matrix(rows, cols);

    for (int i = 0; i < rows; i++){
        for (int j = 0; j < cols; j++){
            System.out.println("Введите значение в ячейке: ");
            Complex value = readComplex(sc);
            matrix.set(i, j, value);
        }
    }

    return matrix;
}


public static void operation(int choice){
    switch (choice){
        case 1: {
            Matrix matr1 = readScanner(sc);
            Matrix matr2 = readScanner(sc);
            System.out.println("Результат сложения матриц:\n " + (matr1.add(matr2)).toString());
            break;
        }
        case 2: {
            Matrix matr1 = readScanner(sc);
            Matrix matr2 = readScanner(sc);
            System.out.println("Результат умножения матриц:\n " + matr1.mult(matr2));
            break;
        }
        case 3: {
            Matrix matr1 = readScanner(sc);
            Matrix matr2 = readScanner(sc);
            System.out.println("Резльтат деления матриц:\n " + matr1.divide(matr2));
            break;
        }
        case 4: {
            Matrix matr = readScanner(sc);
            System.out.println("Транспонированная матрица:\n " + matr.transpose());
            break;
        }
        case 5: {
            Matrix matr = readScanner(sc);
            System.out.println("Определитель матрицы:\n " + matr.determinant());
            break;
        }
        default:
            throw new IllegalArgumentException("Неизвестная операция");

    }
}