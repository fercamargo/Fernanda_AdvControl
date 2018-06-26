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

public class GUIProcesso extends JFrame {
    public static void main(String[] args) {
        new GUIProcesso();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("ID Processo: ");
    private JLabel lbIdProcesso = new JLabel("ID Processo");
    private JTextField fdIdProcesso = new JTextField(15);

    private JLabel lbNumeroAcao = new JLabel("Número da Ação");
    private JTextField fdNumeroAcao = new JTextField(45);

    private JSpinner spinnerdataCadastroProcesso = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditordataCadastroProcesso = new JSpinner.DateEditor(spinnerdataCadastroProcesso, "dd/MM/yyyy");
    private JLabel lbDataCadastroProcesso = new JLabel("Data de Cadastro");
    private JLabel lbSituacaoPagamento = new JLabel("Situação de Pagamento");
    private JTextField fdSituacaoPagamento = new JTextField(45);


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

    DAOProcesso controle = new DAOProcesso();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Processo processo = new Processo();

    public GUIProcesso(){
        setSize(725, 380);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Processos");

        painelCentral.setLayout(new GridLayout(4, 2));
        painelCentral.add(lbNumeroAcao);
        painelCentral.add(fdNumeroAcao);
        painelCentral.add(lbSituacaoPagamento);
        painelCentral.add(fdSituacaoPagamento);

        fdNumeroAcao.setEnabled(false);
        fdSituacaoPagamento.setEnabled(false);

        painelCentral.add(lbDataCadastroProcesso);
        painelCentral.add(spinnerdataCadastroProcesso);
        spinnerdataCadastroProcesso.setEditor(spinnerEditordataCadastroProcesso);
        spinnerdataCadastroProcesso.setEnabled(false);
        List<String> combo = new ArrayList<>();


cp.setBackground(Color.white);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(fdIdProcesso);
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
        fdIdProcesso.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbIdProcesso.setFont(new Font("Courier New", Font.BOLD, 17));
        lbNumeroAcao.setFont(new Font("Courier New", Font.BOLD, 17));
        lbDataCadastroProcesso.setFont(new Font("Courier New", Font.BOLD, 17));
        lbSituacaoPagamento.setFont(new Font("Courier New", Font.BOLD, 17));
        fdIdProcesso.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdNumeroAcao.setFont(new Font("Courier New", Font.PLAIN, 17));
        spinnerdataCadastroProcesso.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdSituacaoPagamento.setFont(new Font("Courier New", Font.PLAIN, 17));
        labelAviso.setFont(new Font("Courier New", Font.BOLD, 20));
        btInserir.setVisible(false);
        btAtualizar.setVisible(false);
        btRemover.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        painelSul.add(labelAviso);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    processo = new Processo();
                    int idProcesso = Integer.valueOf(fdIdProcesso.getText());
                    processo.setIdProcesso(Integer.valueOf(fdIdProcesso.getText()));
                    processo = controle.obter(processo.getIdProcesso());
                    labelAviso.setBackground(Color.green);
                    if (processo != null) {
                        fdIdProcesso.setText(processo.getIdProcesso() + "");
                        fdNumeroAcao.setText(processo.getNumeroAcao() + "");
                        spinnerdataCadastroProcesso.setValue(processo.getDataCadastroProcesso());
                        fdSituacaoPagamento.setText(processo.getSituacaoPagamento() + "");
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdNumeroAcao.setEnabled(false);
                        fdNumeroAcao.setText(null);
                        fdSituacaoPagamento.setEnabled(false);
                        fdSituacaoPagamento.setText(null);
                        spinnerdataCadastroProcesso.setEnabled(false);
                        spinnerdataCadastroProcesso.setValue(new Date());
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
                fdIdProcesso.setEnabled(false);
                fdNumeroAcao.setEnabled(true);
                spinnerdataCadastroProcesso.setEnabled(true);
                fdSituacaoPagamento.setEnabled(true);
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
                if(acao){ //btInserir
                    try {
                    processo = new Processo();
                        processo.setIdProcesso(Integer.valueOf(fdIdProcesso.getText()));
                        processo.setNumeroAcao(fdNumeroAcao.getText());
                        processo.setDataCadastroProcesso((Date) spinnerdataCadastroProcesso.getValue());
                        processo.setSituacaoPagamento(fdSituacaoPagamento.getText());
                        controle.inserir(processo);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdIdProcesso.setEnabled(true);
                        fdIdProcesso.requestFocus();
                        fdNumeroAcao.setEnabled(false);
                        spinnerdataCadastroProcesso.setEnabled(false);
                        fdSituacaoPagamento.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        processo = new Processo();
                        processo.setIdProcesso(Integer.valueOf(fdIdProcesso.getText()));
                        processo.setNumeroAcao(fdNumeroAcao.getText());
                        processo.setDataCadastroProcesso((Date) spinnerdataCadastroProcesso.getValue());
                        processo.setSituacaoPagamento(fdSituacaoPagamento.getText());
                        controle.atualizar(processo);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdIdProcesso.setEnabled(true);
                        fdIdProcesso.requestFocus();
                        fdNumeroAcao.setEnabled(false);
                        spinnerdataCadastroProcesso.setEnabled(false);
                        fdSituacaoPagamento.setEnabled(false);
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
                fdIdProcesso.setEnabled(false);
                fdIdProcesso.setText("");
                fdNumeroAcao.setEnabled(false);
                fdNumeroAcao.setText("");
                spinnerdataCadastroProcesso.setEnabled(false);
                spinnerdataCadastroProcesso.setValue(new Date());
                fdSituacaoPagamento.setEnabled(false);
                fdSituacaoPagamento.setText("");
                fdIdProcesso.setEnabled(true);
                fdIdProcesso.requestFocus();
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
                fdNumeroAcao.setEnabled(true);
                spinnerdataCadastroProcesso.setEnabled(true);
                fdSituacaoPagamento.setEnabled(true);
                fdNumeroAcao.requestFocus();
                fdIdProcesso.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + processo.getIdProcesso() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(processo);
                    labelAviso.setText("Removido!");
                    fdIdProcesso.setText("");
                    fdNumeroAcao.setText("");
                    fdNumeroAcao.setEnabled(false);
                    spinnerdataCadastroProcesso.setEnabled(false);
                    spinnerdataCadastroProcesso.setValue(new Date());
                    fdSituacaoPagamento.setText("");
                    fdSituacaoPagamento.setEnabled(false);
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
                new ProcessoGUIListagem(controle.listInOrderNomeStrings("tanto faz"), cp);
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
