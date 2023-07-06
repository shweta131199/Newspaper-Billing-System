
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.*;
import java.text.*;



class Bill1 extends JFrame implements ActionListener,ItemListener

{
	JLabel l1,l2,l3,l4,l5;
	JLabel l_menutype,l_menuname,l_quantity,l_price;
	
	
	JComboBox cb_menutype;
	
	JTextField t_quantity,t_price,cb_menuname;
	
	
	JButton b1,b2,b3,b4,b5,b_addbill,b_print,b_clear,b_back,b_confirmbill;
	JTable tab;
	DefaultTableModel mdl;
	
	Object colHead[]={"News_paper","Customer Name","Price","Quantity","Total"};
	Object data[][]={{"","Total Bill = ","","",new Integer(0)}};
	JScrollPane jsp;
	int i=0,rno,tot_val;
	
			Connection cn=null;
			PreparedStatement prstm;
			String sql;
			ResultSet rs;
			Statement stm;
			
	JPanel p,p2,p_bill;

	Font f50=new Font("Serif",Font.BOLD,50);
	Font f25=new Font("Serif",Font.BOLD,25);
	Font f30=new Font("Serif",Font.BOLD,30);
	Font f35=new Font("Serif",Font.BOLD,35);
	Font f20=new Font("Serif",Font.BOLD,22);
	Font f15=new Font("Serif",Font.BOLD,16);


	
	Bill1()
	{
		super("Bill");
		setLocation(0,0);
		setSize(1800,1000);		setLayout(null);
		
			l2=new JLabel("Customer Bill");			
			add(l2);
			l2.setBounds(450,20,600,50);
			l2.setFont(new Font("Serif",Font.PLAIN,50));
			
			l3=new JLabel("THE VICTORY");
		//	add(l3);
			l3.setBounds(600,70,600,45);
			l3.setFont(new Font("Serif",Font.BOLD,40));	
			
			l4=new JLabel("Makes You Feel At Home... ");
			//add(l4);
			l4.setBounds(800,130,600,30);
			l4.setFont(new Font("Serif",Font.ITALIC,30));				

			p=new JPanel();
		//	add(p);
			p.setSize(1370,5);
			p.setLocation(0,0);
			p.setLayout(null);
			p.setBackground(Color.BLACK);
				
			
			p2=new JPanel();
		//	add(p2);
			p2.setSize(1370,5);
			p2.setLocation(0,200);
			p2.setLayout(null);
			p2.setBackground(Color.BLACK);				


			p_bill=new JPanel();
			add(p_bill);
			p_bill.setLocation(0,100);
			p_bill.setSize(1370,500);
			p_bill.setLayout(null);
			p_bill.setBackground(new Color(0,0,0,80));


		
			l_menutype=new JLabel("Newspaper Name");			
			p_bill.add(l_menutype);
			l_menutype.setBounds(10,60,200,30);
			l_menutype.setFont(f20);

			cb_menutype=new JComboBox();			
			p_bill.add(cb_menutype);
			cb_menutype.setBounds(220,60,200,30);
			cb_menutype.setFont(f15);
			cb_menutype.addItemListener(this);

			l_menuname=new JLabel("Customer Name");			
			p_bill.add(l_menuname);
			l_menuname.setBounds(10,0,200,30);
			l_menuname.setFont(f20);

			cb_menuname=new JTextField();			
			p_bill.add(cb_menuname);
			cb_menuname.setBounds(220,10,200,30);
			cb_menuname.setFont(f20);
		//cb_menutype.addItemListener(this);			
		
			l_price=new JLabel("Price");			
			p_bill.add(l_price);
			l_price.setBounds(10,110,200,30);
			l_price.setFont(f20);
	
			t_price=new JTextField();			
			p_bill.add(t_price);
			t_price.setBounds(220,110,200,30);
			t_price.setFont(f20);		
			text_validater1(t_price);
			
			l_quantity=new JLabel("Quantity");			
			p_bill.add(l_quantity);
			l_quantity.setBounds(10,160,200,30);
			l_quantity.setFont(f20);
			
			
			t_quantity=new JTextField();			
			p_bill.add(t_quantity);
			t_quantity.setBounds(220,160,200,30);
			t_quantity.setFont(f20);	
			text_validater1(t_quantity);

			b_addbill=new JButton("Add to Bill");			
			p_bill.add(b_addbill);
			b_addbill.setBounds(10,220,200,30);
			b_addbill.setFont(f20);
			b_addbill.addActionListener(this);

			b_confirmbill=new JButton("Confirmbill");			
			p_bill.add(b_confirmbill);
			b_confirmbill.setBounds(220,220,200,30);
			b_confirmbill.setFont(f20);			
			b_confirmbill.addActionListener(this);			
			b_confirmbill.setEnabled(false);
			
			b_print=new JButton("Print");			
			p_bill.add(b_print);
			b_print.setBounds(110,270,200,30);
			b_print.setFont(f20);
			b_print.addActionListener(this);
			b_print.setEnabled(false);
			

			b_clear=new JButton("Clear");			
			p_bill.add(b_clear);
			b_clear.setBounds(10,320,200,30);
			b_clear.setFont(f20);
			b_clear.addActionListener(this);

			
			b_back=new JButton("Back");			
			p_bill.add(b_back);
			b_back.setBounds(220,320,200,30);
			b_back.setFont(f20);			
			b_back.addActionListener(this);
			
			

		
			mdl=new DefaultTableModel(data,colHead);
			tab=new JTable(mdl)
		{
			public Class getColumnClass(int column)
			{
				switch(column)
				{
					case 0 : return String.class;
					case 1 : return String.class;
					case 2 : return String.class;
					case 3 : return String.class;
					default : return Integer.class;
				}
			}
		};
		
		
		//Resize the table coloumns
		DefaultTableColumnModel colModel=(DefaultTableColumnModel)tab.getColumnModel();
		TableColumn col=colModel.getColumn(1);
		
		col=colModel.getColumn(0);		col.setPreferredWidth(240);
		col=colModel.getColumn(1);		col.setPreferredWidth(240);
		col=colModel.getColumn(2);		col.setPreferredWidth(100);
		col=colModel.getColumn(3);		col.setPreferredWidth(100);
		col=colModel.getColumn(4);		col.setPreferredWidth(100);
		
		jsp=new JScrollPane(tab);
		p_bill.add(jsp);
		jsp.setBounds(460,30,800,400);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		try
		{	
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/newspaper","root","");
			stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);			
		}
		catch(Exception exp)
		{
			System.out.println(exp);
		}
		ProductCombo();
	//	ProductCombo1();
	}

		public static void clearTable(final JTable tab)
		{
			for(int i=0;i<tab.getRowCount();i++)
			{
				for(int j=0;j<tab.getColumnCount();j++)
				{
					tab.setValueAt("",i,j);
				}
					
			}
		}
		
		public static void deleteAllRows(final DefaultTableModel mdl)
		{
			for(int i=mdl.getRowCount()-2;i>=0;i--)
			{	
				mdl.removeRow(i);
			}	
		}		
			
	private void ProductCombo()
	{
		
		try
		{
			String sql="select * from News_paper";
			stm=cn.prepareStatement(sql);
			rs=stm.executeQuery(sql);
			while(rs.next())
			{
				cb_menutype.addItem(rs.getString("newspaper_name"));
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/*
	private void ProductCombo1()
	{
		
		try
		{
			String sql="select * from News_paper";
			stm=cn.prepareStatement(sql);
			rs=stm.executeQuery(sql);
			while(rs.next())
			{
				cb_menuname.addItem(rs.getString("newspaper_name"));
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	*/
	
	
		void text_validater1(final JTextField tt)
		{
			tt.addKeyListener(new KeyAdapter()
			{
				public void keyTyped(KeyEvent ke)
				{
					if(tt.getText().length()<10 && ke.getKeyChar()>='0' && ke.getKeyChar()<='9' )
						super.keyTyped(ke); // Optional
					else
					{
						ke.consume(); // Discart the event
						
						Toolkit tk =Toolkit.getDefaultToolkit();
						tk.beep();   // Raise the Sound
					}
				}
			});
		}
	
	
	
	
	
	
	public void itemStateChanged(ItemEvent ie)
	{
		
	}	
	
	public void actionPerformed(ActionEvent e)
	{
	
		if(e.getSource()==b_addbill)
		{
			try
			{
				if(t_price.getText().length()==0 || t_quantity.getText().length()==0 )
				{
					JOptionPane.showMessageDialog(null,"Select the Newspaper and Quantity");
					cb_menuname.setText("");
				}
				else
				{
				//to get the total value
					 rno=mdl.getRowCount();
					int a=Integer.parseInt(t_price.getText());
					int b=Integer.parseInt(t_quantity.getText());
					int c=a*b;
				 tot_val=Integer.parseInt((mdl.getValueAt(rno-1,4)).toString());
					
				//set/over write new values to last row/total cnt row
					mdl.setValueAt(cb_menutype.getSelectedItem(),rno-1,0);
					mdl.setValueAt(cb_menuname.getText(),rno-1,1);
					mdl.setValueAt(t_price.getText(),rno-1,2);
					mdl.setValueAt(t_quantity.getText(),rno-1,3);
					mdl.setValueAt(c,rno-1,4);
					
				//calculate new total
					tot_val=tot_val+c;
					
				//clear
					t_price.setText("");
					t_quantity.setText("");
					cb_menutype.setSelectedIndex(0);
					cb_menuname.setText("");
					
				//	cb_menuname.removeAllItems();
					t_quantity.requestFocus();
					
				//add new row to for total cost
					mdl.addRow(colHead);
					rno=mdl.getRowCount();
					mdl.setValueAt("",rno-1,0);
					mdl.setValueAt("Sub Tot=",rno-1,1);
					mdl.setValueAt("",rno-1,2);
					mdl.setValueAt("",rno-1,3);
					mdl.setValueAt(tot_val,rno-1,4);
				}
				b_confirmbill.setEnabled(true);

			}catch(Exception exp){System.out.println(exp);}
		}
		
		if(e.getSource()==b_confirmbill)
		{
			try
			{
				
				System.out.println(tot_val);
				//add new row to for total cost
					double cgst=(tot_val*2.5)/100;
					
					mdl.addRow(colHead);
					rno=mdl.getRowCount();
					mdl.setValueAt("",rno-1,0);
					mdl.setValueAt("CGST 2.5% ",rno-1,1);
					mdl.setValueAt("",rno-1,2);
					mdl.setValueAt("",rno-1,3);
					mdl.setValueAt(cgst,rno-1,4);		


					mdl.addRow(colHead);
					rno=mdl.getRowCount();
					mdl.setValueAt("",rno-1,0);
					mdl.setValueAt("SGST 2.5%",rno-1,1);
					mdl.setValueAt("",rno-1,2);
					mdl.setValueAt("",rno-1,3);
					mdl.setValueAt(cgst,rno-1,4);		

					double nettotal=cgst+cgst+tot_val;
					
					mdl.addRow(colHead);
					rno=mdl.getRowCount();
					mdl.setValueAt("",rno-1,0);
					mdl.setValueAt("NET AMOUNT =",rno-1,1);
					mdl.setValueAt("",rno-1,2);
					mdl.setValueAt("",rno-1,3);
					mdl.setValueAt(nettotal,rno-1,4);						
						
					b_confirmbill.setEnabled(false);
					b_print.setEnabled(true);
					b_addbill.setEnabled(false);


			}catch(Exception exp){System.out.println(exp);}
		}
		
		
		
		
	
		if(e.getSource()==b2)
		{
			
		}
		
		if(e.getSource()==b_print)
		{
			try
			{
				MessageFormat header=new MessageFormat("Shweta Agency");
				MessageFormat footer=new MessageFormat("Page{0,number,integer}");
				tab.print(JTable.PrintMode.FIT_WIDTH,header,footer);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			b_print.setEnabled(false);

		}
						if(e.getSource()==b_back)
				{
					new home1().setVisible(true);
					setVisible(false);
				}

		
		if(e.getSource()==b_clear)
		{
			t_price.setText("");
			t_quantity.setText("");
			//cb_menuname.removeAllItems();
		//	cb_menutype.setSelectedIndex(0);
			//t_quantity.requestFocus();
			clearTable(tab);
			deleteAllRows(mdl);	
			
		//	mdl.setValueAt("0",0,4);
			
			//tot_val=0;
			b_addbill.setEnabled(true);

			
			b_confirmbill.setEnabled(false);
			b_print.setEnabled(false);

		}
	
	}
	
	public static void main(String args[])
	{
		new Bill1();
	}
}