package Pràctica_Backtracking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public class Gràfics extends JFrame {

    ImageIcon imatge = new ImageIcon("reina.png");
    ImageIcon logo = new ImageIcon("logo.png");
    public JLabel[][] tablero;
    public int dimension, filap3, columnap3;
    public int index = 0;
    boolean pr4 = false;
    ArrayList<boolean[][]> solucions;
    String num = "8";
    JPanel ventana;
    JMenuBar barraMenu, barraBotones;
    JMenu Menu;
    JButton atras, adelante, resuelve;
    JMenuItem Tamaño, Proyecto3, Proyecto4;
    JTextField info, nSols;
    JOptionPane tamaño;
    Dimension dim;

    public void inicializa(int dimension) {
        this.dimension = dimension;
        ventana = new JPanel();
        ventana.setLayout(new GridLayout(dimension, dimension));
        this.setTitle("Pràctica 2 Backtracking");
        this.setIconImage(logo.getImage());
        barraMenu = new JMenuBar();
        barraBotones = new JMenuBar();

        Menu = new JMenu("Menú");
        Tamaño = new JMenuItem("Tria el tamany del tauler");
        Proyecto3 = new JMenuItem("Projecte 3");
        Proyecto4 = new JMenuItem("Projecte 4");
        resuelve = new JButton("Resol");
        atras = new JButton("<");
        adelante = new JButton(">");

        inicializaTablero(dimension);

        Tamaño.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeListener();
                num = JOptionPane.showInputDialog(null, "Introdueix el nombre de files i columnes", 0);
                inicializaTablero(Integer.parseInt(num));
            }
        });

        Proyecto3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                seleccionaCasilla();
                pr4 = false;
            }
        });

        Proyecto4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeListener();
                pr4 = true;
            }
        });

        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (index - 1 >= 0) {
                    index--;
                    pintaSolucio(solucions.get(index));
                }
            }
        });

        adelante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (index + 1 < solucions.size()) {
                    index++;
                    pintaSolucio(solucions.get(index));
                }
            }
        });

        resuelve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (pr4) {
                    Projecte4 p4 = new Projecte4(tablero.length);
                    solucions = p4.cercaSolucio();
                    index = 0;
                    pintaSolucio(solucions.get(index));
                } else {
                    Projecte3 p3 = new Projecte3(tablero.length, filap3, columnap3);
                    pintaSolucio(p3.cercaSolucio());
                }
            }
        });

        Menu.add(Tamaño);
        Menu.add(Proyecto3);
        Menu.add(Proyecto4);
        barraMenu.add(Menu);

        barraBotones.add(atras);
        barraBotones.add(resuelve);
        barraBotones.add(adelante);
//        nSols = new JTextField("Solució "+index);
//        info = new JTextField("Dimensió del tauler: " + dimension);
//        info.setEditable(false);
//        nSols.setEditable(false);
//        barraBotones.add(Box.createHorizontalStrut(250)); // per posar espai enmig
//        barraBotones.add(info);
//        barraBotones.add(nSols);
        barraBotones.setLayout(new GridBagLayout());

        dim = new Dimension(dimension * 80, dimension * 80 + 30);

        this.getContentPane().add(barraMenu, BorderLayout.NORTH);
        this.getContentPane().add(ventana, BorderLayout.CENTER);
        this.getContentPane().add(barraBotones, BorderLayout.SOUTH);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setSize(dim);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializaTablero(int dimension) {
        this.dimension = dimension;
        ventana.removeAll();
        tablero = new JLabel[dimension][dimension];
        ventana.setLayout(new GridLayout(dimension, dimension));
        dim = new Dimension(dimension * 80, dimension * 80 + 30);
        ventana.setSize(dim);
        initCasillas(dimension, tablero);
        ventana.updateUI();
    }

    private void initCasillas(int dimension, JLabel[][] tablero) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                tablero[i][j] = new JLabel();
                tablero[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                pintarCasillas(i, j);
                ventana.add(tablero[i][j]);
            }
        }
    }

    private void pintarCasillas(int i, int j) {
        if ((i + j) % 2 == 0) {
            tablero[i][j].setBackground(Color.WHITE);
        } else {
            tablero[i][j].setBackground(Color.BLACK);
        }
        tablero[i][j].setOpaque(true);
    }

    private void pintaSolucio(boolean[][] solucio) {
        if (solucio != null) {
            for (int i = 0; i < solucio.length; i++) {
                for (int j = 0; j < solucio.length; j++) {
                    tablero[i][j].setIcon(null);
                    if (solucio[i][j]) {
                        ImageIcon icon = new ImageIcon(imatge.getImage().getScaledInstance(tablero[i][j].getWidth(), tablero[i][j].getHeight(), Image.SCALE_DEFAULT));
                        tablero[i][j].setIcon(icon);
                    }
                }
            }
        }
    }

    private void seleccionaCasilla() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                tablero[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent me) {
                    }

                    @Override
                    public void mousePressed(MouseEvent me) {
                        for (int i = 0; i < dimension; i++) {
                            for (int j = 0; j < dimension; j++) {
                                pintarCasillas(i, j);
                            }
                        }
                        for (int i = 0; i < dimension; i++) {
                            for (int j = 0; j < dimension; j++) {
                                if (me.getSource() == tablero[i][j]) {
                                    ponerReina(i, j);
                                    filap3 = i;
                                    columnap3 = j;
                                }
                            }
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent me) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent me) {
                    }

                    @Override
                    public void mouseExited(MouseEvent me) {
                    }
                }
                );
            }
        }
    }

    private void ponerReina(int i, int j) {
        for (int fila = 0; fila < dimension; fila++) {
            for (int columna = 0; columna < dimension; columna++) {
                if (tablero[fila][columna].getIcon() != null) {
                    tablero[fila][columna].setIcon(null);
                    tablero[fila][columna].revalidate();
                    pintarCasillas(fila, columna);
                }
            }
        }
        ImageIcon icon = new ImageIcon(imatge.getImage().getScaledInstance(tablero[i][j].getWidth(), tablero[i][j].getHeight(), Image.SCALE_DEFAULT));
        tablero[i][j].setIcon(icon);
    }

    public void removeListener() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                for (MouseListener ml : tablero[i][j].getMouseListeners()) {
                    tablero[i][j].removeMouseListener(ml);
                }
            }
        }
    }
}
