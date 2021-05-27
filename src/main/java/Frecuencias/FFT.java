package Frecuencias;

public class FFT {
    //io = Imagen original
    public NumeroComplejo[][] calcularTF(NumeroComplejo[][] io, boolean inversa) {
        int ancho = io.length;
        int alto = io[0].length;

        NumeroComplejo[][] it = new NumeroComplejo[ancho][alto]; //imagen transformada

        int ancholg2 = log2(ancho);
        int altolg2 = log2(alto);

        for(int x=0; x<ancho; x++) {
            for(int y=0; y<alto; y++) {
                it[x][y] = new NumeroComplejo(io[x][y]);
            }
        }

        // Inversion de bits por fila
        for (int y = 0; y < alto; y++) {
            // para cada reglon
            int offset = 0;
            for (int i = 0; i < ancho - 1; i++) {
                it[i][y] = new NumeroComplejo(io[offset][y]);
                int pm = ancho / 2;
                while (pm <= offset) {
                    offset -= pm;
                    pm /= 2;
                }
                offset += pm;
            }
        }

        // Inversion de bits por columna
        for(int x=0; x<ancho; x++) {
            int j = 0;
            for(int i=0; i<alto - 1; i++) {
                if(i < j) {
                    NumeroComplejo temp = new NumeroComplejo(it[x][i]);
                    it[x][i] = new NumeroComplejo(it[x][j]);
                    it[x][j] = temp;
                }

                int pm = alto / 2;

                while(pm <= j) {
                    j -= pm;
                    pm /= 2;
                }

                j += pm;
            }
        }

        // Acumulacion p/columnas
        for(int x=0; x<ancho; x++) {
            double cos = -1;
            double sin = 0;
            int l1 = 1, l2 = 1;

            for(int l =0; l<ancholg2; l++) {
                l1 = l2;
                l2 <<= 1;

                double u1 = 1;
                double u2 = 0;

                for(int j=0; j<l1; j++) {
                    for(int i =j; i<ancho; i += l2) {
                        int i1 = i + l1;
                        double t1 = u1 * it[x][i1].getR() - u2 * it[x][i1].getI();
                        double t2 = u1 * it[x][i1].getI() + u2 * it[x][i1].getR();

                        it[x][i1] = it[x][i].suma(new NumeroComplejo(-t1, -t2));
                        it[x][i] = it[x][i].suma(new NumeroComplejo(t1, t2));
                    }

                    double z =u1 * cos - u2 * sin;
                    u2 = u1 * sin + u2 * cos;
                    u1 = z;
                }

                sin = Math.sqrt((1 - cos) / 2);

                if(!inversa)
                    sin = -sin;

                cos = Math.sqrt((1 + cos) / 2);
            }
        }

        // Acumulacion p/fila
        for(int y=0; y<alto; y++) {
            double cos = -1;
            double sin = 0;
            int l1 = 1, l2 = 1;

            for(int l =0; l<altolg2; l++) {
                l1 = l2;
                l2 <<= 1;

                double u1 = 1;
                double u2 = 0;

                for(int j=0; j<l1; j++) {
                    for(int i=j; i<ancho; i += l2) {
                        int i1 = i + l1;
                        double t1 = u1 * it[i1][y].getR() - u2 * it[i1][y].getI();
                        double t2 = u1 * it[i1][y].getI() + u2 * it[i1][y].getR();

                        it[i1][y] = it[i][y].suma(new NumeroComplejo(-t1, -t2));
                        it[i][y] = it[i][y].suma(new NumeroComplejo(t1, t2));
                    }

                    double z = u1 * cos - u2 * sin;

                    u2 = u1 * sin + u2 * cos;
                    u1 = z;
                }

                sin = Math.sqrt((1 - cos) / 2);

                if(!inversa)
                    sin = -sin;

                cos = Math.sqrt((1 + cos) / 2);
            }
        }

        int dim;

        if(inversa)
            dim = ancho;
        else
            dim = alto;

        for(int x=0; x<ancho; x++) {
            for(int y=0; y<alto; y++) {
                it[x][y] = it[x][y].multiplicar(1 / (double)dim);
            }
        }

        return it;
    }

    private int log2(int dimencion) {
        int cont = 0;
        while((dimencion >>= 1) > 0)
            cont++;
        return cont;
    }
}
