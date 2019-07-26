import com.thinking.machines.library.dl.*;
import java.util.*;
class getAll2
{
public static void main(String gg[])
{

try
{
AuthorDAOInterface adaoi=new AuthorDAO();
LinkedList<AuthorInterface> list=new LinkedList();
list=adaoi.getAll(Author.Name);
for(AuthorInterface ai:list)
System.out.println("Name Of Author:   "+ai.getName()+"   Code Of Author:   "+ai.getCode());

}catch(DAOException dao)
{
System.out.println(dao);
}

}
}
