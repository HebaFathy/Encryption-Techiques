package IE;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Ceasar extends JFrame implements ActionListener
{
 String s;
 int shift=0; 
 int index=0;
 
 char [] plain_array;
 char [] cipher_array;
 //char [] alphabet=new char [27];
 char [] alphabet={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                   'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                   '~','!','@','$','%','^','&','*','(',')','-','_','+','=','?','>','<',',','/',':','"',';','.',
                   '1','2','3','4','5','6','7','8','9','0',' ','\n'};
 JPanel button=new JPanel();
 JButton decrypt=new JButton("Decrypt"); 
 
 //Colors
 Color color1=new Color(43,118,154);
 Color color2=new Color(164,185,207);
  
 public Ceasar(String plain)
 { 
    
    /*alaphabet array
    alphabet[84]=' ';   
    for(int i=0;i<26;i++)   
     alphabet[i]=(char)('a'+i);*/
    	
  	
  	//Determine Shift Value 
  	try
  	{ 	
  	 String shift_string=JOptionPane.showInputDialog("Please Insert Shift Value:");
  	 System.out.println(alphabet.length);
     shift =Integer.parseInt(shift_string);
    }
    
     catch(NumberFormatException ex )
    {
    	
     JOptionPane.showMessageDialog(null,"Value Must be Integer","Error",JOptionPane.ERROR_MESSAGE);	
     Ceasar c=new Ceasar(plain);	
    }
 
 	//convert plain text to array 
 	plain_array=plain.toCharArray();
 	
 	//Encrypt
 	if(shift<=86)
 	 for(int i=0;i<plain_array.length;i++)
 	 {
 	  for(int j=0;j<alphabet.length;j++)
 	    if(alphabet[j]==plain_array[i])  
 	     index=j; 	  
 	    plain_array[i]=alphabet[((index+shift)%87)]; 
 	 }
 	
 	else 
 	{
 	 
 	 JOptionPane.showMessageDialog(null,"Invalid Shift Value","Error",JOptionPane.ERROR_MESSAGE); 	
 	 Ceasar c=new Ceasar(plain);	
 	}    
 	
 	//Convert cipher array to Strin for Display
 	s=""; 
 	JTextArea text=new JTextArea();
 	text.setBackground(Color.white);
 	text.setForeground(color1);
 	text.setFont(new Font("DialogInput",Font.BOLD,20));
 	for(int i=0;i<plain_array.length;i++)
 	 s+=(char)plain_array[i]; 	              
 	text.setText("*Cipher Text is :"+"\n"+"   "+s+"\n");          
  
 	//Display the Result 
    decrypt.addActionListener(this);
    button.setBackground(color1);
 	Container c=getContentPane();
 	button.add(decrypt);
 	c.add(text); 	
 	c.add(button,BorderLayout.SOUTH);
 	setSize(400,200);
 	setVisible(true);
 	setTitle("Ciphre Text");
 	
  }
 
 //========================================Decrypt Message=========================================//
 
 public void  Decrypt (String cipher,int shift)
 {
   
  /* //alaphabet array
    alphabet[26]=' ';
    for(int i=0;i<26;i++)
     alphabet[i]=(char)('a'+i);*/     
     
   //convert cipher string to array
   cipher_array=cipher.toCharArray();
   String x="";
   
   //Decrypty Message
   for(int i=0;i<cipher_array.length;i++)
   {
     for(int j=0;j<alphabet.length;j++)
      if(alphabet[j]==cipher_array[i])  
 	   index=j;
 	  
 	  
 	  if((index-shift)%87>=0)
 	   cipher_array[i]=alphabet[(index-shift)%87];
 	  else
 	   cipher_array[i]=alphabet[((index-shift)%87)+87];
 	   
 	  
 	  x+=(char)cipher_array[i]; 	
   }	 
        	
   JOptionPane.showMessageDialog(null,x,"Decrypted Message",JOptionPane.INFORMATION_MESSAGE); 	
 }
 
 //=============================================Action Performed===============================================//
 
 public void actionPerformed(ActionEvent e)
 {
 	
 	if(e.getSource()==decrypt)
 	 
 	 Decrypt(s,shift);
 	  
 	
 }
 
 
 //============================================Main Function===================================================	
 
 public static void main (String arg[])
 {
  
  	Ceasar c=new Ceasar("We Need More , Snow");
 	
 }	
	
	
	
}
