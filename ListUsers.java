import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Label;

public class ListUsers {

	protected Shell shell;
    public Connection connection;
    private Table table;
    public static int j;
    
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ListUsers window = new ListUsers();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.MIN );
		shell.setBackground(SWTResourceManager.getColor(245,245,220));
		shell.setImage(SWTResourceManager.getImage("F:\\Backup\\Eclpise\\QuizApp\\src\\KBC1.jpg"));
		shell.setSize(429, 275);
		shell.setText("List of Users");
		
		
		Button btnDeleteUser = new Button(shell, SWT.NONE);
		btnDeleteUser.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnDeleteUser.setBackground(SWTResourceManager.getColor(255, 0, 0));
		btnDeleteUser.setForeground(SWTResourceManager.getColor(245, 245, 220));
		btnDeleteUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				int i=table.getSelectionIndex();
			    j=i+1;
				String k = Integer.toString(j);
				PreparedStatement ps,his,his1;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails", "root", "password");
				    ps= connection.prepareStatement("DELETE FROM unpw WHERE `id`=?");
				    ps.setString(1,k);
					int result = ps.executeUpdate();
					his= connection.prepareStatement("ALTER TABLE unpw DROP `id`;");
					his.executeUpdate();
                    his1= connection.prepareStatement(" ALTER TABLE unpw ADD `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,ADD PRIMARY KEY (`id`);");
		            his1.executeUpdate();
					if(result==1) {
						JFrame frame =new JFrame();
						frame.setVisible(true);
						JOptionPane.showMessageDialog(frame,"User Deleted","", JOptionPane.PLAIN_MESSAGE);
						frame.setVisible(false);
						try {
							Class.forName("com.mysql.jdbc.Driver");
					      } catch(ClassNotFoundException e1) {
					         System.out.println("Class not found "+ e1);
					      }
					      try {
								connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails", "root", "password");

					            Statement stmt = connection.createStatement();
					            ResultSet rs = stmt.executeQuery("SELECT * FROM unpw");
					            table.removeAll();
				                  
				         while (rs.next()) {
					        	TableItem item1 = new TableItem(table, SWT.NONE);
					        	item1.setText(new String[] {
					        	 rs.getString("id"),
					             rs.getString("Username"),
					             rs.getString("Password")});
					        	table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
					        			1));
				         }
					      } catch(SQLException e1) {
					         System.out.println("SQL exception occured" + e1);
					      }
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDeleteUser.setBounds(91, 210, 75, 25);
		btnDeleteUser.setText("Delete User");
		
		Button btnBack = new Button(shell, SWT.NONE);
		btnBack.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnBack.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				shell.dispose();
				AdminLogin al =new AdminLogin();
				al.open();
			}
		});
		btnBack.setBounds(340, 210, 75, 25);
		btnBack.setText("Back");
		
		TableViewer tab = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table = tab.getTable();
		table.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		table.setForeground(SWTResourceManager.getColor(220, 20, 60));
		table.setBackground(SWTResourceManager.getColor(245, 245, 220));
		table.setBounds(10, 26, 405, 180);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
	      } catch(ClassNotFoundException e1) {
	         System.out.println("Class not found "+ e1);
	      }
	      try {
				connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails", "root", "password");

	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM unpw");		
        			TableColumn tblclmnNos = new TableColumn(table, SWT.NONE);
        			tblclmnNos.setWidth(40);
        			//tblclmnNos.setText("Username");
        			
        			TableColumn tblclmnEno = new TableColumn(table, SWT.NONE);
        			tblclmnEno.setWidth(80); 
        			
        			TableColumn tblclmnEno1 = new TableColumn(table, SWT.NONE);
    				tblclmnEno1.setWidth(120);
    				TableColumn tblclmnEno2 = new TableColumn(table, SWT.NONE);
    				tblclmnEno2.setWidth(80);
    				TableColumn tblclmnEno3 = new TableColumn(table, SWT.NONE);
    				tblclmnEno3.setWidth(80);
    				
    				
    				Label lblId = new Label(shell, SWT.NONE);
    				lblId.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
    				lblId.setBackground(SWTResourceManager.getColor(245, 245, 220));
    				lblId.setForeground(SWTResourceManager.getColor(220, 20, 60));
    				lblId.setBounds(10, 5, 32, 15);
    				lblId.setText("Id");
    				
    				Label lblUsername = new Label(shell, SWT.NONE);
    				lblUsername.setText("Username");
    				lblUsername.setForeground(SWTResourceManager.getColor(220, 20, 60));
    				lblUsername.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
    				lblUsername.setBackground(SWTResourceManager.getColor(245, 245, 220));
    				lblUsername.setBounds(250, 5, 75, 15);
    				
    				Label lblPassword = new Label(shell, SWT.NONE);
    				lblPassword.setText("Password");
    				lblPassword.setForeground(SWTResourceManager.getColor(220, 20, 60));
    				lblPassword.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
    				lblPassword.setBackground(SWTResourceManager.getColor(245, 245, 220));
    				lblPassword.setBounds(330, 5, 75, 15);
    				
    				Button btnEditUser = new Button(shell, SWT.NONE);
    				btnEditUser.addMouseListener(new MouseAdapter() {
    					@Override
    					public void mouseDoubleClick(MouseEvent e) {
    						int i=table.getSelectionIndex();
    					    j=i+1;
    						String k = Integer.toString(j);
    					    
    						PreparedStatement ps;
    						try {
    							Class.forName("com.mysql.jdbc.Driver");
    							connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails", "root", "password");
    							ps= connection.prepareStatement("SELECT * FROM unpw WHERE `id`=?");
    							ps.setString(1,k);
    							ResultSet result = ps.executeQuery();
    							if(result.next()) {
    					           shell.dispose();
						           EditUsers eu=new EditUsers();
						           eu.open();
    							}
    						} catch (ClassNotFoundException | SQLException e1) {
    							// TODO Auto-generated catch block
    							e1.printStackTrace();
    						}
    						
    					}
    				});
    				btnEditUser.setBackground(SWTResourceManager.getColor(0, 0, 255));
    				btnEditUser.setForeground(SWTResourceManager.getColor(245, 245, 220));
    				btnEditUser.setBounds(10, 210, 75, 25);
    				btnEditUser.setText("Edit User");
    				
    				Label lblName = new Label(shell, SWT.NONE);
    				lblName.setText("Name");
    				lblName.setForeground(SWTResourceManager.getColor(220, 20, 60));
    				lblName.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
    				lblName.setBackground(SWTResourceManager.getColor(245, 245, 220));
    				lblName.setBounds(50, 5, 75, 15);
    				
    				Label lblEmailId = new Label(shell, SWT.NONE);
    				lblEmailId.setText("Email ID");
    				lblEmailId.setForeground(SWTResourceManager.getColor(220, 20, 60));
    				lblEmailId.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
    				lblEmailId.setBackground(SWTResourceManager.getColor(245, 245, 220));
    				lblEmailId.setBounds(130, 5, 75, 15);
        			//tblclmnEno.setText("Password");
        		
        		     
	         while (rs.next()) {
	        	TableItem item1 = new TableItem(table, SWT.NONE);
	        	item1.setText(new String[] {
	        	 rs.getString("id"),
	        	 rs.getString("Name"),
	        	 rs.getString("Email"),
	             rs.getString("Username"),
	             rs.getString("Password")});
	        	table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,	1));
	   	         }
	      } catch(SQLException e1) {
	         System.out.println("SQL exception occured" + e1);
	      }}	
}