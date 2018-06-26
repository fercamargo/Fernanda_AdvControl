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

public class GUITipoCliente extends JFrame {
    public static void main(String[] args) {
        new GUITipoCliente();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("Escolha a pessoa");
    private JLabel lbClientePessoaIdPessoa = new JLabel("ID Cliente");
    private List<String> stringclientePessoaIdPessoa = new ArrayList<>();
    private JComboBox comboClientePessoaIdPessoa = new JComboBox();
    private JLabel lbNomeTipoCliente = new JLabel("Tipo do Cliente");
    private JTextField fdNomeTipoCliente = new JTextField(45);
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

    DAOTipoCliente controle = new DAOTipoCliente();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JTextComponent editor = (JTextComponent) comboClientePessoaIdPessoa.getEditor().getEditorComponent();

    TipoCliente tipocliente = new TipoCliente();

    public GUITipoCliente(){
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Tipo do Cliente");

        painelCentral.setLayout(new GridLayout(2, 2));
        painelCentral.add(lbNomeTipoCliente);

        fdNomeTipoCliente.setEnabled(false);

        List<String> combo = new ArrayList<>();
        combo = new DAOCliente().listInOrderNomeStrings("idPessoa");
        for(int x = 0; x < combo.size(); x++) {
            String[] aux = combo.get(x).split(";");
            stringclientePessoaIdPessoa.add(aux[0] + "-" + aux[1]);
        }
        comboClientePessoaIdPessoa = new JComboBox(stringclientePessoaIdPessoa.toArray());
        painelCentral.add(lbClientePessoaIdPessoa);
        painelCentral.add(comboClientePessoaIdPessoa);
        comboClientePessoaIdPessoa.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboClientePessoaIdPessoa));


cp.setBackground(Color.white);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(comboClientePessoaIdPessoa);
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
        comboClientePessoaIdPessoa.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbClientePessoaIdPessoa.setFont(new Font("Courier New", Font.BOLD, 17));
        lbNomeTipoCliente.setFont(new Font("Courier New", Font.BOLD, 17));
        comboClientePessoaIdPessoa.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdNomeTipoCliente.setFont(new Font("Courier New", Font.PLAIN, 17));
        labelAviso.setFont(new Font("Courier New", Font.BOLD, 20));
        btInserir.setVisible(false);
        btAtualizar.setVisible(false);
        btRemover.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        painelSul.add(labelAviso);
        
        comboClientePessoaIdPessoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    fdIdPessoa.setText(String.valueOf(comboClientePessoaIdPessoa.getSelectedItem()).split("-")[0]);
                    //pega o id do cliente e manda para o fdIdPessoa
                }

            }

        });

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    tipocliente = new TipoCliente();
                    tipocliente = controle.obter(tipocliente.getClientePessoaIdPessoa());
                    labelAviso.setBackground(Color.green);
                    if (tipocliente != null) {
                        comboClientePessoaIdPessoa.setSelectedItem(tipocliente.getClientePessoaIdPessoa().toString());
                        fdNomeTipoCliente.setText(tipocliente.getNomeTipoCliente() + "");
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdNomeTipoCliente.setEnabled(false);
                        fdNomeTipoCliente.setText(null);
                        comboClientePessoaIdPessoa.setEnabled(false);
                        comboClientePessoaIdPessoa.setSelectedIndex(0);
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
//                fdClientePessoaIdPessoa.setEnabled(false);
//                comboClientePessoaIdPessoa.setEnabled(true);
                fdNomeTipoCliente.setEnabled(true);
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
                    tipocliente = new TipoCliente();
                    Integer id = Integer.valueOf(fdIdPessoa.getText());
                        tipocliente.setClientePessoaIdPessoa(id);
                        tipocliente.setNomeTipoCliente(fdNomeTipoCliente.getText());
                        controle.inserir(tipocliente);
                        labelAviso.setText("Registro inserido com sucesso!");
//                        fdClientePessoaIdPessoa.setEnabled(true);
//                        fdClientePessoaIdPessoa.requestFocus();
                        comboClientePessoaIdPessoa.setEnabled(false);
                        fdNomeTipoCliente.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        tipocliente = new TipoCliente();
                        Integer idPessoa = Integer.valueOf(String.valueOf(comboClientePessoaIdPessoa.getSelectedItem()).split("-")[0]);
                        tipocliente.setClientePessoaIdPessoa(idPessoa);
                        tipocliente.setNomeTipoCliente(fdNomeTipoCliente.getText());
                        controle.atualizar(tipocliente);
                        labelAviso.setText("Registro alterado com sucesso!");
                        
                        comboClientePessoaIdPessoa.setEnabled(false);
                        fdNomeTipoCliente.setEnabled(false);
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
                comboClientePessoaIdPessoa.setEnabled(false);
                comboClientePessoaIdPessoa.setSelectedIndex(0);
                fdNomeTipoCliente.setEnabled(false);
                fdNomeTipoCliente.setText("");
               
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
                comboClientePessoaIdPessoa.setEnabled(true);
                fdNomeTipoCliente.setEnabled(true);
                
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + tipocliente.getClientePessoaIdPessoa() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(tipocliente);
                    labelAviso.setText("Removido!");
                comboClientePessoaIdPessoa.setEnabled(false);
                comboClientePessoaIdPessoa.setSelectedIndex(0);
                    fdNomeTipoCliente.setText("");
                    fdNomeTipoCliente.setEnabled(false);
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
                new TipoClienteGUIListagem(controle.listInOrderNomeStrings("tanto faz"), cp);
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
