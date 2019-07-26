import com.thinking.machines.library.dl.*;
class add
{
public static void main(String gg[])
{
 AuthorDAO adao=new AuthorDAO(); 
 Author au=new Author();
 au.setName(gg[0]);

 try
 {
 adao.add(au);
 System.out.println("Author Code is:"+au.getCode());
  }catch(Throwable dao)
   {
   dao.printStackTrace();
   }
 }
}