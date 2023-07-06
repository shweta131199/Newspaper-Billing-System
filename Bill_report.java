
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Bill_report extends JFrame implements ActionListener
{	
	JLabel lroll,lname,ldate,l;
	JTextField t1,t2;
	DateButton b1,b2,b3;
	JButton back,badd,bexit,bdis_all,bdis_range;
	String sql,s1,s2;
	Connection conn;
	Statement stm;
	ResultSet rs;
	PreparedStatement prstm;
	Font f;
	

	Bill_report()
	{
	 
		super("db Connectivity");
        setLayout(null);
		setLocation(200,100);
		setSize(900,400);
	//	getContentPane().setBackground(Color.pink);
        setResizable(false);
		
		
	
		b2 = new DateButton();
		b3 = new DateButton();
		l = new JLabel("Bill Report");
	
		bdis_all = new JButton("Display All");
		bdis_range = new JButton("Display In Range");
		back = new JButton("BACK");

		add(bdis_all);
		add(bdis_range);
		
		f=new Font("arial",Font.BOLD,40);
	
		l.setFont(new Font("Arial",Font.BOLD,30));
		add(b2);
		add(b3);
		add(back);
		add(l);
		
		bdis_all.setBounds(20,80,210,30);
		bdis_range.setBounds(20,220,210,30);

		back.setBounds(20,130,210,30);
	
		b2.setBounds(20,180,100,30);
		b3.setBounds(130,180,100,30);
		
		
		l.setBounds(300,10,400,30);
		
		
		bdis_all.addActionListener(this);
		bdis_range.addActionListener(this);
		back.addActionListener(this);

		
		b2.addActionListener(this);
		b3.addActionListener(this);

			try
			{	
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newspaper","root","");
			stm = conn.createStatement();
			}
		
				catch(Exception e)
				{
					e.printStackTrace();
				}

		
	 setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
	}
	
void updateTable(String s1,String s2)
	{
		try
		{
			// 1) Memory allocatin to array
			rs = stm.executeQuery(s1);
			rs.first();
			int rowcnt = rs.getInt(1);
			
			String colHeads[] = { "C-ID", "C-Name", "Address","Phone no","G-Mail"};
			String data[][] = new String[rowcnt][6];
			
			//2) load data into array
			rs = stm.executeQuery(s2);
			rs.first();
			for (int i=0;i<rowcnt;i++)
			{
				data[i][0] = rs.getString(1);
				data[i][1] = rs.getString(2);
				data[i][2] = rs.getString(3);
				data[i][3] = rs.getString(4);
				data[i][4] = rs.getString(5);
				/*data[i][5] = rs.getString(6);
				*/
				rs.next();
			}
			
			// 3) create table
			JTable table = new JTable(data, colHeads);
			add(table);
			table.setEnabled(false);
			JScrollPane jsp = new JScrollPane(table);
			add(jsp);
			jsp.setBounds(350,70,400,270);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}

	
	public void actionPerformed(ActionEvent e)
    {
	
		if(e.getSource() == back)
		{
				//new home();
				dispose();
		}
	
		if(e.getSource() ==bdis_all)
		{
			s1 = "select count(*) from cust_info1";
			s2 = "select * from cust_info1 order by c_id";
			
			updateTable(s1,s2);
		}
	
		if(e.getSource() == bdis_range)
		{
		//	s1 = "select count(*) from date where date between '" + dateFrom.getText() + "' order by date";
			
			updateTable(s1,s2);
		}
	
	
				
	
		
		
	
		
	

	
}

	
	public static void main(String args[])
    {  
		new Bill_report();
    }
}
		
		
	
	
