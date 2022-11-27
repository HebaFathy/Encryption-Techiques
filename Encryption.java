package IE;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Encryption extends JFrame implements ActionListener 

{
 
 int technique=0;
 
 //title
 private JLabel title=new JLabel("   *Encryption Techniques*    ");
 private JPanel title_Panel =new JPanel();
 private Font font=new Font("Marigold",Font.BOLD,22);
 private String plain_string;
 
 //TextArea
 private JTextArea plainText=new JTextArea(10,30);
 
 //labels
 private JLabel plain=new JLabel("Plaint Text ");
 
 //Colors
 Color color1=new Color(43,118,154);
 Color color2=new Color(164,185,207);
 
 //.........................................// 

 //Radio buttons
 private JRadioButton ceasar=new JRadioButton("Ceasar");
 private JRadioButton monalphabitic=new JRadioButton("Monalphabitic");
 private JRadioButton polyalphabitic=new JRadioButton("Polyalphabitic");
 private JRadioButton playfiar=new JRadioButton("Playfiar");
 private JRadioButton hillCipher=new JRadioButton("Hill Cipher");
 private JRadioButton transpostion=new JRadioButton("Transpostion");
 private JRadioButton one=new JRadioButton("One_Time");
 private ButtonGroup radio_group=new ButtonGroup();  
 private JRadioButton gammal=new JRadioButton("El-Gammal");
 private JRadioButton rsa=new JRadioButton("RSA");
 
 //Encryption methods Panel (center)
 private JPanel methods=new JPanel(new GridLayout(9,1,5,5));
 
 //plain panel 
 private JPanel text=new JPanel(new BorderLayout());
 
 //South Panel 
 private JPanel south =new JPanel(); 

 //Buttons 
 private JButton file=new JButton("File"); 
 private JButton next=new JButton("Next");  
 private JButton exit=new JButton("Exit");
 
 
 //-----------------------------------Constructor-------------------------------------------//
 public Encryption()
 {

    Container c=getContentPane();
    c.setLayout(new BorderLayout());
    plain.setForeground(color1);
    
    //title 
    title.setFont(font);
    title.setForeground(Color.white);
    title_Panel.add(title);
    title_Panel.setBackground(color1);
    
    //add radio buttons to a group    
    radio_group.add(ceasar);
    radio_group.add(monalphabitic);
    radio_group.add(polyalphabitic);
    radio_group.add(playfiar);
    radio_group.add(hillCipher);
    radio_group.add(transpostion);
    radio_group.add(one);
    radio_group.add(gammal);
    radio_group.add(rsa);
    
    //Change color of radio buttons 
    ceasar.setBackground(color1);
    ceasar.setForeground(color2);
    monalphabitic.setBackground(color1);
    monalphabitic.setForeground(color2);
    polyalphabitic.setBackground(color1);
    polyalphabitic.setForeground(color2);  
    playfiar.setBackground(color1);
    playfiar.setForeground(color2);
    hillCipher.setBackground(color1);
    hillCipher.setForeground(color2);
    transpostion.setBackground(color1);
    transpostion.setForeground(color2);
    one.setBackground(color1);
    one.setForeground(color2);
    gammal.setBackground(color1);
    gammal.setForeground(color2);
    rsa.setBackground(color1);
    rsa.setForeground(color2);
        
    //methods Panel
    methods.setBorder(new TitledBorder("Techniques"));
    methods.setBackground(color2);
    methods.add(ceasar);
    methods.add(monalphabitic);
    methods.add(polyalphabitic);
    methods.add(playfiar);
    methods.add(hillCipher);
    methods.add(transpostion);
    methods.add(one);
    methods.add(gammal);
    methods.add(rsa);
    
    //West Panel
    text.setBackground(color2);
    text.add(plain,BorderLayout.NORTH);
    text.add(methods,BorderLayout.EAST);
    text.add(plainText,BorderLayout.CENTER);
    
    //south Panel
    south.setBackground(color1);
    south.add(file);
    south.add(next);
    south.add(exit);
        
    //Add actionListener
    file.addActionListener(this);
    next.addActionListener(this);
    exit.addActionListener(this);
    
    //to radioButtons
    ceasar.addActionListener(this);
    monalphabitic.addActionListener(this);
    polyalphabitic.addActionListener(this);
    playfiar.addActionListener(this);
    hillCipher.addActionListener(this);
    transpostion.addActionListener(this);
    one.addActionListener(this);
    gammal.addActionListener(this);
    rsa.addActionListener(this);
    
    
    c.add(title_Panel,BorderLayout.NORTH);
    c.add(text,BorderLayout.WEST);
    c.add(south,BorderLayout.SOUTH);
    setSize(460,400);
    setTitle("Encryption System");
 	setVisible(true);
 	setResizable(false);
 	c.setBackground(color2);
 	
 	JTextArea text=new JTextArea();
 	text.setBackground(color1);
 	text.setForeground(Color.white);
 	text.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,13));
 	
 	String s="               Welcome to Eccryption System Program          \n\n"+
 	          "1- Please Insert Inputs ..(x1,x2,x3) \n"+
 	          "2- Choose The Suitable  Learning Method \n"+
 	          "3- Choose The Suitable  Activition Function\n\n";
 	text.setText(s);          
 	//JOptionPane.showMessageDialog(null,text,"Help",JOptionPane.INFORMATION_MESSAGE);
 
 }
 
 //--------------------actionPerformed Function --------------------------------------//
 public void actionPerformed (ActionEvent e)
 {
  
  if(e.getSource()==ceasar)
   technique=1;
  else if(e.getSource()==monalphabitic)
   technique=2; 
  else if(e.getSource()==polyalphabitic)
   technique=3;
  else if(e.getSource()==playfiar)
   technique=4;
  else if(e.getSource()==hillCipher)
   technique=5;  
  else if(e.getSource()==transpostion)
   technique=6;
  else if(e.getSource()==one)
   technique=7;  
  else if(e.getSource()==gammal)
   technique=8;   
  else if(e.getSource()==rsa)
   technique=9;   
      
  
  //------------------------------------------------------------------	
  //To Encrypt File 
  if(e.getSource()==file)
  {
  	 plain_string="";
  	 String SourceFile;
  	 File name;
	 Button butt=new Button();
	 JFileChooser choose;
	 name=new File(".");
	 choose=new JFileChooser(name);
	 ExampleFileFilter ex=new ExampleFileFilter();
	 ex.addExtension("txt");
	 ex.setDescription("");
	 choose.setFileFilter(ex);	
	 int interval=choose.showOpenDialog(butt);
	 SourceFile=choose.getSelectedFile().getName();
	 
	 BufferedReader input=null;     
     String inLine;
     try
     {
  	
  	     input=new BufferedReader(new FileReader (SourceFile));  	
         while ((inLine=input.readLine())!=null)   
          plain_string+=inLine;
       	  
        plainText.append(plain_string);
        //System.out.println(plain_string);
        
     }
     
     
    
  
     catch(FileNotFoundException exp)
     {
     
      System.out.println("File not Found  ");
     	
     }
  
     catch(IOException exp)
     {
     
       System.out.println(exp.getMessage());
  	
     }	
	
  }	
  
  //------------------------------------------------------------------	
  //To Encrypt Text 
  if(e.getSource()==next)
  {
  
   if(technique==1)
   {
   	plain_string=plainText.getText();
    Ceasar c=new Ceasar(plain_string);	 
   } 
   
   if(technique==2)
   {
   	plain_string=plainText.getText();
    	Monoalphbetic m=new Monoalphbetic(plain_string);
   } 
	
   if(technique==3)
   {
   	plain_string=plainText.getText();
    Polyalphbetic p=new Polyalphbetic(plain_string);
   }
   
   
   	
   if(technique==4)
   {
   	plain_string=plainText.getText();
   	Playfair pl =new 	Playfair(plain_string);
   }
   
   if(technique==5)
   {
   	plain_string=plainText.getText();
   	HillCipher h=new HillCipher(plain_string);
   }
   
   if(technique==6)
   {
   	plain_string=plainText.getText();
   	Transposition t =new Transposition(plain_string);
   } 
   if(technique==7)
   {
   	plain_string=plainText.getText();
   	OneTimePad o =new OneTimePad(plain_string);
   }    
   
   if(technique==8)
   {
   	plain_string=plainText.getText();
   	EL_Gammal g=new EL_Gammal(plain_string);	
   }           
   
   if(technique==9)
   {
   	plain_string=plainText.getText();
   	RSA r =new RSA(plain_string);
   }    
	
  }	
  
  
 //------------------------------------------------------------------
 //Exit
  if(e.getSource()==exit)
  {
  
   System.exit(0);  
   	
  }   
    
 }	
 
 
 /****************////***************////** main method **////*********////******************//
 public static void main(String arg[])

 {

  Encryption frame=new Encryption();
  
 


 }

}