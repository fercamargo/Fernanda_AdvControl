package GUIs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import java.io.File;
import Auxiliar.*;
import DAOs.*;
import Entidades.*;
import javax.persistence.Convert;

public class GUIPessoa extends JFrame {

    public static void main(String[] args) {
        new GUIPessoa();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("ID Pessoa: ");
    private JLabel lbIdPessoa = new JLabel("ID Pessoa");
    private JTextField fdIdPessoa = new JTextField(15);

    private JLabel lbNomePessoa = new JLabel("Nome");
    private JTextField fdNomePessoa = new JTextField(45);

    private JLabel lbCpfPessoa = new JLabel("CPF");
    private JTextField fdCpfPessoa = new JTextField(45);

    private JLabel lbTelefonePessoa = new JLabel("Telefone");
    private JTextField fdTelefonePessoa = new JTextField(45);
    

    private JSpinner spinnerdataCadastro = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditordataCadastro = new JSpinner.DateEditor(spinnerdataCadastro, "dd/MM/yyyy");
    private JLabel lbDataCadastro = new JLabel("Data de Cadastro");
    JLabel lbsexo = new JLabel("Sexo");
    JPanel pnSexo = new JPanel();
    JCheckBox checkFeminino = new JCheckBox("Feminino");
    JCheckBox checkMasculino = new JCheckBox("Masculino");
    JPanel painelImagem = new JPanel(new GridLayout(1, 1));
    Image img;
    Image imagemAux;
    String origem;
    String destino = "src/fotos/";
    String semImagem = "src/fotos/0.png";
    String escolherImagem = "src/fotos/0a.png";
    JLabel labelFoto = new JLabel("");
    Boolean uploadFoto = false;

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

    DAOPessoa controle = new DAOPessoa();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 12);
    Font fonteL = new Font("Courier New", Font.PLAIN, 12);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Pessoa pessoa = new Pessoa();

    public GUIPessoa() {
        setSize(725, 420);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Pessoas");

        try {
            origem = "/fotos/0.png";
            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            labelFoto.setIcon(icone);

        } catch (Exception erro) {
            System.out.println("erro ao carregar a imagem");
        }

        painelCentral.setLayout(new GridLayout(5, 2));
        painelCentral.add(lbNomePessoa);
        painelCentral.add(fdNomePessoa);
        painelCentral.add(lbCpfPessoa);
        painelCentral.add(fdCpfPessoa);
        painelCentral.add(lbTelefonePessoa);
        painelCentral.add(fdTelefonePessoa);

        fdNomePessoa.setEnabled(false);
        fdCpfPessoa.setEnabled(false);
        fdTelefonePessoa.setEnabled(false);

        painelCentral.add(lbDataCadastro);
        painelCentral.add(spinnerdataCadastro);
        spinnerdataCadastro.setEditor(spinnerEditordataCadastro);
        spinnerdataCadastro.setEnabled(false);
        painelCentral.add(lbsexo);
        pnSexo.add(checkFeminino);
        checkFeminino.setEnabled(false);
        pnSexo.add(checkMasculino);
        checkMasculino.setEnabled(false);
        painelCentral.add(pnSexo);
        List<String> combo = new ArrayList<>();

        cp.setBackground(Color.white);
        painelImagem.setBackground(Color.white);
        painelImagem.add(labelFoto);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.WEST);
        cp.add(painelImagem, BorderLayout.EAST);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
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
        pnSexo.setBackground(Color.WHITE);
        checkFeminino.setBackground(Color.WHITE);
        checkMasculino.setBackground(Color.WHITE);

