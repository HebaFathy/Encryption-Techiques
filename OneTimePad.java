package IE;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class OneTimePad extends JFrame implements ActionListener
{
 
 JPanel button=new JPanel();
 JButton decrypt=new JButton("Decrypt"); 
 
 //Colors
 Color color1=new Color(43,118,154);
 Color color2=new Color(164,185,207);
 
 int acci=0;
 char [] key;
 char [] plain_array;
 char [] cipher_array; 
 char [] alphabet=new char [27];

 String s="";
 String cipher="";
 	 
public OneTimePad(String plain)
{
	
   //Get key
   String key_string=JOptionPane.showInputDialog("Please Insert key:");	
   key=key_string.toCharArray(); 
   
  //Remove space from plain text 
  for(int i=0;i<plain.length();i++)
   if(plain.charAt(i)!=' ')
    s+=plain.charAt(i);  
      
  plain_array=s.toCharArray();
  
  //check validity of the key length
  if(plain_array.length>key.length)
  {  	
  	
   JOptionPane.showMessageDialog(null,"Please Insert The  same Plain Text Length","Error",JOptionPane.ERROR_MESSAGE);	
   OneTimePad o =new OneTimePad(plain);
   
  }  
  
  //convert plian to array
  plain_array=plain.toCharArray();
  
  //Encrypt 
  for(int i=0;i<plain_array.length;i++)
  {
   	 acci=(int)plain_array[i]^(int)key[i];
   	 System.out.println(acci);
   	  cipher+=(char)(acci+97-32);
   	System.out.println((acci+97-32)+"***");
  } 
  
  System.out.println(cipher);
  	
  JTextArea text=new JTextArea();
  text.setBackground(Color.white);
  text.setForeground(color1);
  text.setFont(new Font("DialogInput",Font.BOLD,20));  
	              
  text.setText("*Cipher Text is :"+"\n"+"   "+cipher+"\n");
    
  //Display the Result 
  Container c=getContentPane();
  decrypt.addActionListener(this);
  button.setBackground(color1); 	
  button.add(decrypt);
  c.add(text); 	
  c.add(button,BorderLayout.SOUTH);
  setSize(400,200);
  setVisible(true);
  setTitle("Ciphre Text");   		
}

//========================================Decrypt Message=====================================================//
public void  Decrypt (String cipher)
{
   String y="";
   int temp;
   cipher_array=cipher.toCharArray();
   
   for(int i=0;i<cipher_array.length;i++)
   { 
   	 temp=(int)(cipher_array[i])-97+32;
   	 acci=temp^key[i];
   	 System.out.println(acci);   	 
   	  y+=(char)(int)(Math.abs(acci));
   }
       	      	  	        	
   JOptionPane.showMessageDialog(null,y,"Decrypted Message",JOptionPane.INFORMATION_MESSAGE); 	
} 
	
//=============================================Action Performed===============================================//
 
public void actionPerformed(ActionEvent e)
{
 	
 	if(e.getSource()==decrypt)
 	  Decrypt(cipher);
 	  
 	
}
 	
 
//============================================Main Function===================================================	
 
public static void main (String arg[])
{
  
  	OneTimePad o =new OneTimePad("we need more snow");
 	
}	

		
	
	
	
}