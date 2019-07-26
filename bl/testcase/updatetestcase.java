import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.thinking.machines.library.bl.*;
class AuthorUpdate extends JFrame
{
private JTable table;
private JScrollPane jsp;
private AuthorModel authorModel;
private Container c;
private UpdatePanel rp;
private JPanel panel; 
AuthorUpdate()
{
c=getContentPane();
c.setLayout(new BorderLayout());
authorModel=new AuthorModel();
table=new JTable(authorModel);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
rp=new UpdatePanel();
c.add(jsp,BorderLayout.CENTER);
panel=new JPanel();
panel.add(rp);
setLocation(10,10);
c.add(panel,BorderLayout.SOUTH);
setSize(500,600);
setVisible(true);
}
class UpdatePanel extends JPanel
{
private JTextField codeTextField;
private JTextField nameTextField;
private JButton updateButton;
UpdatePanel()
{
setLayout(new FlowLayout());
codeTextField=new JTextField(5);
nameTextField=new JTextField(10);
updateButton=new JButton("Update");
add(codeTextField);
add(nameTextField);
add(updateButton);
updateButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
int code=Integer.parseInt(codeTextField.getText().trim());
String name=nameTextField.getText().trim();
AuthorInterface authorInterface=new Author();
authorInterface.setName(name);
authorInterface.setCode(code);
try
{
authorModel.updateAuthor(authorInterface);
JOptionPane.showMessageDialog(AuthorUpdate.this,"Author Updated!");
codeTextField.setText(" ");
nameTextField.setText(" ");
}catch(BLException e)
{
JOptionPane.showMessageDialog(AuthorUpdate.this,e.getMessage());
}
}
});
}
}
public static void main(String hh[])
{

new AuthorUpdate();
} 
}