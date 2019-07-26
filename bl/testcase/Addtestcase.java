import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.thinking.machines.library.bl.*;
class AuthorModelTestCase extends JFrame
{
private JTable table;
private JScrollPane jsp;
private AuthorModel authorModel;
private Container c;
private AddPanel ap;
private JPanel panel; 
AuthorModelTestCase()
{
c=getContentPane();
c.setLayout(new BorderLayout());
authorModel=new AuthorModel();
table=new JTable(authorModel);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
ap=new AddPanel();
c.add(jsp,BorderLayout.CENTER);
panel=new JPanel();
panel.add(ap);
setLocation(10,10);
c.add(panel,BorderLayout.SOUTH);
setSize(500,600);
setVisible(true);
}
class AddPanel extends JPanel
{
private JTextField nameTextField;
private JButton addButton;
AddPanel()
{
setLayout(new FlowLayout());
nameTextField=new JTextField(20);
addButton=new JButton("Add");
add(nameTextField);
add(addButton);
addButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
String name=nameTextField.getText();
AuthorInterface authorInterface=new Author();
authorInterface.setName(name);
try
{
authorModel.addAuthor(authorInterface);
JOptionPane.showMessageDialog(AuthorModelTestCase.this,"Author added");
nameTextField.setText(" ");
}catch(BLException e)
{
JOptionPane.showMessageDialog(AuthorModelTestCase.this,e.getMessage());
}
}
});
}
}
public static void main(String hh[])
{

new AuthorModelTestCase();
} 
}