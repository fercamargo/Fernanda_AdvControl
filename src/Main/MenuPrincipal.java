package Main;

import Auxiliar.CentroDoMonitorMaior;
import GUIs.GUIAdvogado;
import GUIs.GUIPessoa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Fernanda
 */
public class MenuPrincipal extends JFrame {

    public static void main(String[] args) {
        new MenuPrincipal();
    }

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JLabel lbTitulo = new JLabel("Menu - Cadastros");

    Font fonte = new Font("Calibri", Font.CENTER_BASELINE, 30);

    JLabel labelComImagemDeTamanhoDiferente = new JLabel();

    JMenuBar menuBar = new JMenuBar();
    JMenu menuCadastros = new JMenu("Cadastros");
    JMenuItem cadPessoa = new JMenuItem("Pessoa");
    JMenuItem cadAdvogado = new JMenuItem("Advogado");

    Point p;

    public MenuPrincipal() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 1200);
        setTitle(lbTitulo.getText());

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pnNorte.add(lbTitulo);
        lbTitulo.setFont(fonte);
        pnNorte.setBackground(Color.LIGHT_GRAY);

        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/fotos/logomarca.png"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(674, 208, Image.SCALE_FAST));

            labelComImagemDeTamanhoDiferente = new JLabel();
            labelComImagemDeTamanhoDiferente.setIcon(icone);
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem");
        }

        cadPessoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new GUIPessoa();
            }
        });

        cadAdvogado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new GUIAdvogado();
            }
        });

        pnCentro.add(labelComImagemDeTamanhoDiferente);
        pnCentro.setBackground(Color.BLACK);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);

        menuCadastros.add(cadPessoa);
        menuCadastros.add(cadAdvogado);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai do sistema  
                System.exit(0);
            }
        });

        pack();
        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
    }
}
