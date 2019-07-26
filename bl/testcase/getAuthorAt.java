import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.thinking.machines.library.bl.*;
class GetAuthorAtIndex extends JFrame
{
private JTable table;
private JScrollPane jsp;
private AuthorModel authorModel;
private Container c;
private GetAuthorAtIndexPanel gaaip;
private JPanel panel; 
private JTextArea textArea;
GetAuthorAtIndex()
{
c=getContentPane();
c.setLayout(new BorderLayout);
authorModel=new AuthorModel();
table=new JTable(authorModel);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
gaaip=new GetAuthorAtIndexPanel();
c.add(jsp,BorderLayout.CENTER);
panel=new JPanel();
panel.add(gaaip);
textArea=new JTextArea();
setLocation(10,10);
c.add(textArea,BorderLayout.NORTH);
c.add(panel,BorderLayout.SOUTH);
setSize(500,600);
GetVisible(true);
}
class GetAuthorAtIndexPanel extends JPanel
{
private JButton getAuthorAtIndexButton;
private JLable s.NoLable;
private JTextField s.NoTextField;
GetAuthorAtIndexPanel()
{
setLayout(new FlowLayout());
s.NoLable=new JLable(" so.no "); 
s.NoTextField=new JTextField(10);
getAuthorAtIndexButton=new JButton("Get AuthorName");
add(s.NoLable);
add(s.NoTextField);
getAuthorAtIndexButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
int so.No=Integer.parseInt(.getText().trim());
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