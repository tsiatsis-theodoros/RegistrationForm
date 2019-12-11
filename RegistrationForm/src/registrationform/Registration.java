package registrationform;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Registration {

	public static void main(String[] args) {
		Connection c=null;
		Statement stmt=null;
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Connecting to a selected database...");
			c=DriverManager.getConnection("jdbc:sqlite:C:/Users/bitscavridis/Documents/registrationform.sqlite");
			System.out.println("Opened database ");
			System.out.println("Creating table in given database...");
			stmt =c.createStatement();
			String sql= "CREATE TABLE IF NOT EXISTS REGISTRATIONS" + "(FIRSTNAME VARCHAR(50) NOT NULL," + "LASTNAME VARCHAR(50) NOT NULL," + "EMAIL VARCHAR(50))";stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
			stmt.close();
			c.close();
		}
		catch(Exception e) {
			System.err.println(e.getClass().getName()+":"+e.getMessage());
			System.exit(0);
		}
		System.out.println("Table is Created");
    	

    	// Create frame with title Registration Demo
        JFrame frame= new JFrame(); 
        frame.setTitle("JFrame Registration Demo");
        
        
        // Panel to define the layout. We are using GridBagLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
 
        JPanel headingPanel = new JPanel();
        JLabel headingLabel = new JLabel("Please insert your details");
        headingPanel.add(headingLabel);
        
        
        // Panel to define the layout. We are using GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        // Constraints for the layout
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(5, 5, 5, 5);     
        constr.anchor = GridBagConstraints.WEST;
        
        
        // Set the initial grid values to 0,0
        constr.gridx=0;
        constr.gridy=0;
  
        // Declare the required Labels
        JLabel firstNameLabel = new JLabel("Enter your name :");
        JLabel lastNameLabel = new JLabel("Enter your surname :");
        JLabel emailLabel = new JLabel("Enter email :");
         
        // Declare Text fields
        JTextField firstNameTxt = new JTextField(20);
        JTextField lastNameTxt = new JTextField(20);
        JTextField emailTxt = new JTextField(20);
         
        panel.add(firstNameLabel, constr);
        constr.gridx=1;
        panel.add(firstNameTxt, constr);
        constr.gridx=0; constr.gridy=1;
         
        panel.add(lastNameLabel, constr);
        constr.gridx=1;
        panel.add(lastNameTxt, constr);
        constr.gridx=0; constr.gridy=2;
         
        panel.add(emailLabel, constr);
        constr.gridx=1;
        panel.add(emailTxt, constr);
        constr.gridy=3;
         
        constr.gridwidth = 2;
        constr.anchor = GridBagConstraints.CENTER;
  
        // Button with text "Register"
        JButton register = new JButton("Register");

        // add a listener to button
        register.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		String fn = firstNameTxt.getText();
        		String ln = lastNameTxt.getText();
        		String em = emailTxt.getText();
        		System.out.println(fn);
        		System.out.println(ln);
        		System.out.println(em);
        		Connection c=null;
        		Statement stmt=null;
    			try {
    			
    				Class.forName("org.sqlite.JDBC");
    				System.out.println("Connecting to a selected database...");
    				c=DriverManager.getConnection("jdbc:sqlite:C:/Users/bitscavridis/Documents/registrationform.sqlite");
    				System.out.println("Opened database ");
    				stmt =c.createStatement();
    				stmt.executeUpdate("INSERT INTO REGISTRATIONS " + "VALUES ('" + fn + "', '" + ln + "', '" + em + "')");
    				System.out.println("Values inserted in the table!!!");
    				stmt.close();
    				c.close();
    			}
    			catch(Exception ex) {
    				System.err.println(e.getClass().getName()+":"+ex.getMessage());
    				System.exit(0);
    			}
    		
			
            headingLabel.setText("Thanks for registering. We'll get back to you shortly.");
            firstNameTxt.setText("");
            lastNameTxt.setText("");
            emailTxt.setText("");
          }
        });
        
 
  
        // Add label and button to panel
        panel.add(register, constr);
               
        mainPanel.add(headingPanel);
        mainPanel.add(panel);
 
        // Add panel to frame
        frame.add(mainPanel);
        frame.pack();
                frame.setSize(400, 400);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

	}

}
