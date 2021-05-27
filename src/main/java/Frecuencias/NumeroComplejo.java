package Frecuencias;

public class NumeroComplejo {

    private double r; // parte real
    private double i; // parte imaginaria

    public NumeroComplejo(double r, double i) {
        this.r = r;
        this.i = i;
    }

    public NumeroComplejo(NumeroComplejo complejo) {
        this.r = complejo.getR();
        this.i = complejo.getI();
    }

    public double getR() {
        return this.r;
    }

    public double getI() {
        return this.i;
    }

    public NumeroComplejo suma(NumeroComplejo sumador) {
        double r = this.r + sumador.getR();
        double i = this.i + sumador.getI();

        return new NumeroComplejo(r, i);
    }

    public NumeroComplejo multiplicar(double multiplo) {
        double r = this.r * multiplo;
        double i = this.i * multiplo;

        return new NumeroComplejo(r, i);
    }

    @Override
    public String toString() {
        String com = "";
        if(this.i > 0)
            com = "+"+this.i+"i";
        else if(this.i < 0)
            com = this.i+"i";

        return this.r+com;
    }
}
