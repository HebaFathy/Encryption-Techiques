package IE;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.security.GeneralSecurityException;
import java.security.spec.InvalidKeySpecException;


public class Transposition extends JFrame implements ActionListener
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
 char matrix [][];
 
 String s="";
 String cipher="";
 String key_string="";
 
public Transposition(String plain) 
{
   
   //Get key
   key_string=JOptionPane.showInputDialog("Please Insert key:");
   
   if(checkifInvalid(key_string))
   {
   
   	JOptionPane.showMessageDialog(null,"Please Insert Small Characters","Error",JOptionPane.ERROR_MESSAGE);
   	key_string=JOptionPane.showInputDialog("Please Insert key:");
   } 
   
  	
   key=key_string.toCharArray();
   
   //Remove space from plain text 
   for(int i=0;i<plain.length();i++)
    if(plain.charAt(i)!=' ')
     s+=plain.charAt(i);
     
   //convert  plain to array
   plain_array=s.toCharArray();  
   
   //Detrmine the hight of the matrix 
   int h=plain_array.length/key.length;
   if((plain_array.length%key.length)!=0)
    h=h+2;
   else 
    h=h+1;    
   
   //make matrix width the same as key length
   matrix=new char [h][key.length];
   
   //inialize the matrix
    for(int i=0;i<matrix.length;i++)
     for(int j=0;j<matrix[0].length;j++)
      matrix[i][j]=' ';
       
   //put key in first raw 
   for(int i=0;i<key.length;i++)
    matrix[0][i]=key[i]; 
   
   //put plain text in matrix
   int y=1; //hight 
   for(int i=0;i<plain_array.length;i=i+key.length)
   {
   	for(int x=0;x<key.length;x++)
     if(i+x<plain_array.length)
      matrix[y][x]=plain_array[i+x];
    
    y++; 
   } 
   
   for(int i=0;i<matrix.length;i++)
   {
   	 for(int j=0;j<matrix[0].length;j++)
      System.out.print(matrix[i][j]);
     System.out.println(); 
   }
   
   //sort key to encrypt 
   java.util.Arrays.sort(key);
   
   int count=0;
   //put * in empty places
   for(int i=0;i<matrix.length;i++)
    for(int j=0;j<matrix[0].length;j++)
     if(matrix[i][j]==' ')
      {
      	matrix[i][j]=(char)((int)'a'+count);
      	count++;
      }	
        
   //Encrypt
   for(int i=0;i<key.length;i++)
   {
    
    for(int x=0;x<key.length;x++)
     if(matrix[0][x]==key[i])
      for(int z=1;z<matrix.length;z++)
        cipher+=matrix[z][x];
   }
   
       
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
    setSize(450,200);
    setVisible(true);
    setTitle("Ciphre Text"); 
     		  
}

//========================================Decrypt Message=====================================================//
public void  Decrypt (String cipher,String key_string)
{
   
   String c="";
   int index_w=0;
   key=key_string.toCharArray();
   char min=key[0]; 
   cipher_array=cipher.toCharArray(); 
   
   //Empty the matrix 
   for(int i=0;i<matrix.length;i++)
    for(int j=0;j<matrix[0].length;j++)
     matrix[i][j]=' ';
   
   //put key in first raw
   for(int i=0;i<key.length;i++)
    matrix[0][i]=key[i];   
  
   
   //put cipher text in matrix
   int y=0; //width
   int counter=0; 
   for(int i=0;i<cipher_array.length;i=i+matrix.length-1)
   {
     	
   	//find minimum in key
   	for(int z=0;z<key.length;z++)
   	 if(key[z]<=min)
   	  min=key[z];
      	  
   	//find index of key
   	for(int w=0;w<matrix[0].length;w++)
   	 if(matrix[0][w]==min)
   	 {
   	   index_w=w; 	   
   	 }
   	 
   	key[index_w]='~';
   	min='~';   	  
   	  	 
   	for(int x=1;x<matrix.length;x++)
     if(i+counter<cipher_array.length)
     {
      matrix[x][index_w]=cipher_array[i+counter];
      counter++;
     }
     counter=0;     
   }
   
   for(int i=0;i<matrix.length;i++)
   {
    for(int j=0;j<matrix[0].length;j++)
      System.out.print(matrix[i][j]);
    System.out.println();  
   }
   
   
   
   //Decrypt
   for(int i=1;i<matrix.length;i++)
    for(int j=0;j<matrix[0].length;j++)
      if(matrix[i][j]!=' ')
        c+=matrix[i][j];
   
   JOptionPane.showMessageDialog(null,c,"Decrypted Message",JOptionPane.INFORMATION_MESSAGE);
   System.out.println(c);    
       
   
} 
	
//=============================================Action Performed===============================================//
 
public void actionPerformed(ActionEvent e)
{
 	
 	if(e.getSource()==decrypt)
 	  Decrypt(cipher,key_string);
 	  
 	
}
//=============================================check key===============================================// 
public boolean checkifInvalid(String key)
{
 
 int flag =0;
 char num[]={'1','2','3','4','5','6','7','8','9','0'};
 char special[]={' ','~','!','@','$','%','^','&','*','(',')','-','_','+','=','?','>','<',',','/',':','"',';','.'};
 char capital[]={'A','B','C','D','E','F','G','H','I','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
 
 for(int i=0;i<key.length();i++)
  for(int j=0;j<num.length;j++)
   if(key.charAt(i)==num[j])
    flag=1;	
    
 for(int i=0;i<key.length();i++)
  for(int j=0;j<special.length;j++)
   if(key.charAt(i)==special[j])
    flag=1;	
    
 for(int i=0;i<key.length();i++)
  for(int j=0;j<capital.length;j++)
   if(key.charAt(i)==capital[j])
    flag=1;
    
 if(flag==1)
  return true;
 else 
  return false;    	         
}	
 
//============================================Main Function===================================================	
 
public static void main (String arg[]) 
{
  
  	Transposition t =new Transposition("please transfer one million dollar");
 	
}	

		
	
	
	
}