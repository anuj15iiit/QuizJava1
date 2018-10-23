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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class EditQuestion {

	protected Shell shell;
	private Text Q;
	private Text A;
	private Text B;
	private Text C;
	private Text D;
    public Connection connection;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EditQuestion window = new EditQuestion();
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
		shell.setText("Editing Question");

		Label lblEnterTheQuestions = new Label(shell, SWT.NONE);
		lblEnterTheQuestions.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		lblEnterTheQuestions.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblEnterTheQuestions.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblEnterTheQuestions.setBounds(10, 10, 118, 15);
		lblEnterTheQuestions.setText("Enter the Questions:");
		
		Q = new Text(shell, SWT.BORDER);
		Q.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		Q.setBounds(10, 31, 414, 21);
		
		Label lblEnterTheOptions = new Label(shell, SWT.NONE);
		lblEnterTheOptions.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		lblEnterTheOptions.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblEnterTheOptions.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblEnterTheOptions.setBounds(59, 58, 95, 15);
		lblEnterTheOptions.setText("Enter the Options:");
		
		A = new Text(shell, SWT.BORDER);
		A.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		A.setBounds(59, 79, 76, 21);
		
		B = new Text(shell, SWT.BORDER);
		B.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		B.setBounds(59, 104, 76, 21);
		
		C = new Text(shell, SWT.BORDER);
		C.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		C.setBounds(59, 131, 76, 21);
		
		D = new Text(shell, SWT.BORDER);
		D.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		D.setBounds(59, 158, 76, 21);
		 
		
		Button btnSubmit = new Button(shell, SWT.NONE);
		btnSubmit.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnSubmit.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				shell.dispose();
				ListQuestions lq=new ListQuestions();
				lq.open();
			}
		});
		btnSubmit.setBounds(349, 201, 75, 25);
		btnSubmit.setText("Back");
		Button RA = new Button(shell, SWT.RADIO);
		RA.setForeground(SWTResourceManager.getColor(220, 20, 60));
		RA.setBackground(SWTResourceManager.getColor(245, 245, 220));
		RA.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		RA.setBounds(10, 81, 30, 16);
		RA.setText("A");
		
		Button RB = new Button(shell, SWT.RADIO);
		RB.setForeground(SWTResourceManager.getColor(220, 20, 60));
		RB.setBackground(SWTResourceManager.getColor(245, 245, 220));
		RB.setText("B");
		RB.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		RB.setBounds(10, 108, 30, 16);
		
		Button RC = new Button(shell, SWT.RADIO);
		RC.setForeground(SWTResourceManager.getColor(220, 20, 60));
		RC.setBackground(SWTResourceManager.getColor(245, 245, 220));
		RC.setText("C");
		RC.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		RC.setBounds(10, 133, 30, 16);
		
		Button RD = new Button(shell, SWT.RADIO);
		RD.setForeground(SWTResourceManager.getColor(220, 20, 60));
		RD.setBackground(SWTResourceManager.getColor(245, 245, 220));
		RD.setText("D");
		RD.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		RD.setBounds(10, 160, 30, 16);
        
		  try {
			  Class.forName("com.mysql.jdbc.Driver");
				connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/questions", "root", "password");
				PreparedStatement ps,ps1;
				ps= connection.prepareStatement("SELECT * FROM quest WHERE `id`=?");
				ps.setInt(1,ListQuestions.j);
				ResultSet result = ps.executeQuery();
				ps1= connection.prepareStatement("SELECT * FROM ur WHERE `id`=?");
				ps1.setInt(1,ListQuestions.j);
			    ps1.executeQuery();	
			    result.next();
		        Q.setText(result.getString("Question"));
		        A.setText(result.getString("A"));
		        B.setText(result.getString("B"));
		        C.setText(result.getString("C"));
		        D.setText(result.getString("D"));
		        String s= result.getString("correct");
		        if(s.matches("A")) {RA.setSelection(true);}
		        else if(s.matches("B")) {RB.setSelection(true);}
		        else if(s.matches("C")) {RC.setSelection(true);}
		        else if(s.matches("D")) {RD.setSelection(true);}
	      
    } catch(SQLException | ClassNotFoundException e1) {
       System.out.println("SQL exception occured" + e1);
    }
		
		Button btnSubmit_1 = new Button(shell, SWT.NONE);
		btnSubmit_1.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnSubmit_1.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnSubmit_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				PreparedStatement ps, hs;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/questions", "root", "password");
					ps= connection.prepareStatement("UPDATE quest SET `Question`=?, `A`=?, `B`=?, `C`=?, `D`=?, `correct`=?  WHERE `id`=?");
					ps.setString(1,Q.getText());
					ps.setString(2,A.getText());
					ps.setString(3,B.getText());
					ps.setString(4,C.getText());
					ps.setString(5,D.getText());
					if( RA.getSelection()) {ps.setString(6,"A");}
					else if(RB.getSelection()) {ps.setString(6,"B");}
					else if(RC.getSelection()) {ps.setString(6,"C");}
					else if(RD.getSelection()) {ps.setString(6,"D");}
					ps.setInt(7,ListQuestions.j);
					int result = ps.executeUpdate();
					
					hs=connection.prepareStatement("UPDATE ur SET `correct`=? WHERE `id`=?;");
					if(RA.getSelection()) {hs.setString(1,"A");}
					else if(RB.getSelection()) {hs.setString(1,"B");}
					else if(RC.getSelection()) {hs.setString(1,"C");}
					else if(RD.getSelection()) {hs.setString(1,"D");}
					hs.setInt(2, ListQuestions.j );
					int j =hs.executeUpdate();
					if(result==1&&j==1) {
						JFrame frame =new JFrame();
						frame.setVisible(true);
						JOptionPane.showMessageDialog(frame,"Question Edited","", JOptionPane.PLAIN_MESSAGE);
						frame.setVisible(false);
					}
                  
					
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
		});
		btnSubmit_1.setBounds(10, 201, 75, 25);
		btnSubmit_1.setText("Update");
		
		
		Label lblEnterCorrectAnswer = new Label(shell, SWT.NONE);
		lblEnterCorrectAnswer.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		lblEnterCorrectAnswer.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblEnterCorrectAnswer.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblEnterCorrectAnswer.setBounds(10, 58, 45, 15);
		lblEnterCorrectAnswer.setText("Correct:");
		

	}

}
