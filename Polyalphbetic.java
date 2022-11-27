package IE;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class Polyalphbetic extends JFrame implements ActionListener
{
	
 String s; 
 int index1=0;
 int index2=0;
 int flag=0;
 char [] key;
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

public Polyalphbetic(String plain)
{
  
/* //alaphabet array
 alphabet[26]=' ';
 for(int i=1;i<26;i++)   
   alphabet[i]=(char)('a'+i);*/
  
 //convert plain text to array 
 plain_array=plain.toCharArray();
  
 //get Key
 String key_string=JOptionPane.showInputDialog("Please Insert Key:");
 if(checkifInvalid(key_string))
 {
   
   	JOptionPane.showMessageDialog(null,"Please Insert Small Characters","Error",JOptionPane.ERROR_MESSAGE);
   	key_string=JOptionPane.showInputDialog("Please Insert key:");
 } 
   	 
 //convert to array 
 key= key_string.toCharArray();
  
 //ckeck validity of the key i.e (no same chars) 
 for(int i=0;i<key.length;i++)
  for(int j=0;j<key.length;j++)
   if(i!=j)
    if(key[i]==key[j])
     flag=1;
  
if(flag==1)
{
 	
  JOptionPane.showMessageDialog(null,"Please do not Insert same Characters","Error",JOptionPane.ERROR_MESSAGE);	
  Polyalphbetic p=new Polyalphbetic(plain);		
  flag=0;	
}
 
 //Encrypt
 for(int i=0;i<plain_array.length;i=i+key.length)
  for(int j=0;j<key.length&&i+j<plain_array.length;j++)
  {
    //get index of char in plian text 
    for(int x=0;x<alphabet.length;x++)
     if(plain_array[i+j]==alphabet[x])
      index1=x;
    
    //get index of char in key 
    for(int x=0;x<alphabet.length;x++)
     if(key[j]==alphabet[x])
      index2=x;
      
    plain_array[i+j]=alphabet[(index1+index2)%87];
    System.out.println(plain_array[i+j]);	
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
 
public void  Decrypt (String cipher,char key[])
{
  
 //convert cipher string to array
 cipher_array=cipher.toCharArray();
 String y="";
 
 //Decrypt
 for(int i=0;i<cipher_array.length;i=i+key.length)
  for(int j=0;j<key.length&&i+j<cipher_array.length;j++)
  {
    //get index of char in plian text 
    for(int x=0;x<alphabet.length;x++)
     if(cipher_array[i+j]==alphabet[x])
      index1=x;
    
    //get index of char in key 
    for(int x=0;x<alphabet.length;x++)
     if(key[j]==alphabet[x])
      index2=x;
      
    if((index1-index2)%86>=0)
     cipher_array[i+j]=alphabet[(index1-index2)%87];
    else
     cipher_array[i+j]=alphabet[((index1-index2)%87)+87];
     
    y+=(char)cipher_array[i+j];  
   
  }
  
  JOptionPane.showMessageDialog(null,y,"Decrypted Message",JOptionPane.INFORMATION_MESSAGE);   		
}
 
//=============================================Action Performed===============================================//
 
public void actionPerformed(ActionEvent e)
{
 	
 	if(e.getSource()==decrypt)
 	 
 	 Decrypt(s,key);
 	  
 	
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
  
  	Polyalphbetic p =new Polyalphbetic("We Need More Snow @");
 	
 }	

}