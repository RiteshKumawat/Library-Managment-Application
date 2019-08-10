package com.thinking.machines.library.dl;
import java.util.*;//because of "Linked List";
import java.io.*;
public class AuthorDAO implements AuthorDAOInterface
{
public static String FileName="Author.data";
 public void add(AuthorInterface authorInterface)throws DAOException
 {
  try
 {
 File f=new File(FileName);
 RandomAccessFile randomAccessFile=new RandomAccessFile(f,"rw");
 int code=1;
 String Vname;
 
if(randomAccessFile.length()==0)
  {
   randomAccessFile.writeBytes(String.format("%-10s",code));
   randomAccessFile.writeBytes("\n");
   randomAccessFile.writeBytes(String.format("%-10s",code));
   randomAccessFile.writeBytes("\n");
   randomAccessFile.writeBytes(String.valueOf(code));
   randomAccessFile.writeBytes("\n");
   randomAccessFile.writeBytes(authorInterface.getName());
   randomAccessFile.writeBytes("\n");
   authorInterface.setCode(code);
    randomAccessFile.close();
   return;
  }
    String lastAdded=randomAccessFile.readLine();
    String NumberOfAuthor=randomAccessFile.readLine();
    while(randomAccessFile.getFilePointer()<randomAccessFile.length())
    {
    randomAccessFile.readLine();
    Vname=randomAccessFile.readLine();
    if(Vname.equalsIgnoreCase(authorInterface.getName()))
     {
      randomAccessFile.close();
      throw new DAOException("That Name is Alloted Already!");
          
      }
    }
      int lA=Integer.parseInt(lastAdded.trim());
      lA++;
      int NOA=Integer.parseInt(NumberOfAuthor.trim());
      NOA++;
      code=lA;
        
 randomAccessFile.writeBytes(String.valueOf(code));
      randomAccessFile.writeBytes("\n");
      randomAccessFile.writeBytes(authorInterface.getName());
      randomAccessFile.writeBytes("\n");
      randomAccessFile.seek(0);
      randomAccessFile.writeBytes(String.format("%-10s",lA));
      randomAccessFile.writeBytes("\n");
      randomAccessFile.writeBytes(String.format("%-10s",NOA));
      randomAccessFile.writeBytes("\n");
      randomAccessFile.close();
    authorInterface.setCode(code);  
    
  }catch(Exception ioe)
    {    	
ioe.printStackTrace();
    }
} 
 public void update(AuthorInterface authorInterface)throws DAOException
 {
    try
     {
     File file=new File(FileName);
     if(file.exists()==false)
      {
     throw new DAOException("Author is not Exist");
     }
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
      if(randomAccessFile.length()==0)
       { 
      randomAccessFile.close();
       throw new DAOException("Invalid Code");
       }
     boolean nameFound,codeFound;
nameFound=codeFound=false;  
 randomAccessFile.readLine();
   randomAccessFile.readLine();
 while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
int Vcode=Integer.parseInt(randomAccessFile.readLine());
String Vname=randomAccessFile.readLine();
if(Vcode==authorInterface.getCode())
{
codeFound=true;
}
if(Vcode!=authorInterface.getCode()&&authorInterface.getName().equalsIgnoreCase(Vname))
{
nameFound=true;
}
}
if(codeFound==false)
{
throw new DAOException("Invalid Code");
}
if(nameFound==true)
{
throw new DAOException("This name exists already.");
}
randomAccessFile.seek(0);
File tmpFile=new File("tmpFile.data");
if(tmpFile.exists())
{
tmpFile.delete();
}
RandomAccessFile tmprandomAccessFile=new RandomAccessFile(tmpFile,"rw");
tmprandomAccessFile.writeBytes(randomAccessFile.readLine());
tmprandomAccessFile.writeBytes("\n");
tmprandomAccessFile.writeBytes(randomAccessFile.readLine());
tmprandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
int Vcode=Integer.parseInt(randomAccessFile.readLine());
String Vname=randomAccessFile.readLine();
if(authorInterface.getCode()!=Vcode)
{
tmprandomAccessFile.writeBytes(Vcode+"\n"+Vname+"\n");
}
else
{
tmprandomAccessFile.writeBytes(Vcode+"\n"+authorInterface.getName()+"\n");
}
}
randomAccessFile.seek(0);
tmprandomAccessFile.seek(0);
while(tmprandomAccessFile.getFilePointer()<tmprandomAccessFile.length())
{
randomAccessFile.writeBytes(tmprandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmprandomAccessFile.length());
tmprandomAccessFile.setLength(0);
randomAccessFile.close();
randomAccessFile.close();       
}
  catch(IOException io)
   {
   throw new DAOException(io.getMessage());
    }
}
 public void delete(int code)throws DAOException
 { 
  try
     {
     File file=new File(FileName);
     if(file.exists()==false)
       {
       throw new DAOException("Author is not Exist");
       }
        RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
         if(randomAccessFile.length()==0)
          { 
          randomAccessFile.close();
         throw new DAOException("Invalid Code");
          }
           boolean codeFound=false;
              randomAccessFile.readLine();
              randomAccessFile.readLine();
          while(randomAccessFile.getFilePointer()<randomAccessFile.length())
          {
          int Vcode=Integer.parseInt(randomAccessFile.readLine());
           randomAccessFile.readLine();
           if(Vcode==code)
            {
            codeFound=true;
            }
           }
          if(codeFound==false)
            {
            throw new DAOException("Invalid Code");
            }
          randomAccessFile.seek(0);
          File tmpFile=new File("tmpFile.data");
           if(tmpFile.exists())
            {
            tmpFile.delete();
            }
RandomAccessFile tmprandomAccessFile=new RandomAccessFile(tmpFile,"rw");
tmprandomAccessFile.writeBytes(randomAccessFile.readLine());
tmprandomAccessFile.writeBytes("\n");
int noa=Integer.parseInt(randomAccessFile.readLine().trim());
noa--;
tmprandomAccessFile.writeBytes(String.format("%-10s",noa)+"\n");
     while(randomAccessFile.getFilePointer()<randomAccessFile.length())
         {
        int Vcode=Integer.parseInt(randomAccessFile.readLine());
        String Vname=randomAccessFile.readLine();
        if(code!=Vcode)
              {
               tmprandomAccessFile.writeBytes(Vcode+"\n"+Vname+"\n");
              }
         }
       randomAccessFile.seek(0);
       tmprandomAccessFile.seek(0);
     while(tmprandomAccessFile.getFilePointer()<tmprandomAccessFile.length())
      {
       randomAccessFile.writeBytes(tmprandomAccessFile.readLine()+"\n");
     }
      randomAccessFile.setLength(tmprandomAccessFile.length());
       tmprandomAccessFile.setLength(0);
        randomAccessFile.close();
         randomAccessFile.close();       
  }
  catch(IOException io)
   {
   throw new DAOException(io.getMessage());
    }
}
public LinkedList<AuthorInterface> getAll() throws DAOException
 {
 try{

LinkedList<AuthorInterface> list=new LinkedList<AuthorInterface>();
File file=new File(FileName);
if(!(file.exists()))
{
throw new DAOException("Author is Not Exist");
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
throw new DAOException("Invalid Code");
}

String lastGenerated=randomAccessFile.readLine();
String NumberOfRecords=randomAccessFile.readLine();
  while(randomAccessFile.getFilePointer()<randomAccessFile.length())
   {
int Vcode=Integer.parseInt(randomAccessFile.readLine());
String Vname=randomAccessFile.readLine();
AuthorInterface ai=new Author();
ai.setCode(Vcode);
ai.setName(Vname);
list.add(ai);
}
Collections.sort(list);
return list;
  }catch(IOException ioe)
         {
         throw new DAOException(ioe.getMessage());
         }
 }

