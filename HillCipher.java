package IE;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class HillCipher extends JFrame //implements ActionListener
{
 
 int p1;
 int p2;
 int determinant;
 int []p=new int [2];
 int []e_result=new int [2];
 int []d_result=new int [2];
 String s="";
 String c="";
 String cipher="";
 char[]temp=new char[2]; 
 char [] alphabet=new char [26];
 int key[][]={{15,12},{11,3}};
 int inverse[][]={{3,-12},{-11,15}};
 JPanel button=new JPanel();
 JButton decrypt=new JButton("Decrypt"); 
 
 //Colors
 Color color1=new Color(43,118,154);
 Color color2=new Color(164,185,207); 
 
 public HillCipher (String plain)
 {
 	
  //Encrypt
  //alaphabet array
  for(int i=0;i<26;i++)
    alphabet[i]=(char)('a'+i);
    
  //Remove space from plain text 
   for(int i=0;i<plain.length();i++)
    if(plain.charAt(i)!=' ')
     s+=plain.charAt(i);
   if(s.length()%2!=0)
    s+='x';
   System.out.println(s);   
  //compute determinant of key
  determinant=(int)(key[0][0]*key[1][1]-key[0][1]*key[1][0]);  
  System.out.println(determinant);
  
  
   
  //Start
  for(int i=0;i<s.length();i+=2)
  {
   temp[0]=s.charAt(i);
   temp[1]=s.charAt(i+1);
   
   System.out.println(temp[0]+" "+temp[1]);
   //get index of chars
   for(int j=0;j<alphabet.length;j++)
    if(alphabet[j]==temp[0])
     p1=j;
   
   //get index of chars
   for(int j=0;j<alphabet.length;j++)
    if(alphabet[j]==temp[1])
     p2=j;
     
   //put in plian matrix
   p[0]=p1;
   p[1]=p2;
   
   //multiply p by key 
   e_result=Multiplay(key,p);    	  	
   
   System.out.println(p1);
   System.out.println(p2);
   
   System.out.println(e_result[0]);
   System.out.println(e_result[1]);
   //get cipher text 
   cipher+=alphabet[e_result[0]%26];
   cipher+=alphabet[e_result[1]%26];
   
   //Decrypt
   d_result=Multiplay(inverse,e_result);
   
   c+=alphabet[d_result[0]/determinant];
   c+=alphabet[d_result[1]/determinant];
   
  }
  
  // System.out.println(cipher);    
  JTextArea text=new JTextArea();
  text.setBackground(Color.white);
  text.setForeground(color1);
  text.setFont(new Font("DialogInput",Font.BOLD,20));
  text.setText("*Cipher Text is :"+"\n"+"   "+cipher+"\n"+"*Decrypted Message is :"+"\n"+"   "+c+"\n");          
  
  
  //Display the Result 
  //decrypt.addActionListener(this);
  button.setBackground(color1);
  Container c=getContentPane();
  button.add(decrypt);
  c.add(text); 	
  //c.add(button,BorderLayout.SOUTH);
  setSize(450,250);
  setVisible(true);
  setTitle("Ciphre Text");
      
     	
 }
 
//========================================Multiplay Matrix ==================================================//            
 public int[] Multiplay (int key_matrix[][],int p_matrix[])
 {
 	int[]result=new int [2];
 	result[0] =(key_matrix[0][0]*p_matrix[0])+(key_matrix[0][1]*p_matrix[1]);
 	result[1] =(key_matrix[1][0]*p_matrix[0])+(key_matrix[1][1]*p_matrix[1]);
 	return result;
 }
 
//============================================Main Function===================================================//	
 
 
 public static void main(String args[])
 {
  
  HillCipher h=new HillCipher("we need more snow" );	
 	
 }

}	