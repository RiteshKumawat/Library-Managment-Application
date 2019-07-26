import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.thinking.machines.library.bl.*;
class AuthorRemove extends JFrame
{
private JTable table;
private JScrollPane jsp;
private AuthorModel authorModel;
private Container c;
private RemovePanel rp;
private JPanel panel; 
AuthorRemove()
{
c=getContentPane();
c.setLayout(new BorderLayout());
authorModel=new AuthorModel();
table=new JTable(authorModel);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
rp=new RemovePanel();
c.add(jsp,BorderLayout.CENTER);
panel=new JPanel();
panel.add(rp);
setLocation(10,10);
c.add(panel,BorderLayout.SOUTH);
setSize(500,600);
setVisible(true);
}
class RemovePanel extends JPanel
{
private JTextField codeTextField;
private JButton removeButton;
RemovePanel()
{
setLayout(new FlowLayout());
codeTextField=new JTextField(10);
removeButton=new JButton("Remove");
add(codeTextField);
add(removeButton);
removeButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
int code=Integer.parseInt(codeTextField.getText().trim());
try
{
authorModel.removeAuthor(code);
JOptionPane.showMessageDialog(AuthorRemove.this,"Author Removed!");
codeTextField.setText(" ");
}catch(BLException e)
{
JOptionPane.showMessageDialog(AuthorRemove.this,e.getMessage());
}
}
});
}
}
public static void main(String hh[])
{

new AuthorRemove();
} 
}