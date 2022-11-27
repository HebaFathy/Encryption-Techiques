package IE;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class RSA extends JFrame implements ActionListener
{
 
 int p;
 int q;
 int n;
 int z;
 int d;
 int e;
 int m;
 int alpha;
 int index;
 String cipher="";
 char [] alphabet=new char [27];
 
 JPanel button=new JPanel();
 JButton decrypt=new JButton("Decrypt"); 
 
 //Colors
 Color color1=new Color(43,118,154);
 Color color2=new Color(164,185,207);
  
 public RSA(String plain)
 { 
  
  //take numbers
  p=Integer.parseInt(JOptionPane.showInputDialog("Please Insert p:"));	
  q=Integer.parseInt(JOptionPane.showInputDialog("Please Insert q:"));
  
  //Check if p=q
  if(p==q)
  {
    JOptionPane.showMessageDialog(null,"p and q Must Not be Equal","Error",JOptionPane.ERROR_MESSAGE);	
    RSA r=new RSA(plain);	
  }
  
  //Apply Ferme Test
  if((FermeTest(p))==false||(FermeTest(q))==false)
  {
    JOptionPane.showMessageDialog(null,"Value Must Prime","Error",JOptionPane.ERROR_MESSAGE);	
    RSA r=new RSA(plain);	
  }
  
  
  else
  {
   //Compute n and z
   n=p*q;
   z=(p-1)*(q-1);	
      
   //Find d such that d relativly prime to z and < n
   d=2;	
   for(int i=2;i<n;i++)
   { 
     
     if((z%d!=0))
      break;
     else 
      d=i;      
   }  
   
   System.out.println("z= "+z);     
   System.out.println("d= "+d);  
   
   //Compute e such that (e*d)%z=1 & e!=d
   alpha=1;
   for(int i=1;i<n;i++)
   {
    m=alpha*z+1;     
    e=m/d;
    
    if((e*d)%z==1&&(d!=e))
     break;
    else
     alpha=i; 	
   	
   }
   
   System.out.println("e= "+e);    
  }
  
  //=================================Encryption===============================================/
  //Encrypt
       //alaphabet array
  alphabet[26]=' ';   
  for(int i=0;i<26;i++)   
    alphabet[i]=(char)('a'+i);
  //char [] alphabet={'*','a','b','c','d','e','f','g','g','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
  for(int i=0;i<plain.length();i++)
  {
  	
   for(int j=0;j<alphabet.length;j++)
    if(alphabet[j]==plain.charAt(i))
     index=j;
  //System.out.println(index);
  index=(int)(Math.pow(index,e)%n);     
  if(index<alphabet.length)
    cipher+=alphabet[index];
  
    System.out.println(index);    
    
  
  }
  System.out.println(cipher);    
  JTextArea text=new JTextArea();
  text.setBackground(Color.white);
  text.setForeground(color1);
  text.setFont(new Font("DialogInput",Font.BOLD,20));
  text.setText("z= "+z+"\n"+"d= "+d+"\n"+"e= "+e+"\n"+"*Cipher Text is :"+"\n"+"   "+cipher+"\n");          
  
  //Display the Result 
  decrypt.addActionListener(this);
  button.setBackground(color1);
  Container c=getContentPane();
  button.add(decrypt);
  c.add(text); 	
  c.add(button,BorderLayout.SOUTH);
  setSize(450,250);
  setVisible(true);
  setTitle("Ciphre Text");
   
 }
 
 //========================================Decrypt Message=========================================//
 
 public void  Decrypt (String cipher)
 {
    String x="";  
   
    //alaphabet array
    alphabet[26]=' ';
    for(int i=0;i<26;i++)
     alphabet[i]=(char)('a'+i);
   //char [] alphabet={'*','a','b','c','d','e','f','g','g','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};         
  
   //Decrypty Message
   for(int i=0;i<cipher.length();i++)
   {  	
    for(int j=0;j<alphabet.length;j++)
     if(alphabet[j]==cipher.charAt(i))
      index=j;
    System.out.println(index);
    index=(int)(Math.pow(index,d)%n);     
    if(index<alphabet.length)
      x+=alphabet[index];
     
    System.out.println(index);     
   }
        	
   JOptionPane.showMessageDialog(null,x,"Decrypted Message",JOptionPane.INFORMATION_MESSAGE); 	
 } 
 
//=============================================Ferme Test===============================================//
 public boolean FermeTest(int num)
 {
  
   if(num==2)
    return true;
   if(num==3)
    return true;
   if(num==5)
    return true;
   if(num==7)
    return true;
    
   else if((((int)Math.pow(2,num))%num==2)/*&&(((int)Math.pow(3,num))%num==3)&&(((int)Math.pow(5,num))%num==5)&&(((int)Math.pow(7,num))%num==7)*/)
    return true;
   else
    return false;  
    
 }
 
 //=============================================Action Performed===============================================//
 
 public void actionPerformed(ActionEvent e)
 {
 	
 	if(e.getSource()==decrypt)
 	 
 	 Decrypt(cipher);
 	  
 	
 }
 
 
 //============================================Main Function===================================================	
 
 
 public static void main(String args[])
 {
  
  RSA r=new RSA("please transfer one million dollar" );	
 	
 }


}  