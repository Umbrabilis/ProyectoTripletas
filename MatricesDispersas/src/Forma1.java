public class Forma1 {
    private Nodo Punta;

    public Forma1(){
        this.Punta = null;
    }

    public void CrearForma1 (int Matriz[][]){
        Paso1(Matriz.length, Matriz[0].length);
        Paso2(Matriz);
        Paso3();
    }

    public void Paso1(int f, int c) {
        int mayor = 0;
        if (f > c){
            mayor = f;
        } else {
            mayor = c;
        }
        InsertarFinal(mayor);
        for (int i = 0; i < mayor; i++){
            InsertarFinal(i);
        }
        Punta.setFila(f);
        Punta.setCol(c);
    }

    public void Paso2(int[][] Matriz){
        Nodo p = Punta.getLiga();
        for (int i = 0; i < Matriz.length; i++){
            for (int j = 0; j < Matriz[0].length; j++){
                if (Matriz[i][j] != 0){
                    InsertarFinalF(i, j, Matriz[i][j], p);
                }
            }
            p = p.getLiga();
        }
    }

    public void Paso3() {
        Nodo Rc = Punta.getLiga(), A = Rc, p = Punta.getLiga(), q = p.getLF();
        while (Rc != Punta) {
            A = Rc;
            p = Punta.getLiga();
            q = p.getLF();
            while (p != Punta) {
                while (q != p) {
                    if (A.getCol() == q.getCol()) {
                        A.setLC(q);
                        A = q;
                        q = p;
                    } else {
                        q = q.getLF();
                    }
                }
                p = p.getLiga();
                q = p.getLF();
            }
            A.setLC(Rc);
            Rc = Rc.getLiga();
        }
    }

    public void InsertarFinal(int mayor) {
        Nodo x = new Nodo(mayor, mayor);
        x.setLF(x);
        Nodo p = Punta;
        if (Punta == null) {
            Punta = x;
            Punta.setLiga(Punta);
        } else {
            while (p.getLiga() != Punta) {
                p= p.getLiga();
            }
            p.setLiga(x);
            x.setLiga(Punta);
        }
    }

    public void InsertarFinalF (int fila, int columna, int dato, Nodo p){
        Nodo x = new Nodo(fila, columna, dato);
        Nodo q = p;
        if (p.getLF() == null) {
            p = x;
            p.setLF(p);
            p.setLC(p);
        } else {
            while (q.getLF() != p) {
                q= q.getLF();
            }
            q.setLF(x);
            x.setLF(p);
        }
    }

    public void MostrarForma1 () {
        Nodo p = Punta;
        System.out.println("|" + p.getFila() + "⋅" + p.getCol() + "⋅" + p.getDato() + "|");
        p = p.getLiga();
        Nodo q = p.getLF();
        while (p != Punta){
            System.out.print("|" + p.getFila() + "⋅" + p.getCol() + "⋅" + p.getDato() + "|" + "---->");
            while (q != p){
                System.out.print("|" + q.getFila() + "⋅" + q.getCol() + "⋅" + q.getDato() + "|--->");
                q = q.getLF();
            }
            p = p.getLiga();
            q = p.getLF();
            System.out.println();
        }
    }

    public void Multiplicar(Forma1 a, Forma1 b){
        int suma = 0;
        Forma1 c = new Forma1();
        c.Paso1(a.Punta.getFila(), b.Punta.getCol());
        Nodo P = c.Punta.getLiga();
        for (int i = 0; i < a.Punta.getFila(); i++){
            for (int j = 0; j < b.Punta.getCol(); j++){
                for (int l = 0; l < b.Punta.getFila(); l++) {
                    suma += a.BuscarDato(i, l) * b.BuscarDato(l, j);
                }
                if (suma != 0){
                    while (P.getFila() != i){
                        P = P.getLiga();
                    }
                    c.InsertarFinalF(i, j, suma, P);
                    P = c.Punta.getLiga();
                }
                suma = 0;
            }
        }
        c.Paso3();
        c.MostrarForma1();
    }

    public void Suma (Forma1 a, Forma1 b){
        Forma1 c = new Forma1();
        c.Paso1(a.Punta.getFila(), a.Punta.getCol());
        Nodo P = c.Punta.getLiga();
        int suma = 0;
        for (int i = 0; i < a.Punta.getFila(); i++){
            for (int j = 0; j < a.Punta.getCol(); j++){
                suma = a.BuscarDato(i,j) + b.BuscarDato(i, j);
                if (suma != 0){
                    while (P.getFila() != i){
                        P = P.getLiga();
                    }
                    c.InsertarFinalF(i, j, suma, P);
                    P = c.Punta.getLiga();
                }
                suma = 0;
            }
        }
        c.MostrarForma1();
    }

    public void SumaFilaColumnas(){
        int [] Filas = new int [Punta.getFila()];
        int [] Columnas = new int [Punta.getCol()];
        Nodo p = Punta.getLiga();
        Nodo q = p.getLF();

        while (p != Punta){
            while (q != p){
                Filas[q.getFila()] += q.getDato();
                Columnas[q.getCol()] += q.getDato();
                q = q.getLF();
            }
            p = p.getLiga();
            q = p.getLF();
        }
    }

    public void Eliminar (int fila, int columna, int dato){
        if (this.BuscarDato(fila, columna) == dato){
            Nodo P = Punta.getLiga();
            Nodo Q = Punta;
            while (P.getFila() != fila){
                Q = P;
                P = P.getLiga();
            }
            Q = P;
            P = P.getLF();
            while (P.getCol() != columna){
                Q = P;
                P = P.getLF();
            }
            Q.setLF(P.getLF());
            Q.setLC(P.getLC());
        }
        else if (fila > this.Punta.getFila() || columna > this.Punta.getCol()){
            System.out.println("Rango fuera de la matriz");
        }
        else if (this.BuscarDato(fila, columna) != dato) {
            System.out.println("No existe el dato a eliminar");
        }
        this.MostrarForma1();
    }

    public void Insertar (int fila, int columna, int dato){
        if (fila > this.Punta.getFila() || columna > this.Punta.getCol()){
            System.out.println("Rango fuera de la matriz");
        }
        else if (this.BuscarDato(fila, columna) != 0) {

        } else {
            Nodo P = Punta.getLiga();
            Nodo Q = Punta;
        }
    }
    public int BuscarDato (int fila, int columna){
        Nodo p = Punta.getLiga();
        Nodo q = p.getLF();
        while (p != Punta){
            while (q != p){
                if (q.getFila() == fila && q.getCol() == columna){
                    return q.getDato();
                }
                q = q.getLF();
            }
            p = p.getLiga();
            q = p.getLF();
        }
        return 0;
    }


}


