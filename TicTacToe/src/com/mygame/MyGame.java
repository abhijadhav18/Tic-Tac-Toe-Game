package com.mygame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyGame extends JFrame implements ActionListener { // We are extending JFrame Class to use all the properties of it.

    JLabel heading, clockLabel; // Here we declaring the variable for the heading and clock
    Font font = new Font("", Font.BOLD, 40); // Here we set the font of our label
    JPanel mainPanel;

    JButton[] btns = new JButton[9]; // this array will store our 9 buttons 

    //game Instance variable
    int gameChances[] = {2,2,2,2,2,2,2,2,2};
    int activePlayer = 0;
    
    int wps[][]={
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6}
    };
    
    int winner=2;

    MyGame() {
        System.out.println("Creating Instance");
        setTitle("Tic Tac Toe Game"); // this function helps to set the title
        setSize(850, 850); // this function helps to set Size of window of our game
        ImageIcon icon = new ImageIcon("src/img/icon.png");
        setIconImage(icon.getImage());  // this function helps to set iconImage by passing the  object og ImageIcon and calling the GetImage Function to get the Image

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this function helps to close the program at the exit of JFrame 
        createGUI(); // this function helps to 
        setVisible(true); //this function helps to see that our title and icon is set

    }

    // Now we need new function to set the border layout
    private void createGUI() {
        this.getContentPane().setBackground(Color.decode("#B268B4"));
        this.setLayout(new BorderLayout());

        //north heading - Creating Object For the JLabel Heading
        heading = new JLabel("Tic-Tac-Toe by Abhishek Jadhav"); // Here we labelling the window at the north section part

        heading.setFont(font); // Here we are setting the font 
        heading.setHorizontalAlignment(SwingConstants.CENTER);// To centre the label
        heading.setForeground(Color.WHITE);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);

        this.add(heading, BorderLayout.NORTH); // here we adding the text to the north section

        // Creating Object For JLabel For Clock similarly as we create for our heading of window i.e. LABEL
        clockLabel = new JLabel("Time");
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setForeground(Color.WHITE);

        this.add(clockLabel, BorderLayout.SOUTH);

        // Now we will create thread
        Thread t = new Thread() {
            public void run() {
                try {
                    while (true) { // As Our loop is infinite thus the time will be shown continuously 
                        String datetime = new Date().toLocaleString();

                        clockLabel.setText(datetime); // This gives us current time 

                        Thread.sleep(1000); // Here the thread will sleepfor 1 second and then again it will set the time after 1 second

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        t.start();

        // Now here we will start our panel section
        mainPanel = new JPanel(); // First we create object of mainPanel

        //Now we will set frid layout of mainPanel
        mainPanel.setLayout(new GridLayout(3, 3));

        // This for loop will run for 9 times and 9 times the font will also get set and thus these 9 buttons will get added to the window.
        for (int i = 1; i <= 9; i++) {
            JButton btn = new JButton();
            btn.setBackground(Color.decode("#482653"));
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i - 1] = btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i - 1));
        }

        this.add(mainPanel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton = (JButton) e.getSource();

        String nameStr = currentButton.getName();

        int name = Integer.parseInt(nameStr.trim());
        
        
        if(gameChances[name]==2){
            if(activePlayer==1){
                currentButton.setIcon(new ImageIcon("src/img/x.png"));
                
                gameChances[name]=activePlayer;
                activePlayer=0;
            }
            else{
                currentButton.setIcon(new ImageIcon("src/img/o.png"));
                
                gameChances[name]=activePlayer;
                activePlayer=1;
            }
            // Find The Winner
            
            for(int []temp:wps){
                if((gameChances[temp[0]]==gameChances[temp[1]])&&(gameChances[temp[1]]==gameChances[temp[2]])&&gameChances[temp[2]]!=2){
                 
                    winner=gameChances[temp[0]];
                    JOptionPane.showMessageDialog(null, "Player " + winner + " has won the Game");
                    int i=JOptionPane.showConfirmDialog(this,"Wanna try your luck again 😁?");
                    
                    if(i==0){
                        this.setVisible(false);
                        new MyGame();
                    }
                    else if(i==1){
                        System.exit(34234);
                    }
                    else{
                        
                    }
                    System.out.println(i);
                    break;
                    
                }
            }
            //....
            
        }
        else{
            JOptionPane.showMessageDialog(this,"Position is already occupied");
        }

       
    }

}
