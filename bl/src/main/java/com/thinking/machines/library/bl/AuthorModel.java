package com.thinking.machines.library.bl;
import java.util.*;
import javax.swing.table.*;
public class AuthorModel extends AbstractTableModel
{
private String [] title={"S.No","Author"};
private LinkedList<AuthorInterface> authorsLinkedList;

public int getColumnCount()
{
return title.length;
}

public AuthorModel()
{
initializeDataStructure();
}

public void initializeDataStructure()
{
LinkedList<com.thinking.machines.library.dl.AuthorInterface> dlAuthorsLinkedList;
com.thinking.machines.library.dl.AuthorDAOInterface authorDAO;
authorDAO=new com.thinking.machines.library.dl.AuthorDAO();
try
{
dlAuthorsLinkedList=authorDAO.getAll(com.thinking.machines.library.dl.Author.Name);
authorsLinkedList=new LinkedList<AuthorInterface>();
AuthorInterface authorInterface;
for(com.thinking.machines.library.dl.AuthorInterface dlAuthor:dlAuthorsLinkedList)
 {
  authorInterface=new Author();
  authorInterface.setCode(dlAuthor.getCode());
  authorInterface.setName(dlAuthor.getName());
  authorsLinkedList.add(authorInterface);
 
 }
}catch(com.thinking.machines.library.dl.DAOException daoException)
{
 authorsLinkedList=new LinkedList<AuthorInterface>();
 }

}

public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0)return rowIndex+1;
return authorsLinkedList.get(rowIndex).getName();
}

public int getRowCount()
{
return authorsLinkedList.size();
}

public String getColumnName(int columnIndex)
{
return title[columnIndex];
}

public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}

public Class getColumnClass(int columnIndex)
{
if(columnIndex==0)return Integer.class;
return String.class;
}

