package IE;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class Monoalphbetic extends JFrame implements ActionListener
{
 
 String s; 
 int index=0;
 int flag=0;
 char [] plain_array;
 char [] cipher_array;
 char [] alphabet=new char [27];
 char [] rand=new char [27];
 JPanel button=new JPanel();
 JButton decrypt=new JButton("Decrypt"); 
 
 //Colors
 Color color1=new Color(43,118,154);
 Color color2=new Color(164,185,207);
  
 public Monoalphbetic(String plain)
 { 
    
    //alaphabet array
    alphabet[0]=' ';   
    for(int i=1;i<27;i++)   
     alphabet[i]=(char)('a'+i);
     
    rand[26]=' ';
        
    //generate random order of characters
    for(int i=0;i<26;i++)
    {     
     rand[i]=(char)((int)((int)'a' + Math.random() * ((int)'z'-(int)'a'+1))); 	
     for(int j=0;j<i;j++)
      if(rand[i]==rand[j])
        flag=1;	
         
     while(flag==1)
     {
      
      flag=0;	
      rand[i]=(char)((int)((int)'a' + Math.random() * ((int)'z'-(int)'a'+1)));      
      for(int j=0;j<i;j++)
       if(rand[i]==rand[j])
        flag=1;	
     }
     
     flag=0;     	
    }
    
    for(int i=0;i<27;i++)   
     System.out.println(rand[i]);
     
    
    //convert plain text to array 
 	plain_array=plain.toCharArray();
 	
    //Encrypt
    for(int i=0;i<plain_array.length;i++)
    {
     
     for(int j=0;j<alphabet.length;j++)
 	    if(alphabet[j]==plain_array[i])  
 	     index=j; 	  	
     plain_array[i]=rand[index];	
    }
    
      	
 	s=""; 
 	JTextArea text=new JTextArea();
 	text.setBackground(Color.white);
 	text.setForeground(color1);
 	text.setFont(new Font("DialogInput",Font.BOLD,20));
 	
 	for(int i=0;i<plain_array.length;i++)
 	 s+=(char)plain_array[i];
 	  	              
 	text.setText("*Cipher Text is :"+"\n"+"   "+s+"\n");          
  
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
 
//========================================Decrypt Message=========================================//
 
 public void  Decrypt (String cipher)
  {
  
   //convert cipher string to array
   cipher_array=cipher.toCharArray();
   String x="";
   
    for(int i=0;i<cipher_array.length;i++)
     {
      for(int j=0;j<alphabet.length;j++)
 	   if(rand[j]==plain_array[i])  
 	     index=j;
 	     
 	    cipher_array[i]= alphabet[index]; 	    
 	    x+=(char)cipher_array[i]; 
 	 }  
 	   
 	      	  	        	
   JOptionPane.showMessageDialog(null,x,"Decrypted Message",JOptionPane.INFORMATION_MESSAGE); 	
 }
 
 //=============================================Action Performed===============================================//
 
 public void actionPerformed(ActionEvent e)
 {
 	
 	if(e.getSource()==decrypt)
 	 
 	 Decrypt(s);
 	  
 	
 }
 
 
 //============================================Main Function===================================================	
 
 public static void main (String arg[])
 {
  
  	Monoalphbetic m=new Monoalphbetic("we need more snow");
 	
 }	
	
	
	
}
