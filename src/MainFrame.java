import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private JButton jbtRun = new JButton("Run");
    private JButton jbtUp = new JButton("Up");
    private JButton jbtDown = new JButton("Down");
    private JButton jbtLeft = new JButton("Left");
    private JButton jbtRight = new JButton("Right");
    private JButton jbtExit = new JButton("Exit");
    private Container cp;
    private JLabel jlb = new JLabel();
    private JLabel jlb2 = new JLabel();
    private JPanel jpnC = new JPanel();
    private JPanel jpnS = new JPanel(new GridLayout(1,6,2,2));
    private ImageIcon icon = new ImageIcon("駱駝.png");
    private Timer tim , tim2;
    private int dirflag = 1;
    private boolean jlb2flag = false;
    private float m = 0.0f;
    private int tarX, tarY;
    int ObjX =0 , ObjY = 0;
    int ObjW =128 , ObjH = 128;
    int newjlbX , newjlbY;
    public MainFrame(){
        this.init();
    }
    public void init() {
        cp = this.getContentPane();
        this.setBounds(100, 100, 1000, 800);
        cp.add(jpnC, BorderLayout.CENTER);
        cp.add(jpnS, BorderLayout.SOUTH);
        jpnC.setLayout(null);
        jpnC.add(jlb);

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jlb.setBounds(ObjX, ObjY, ObjW, ObjH);
        jlb.setIcon(icon);
        jpnS.add(jbtRun);
        jpnS.add(jbtUp);
        jpnS.add(jbtDown);
        jpnS.add(jbtLeft);
        jpnS.add(jbtRight);
        jpnS.add(jbtExit);
        tim2 = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(Math.abs(jlb.getX() - tarX)< 30 && Math.abs(jlb.getY()-tarY)<30){
                    tim2.stop();
                }else {
                   if(jlb.getX() - tarX <0){
                       newjlbX = jlb.getX()+1;
                   }else {
                       newjlbX = jlb.getY()-1;
                   }
                    newjlbY = Math.round(m * (float)(newjlbX - tarX)+ tarY);
                    jlb.setLocation(newjlbX,newjlbY);
                }
            }
        });
        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        jlb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                jlb2flag = true;

            }
        });
        jpnC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(jlb2flag){
                    tarX = mouseEvent.getX();
                    tarY = mouseEvent.getY();
                    m = (float)(tarY - jlb.getY())/(float)(tarX -jlb.getX());
                    tim2.start();
                    jlb2flag = false;
                }
            }
        });
        tim = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                switch (dirflag) {
                    case 1:
                        if (ObjY > 0) {
                            ObjY -= 10;
                        }
                        break;
                    case 2:
                        if (ObjY < 600 - ObjH) {
                            ObjY += 10;
                        }
                        break;
                    case 3:
                        if(ObjX -10 >0){
                            ObjX -= 10;

                        }
                        break;
                    case 4:
                        if(ObjX < 550 -ObjW){
                            ObjX += 10;

                        }
                        break;
                }
                jlb.setLocation(ObjX, ObjY);
            }
        });

        jbtRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tim.start();
            }
        });
        jbtUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dirflag =1;
            }
        });
        jbtDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dirflag =2;
            }
        });
        jbtLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dirflag =3;
            }
        });
        jbtRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dirflag =4;
            }
        });
    }
}