public void addAuthor(AuthorInterface authorInterface)throws BLException
{
for(AuthorInterface vAuthorInterface:authorsLinkedList)
{
if(vAuthorInterface.getName().equalsIgnoreCase(authorInterface.getName()))
{
throw new BLException("Author :"+authorInterface.getName()+"Exists");
}
}
com.thinking.machines.library.dl.AuthorInterface dlAuthorInterface;
dlAuthorInterface =new com.thinking.machines.library.dl.Author();
dlAuthorInterface.setName(authorInterface.getName());
try
{
new com.thinking.machines.library.dl.AuthorDAO().add(dlAuthorInterface);
authorInterface.setCode(dlAuthorInterface.getCode());
int e=0;
while(e<authorsLinkedList.size())
{
if(authorsLinkedList.get(e).getName().toUpperCase().compareTo(authorInterface.getName().toUpperCase())>0)
{
break;
}
e++;
}
authorsLinkedList.add(e,authorInterface);
Collections.sort(authorsLinkedList);
fireTableDataChanged();
}catch(com.thinking.machines.library.dl.DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}

public void updateAuthor(AuthorInterface authorInterface)throws BLException
{
boolean foundCode=false;
int authorIndex=0;
for(AuthorInterface vAuthorInterface:authorsLinkedList)
{
if(vAuthorInterface.getCode()==authorInterface.getCode())
{
foundCode=true;
break;
}
authorIndex++;
}
if(foundCode==false)
{
throw new BLException("Invalid author Code : "+authorInterface.getCode());
}
boolean foundName=false;
for(AuthorInterface vAuthorInterface:authorsLinkedList)
{
if(vAuthorInterface.getCode()!=authorInterface.getCode()&&vAuthorInterface.getName().equalsIgnoreCase(authorInterface.getName()))
{
foundName=true;
break;
}
}
if(foundName)
{
throw new BLException("Author : "+authorInterface.getName()+" exists.");
}
com.thinking.machines.library.dl.AuthorInterface dlAuthorInterface;
dlAuthorInterface=new com.thinking.machines.library.dl.Author();
dlAuthorInterface.setCode(authorInterface.getCode());
dlAuthorInterface.setName(authorInterface.getName());
try
{
new com.thinking.machines.library.dl.AuthorDAO().update(dlAuthorInterface);
authorsLinkedList.remove(authorIndex);
int e=0;
while(e<authorsLinkedList.size())
{
if(authorsLinkedList.get(e).getName().toUpperCase().compareTo(authorInterface.getName().toUpperCase())>0)
{
break;
}
e++;
}
authorsLinkedList.add(e,authorInterface);
Collections.sort(authorsLinkedList);
fireTableDataChanged();
}catch(com.thinking.machines.library.dl.DAOException daoException)
{
throw new BLException(daoException.getMessage());
}
}

public void removeAuthor(int code)throws BLException
{
boolean foundCode=false;
int authorIndex=0;
for(AuthorInterface vAuthorInterface:authorsLinkedList)
{
if(vAuthorInterface.getCode()==code)
{
foundCode=true;
break;
}
authorIndex++;
}
if(foundCode==false)
{
throw new BLException("Invalid author Code : "+code);
}
try
{
new com.thinking.machines.library.dl.AuthorDAO().delete(code);
authorsLinkedList.remove(authorIndex);
fireTableDataChanged();
}catch(com.thinking.machines.library.dl.DAOException daoException)
{
throw new BLException(daoException.getMessage());
}
}

public int getIndexOf(AuthorInterface authorInterface)throws BLException
{
boolean foundCode=false;
int authorIndex=0;
for(AuthorInterface vAuthorInterface:authorsLinkedList)
{
if(vAuthorInterface.getCode()==authorInterface.getCode())
{
foundCode=true;
break;
}
authorIndex++;
}
if(foundCode==false)
{
throw new BLException("Invalid authorCode : "+authorInterface.getCode());
}
return authorIndex;
} 

public AuthorInterface getAuthorAt(int index)throws BLException
{
if(index<0||index>=authorsLinkedList.size())throw new BLException("Index out of Bounds :"+index);
return authorsLinkedList.get(index);
}
 
public AuthorInterface AuthorByName(String name,boolean compareLeft,boolean ComparableInCaseSensitive)throws BLException
{
if(compareLeft && ComparableInCaseSensitive)
{
AuthorInterface authorInterface;
String vName;
for(int i=0;i<authorsLinkedList.size();i++)
{
authorInterface=authorsLinkedList.get(i);
vName=authorInterface.getName();
if(vName.toUpperCase().startsWith(name.toUpperCase()))return authorInterface;
}
}
if(compareLeft && ComparableInCaseSensitive==false)
{
AuthorInterface authorInterface;
String vName;
for(int i=0;i<authorsLinkedList.size();i++)
{
authorInterface=authorsLinkedList.get(i);
vName=authorInterface.getName();
if(vName.startsWith(name))return authorInterface;
}
}
if(compareLeft==false && ComparableInCaseSensitive==false)
{
AuthorInterface authorInterface;
String vName;
for(int i=0;i<authorsLinkedList.size();i++)
{
authorInterface=authorsLinkedList.get(i);
vName=authorInterface.getName();
if(vName.equals(name))return authorInterface;
}
}
throw new BLException("Invalid Name :"+name);
}
public int getAuthorIndexByName(String name,boolean compareLeft,boolean compareInCaseSensitive)throws BLException
{
 if(compareLeft && compareInCaseSensitive==false)
 {
AuthorInterface authorInterface;
String vName;
for(int i=0;i<authorsLinkedList.size();i++)
{
authorInterface=authorsLinkedList.get(i);
vName=authorInterface.getName();

if(vName.toUpperCase().startsWith(name.toUpperCase()))return i;
}
}
if(compareLeft && compareInCaseSensitive)
{
AuthorInterface authorInterface;
String vName;
for(int i=0;i<authorsLinkedList.size();i++)
{
authorInterface=authorsLinkedList.get(i);
vName=authorInterface.getName();
if(vName.startsWith(name))return i;
}
}
if(compareLeft==false && compareInCaseSensitive==false)
{
AuthorInterface authorInterface;
String vName;
for(int i=0;i<authorsLinkedList.size();i++)
{
authorInterface=authorsLinkedList.get(i);
vName=authorInterface.getName();
if(vName.equals(name))return i;
}
}
throw new BLException("Invalid Name :"+name);

}
}