/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.*;  
import java.awt.event.*; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Black Beauty
 */
public class JCalendar extends JFrame{
    JLabel monthName;
    JButton button2,left,right;
    JPanel jp1,jp2,jp3,jp4,jp5;
    
    public JCalendar() throws IOException {
        super("Simple Calendar");
        Container cp = getContentPane();
        
        //initialise the data retrieving class
        CalendarCB test = new CalendarCB();
        String calHead=monthToString(test.month)+" "+String.valueOf(test.year);
        
        monthName = new JLabel(calHead);
        //unused Button2
        button2 = new JButton ("A test.");
        
        button2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
            System.exit(0);
        }
        });
        
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        
        jp1.setLayout(new BoxLayout(jp1,BoxLayout.PAGE_AXIS));
        //jp2.setLayout(new GridLayout(5,7));
        jp2.setLayout(new FlowLayout(FlowLayout.LEADING,15,20));
        jp4.setLayout(new GridLayout(7,7));

        
        /*
        *    ________________
        *   |____jp1_________| ------------------BorderLayout(Contentpane:cp)
        *   |   |        |   |
        *   |jp3|  jp4   |jp5|--- Flow Layout(jp2)
        *   |   |        |   |
        *   |_  |________|_  |
        */
        monthName.setAlignmentX(Component.CENTER_ALIGNMENT);
        monthName.setFont(new Font("SansSerif", Font.PLAIN, 32));
        
        jp1.add(Box.createRigidArea(new Dimension(0,10)));
        jp1.add(monthName);
        
        jp2.add(jp3);
        jp2.add(jp4);
        jp2.add(jp5);       
        
        //Add into gridlayout jp4
        //-----------------------------
        
        
        for (int i=0;i<49;i++) {         
            final JLabel label;
            if (i<7) { // do daysAL
                ArrayList <String> daysAL = test.getdaysAL();
                label = new JLabel(daysAL.get(i),SwingConstants.CENTER);
                label.setPreferredSize(new Dimension(80,80));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jp4.add(label);
            }  else { // do calData
                ArrayList <String> calData = test.getcalData(); 
                label = new JLabel(calData.get(i-7),SwingConstants.CENTER);
                if (calData.get(i-7).equals(String.valueOf(test.day))) {
                    label.setOpaque(true);
                    label.setBackground(new Color(0,120,215));
                    label.setForeground(new Color(255,255,255));
                }
                label.setPreferredSize(new Dimension(80,80));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jp4.add(label);
            }
        }
        //------   
        //------
        ImageIcon imageIcon = new ImageIcon("src/img/arrow2.png"); // load the image to a imageIcon
        
        left = new JButton(imageIcon);
        left.setBorderPainted(false);
        left.setContentAreaFilled(false);
        left.setFocusPainted(false);
        
        //actionlistener left
        left.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (test.month==0) {
                test.month=11;
                test.year--;
            } else {
                test.month--;
            }
            test.calcal();
            displayData(test);
            /*  System.out.println(test.month);
                System.out.println(test.year);
                System.out.println(test.daysinMonth);
                System.out.println(test.blockdays);
            */
        }
        });
        
        jp3.add(left);
        
        //How to flip an image
           /* BufferedImage image = ImageIO.read(new File("src/img/arrow2.png"));
            BufferedImage flip = new BufferedImage (image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
            for (int y=0; y < image.getHeight();y++) {
                for (int x=0; x<image.getWidth();x++) {
                    flip.setRGB((image.getWidth()-1)-x,y,image.getRGB(x,y));
                    ImageIO.write(flip, "png", new File("src/img/arrow3.png"));
                }
            }       
                    
            //hflip(image);
             */  
           
        imageIcon = new ImageIcon("src/img/arrow3.png");
        right = new JButton(imageIcon);
        right.setBorderPainted(false);
        right.setContentAreaFilled(false);
        right.setFocusPainted(false);
        
        right.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (test.month==11) {
                test.month=0;
                test.year++;
            } else {
            test.month++;}
            test.calcal();
            displayData(test);
            /*  System.out.println(test.month);
                System.out.println(test.year);
                System.out.println(test.daysinMonth);
                System.out.println(test.blockdays);
            */
        }
        });
        jp5.add(right);

        cp.setForeground(Color.RED);
        setResizable(false);
        cp.add(jp1,BorderLayout.PAGE_START);
        cp.add(jp2,BorderLayout.CENTER);    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    private static String monthToString(int month) {
        String answer="";
        switch (month) {
            case 0:
                answer="January";
                break;
            case 1:
                answer="February";
                break;
            case 2:
                answer="March";
                break;
            case 3:
                answer="April";
                break;
            case 4:
                answer="May";
                break;
            case 5:
                answer="June";
                break;
            case 6:
                answer="July";
                break;
            case 7:
                answer="August";
                break;
            case 8:
                answer="September";
                break;
            case 9:
                answer="October";
                break;
            case 10:
                answer="November";
                break;
            case 11:
                answer="December";
                break;
            default:
                answer= "Unknown";            
        }       
        return answer;
    }
    
    private void displayData(CalendarCB test) { //repopulate method
        jp4.removeAll();
        jp1.removeAll();
        String calHead=monthToString(test.month)+" "+String.valueOf(test.year);
        monthName = new JLabel(calHead);
        monthName.setAlignmentX(Component.CENTER_ALIGNMENT);
        monthName.setFont(new Font("SansSerif", Font.PLAIN, 32)); 
        jp1.add(Box.createRigidArea(new Dimension(0,10)));
        jp1.add(monthName);
        
         for (int i=0;i<49;i++) {         
            final JLabel label;
            if (i<7) { // do daysAL
                ArrayList <String> daysAL = test.getdaysAL();
                label = new JLabel(daysAL.get(i),SwingConstants.CENTER);
                label.setPreferredSize(new Dimension(80,80));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jp4.add(label);
            }  else { // do calData
                ArrayList <String> calData = test.getcalData(); 
                label = new JLabel(calData.get(i-7),SwingConstants.CENTER);
                if (calData.get(i-7).equals(String.valueOf(test.day)) && 
                        test.todayShown==true) {
                    label.setOpaque(true);
                    label.setBackground(new Color(0,120,215));
                    label.setForeground(new Color(255,255,255));
                }
                label.setPreferredSize(new Dimension(80,80));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jp4.add(label);
            }
        }
         jp4.revalidate();
         jp4.repaint();
         jp1.repaint();
    }
    
    public static void main (String[] args) throws IOException {
        new JCalendar().setVisible(true);
    }

}
