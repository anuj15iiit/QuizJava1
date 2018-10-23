import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainLogin {
    protected Display display;
	protected Shell shell;
	private Text utxt;
	private Text ptxt;
	
	public Connection connection;
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainLogin window = new MainLogin();
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
		shell = new Shell(SWT.CLOSE | SWT.TITLE  );
		shell.setBackgroundImage(SWTResourceManager.getImage("F:\\Backup\\Eclpise\\QuizApp\\src\\KBC.jpeg"));
		shell.setImage(SWTResourceManager.getImage("F:\\Backup\\Eclpise\\QuizApp\\src\\KBC1.jpg"));
		shell.setSize(450, 275);
		shell.setText("LOGIN");
		
		Label lblUsername = new Label(shell, SWT.NONE);
		lblUsername.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblUsername.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblUsername.setAlignment(SWT.CENTER);
		lblUsername.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lblUsername.setBounds(10, 71, 80, 23);
		lblUsername.setText("Username:");
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblPassword.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblPassword.setAlignment(SWT.CENTER);
		lblPassword.setText("Password:");
		lblPassword.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lblPassword.setBounds(10, 122, 80, 23);
		
		utxt = new Text(shell, SWT.BORDER);
		utxt.setBounds(107, 73, 238, 21);
		
		ptxt = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		ptxt.setBounds(107, 124, 238, 21);
		
		connectDB();
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				String uname= utxt.getText();
				String pname= ptxt.getText();
				if((uname.equals("admin")) && (pname.equals("admin"))){
					shell.dispose();
					AdminLogin al= new AdminLogin();
					al.open();
			 		}
				    PreparedStatement ps;
				    try {
				    ps = connection.prepareStatement("SELECT `Username`, `Password` FROM `UnPw` WHERE `Username`= ? AND`Password`= ?");
				    ps.setString(1,utxt.getText());
				    ps.setString(2, ptxt.getText());
				    ResultSet result = ps.executeQuery();
				    if(result.next()) {
				      shell.dispose();
				      Question4 ql= new Question4();
				      ql.open();
		 		      }
				    else
					{JFrame frame =new JFrame();
					frame.setVisible(true);
					JOptionPane.showMessageDialog(frame,"Incorrect Credentials","", JOptionPane.PLAIN_MESSAGE);
					frame.setVisible(false);
					utxt.setText("");
					ptxt.setText("");}}
				    catch(SQLException ex) {
					  Logger.getLogger(MainLogin.class.getName()).log(Level.SEVERE, null, ex);
					  ex.printStackTrace();
				     }
				
			}
		});
		btnNewButton.setText("SUBMIT");
		btnNewButton.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		btnNewButton.setBounds(42, 210, 114, 26);
		
		Button btnShowPassword = new Button(shell, SWT.CHECK);
		btnShowPassword.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnShowPassword.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnShowPassword.setBackground(SWTResourceManager.getColor(245, 245, 220));
		btnShowPassword.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnShowPassword.getSelection()) {
					ptxt.setEchoChar((char) 0);
				}
				else {
					ptxt.setEchoChar('•');
				}
			}
		});
		btnShowPassword.setBounds(268, 221, 106, 16);
		btnShowPassword.setText("Show Password");

	}
	
	public void connectDB() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		
		/*if(connection != null || !connection.isClosed())
		{	
			connection.close();
		}*/
		
		connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails", "root", "password");
		}
		catch(SQLException ex) {
			Logger.getLogger(MainLogin.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
// add user; add books; issue books;  