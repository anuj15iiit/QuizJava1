import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class Question4 {
    
	protected static int correct;
	protected static int incorrect;
	protected Shell shell;
	private Text textQuestion;
	private Text textA;
	private Text textB;
	private Text textC;
	private Text textD;
    public Connection connection;
    private Text textID;
    public Statement stat;
    public ResultSet rs;
   
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Question4 window = new Question4();
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
		shell = new Shell(SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.MIN ) ;
		shell.setBackground(SWTResourceManager.getColor(245, 245, 220));
		shell.setBackgroundImage(SWTResourceManager.getImage("F:\\Backup\\Eclpise\\QuizApp\\src\\KBC.jpg"));
		shell.setImage(SWTResourceManager.getImage("F:\\Backup\\Eclpise\\QuizApp\\src\\KBC1.jpg"));
		shell.setSize(450, 275);
		shell.setText("Question");
		
		
		Label lblQWhichCity = new Label(shell, SWT.NONE);
		lblQWhichCity.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblQWhichCity.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblQWhichCity.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lblQWhichCity.setBounds(12, 10, 24, 41);
		lblQWhichCity.setText("Q.");
		
		Button A = new Button(shell, SWT.RADIO);
		A.setForeground(SWTResourceManager.getColor(220, 20, 60));
		A.setBackground(SWTResourceManager.getColor(245, 245, 220));
		A.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		A.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		A.setBounds(12, 57, 35, 21);
		A.setText("A.");
		
		Button B = new Button(shell, SWT.RADIO);
		B.setForeground(SWTResourceManager.getColor(220, 20, 60));
		B.setBackground(SWTResourceManager.getColor(245, 245, 220));
		B.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		B.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		B.setBounds(12, 84, 35, 21);
		B.setText("B.");
		
		Button C = new Button(shell, SWT.RADIO);
		C.setForeground(SWTResourceManager.getColor(220, 20, 60));
		C.setBackground(SWTResourceManager.getColor(245, 245, 220));
		C.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		C.setBounds(12, 111, 35, 21);
		C.setText("C.");
		
		Button D = new Button(shell, SWT.RADIO);
		D.setForeground(SWTResourceManager.getColor(220, 20, 60));
		D.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		D.setBackground(SWTResourceManager.getColor(245, 245, 220));
		D.setBounds(12, 138, 35, 21);
		D.setText("D.");
		Button btnSubmit = new Button(shell, SWT.NONE);
		btnSubmit.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnSubmit.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				PreparedStatement ps;
				try {
					ps= connection.prepareStatement("UPDATE ur SET `UserResponse` = ? WHERE `id` = ?;");
					if(A.getSelection()) {ps.setString(1,"A");}
					else if(B.getSelection()) {ps.setString(1,"B");}
					else if(C.getSelection()) {ps.setString(1,"C");}
					else if(D.getSelection()) {ps.setString(1,"D");}
					int i = Integer.parseInt(textID.getText());
					ps.setInt(2,i );
					ps.executeUpdate();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JFrame frame =new JFrame();
				frame.setVisible(true);
				JOptionPane.showMessageDialog(frame,"Answer Submitted","", JOptionPane.PLAIN_MESSAGE);
				frame.setVisible(false);
				
			}
		});
		btnSubmit.setBounds(222, 212, 99, 25);
		btnSubmit.setText("Submit");
		
		Button button = new Button(shell, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		button.setForeground(SWTResourceManager.getColor(220, 20, 60));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				try {
					
					  if(rs.previous()) {
					textID.setText(rs.getString("id"));
		            textQuestion.setText(rs.getString("Question"));
		            textA.setText(rs.getString("A"));
		            textB.setText(rs.getString("B"));
		            textC.setText(rs.getString("C"));
		            textD.setText(rs.getString("D"));}
		          
		            else {JFrame frame =new JFrame();
					frame.setVisible(true);
					JOptionPane.showMessageDialog(frame,"No Previous Question","", JOptionPane.PLAIN_MESSAGE);
					frame.setVisible(false);}
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setText("Prev");
		button.setBounds(12, 212, 99, 25);
		
		Button btnNext = new Button(shell, SWT.NONE);
		btnNext.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnNext.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
				try {
					
					if(rs.next()) {
					textID.setText(rs.getString("id"));
		            textQuestion.setText(rs.getString("Question"));
		            textA.setText(rs.getString("A"));
		            textB.setText(rs.getString("B"));
		            textC.setText(rs.getString("C"));
		            textD.setText(rs.getString("D"));}
		            
		            else {JFrame frame =new JFrame();
					frame.setVisible(true);
					JOptionPane.showMessageDialog(frame,"No next question...","", JOptionPane.PLAIN_MESSAGE);
					frame.setVisible(false);}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNext.setBounds(117, 212, 99, 25);
		btnNext.setText("Next");
		
		textQuestion = new Text(shell, SWT.READ_ONLY);
		textQuestion.setForeground(SWTResourceManager.getColor(220, 20, 60));
		textQuestion.setBackground(SWTResourceManager.getColor(245, 245, 220));
		textQuestion.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textQuestion.setBounds(53, 10, 371, 41);
		
		
		textA = new Text(shell, SWT.READ_ONLY);
		textA.setForeground(SWTResourceManager.getColor(220, 20, 60));
		textA.setBackground(SWTResourceManager.getColor(245, 245, 220));
		textA.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		textA.setBounds(53, 57, 147, 21);
		
		textB = new Text(shell, SWT.READ_ONLY);
		textB.setForeground(SWTResourceManager.getColor(220, 20, 60));
		textB.setBackground(SWTResourceManager.getColor(245, 245, 220));
		textB.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		textB.setBounds(53, 84, 147, 21);
		
		textC = new Text(shell, SWT.READ_ONLY);
		textC.setForeground(SWTResourceManager.getColor(220, 20, 60));
		textC.setBackground(SWTResourceManager.getColor(245, 245, 220));
		textC.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		textC.setBounds(53, 111, 147, 21);
		
		textD = new Text(shell, SWT.READ_ONLY);
		textD.setForeground(SWTResourceManager.getColor(220, 20, 60));
		textD.setBackground(SWTResourceManager.getColor(245, 245, 220));
		textD.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		textD.setBounds(53, 138, 147, 21);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnNewButton.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
				int reply = JOptionPane.showConfirmDialog(null, "Do you really want to Submit?", "", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {
		        	shell.dispose();
					Win w =new Win();
					w.open();
		        }
		        else { }
				}
		});
		btnNewButton.setBounds(325, 212, 99, 25);
		btnNewButton.setText("Final Submit");
		
		
		textID = new Text(shell, SWT.READ_ONLY);
		textID.setForeground(SWTResourceManager.getColor(220, 20, 60));
		textID.setBackground(SWTResourceManager.getColor(245, 245, 220));
		textID.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		textID.setBounds(33, 10, 24, 41);
        
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/questions", "root", "password");
			stat = connection.createStatement();
            rs = stat.executeQuery("SELECT * FROM quest;");
            if(rs.next()) {
            textID.setText(rs.getString("id"));
            textQuestion.setText(rs.getString("Question"));
            textA.setText(rs.getString("A"));
            textB.setText(rs.getString("B"));
            textC.setText(rs.getString("C"));
            textD.setText(rs.getString("D"));}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
