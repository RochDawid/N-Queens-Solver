package Pràctica_Backtracking;

public class Tauler {

    public int tamany_tauler;
    public boolean[][] tauler;

    public Tauler(int tamany_tauler) {
        this.tamany_tauler = tamany_tauler;
        tauler = new boolean[this.tamany_tauler][this.tamany_tauler];
    }

    public void setCasella(int i, int j) {
        tauler[i][j] = true;
    }

    public void clearCasella(int i, int j) {
        tauler[i][j] = false;
    }

    public boolean getCasella(int i, int j) {
        return tauler[i][j];
    }

    public boolean[][] getMatriu() {
        return copiarMatriu(tauler);
    }
    
    private boolean[][] copiarMatriu(boolean[][] origen) {
        boolean[][] desti = new boolean[origen.length][origen[1].length];
        for (int i = 0; i < origen.length; i++) {
            for (int k = 0; k < origen[1].length; k++) {
                desti[i][k] = origen[i][k];
            }
        }
        return desti;
    }
}
