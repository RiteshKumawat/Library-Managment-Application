import com.thinking.machines.library.dl.*;
class update
{
public static void main(String gg[])
{
Author au=new Author();
au.setCode(Integer.parseInt(gg[0]));
au.setName(gg[1]);
try
{
AuthorDAO adao=new AuthorDAO();
adao.update(au);
System.out.println("Author Updated !");
System.out.println("Name is :"+au.getName());
System.out.println("Code is :"+au.getCode());
}
catch(DAOException dao)
{
System.out.println(dao);
}

}
}