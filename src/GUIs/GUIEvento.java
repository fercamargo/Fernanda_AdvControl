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
import GUIs.EventoGUIListagem;
import java.sql.Time;

public class GUIEvento extends JFrame {
    public static void main(String[] args) {
        new GUIEvento();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("ID Agenda: ");
    private JLabel lbIdAgenda = new JLabel("ID Agenda");
    private JTextField fdIdAgenda = new JTextField(15);

    private JSpinner spinnerdataEvento = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditordataEvento = new JSpinner.DateEditor(spinnerdataEvento, "dd/MM/yyyy");
    private JLabel lbDataEvento = new JLabel("Data do Evento");
    private JLabel lbHoraInicio = new JLabel("Hora de Início");
    private JTextField fdHoraInicio = new JTextField(20);

    private JLabel lbHotaTermino = new JLabel("Hora de Término");
    private JTextField fdHoraTermino = new JTextField(20);

    private JLabel lbProcessoIdProcesso = new JLabel("ID do Processo");
    private List<String> stringprocessoIdProcesso = new ArrayList<>();
    private JComboBox comboProcessoIdProcesso = new JComboBox();
    private JLabel lbAdvogadoPessoaIdPessoa = new JLabel("ID Advogado");
    private List<String> stringadvogadoPessoaIdPessoa = new ArrayList<>();
    private JComboBox comboAdvogadoPessoaIdPessoa = new JComboBox();
    private JLabel lbTipoEventoIdTipoEvento = new JLabel("ID Tipo do Evento");
    private List<String> stringtipoEventoIdTipoEvento = new ArrayList<>();
    private JComboBox comboTipoEventoIdTipoEvento = new JComboBox();
    private JTextField fdIdProcesso = new JTextField(30);
    private JTextField fdIdAdvogado = new JTextField(30);
    private JTextField fdTipoEvento = new JTextField(30);
    private JTextField fdIdEvento = new JTextField(30);

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
    DAOAdvogado daoAdvogado = new DAOAdvogado();
    DAOProcesso daoProcesso = new DAOProcesso();
    DAOEvento controle = new DAOEvento();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JTextComponent editor = (JTextComponent) comboProcessoIdProcesso.getEditor().getEditorComponent();

    Evento evento = new Evento();

    public GUIEvento(){
        setSize(725, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Eventos");

        painelCentral.setLayout(new GridLayout(7, 2));
        
        painelCentral.add(lbHoraInicio);
        painelCentral.add(fdHoraInicio);
        painelCentral.add(lbHotaTermino);
        painelCentral.add(fdHoraTermino);

        fdHoraInicio.setEnabled(false);
        fdHoraTermino.setEnabled(false);

        painelCentral.add(lbDataEvento);
        painelCentral.add(spinnerdataEvento);
        spinnerdataEvento.setEditor(spinnerEditordataEvento);
        spinnerdataEvento.setEnabled(false);
        
        List<String> combo = new ArrayList<>();
        combo = new DAOProcesso().listInOrderNomeStrings("idProcesso");
        for(int x = 0; x < combo.size(); x++) {
            String[] aux = combo.get(x).split(";");
            stringprocessoIdProcesso.add(aux[0] + "-" + aux[1]);
        }
        comboProcessoIdProcesso = new JComboBox(stringprocessoIdProcesso.toArray());
        painelCentral.add(lbProcessoIdProcesso);
        painelCentral.add(comboProcessoIdProcesso);
        comboProcessoIdProcesso.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboProcessoIdProcesso));
        combo = new DAOAdvogado().listInOrderNomeStrings("idAdvogado");
        for(int x = 0; x < combo.size(); x++) {
            String[] aux = combo.get(x).split(";");
            stringadvogadoPessoaIdPessoa.add(aux[0] + "-" + aux[1]);
        }
        comboAdvogadoPessoaIdPessoa = new JComboBox(stringadvogadoPessoaIdPessoa.toArray());
        painelCentral.add(lbAdvogadoPessoaIdPessoa);
        painelCentral.add(comboAdvogadoPessoaIdPessoa);
        comboAdvogadoPessoaIdPessoa.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboAdvogadoPessoaIdPessoa));
        combo = new DAOProcesso().listInOrderNomeStrings("idProcesso");
        for(int x = 0; x < combo.size(); x++) {
            String[] aux = combo.get(x).split(";");
            stringtipoEventoIdTipoEvento.add(aux[0] + "-" + aux[1]);
        }
        comboTipoEventoIdTipoEvento = new JComboBox(stringtipoEventoIdTipoEvento.toArray());
        painelCentral.add(lbTipoEventoIdTipoEvento);
        painelCentral.add(comboTipoEventoIdTipoEvento);
        comboTipoEventoIdTipoEvento.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboTipoEventoIdTipoEvento));


