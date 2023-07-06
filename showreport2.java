import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.lang.String.*;
import java.util.Properties;
import java.awt.event.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.*;


public class showreport2 extends JFrame implements ActionListener
    {
	 JLabel name;
     JTable table;
     JScrollPane jsp;
	 String data[][];
	 int vn,am;
	 JButton back,print;
	 JLabel lvtype,lamt;
	 JTextField tvtype,tamt;
	 
	 showreport2(String data[][])  //
	 {
	  setTitle("Report");
	  setSize(650,400);
	  setLocation(200,80);
	  setLayout(null);
	  setResizable(false);
	  getContentPane().setBackground(Color.cyan);
	  
      name=new JLabel("Bill Report");
	  lvtype=new JLabel("Total Records");
	  lamt=new JLabel("Total Amount");
	  tvtype=new JTextField(20);
	  tamt=new JTextField(20);
	  
	  add(name);
	  add(lamt);
	  add(lvtype);
	  add(tamt);
	  add(tvtype);
	  
	  name.setFont(new Font("Lucida Calligraphy",Font.ITALIC,30));
	  lvtype.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
      lamt.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
      
	  print=new JButton("Print");	  
	  print.setMnemonic('P');
	  add(print);
	  print.setBounds(350,330,90,25);
	  
	  back=new JButton("Back");	  
	  back.setMnemonic('B');
	  add(back);
	  back.setBounds(450,330,90,25);	
	  
	  name.setBounds(230,20,250,30);
	  lvtype.setBounds(30,70,150,20);
      tvtype.setBounds(190,70,90,20);
      lamt.setBounds(400,70,120,20);
      tamt.setBounds(530,70,90,20);	
	  
	  tvtype.setEditable(false);
	  tamt.setEditable(false);
	  
	  tvtype.setText(""+vn);//
	  tamt.setText(""+am);//
	  
	  back.setFont(new Font("Lucida Calligraphy",Font.BOLD,13));
      print.setFont(new Font("Lucida Calligraphy",Font.BOLD,13));
	  
	  String cname[]={ "Bill_no", "N_ID", "N_NAME", "TOTAL_AMT", "BILL_DATE"};
	   
	  table=new JTable(data,cname);
	  
	  int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
      int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
	  add(table);
	  DefaultTableColumnModel colModel=(DefaultTableColumnModel)table.getColumnModel();
			TableColumn col=colModel.getColumn(1);
			col=colModel.getColumn(0);        col.setPreferredWidth(60);
			col=colModel.getColumn(1);        col.setPreferredWidth(60);
			col=colModel.getColumn(2);        col.setPreferredWidth(180);
			col=colModel.getColumn(3);        col.setPreferredWidth(100);
			col=colModel.getColumn(4);        col.setPreferredWidth(110);
			
	  table.setRowHeight(20);
	  jsp=new JScrollPane(table,v,h);
	  jsp.setBounds(50,110,500,200);
	  add(jsp);	
	  
	  
	  back.addActionListener(this);
      print.addActionListener(this);	
	  
	  setVisible(true);
	  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 }
	public void actionPerformed(ActionEvent ae)
	 {
	  if(ae.getSource()==back)
	   {
	    dispose();
	   }
	   
	   if(ae.getSource()==print)
	   {
	    try
      {  
	  MessageFormat header = new MessageFormat("Bill Report");
          MessageFormat footer = new MessageFormat("Page {0,number,integer}");
          table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		  //getToolkit().getPrintJob(this,"Print Page",new Properties());
      }
      catch(Exception exp)
      {   exp.printStackTrace();
      } }
	 
	 }
	public static void main(String args[])
	{
		String data[][]=new String[0][0];
		new showreport2(data);
	}


}