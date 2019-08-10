import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.thinking.machines.library.bl.*;
class GetAuthor extends JFrame
{
 public static void main(String[] args) {
 	try{
 	AuthorModel am = new AuthorModel();
 	am.getAuthorIndexByName("Somin Ali",true,false);
 	
 }catch(BLException bl)
 {
 	System.out.println(bl.getMessage());
 }
 }
}