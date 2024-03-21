import org.w3c.dom.ls.LSOutput;

public class Tripleta {
    private int Tri[][];

    public Tripleta(int N, int f, int c) {
        Tri = new int [N+1][3];
        Tri[0][0] = f;
        Tri[0][1] = c;
        Tri[0][2] = N;
    }
    public int[][] getTri() {
        return Tri;
    }
    public void setTri(int[][] tri) {
        Tri = tri;
    }
    public int getTri(int i, int j) {
        return Tri[i][j];
    }
    public void setTri(int i, int j, int valor) {
        Tri[i][j] = valor;
    }

    public void LlenarTripleta(int Matriz[][]){
        int k = 1;
        for (int i = 0; i < Matriz.length; i++){
            for (int j = 0; j < Matriz[i].length; j++){
                if (Matriz[i][j] != 0){
                    Tri[k][0] = i;
                    Tri[k][1] = j;
                    Tri[k][2] = Matriz[i][j];
                    k++;
                }
            }
        }
    }

    public void MostrarTripleta(){
        for (int i = 0; i < Tri.length; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(Tri[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void Multiplicar(Tripleta a, Tripleta b){
        Tripleta NuevaTri = new Tripleta(a.getTri(0, 0)*b.getTri(0, 1), a.getTri(0, 0), b.getTri(0, 1));
        int suma = 0, k = 1, o = 0;

        for (int i = 0; i < a.getTri(0, 0); i++){
            for (int j = 0; j < b.getTri(0, 1); j++){
                for (int l = 0; l < b.getTri(0, 0); l++){
                    suma += a.BuscarDato(i, l) * b.BuscarDato(l, j);
                }
                if (suma != 0){
                    NuevaTri.setTri(k, 0, i);
                    NuevaTri.setTri(k, 1, j);
                    NuevaTri.setTri(k, 2, suma);
                    k++;
                    o++;
                }
                suma = 0;
            }
        }

        if (o != NuevaTri.getTri(0, 2)){
            Tripleta Total = new Tripleta(o, NuevaTri.getTri(0, 0), NuevaTri.getTri(0, 1));
            Total = NuevaTri.Redimensionar(Total);
            Total.MostrarTripleta();
        }
        else {
            NuevaTri.MostrarTripleta();
        }
    }

    public int BuscarDato(int f, int c){
        for (int i = 1; i < Tri.length; i++){
            if (Tri[i][0] == f && Tri[i][1] == c){
                return Tri[i][2];
            }
        }
        return 0;
    }

    public void Sumar (Tripleta a, Tripleta b){
        Tripleta NuevaTri = new Tripleta(a.getTri(0,0) * a.getTri(0, 1), a.getTri(0,0), a.getTri(0, 1));
        int k = 1, suma = 0, o = 0;
        for (int i = 0; i < a.getTri(0,0); i++){
            for (int j = 0; j < a.getTri(0,1); j++){
                suma = a.BuscarDato(i,j) + b.BuscarDato(i, j);
                if (suma != 0){
                    NuevaTri.setTri(k, 0, i);
                    NuevaTri.setTri(k, 1, j);
                    NuevaTri.setTri(k, 2, suma);
                    k++;
                    o++;
                    suma = 0;
                }
            }
        }

        if (o != NuevaTri.getTri(0,2)) {
            Tripleta Total = new Tripleta(o, a.getTri(0,0), a.getTri(0,1));
            Total = NuevaTri.Redimensionar(Total);
            Total.MostrarTripleta();
        } else {
           NuevaTri.MostrarTripleta();
        }
    }

    public Tripleta Redimensionar (Tripleta Total){
        int l = 1;
        for (int i = 1; i < Total.getTri(0, 2)+1; i++){
            Total.setTri(l, 0, this.getTri(i, 0));
            Total.setTri(l, 1, this.getTri(i, 1));
            Total.setTri(l, 2, this.getTri(i, 2));
            l++;
        }
        return Total;
    }

    public void SumaFilasColumnas() {
        int[] Filas = new int [this.getTri(0, 0)];
        int[] Columnas = new int [this.getTri(0, 1)];

        for (int i = 1; i <= this.getTri(0, 2); i++){
            Filas[this.getTri(i, 0)] += this.getTri(i, 2);
            Columnas[this.getTri(i, 1)] += this.getTri(i, 2);
        }
    }

    public void InsertarDato (int fila, int columna, int dato){
        boolean b = false;
        if (this.BuscarDato(fila, columna) != 0){
            for (int i = 1; i <= this.getTri(0, 2); i++){
                if (this.getTri(i, 0) == fila && this.getTri(i, 1) == columna){
                    this.setTri(i, 2, dato);
                }
            }
            this.MostrarTripleta();
        }
        else if (fila+1 > this.getTri(0,0) || columna+1 > this.getTri(0,1)){
            System.out.println("Posición no válida en la matriz");
        }
        else {
            Tripleta Nueva = new Tripleta(this.getTri(0,2)+1, this.getTri(0,0), this.getTri(0,1));
            int k = 1, r=1;
            for (int l = 1; l <= Nueva.getTri(0, 2) && b == false; l++){
                if (this.getTri(r, 0) >= fila){
                    if (this.getTri(r, 0) > fila){
                        Nueva.setTri(l, 0, fila);
                        Nueva.setTri(l, 1, columna);
                        Nueva.setTri(l, 2, dato);
                    } else {
                        while (this.getTri(r, 0) == fila && b == false){
                            if (this.getTri(r, 1) > columna){
                                Nueva.setTri(l, 0, fila);
                                Nueva.setTri(l, 1, columna);
                                Nueva.setTri(l, 2, dato);
                                b = true;
                            } else {
                                Nueva.setTri(k, 0, this.getTri(r,0));
                                Nueva.setTri(k, 1, this.getTri(r, 1));
                                Nueva.setTri(k, 2, this.getTri(r,2));
                                k++;
                                r++;
                                l++;
                                if (r+1 > this.getTri(0,2)){ // Por si el dato a insertar está en el límite inferior derecho
                                    break;
                                }
                            }
                        }
                        if (b == false){
                            Nueva.setTri(l, 0, fila);
                            Nueva.setTri(l, 1, columna);
                            Nueva.setTri(l, 2, dato);
                            l++;
                        }
                    }
                }
                else {
                    Nueva.setTri(k, 0, this.getTri(r,0));
                    Nueva.setTri(k, 1, this.getTri(r, 1));
                    Nueva.setTri(k, 2, this.getTri(r,2));
                    k++;
                    r++;
                }
            }
            k++;
            for (; k <= Nueva.getTri(0, 2); k++){
                Nueva.setTri(k, 0, this.getTri(r,0));
                Nueva.setTri(k, 1, this.getTri(r, 1));
                Nueva.setTri(k, 2, this.getTri(r,2));
                r++;
            }
            Nueva.MostrarTripleta();
        }
    }

    public void EliminarDato (int fila, int columna, int dato) {
        int r = 1;
        if (this.BuscarDato(fila, columna) == dato){
            Tripleta Nueva = new Tripleta(this.getTri(0,2)-1, this.getTri(0,0), this.getTri(0,1));
            for (int k = 1; k <= Nueva.getTri(0, 2); k++){
                if (this.getTri(k, 0) == fila && this.getTri(k, 1) == columna){
                    r++;
                }
                Nueva.setTri(k, 0, this.getTri(r,0));
                Nueva.setTri(k, 1, this.getTri(r, 1));
                Nueva.setTri(k, 2, this.getTri(r,2));
                r++;
            }
            Nueva.MostrarTripleta();
        }
        else if (this.BuscarDato(fila, columna) != dato) {
            System.out.println("No existe el dato a eliminar");
        }
        else {
            System.out.println("Rango fuera de la matriz");
        }
    }
}
