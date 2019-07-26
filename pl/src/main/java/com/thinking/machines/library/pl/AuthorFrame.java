package com.thinking.machines.library.pl;
import javax.swing.*;
import java.awt.*;
public class AuthorFrame extends JFrame
{
private ImageIcon icon;
private Container container;
private AuthorPanel authorPanel;
public AuthorFrame()
{
initComponent();
setAppearence();
setListener();
}
private void initComponent()
{
setTitle(Application.TITLE);
icon=new ImageIcon("../icons/logo.png");
setIconImage(icon.getImage());
container=getContentPane();
authorPanel=new AuthorPanel();
authorPanel.setBounds(5,5,475,600);
}
private void setAppearence()
{
container.setLayout(null);
container.add(authorPanel);
setSize(500,650);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(d.width/2-getWidth()/2,d.height/2-getHeight()/2);
}
private void setListener()
{
setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}
}