public LinkedList<AuthorInterface> getAll(Author.ATTRIBUTE sortBy) throws DAOException
 {
 try{
if(sortBy==Author.ATTRIBUTE.CODE)
{
return getAll();
}
LinkedList<AuthorInterface> list=new LinkedList<AuthorInterface>();
File file=new File(FileName);
if(!(file.exists()))
{
throw new DAOException("Author is Not Exist");
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
throw new DAOException("Invalid Code");
}

String lastGenerated=randomAccessFile.readLine();
String NumberOfRecords=randomAccessFile.readLine();
  while(randomAccessFile.getFilePointer()<randomAccessFile.length())
   {
int Vcode=Integer.parseInt(randomAccessFile.readLine());
String Vname=randomAccessFile.readLine();
AuthorInterface ai=new Author();
ai.setCode(Vcode);
ai.setName(Vname);
list.add(ai);
}
Collections.sort(list,new Comparator<AuthorInterface>()//An Anonymous Class Created Because of Comparing By name.
                         {                            //In this class I'm Defining a Function (Compare) for Sort The List.
                           public int compare(AuthorInterface a1,AuthorInterface a2)
                             {

                          return a1.getName().toUpperCase().compareTo(a2.getName().toUpperCase());

                              }
                                                                                        
                          });   
return list;

}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}
 }
 

 public AuthorInterface getByCode(int code)throws DAOException
{ 
 
  try
 {
  File file=new File(FileName);
  if(file.exists()==false)
   {
    throw new DAOException("Authors Are Not Exist");
    }
   RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
   if(randomAccessFile.length()==0)
   {
   throw new DAOException("Authors are not Exist");
   }
randomAccessFile.readLine();
randomAccessFile.readLine();
boolean found=false;
String name=" ";
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
int Vcode=Integer.parseInt(randomAccessFile.readLine().trim());
String Vname=randomAccessFile.readLine();
if(Vcode==code)
{
found=true;
name=Vname;
}
}
if(found==false)
{
throw new DAOException("Not Found.!");
}
Author au=new Author();
au.setCode(code);
au.setName(name);
return (AuthorInterface)au;
}catch(IOException io)
{
throw new DAOException(io.getMessage());
}

}
 public AuthorInterface getByName(String name)throws DAOException
 {
   try
   {
   File file=new File(FileName);
   if(file.exists()==false)
     {
    throw new DAOException("Authors Are Not Exist");
      }
 RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
   if(randomAccessFile.length()==0)
        {
        throw new DAOException("Authors are not Exist");
         }
         randomAccessFile.readLine();
        randomAccessFile.readLine();
         boolean found=false;
       int code=0;      
     while(randomAccessFile.getFilePointer()<randomAccessFile.length())
        {
     int Vcode=Integer.parseInt(randomAccessFile.readLine().trim());
     String Vname=randomAccessFile.readLine();
     if(name.equalsIgnoreCase(Vname))
      {
      found=true;
      code=Vcode;
       }
        }
   if(found==false)
     {
    throw new DAOException("Not Found.!");
      }
Author a1=new Author();
      a1.setName(name);
      a1.setCode(code);
 
      return (AuthorInterface)a1;
 
     
    }catch(IOException io)
      {
     throw new DAOException(io.getMessage());
        }
             
}


public long getCount() throws DAOException
 {
 try
{
File file=new File(FileName);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(!(file.exists()))
{
throw new DAOException("Authors are not exist");
}
if(randomAccessFile.length()==0)
{
throw new DAOException("Authors are not exist");
}
randomAccessFile.readLine();
return Long.parseLong(randomAccessFile.readLine().trim());
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}
 }
}
