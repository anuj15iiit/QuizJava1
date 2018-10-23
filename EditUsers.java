import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class EditUsers {

	protected Shell shell;
	private Text untxt;
	private Text pwtxt;
	public Connection connection;
	private Text etxt;
	private Text ntxt;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EditUsers window = new EditUsers();
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
		shell = new Shell();
		shell.setBackgroundImage(SWTResourceManager.getImage("F:\\Backup\\Eclpise\\QuizApp\\src\\KBC.jpg"));
		shell.setImage(SWTResourceManager.getImage("F:\\Backup\\Eclpise\\QuizApp\\src\\KBC1.jpg"));
		shell.setSize(450, 275);
		shell.setText("Editing User");
		
       
		Label lblEnterTheUsername = new Label(shell, SWT.NONE);
		lblEnterTheUsername.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		lblEnterTheUsername.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblEnterTheUsername.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblEnterTheUsername.setBounds(10, 106, 190, 15);
		lblEnterTheUsername.setText("Enter the Username:");
		
		untxt = new Text(shell, SWT.BORDER);
		untxt.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		untxt.setBounds(10, 127, 190, 21);
		
		Label lblEnterThePassword = new Label(shell, SWT.NONE);
		lblEnterThePassword.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		lblEnterThePassword.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblEnterThePassword.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblEnterThePassword.setBounds(10, 154, 135, 15);
		lblEnterThePassword.setText("Enter the Password:");
		
		pwtxt = new Text(shell, SWT.BORDER);
		pwtxt.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		pwtxt.setBounds(10, 175, 190, 21);
		
		Button btnSubmit = new Button(shell, SWT.NONE);
		btnSubmit.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnSubmit.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				shell.dispose();
				ListUsers al=new ListUsers();
				al.open();
				
				
			
			}
		});
		btnSubmit.setBounds(349, 202, 75, 25);
		btnSubmit.setText("Back");
		
		Button btnSubmit_1 = new Button(shell, SWT.NONE);
		btnSubmit_1.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnSubmit_1.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnSubmit_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnSubmit_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				PreparedStatement ps;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails", "root", "password");
					ps= connection.prepareStatement("UPDATE unpw SET `Username`=?,`Password`=?,`Name`=?,`Email`=? WHERE `id`=?;");
					ps.setString(1,untxt.getText());
					ps.setString(2, pwtxt.getText());
					ps.setString(3, ntxt.getText());
					ps.setString(4, etxt.getText());
					ps.setInt(5, ListUsers.j);
					int result = ps.executeUpdate();
					if(result==1) {
						JFrame frame =new JFrame();
						frame.setVisible(true);
						JOptionPane.showMessageDialog(frame,"User Edited","", JOptionPane.PLAIN_MESSAGE);
						frame.setVisible(false);
						
					}
					
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSubmit_1.setBounds(10, 202, 75, 25);
		btnSubmit_1.setText("Update");
		
		etxt = new Text(shell, SWT.BORDER);
		etxt.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		etxt.setBounds(10, 79, 190, 21);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblNewLabel.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblNewLabel.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		lblNewLabel.setBounds(10, 58, 190, 15);
		lblNewLabel.setText("Enter E-mail id:");
		
		ntxt = new Text(shell, SWT.BORDER);
		ntxt.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		ntxt.setBounds(10, 31, 190, 21);
		
		Label lblEnterName = new Label(shell, SWT.NONE);
		lblEnterName.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblEnterName.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblEnterName.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		lblEnterName.setBounds(10, 10, 190, 15);
		lblEnterName.setText("Enter Name:");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails", "root", "password");
			PreparedStatement ps;
			ps= connection.prepareStatement("SELECT * FROM unpw WHERE `id`=?");
			ps.setInt(1,ListUsers.j);
			System.out.println(ListUsers.j);
			ResultSet result = ps.executeQuery();
			result.next();
	        untxt.setText(result.getString("Username"));
	        pwtxt.setText(result.getString("Password"));
	        ntxt.setText(result.getString("Name"));
	        etxt.setText(result.getString("Email")); } 
		     catch(SQLException | ClassNotFoundException e1) {
	            System.out.println("SQL exception occured" + e1);
	        }
		
	}

}
