public class Nodo {
    private int Dato;
    private int Fila;
    private int Col;
    private Nodo Liga;
    private Nodo LC;
    private Nodo LF;

    public Nodo (int fila, int columna){
        this.Col = columna;
        this.Fila = fila;
        this.Liga = null;
        this.LC = null;
        this.LF = null;
        this.Dato = 0;
    }

    public Nodo (int fila, int columna, int Dato){
        this.Col = columna;
        this.Fila = fila;
        this.Liga = null;
        this.LC = null;
        this.LF = null;
        this.Dato = Dato;
    }

    public int getDato() {
        return Dato;
    }

    public void setDato(int dato) {
        Dato = dato;
    }

    public int getFila() {
        return Fila;
    }

    public void setFila(int fila) {
        Fila = fila;
    }

    public int getCol() {
        return Col;
    }

    public void setCol(int col) {
        Col = col;
    }

    public Nodo getLiga() {
        return Liga;
    }

    public void setLiga(Nodo liga) {
        Liga = liga;
    }

    public Nodo getLC() {
        return LC;
    }

    public void setLC(Nodo LC) {
        this.LC = LC;
    }

    public Nodo getLF() {
        return LF;
    }

    public void setLF(Nodo LF) {
        this.LF = LF;
    }
}
