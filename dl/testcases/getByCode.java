import com.thinking.machines.library.dl.*;
class getByCode
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);

try
{
AuthorDAOInterface adaoi=new AuthorDAO();
AuthorInterface ai=new Author();
ai=adaoi.getByCode(code);
System.out.println("Name Of Author"+ai.getName()+"\n"+"Code Of Author :"+ai.getCode());

}catch(DAOException dao)
{
System.out.println(dao);
}

}
}