cp.setBackground(Color.white);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(fdIdAgenda);
        
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
        fdIdAgenda.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbIdAgenda.setFont(new Font("Courier New", Font.BOLD, 17));
        lbDataEvento.setFont(new Font("Courier New", Font.BOLD, 17));
        lbHoraInicio.setFont(new Font("Courier New", Font.BOLD, 17));
        lbHotaTermino.setFont(new Font("Courier New", Font.BOLD, 17));
        lbProcessoIdProcesso.setFont(new Font("Courier New", Font.BOLD, 17));
        lbAdvogadoPessoaIdPessoa.setFont(new Font("Courier New", Font.BOLD, 17));
        lbTipoEventoIdTipoEvento.setFont(new Font("Courier New", Font.BOLD, 17));
        fdIdAgenda.setFont(new Font("Courier New", Font.PLAIN, 17));
        spinnerdataEvento.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdHoraInicio.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdHoraTermino.setFont(new Font("Courier New", Font.PLAIN, 17));
        comboProcessoIdProcesso.setFont(new Font("Courier New", Font.PLAIN, 17));
        comboAdvogadoPessoaIdPessoa.setFont(new Font("Courier New", Font.PLAIN, 17));
        comboTipoEventoIdTipoEvento.setFont(new Font("Courier New", Font.PLAIN, 17));
        labelAviso.setFont(new Font("Courier New", Font.BOLD, 20));
        btInserir.setVisible(false);
        btAtualizar.setVisible(false);
        btRemover.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        painelSul.add(labelAviso);
        
        comboProcessoIdProcesso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    fdIdProcesso.setText(String.valueOf(comboProcessoIdProcesso.getSelectedItem()).split("-")[0]);
                    
                }

            }

        });
        
        comboAdvogadoPessoaIdPessoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    fdIdAdvogado.setText(String.valueOf(comboAdvogadoPessoaIdPessoa.getSelectedItem()).split("-")[0]);
                    
                }

            }

        });
        
        comboTipoEventoIdTipoEvento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    fdTipoEvento.setText(String.valueOf(comboTipoEventoIdTipoEvento.getSelectedItem()).split("-")[0]);
                    
                }

            }

        });

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    evento = new Evento();
                    int idAgenda = Integer.valueOf(fdIdAgenda.getText());
                    evento.setIdAgenda(Integer.valueOf(fdIdAgenda.getText()));
                    evento = controle.obter(evento.getIdAgenda());
                    labelAviso.setBackground(Color.green);
                    if (evento != null) {
                        fdIdAgenda.setText(evento.getIdAgenda() + "");
                        spinnerdataEvento.setValue(evento.getDataEvento());
                        fdHoraInicio.setText(evento.getHoraInicio() + "");
                        fdHoraTermino.setText(evento.getHoraTermino() + "");
                        comboProcessoIdProcesso.setSelectedItem(evento.getProcessoIdProcesso().toString());
                        comboAdvogadoPessoaIdPessoa.setSelectedItem(evento.getAdvogadoPessoaIdPessoa().toString());
                        comboTipoEventoIdTipoEvento.setSelectedItem(evento.getTipoEventoIdTipoEvento().toString());
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdHoraInicio.setEnabled(false);
                        fdHoraInicio.setText(null);
                        fdHoraTermino.setEnabled(false);
                        fdHoraTermino.setText(null);
                        spinnerdataEvento.setEnabled(false);
                        spinnerdataEvento.setValue(new Date());
                        comboProcessoIdProcesso.setEnabled(false);
                        comboProcessoIdProcesso.setSelectedIndex(0);
                        comboAdvogadoPessoaIdPessoa.setEnabled(false);
                        comboAdvogadoPessoaIdPessoa.setSelectedIndex(0);
                        comboTipoEventoIdTipoEvento.setEnabled(false);
                        comboTipoEventoIdTipoEvento.setSelectedIndex(0);
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
                fdIdAgenda.setEnabled(false);
                spinnerdataEvento.setEnabled(true);
                fdHoraInicio.setEnabled(true);
                fdHoraTermino.setEnabled(true);
                comboProcessoIdProcesso.setEnabled(true);
                comboAdvogadoPessoaIdPessoa.setEnabled(true);
                comboTipoEventoIdTipoEvento.setEnabled(true);
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
                    evento = new Evento();
                        evento.setIdAgenda(Integer.valueOf(fdIdAgenda.getText()));
                        evento.setDataEvento((Date) spinnerdataEvento.getValue());
                        evento.setHoraInicio(Time.valueOf(fdHoraInicio.getText()));
                        evento.setHoraTermino(Time.valueOf(fdHoraTermino.getText()));
                        Integer idProcesso = Integer.valueOf(String.valueOf(comboProcessoIdProcesso.getSelectedItem()).split("-")[0]);
                        evento.setProcessoIdProcesso(idProcesso);
                        Integer idAdvogado = Integer.valueOf(String.valueOf(comboAdvogadoPessoaIdPessoa.getSelectedItem()).split("-")[0]);
                        evento.setAdvogadoPessoaIdPessoa(idAdvogado);
                        Integer idTipoEvento = Integer.valueOf(String.valueOf(comboTipoEventoIdTipoEvento.getSelectedItem()).split("-")[0]);
                        evento.setTipoEventoIdTipoEvento(idTipoEvento);
                        controle.inserir(evento);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdIdAgenda.setEnabled(true);
                        fdIdAgenda.requestFocus();
                        spinnerdataEvento.setEnabled(false);
                        fdHoraInicio.setEnabled(false);
                        fdHoraTermino.setEnabled(false);
                        comboProcessoIdProcesso.setEnabled(false);
                        comboAdvogadoPessoaIdPessoa.setEnabled(false);
                        comboTipoEventoIdTipoEvento.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        evento = new Evento();
                        evento.setIdAgenda(Integer.valueOf(fdIdAgenda.getText()));
                        evento.setDataEvento((Date) spinnerdataEvento.getValue());
                        evento.setHoraInicio(Time.valueOf(fdHoraInicio.getText()));
                        evento.setHoraTermino(Time.valueOf(fdHoraTermino.getText()));
                        Integer idProcesso = Integer.valueOf(String.valueOf(comboProcessoIdProcesso.getSelectedItem()).split("-")[0]);
                        evento.setProcessoIdProcesso(idProcesso);
                        Integer idAdvogado = Integer.valueOf(String.valueOf(comboAdvogadoPessoaIdPessoa.getSelectedItem()).split("-")[0]);
                        evento.setAdvogadoPessoaIdPessoa(idAdvogado);
                        Integer idTipoEvento = Integer.valueOf(String.valueOf(comboTipoEventoIdTipoEvento.getSelectedItem()).split("-")[0]);
                        evento.setTipoEventoIdTipoEvento(idTipoEvento);
                        controle.atualizar(evento);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdIdAgenda.setEnabled(true);
                        fdIdAgenda.requestFocus();
                        spinnerdataEvento.setEnabled(false);
                        fdHoraInicio.setEnabled(false);
                        fdHoraTermino.setEnabled(false);
                        comboProcessoIdProcesso.setEnabled(false);
                        comboAdvogadoPessoaIdPessoa.setEnabled(false);
                        comboTipoEventoIdTipoEvento.setEnabled(false);
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
                fdIdAgenda.setEnabled(false);
                fdIdAgenda.setText("");
                spinnerdataEvento.setEnabled(false);
                spinnerdataEvento.setValue(new Date());
                fdHoraInicio.setEnabled(false);
                fdHoraInicio.setText("");
                fdHoraTermino.setEnabled(false);
                fdHoraTermino.setText("");
                comboProcessoIdProcesso.setEnabled(false);
                comboProcessoIdProcesso.setSelectedIndex(0);
                comboAdvogadoPessoaIdPessoa.setEnabled(false);
                comboAdvogadoPessoaIdPessoa.setSelectedIndex(0);
                comboTipoEventoIdTipoEvento.setEnabled(false);
                comboTipoEventoIdTipoEvento.setSelectedIndex(0);
                fdIdAgenda.setEnabled(true);
                fdIdAgenda.requestFocus();
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
                spinnerdataEvento.setEnabled(true);
                fdHoraInicio.setEnabled(true);
                fdHoraTermino.setEnabled(true);
                comboProcessoIdProcesso.setEnabled(true);
                comboAdvogadoPessoaIdPessoa.setEnabled(true);
                comboTipoEventoIdTipoEvento.setEnabled(true);
                fdHoraInicio.requestFocus();
                fdIdAgenda.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + evento.getIdAgenda() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(evento);
                    labelAviso.setText("Removido!");
                    fdIdAgenda.setText("");
                    spinnerdataEvento.setEnabled(false);
                    spinnerdataEvento.setValue(new Date());
                    fdHoraInicio.setText("");
                    fdHoraInicio.setEnabled(false);
                    fdHoraTermino.setText("");
                    fdHoraTermino.setEnabled(false);
                comboProcessoIdProcesso.setEnabled(false);
                comboProcessoIdProcesso.setSelectedIndex(0);
                comboAdvogadoPessoaIdPessoa.setEnabled(false);
                comboAdvogadoPessoaIdPessoa.setSelectedIndex(0);
                comboTipoEventoIdTipoEvento.setEnabled(false);
                comboTipoEventoIdTipoEvento.setSelectedIndex(0);
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
                new EventoGUIListagem(controle.listInOrderNomeStrings("tanto faz"), cp);
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
