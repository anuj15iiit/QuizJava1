import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
public class ListQuestions {

	protected Shell shell;
	private Table table;
	public Connection connection;
    public static int j;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ListQuestions window = new ListQuestions();
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
		shell.setSize(775, 333);
		shell.setText("List of Questions");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		table.setBackground(SWTResourceManager.getColor(245, 245, 220));
		table.setForeground(SWTResourceManager.getColor(220, 20, 60));
		table.setBounds(10, 40, 754, 223);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/questions", "root", "password");
			 Statement stmt = connection.createStatement();
	          ResultSet rs = stmt.executeQuery("SELECT * FROM quest");
	
    			TableColumn tblclmnNos = new TableColumn(table, SWT.NONE);
    			tblclmnNos.setWidth(40);
    			//tblclmnNos.setText("Questions");
    			
    			TableColumn tblclmnEno = new TableColumn(table, SWT.NONE);
    			tblclmnEno.setWidth(250);
    			//tblclmnEno.setText("A");
    			
    			TableColumn tblclmnNos1 = new TableColumn(table, SWT.NONE);
    			tblclmnNos1.setWidth(100);
    			//tblclmnNos.setText("B");
    			
    			TableColumn tblclmnEno1 = new TableColumn(table, SWT.NONE);
    			tblclmnEno1.setWidth(100);
    			//tblclmnEno.setText("C");
    			
    			TableColumn tblclmnNos2 = new TableColumn(table, SWT.NONE);
    			tblclmnNos2.setWidth(100);
    			//tblclmnNos.setText("D");
    			
    			TableColumn tblclmnEno2 = new TableColumn(table, SWT.NONE);
    			tblclmnEno2.setWidth(100);
    			//tblclmnEno.setText("correct");
    			TableColumn tblclmnEno3 = new TableColumn(table, SWT.NONE);
    			tblclmnEno3.setWidth(60);
    			//tblclmnEno.setText("correct"); 
         
         while (rs.next()) {
        	TableItem item1 = new TableItem(table, SWT.NONE);
        	item1.setText(new String[] {
        	 rs.getString("id"),
             rs.getString("Question"),
             rs.getString("A"),
             rs.getString("B"),
             rs.getString("C"),
             rs.getString("D"),
             rs.getString("correct")});
        	table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
        			1));
		}
		} catch (SQLException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Button btnBack = new Button(shell, SWT.NONE);
		btnBack.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnBack.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				shell.dispose();
				AdminLogin al = new AdminLogin();
				al.open();
			}
		});
		btnBack.setBounds(689, 269, 75, 25);
		btnBack.setText("Back");
		
		
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				int i=table.getSelectionIndex();
				 j=i+1;
				String k = Integer.toString(j);
			    
				PreparedStatement ps,ps1,his,his1,his2,his3;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/questions", "root", "password");
					ps= connection.prepareStatement("DELETE FROM quest WHERE `id`=?");
					ps.setString(1,k);
					int result = ps.executeUpdate();
					ps1= connection.prepareStatement("DELETE FROM ur WHERE `id`=?");
					ps1.setString(1,k);
					int result1 = ps1.executeUpdate();
					his= connection.prepareStatement("ALTER TABLE quest DROP `id`;");
					his.executeUpdate();
                    his1= connection.prepareStatement(" ALTER TABLE quest ADD `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,ADD PRIMARY KEY (`id`);");
		            his1.executeUpdate();
		            his2= connection.prepareStatement("ALTER TABLE ur DROP `id`;");
					his2.executeUpdate();
                    his3= connection.prepareStatement(" ALTER TABLE ur ADD `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,ADD PRIMARY KEY (`id`);");
		            his3.executeUpdate();
					if(result==1&&result1==1) {
						JFrame frame =new JFrame();
						frame.setVisible(true);
						JOptionPane.showMessageDialog(frame,"Question Deleted","", JOptionPane.PLAIN_MESSAGE);
						frame.setVisible(false);
						try {
							Class.forName("com.mysql.jdbc.Driver");
							connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/questions", "root", "password");
							 Statement stmt = connection.createStatement();
					          ResultSet rs = stmt.executeQuery("SELECT * FROM quest");
					          table.removeAll();
			                   
			        	
				         
				         while (rs.next()) {
				        	TableItem item1 = new TableItem(table, SWT.NONE);
				        	item1.setText(new String[] {
				        	 rs.getString("id"),
				             rs.getString("Question"),
				             rs.getString("A"),
				             rs.getString("B"),
				             rs.getString("C"),
				             rs.getString("D"),
				             rs.getString("correct")});
				        	table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				        			1));
						}
						} catch (SQLException | ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(SWTResourceManager.getColor(255, 0, 0));
		btnNewButton.setForeground(SWTResourceManager.getColor(245, 245, 220));
		btnNewButton.setBounds(112, 269, 96, 25);
		btnNewButton.setText("Delete Question");
		
		Label lblId = new Label(shell, SWT.NONE);
		lblId.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblId.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblId.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lblId.setBounds(10, 10, 25, 24);
		lblId.setText("Id");
		
		Label lblQuestions = new Label(shell, SWT.NONE);
		lblQuestions.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lblQuestions.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblQuestions.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblQuestions.setBounds(51, 10, 86, 24);
		lblQuestions.setText("Questions");
		
		Label lblOptionA = new Label(shell, SWT.NONE);
		lblOptionA.setText("Option A");
		lblOptionA.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblOptionA.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lblOptionA.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblOptionA.setBounds(299, 10, 75, 24);
		
		Label lblOptionb = new Label(shell, SWT.NONE);
		lblOptionb.setText("OptionB");
		lblOptionb.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblOptionb.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lblOptionb.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblOptionb.setBounds(400, 10, 80, 24);
		
		Label lblOptionC = new Label(shell, SWT.NONE);
		lblOptionC.setText("Option C");
		lblOptionC.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblOptionC.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lblOptionC.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblOptionC.setBounds(500, 10, 75, 24);
		
		Label lblOptionD = new Label(shell, SWT.NONE);
		lblOptionD.setText("Option D");
		lblOptionD.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblOptionD.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lblOptionD.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblOptionD.setBounds(600, 10, 75, 24);
		
		Label lblCorrectAnswer = new Label(shell, SWT.NONE);
		lblCorrectAnswer.setText("Answer");
		lblCorrectAnswer.setForeground(SWTResourceManager.getColor(220, 20, 60));
		lblCorrectAnswer.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		lblCorrectAnswer.setBackground(SWTResourceManager.getColor(245, 245, 220));
		lblCorrectAnswer.setBounds(702, 10, 58, 24);
		
		Button btnEditQuestion = new Button(shell, SWT.NONE);
		btnEditQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				int i=table.getSelectionIndex();
			    j=i+1;
				String k = Integer.toString(j);
			    
				PreparedStatement ps,ps1;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/questions", "root", "password");
					ps= connection.prepareStatement("SELECT * FROM quest WHERE `id`=?");
					ps.setString(1,k);
					ResultSet result = ps.executeQuery();
					ps1= connection.prepareStatement("SELECT * FROM ur WHERE `id`=?");
					ps1.setString(1,k);
					ResultSet result1 = ps1.executeQuery();
					if(result.next()&&result1.next()) {
						shell.dispose();
						EditQuestion eq= new EditQuestion();
						eq.open();
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEditQuestion.setBackground(SWTResourceManager.getColor(0, 0, 255));
		btnEditQuestion.setForeground(SWTResourceManager.getColor(245, 245, 220));
		btnEditQuestion.setBounds(10, 269, 96, 25);
		btnEditQuestion.setText("Edit Question");
		
		

	}
}
