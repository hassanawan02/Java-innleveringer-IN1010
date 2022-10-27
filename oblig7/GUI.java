import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.PanelUI;

public class GUI {
    private final int bretthoeyde = 600;
    private final int bredde = 432;
    private final int GRID = 12;
    private final int kpanelhoeyde= 100;
    private final int rnetthoeyde = 432;
    private final int antSkatter = 10;

    private Kontroller kontroller;
    private JFrame vindu;
    private JPanel hoved, panel, rutenett, kontroll, informasjon;
    private JButton avslutt, opp, ned, venstre, hoeyre;
    private JLabel snakelengde;
    private JLabel ruter[][] = new JLabel[GRID][GRID];

    public GUI(Kontroller kontroller){
        this.kontroller = kontroller;

        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            System.exit(9);
        }
        class Avslutt implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                kontroller.avsluttSpill();
            }
        }
        class Opp implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                kontroller.oppdaterBevegelse("opp");
            }
        }
        class Ned implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                kontroller.oppdaterBevegelse("ned");
            }
        }
        class Venstre implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                kontroller.oppdaterBevegelse("venstre");
            }
        }
        class Hoeyre implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                kontroller.oppdaterBevegelse("hoeyre");
            }
        }
        vindu = new JFrame("Slangespill");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hoved = new JPanel();
        hoved.setLayout(new BorderLayout());
        vindu.add(hoved);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(bredde, kpanelhoeyde));
        hoved.add(panel, BorderLayout.NORTH);

        informasjon = new JPanel();
        informasjon.setLayout(new GridBagLayout());
        informasjon.setPreferredSize(new Dimension(bredde / 4, kpanelhoeyde));
        informasjon.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        snakelengde = new JLabel();
        informasjon.add(snakelengde);
        panel.add(informasjon, BorderLayout.WEST);

        kontroll = new JPanel();
        kontroll.setLayout(new BorderLayout());
        kontroll.setPreferredSize(new Dimension(bredde / 2, kpanelhoeyde));
        panel.add(kontroll, BorderLayout.CENTER);

        opp = new JButton("Opp");
        opp.addActionListener(new Opp());

        ned = new JButton("Ned");
        ned.addActionListener(new Ned());

        venstre = new JButton("Venstre");
        venstre.addActionListener(new Venstre());

        hoeyre = new JButton("Hoeyre");
        hoeyre.addActionListener(new Hoeyre());

        kontroll.add(opp, BorderLayout.NORTH);
        kontroll.add(ned, BorderLayout.SOUTH);
        kontroll.add(venstre, BorderLayout.WEST);
        kontroll.add(hoeyre, BorderLayout.EAST);

        avslutt = new JButton("Avslutt");
        avslutt.setLayout(new BorderLayout());
        avslutt.setPreferredSize(new Dimension(bredde / 4, 100));
        avslutt.addActionListener(new Avslutt());
        panel.add(avslutt, BorderLayout.EAST);

        rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(GRID, GRID));
        rutenett.setPreferredSize(new Dimension(bredde, rnetthoeyde));
        rutenett.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        hoved.add(rutenett, BorderLayout.SOUTH);

        for(int rad = 0; rad < GRID; rad++){
            for(int kol = 0; kol < GRID; kol++){
                JLabel rute = new JLabel("", SwingConstants.CENTER);
                rute.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                ruter[rad][kol] = rute;
                rute.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

                rutenett.add(rute);
            }
        }

        vindu.setSize(bredde, bretthoeyde);
        vindu.setVisible(true);





    }
    public void tegnStart(){
        for(int rad = 0; rad < GRID; rad++){
            for(int kol = 0; kol < GRID; kol++){
                ruter[rad][kol].setText("");
            }
        }

        ruter[6][6].setText("0");
        kontroller.forlengSlange(new Slange(6, 6, true));

        for(int i = 0; i < antSkatter; i++){
            int skattRad = 0;
            int skattKol = 0;
            while((skattRad !=6 && skattKol != 6) || ruter[skattRad][skattKol].getText().equals("$")){
                skattRad = Skatt.trekk(0, GRID -1);
                skattKol = Skatt.trekk(0, GRID -1);
                if((skattRad != 6 && skattKol != 6) || ruter[skattRad][skattKol].getText().equals("$")){
                    break;
                }
            }
            ruter[skattRad][skattKol].setText("$");
            Skatt ny = new Skatt(skattRad, skattKol);
            kontroller.leggTilSkatt(i, ny);
        }
    }
    public void tegnIgjen(){
        Koe<Slange> slange = kontroller.hentSlange();
        Skatt[] skattene = kontroller.hentSkatter();

        for(int rad = 0; rad < GRID; rad++){
            for(int kol = 0; kol < GRID; kol++){
                ruter[rad][kol].setText("");
            }
        }
        for(int i = 0; i < skattene.length; i++){
            if(skattene[i] != null){
                ruter[skattene[i].hentRadnr()][skattene[i].hentKolnr()].setText("$");
            }
        }
        for(Slange part : slange){
            int slangeRad = part.hentRadnr();
            int slangeKol = part.hentKolnr();
            if(part.erHode()){
                ruter[slangeRad][slangeKol].setText("O");
            }else{
                ruter[slangeRad][slangeKol].setText("x");
            }
        }

        snakelengde.setText(" " + kontroller.hentHalelengde() + " ");
        
    }

}
