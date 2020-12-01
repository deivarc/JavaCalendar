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
    
    public JCalendar() throws IOException {
        super("Simple Calendar");
        Container cp = getContentPane();
        
        monthName = new JLabel("December");
        //unused Button2
        button2 = new JButton ("A test.");
        
        button2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
            System.exit(0);
        }
        });
        
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();
        JPanel jp5 = new JPanel();
        jp1.setLayout(new BoxLayout(jp1,BoxLayout.PAGE_AXIS));
        //jp2.setLayout(new GridLayout(5,7));
        jp2.setLayout(new FlowLayout(FlowLayout.LEADING,15,20));
        jp4.setLayout(new GridLayout(5,7));

        
        /*
        *    ________________
        *   |____jp1_________|-------------------BorderLayout
        *   |   |        |   |
        *   |jp3|  jp4   |jp5|--- Flow Layout
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
        
        for (int i=0;i<35;i++) {
            final JLabel label = new JLabel("Label",SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(80,80));
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jp4.add(label);
        }
        
        ImageIcon imageIcon = new ImageIcon("src/img/arrow2.png"); // load the image to a imageIcon
        
        left = new JButton(imageIcon);
        left.setBorderPainted(false);
        left.setContentAreaFilled(false);
        left.setFocusPainted(false);
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
        jp5.add(right);

        setResizable(false);
        cp.add(jp1,BorderLayout.PAGE_START);
        cp.add(jp2,BorderLayout.CENTER);    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
            
    public static void main (String[] args) throws IOException {
        new JCalendar().setVisible(true);
    }

}
