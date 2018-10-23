import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;


public class AdminLogin {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AdminLogin window = new AdminLogin();
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
		shell = new Shell(SWT.CLOSE | SWT.MIN | SWT.TITLE | SWT.APPLICATION_MODAL );
		shell.setBackgroundImage(SWTResourceManager.getImage("F:\\Backup\\Eclpise\\QuizApp\\src\\KBC.jpg"));
		shell.setImage(SWTResourceManager.getImage("F:\\Backup\\Eclpise\\QuizApp\\src\\KBC1.jpg"));
		shell.setSize(450, 275);
		shell.setText("ADMIN MENU");
		
		Button AQ = new Button(shell, SWT.NONE);
		AQ.setAlignment(SWT.LEFT);
		AQ.setBackground(SWTResourceManager.getColor(245, 245, 220));
		AQ.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		AQ.setForeground(SWTResourceManager.getColor(220, 20, 60));
		AQ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				shell.dispose();
				AddQuestion aq= new AddQuestion();
				aq.open();
			}
		});
		AQ.setBounds(4, 13, 150, 25);
		AQ.setText("Add Questions");
		
		Button LQ = new Button(shell, SWT.NONE);
		LQ.setAlignment(SWT.LEFT);
		LQ.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		LQ.setBackground(SWTResourceManager.getColor(245, 245, 220));
		LQ.setForeground(SWTResourceManager.getColor(220, 20, 60));
		LQ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				shell.dispose();
				ListQuestions lq = new ListQuestions();
				lq.open();
			}
		});
		LQ.setBounds(4, 44, 150, 25);
		LQ.setText("List of Questions");
		
		Button AU = new Button(shell, SWT.NONE);
		AU.setAlignment(SWT.LEFT);
		AU.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		AU.setBackground(SWTResourceManager.getColor(245, 245, 220));
		AU.setForeground(SWTResourceManager.getColor(220, 20, 60));
		AU.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				shell.dispose();
				AddUser au =new AddUser();
				au.open();
			}
		});
		AU.setBounds(4, 75, 150, 25);
		AU.setText("Add User");
		
		Button LU = new Button(shell, SWT.NONE);
		LU.setAlignment(SWT.LEFT);
		LU.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.NORMAL));
		LU.setBackground(SWTResourceManager.getColor(245, 245, 220));
		LU.setForeground(SWTResourceManager.getColor(220, 20, 60));
		LU.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				shell.dispose();
				ListUsers lu = new ListUsers();
				lu.open();
			}
		});
		LU.setBounds(4, 106, 150, 25);
		LU.setText("List of Users");
		
		Button btnBack = new Button(shell, SWT.NONE);
		btnBack.setFont(SWTResourceManager.getFont("Times New Roman", 9, SWT.NORMAL));
		btnBack.setForeground(SWTResourceManager.getColor(220, 20, 60));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				shell.dispose();
				MainLogin ml= new MainLogin();
				ml.open();
			}
		});
		btnBack.setBounds(359, 211, 75, 25);
		btnBack.setText("Back");

	}
	



}
