package EFUG;

import javax.swing.*;
import java.awt.event.*;

public class ResultOfBmi extends JFrame implements  ActionListener {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JLabel fullName;
    private JLabel resultBmi;
    private JLabel currentWeghit;
    private JLabel idealWeghit;

    public ResultOfBmi(String name ,String weightStatus, int weight, double idealWeight) {

        add(contentPane);
        setSize(250,250);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        this.fullName.setText(name);
        this.resultBmi.setText(weightStatus);
        this.currentWeghit.setText(weight + "");
        this.idealWeghit.setText(idealWeight + "");

    }



    private void onCancel() {
        this.setVisible(false);
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
