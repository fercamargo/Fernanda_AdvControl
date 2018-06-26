package GUIs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import java.io.File;
import javax.swing.text.JTextComponent;
import Auxiliar.*;
import DAOs.*;
import Entidades.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

public class GUICliente extends JFrame {

    public static void main(String[] args) {
        new GUICliente();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("Escolha a pessoa");
    private JLabel lbPessoaIdPessoa = new JLabel("ID Pessoa");
    private List<String> stringpessoaIdPessoa = new ArrayList<>();
    private JComboBox comboPessoaIdPessoa = new JComboBox();
    private JLabel lbEstadoCivilCliente = new JLabel("Estado Civil");
    private JTextField fdEstadoCivilCliente = new JTextField(45);
    private JTextField fdIdPessoa = new JTextField(30);

    private JPanel painelNortes = new JPanel(new GridLayout(2, 1));
    private JPanel painelNorteCima = new JPanel();
    private JPanel painelNorteBaixo = new JPanel();
    private JPanel painelCentralFora = new JPanel(new BorderLayout());
    private JPanel painelCentral = new JPanel();
    private JPanel painelSul = new JPanel();
    private JLabel labelBranco = new JLabel();

    JButton btInserir = new JButton(new ImageIcon(getClass().getResource("/icones/add.png")));
    JButton btSalvar = new JButton(new ImageIcon(getClass().getResource("/icones/confirmar.png")));
    JButton btRemover = new JButton(new ImageIcon(getClass().getResource("/icones/deletar.png")));
    JButton btAtualizar = new JButton(new ImageIcon(getClass().getResource("/icones/att.png")));
    JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/icones/search.png")));
    JButton btCancelar = new JButton(new ImageIcon(getClass().getResource("/icones/cancelar.png")));
    JButton btListar = new JButton(new ImageIcon(getClass().getResource("/icones/listar.png")));

    DAOPessoa daoPessoa = new DAOPessoa();
    DAOCliente controle = new DAOCliente();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JTextComponent editor = (JTextComponent) comboPessoaIdPessoa.getEditor().getEditorComponent();

    Cliente cliente = new Cliente();

    public GUICliente() {
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Clientes");

        painelCentral.setLayout(new GridLayout(2, 2));

        painelCentral.add(lbEstadoCivilCliente);

        fdEstadoCivilCliente.setEnabled(false);

        List<String> combo = new ArrayList<>();
        combo = new DAOPessoa().listInOrderNomeStrings("idPessoa");
        for (int x = 0; x < combo.size(); x++) {
            String[] aux = combo.get(x).split(";");
            stringpessoaIdPessoa.add(aux[0] + "-" + aux[1]);
        }
        comboPessoaIdPessoa = new JComboBox(stringpessoaIdPessoa.toArray());
        painelCentral.add(lbPessoaIdPessoa);
        painelCentral.add(comboPessoaIdPessoa);
        comboPessoaIdPessoa.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboPessoaIdPessoa));
        comboPessoaIdPessoa.setEnabled(true);

        cp.setBackground(Color.white);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(comboPessoaIdPessoa);
        painelNorteCima.add(fdIdPessoa);
        painelNorteBaixo.add(btBuscar);
        painelNorteBaixo.add(btInserir);
        painelNorteBaixo.add(btAtualizar);
        painelNorteBaixo.add(btRemover);
        painelNorteBaixo.add(btSalvar);
        painelNorteBaixo.add(btCancelar);
        painelNorteBaixo.add(btListar);
        painelNorteCima.setBackground(Color.white);
        painelNorteBaixo.setBackground(Color.white);
        painelCentralFora.setBackground(Color.white);
        painelCentral.setBackground(Color.white);
        painelSul.setBackground(Color.white);
        btInserir.setBackground(Color.WHITE);
        btSalvar.setBackground(Color.WHITE);
        btRemover.setBackground(Color.WHITE);
        btAtualizar.setBackground(Color.WHITE);
        btBuscar.setBackground(Color.WHITE);
        btCancelar.setBackground(Color.WHITE);
        btListar.setBackground(Color.WHITE);

        labelTitulo.setFont(new Font("Courier New", Font.BOLD, 20));
        fdIdPessoa.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbPessoaIdPessoa.setFont(new Font("Courier New", Font.BOLD, 17));
        lbEstadoCivilCliente.setFont(new Font("Courier New", Font.BOLD, 17));
        comboPessoaIdPessoa.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdEstadoCivilCliente.setFont(new Font("Courier New", Font.PLAIN, 17));
        labelAviso.setFont(new Font("Courier New", Font.BOLD, 20));
        btInserir.setVisible(false);
        btAtualizar.setVisible(false);
        btRemover.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        painelSul.add(labelAviso);

        comboPessoaIdPessoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    fdIdPessoa.setText(String.valueOf(comboPessoaIdPessoa.getSelectedItem()).split("-")[0]);
                    //pega o id da pessoa e manda para o fdIdPessoa
                }

            }

        });

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliente = new Cliente();
                    cliente = controle.obter(cliente.getPessoaIdPessoa());
                    labelAviso.setBackground(Color.green);
                    if (cliente != null) {
                        comboPessoaIdPessoa.setSelectedItem(cliente.getPessoaIdPessoa().toString());
                        fdEstadoCivilCliente.setText(cliente.getEstadoCivilCliente() + "");
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdEstadoCivilCliente.setEnabled(false);
                        fdEstadoCivilCliente.setText(null);
                        comboPessoaIdPessoa.setEnabled(false);
                        comboPessoaIdPessoa.setSelectedIndex(0);
                        labelAviso.setText("Não encontrado!");
                        labelAviso.setBackground(Color.red);
                        btInserir.setVisible(true);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);
                        btListar.setVisible(false);
                    }
                } catch (Exception erro) {
                    labelAviso.setText("Preencha os campos corretamente!");
                    labelAviso.setBackground(Color.red);
                }
            }
        }
        );

        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = true;
                fdIdPessoa.setEnabled(false);
                comboPessoaIdPessoa.setEnabled(true);
                fdEstadoCivilCliente.setEnabled(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btInserir.setVisible(false);
                btListar.setVisible(false);
            }
        }
        );

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao) { //btInserir
                    try {
                        cliente = new Cliente();
                        Integer id = Integer.valueOf(fdIdPessoa.getText());
                        cliente.setEstadoCivilCliente(fdEstadoCivilCliente.getText());
                        controle.inserir(cliente);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdIdPessoa.setEnabled(true);
                        fdIdPessoa.requestFocus();
                        comboPessoaIdPessoa.setEnabled(false);
                        fdEstadoCivilCliente.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        cliente = new Cliente();
                        Integer idPessoa = Integer.valueOf(String.valueOf(comboPessoaIdPessoa.getSelectedItem()).split("-")[0]);
                        cliente.setPessoaIdPessoa(idPessoa);
                        cliente.setEstadoCivilCliente(fdEstadoCivilCliente.getText());
                        controle.atualizar(cliente);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdIdPessoa.setEnabled(true);
                        fdIdPessoa.requestFocus();
//                        comboPessoaIdPessoa.setEnabled(false);
//                        fdEstadoCivilCliente.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                }
            }
        }
        );

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelAviso.setText("Cancelado!");
                comboPessoaIdPessoa.setEnabled(false);
                comboPessoaIdPessoa.setSelectedIndex(0);
                fdEstadoCivilCliente.setEnabled(false);
                fdEstadoCivilCliente.setText("");
//                fdPessoaIdPessoa.setEnabled(true);
//                fdPessoaIdPessoa.requestFocus();
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
            }
        }
        );

        btAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = false;
                comboPessoaIdPessoa.setEnabled(true);
                fdEstadoCivilCliente.setEnabled(true);
//                fdPessoaIdPessoa.setEnabled(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btRemover.setVisible(false);
                btAtualizar.setVisible(false);
                btListar.setVisible(false);
            }
        }
        );

        btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btRemover.setVisible(false);
                btListar.setVisible(false);
                btAtualizar.setVisible(false);
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + cliente.getPessoaIdPessoa() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(cliente);
                    labelAviso.setText("Removido!");
                    comboPessoaIdPessoa.setEnabled(false);
                    comboPessoaIdPessoa.setSelectedIndex(0);
                    fdEstadoCivilCliente.setText("");
                    fdEstadoCivilCliente.setEnabled(false);
                    btListar.setVisible(true);
                } else {
                    labelAviso.setText("Remoção cancelada!");
                    btAtualizar.setVisible(true);
                    btRemover.setVisible(true);
                }
            }
        }
        );

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClienteGUIListagem(controle.listInOrderNomeStrings("tanto faz"), cp);
            }
        }
        );

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        }
        );
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
