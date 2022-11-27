package IE;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class EL_Gammal extends JFrame implements ActionListener
{
 int p;
 int x;
 int g;
 int y;
 int k;
 int a;
 int b;
 int m;
 double z;
 int index=0;
 int index1=0; 
 int index2=0;
 
 String cipher="";
 char [] alphabet=new char [27];
 
 JPanel button=new JPanel();
 JButton decrypt=new JButton("Decrypt"); 
 
 //Colors
 Color color1=new Color(43,118,154);
 Color color2=new Color(164,185,207);
  
 
 public EL_Gammal(String plain)
 {
  //take number p
  p=Integer.parseInt(JOptionPane.showInputDialog("Please Insert p:"));	
  
  //Apply Ferme Test
  if((FermeTest(p))==false)
  {
    JOptionPane.showMessageDialog(null,"Value Must Prime","Error",JOptionPane.ERROR_MESSAGE);	
    EL_Gammal g=new EL_Gammal(plain);	
  }
  
  //generate random numbers x and g and less than p
  /*x=1+(int)(Math.random()*(p-1)); 
  g=1+(int)(Math.random()*(p-1)); */
  x=5;
  g=13;
  
  while(FermeTest(x)==false||FermeTest(g)==false) 
  {
  	x=1+(int)(Math.random()*(p-1)); 
    g=1+(int)(Math.random()*(p-1)); 
  }  
  
  //Compute y
  y=(int)(Math.pow(g,x)%p);  
    
  //Encrypt
  //alaphabet array
    alphabet[26]=' ';
    for(int i=0;i<26;i++)
     alphabet[i]=(char)('a'+i);
  
  
  for(int i=0;i<plain.length();i++)
  {
   //Choose random number key k relatively prime to (p-1) & less than (p-1)  
   //k=1+(int)(Math.random()*(p-2));
   k=9;
   /*while((p-1)%k==0)
    k=1+(int)(Math.random()*(p-2));*/
  
   //Compute a and b
   a=(int)(Math.pow(g,k)%p);
   	   
   
   for(int j=0;j<alphabet.length;j++)
    if(alphabet[j]==plain.charAt(i))
      index=j;
   b=(int)((Math.pow(y,k)*index)%p);
   cipher+=alphabet[a];
   cipher+=alphabet[b];
   
   System.out.println(a);
   System.out.println(b); 
  }	
  
  System.out.println("x= "+x);
  System.out.println("g= "+g);
  System.out.println("y= "+y);
  System.out.println("k= "+k);
  System.out.println("a= "+a);
  System.out.println("b= "+b);
  System.out.println("cipher is "+cipher);
  
  // System.out.println(cipher);    
  JTextArea text=new JTextArea();
  text.setBackground(Color.white);
  text.setForeground(color1);
  text.setFont(new Font("DialogInput",Font.BOLD,20));
  text.setText("*Cipher Text is :"+"\n"+"   "+cipher+"\n");          
  
  //Display the Result 
  decrypt.addActionListener(this);
  button.setBackground(color1);
  Container c=getContentPane();
  button.add(decrypt);
  c.add(text); 	
  c.add(button,BorderLayout.SOUTH);
  setSize(700,250);
  setVisible(true);
  setTitle("Ciphre Text");
   
   	
 }
 
 //========================================Decrypt Message=========================================//
 
 public void  Decrypt (String cipher)
 {
    String u="";  
    char temp[]=new char[2];
    
   //alaphabet array
    alphabet[26]=' ';
    for(int i=0;i<26;i++)
     alphabet[i]=(char)('a'+i);
     
   for(int i=0;i<26;i++)  
   System.out.println(alphabet[i]) ; 
  
   //Decrypty Message
   for(int i=0;i<cipher.length();i+=2)
   {  	
    
    temp[0]=cipher.charAt(i);
    temp[1]=cipher.charAt(i);
    
    for(int j=0;j<alphabet.length;j++)
     if(alphabet[j]==cipher.charAt(i))
      index1=j;
    for(int j=0;j<alphabet.length;j++)
     if(alphabet[j]==cipher.charAt(i+1))
      index2=j;
    
    //z=Math.pow(j,(p-1-x));
    z=Math.pow(index1,(p-1-x)); 
    m=(int)((z*index2)%p);     
    //if(m<alphabet.length&&m>0)
      u+=alphabet[m];
     
    System.out.println(z+" "+index2+" "+m);     
   }
        	
   JOptionPane.showMessageDialog(null,u,"Decrypted Message",JOptionPane.INFORMATION_MESSAGE); 	
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
    
   if((((int)Math.pow(2,num))%num==2)/*&&(((int)Math.pow(3,num))%num==3)&&(((int)Math.pow(5,num))%num==5)&&(((int)Math.pow(7,num))%num==7)*/)
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
  
  EL_Gammal g=new EL_Gammal("weneedmoresnow" );	
 	
 }

 	
}	 