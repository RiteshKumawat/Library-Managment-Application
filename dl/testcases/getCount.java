import com.thinking.machines.library.dl.*;
class getCount
{
public static void main(String gg[])
{
try
{
AuthorDAOInterface adaoi=new AuthorDAO();
long NumberOfAuthors=adaoi.getCount();
System.out.println("Number Of Authors :"+NumberOfAuthors);

}catch(DAOException dao)
{
System.out.println(dao);
}

}
}
