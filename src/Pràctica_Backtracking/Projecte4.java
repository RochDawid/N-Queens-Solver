package Pràctica_Backtracking;

import java.util.ArrayList;

public class Projecte4 {

    private int T_tauler;
    private ArrayList<boolean[][]> solucions = new ArrayList<boolean[][]>();

    public Projecte4(int dimension) {
        this.T_tauler = dimension;
    }

    public ArrayList<boolean[][]> cercaSolucio() {

        Tauler tauler = new Tauler(T_tauler);
        resolucio(tauler, 0);
        if (solucions.isEmpty()) {
            System.out.println("No s'ha trobat cap solució");
        }
        return solucions;
    }

    private void resolucio(Tauler tauler, int columna) {
        if (columna == T_tauler) {
            solucions.add(tauler.getMatriu());
            return;
        }
        for (int i = 0; i < T_tauler; i++) {
            if (esSegura(tauler, i, columna)) {
                tauler.setCasella(i, columna);
                resolucio(tauler, columna + 1);
                tauler.clearCasella(i, columna);
            }
        }
    }

    private boolean esSegura(Tauler tauler, int fila, int columna) {
        int i, j;
        for (i = 0; i < columna; i++) {
            if (tauler.getCasella(fila, i)) {
                return false;
            }
        }
        for (i = fila, j = columna; i >= 0 && j >= 0; i--, j--) {
            if (tauler.getCasella(i, j)) {
                return false;
            }
        }

        for (i = fila, j = columna; j >= 0 && i < T_tauler; i++, j--) {
            if (tauler.getCasella(i, j)) {
                return false;
            }
        }
        return true;
    }
}
