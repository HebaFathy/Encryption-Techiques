package IE;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class Playfair extends JFrame implements ActionListener
{
 
 String s="";;
 int flag=0;
 int flag1=0;
 int index_i=0;
 int index_j=0; 
 
 char [] key;
 char [] plain_array;
 char [] cipher_array;
 char [] non_key;
 char [] alphabet=new char [25];
 char [][]matrix=new char [5][5];
 
 String non_key_string;
 String cipher_string="";
 
 JPanel button=new JPanel();
 JButton decrypt=new JButton("Decrypt"); 
 
 //Colors
 Color color1=new Color(43,118,154);
 Color color2=new Color(164,185,207);
 
 public Playfair(String plain)
 {
 	
  //Get key
  String key_string=JOptionPane.showInputDialog("Please Insert key:");
  if(checkifInvalid(key_string))
  {
   
   	JOptionPane.showMessageDialog(null,"Please Insert Small Characters","Error",JOptionPane.ERROR_MESSAGE);
   	key_string=JOptionPane.showInputDialog("Please Insert key:");
  } 
   	
  key=key_string.toCharArray();  
  
  
  //Remove space from plain text and check Lenght if odd add x 
  for(int i=0;i<plain.length();i++)
   if(plain.charAt(i)!=' ')
    s+=plain.charAt(i);  
   if(s.length()%2!=0)
    s+='x';   
  plain_array=s.toCharArray();
  
  
  //Convert any J==>I in Plaint Text and Key 
  //key 
  for(int i=0;i<key.length;i++)
   if(key[i]=='j')
    key[i]='i';
  
  //plain text 
  for(int i=0;i<plain_array.length;i++)
   if(plain_array[i]=='j')
    plain_array[i]='i';
    
  //ckeck validity of the key i.e (no same chars) 
  for(int i=0;i<key.length;i++)
  for(int j=0;j<key.length;j++)
   if(i!=j)
    if(key[i]==key[j])
     flag1=1;
  
  if(flag1==1)
  {
 	
    JOptionPane.showMessageDialog(null,"Please do not Rapeated Characters","Error",JOptionPane.ERROR_MESSAGE);
  } 	
    
  if(flag1==1)  
  {
   	Playfair pl=new Playfair(plain);
    flag1=0;
  }
  	
  
  else
  {
   //Set i and j to i
   alphabet[8]='i';
  
   //Fill alphabet array up to h  and before (i/j)
   for(int i=0;i<8;i++)
    alphabet[i]=(char)('a'+i); 
  
   //Fill alphabet array up to z  and after (i/j)
   for(int i=9;i<alphabet.length;i++)
    alphabet[i]=(char)('a'+i+1);
   
   //extract non key letters
   non_key_string="";
   for(int i=0;i<alphabet.length;i++)
   {
    for(int j=0;j<key.length;j++)
     if(alphabet[i]==key[j])
      flag=1; 
    
     if(flag!=1)
      non_key_string+=alphabet[i];
     flag=0; 
   }
      
   //Fill key in Matrix
   int counter=0;;
   for(int i=0;i<5;i++) 
    for(int j=0;j<5;j++)
     if(counter<key.length)
     {
      matrix[i][j]=key[counter];
      counter++;
      index_i=i;
      index_j=j;
     }
  
 
   ///Fill The Rest of the array 
   non_key=non_key_string.toCharArray();
   if(index_j>=4)
   { 
    index_i=index_i+1;
    index_j=0;
   }
  
   else
    index_j=index_j+1;
  
   counter=0;
   for(int i=index_i;i<5;i++) 
    for(int j=index_j;j<5;j++)
    {
     if(counter<non_key.length)
     {
      matrix[i][j]=non_key[counter];
      counter++;    
     }
     index_j=0;
    }  
   
 ///////////////////////////////////////////  
  System.out.print("The Matrix is: "+"\n\n");
   for(int i=0;i<5;i++) 
   {
    	for(int j=0;j<5;j++) 
         System.out.print(matrix[i][j]+" ");
     System.out.println(); 
   }
  ///////////////////////////////////////////  
    
 //Encrypt
  String temp="";
  int index_i_1=0; //indecies of found chars
  int index_j_1=0;
  int index_i_2=0;
  int index_j_2=0;  
  
  for(int i=0;i<plain_array.length;i=i+2)
  { 
  
   for(int j=0;j<2;j++)
    temp+=plain_array[i+j];
   
   //check if there is repeated chars to set it to x 
   if(temp.charAt(0)==temp.charAt(1))
   {
   	plain_array[i+1]='x';
   	temp="";
   	for(int j=0;j<2;j++)
     temp+=plain_array[i+j];
   }	
  
   //get index of char1
   for(int x=0;x<matrix.length;x++)
    for(int y=0;y<matrix[0].length;y++)
     if(matrix[x][y]==temp.charAt(0))   
     {
     	index_i_1=x;
        index_j_1=y;
     }
     
  //get index of char2  
  for(int x=0;x<matrix.length;x++)
   for(int y=0;y<matrix[0].length;y++)
    if(matrix[x][y]==temp.charAt(1))   
    {
     	index_i_2=x;
        index_j_2=y;
    }   
  
  
  //if in the same row
  if(index_i_1==index_i_2)
  {
    if(index_j_1>=4)
     index_j_1=-1;
    
    if(index_j_2>=4)
     index_j_2=-1;
     
    cipher_string+=matrix[index_i_1][index_j_1+1];
    cipher_string+=matrix[index_i_2][index_j_2+1];
  }
  
  //if in the same column
  else if(index_j_1==index_j_2)
  {
    if(index_i_1>=4)
     index_i_1=-1;
    
    if(index_i_2>=4)
     index_i_2=-1;
      
    cipher_string+=matrix[index_i_1+1][index_j_1];
    cipher_string+=matrix[index_i_2+1][index_j_2];
  }
  
  else
  {
      
   cipher_string+=matrix[index_i_1][index_j_2];	
   cipher_string+=matrix[index_i_2][index_j_1];	
  	
  }
  
  temp="";
  
  }//for     
 
    
  
  JTextArea text=new JTextArea();
  text.setBackground(Color.white);
  text.setForeground(color1);
  text.setFont(new Font("DialogInput",Font.BOLD,20));

  
	              
  text.setText("*Cipher Text is :"+"\n"+"   "+cipher_string+"\n");          
  
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

 }//big else
 
}
//========================================Decrypt Message=========================================//
 
 public void  Decrypt (String cipher)
 {
  
  //convert cipher string to array
  cipher_array=cipher.toCharArray();
  String xx="";
  
  //Decrypt
  String temp="";
  int index_i_1=0; //indecies of found chars
  int index_j_1=0;
  int index_i_2=0;
  int index_j_2=0;  
  
  for(int i=0;i<cipher_array.length;i=i+2)
  { 
  
   for(int j=0;j<2;j++)
    temp+=cipher_array[i+j];
   
   //check if there is repeated chars to set it to x 
   if(temp.charAt(0)==temp.charAt(1))
   {
   	cipher_array[i+1]='x';
   	temp="";
   	for(int j=0;j<2;j++)
     temp+=cipher_array[i+j];
   }	
  
   //get index of char1
   for(int x=0;x<matrix.length;x++)
    for(int y=0;y<matrix[0].length;y++)
     if(matrix[x][y]==temp.charAt(0))   
     {
     	index_i_1=x;
        index_j_1=y;
     }
     
   //get index of char2  
   for(int x=0;x<matrix.length;x++)
    for(int y=0;y<matrix[0].length;y++)
     if(matrix[x][y]==temp.charAt(1))   
     {
     	index_i_2=x;
        index_j_2=y;
     }
    
    
  //if in the same row
  if(index_i_1==index_i_2)
  {
    if(index_j_1<=0)
     index_j_1=5;
    
    if(index_j_2<=0)
     index_j_2=5;
     
    xx+=matrix[index_i_1][index_j_1-1];
    xx+=matrix[index_i_2][index_j_2-1];
  }
  
  //if in the same column
  else if(index_j_1==index_j_2)
  {
    if(index_i_1<=0)
     index_i_1=5;
    
    if(index_i_2<=0)
     index_i_2=5;
      
    xx+=matrix[index_i_1-1][index_j_1];
    xx+=matrix[index_i_2-1][index_j_2];
  }
  
  else
  {
      
   xx+=matrix[index_i_1][index_j_2];	
   xx+=matrix[index_i_2][index_j_1];	
  	
  }
  
  temp="";
  
  }//for     
 
 
 	      	  	        	
   JOptionPane.showMessageDialog(null,xx,"Decrypted Message",JOptionPane.INFORMATION_MESSAGE); 	
} 

//=============================================Action Performed===============================================//
 
public void actionPerformed(ActionEvent e)
{
 	
 	if(e.getSource()==decrypt)
 	  Decrypt(cipher_string);
 	  
 	
}
//=============================================check key===============================================// 
public boolean checkifInvalid(String key)
{
 
 int flag =0;
 char num[]={'1','2','3','4','5','6','7','8','9','0'};
 char special[]={' ','~','!','@','$','%','^','&','*','(',')','-','_','+','=','?','>','<',',','/',':','"',';'};
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
  
  	Playfair pl =new Playfair("we need more snjow");
 	
}	

	
	
}	