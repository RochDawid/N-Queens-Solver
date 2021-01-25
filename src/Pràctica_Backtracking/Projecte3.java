package Pràctica_Backtracking;

public class Projecte3 {

    private int T_tauler;
    private int filaReina = 0;
    private int columnaReina = 0;
    boolean[][] solucio;
    boolean resultat = false;

    public Projecte3(int dimension, int fila, int columna) {
        this.T_tauler = dimension;
        this.filaReina = fila;
        this.columnaReina = columna;
    }

    public boolean[][] cercaSolucio() {
        Tauler tauler = new Tauler(T_tauler);
        tauler.setCasella(this.filaReina, this.columnaReina);
        if (!resolucio(tauler, 0)) {
            System.out.println("No s'ha trobat cap solució");
        }
        return solucio;
    }

    private boolean resolucio(Tauler tauler, int columna) {
        if (columnaReina == 0 && columna == columnaReina) {
            columna++;
        }
        
        for (int i = 0; i < T_tauler && !resultat; i++) {
            if (esSegura(tauler, i, columna)) {
                tauler.setCasella(i, columna);
                if (columna < T_tauler - 1) {
                    if ((columna + 1) == columnaReina && columnaReina < (T_tauler - 1)) {
                        resultat = resolucio(tauler, columna + 2);
                    } else if ((columna + 1) == columnaReina && columnaReina == (T_tauler - 1)) {
                        resultat = true;
                        solucio = tauler.getMatriu();
                    } else {
                        resultat = resolucio(tauler, columna + 1);
                    }
                    if (!resultat) {
                        tauler.clearCasella(i, columna);
                    }
                } else {
                    resultat = true;
                    solucio = tauler.getMatriu();
                }
            }
        }
        return resultat;
    }

    private boolean esSegura(Tauler tauler, int fila, int columna) {
        int i, j;
        //Comprova a l'esquerra de la fila
        for (i = 0; i < columna; i++) {
            if (tauler.getCasella(fila, i)) {
                return false;
            }
        }
        //Comprova a la dreta de la fila
        for (i = columna; i < T_tauler; i++) {
            if (tauler.getCasella(fila, i)) {
                return false;
            }
        }

        //Comprova adalt de la casella
        for (i = 0; i < fila; i++) {
            if (tauler.getCasella(i, columna)) {
                return false;
            }
        }
        //Comprova abaix de la casella
        for (i = fila; i < T_tauler; i++) {
            if (tauler.getCasella(i, columna)) {
                return false;
            }
        }

        //Comprova a diagonal superior esquerra
        for (i = fila, j = columna; i >= 0 && j >= 0; i--, j--) {
            if (tauler.getCasella(i, j)) {
                return false;
            }
        }
        //Comprova la diagonal inferior esquerra
        for (i = fila, j = columna; j >= 0 && i < T_tauler; i++, j--) {
            if (tauler.getCasella(i, j)) {
                return false;
            }
        }

        //Comprova a diagonal superior dreta
        for (i = fila, j = columna; i >= 0 && j < T_tauler; i--, j++) {
            if (tauler.getCasella(i, j)) {
                return false;
            }
        }
        //Comprova la diagonal inferior dreta
        for (i = fila, j = columna; j < T_tauler && i < T_tauler; i++, j++) {
            if (tauler.getCasella(i, j)) {
                return false;
            }
        }
        return true;
    }
}
