package com.thinking.machines.library.dl;
public class Author implements AuthorInterface 
{
public static enum ATTRIBUTE{CODE,NAME};
public static ATTRIBUTE Code=ATTRIBUTE.CODE;
public static ATTRIBUTE Name=ATTRIBUTE.NAME;
private int code;
private String name;
public void setName(String name)
{
this.name=name;
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public String getName()
{
return this.name;
}
public boolean equals(Object object)
{
if(!(object instanceof AuthorInterface))return false;
AuthorInterface otherAuthor;
otherAuthor=(AuthorInterface)object;
return this.code==otherAuthor.getCode();
}
public int compareTo(AuthorInterface otherAuthor)
{
return this.code-otherAuthor.getCode();
}
}