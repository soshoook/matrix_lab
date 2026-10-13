public class Complex {

    private final double re;
    private final double im;

    public Complex(double re, double im){
        this.re = re;
        this.im = im;
    }

    @Override
    public String toString(){
        String a = "";
        if (this.im >= 0)
            a = re + " + " + im + "i";
        else
            a = re + " - "+ (-im) + "i";
        return a;
    }

    public boolean isZero() {
        return re == 0.0 && im == 0.0;
    }

    public double abs2() {
// |z|² = a² + b²
        double re_abs2 = re * re;
        double im_abs2 = im * im;
        return re_abs2 + im_abs2;
    }

    public Complex sopr(){
//z̄ = a − bi
        Complex sopr = new Complex(re, -im);
        return sopr;
    }

    public Complex add(Complex digit){
//(a + bi) + (c + di) = (a + c) + (b + d)i
        double re_summ = (re + digit.re);
        double im_summ = (im + digit.im);
        Complex summ = new Complex(re_summ, im_summ);
        return summ;
    }

    public Complex subs(Complex digit){
//(a + bi) + (c + di) = (a - c) + (b - d)i
        double re_sub = (re - digit.re);
        double im_sub = (im - digit.im);
        Complex sub = new Complex(re_sub, im_sub);
        return sub;
    }

    public Complex mult(Complex digit){
// (a + bi)(c + di) = ac + adi + bci + bd·i² = (ac − bd) + (ad + bc)i
        double min = ((re * digit.re) - (im * digit.im));
        double pl = ((re * digit.im) + (im * digit.re));
        Complex mul = new Complex(min, pl);
        return mul;
    }


    public Complex div(Complex digit) {
        if (digit.isZero())
            throw new ArithmeticException("Деление на ноль");
        //z / w = (z · w̄) / (w · w̄) = (z · w̄) / |w|²
        Complex conjugate = digit.sopr();
        Complex numerator = this.mult(conjugate);
        double modSq = digit.abs2();
        return new Complex(numerator.re / modSq, numerator.im / modSq);
    }

}
