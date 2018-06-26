package GUIs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import Auxiliar.*;
import DAOs.*;
import Entidades.*;

public class GUIAdvogado extends JFrame {

    public static void main(String[] args) {
        new GUIAdvogado();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("Escolha a pessoa");
    private JLabel lbPessoaIdPessoa = new JLabel("ID Pessoa");
    private List<String> stringpessoaIdPessoa = new ArrayList<>();
    private JComboBox comboPessoaIdPessoa = new JComboBox();
    private JLabel lbNumeroOab = new JLabel("Número OAB");
    private JTextField fdNumeroOab = new JTextField(30);
    private JTextField fdIdPessoa = new JTextField(30);

    private JLabel lbEmail = new JLabel("E-mail");
    private JTextField fdEmail = new JTextField(45);

    private JLabel lbSenha = new JLabel("Senha");
    private JTextField fdSenha = new JTextField(45);

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
    DAOAdvogado controle = new DAOAdvogado();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JTextComponent editor = (JTextComponent) comboPessoaIdPessoa.getEditor().getEditorComponent();

    Advogado advogado = new Advogado();

    public GUIAdvogado() {
        setSize(725, 380);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Advogados");

        painelCentral.setLayout(new GridLayout(4, 2));
        
        painelCentral.add(lbNumeroOab);
        painelCentral.add(fdNumeroOab);
        painelCentral.add(lbEmail);
        painelCentral.add(fdEmail);
        painelCentral.add(lbSenha);
        painelCentral.add(fdSenha);

        fdNumeroOab.setEnabled(false);
        fdEmail.setEnabled(false);
        fdSenha.setEnabled(false);

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
        painelNorteCima.add(lbPessoaIdPessoa);
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
        comboPessoaIdPessoa.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbPessoaIdPessoa.setFont(new Font("Courier New", Font.BOLD, 17));
        lbNumeroOab.setFont(new Font("Courier New", Font.BOLD, 17));
        lbEmail.setFont(new Font("Courier New", Font.BOLD, 17));
        lbSenha.setFont(new Font("Courier New", Font.BOLD, 17));
        comboPessoaIdPessoa.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdNumeroOab.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdEmail.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdSenha.setFont(new Font("Courier New", Font.PLAIN, 17));
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
                    advogado = new Advogado();
                    advogado = controle.obter(Integer.valueOf(fdIdPessoa.getText()));//mudei aqui
                    labelAviso.setBackground(Color.green);
                    if (advogado != null) {
                        //comboPessoaIdPessoa.setSelectedItem(advogado.getPessoaIdPessoa().toString());
                        fdNumeroOab.setText(advogado.getNumeroOab() + "");
                        fdEmail.setText(advogado.getEmail() + "");
                        fdSenha.setText(advogado.getSenha() + "");
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdNumeroOab.setEnabled(false);
                        fdNumeroOab.setText("");
                        fdEmail.setEnabled(false);
                        fdEmail.setText(null);
                        fdSenha.setEnabled(false);
                        fdSenha.setText(null);
//                        comboPessoaIdPessoa.setEnabled(false);
//                        comboPessoaIdPessoa.setSelectedIndex(0);
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
//                comboPessoaIdPessoa.setEnabled(false);
//                comboPessoaIdPessoa.setEnabled(true);
                fdNumeroOab.setEnabled(true);
                fdEmail.setEnabled(true);
                fdSenha.setEnabled(true);
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
                        advogado = new Advogado();
                        Integer id = Integer.valueOf(fdIdPessoa.getText());
                        advogado.setPessoaIdPessoa(id);
                        advogado.setNumeroOab(Integer.valueOf(fdNumeroOab.getText()));
                        advogado.setEmail(fdEmail.getText());
                        advogado.setSenha(fdSenha.getText());
                        controle.inserir(advogado);
                        labelAviso.setText("Registro inserido com sucesso!");
//                        comboPessoaIdPessoa.setEnabled(true);
//                        comboPessoaIdPessoa.requestFocus();

                        fdNumeroOab.setEnabled(false);
                        fdEmail.setEnabled(false);
                        fdSenha.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados! " +erro.getLocalizedMessage());
                    }
                } else { //btAlterar
                    try {
                        advogado = new Advogado();
                        Integer idPessoa = Integer.valueOf(String.valueOf(comboPessoaIdPessoa.getSelectedItem()).split("-")[0]);
                        advogado.setPessoaIdPessoa(idPessoa);
                        advogado.setNumeroOab(Integer.valueOf(fdNumeroOab.getText()));
                        advogado.setEmail(fdEmail.getText());
                        advogado.setSenha(fdSenha.getText());
                        controle.atualizar(advogado);
                        labelAviso.setText("Registro alterado com sucesso!");

                        //????????????????????????????????????????????????????????????
//                        fdPessoaIdPessoa.setEnabled(true);
//                        fdPessoaIdPessoa.requestFocus();
                        comboPessoaIdPessoa.setEnabled(true);
                        fdNumeroOab.setEnabled(false);
                        fdEmail.setEnabled(false);
                        fdSenha.setEnabled(false);
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
                fdNumeroOab.setEnabled(false);
                fdNumeroOab.setText("");
                fdEmail.setEnabled(false);
                fdEmail.setText("");
                fdSenha.setEnabled(false);
                fdSenha.setText("");
                //?????????????????????
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
                fdNumeroOab.setEnabled(true);
                fdEmail.setEnabled(true);
                fdSenha.setEnabled(true);
                fdEmail.requestFocus();
                //????????????????????????????????????????? fdPessoaIdPessoa.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + advogado.getPessoaIdPessoa() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(advogado);
                    labelAviso.setText("Removido!");
                    comboPessoaIdPessoa.setEnabled(false);
                    comboPessoaIdPessoa.setSelectedIndex(0);
                    fdNumeroOab.setText("");
                    fdNumeroOab.setEnabled(false);
                    fdEmail.setText("");
                    fdEmail.setEnabled(false);
                    fdSenha.setText("");
                    fdSenha.setEnabled(false);
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
                AdvogadoGUIListagem advogadoGUIListagem = new AdvogadoGUIListagem(controle.listInOrderNomeStrings("tanto faz"), cp);
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
