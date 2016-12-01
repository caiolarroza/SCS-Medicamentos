/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author caiol
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    
    
    
    public FrmPrincipal() {
        initComponents();
        //janela iniciar centralizada
        setLocationRelativeTo(null);
        //janela iniciar maximizada
        setExtendedState(MAXIMIZED_BOTH); 
        
        /*SOFTWARE FEITO SOB A PREMISSA DE POG(PROGRAMAÇÃO ORIENTADA A
        GAMBIARRA), USANDO COMO BASE UMA METODOLOGIA AMPLAMENTE CONHECIDA E
        UTILIZADA AO REDOR DO MUNDO, A GO HORSE.
        */
        
            
    }
    
    public FrmPrincipal(String tipoUsuario){
        this();
        liberarBotoes(tipoUsuario);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dtpTela = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        btnAbrirCaixa = new javax.swing.JButton();
        btnFecharCaixa = new javax.swing.JButton();
        btnCliente = new javax.swing.JButton();
        btnVenda = new javax.swing.JButton();
        btnMedicamento = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnFornecedor = new javax.swing.JButton();
        btnUsuario = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnVenEfetu = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itmCliente = new javax.swing.JMenuItem();
        itmMedicamento = new javax.swing.JMenuItem();
        itmFornecedor = new javax.swing.JMenuItem();
        itmUsuario = new javax.swing.JMenuItem();
        itmVenEfetu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itmAbrirCaixa = new javax.swing.JMenuItem();
        itmFecharCaixa = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        itmVenda = new javax.swing.JMenuItem();
        itmSobre = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(tamanhoTela());
        setSize(getSize());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        dtpTela.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout dtpTelaLayout = new javax.swing.GroupLayout(dtpTela);
        dtpTela.setLayout(dtpTelaLayout);
        dtpTelaLayout.setHorizontalGroup(
            dtpTelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 909, Short.MAX_VALUE)
        );
        dtpTelaLayout.setVerticalGroup(
            dtpTelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.setMaximumSize(new java.awt.Dimension(262, 608));
        jPanel1.setMinimumSize(new java.awt.Dimension(262, 608));
        jPanel1.setPreferredSize(new java.awt.Dimension(262, 608));

        btnAbrirCaixa.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        btnAbrirCaixa.setText("ABRIR CAIXA");
        btnAbrirCaixa.setPreferredSize(new java.awt.Dimension(151, 31));
        btnAbrirCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCaixaActionPerformed(evt);
            }
        });

        btnFecharCaixa.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        btnFecharCaixa.setText("FECHAR CAIXA");
        btnFecharCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharCaixaActionPerformed(evt);
            }
        });

        btnCliente.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        btnCliente.setText("CLIENTE");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        btnVenda.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        btnVenda.setText("VENDA");
        btnVenda.setMaximumSize(new java.awt.Dimension(151, 31));
        btnVenda.setPreferredSize(new java.awt.Dimension(151, 31));
        btnVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendaActionPerformed(evt);
            }
        });

        btnMedicamento.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        btnMedicamento.setText("MEDICAMENTO");
        btnMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicamentoActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Carrinho.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cliente.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Remedio.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/CadeadoAberto.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/CadeadoTrancado.png"))); // NOI18N

        btnFornecedor.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        btnFornecedor.setText("FORNECEDOR");
        btnFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFornecedorActionPerformed(evt);
            }
        });

        btnUsuario.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        btnUsuario.setText("USUARIO");
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fornecedor.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Usuario.png"))); // NOI18N

        btnVenEfetu.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        btnVenEfetu.setText("VENDAS EFETUADAS");
        btnVenEfetu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenEfetuActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Carrinho.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(btnVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(btnCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(btnFecharCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(btnAbrirCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel9))
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnVenEfetu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnMedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel7)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(44, 44, 44))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel7)
                .addGap(96, 96, 96)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbrirCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFecharCaixa))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliente))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(btnVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMedicamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVenEfetu)
                    .addComponent(jLabel9))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jMenu1.setText("Cadastro e Pesquisa");
        jMenu1.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N

        itmCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cliente.png"))); // NOI18N
        itmCliente.setText("Cliente");
        itmCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmClienteActionPerformed(evt);
            }
        });
        jMenu1.add(itmCliente);

        itmMedicamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Remedio.png"))); // NOI18N
        itmMedicamento.setText("Medicamento");
        itmMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmMedicamentoActionPerformed(evt);
            }
        });
        jMenu1.add(itmMedicamento);

        itmFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fornecedor.png"))); // NOI18N
        itmFornecedor.setText("Fornecedor");
        itmFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmFornecedorActionPerformed(evt);
            }
        });
        jMenu1.add(itmFornecedor);

        itmUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Usuario.png"))); // NOI18N
        itmUsuario.setText("Usuario");
        itmUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmUsuarioActionPerformed(evt);
            }
        });
        jMenu1.add(itmUsuario);

        itmVenEfetu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Carrinho.png"))); // NOI18N
        itmVenEfetu.setText("Vendas Efetuadas");
        itmVenEfetu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmVenEfetuActionPerformed(evt);
            }
        });
        jMenu1.add(itmVenEfetu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Caixa");
        jMenu2.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N

        itmAbrirCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/CadeadoAberto.png"))); // NOI18N
        itmAbrirCaixa.setText("Abrir Caixa");
        itmAbrirCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmAbrirCaixaActionPerformed(evt);
            }
        });
        jMenu2.add(itmAbrirCaixa);

        itmFecharCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/CadeadoTrancado.png"))); // NOI18N
        itmFecharCaixa.setText("Fechar Caixa");
        itmFecharCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmFecharCaixaActionPerformed(evt);
            }
        });
        jMenu2.add(itmFecharCaixa);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Venda");
        jMenu3.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N

        itmVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Carrinho.png"))); // NOI18N
        itmVenda.setText("Venda");
        itmVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmVendaActionPerformed(evt);
            }
        });
        jMenu3.add(itmVenda);

        jMenuBar1.add(jMenu3);

        itmSobre.setText("Sobre");
        itmSobre.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        itmSobre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itmSobreMouseClicked(evt);
            }
        });
        jMenuBar1.add(itmSobre);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtpTela))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
            .addComponent(dtpTela, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void liberarBotoes(String tipoUsuario){
        if(tipoUsuario.equals("Atendente")){
            btnUsuario.setEnabled(false);
            itmUsuario.setEnabled(false);
            btnFecharCaixa.setEnabled(false);
            itmFecharCaixa.setEnabled(false);
        }
    }
    
    public Dimension tamanhoTela(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(screenSize.getWidth(), screenSize.getHeight());
        return screenSize;
    }
    private void btnAbrirCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCaixaActionPerformed
        FrmAbrirCaixa fAbrirCaixa = new FrmAbrirCaixa();
        dtpTela.removeAll();
        dtpTela.add(fAbrirCaixa);
        fAbrirCaixa.setVisible(true);
        fAbrirCaixa.setPosicao();
        
    }//GEN-LAST:event_btnAbrirCaixaActionPerformed

    private void itmAbrirCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAbrirCaixaActionPerformed
        FrmAbrirCaixa fAbrirCaixa = new FrmAbrirCaixa();
        dtpTela.removeAll();
        dtpTela.add(fAbrirCaixa);
        fAbrirCaixa.setVisible(true);
        fAbrirCaixa.setPosicao();
        
    }//GEN-LAST:event_itmAbrirCaixaActionPerformed

    private void btnFecharCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharCaixaActionPerformed
        FrmFecharCaixa fFecharCaixa = new FrmFecharCaixa();
        dtpTela.removeAll();
        dtpTela.add(fFecharCaixa);
        fFecharCaixa.setVisible(true);
        fFecharCaixa.setPosicao();
        
    }//GEN-LAST:event_btnFecharCaixaActionPerformed

    private void itmFecharCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmFecharCaixaActionPerformed
        FrmFecharCaixa fFecharCaixa = new FrmFecharCaixa();
        dtpTela.removeAll();
        dtpTela.add(fFecharCaixa);
        fFecharCaixa.setVisible(true);
        fFecharCaixa.setPosicao();
        
    }//GEN-LAST:event_itmFecharCaixaActionPerformed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        FrmCliente fCliente = new FrmCliente();
        dtpTela.removeAll();
        dtpTela.add(fCliente);
        fCliente.setVisible(true);
        fCliente.setPosicao();
        
    }//GEN-LAST:event_btnClienteActionPerformed

    private void itmClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmClienteActionPerformed
        FrmCliente fCliente = new FrmCliente();
        dtpTela.removeAll();
        dtpTela.add(fCliente);
        fCliente.setVisible(true);
        fCliente.setPosicao();
        
    }//GEN-LAST:event_itmClienteActionPerformed

    private void btnVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendaActionPerformed
        FrmVenda fVenda = new FrmVenda();
        dtpTela.removeAll();
        dtpTela.add(fVenda);
        fVenda.setVisible(true);
        fVenda.setPosicao();
        
    }//GEN-LAST:event_btnVendaActionPerformed

    private void itmVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmVendaActionPerformed
        FrmVenda fVenda = new FrmVenda();
        dtpTela.removeAll();
        dtpTela.add(fVenda);
        fVenda.setVisible(true);
        fVenda.setPosicao();
        
    }//GEN-LAST:event_itmVendaActionPerformed

    private void itmMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmMedicamentoActionPerformed
        FrmMedicamento fMedicamento = new FrmMedicamento();
        dtpTela.removeAll();
        dtpTela.add(fMedicamento);
        fMedicamento.setVisible(true);
        fMedicamento.setPosicao();
        
    }//GEN-LAST:event_itmMedicamentoActionPerformed

    private void btnMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicamentoActionPerformed
        FrmMedicamento fMedicamento = new FrmMedicamento();
        dtpTela.removeAll();
        dtpTela.add(fMedicamento);
        fMedicamento.setVisible(true);
        fMedicamento.setPosicao();
        
    }//GEN-LAST:event_btnMedicamentoActionPerformed

    private void btnFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFornecedorActionPerformed
        FrmFornecedor fFornecedor = new FrmFornecedor();
        dtpTela.removeAll();
        dtpTela.add(fFornecedor);
        fFornecedor.setVisible(true);
        fFornecedor.setPosicao();
        
    }//GEN-LAST:event_btnFornecedorActionPerformed

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed
        FrmUsuario fUsuario = new FrmUsuario();
        dtpTela.removeAll();
        dtpTela.add(fUsuario);
        fUsuario.setVisible(true);
        fUsuario.setPosicao();
    }//GEN-LAST:event_btnUsuarioActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        
    }//GEN-LAST:event_formWindowOpened

    private void itmFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmFornecedorActionPerformed
        FrmFornecedor fFornecedor = new FrmFornecedor();
        dtpTela.removeAll();
        dtpTela.add(fFornecedor);
        fFornecedor.setVisible(true);
        fFornecedor.setPosicao();
    }//GEN-LAST:event_itmFornecedorActionPerformed

    private void itmUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmUsuarioActionPerformed
        FrmUsuario fUsuario = new FrmUsuario();
        dtpTela.removeAll();
        dtpTela.add(fUsuario);
        fUsuario.setVisible(true);
        fUsuario.setPosicao();
    }//GEN-LAST:event_itmUsuarioActionPerformed

    private void itmSobreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itmSobreMouseClicked
        FrmSobre fSobre = new FrmSobre();
        fSobre.setVisible(true);
    }//GEN-LAST:event_itmSobreMouseClicked

    private void btnVenEfetuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenEfetuActionPerformed
        FrmVendasEfetuadas fVenEfetu = new FrmVendasEfetuadas();
        dtpTela.removeAll();
        dtpTela.add(fVenEfetu);
        fVenEfetu.setVisible(true);
        fVenEfetu.setPosicao();
    }//GEN-LAST:event_btnVenEfetuActionPerformed

    private void itmVenEfetuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmVenEfetuActionPerformed
        FrmVendasEfetuadas fVenEfetu = new FrmVendasEfetuadas();
        dtpTela.removeAll();
        dtpTela.add(fVenEfetu);
        fVenEfetu.setVisible(true);
        fVenEfetu.setPosicao();
    }//GEN-LAST:event_itmVenEfetuActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //Look and Feel do sistema atual
       try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirCaixa;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnFecharCaixa;
    private javax.swing.JButton btnFornecedor;
    private javax.swing.JButton btnMedicamento;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JButton btnVenEfetu;
    private javax.swing.JButton btnVenda;
    private javax.swing.JDesktopPane dtpTela;
    private javax.swing.JMenuItem itmAbrirCaixa;
    private javax.swing.JMenuItem itmCliente;
    private javax.swing.JMenuItem itmFecharCaixa;
    private javax.swing.JMenuItem itmFornecedor;
    private javax.swing.JMenuItem itmMedicamento;
    private javax.swing.JMenu itmSobre;
    private javax.swing.JMenuItem itmUsuario;
    private javax.swing.JMenuItem itmVenEfetu;
    private javax.swing.JMenuItem itmVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
