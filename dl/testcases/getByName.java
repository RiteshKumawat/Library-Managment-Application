import com.thinking.machines.library.dl.*;
class getByName
{
public static void main(String gg[])
{

try
{
AuthorDAOInterface adaoi=new AuthorDAO();
AuthorInterface ai=new Author();
ai=adaoi.getByName(gg[0]);
System.out.println("Name Of Author"+ai.getName()+"\n"+"Code Of Author :"+ai.getCode());

}catch(DAOException dao)
{
System.out.println(dao);
}

}
}
