package com.thinking.machines.library.pl;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import javax.swing.filechooser.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import com.thinking.machines.library.bl.*;
public class AuthorPanel extends JPanel implements DocumentListener,ListSelectionListener
{
private ImageIcon clearIcon;
private static final int VIEW_MODE=1;
private static final int ADD_MODE=2;
private static final int EDIT_MODE=3;
private static final int DELETE_MODE=4;
private static final int EXPORT_TO_PDF_MODE=5;
private int mode;
private JLabel authorTitleLabel;
private JLabel authorSearchLabel;
private JLabel authorNotFoundLabel,localLabel;
private JTextField searchTextField,localTextField;
private JButton clearButton,updateButton,cancelButton,deleteButton,exportToPDFButton;
private JTable authorTable;
private JScrollPane authorTableScrollPane;
private JPanel buttonPanel,buttonPanel1;
private AuthorModel authorModel;
private AuthorCRUDPanel authorCRUDPanel;
public AuthorPanel()
{
initComponents();
setAppearance();
addListener();
this.setViewMode();
authorCRUDPanel.setViewMode();
}
private void initComponents()
{
clearIcon=new ImageIcon("clearIcon.png");
authorModel =new AuthorModel();
authorTitleLabel=new JLabel("AUTHOR");
authorSearchLabel =new JLabel("Search");
authorNotFoundLabel =new JLabel(" ");
searchTextField=new JTextField();
clearButton=new JButton(clearIcon);
authorTable=new JTable(authorModel);
authorTable.getColumnModel().getColumn(0).setPreferredWidth(70-20);
authorTable.getColumnModel().getColumn(1).setPreferredWidth(450);
authorTable.setRowHeight(30);
java.awt.Font tableHeaderFont=new java.awt.Font("Verdana",java.awt.Font.BOLD,14);
authorTable.getTableHeader().setFont(tableHeaderFont);
authorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
authorTableScrollPane=new JScrollPane(authorTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
authorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
authorCRUDPanel=new AuthorCRUDPanel();
 setLayout(null);
int lm=0;
int tm=0;
authorTitleLabel.setBounds(lm+5,tm+5,100,40);
authorSearchLabel.setBounds(lm+5,tm+5+40+5,50,30);
searchTextField.setBounds(lm+5+50+5,tm+50,350,20+5+5);
clearButton.setBounds(lm+5+50+2+350+5,tm+50,30,30);
authorNotFoundLabel.setBounds(lm+5+50+5+350-60,tm+20+10,60,20);
authorTableScrollPane.setBounds(lm+5,tm+5+40+5+30+5,465,300);
authorCRUDPanel.setBounds(lm+5,tm+5+40+5+30+5+305,465,200);
}
private void setAppearance()
{
java.awt.Font titleFont=new java.awt.Font("Verdana",java.awt.Font.BOLD,18);
authorTitleLabel.setFont(titleFont);

java.awt.Font generalFont=new java.awt.Font("Vardana",java.awt.Font.PLAIN,14);
authorSearchLabel.setFont(generalFont);

java.awt.Font errorFont=new java.awt.Font("Verdana",java.awt.Font.BOLD,10);
authorNotFoundLabel.setFont(errorFont);
authorNotFoundLabel.setForeground(Color.red);

java.awt.Font tableHeaderFont =new java.awt.Font("Verdana",java.awt.Font.BOLD,14);
authorTable.getTableHeader().setFont(tableHeaderFont);
add(authorTitleLabel);
add(authorSearchLabel);
add(clearButton);
add(searchTextField);
add(authorCRUDPanel);
add(authorNotFoundLabel);
add(authorTableScrollPane);
}
private void addListener()
{
authorTable.getSelectionModel().addListSelectionListener(this);
searchTextField.getDocument().addDocumentListener(this);
clearButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e)
{
searchTextField.setText("");
searchTextField.requestFocus();
}
});
}
private void search()
{
authorNotFoundLabel.setText(" ");
String leftPartOfNameToSearch=searchTextField.getText().trim();
if(leftPartOfNameToSearch.length()==0) return;
try
{
int index=authorModel.getAuthorIndexByName(leftPartOfNameToSearch,true,true);
authorTable.setRowSelectionInterval(index,index);
authorTable.scrollRectToVisible(new java.awt.Rectangle(authorTable.getCellRect(index,0,true)));
}catch(BLException e)
{
authorNotFoundLabel.setText("Not Found");
}
}
public void changedUpdate(DocumentEvent de)
{
search();
}
public void removeUpdate(DocumentEvent de)
{
search();
}
public void insertUpdate(DocumentEvent de)
{
search();
}
void setViewMode()
{
this.mode=VIEW_MODE;
if(authorModel.getRowCount()==0)
{
searchTextField.setText(" ");
searchTextField.setEnabled(false);
clearButton.setEnabled(false);
authorTable.setEnabled(false);
}
else
{
authorSearchLabel.setEnabled(true);
searchTextField.setEnabled(true);
clearButton.setEnabled(true);
authorTable.setEnabled(true);
}
}
public void setAddMode()
{
this.mode=ADD_MODE;
authorSearchLabel.setEnabled(true);
searchTextField.setEnabled(false);
clearButton.setEnabled(false);
authorTable.setEnabled(false);
}
public void setEditMode()
{
this.mode=EDIT_MODE;
authorSearchLabel.setEnabled(true);
searchTextField.setEnabled(true);
clearButton.setEnabled(true);
authorTable.setEnabled(true);
}
public void setDeleteMode()
{
this.mode=DELETE_MODE;
authorSearchLabel.setEnabled(true);
searchTextField.setEnabled(false);
clearButton.setEnabled(false);
authorTable.setEnabled(false);
}
public void setExportToPDFMode()
{
this.mode=EXPORT_TO_PDF_MODE;
authorSearchLabel.setEnabled(true);
searchTextField.setEnabled(false);
clearButton.setEnabled(false);
authorTable.setEnabled(false);
}
public void valueChanged(ListSelectionEvent ev)
{
int selectedRow=authorTable.getSelectedRow();
if(selectedRow<0||selectedRow>authorModel.getRowCount())
{
authorCRUDPanel.clear();
return;
}
try
{
AuthorInterface selectedAuthor=authorModel.getAuthorAt(selectedRow);
authorCRUDPanel.setAuthor(selectedAuthor);
}catch(BLException blException)
{
authorCRUDPanel.clear();
}
}
public class AuthorCRUDPanel extends JPanel implements ActionListener
{
private ImageIcon addIcon;
private ImageIcon editIcon;
private ImageIcon deleteIcon;
private ImageIcon cancelIcon;
private ImageIcon pdfIcon;
private ImageIcon saveIcon;
private ImageIcon clearIcon;
private JLabel authorLabel;
private JLabel authorCaptionLabel;
private JTextField authorTextField;
private JButton addButton;
private JButton editButton;
private JButton cancelButton;
private JButton clearAuthorTextFieldButton;
private JButton deleteButton;
private JButton exportToPDFButton;
private JPanel buttonsPanel;
private AuthorInterface author;
AuthorCRUDPanel()
{
initComponents();
setAppearance();
addListener();
}
private void initComponents()
{
addIcon=new ImageIcon("addIcon.png");
editIcon = new ImageIcon("editIcon.png");
deleteIcon=new ImageIcon("deleteIcon.png");
saveIcon=new ImageIcon("saveIcon.png");
cancelIcon=new ImageIcon("cancelIcon.png");
pdfIcon=new ImageIcon("pdfIcon.png");
clearIcon=new ImageIcon("clearIcon.png");
authorCaptionLabel=new JLabel("Author:");
authorLabel=new JLabel(" ");
authorTextField=new JTextField();
clearAuthorTextFieldButton=new JButton(clearIcon);
buttonsPanel=new JPanel();
addButton=new JButton(addIcon);
editButton=new JButton(editIcon);
cancelButton=new JButton(cancelIcon);
deleteButton=new JButton(deleteIcon);
exportToPDFButton=new JButton(pdfIcon);
buttonsPanel.setLayout(null);
}
private void setAppearance()
{
java.awt.Font font=new java.awt.Font("Verdana",java.awt.Font.PLAIN,14);
authorLabel.setFont(font);
authorCaptionLabel.setFont(font);
authorTextField.setFont(font);
setLayout(null);

int lm=0;
int tm=5;
authorCaptionLabel.setBounds(lm+5,tm+5,65,30);
authorLabel.setBounds(lm+5+65+5,tm+5,300,30);
authorTextField.setBounds(lm+5+60+5,tm+5,350,30);
clearAuthorTextFieldButton.setBounds(lm+5+5+60+5+350+2,tm+5,30,30);
buttonsPanel.setBounds(465/2-310/2,tm+5+30+40,310,70);
addButton.setBounds(10,10,50,50);
editButton.setBounds(70,10,50,50);
cancelButton.setBounds(130,10,50,50);
cancelButton.setEnabled(false);
deleteButton.setBounds(190,10,50,50);
exportToPDFButton.setBounds(250,10,50,50);
buttonsPanel.add(addButton);
buttonsPanel.add(editButton);
buttonsPanel.add(cancelButton);
buttonsPanel.add(exportToPDFButton);
buttonsPanel.add(deleteButton);
buttonsPanel.setBorder(BorderFactory.createLineBorder(new Color(122,138,153)));
add(authorCaptionLabel);
add(authorTextField);
add(buttonsPanel);
add(clearAuthorTextFieldButton);
}
private void addListener()
{
addButton.addActionListener(this);
editButton.addActionListener(this);
cancelButton.addActionListener(this);
deleteButton.addActionListener(this);
exportToPDFButton.addActionListener(this);
}

public void setViewMode()
{
authorLabel.setVisible(true);
authorTextField.setVisible(false);
clearAuthorTextFieldButton.setVisible(false);
addButton.setEnabled(true);
cancelButton.setEnabled(false);
addButton.setIcon(addIcon);
editButton.setIcon(editIcon);
if(authorModel.getRowCount()==0)
{
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
else
{
editButton.setEnabled(true);
deleteButton.setEnabled(true);
exportToPDFButton.setEnabled(true);
}
}
public void setAddMode()
{
addButton.setIcon(saveIcon);
authorCaptionLabel.setVisible(true);
authorTextField.setVisible(true);
authorLabel.setVisible(false);
clearAuthorTextFieldButton.setEnabled(true);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
cancelButton.setEnabled(true);
exportToPDFButton.setEnabled(false);
authorTextField.setText(" ");
authorTextField.setEditable(true);
}

public void setEditMode()
{
authorTextField.setText(author.getName());
authorCaptionLabel.setVisible(false);
authorTextField.setVisible(true);
clearAuthorTextFieldButton.setVisible(true);
editButton.setIcon(saveIcon);
editButton.setEnabled(true);
deleteButton.setEnabled(false);
cancelButton.setEnabled(true);
exportToPDFButton.setEnabled(false);
}

public void setDeleteMode()
{
addButton.setEnabled(false);
cancelButton.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}

public void setExportToPDFMode()
{
addButton.setEnabled(false);
cancelButton.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
public void setAuthor(AuthorInterface author)
{
this.author=author;
this.authorLabel.setText(author.getName());
}
public void clear()
{
this.author=null;
this.authorLabel.setText(" ");
}
public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==addButton)
{
if(AuthorPanel.this.mode==VIEW_MODE)
{
AuthorPanel.this.setAddMode();
this.setAddMode();
}
else
{
String name=authorTextField.getText().trim();
try
{
AuthorInterface newAuthor=new Author();
newAuthor.setName(name);
authorModel.addAuthor(newAuthor);
int index=authorModel.getIndexOf(newAuthor);
authorTable.setRowSelectionInterval(index,index);
authorTable.scrollRectToVisible(new java.awt.Rectangle(authorTable.getCellRect(index,0,true)));
AuthorPanel.this.setViewMode();
this.setViewMode();
}catch(BLException blException)
{
JOptionPane.showMessageDialog(AuthorPanel.this,blException.getMessage());
}
}
}else 
if(ev.getSource()==editButton)
{
if(AuthorPanel.this.mode==VIEW_MODE)
{
int selectedRow=authorTable.getSelectedRow();
if(selectedRow<0||selectedRow>=authorModel.getRowCount())
{
JOptionPane.showMessageDialog(AuthorPanel.this,"Select Author to edit");
return;
}
AuthorPanel.this.setEditMode();
this.setEditMode();
}
else
{
String name=authorTextField.getText().trim();
try
{
AuthorInterface newAuthor =new Author();
newAuthor.setCode(author.getCode());
newAuthor.setName(name);
authorModel.updateAuthor(newAuthor);
int index=authorModel.getIndexOf(newAuthor);
authorTable.setRowSelectionInterval(index,index);
authorTable.scrollRectToVisible(new java.awt.Rectangle(authorTable.getCellRect(index,0,true)));
AuthorPanel.this.setViewMode();
this.setViewMode();
}catch(BLException blException)
{
JOptionPane.showMessageDialog(AuthorPanel.this,blException.getMessage());
}
}
}else if(ev.getSource()==deleteButton)
{
int selectedRow=authorTable.getSelectedRow();
if(selectedRow<0||selectedRow>=authorModel.getRowCount())
{
JOptionPane.showMessageDialog(AuthorPanel.this,"Select author to delete");
return;
}
AuthorPanel.this.setDeleteMode();
this.setDeleteMode();
int selectedOption=JOptionPane.showConfirmDialog(AuthorPanel.this,"Delete : "+this.author.getName(),"Confirmatiuon",JOptionPane.YES_NO_OPTION );
if(selectedOption==JOptionPane.NO_OPTION)
{
AuthorPanel.this.setViewMode();
this.setViewMode();
return;
}
try
{
authorModel.removeAuthor(this.author.getCode());
}catch(BLException blException)
{
JOptionPane.showMessageDialog(AuthorPanel.this,blException.getMessage());
}
AuthorPanel.this.setViewMode();
this.setViewMode();
}else
 if(ev.getSource()==cancelButton)
{
AuthorPanel.this.setViewMode();
this.setViewMode();
}else 
if(ev.getSource()==exportToPDFButton)
{
AuthorPanel.this.setExportToPDFMode();
this.setExportToPDFMode();
JFileChooser jfc=new JFileChooser();
jfc.setCurrentDirectory(new File("."));
jfc.setAcceptAllFileFilterUsed(false);
jfc.setFileFilter(new FileNameExtensionFilter("PDF Files","pdf"));
int selectedOption=jfc.showSaveDialog(AuthorPanel.this);
if(selectedOption==jfc.APPROVE_OPTION)
{
File selectedFile=jfc.getSelectedFile();
File parentFolder=new File(selectedFile.getParent());
if(parentFolder.exists()==false)
{
JOptionPane.showMessageDialog(AuthorPanel.this,"Invalid path :"+parentFolder.getAbsolutePath());
return;
}
String selectedFileNameWithPath=selectedFile.getAbsolutePath();
if(selectedFileNameWithPath.endsWith(".pdf")==false)
{
if(selectedFileNameWithPath.endsWith("."))
{
selectedFileNameWithPath+="pdf";
}
else
{
selectedFileNameWithPath+=".pdf";
}
}
String fullPath=selectedFileNameWithPath;
try
{
Document document=new Document();
PdfWriter.getInstance(document,new FileOutputStream(new File(fullPath)));
document.open();
com.itextpdf.text.Font font=new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN,24,com.itextpdf.text.Font.BOLD);
Paragraph paragraph;
float widths[]={1f,5f};
PdfPTable table=null;
com.itextpdf.text.Image img;
PdfPCell cell=null;
java.text.SimpleDateFormat sdf;
sdf=new java.text.SimpleDateFormat("dd/mm/yy (hh:mm:ss)");
java.util.Date today =new java.util.Date();
String todayString=sdf.format(today);
int pageSize=6;
boolean newPage=true;
int pageNumber=0;
int x;
AuthorInterface authorInterface;
x=0;
while(x<authorModel.getRowCount())
{
if(newPage==true)
{
pageNumber++;
paragraph=new Paragraph("ABC Book Store",font);
paragraph.setAlignment(Element.ALIGN_CENTER);
document.add(paragraph);
paragraph=new Paragraph("Page No."+pageNumber);
paragraph.setAlignment(Element.ALIGN_RIGHT);
document.add(paragraph);
document.add(new Paragraph(" "));
img=com.itextpdf.text.Image.getInstance(AuthorPanel.this.getClass().getResource("logo.png"));
img.setAbsolutePosition(50,760);
document.add(img);
table=new PdfPTable(2);
table.setWidths(widths);
cell=new PdfPCell(new Paragraph(" S.No."));
cell.setBackgroundColor(BaseColor.GRAY);
table.addCell(cell);
cell=new PdfPCell(new Paragraph("Authors"));
cell.setBackgroundColor(BaseColor.GRAY);
table.addCell(cell);
}
newPage=false;
authorInterface=authorModel.getAuthorAt(x);
cell=new PdfPCell(new Paragraph(String.valueOf(x+1)));
table.addCell(cell);
cell=new PdfPCell(new Paragraph(authorInterface.getName()));
table.addCell(cell);
x++;
if(x%pageSize==0||x==authorModel.getRowCount())
{
document.add(table);
paragraph=new Paragraph("Generated on : "+todayString);
document.add(paragraph);
paragraph=new Paragraph("Software by :Thinking Machines");
document.add(paragraph);
if(x<authorModel.getRowCount());
{
document.newPage();
newPage=true;
}
}
}
document.close();
JOptionPane.showMessageDialog(AuthorPanel.this,"Pdf file : "+fullPath+" Created.."); 
}catch(Exception exception)
{
System.out.println(exception);
JOptionPane.showMessageDialog(AuthorPanel.this,"Unable to created pdf File : "+fullPath);
}
}
AuthorPanel.this.setViewMode();
this.setViewMode();
}
}
}
}
