package swingUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

class Employee {
    private String name;
    private String department;
    private String id;

    public Employee(String name, String department, String id) {
        this.name = name;
        this.department = department;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Department: " + department + ", ID: " + id;
    }
}

public class Main {

    public static void main(String[] args) {
        HashMap<String, Employee> empData = new HashMap<>();

        JFrame f1 = new JFrame("Employee Details");
        f1.setVisible(true);
        f1.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));

        JLabel name = new JLabel("Name: ");
        JTextField t1 = new JTextField();
        JLabel messageLabel1 = new JLabel("");

        JLabel department = new JLabel("Department: ");
        JTextField t2 = new JTextField();

        JLabel id = new JLabel("ID: ");
        JTextField t3 = new JTextField();
        JLabel messageLabel = new JLabel("");


        JButton submitButton = new JButton("Submit");
        JButton readFile = new JButton("Read File");
        
        t3.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInput();
            }

            private void validateInput() {
                String text = t3.getText(); 
               
                if (text.matches("\\d*")) { // Allow only digits
                    messageLabel.setText("Valid input.");
                    messageLabel.setForeground(Color.black);
                }else if (!text.matches("\\d*")) {
                	 messageLabel.setText("Invalid input. Numbers only.");
                     messageLabel.setForeground(Color.red);
                }
                
                if (text.matches("")) {
                	messageLabel.setText("");
                }
            }
        });
        
        t1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInput();
            }

            private void validateInput() {
                String text1 = t1.getText(); 
                
                if (!text1.matches("[a-zA-Z]+")) {
                 	messageLabel1.setText("No digits allowed");
                 	messageLabel1.setForeground(Color.red);
                 } else if (text1.matches("[a-zA-Z]+")){
                 	messageLabel1.setText("Valid input");
                 	messageLabel1.setForeground(Color.black);
                 }else {
                 	messageLabel1.setText("");
                 }
                
                if (text1.matches("")) {
                	messageLabel1.setText("");
                }
            }
        });
        
        panel.add(name);
        panel.add(t1);
        panel.add(department);
        panel.add(t2);
        panel.add(id);
        panel.add(t3);
        panel.add(submitButton);
        panel.add(readFile);
        panel.add(messageLabel1);
        panel.add(messageLabel);
        
        f1.add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empId = t3.getText().trim();

                Employee employee = new Employee(t1.getText().trim(), t2.getText().trim(), empId);
                empData.put(empId, employee);
                
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\admin\\Desktop\\hb./employee_data.txt"))) {
                    for (Employee emp : empData.values()) {
                        writer.write(emp.toString());
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(f1, "File created successfully!");
                    writer.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(f1, "Error creating file" );
                }
            }
        });


        readFile.addActionListener(new ActionListener () {
        	public void actionPerformed(ActionEvent e) {
        		try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\hb./employee_data.txt"))) {
        			System.out.println(reader);
        		} catch (Exception ex) {
        			
        		}
        	}
        });
    }
}

/* NOTES:
    -Real-time validation: Use DocumentListener for dynamic feedback as the user types.
	-Focus-based validation: Use InputVerifier for validation when focus leaves the field.
 */
 