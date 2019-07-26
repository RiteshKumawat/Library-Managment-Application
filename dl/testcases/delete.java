import com.thinking.machines.library.dl.*;
class delete
{
public static void main(String ss[])
{
int code=Integer.parseInt(ss[0]);
AuthorDAO adao=new AuthorDAO();
try
{
adao.delete(code);
}catch(DAOException dao)
{
System.out.println(dao);
}
}
}