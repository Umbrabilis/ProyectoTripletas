import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
public class Main {
    public static void main(String[] args) {
        String nombreArchivoRelativo = "MatricesDispersas" + File.separator + "src" + File.separator + "Matriz.txt";
        File archivo = new File(nombreArchivoRelativo);
        String rutaAbsoluta = archivo.getAbsolutePath();
        int Matriz[][] = Matriz(rutaAbsoluta);
        String Matriz2Ruta = "MatricesDispersas" + File.separator + "src" + File.separator + "Matriz2.txt";
        String archivo2 = "Matriz2.txt";
        String rutaAbsolutaMatriz2 = archivo.getAbsolutePath();
        int Matriz2[][] = Matriz(rutaAbsolutaMatriz2);
        //Tripleta T = new Tripleta(DatosDiferentesdeCero(Matriz), Matriz.length, Matriz[0].length);
        //Tripleta T2 = new Tripleta(DatosDiferentesdeCero(Matriz2), Matriz2.length, Matriz2[0].length);
        //T.LlenarTripleta(Matriz);
        //T.MostrarTripleta();
        //T.InsertarDato(0, 0, 12);
        //T.InsertarDato(0, 1, 10);
        //T2.LlenarTripleta(Matriz2);
        //T.SumaFilasColumnas();
        //T.Sumar(T, T2);
        Forma1 F1 = new Forma1();
        F1.CrearForma1(Matriz);
        Forma1 F1i = new Forma1();
        F1.Eliminar(2, 1, 6);
        //F1.MostrarForma1();
        F1i.CrearForma1(Matriz2);
        //F1i.MostrarForma1();
        //F1.Suma(F1, F1i);
        //F1.MostrarForma1();
    }

    public static int[][] Matriz(String archivo) {
        int Matriz[][];
        int filas = 0;
        int columnas = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea = reader.readLine();
            while (linea != null) {
                filas++;
                String[] valores = linea.split(" ");
                columnas = valores.length;
                linea = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Matriz = new int[filas][columnas];
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int i = 0;
            while ((linea = reader.readLine()) != null) {
                String[] valores = linea.split(" ");
                for (int j = 0; j < valores.length; j++) {
                    Matriz[i][j] = Integer.parseInt(valores[j]);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Matriz;
    }

    public static int DatosDiferentesdeCero(int [][] Matriz){
        int i = 0;
        int cont = 0;
        for (; i < Matriz.length; i++){
            for (int j = 0; j < Matriz[i].length; j++){
                if (Matriz[i][j] != 0){
                    cont++;
                }
            }
        }
        return cont;
    }
}