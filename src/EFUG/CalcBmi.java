package EFUG;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class CalcBmi extends JFrame implements  ActionListener {
    private JPanel contentPane;
    private JButton buttonSend;
    private JButton buttonCancel;
    private JTextField textFieldFirstName;
    private JTextField textFieldLastName;
    private JTextField textFieldAge;
    private JRadioButton manRadioButton;
    private JRadioButton womanRadioButton;
    private JSlider slider1;
    private JRadioButton smallRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton largeRadioButton;
    private JLabel valueHeight;
    private JSlider slider2;
    private JLabel valueOfWeigt;
    private  double slimness;


    public CalcBmi() {
        //setContentPane(contentPane);

        getRootPane().setDefaultButton(buttonSend);
        this.slimness=0;
        this.add(contentPane);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 480);
        this.setVisible(true);
        this.setResizable(false);

        addTextFieldsListeners();

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                 valueHeight.setText(slider1.getValue() + "cm");

            }
        });
        manRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                womanRadioButton.setSelected(false);
            }
        });
        womanRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manRadioButton.setSelected(false);
            }
        });


        smallRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               slimness=Constants.SMALL;
                mediumRadioButton.setSelected(false);
                largeRadioButton.setSelected(false);

            }
        });
        mediumRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slimness=Constants.MEDIUM;
                smallRadioButton.setSelected(false);
                largeRadioButton.setSelected(false);

            }
        });
        largeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slimness=Constants.LARGE;
                smallRadioButton.setSelected(false);
                mediumRadioButton.setSelected(false);
            }
        });

        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                valueOfWeigt.setText(slider2.getValue() + "Kg");

            }
        });
    }





    private void onOK() {
        this.setVisible(false);
        ResultOfBmi resultOfBmi= new ResultOfBmi(this.getName(), this.resultBmi(), this.getWeight(), this.resultOfIdealWeight());
    }
    public String resultBmi(){
        double bmi =calculateBmi();
        String result="";

        if (bmi<=15){
          result=Constants.ANOREXIC;

        }else if (bmi>15 && bmi<18.5){
            result=Constants.UNDERWEIGHT;
        }else if (bmi>18.5 && bmi<24.9){
            result=Constants.NORMAL;

        }else if (bmi> 25 && bmi < 29.9){
            result=Constants.OVERWEIGHT;

        }else if (bmi > 30 && bmi < 35 ){
            result=Constants.OBESE;

        }else if (bmi >= 35){
            result = Constants.EXTREME_OBESE;
        }

       return result;
    }

    public double resultOfIdealWeight (){
        double idealWeight=0;
        double height=(double)this.slider1.getValue();
        double age= Double.parseDouble(this.textFieldAge.getText());
        idealWeight=(height-Constants.HUNDRED + (age/Constants.TEN)) *Constants.SMALL*this.slimness;
      return idealWeight;
    }

    public String getName(){
        return this.textFieldFirstName.getText() + " " + this.textFieldLastName.getText();
    }

    private double calculateBmi(){
        double height= (double) this.slider1.getValue()/100;
        double weight=(double) this.slider2.getValue();

        return weight/Math.pow(height,2);
    }

    public int getWeight(){
        return this.slider2.getValue();

    }


    private void onCancel() {

        this.setVisible(false);
    }

    private  void addTextFieldsListeners(){
        buttonSend.addMouseListener(new  MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getSource()==buttonSend) {
                    try {
                        Thread.sleep(10);
                        onOK();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }

                }
            }
        });
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonSend){
           this.onOK();
        }
    }
}
