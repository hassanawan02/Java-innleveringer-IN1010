import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class testprogram{
    public testprogram(){
    }
    public static void main(String[] args){
        // Denne try and catch burde absolutt alltid vaere med.
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            System.exit(1);
        }
        JFrame vindu = new JFrame("Slangespill");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        vindu.add(panel);
        panel.setLayout(new BorderLayout());
        panel.add(new JButton("Opp") , BorderLayout.NORTH);
        panel.add(new JButton("Venstre") , BorderLayout.WEST);
        panel.add(new JButton("Hoeyre") , BorderLayout.EAST);
        panel.add(new JButton("Ned") , BorderLayout.SOUTH);
        panel.setLayout(new GridLayout(12, 12));

        // String bruker = System.getProperty("user.name");
        // JLabel hilsen = new JLabel("Hallo " + bruker + "!");
        // panel.add(hilsen);
        JButton opp = new JButton("Opp");
        class Opp implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){

            }
        }

        JButton exitknapp = new JButton("Avslutt");
        class Stopp implements ActionListener{
            @Override
            public void actionPerformed (ActionEvent e){
                System.exit(0);
            }
        }
        
        // class Nuller implements ActionListener {
        //     @Override
        //     public void actionPerformed (ActionEvent e) {
        //     int tellerverdi = 0;
        //     antall.setText(" " + tellerverdi);
        //     try {
        //     Thread.sleep(10_000);
        //     } catch (InterruptedException ie) {}
        //     }
        //     }
            
        exitknapp.addActionListener(new Stopp());
        panel.add(exitknapp);

        vindu.pack();
        vindu.setVisible(true);
    }
}
