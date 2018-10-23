import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;


public class AddQuestion {

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
			AddQuestion window = new AddQuestion();
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
		shell.setText("Add Questions");
		
		Label lblEnterTheQuestions = new Label(shell, SWT.NONE);
		lblEnterTheQuestions.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		lblEnterTheQuestions.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblEnterTheQuestions.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblEnterTheQuestions.setBounds(10, 10, 118, 15);
		lblEnterTheQuestions.setText("Enter the Question:");
		
		Q = new Text(shell, SWT.BORDER);
		Q.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		Q.setBounds(10, 31, 424, 21);
		
		A = new Text(shell, SWT.BORDER);
		A.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		A.setBounds(46, 79, 76, 21);
		
		B = new Text(shell, SWT.BORDER);
		B.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		B.setBounds(46, 106, 76, 21);
		
		C = new Text(shell, SWT.BORDER);
		C.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		C.setBounds(46, 133, 76, 21);
		
		D = new Text(shell, SWT.BORDER);
		D.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		D.setBounds(46, 160, 76, 21);
		
		
		Button btnSubmit = new Button(shell, SWT.NONE);
		btnSubmit.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnSubmit.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				shell.dispose();
				AdminLogin al=new AdminLogin();
				al.open();
			}
		});
		btnSubmit.setBounds(359, 211, 75, 25);
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
					ps= connection.prepareStatement("INSERT INTO quest ( `Question`, `A`, `B`, `C`, `D`, `correct`) VALUES (?,?,?,?,?,?)");
					ps.setString(1,Q.getText());
					ps.setString(2,A.getText());
					ps.setString(3,B.getText());
					ps.setString(4,C.getText());
					ps.setString(5,D.getText());
					if( RA.getSelection()) {ps.setString(6,"A");}
					else if(RB.getSelection()) {ps.setString(6,"B");}
					else if(RC.getSelection()) {ps.setString(6,"C");}
					else if(RD.getSelection()) {ps.setString(6,"D");}
					
					int result = ps.executeUpdate();
					
					hs=connection.prepareStatement("INSERT INTO ur (`correct`) VALUES (?);");
					if(RA.getSelection()) {hs.setString(1,"A");}
					else if(RB.getSelection()) {hs.setString(1,"B");}
					else if(RC.getSelection()) {hs.setString(1,"C");}
					else if(RD.getSelection()) {hs.setString(1,"D");}
					int j =hs.executeUpdate();
					if(result==1&&j==1) {
						JFrame frame =new JFrame();
						frame.setVisible(true);
						JOptionPane.showMessageDialog(frame,"Question Submitted","", JOptionPane.PLAIN_MESSAGE);
						frame.setVisible(false);
					}
                  RA.setSelection(false);
                  RB.setSelection(false);
                  RC.setSelection(false);
                  RD.setSelection(false);
					
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Q.setText("");
				A.setText("");
				B.setText("");
				C.setText("");
				D.setText("");
			
			}
		});
		btnSubmit_1.setBounds(10, 211, 75, 25);
		btnSubmit_1.setText("Submit");
		
		
		Label lblEnterCorrectAnswer = new Label(shell, SWT.NONE);
		lblEnterCorrectAnswer.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		lblEnterCorrectAnswer.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblEnterCorrectAnswer.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblEnterCorrectAnswer.setBounds(10, 58, 45, 15);
		lblEnterCorrectAnswer.setText("Correct:");
		
		
	}
}
