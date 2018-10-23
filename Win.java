import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class Win {

	protected Shell shell;
	 public Table table;
	 public Connection connection;
	 public Text text;
	 public int icorrect=0;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Win window = new Win();
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
		shell.setBackgroundImage(SWTResourceManager.getImage("F:\\Backup\\Eclpise\\QuizApp\\src\\KBC.jpg"));
		shell.setImage(SWTResourceManager.getImage("F:\\Backup\\Eclpise\\QuizApp\\src\\KBC1.jpg"));
		shell.setSize(450, 275);
		shell.setText("Result");
		
		Label lblCongratulationsYouWon = new Label(shell, SWT.NONE);
		lblCongratulationsYouWon.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblCongratulationsYouWon.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblCongratulationsYouWon.setFont(SWTResourceManager.getFont("Times New Roman", 15, SWT.NORMAL));
		lblCongratulationsYouWon.setBounds(10, 10, 414, 28);
		lblCongratulationsYouWon.setText("Congratulations.. You Have Completed the quiz");
		

		TableViewer tab = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table = tab.getTable();
		table.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		table.setForeground(SWTResourceManager.getColor(220, 20, 60));
		table.setHeaderBackground(SWTResourceManager.getColor(220, 20, 60));
		table.setBackground(SWTResourceManager.getColor(245, 245, 220));
		table.setBounds(10, 36, 414, 170);
		
		Button btnStartAgain = new Button(shell, SWT.NONE);
		btnStartAgain.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnStartAgain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				PreparedStatement pis;
				   try {
					pis= connection.prepareStatement("UPDATE ur SET `UserResponse` = ? ;");
					pis.setString(1, "Not_Answered");
					pis.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				shell.dispose();
				MainLogin ml=new MainLogin();
				ml.open();
			}
		});
		btnStartAgain.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.NORMAL));
		btnStartAgain.setBounds(337, 212, 87, 25);
		btnStartAgain.setText("Start Again");
		
		
		
		try {
			// TODO Auto-generated method stub
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/questions", "root", "password");
			 Statement stmt = connection.createStatement();
	           ResultSet rs = stmt.executeQuery("SELECT * FROM ur");
	           //TableItem item = new TableItem(table, SWT.NONE);
	       	TableColumn tblclmnNos = new TableColumn(table, SWT.NONE);
				tblclmnNos.setWidth(100);
				//tblclmnNos.setText("UserResponse");
				
				TableColumn tblclmnEno = new TableColumn(table, SWT.NONE);
				tblclmnEno.setWidth(100);
				//tblclmnEno.setText("correct");
				TableColumn tblclmnEno1 = new TableColumn(table, SWT.NONE);
				tblclmnEno1.setWidth(100);
				//tblclmnEno.setText("correct");
			 
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] {"ID","User Response","Correct Answer"});
				TableItem item2 = new TableItem(table,SWT.NONE);
				item2.setText(new String[] {" "," "," "});
	    while (rs.next()) {
	   	TableItem item1 = new TableItem(table, SWT.NONE);
	   	item1.setText(new String[] {
	   	 rs.getString("id"),
	        rs.getString("UserResponse"),
	        rs.getString("correct")});
	   	table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
	   			1));
	   	String r1= rs.getString("UserResponse");
	   	String r2= rs.getString("correct");
	   	
	   	
	   		if(r1.equals(r2))	{icorrect+=1;}
	    }	} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		text = new Text(shell, SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		text.setForeground(SWTResourceManager.getColor(220, 20, 60));
		text.setBackground(SWTResourceManager.getColor(245, 245, 220));
		text.setBounds(85, 215, 76, 21);
	  
	  String pr = Integer.toString(icorrect);
	  text.setText(pr);
		
		
		Label lblScore = new Label(shell, SWT.NONE);
		lblScore.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		lblScore.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblScore.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblScore.setBounds(10, 215, 55, 15);
		lblScore.setText("Score:");
		
		

	
}

		
}