        labelTitulo.setFont(new Font("Courier New", Font.BOLD, 20));
        lbIdPessoa.setFont(new Font("Courier New", Font.BOLD, 17));
        lbNomePessoa.setFont(new Font("Courier New", Font.BOLD, 17));
        lbCpfPessoa.setFont(new Font("Courier New", Font.BOLD, 17));
        lbTelefonePessoa.setFont(new Font("Courier New", Font.BOLD, 17));
        lbDataCadastro.setFont(new Font("Courier New", Font.BOLD, 17));
        lbsexo.setFont(new Font("Courier New", Font.BOLD, 17));
        fdIdPessoa.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdNomePessoa.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdCpfPessoa.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdTelefonePessoa.setFont(new Font("Courier New", Font.PLAIN, 17));
        spinnerdataCadastro.setFont(new Font("Courier New", Font.PLAIN, 17));
        checkFeminino.setFont(new Font("Courier New", Font.PLAIN, 17));
        checkMasculino.setFont(new Font("Courier New", Font.PLAIN, 17));
        labelAviso.setFont(new Font("Courier New", Font.BOLD, 8));
        btInserir.setVisible(false);
        btAtualizar.setVisible(false);
        btRemover.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        painelSul.add(labelAviso);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadFoto = false;
                try {
                    pessoa = new Pessoa();
                    int idPessoa = Integer.valueOf(fdIdPessoa.getText());
                    pessoa.setIdPessoa(Integer.valueOf(fdIdPessoa.getText()));
                    pessoa = controle.obter(pessoa.getIdPessoa());
                    labelAviso.setBackground(Color.green);
                    if (pessoa != null) {
                        fdIdPessoa.setText(pessoa.getIdPessoa() + "");
                        fdNomePessoa.setText(pessoa.getNomePessoa() + "");
                        fdCpfPessoa.setText(pessoa.getCpfPessoa() + "");
                        fdTelefonePessoa.setText(pessoa.getTelefonePessoa() + "");
                        spinnerdataCadastro.setValue(pessoa.getDataCadastro());
//                        if (pessoa.getSexo()){ 
//                            checkFeminino.setSelected(true);
//                            checkMasculino.setSelected(false);
//                        } else {
//                            checkMasculino.setSelected(true);
//                            checkFeminino.setSelected(false);
//                        }
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                        try {
                            String aux = String.valueOf(pessoa.getIdPessoa()).trim();
                            origem = "/fotos/" + aux + ".png";
                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));

                            labelFoto.setIcon(icone);

                        } catch (Exception erro) {
                            origem = "/fotos/0.png";
                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);
                        }
                    } else {
                        try {
                            origem = "/fotos/0.png";
                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);

                        } catch (Exception erro) {
                            System.out.println("erro ao carregar a imagem");
                        }
                        fdNomePessoa.setEnabled(false);
                        fdNomePessoa.setText(null);
                        fdCpfPessoa.setEnabled(false);
                        fdCpfPessoa.setText(null);
                        fdTelefonePessoa.setEnabled(false);
                        fdTelefonePessoa.setText(null);
                        spinnerdataCadastro.setEnabled(false);
                        spinnerdataCadastro.setValue(new Date());
                        labelAviso.setText("Não encontrado!");
                        labelAviso.setBackground(Color.red);
                        btInserir.setVisible(true);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);
                        btListar.setVisible(false);
                        try {
                            origem = "/fotos/0.png";
                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);

                        } catch (Exception erro) {
                            System.out.println("erro ao carregar a imagem");
                        }
                    }
                } catch (Exception erro) {
                    labelAviso.setText("Preencha os campos corretamente! " + erro.getMessage());
                    labelAviso.setBackground(Color.red);
                }
            }
        }
        );

        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = true;
                try {
                    origem = "/fotos/0a.png";
                    ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                    imagemAux = icone.getImage();
                    icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                    labelFoto.setIcon(icone);

                } catch (Exception erro) {
                    System.out.println("erro ao carregar a imagem");
                }
                fdIdPessoa.setEnabled(false);
                fdNomePessoa.setEnabled(true);
                fdCpfPessoa.setEnabled(true);
                fdTelefonePessoa.setEnabled(true);
                spinnerdataCadastro.setEnabled(true);
                uploadFoto = true;
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
                uploadFoto = false;
                if (acao) { //btInserir
                    try {
                        pessoa = new Pessoa();
                        pessoa.setIdPessoa(Integer.valueOf(fdIdPessoa.getText()));
                        pessoa.setNomePessoa(fdNomePessoa.getText());
                        pessoa.setCpfPessoa(fdCpfPessoa.getText());
                        pessoa.setTelefonePessoa(fdTelefonePessoa.getText());
                        pessoa.setDataCadastro((Date) spinnerdataCadastro.getValue());
                        Boolean respSexo = null;
                        if (checkFeminino.isSelected()) {
                            respSexo = true;
                        } else if (checkMasculino.isSelected()) {
                            respSexo = false;
                        } else {
                            int a = 1 / 0;
                        }
                        pessoa.setSexo(respSexo);
                        controle.inserir(pessoa);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdIdPessoa.setEnabled(true);
                        fdIdPessoa.requestFocus();
                        fdNomePessoa.setEnabled(false);
                        fdCpfPessoa.setEnabled(false);
                        fdTelefonePessoa.setEnabled(false);
                        spinnerdataCadastro.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        pessoa = new Pessoa();
                        pessoa.setIdPessoa(Integer.valueOf(fdIdPessoa.getText()));
                        pessoa.setNomePessoa(fdNomePessoa.getText());
                        pessoa.setCpfPessoa(fdCpfPessoa.getText());
                        pessoa.setTelefonePessoa(fdTelefonePessoa.getText());
                        pessoa.setDataCadastro((Date) spinnerdataCadastro.getValue());
                         Boolean respSexo = null;
                        if (checkFeminino.isSelected()) {
                            respSexo = true; 
                        } else {
                            respSexo = false;
                        }
                        pessoa.setSexo(respSexo);
                        controle.atualizar(pessoa);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdIdPessoa.setEnabled(true);
                        fdIdPessoa.requestFocus();
                        fdNomePessoa.setEnabled(false);
                        fdCpfPessoa.setEnabled(false);
                        fdTelefonePessoa.setEnabled(false);
                        spinnerdataCadastro.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                }
                destino = destino + pessoa.getIdPessoa() + ".png";
                CopiaImagem.copiar(origem, destino);
                destino = "src/fotos/";
            }
        }
        );

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    origem = "/fotos/0.png";
                    ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                    imagemAux = icone.getImage();
                    icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                    labelFoto.setIcon(icone);

                } catch (Exception erro) {
                    System.out.println("erro ao carregar a imagem");
                }
                labelAviso.setText("Cancelado!");
                fdIdPessoa.setEnabled(false);
                fdIdPessoa.setText("");
                fdNomePessoa.setEnabled(false);
                fdNomePessoa.setText("");
                fdCpfPessoa.setEnabled(false);
                fdCpfPessoa.setText("");
                fdTelefonePessoa.setEnabled(false);
                fdTelefonePessoa.setText("");
                spinnerdataCadastro.setEnabled(false);
                spinnerdataCadastro.setValue(new Date());
                fdIdPessoa.setEnabled(true);
                fdIdPessoa.requestFocus();
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
                uploadFoto = false;
                acao = false;
                fdNomePessoa.setEnabled(true);
                fdCpfPessoa.setEnabled(true);
                fdTelefonePessoa.setEnabled(true);
                spinnerdataCadastro.setEnabled(true);
                fdNomePessoa.requestFocus();
                fdIdPessoa.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + pessoa.getIdPessoa() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(pessoa);
                    labelAviso.setText("Removido!");
                    fdIdPessoa.setText("");
                    fdNomePessoa.setText("");
                    fdNomePessoa.setEnabled(false);
                    fdCpfPessoa.setText("");
                    fdCpfPessoa.setEnabled(false);
                    fdTelefonePessoa.setText("");
                    fdTelefonePessoa.setEnabled(false);
                    spinnerdataCadastro.setEnabled(false);
                    spinnerdataCadastro.setValue(new Date());
                    String aux = String.valueOf(pessoa.getIdPessoa()).trim();
                    origem = "src/fotos/" + aux + ".png";
                    System.out.println(origem);
                    try {
                        File f = new File(origem);
                        if (f.exists()) {
                            f.delete();
                        }
                    } catch (Exception erro) {
                        System.out.println("Erro");
                    }
                    btListar.setVisible(true);
                } else {
                    labelAviso.setText("Remoção cancelada!");
                    btAtualizar.setVisible(true);
                    btRemover.setVisible(true);
                }
            }
        }
        );

        labelFoto.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (uploadFoto) {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    if (fc.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                        File img = fc.getSelectedFile();
                        origem = fc.getSelectedFile().getAbsolutePath();
                        try {
                            ImageIcon icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                            Image imagemAux;
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);

                        } catch (Exception ex) {
                            System.out.println("Erro: " + ex.getMessage());
                        }
                    }

                }

            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PessoaGUIListagem(controle.listInOrderNomeStrings("tanto faz"), cp);
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
