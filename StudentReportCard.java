import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class StudentReportCard extends JFrame {
    private JTextField rollNoField;
    private JTextField nameField;
    private JTextField mark1Field;
    private JTextField mark2Field;
    private JTextField mark3Field;
    private JButton calculateButton;

    public StudentReportCard() {
        setTitle("Student Report Card");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel rollNoLabel = new JLabel("Roll No:");
        rollNoLabel.setBounds(20, 20, 80, 20);
        add(rollNoLabel);

        rollNoField = new JTextField();
        rollNoField.setBounds(100, 20, 150, 20);
        add(rollNoField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 50, 80, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 50, 150, 20);
        add(nameField);

        JLabel mark1Label = new JLabel("Subject 1:");
        mark1Label.setBounds(20, 80, 80, 20);
        add(mark1Label);

        mark1Field = new JTextField();
        mark1Field.setBounds(100, 80, 50, 20);
        add(mark1Field);

        JLabel mark2Label = new JLabel("Subject 2:");
        mark2Label.setBounds(20, 110, 80, 20);
        add(mark2Label);

        mark2Field = new JTextField();
        mark2Field.setBounds(100, 110, 50, 20);
        add(mark2Field);

        JLabel mark3Label = new JLabel("Subject 3:");
        mark3Label.setBounds(20, 140, 80, 20);
        add(mark3Label);

        mark3Field = new JTextField();
        mark3Field.setBounds(100, 140, 50, 20);
        add(mark3Field);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(100, 170, 100, 30);
        add(calculateButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rollNo = Integer.parseInt(rollNoField.getText());
                String name = nameField.getText();
                int mark1 = Integer.parseInt(mark1Field.getText());
                int mark2 = Integer.parseInt(mark2Field.getText());
                int mark3 = Integer.parseInt(mark3Field.getText());

                int[] marks = {mark1, mark2, mark3};
                Student student = new Student(rollNo, name, marks);
                JOptionPane.showMessageDialog(null, studentReport(student));
            }
        });

        setVisible(true);
    }

    private String studentReport(Student student) {
        return "Roll No: " + student.getRollNo() +
                "\nName: " + student.getName() +
                "\nSubject Marks: " + Arrays.toString(student.getSubjectMarks()) +
                "\nTotal Marks: " + student.computeTotalMarks() +
                "\nPercentage: " + student.computePercentage() + "%" +
                "\nGrade: " + student.computeGrade();
    }

    public static void main(String[] args) {
        new StudentReportCard();
    }
}
