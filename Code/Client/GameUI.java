/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.LineMetrics;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris Mazur
 */
/*class WordStart {

 public WordStart(int i, int j, boolean is_across, int length) {
 this.i = i;
 this.j = j;
 this.is_across = is_across;
 this.length = length;
 }
 int i;
 int j;
 boolean is_across; // if false, this is a down start
 int length;
 }*/
public class GameUI extends javax.swing.JFrame {

    public static void paintComponent(Graphics oldg) {
        Graphics2D g = (Graphics2D) oldg;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 1000);

        if (word_starts == null) {
            return;
        }

        g.setColor(Color.black);
        graphics(g);
        drawOutline(g);
    }

    public GameUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        enterIPAddressMenu = new javax.swing.JFrame();
        ipAddrTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ipAddrOKButton = new javax.swing.JButton();
        connectionNotifyWaiting = new javax.swing.JLabel();
        currentViewPanel = new javax.swing.JPanel();
        lobbyPanel = new javax.swing.JPanel();
        radioJoinLobby1 = new javax.swing.JRadioButton();
        radioJoinLobby2 = new javax.swing.JRadioButton();
        radioJoinLobby3 = new javax.swing.JRadioButton();
        radioJoinLobby4 = new javax.swing.JRadioButton();
        radioJoinLobby5 = new javax.swing.JRadioButton();
        radioJoinLobby6 = new javax.swing.JRadioButton();
        radioJoinLobby7 = new javax.swing.JRadioButton();
        radioJoinLobby8 = new javax.swing.JRadioButton();
        joinSelectedLobbyButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        playersInLobby1 = new javax.swing.JLabel();
        playersInLobby2 = new javax.swing.JLabel();
        playersInLobby3 = new javax.swing.JLabel();
        playersInLobby4 = new javax.swing.JLabel();
        playersInLobby5 = new javax.swing.JLabel();
        playersInLobby6 = new javax.swing.JLabel();
        playersInLobby7 = new javax.swing.JLabel();
        playersInLobby8 = new javax.swing.JLabel();
        gameViewPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenuOption = new javax.swing.JMenu();
        fileNewGame = new javax.swing.JMenuItem();
        fileExit = new javax.swing.JMenuItem();

        enterIPAddressMenu.setTitle("Enter Server IP Address");
        enterIPAddressMenu.setAlwaysOnTop(true);
        enterIPAddressMenu.setMinimumSize(new java.awt.Dimension(300, 150));
        enterIPAddressMenu.setName("enterIPAddressMenuFrame"); // NOI18N
        enterIPAddressMenu.setPreferredSize(new java.awt.Dimension(300, 150));

        ipAddrTextField.setText("127.0.0.1");
        ipAddrTextField.setName(""); // NOI18N

        jLabel2.setText("IP Address: ");

        ipAddrOKButton.setText("OK");
        ipAddrOKButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ipAddrOKButtonMouseClicked(evt);
            }
        });
        ipAddrOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipAddrOKButtonActionPerformed(evt);
            }
        });

        connectionNotifyWaiting.setText(" ");
        connectionNotifyWaiting.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout enterIPAddressMenuLayout = new javax.swing.GroupLayout(enterIPAddressMenu.getContentPane());
        enterIPAddressMenu.getContentPane().setLayout(enterIPAddressMenuLayout);
        enterIPAddressMenuLayout.setHorizontalGroup(
            enterIPAddressMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterIPAddressMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(enterIPAddressMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(enterIPAddressMenuLayout.createSequentialGroup()
                        .addComponent(ipAddrTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ipAddrOKButton))
                    .addGroup(enterIPAddressMenuLayout.createSequentialGroup()
                        .addComponent(connectionNotifyWaiting, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 89, Short.MAX_VALUE)))
                .addContainerGap())
        );
        enterIPAddressMenuLayout.setVerticalGroup(
            enterIPAddressMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterIPAddressMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(connectionNotifyWaiting, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(enterIPAddressMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enterIPAddressMenuLayout.createSequentialGroup()
                        .addGroup(enterIPAddressMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ipAddrTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enterIPAddressMenuLayout.createSequentialGroup()
                        .addComponent(ipAddrOKButton)
                        .addContainerGap())))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        currentViewPanel.setBackground(new java.awt.Color(200, 200, 200));
        currentViewPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                currentViewPanelKeyTyped(evt);
            }
        });

        buttonGroup1.add(radioJoinLobby1);
        radioJoinLobby1.setText("Join Lobby 1");
        radioJoinLobby1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        radioJoinLobby1.setRequestFocusEnabled(false);
        radioJoinLobby1.setRolloverEnabled(false);

        buttonGroup1.add(radioJoinLobby2);
        radioJoinLobby2.setText("Join Lobby 2");

        buttonGroup1.add(radioJoinLobby3);
        radioJoinLobby3.setText("Join Lobby 3");

        buttonGroup1.add(radioJoinLobby4);
        radioJoinLobby4.setText("Join Lobby 4");

        buttonGroup1.add(radioJoinLobby5);
        radioJoinLobby5.setText("Join Lobby 5");

        buttonGroup1.add(radioJoinLobby6);
        radioJoinLobby6.setText("Join Lobby 6");

        buttonGroup1.add(radioJoinLobby7);
        radioJoinLobby7.setText("Join Lobby 7");

        buttonGroup1.add(radioJoinLobby8);
        radioJoinLobby8.setText("Join Lobby 8");

        joinSelectedLobbyButton.setText("Join Selected Lobby");
        joinSelectedLobbyButton.setToolTipText("");
        joinSelectedLobbyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinSelectedLobbyButtonActionPerformed(evt);
            }
        });

        jLabel1.setText(" Lobbies                      Players");

        playersInLobby1.setText("0/8");

        playersInLobby2.setText("0/8");

        playersInLobby3.setText("0/8");

        playersInLobby4.setText("0/8");

        playersInLobby5.setText("0/8");

        playersInLobby6.setText("0/8");

        playersInLobby7.setText("0/8");

        playersInLobby8.setText("0/8");

        javax.swing.GroupLayout lobbyPanelLayout = new javax.swing.GroupLayout(lobbyPanel);
        lobbyPanel.setLayout(lobbyPanelLayout);
        lobbyPanelLayout.setHorizontalGroup(
            lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lobbyPanelLayout.createSequentialGroup()
                .addGroup(lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lobbyPanelLayout.createSequentialGroup()
                        .addGap(0, 55, Short.MAX_VALUE)
                        .addComponent(joinSelectedLobbyButton))
                    .addGroup(lobbyPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lobbyPanelLayout.createSequentialGroup()
                                .addComponent(radioJoinLobby7)
                                .addGap(27, 27, 27)
                                .addComponent(playersInLobby7))
                            .addGroup(lobbyPanelLayout.createSequentialGroup()
                                .addComponent(radioJoinLobby8)
                                .addGap(27, 27, 27)
                                .addComponent(playersInLobby8))
                            .addGroup(lobbyPanelLayout.createSequentialGroup()
                                .addComponent(radioJoinLobby5)
                                .addGap(27, 27, 27)
                                .addComponent(playersInLobby5))
                            .addGroup(lobbyPanelLayout.createSequentialGroup()
                                .addComponent(radioJoinLobby6)
                                .addGap(27, 27, 27)
                                .addComponent(playersInLobby6))
                            .addGroup(lobbyPanelLayout.createSequentialGroup()
                                .addComponent(radioJoinLobby3)
                                .addGap(27, 27, 27)
                                .addComponent(playersInLobby3))
                            .addGroup(lobbyPanelLayout.createSequentialGroup()
                                .addComponent(radioJoinLobby4)
                                .addGap(27, 27, 27)
                                .addComponent(playersInLobby4))
                            .addGroup(lobbyPanelLayout.createSequentialGroup()
                                .addGroup(lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioJoinLobby2)
                                    .addComponent(radioJoinLobby1))
                                .addGap(27, 27, 27)
                                .addGroup(lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(playersInLobby1)
                                    .addComponent(playersInLobby2)))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        lobbyPanelLayout.setVerticalGroup(
            lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lobbyPanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioJoinLobby1)
                    .addComponent(playersInLobby1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioJoinLobby2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playersInLobby2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioJoinLobby3)
                    .addComponent(playersInLobby3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioJoinLobby4)
                    .addComponent(playersInLobby4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioJoinLobby5)
                    .addComponent(playersInLobby5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioJoinLobby6)
                    .addComponent(playersInLobby6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioJoinLobby7)
                    .addComponent(playersInLobby7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lobbyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioJoinLobby8)
                    .addComponent(playersInLobby8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                .addComponent(joinSelectedLobbyButton)
                .addContainerGap())
        );

        gameViewPanel.setFocusCycleRoot(true);
        gameViewPanel.setNextFocusableComponent(gameViewPanel);
        gameViewPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gameViewPanelMouseClicked(evt);
            }
        });
        gameViewPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                gameViewPanelKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout gameViewPanelLayout = new javax.swing.GroupLayout(gameViewPanel);
        gameViewPanel.setLayout(gameViewPanelLayout);
        gameViewPanelLayout.setHorizontalGroup(
            gameViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        gameViewPanelLayout.setVerticalGroup(
            gameViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout currentViewPanelLayout = new javax.swing.GroupLayout(currentViewPanel);
        currentViewPanel.setLayout(currentViewPanelLayout);
        currentViewPanelLayout.setHorizontalGroup(
            currentViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentViewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gameViewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lobbyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        currentViewPanelLayout.setVerticalGroup(
            currentViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentViewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(currentViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gameViewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lobbyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fileMenuOption.setText("File");

        fileNewGame.setText("New Game");
        fileNewGame.setActionCommand("fileNewGame");
        fileNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileNewGameActionPerformed(evt);
            }
        });
        fileMenuOption.add(fileNewGame);

        fileExit.setText("Exit");
        fileExit.setActionCommand("fileExitGame");
        fileMenuOption.add(fileExit);

        jMenuBar1.add(fileMenuOption);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(currentViewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentViewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileNewGameActionPerformed
        // TODO add your handling code here:
        dispose();
        client = new Client();
        main(new String[]{"New Game"});
    }//GEN-LAST:event_fileNewGameActionPerformed

    private void joinSelectedLobbyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinSelectedLobbyButtonActionPerformed
        // TODO add your handling code here:
        sendLobbySelection();
    }//GEN-LAST:event_joinSelectedLobbyButtonActionPerformed

    private void gameViewPanelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gameViewPanelKeyTyped
        // TODO add your handling code here:
        System.out.println("You pressed " + evt.getKeyChar());
        lastKeyPressed = evt.getKeyChar();
        client.charVal = lastKeyPressed;
        paintComponent(gameViewPanel.getGraphics());
    }//GEN-LAST:event_gameViewPanelKeyTyped

    private void gameViewPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameViewPanelMouseClicked
        // TODO add your handling code here:
        System.out.println("Mouse clicked at " + evt.getX() + ", " + evt.getY());

        int squareSize = (int) (7.0 / 11 * xss / word_starts[0].length);
        int xStart = xss * 2 / 11;
        int yStart = yss * 2 / 11;
        int i = (evt.getY() - yStart) / squareSize;
        int j = (evt.getX() - xStart) / squareSize;

        if (evt.getY() >= yStart && evt.getX() >= xStart
                && i < word_starts.length && j < word_starts[0].length) {
            System.out.println("Click at row " + i + ", col " + j);
            xHighlight = xCurrSquare = i;
            yHighlight = yCurrSquare = j;
        }

        gameViewPanel.requestFocus();
        paintComponent(gameViewPanel.getGraphics());
        client.xCoord = j;
        client.yCoord = j;
    }//GEN-LAST:event_gameViewPanelMouseClicked

    private void currentViewPanelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_currentViewPanelKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_currentViewPanelKeyTyped

    private void ipAddrOKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipAddrOKButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipAddrOKButtonActionPerformed

    private void ipAddrOKButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ipAddrOKButtonMouseClicked
        // TODO add your handling code here:
        client._SERVER_ADDR = ipAddrTextField.getText();
        enterIPAddressMenu.setVisible(false);
        initialConnection();


    }//GEN-LAST:event_ipAddrOKButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                client = new Client();
                GameUI g = new GameUI();
                g.setVisible(true);
                g.setLocationRelativeTo(null);
                gameViewPanel.setFocusable(true);
                //init();

                enterIPAddressMenu.setVisible(true);
                enterIPAddressMenu.setLocationRelativeTo(null);

            }
        });
    }

    public static void initialConnection() {
        try {
            client.connect();
            
            client.nIn = new BufferedInputStream(client.server.getInputStream());
            
            client.buffer = new byte[client._BUF_SIZE];
            
            System.out.println("Client attempting to read...");
            Thread.sleep(100);
            client.nIn.read(client.buffer);
            
            System.out.println("Client received lobby info from Connection #" + (int) client.buffer[1]);
            
            setLobbies(client.buffer);

        } catch (IOException ex) {
        } catch (InterruptedException ex) {
            Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setLobbies(byte[] buf) {
        playersInLobby1.setText((int) buf[8] + "/ 8");
        playersInLobby2.setText((int) buf[9] + "/ 8");
        playersInLobby3.setText((int) buf[10] + "/ 8");
        playersInLobby4.setText((int) buf[11] + "/ 8");
        playersInLobby5.setText((int) buf[12] + "/ 8");
        playersInLobby6.setText((int) buf[13] + "/ 8");
        playersInLobby7.setText((int) buf[14] + "/ 8");
        playersInLobby8.setText((int) buf[15] + "/ 8");
    }

    public void sendLobbySelection() {
//state is 10 indicates sending for lobbies
        client.buffer = new byte[client._BUF_SIZE];
        client.buffer[0] = 10;

        switch (buttonGroup1.getSelection().getActionCommand()) {
            case "1":
                client.buffer[1] = 1;
                break;
            case "2":
                client.buffer[1] = 2;
                break;
            case "3":
                client.buffer[1] = 3;
                break;
            case "4":
                client.buffer[1] = 4;
                break;
            case "5":
                client.buffer[1] = 5;
                break;
            case "6":
                client.buffer[1] = 6;
                break;
            case "7":
                client.buffer[1] = 7;
                break;
            case "8":
                client.buffer[1] = 8;
                break;

            default:
                System.out.println("Could not parse lobby selection - ensure a radiobutton is selected!");
                break;
        }
        if (client.buffer[1] != 0) {
            sendPacket();
        }

    }

    public void sendPacket() {
        //Player buffer[0] is 0 for loadinfo. P2 sends 10 with lobby selection. 20 for name and 30 for actions.
        try {
            client.nOut = new BufferedOutputStream(client.server.getOutputStream());
            client.nOut.write(client.buffer);
        } catch (IOException ex) {
            Logger.getLogger(GameUI.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void init() {
        paintComponent(gameViewPanel.getGraphics());
        int cols = 6; //(int) (Math.random() * 5) + 5;
        boolean hasLetter[][] = new boolean[cols][cols];
        mem_pos = new char[hasLetter.length][hasLetter.length];


        for (int i = 0; i < word_starts.length; i++) {
            for (int j = 0; j < word_starts[0].length; j++) {
                if (hasLetter[i][j] == false) {
                    word_starts[i][j] = WALL;
                } else {
                    word_starts[i][j] = BLANK;
                }
            }
        }
        for (int i = 0; i < word_starts.length; i++) {
            for (int j = 0; j < word_starts[0].length; j++) {
                int thing = 0;
                if (hasLetter[i][j] == false) {
                    continue;
                }
                if ((i == 0 || hasLetter[i - 1][j] == false) && // there is a wall above us AND
                        (i < 4 && hasLetter[i + 1][j] == true)) { // no wall below us
                    word_starts[i][j] = DOWN;
                    thing++;
                }
                if ((j == 0 || hasLetter[i][j - 1] == false)
                        && (j < 4 && hasLetter[i][j + 1] == true)) {
                    if (thing == 1) {
                        word_starts[i][j] = BOTH;
                    } else {
                        word_starts[i][j] = ACROSS;
                    }
                }
            }
        }
        fillSpace(mem_board);
    }
    
    public static void insertChar(int i, int j, char k) {
        mem_pos[i][j] = k;
        lastKeyPressed = 0;

    }

    public static void drawChar(int i, int j, Graphics2D g, Color color) {
        char k = mem_pos[i][j];
        int xStart = xss * 2 / 11;
        int yStart = yss * 2 / 11;
        String disThing = String.valueOf(k);
        System.out.println(disThing);
        Font f = new Font("Arial", Font.PLAIN, 18);
        g.setColor(color);
        g.setFont(f);
        g.drawString(disThing, (int) (xStart + j * xss * 7.0 / 11 / word_starts[0].length) + 15, yStart + (int) (i * yss * 7.0 / 11 / word_starts[0].length) + 25);
    }

    public static void fillBox(int i, int j, Graphics2D g, Color color) {
        int xStart = xss * 2 / 11;
        int yStart = yss * 2 / 11;
        g.setColor(color);
        g.fillRect((int) (xStart + j * xss * 7.0 / 11 / word_starts[0].length), yStart + (int) (i * yss * 7.0 / 11 / word_starts[0].length), (int) (7.0 / 11 * xss / word_starts[0].length), (int) (7.0 / 11 * yss / word_starts[0].length));
    }

    public static void drawBox(int i, int j, Graphics2D g, Color color) {
        int xStart = xss * 2 / 11;
        int yStart = yss * 2 / 11;
        g.setColor(color);
        g.drawRect((int) (xStart + j * xss * 7.0 / 11 / word_starts[0].length), yStart + (int) (i * yss * 7.0 / 11 / word_starts[0].length), (int) (7.0 / 11 * xss / word_starts[0].length), (int) (7.0 / 11 * yss / word_starts[0].length));
    }

    private static void graphics(Graphics2D g) {

        Font f = new Font("Arial", Font.PLAIN, 12);
        g.setFont(f);
        int count = 0;
        int xStart = xss * 2 / 11;
        int yStart = yss * 2 / 11;
        int highlighted_word_start = word_starts[xHighlight][yHighlight];
        if (highlighted_word_start != BLANK
                && highlighted_word_start != WALL) {
            if (highlighted_word_start == BOTH) {
                highlightWord(ACROSS, xHighlight, yHighlight, g);
                highlightWord(DOWN, xHighlight, yHighlight, g);
            } else {
                highlightWord(highlighted_word_start, xHighlight, yHighlight, g);
            }
        }
        if (lastKeyPressed != 0) {
            System.out.println("Working...");
            insertChar(xCurrSquare, yCurrSquare, lastKeyPressed);
            paintComponent(gameViewPanel.getGraphics());
        }
        g.setColor(Color.black);
        for (int i = 0; i < word_starts.length; i++) {
            for (int j = 0; j < word_starts[0].length; j++) {
                if (word_starts[i][j] == WALL) {
                    fillBox(i, j, g, Color.black);
                } else if (word_starts[i][j] == BLANK) {
                } else {
                    count++;
                    LineMetrics lm = f.getLineMetrics(".", g.getFontRenderContext());
                    g.drawString(count + ".", (int) (2 + xStart + j * xss * 7.0 / 11 / word_starts[0].length), (int) (2 + lm.getAscent() + yStart + i * yss * 7.0 / 11 / word_starts[0].length));
                }
            }
        }

    }

    public static void highlightWord(int dir, int i, int j, Graphics2D g) {
        System.out.println("highlightWord " + dir + " i " + i + " j " + j);
        boolean is_across = false;
        if (dir == ACROSS) {
            is_across = true;
        }
        if (dir == ACROSS || dir == DOWN) {
            int len;
            for (len = 1; len < word_starts.length; len++) {
                if (is_across) {
                    fillBox(i, j, g, Color.gray);
                    j++;
                } else {
                    fillBox(i, j, g, Color.gray);
                    i++;
                }
                if (i >= word_starts.length || j >= word_starts.length || word_starts[i][j] == WALL) {
                    break;
                }
            }
        }
    }

    private static void drawOutline(Graphics2D g) {
        int xStart = xss * 2 / 11;
        int yStart = yss * 2 / 11;

        for (int i = 0; i <= word_starts[0].length; i++) {
            int x = (int) (xStart + 7.0 / 11 * xss * i / word_starts[0].length);
            g.drawLine(x, yStart, x, (int) (yStart + yss * 7.0 / 11));
        }
        for (int j = 0; j <= word_starts[1].length; j++) {
            int y = (int) (yStart + 7.0 / 11 * yss * j / word_starts[1].length);
            g.drawLine(xStart, y, (int) (xStart + xss * 7.0 / 11), y);
        }
        for (int k = 0; k < word_starts.length; k++) {
            for (int l = 0; l < word_starts.length; l++) {
                drawChar(k, l, g, Color.black);
            }
        }
        drawBox(xCurrSquare, yCurrSquare, g, Color.red);

    }

    static int findWordLength(int[][] word_starts, int i, int j,
            boolean is_across) {
        int len;
        for (len = 1; len < word_starts.length; len++) {
            if (is_across) {
                j++;
            } else {
                i++;
            }
            if (i >= word_starts.length || j >= word_starts.length || word_starts[i][j] == WALL) {
                break;
            }
        }
        return len;
    }

    public static void fillSpace(char[][] tboard) {
        paintComponent(gameViewPanel.getGraphics());
        for (int i = 0; i < word_starts.length; i++) {
            for (int j = 0; j < word_starts.length; j++) {
                if (tboard[i][j] == 0) {
                    word_starts[i][j] = WALL;
                }
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.ButtonGroup buttonGroup1;
    private static javax.swing.JLabel connectionNotifyWaiting;
    private static javax.swing.JPanel currentViewPanel;
    private static javax.swing.JFrame enterIPAddressMenu;
    private javax.swing.JMenuItem fileExit;
    private javax.swing.JMenu fileMenuOption;
    private javax.swing.JMenuItem fileNewGame;
    static javax.swing.JPanel gameViewPanel;
    private static javax.swing.JButton ipAddrOKButton;
    private static javax.swing.JTextField ipAddrTextField;
    private static javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton joinSelectedLobbyButton;
    private javax.swing.JPanel lobbyPanel;
    private static javax.swing.JLabel playersInLobby1;
    private static javax.swing.JLabel playersInLobby2;
    private static javax.swing.JLabel playersInLobby3;
    private static javax.swing.JLabel playersInLobby4;
    private static javax.swing.JLabel playersInLobby5;
    private static javax.swing.JLabel playersInLobby6;
    private static javax.swing.JLabel playersInLobby7;
    private static javax.swing.JLabel playersInLobby8;
    private javax.swing.JRadioButton radioJoinLobby1;
    private javax.swing.JRadioButton radioJoinLobby2;
    private javax.swing.JRadioButton radioJoinLobby3;
    private javax.swing.JRadioButton radioJoinLobby4;
    private javax.swing.JRadioButton radioJoinLobby5;
    private javax.swing.JRadioButton radioJoinLobby6;
    private javax.swing.JRadioButton radioJoinLobby7;
    private javax.swing.JRadioButton radioJoinLobby8;
    // End of variables declaration//GEN-END:variables
//Beginning of Game variables declaration
    static int xHighlight = 0;
    static int yHighlight = 0;
    static int xCurrSquare = 0;
    static int yCurrSquare = 0;
    static char lastKeyPressed = 0;
    static Client client;
    static int xss = 500; //xss = x screen size
    static int yss = 500; //yss = y screen size
    static int word_starts[][] = new int[6][6];
    static char mem_board[][];
    static char mem_pos[][];
    static final int WALL = -1;
    static final int BLANK = 0;
    static final int ACROSS = 1;
    static final int DOWN = 2;
    static final int BOTH = 3;
    char board[][];
}
