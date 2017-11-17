package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class Controller {

    @FXML


    public TextField display;

    @FXML

    public Button button1;

    @FXML

    public Button button2;

    @FXML

    public Button button3;

    @FXML

    public Button button4;

    @FXML

    public Button button5;

    @FXML

    public Button button6;

    @FXML

    public Button button7;

    @FXML

    public Button button8;

    @FXML

    public Button button9;

    @FXML

    public Button button0;

    @FXML

    public Button buttonSum;

    @FXML

    public Button buttonEqual;

    @FXML

    public Button buttonMulti;

    @FXML

    public Button buttonDev;

    @FXML

    public Button buttonMinus;

    @FXML

    public Button buttonDot;

    @FXML

    public Button buttonAC;

    @FXML

    public Button buttonRoot;

    @FXML

    public Button buttonCos;

    @FXML

    public Button buttonSin;

    @FXML

    public Button buttonPowerMinusOne;

    float memo = 0;

    int operation = -1;

    @FXML
    public void processNumpad(ActionEvent event) {

        if (event.getSource() == button1) {

            display.setText(display.getText() + "1");

        }

        else if (event.getSource() == button2) {

            display.setText(display.getText() + "2");

        }

        else if (event.getSource() == button3) {

            display.setText(display.getText() + "3");

        }

        else if (event.getSource() == button4) {

            display.setText(display.getText() + "4");

        }

        else if (event.getSource() == button5) {

            display.setText(display.getText() + "5");

        }

        else if (event.getSource() == button6) {

            display.setText(display.getText() + "6");

        }

        else if (event.getSource() == button7) {

            display.setText(display.getText() + "7");

        }

        else if (event.getSource() == button8) {

            display.setText(display.getText() + "8");

        }


        else if (event.getSource() == button9) {

            display.setText(display.getText() + "9");

        }



        else if (event.getSource() == button0) {

            display.setText(display.getText() + "0");

        }

        else if (event.getSource() == buttonDot) {

            display.setText(display.getText() + ".");

        }


        else if (event.getSource() == buttonAC) {

            memo = 0;
            display.setText(" ");

        }



        else if (event.getSource() == buttonSum) {

            memo = Float.parseFloat(display.getText());
            operation = 1;
            display.setText(" ");

        }

        else if (event.getSource() == buttonMulti) {

            memo = Float.parseFloat(display.getText());
            operation = 2;
            display.setText(" ");

        }

        else if (event.getSource() == buttonDev) {

            memo = Float.parseFloat(display.getText());
            operation = 3;
            display.setText(" ");
        }

        else if (event.getSource() == buttonMinus) {

            memo = Float.parseFloat(display.getText());
            operation = 4;
            display.setText(" ");
        }

        else if (event.getSource() == buttonRoot) {

            memo = Float.parseFloat(display.getText());
            Double Root = Math.sqrt(memo);

            DecimalFormat format = new DecimalFormat("#.00");
            String formattedText = format.format(Root);
            display.setText(formattedText);

        }

        else if (event.getSource() == buttonCos) {

            memo = Float.parseFloat(display.getText());
            Double Cos = Math.cos(memo);

            DecimalFormat format = new DecimalFormat("#0.00");
            String formattedText = format.format(Cos);
            display.setText(formattedText);

        }

        else if (event.getSource() == buttonSin) {

            memo = Float.parseFloat(display.getText());
            Double Sin = Math.sin(memo);

            DecimalFormat format = new DecimalFormat("#0.00000000000");
            String formattedText = format.format(Sin);
            display.setText(formattedText);

        }

        else if (event.getSource() == buttonPowerMinusOne) {

            memo = Float.parseFloat(display.getText());
            Float Power = 1/memo;

            DecimalFormat format = new DecimalFormat("#0.00000000000");
            String formattedText = format.format(Power);
            display.setText(formattedText);

        }

        else if (event.getSource() == buttonEqual) {

            Float theSecondOpearand = Float.parseFloat(display.getText());

            switch (operation) {

                case 1:
                    Float Sum = memo + theSecondOpearand;
                    display.setText(String.valueOf(Sum));
                    break;
                case 2:
                    Float Multi = memo * theSecondOpearand;
                    display.setText(String.valueOf(Multi));
                    break;
                case 3:
                    Float dev = memo / theSecondOpearand;
                    display.setText(String.valueOf(dev));
                    break;
                case 4:
                    Float Minus = memo - theSecondOpearand;
                    display.setText(String.valueOf(Minus));
                    break;
            }

        }
    }


    }




