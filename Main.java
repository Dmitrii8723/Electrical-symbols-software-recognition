package sample;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.StrictMath.round;
import static java.lang.System.out;
import static sample.VVandWW.*;


public class Main extends Application{

    /**
     * Resets the canvas to its original look by filling in a rectangle covering
     * its entire width and height. Color.BLUE is used in this demo.
     *
     * @param canvas The canvas to reset
     * @param color The color to fill
     */

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    public int[][] test = new int[12][24];
    public int[] Newtest = new int[289];
    private Component frame;
    public double dY1;
    public int YmaxCurv = 0;


   static Canvas canvas;
   static GraphicsContext gc;


    public void reset(Canvas canvas, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

    }



        @Override

        public void start (Stage primaryStage){
            primaryStage.setTitle("Electrical drawings software recognition");
            Group root = new Group();

            canvas = new Canvas(1440, 720);
            gc = canvas.getGraphicsContext2D();

            // Draw background with gradient
            Rectangle rect = new Rectangle(1540, 1004);
            drawBackground(rect);
            root.getChildren().add(rect);

            // Creating the first button
            button1 = new Button();
            button1.setText("Open the final drawing");
            button1.setFont(javafx.scene.text.Font.font(15));
            button1.setTranslateX(50);
            button1.setTranslateY(854);
            button1.setPrefSize(400, 80);
            root.getChildren().add(button1);

            // Creating the second button
            button2 = new Button();
            button2.setText("Clean the plot and the final drawing document");
            button2.setFont(javafx.scene.text.Font.font(15));
            button2.setTranslateX(1090);
            button2.setTranslateY(854);
            button2.setPrefSize(400, 80);
            root.getChildren().add(button2);

            // Creating the third button
            button3 = new Button();
            button3.setText("Reverse the last step");
            button3.setFont(javafx.scene.text.Font.font(15));
            button3.setTranslateX(50);
            button3.setTranslateY(40);
            button3.setPrefSize(200, 40);
            root.getChildren().add(button3);



            // Create the Canvas, filled in with Blue

            canvas.setTranslateX(50);
            canvas.setTranslateY(100);
            reset(canvas, Color.WHITE);


            {
                // vertical lines
                //  gc.setStroke(Color.BLUE);
                //  for(int i = 0 ; i < 1200 ; i+=4){
                //      gc.strokeLine(i, 0, i, 1200 - (1200%4));
                //  }

                // horizontal lines
                //   gc.setStroke(Color.BLUE);
                //   for(int i = 0 ; i < 600 ; i+=4){
                //       gc.strokeLine(0, i, 1200, i);
                //   }
            }
            int[][] test = new int[12][24];
            for (int k = 0; k <= 11; k++) {
                for (int z = 0; z <= 23; z++) {
                    test[k][z] = -1;
                }
            }


            double[] X = new double[10000];
            double[] Y = new double[10000];
            double[] X1 = new double[10000];
            double[] Y1 = new double[10000];


            // Fill portions as the user drags the mouse
            canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {

                public int x = 0;
                public int y = 0;


                @Override


                public void handle(MouseEvent e) {

                    if (Y[0] == 0.0) {
                        x = 0;
                        y = 0;
                    }

                    X[x] = e.getX();
                    out.print(X[x] + " ");

                    x++;

                    out.print(x + " ");

                    Y[y] = e.getY();
                    out.print(Y[y] + " ");

                    y++;

                    gc.setFill(Color.BLACK);
                    gc.fillRect((round(e.getX()) / 2) * 2, (round(e.getY()) / 2) * 2, 2, 2);


                }

            });


            canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {


                public double Cy;
                public double Cx;
                public double Xzero;
                public double Yzero;
                public double Xfinal;
                public double Yfinal;

                @Override

                public void handle(MouseEvent t) {


                    if (t.getClickCount() == 1) {


                        for (int k = 0; k <= 11; k++) {
                            for (int z = 0; z <= 23; z++) {
                                test[k][z] = -1;
                            }
                        }


                        out.println();
                        out.println();


                        int sum = 0;
                        for (int y = 0; y <= 9999; y++) {


                            if (Y[y] != 0.0) {

                                sum = sum + 1;
                                //      System.out.print(sum + " ");
                            }

                        }

                        double K = Y[sum - 1] - Y[0];
                        double K2 = X[sum - 1] - X[0];


                        double Gap = 1.33 * (K / 12);


                        out.println();
                        //        System.out.print(Gap);
                        out.println();


                        //////////////////////////////////////////////////// FOR THE LINE FROM THE TOP TO THE BOTTOM/////////////////////////////////////////////////////////////////////////////////////////////////////

                        if (K > 10 && (X[sum - 1] - X[0]) < 10 && (X[sum - 1] - X[0]) > -10) {


                            for (double i = Y[0] - 2 * (K / 12); i < Y[0] + 2 * (K / 12) + K; i += 1.33 * (K / 12)) {

                                double sumI = Gap;

                                sumI = sumI + i;

                                for (double j = X[0] - 4 * (K / 12); j < (X[0] + (K / 12) * 24) + 4 * (K / 12); j += 1.33 * (K / 12)) {

                                    double sumJ = Gap;
                                    sumJ = sumJ + j;

                                    int count = 0;
                                    for (int y = 0; y <= sum - 1; y++) {

                                        if ((Y[y] >= i && Y[y] <= sumI) && (X[y] >= j && X[y] <= sumJ) && (count == 0)) {

                                            test[(int) ((sumI - Y[0] + 2 * 1.33 * (K / 12)) / Gap - 1)][(int) ((sumJ - X[0] + 4 * 1.33 * (K / 12)) / Gap - 1)] = 1;

                                            count++;
                                        }


                                    }

                                }





                            }

                            //Horisontal

                            //     for (double i = Y[0] - 2 * (K / 12); i < Y[0] + 2 * (K / 12) + K; i += 1.33 * (K / 12)) {

                            //         gc.setStroke(Color.BLUE);

                            //         gc.strokeLine(X[0] - 4 * (K / 12), i, X[0] + ((K / 12) * 24) + 4 * (K / 12), i);
                            //     }


                            //Vertical
                            //       for (double j = X[0] - 4 * (K / 12); j < (X[0] + (K / 12) * 24) + 4 * (K / 12); j += 1.33 * (K / 12)) {


                            //          gc.setStroke(Color.BLUE);

                            //           gc.strokeLine(j, Y[0] - 2 * (K / 12), j, Y[0] + K + 2 * (K / 12));


                            //       }

                        }

//////////////////////////////////////////////////////////////////// FOR THE LINE FROM THE BOTTOM TO THE TOP//////////////////////////////////////////////////////

                        if (K < -10 && (X[sum - 1] - X[0]) < 10 && (X[sum - 1] - X[0]) > -10) {


                            for (double i = Y[0] - 2 * (K / 12); i > Y[0] + 2 * K / 12 + K; i += 1.33 * (K / 12)) {

                                double sumI = Gap;

                                sumI = i + sumI;


                                for (double j = X[0] + 4 * (K / 12); j < X[0] - ((K / 12) * 24) - 4 * (K / 12); j -= 1.33 * (K / 12)) {

                                    double sumJ = Gap;
                                    sumJ = j - sumJ;

                                    int count = 0;
                                    for (int y = 0; y <= sum - 1; y++) {

                                        if ((Y[y] <= i && Y[y] >= sumI) && (X[y] >= j && X[y] <= sumJ) && (count == 0)) {

                                            test[(int) ((sumI - Y[0] + 3 * (K / 12)) / Gap - 1)][(int) ((sumJ - X[0] - 5 * (K / 12)) / -Gap - 1)] = 1;

                                            count++;
                                        }

                                        //  }


                                    }

                                }
                            }


                            //Horisontal

                            //     for (double i = Y[0] - 2 * (K / 12); i > Y[0] + 2 * K / 12 + K; i += 1.33 * (K / 12)) {

                            //         gc.setStroke(Color.BLUE);

                            //         gc.strokeLine(X[0] + 4 * (K / 12), i, X[0] - ((K / 12) * 24) - 4 * (K / 12), i);
                            //     }


                            //Vertical
                            //     for (double j = X[0] + 4 * (K / 12); j < X[0] - ((K / 12) * 24) - 4 * (K / 12); j -= 1.33 * (K / 12)) {


                            //         gc.setStroke(Color.BLUE);

                            //          gc.strokeLine(j, Y[0] - 2 * (K / 12), j, Y[0] + K + 2 * (K / 12));


                            //      }


                        }


                        ///////////////////////////////////////////////// FOR THE LINE FROM THE LEFT TO THE RIGHT///////////////////////////////////////////////////////////////////////////////////////////////


                        if (K < 10 && ((Y[sum - 1] - Y[0]) > -10) && (X[sum - 1] - X[0]) > 10) {


                            for (double i = Y[0] - 2 * (K2 / 24); i < Y[0] + (K2 / 24) * 12 + 2 * (K2 / 24); i += 1.33 * (K2 / 24)) {

                                double sumI = 1.33 * (K2 / 24);

                                sumI = i + sumI;

                                for (double j = X[0] - 4 * (K2 / 24); j < (X[0] + (K2 / 24) * 24) + 4 * (K2 / 24); j += 1.33 * (K2 / 24)) {

                                    double sumJ = 1.33 * (K2 / 24);
                                    sumJ = j + sumJ;

                                    int count = 0;
                                    for (int y = 0; y <= sum - 1; y++) {

                                        if ((Y[y] >= i && Y[y] <= sumI) && (X[y] >= j && X[y] <= sumJ) && (count == 0)) {

                                            test[(int) ((sumI - Y[0] + 2 * (K2 / 24)) / (1.33 * K2 / 24) - 1)][(int) ((sumJ - X[0] + 5 * (K2 / 24)) / (1.33 * K2 / 24) - 1)] = 1;

                                            count++;
                                        }

                                        //  }


                                    }

                                }
                            }


                            //Horisontal

                            //    for (double i = Y[0] - 2 * ((K2) / 24); i < Y[0] + ((K2)/24)*12 + 2 * ((K2) / 24); i += 1.33 * ((K2) / 24)) {

                            //        gc.setStroke(Color.BLUE);

                            //            gc.strokeLine(X[0] - 4 * ((K2) / 24), i, (X[0] + ((K2) / 24) * 24) + 4 * ((K2) / 24), i);
                            //     }


                            //Vertical
                            //      for (double j = X[0] - 4 * ((K2) / 24); j < (X[0] + ((K2) / 24) * 24) + 4 * ((K2) / 24); j += 1.33 * ((K2) / 24)) {


                            //          gc.setStroke(Color.BLUE);

                            //          gc.strokeLine(j, Y[0] - 2 * ((K2) / 24), j, Y[0] + ((K2)/24)*12 + 2 * ((K2) / 24));

                            //      }


                        }


                        //////////////////////////////////////// FOR THE LINE FROM THE RIGHT TO THE LEFT////////////////////////////////////////////////////////

                        if (K < 10 && ((Y[sum - 1] - Y[0]) > -10) && (X[sum - 1] - X[0]) < -10) {


                            for (double i = Y[0] + 2 * ((K2) / 24); i < Y[0] - ((K2) / 24) * 12 - 2 * ((K2) / 24); i += -1.33 * ((K2) / 24)) {

                                double sumI = 1.33 * (K2 / 24);

                                sumI = i - sumI;

                                for (double j = X[0] - 4 * ((K2) / 24); j > (X[0] + ((K2) / 24) * 24) + 4 * ((K2) / 24); j += 1.33 * ((K2) / 24)) {

                                    double sumJ = 1.33 * (K2 / 24);
                                    sumJ = j + sumJ;

                                    int count = 0;
                                    for (int y = 0; y <= sum - 1; y++) {

                                        if ((Y[y] >= i && Y[y] <= sumI) && (X[y] <= j && X[y] >= sumJ) && (count == 0)) {

                                            test[(int) ((sumI - Y[0] + 4 * (K2 / 24)) / (1.33 * K2 / 24) - 1)][(int) ((sumJ - X[0] + 5 * (K2 / 24)) / (1.33 * K2 / 24) - 1)] = 1;

                                            count++;
                                        }

                                        //  }


                                    }

                                }
                            }


                            //Horisontal

                            //     for (double i = Y[0] + 2 * ((K2) / 24); i < Y[0] - ((K2)/24)*12 - 2 * ((K2) / 24); i += -1.33 * ((K2) / 24)) {

                            //         gc.setStroke(Color.BLUE);

                            //         gc.strokeLine(X[0] - 4 * ((K2) / 24), i, (X[0] + ((K2) / 24) * 24) + 4 * ((K2) / 24), i);
                            //     }


                            //Vertical
                            //      for (double j = X[0] - 4 * ((K2) / 24); j > (X[0] + ((K2) / 24) * 24) + 4 * ((K2) / 24); j += 1.33 * ((K2) / 24)) {


                            //          gc.setStroke(Color.BLUE);

                            //          gc.strokeLine(j, Y[0] + 2 * ((K2) / 24), j, Y[0] - ((K2)/24)*12 - 2 * ((K2) / 24));

                            //      }


                        }


                        //////////////////////////////////////// DIODE (WISECLOCK) X0 and Y0 in the upper corner////////////////////////////////////////////////////


                        //////////////Preparation of the coordinates////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        int dYbig = 0;
                        for (int y = 0; y <= 9998; y++) {

                            if (Y[y + 1] > Y[y]) {

                                dYbig = dYbig + 1;
                                //               System.out.print(dYbig + " ");
                            }

                        }

                        out.println();

                        //////////////Increase on 1///////////////////////////////////////
                        dYbig = dYbig;

                        int dXbig = 0;

                        for (int y = dYbig; y <= 9998; y++) {

                            if (X[y + 1] > X[y]) {

                                dXbig = dXbig + 1;
                                // System.out.print(dXbig + " ");
                            }


                        }


                        dXbig = dXbig + dYbig;

                        dXbig = dXbig;
                        //  System.out.print(dXbig + " ");


                        double dX1;
                        double dX2;
                        double dY2;

                        dY1 = Y[dYbig] - Y[0];

                        out.println();
                        //   System.out.print(dY1 + " ");
                        out.println();

                        dX1 = X[dXbig] - X[dYbig];

                        out.println();
                        //   System.out.print(dX1 + " ");
                        out.println();

                        dY2 = Y[dXbig] - Y[dYbig];

                        out.println();
                        //   System.out.print(dY2 + " ");
                        out.println();

                        dX2 = X[dYbig] - X[0];

                        out.println();
                        //   System.out.print(dX2 + " ");
                        out.println();


                        double dGap = (4 * (dY1 / 12) + dY1) / 12;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        if (dY1 > 10 && dX1 > 10 && dY2 < -10 && dX2 < 10 && dX2 > -10) {


                            for (double i = Y[0] - 2 * (dY1 / 12); i < Y[0] + dY1 + 2 * (dY1 / 12); i += dGap) {

                                double sumI = dGap;

                                sumI = sumI + i;


                                for (double j = X[0] - 7 * (dY1 / 12); j < (X[0] + (dY1 / 12) * 24) + (dY1 / 12); j += dGap) {

                                    double sumJ = dGap;
                                    sumJ = sumJ + j;


                                    int count = 0;
                                    for (int y = 0; y <= sum - 1; y++) {

                                        if ((Y[y] >= i && Y[y] <= sumI) && (X[y] >= j && X[y] <= sumJ) && (count == 0)) {

                                            test[(int) ((sumI - Y[0] + 3 * (dY1 / 12)) / dGap - 1)][(int) ((sumJ - X[0] + 8 * (dY1 / 12)) / dGap - 1)] = 1;
                                            //  int z1 = (int) ((sumI - Y[0] - 2*(dY1/12))/ dGap - 1);
                                            //  int z2 = (int) ((sumJ - X[0] - 8*(dY1/12))/ dGap - 1);
                                            //  System.out.print(z1 + " " + z2);
                                            //  System.out.println();

                                            count++;
                                        }

                                    }

                                }
                            }


                            //Horisontal

                            //    for (double i = Y[0] - 2*(dY1/12); i < Y[0]+dY1+2*(dY1/12); i += dGap) {

                            //       gc.setStroke(Color.BLUE);

                            //       gc.strokeLine(X[0] - 7 * (dY1 / 12), i, (X[0] + (dY1 / 12) * 24) + (dY1 / 12) , i);
                            //    }


                            //Vertical
                            //  for (double j = X[0] - 7 * (dY1 / 12); j < (X[0] + (dY1 / 12) * 24) + (dY1 / 12) ; j += dGap) {


                            //       gc.setStroke(Color.BLUE);

                            //       gc.strokeLine(j, Y[0] - 2*(dY1/12), j, Y[0]+dY1+2*(dY1/12));

                            //   }

                        }


                        ////////////////Circle////////////////////////////////////////////////////////////////////////////////////////


                        int Xmin = 0;
                        int Xmax = 0;
                        int Ymin = 0;
                        int Ymax = 0;

//////////////////////////////////CORRECT///////////////////////////////////////////////////////////////////////////////////////

                        Xmin = 0;


                        out.println();

/////////////////////////////////CORRECT////////////////////////////////////////////////////////////////////////////////////////

                        for (int y = 0; y <= 9998; y++) {

                            if (Y[y + 1] > Y[0]) {

                                Xmax = Xmax + 1;

                                out.print(Xmax + " " + X[Xmax] + " ");
                            }

                        }

                        Xmax = Xmax + 1;
                        out.println();

                        double Diameter;


                        Diameter = X[Xmax] - X[Xmin];

                         System.out.println();
                          System.out.print(Diameter);
                          System.out.println();


                        double C_Gap;

                        C_Gap = ((Diameter / 12) * 16) / 12;


                        if (Diameter > 10) {


                            for (double i = Y[0] - C_Gap * 6; i < Y[0] + C_Gap * 6; i += C_Gap) {

                                double sumI = C_Gap;

                                sumI = sumI + i;

                                // System.out.println();
                                //  System.out.print(Y[0]);
                                //  System.out.println();
                                //  System.out.print(C_Gap);
                                // System.out.println();
                                // System.out.print(i);
                                //  System.out.println();
                                //  System.out.print(sumI);
                                //  System.out.println();

                                for (double j = X[0] - 8 * C_Gap; j < (X[0] + C_Gap * 24) - 8 * C_Gap; j += C_Gap) {

                                    double sumJ = C_Gap;
                                    sumJ = sumJ + j;

                                    //  System.out.println();
                                    //  System.out.print(j);
                                    //  System.out.println();
                                    // System.out.print(sumJ);
                                    // System.out.println();

                                    int count = 0;

                                    for (int y = 0; y <= sum - 1; y++) {

                                        if ((Y[y] >= i && Y[y] <= sumI) && (X[y] >= j && X[y] <= sumJ) && (count == 0)) {

                                            test[(int) ((sumI - Y[0] + 6 * C_Gap) / C_Gap - 1)][(int) ((sumJ - X[0] + 8 * C_Gap) / C_Gap - 1)] = 1;
                                            int z1 = (int) ((sumI - Y[0] + 6 * C_Gap) / C_Gap - 1);
                                            int z2 = (int) ((sumJ - X[0] + 8 * C_Gap) / C_Gap - 1);
                                            out.print(z1 + " " + z2);
                                            out.println();

                                            count++;
                                        }

                                    }

                                }
                            }

/*
         //Horisontal

               for (double i = Y[0] - C_Gap*6; i < Y[0] + C_Gap*6 ; i += C_Gap) {

                   gc.setStroke(Color.BLUE);

                   gc.strokeLine(X[0] - 8*C_Gap, i, (X[0] + C_Gap*24) - 8*C_Gap, i);
               }


         //Vertical
                for (double j = X[0] - 8*C_Gap; j < (X[0] + C_Gap*24) - 8*C_Gap ; j += C_Gap) {


                    gc.setStroke(Color.BLUE);

                    gc.strokeLine(j, Y[0] - C_Gap*6, j, Y[0] + C_Gap*6);

                }



*/


                        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        //////////////////////////////////////////////////////////////////// FOR THE ANGLE FROM THE BOTTOM TO THE TOP and to the right//////////////////////////////////////////////////////




                            if (K < -10 && (X[sum - 1] - X[0]) > 10 && (Y[sum-1] - Y[0]) < -10) {



                                for (double i = Y[0] - 2 * (K / 12); i > Y[0] + 2 * K / 12 + K; i += 1.33 * (K / 12)) {

                                double sumI = Gap;

                                sumI = i + sumI;

                             //   System.out.println();
                              //  System.out.print(Gap);
                              //  System.out.println();

                              //   System.out.println();
                             //    System.out.print(i);
                              //    System.out.println();
                               //   System.out.print(sumI);
                               //   System.out.println();


                                int z2 = 0;

                                for (double j = X[0] + 4 * (K / 12); j < X[0] - ((K / 12) * 24) - 4 * (K / 12); j -= 1.33 * (K / 12)) {

                                    double sumJ = Gap;
                                    sumJ = j - sumJ;

                                 //   System.out.println();
                                 //   System.out.print(j);
                                 //   System.out.println();
                                 //   System.out.print(sumJ);
                                 //   System.out.println();

                                    int count = 0;

                                    for (int y = 0; y <= sum - 1; y++) {

                                        if ((Y[y] <= i && Y[y] >= sumI) && (X[y] >= j && X[y] <= sumJ) && (count == 0) && z2 <= 22) {


                                                test[(int) (12 - ((sumI - Y[0] + 3 * (K / 12)) / Gap - 1))][(int) ((sumJ - X[0] - 5 * (K / 12)) / -Gap - 1)] = 1;
                                                int z1 = (int) (12 - ((sumI - Y[0] + 3 * (K / 12)) / Gap - 1));
                                                z2 = (int) ((sumJ - X[0] - 5 * (K / 12)) / -Gap - 1);
                                                out.print(z1 + " " + z2);
                                                out.println();
                                                count++;

                                        }
                                        //  }


                                    }

                                }
                            }


                            //Horisontal
/*
                                 for (double i = Y[0] - 2 * (K / 12); i > Y[0] + 2 * K / 12 + K; i += 1.33 * (K / 12)) {

                                     gc.setStroke(Color.BLUE);

                                     gc.strokeLine(X[0] + 4 * (K / 12), i, X[0] - ((K / 12) * 24) - 4 * (K / 12), i);
                                 }


                            //Vertical
                                 for (double j = X[0] + 4 * (K / 12); j < X[0] - ((K / 12) * 24) - 4 * (K / 12); j -= 1.33 * (K / 12)) {


                                     gc.setStroke(Color.BLUE);

                                      gc.strokeLine(j, Y[0] - 2 * (K / 12), j, Y[0] + K + 2 * (K / 12));


                                  }

*/
                        }


                        //////////////////////////////////////////////////// FOR THE Angle FROM THE TOP TO THE BOTTOM to the Right/////////////////////////////////////////////////////////////////////////////////////////////////////

                        if (K > 10 && (X[sum - 1] - X[0]) > 10) {


                            for (double i = Y[0] - 2 * (K / 12); i < Y[0] + 2 * (K / 12) + K; i += 1.33 * (K / 12)) {

                                double sumI = Gap;

                                sumI = sumI + i;

                                int z2 = 0;

                                for (double j = X[0] - 4 * (K / 12); j < (X[0] + (K / 12) * 24) + 4 * (K / 12); j += 1.33 * (K / 12)) {

                                    double sumJ = Gap;
                                    sumJ = sumJ + j;

                                    int count = 0;
                                    for (int y = 0; y <= sum - 1; y++) {

                                        if ((Y[y] >= i && Y[y] <= sumI) && (X[y] >= j && X[y] <= sumJ) && (count == 0)&& z2 <= 22) {

                                            test[(int) ((sumI - Y[0] + 2 * 1.33 * (K / 12)) / Gap - 1)][(int) ((sumJ - X[0] + 4 * 1.33 * (K / 12)) / Gap - 1)] = 1;
                                            int z1 = (int) ((sumI - Y[0] + 2 * 1.33 * (K / 12)) / Gap - 1);
                                            z2 = (int) ((sumJ - X[0] + 4 * 1.33 * (K / 12)) / Gap - 1);
                                            out.print(z1 + " " + z2);
                                            out.println();
                                            count++;
                                        }


                                    }

                                }
                            }

/*
                            //Horisontal

                                 for (double i = Y[0] - 2 * (K / 12); i < Y[0] + 2 * (K / 12) + K; i += 1.33 * (K / 12)) {

                                     gc.setStroke(Color.BLUE);

                                     gc.strokeLine(X[0] - 4 * (K / 12), i, X[0] + ((K / 12) * 24) + 4 * (K / 12), i);
                                 }


                            //Vertical
                                   for (double j = X[0] - 4 * (K / 12); j < (X[0] + (K / 12) * 24) + 4 * (K / 12); j += 1.33 * (K / 12)) {


                                      gc.setStroke(Color.BLUE);

                                       gc.strokeLine(j, Y[0] - 2 * (K / 12), j, Y[0] + K + 2 * (K / 12));


                                   }
*/

                        }


                        Cy = Y[sum - 1] - Y[0];
                        Cx = X[sum - 1] - X[0];


                        //Starting Coordinates
                        Xzero = X[0];
                        Yzero = Y[0];


                        Xfinal = X[sum - 1];
                        Yfinal = Y[sum - 1];


                        ////////////////////Rewriting the coordinates from the arrays Y and X to new arrays Y1 and X1//////////////////////////////////

                        for (int y = 0; y <= 9999; y++) {
                            Y1[y] = Y[y];
                            X1[y] = X[y];
                        }


/////////////////////////////////////Refresh the arrays Y and X/////////////////////////////////////////////////////////////////////////////////

                        for (int y = 0; y <= 9999; y++) {
                            Y[y] = 0.0;

                        }

                        for (int x = 0; x <= 9999; x++) {
                            X[x] = 0.0;

                        }


                        int[] Newtest = new int[289];
                        Newtest = new int[Newtest.length + 1];
                        Newtest[288] = -1;

                        int T = 0;
                        for (int k = 0; k <= 11; k++) {
                            for (int z = 0; z <= 23; z++) {

                                Newtest[T] = test[k][z];
                                T = T + 1;
                                // System.out.print(test[k][z] + " ");

                            }
                        }

                        int NewNum = sum -1;

                        out.println();

                        ////////send Array - "Newtest" to the constructor VVandWW////////////////////////////////
                        VVandWW.VVandWW(Newtest, Cy, Cx, Xzero, Yzero, Xfinal, Yfinal, dY1, dX1, dY2, dX2, Diameter, X1, Y1, NewNum);


                        ////////call the class VVandWW////////////////////////////////

                        try {
                            VVandWW.setupMySCVvvANDwwArrays();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }


                        for (int k = 0; k <= 11; k++) {
                            for (int z = 0; z <= 23; z++) {
                                test[k][z] = -1;
                            }
                        }

                    }


                }

            });


            // vertical lines
            gc.setStroke(Color.WHEAT);
            gc.setLineWidth(0.5);
            for (int i = 0; i < 1440; i += 30) {
                gc.strokeLine(i, 0, i, 720 - (720 % 30));
            }

            // horizontal lines
            gc.setStroke(Color.WHEAT);
            gc.setLineWidth(0.5);
            for (int i = 0; i < 720; i += 30) {
                gc.strokeLine(0, i, 1440, i);
            }
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);

            button3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {


                    try {


                        PdfReader reader1 = new PdfReader("Experiment2.pdf");
                        PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream("Experiment1.pdf"));
                        PdfContentByte content = stamper.getOverContent(1);
                        content = stamper.getOverContent(1);
                        stamper.close();

                        PdfReader reader2 = new PdfReader("Experiment1.pdf");
                        stamper = new PdfStamper(reader2, new FileOutputStream("Experiment.pdf"));
                        content = stamper.getOverContent(1);
                        content = stamper.getOverContent(1);
                        stamper.close();







                    } catch (Exception e1) {
                        System.err.println(e1.getMessage());
                    }

////////////////////////////////////////FOR THE ANGLE FROM THE Top TO THE Bottom and to the right///////////////////////////////////////////////////////////

                    if (NewZZ4 > -0.2 &&(Xfinal1 - Xzero1) > 10 && (Yfinal1 - Yzero1) > 10){

                        for  (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);

                            gc.strokeLine(Xzero1, Yzero1, Xzero1, Yfinal1);

                            gc.strokeLine(Xzero1-1, Yfinal1, Xfinal1, Yfinal1);

                            gc.setStroke(Color.BLACK);
                        }

                    }







////////////////////////////////////////For the angle BTR(just a angle BTR)///////////////////////////////////////////////////////////

                    if (NewZZ3 > -0.2 && (Xfinal1 - Xzero1) > 10 && (Yfinal1 - Yzero1) < -10){

                        for  (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);

                            gc.strokeLine(Xzero1, Yzero1, Xzero1, Yfinal1);

                            gc.strokeLine(Xzero1-1, Yfinal1, Xfinal1, Yfinal1);

                            gc.setStroke(Color.BLACK);
                        }

                    }



////////////////////////IF CIRCLE////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    if (NewZZ2 > -0.2 && newDiameter > 10) {
                        gc.setFill(Color.WHITE);
                        gc.fillOval(Xzero1 - 2, Yzero1 - newDiameter / 2 - 2, newDiameter + 4, newDiameter + 4);
                        gc.setStroke(Color.BLACK);

                        gc.setStroke(Color.WHEAT);
                        gc.setLineWidth(0.5);
                        for (int i = 0; i < 1440; i += 30) {
                            gc.strokeLine(i, 0, i, 720 - (720 % 30));
                        }

                        // horizontal lines
                        gc.setStroke(Color.WHEAT);
                        gc.setLineWidth(0.5);
                        for (int i = 0; i < 720; i += 30) {
                            gc.strokeLine(0, i, 1440, i);
                        }
                    }


                    //////////////////////////////////IF Diode (Without conditions)////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    if (NewZZ1 > -0.2 && (newdY1 > 10) && (newdX1 > 10) && (newdY2 < -5) && (newdX2 < 10) && (newdX2 > -10)) {

                        for  (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);

                            ////draw vertical side////////////////////////////////

                            gc.strokeLine( newX1[0] , newY1[0],  newX1[0],  newdY1+newY1[0] );

                            /////bottom line///////////////////////////////////

                            gc.strokeLine(newX1[0],  newdY1+newY1[0],newX1[0]+newdY1,newdY1/2+newY1[0]);

                            /////////////upper line///////////////////////////////////

                            gc.strokeLine(newX1[0]+newdY1,newdY1/2+newY1[0],newX1[0],newY1[0]);


                            ////draw Line in front of the diode//////////////////////////

                            gc.strokeLine(newX1[0]+newdY1, newdY1+newY1[0],newX1[0]+newdY1, newY1[0]);


                            gc.setStroke(Color.BLACK);
                        }
                    }




//////////////////////////////////IF Diode d2 > d1 and from angle (which from bottom to the top and to the right)////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    if (NewZZ1 > -0.2 && (Xangle1[CountAng] -Xdiode1[0]) <=15 && (Xangle1[CountAng] -Xdiode1[0]) >= -15 && (d2 > d1)) {

                        for  (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);

                            ////draw vertical side////////////////////////////////

                            gc.strokeLine( Xangle1[CountAng] , (Ydiode1[0] - d3),  Xangle1[CountAng],  Ydiode1[0] + newdY1 -d3 );

                            /////bottom line///////////////////////////////////

                            gc.strokeLine(Xangle1[CountAng],  Ydiode1[0] + newdY1 -d3, Xzero1 + newdY1 ,Yangle1[CountAng]);

                            /////////////upper line///////////////////////////////////

                            gc.strokeLine(Xzero1 + newdY1, Yangle1[CountAng],Xangle1[CountAng],Ydiode1[0] - d3);


                            ////draw Line in front of the diode//////////////////////////

                            gc.strokeLine(Xzero1 + newdY1, Ydiode1[0] - d3,Xzero1 + newdY1, Ydiode1[0] + newdY1 -d3);

                            gc.setStroke(Color.BLACK);
                        }
                    }


                    //////////////////////////////////IF Diode d2 < d1 and from angle (which from bottom to the top and to the right)////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    if (NewZZ1 > -0.2 && (Xangle1[CountAng] -Xdiode1[0]) <=15 && (Xangle1[CountAng] -Xdiode1[0]) >= -15 && (d2 < d1)) {

                        for  (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);

                            ////draw vertical side////////////////////////////////

                            gc.strokeLine( Xangle1[CountAng] , (Ydiode1[0] + d3),  Xangle1[CountAng],  Ydiode1[0] + newdY1 +d3 );

                            /////bottom line///////////////////////////////////

                            gc.strokeLine(Xangle1[CountAng],  Ydiode1[0] + newdY1 +d3, Xzero1 + newdY1 ,Yangle1[CountAng]);

                            /////////////upper line///////////////////////////////////

                            gc.strokeLine(Xzero1 + newdY1, Yangle1[CountAng],Xangle1[CountAng],Ydiode1[0] + d3);


                            ////draw Line in front of the diode//////////////////////////

                            gc.strokeLine(Xzero1 + newdY1, Ydiode1[0] + d3,Xzero1 + newdY1, Ydiode1[0] + newdY1 +d3);

                            gc.setStroke(Color.BLACK);
                        }
                    }

                    ////////////////////////The condition for Circle (left side of circle), line is left to right////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    if (NewZZ0 > -0.2 && (( (Xcircle1[0] - XlineLtoR[NewNum1])) <= 15) && (((Xcircle1[0] - XlineLtoR[NewNum1])) >= -15) && ((YlineLtoR[0] - Ycircle1[0]) >= -15) &&((YlineLtoR[0] - Ycircle1[0]) <= 15) ) {
                        for (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(XlineLtoR[0], (float) Ycircle1[0], Xcircle1[0], Ycircle1[0]);
                            gc.setStroke(Color.BLACK);
                        }
                    }

                    ////////////////////////The condition for Circle (right side of circle), line is left to right////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    if (NewZZ0 > -0.2 && ((XlineLtoR[0] - (Xcircle1[0]+newDiameterSpecial)) <= 15) && ((XlineLtoR[0] - (Xcircle1[0]+newDiameterSpecial)) >= -15) && ((YlineLtoR[0] - Ycircle1[0]) >= -15) &&(YlineLtoR[0] - Ycircle1[0]) <= 15) {
                        for (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(Xcircle1[0]+newDiameterSpecial, Ycircle1[0], Xfinal1, Ycircle1[0]);
                            gc.setStroke(Color.BLACK);
                        }
                    }


                    ////////////////////////From right horisontal side of diode and line is drawn from left to the right with previous angle BTR////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    if (NewZZ0 > -0.2 && (Xzero1 - DiodeX) <= 15  && (Xzero1 - DiodeX) >= -15 && (Yzero1 - Yangle1[CountAng]) <= 15  && (Yzero1 - Yangle1[CountAng]) >= -15 ) {
                        for (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(DiodeX, Yangle1[CountAng], Xfinal1, Yangle1[CountAng]);
                            gc.setStroke(Color.BLACK);
                        }
                    }

                    /////////////////////////////////////If line from the Left to the Right (just a line)////////////////////////////////////////////////

                    if (NewZZ0 > -0.2 && ((Cnew < 10 && Cnew > - 10)) && (Clength > 0)) {
                        for (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(Xzero1, Yzero1, Xfinal1, Yzero1);
                            gc.setStroke(Color.BLACK);
                        }
                    }

/////////////////////////////////////If line from the Right to the Left (just a line)////////////////////////////////////////////////

                    if (NewZZ0 > -0.2 && ((Cnew < 10 && Cnew > - 10)) && (Clength < 0)) {
                        for (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(newX1[0], newY1[0], newX1[NewNum1], newY1[0]);
                            gc.setStroke(Color.BLACK);
                        }
                    }

                    ////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM and FROM RIGHT to THE LEFT and if Xfinal FAR AWAY FROM (Xcircle1[0] + newDiameterSpecial / 2)////////////////////////////////

                    if (NewZZ0 > -0.2 && (newX1[0] - XforLR <= 15 && newX1[0] - XforLR >= -15 && newY1[0] - YforTB <= 15 && newY1[0] - YforTB >= -15 && ((Xfinal1 - (Xcircle1[0] + newDiameterSpecial / 2)) > 15) || ((Xfinal1 - (Xcircle1[0] + newDiameterSpecial / 2)) < -15))) {
                        for (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(XforLR, YforTB, Xfinal1, YforTB);
                            gc.setStroke(Color.BLACK);
                        }
                    }


                    ////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM and FROM RIGHT to THE LEFT and if Xfinal CLOSE TO (Xcircle1[0] + newDiameterSpecial / 2)////////////////////////////////

                    if (NewZZ0 > -0.2 && (newX1[0] - XforLR <= 15 && newX1[0] - XforLR >= -15 && newY1[0] - YforTB <= 15 && newY1[0] - YforTB >= -15 && ((Xfinal1 - (Xcircle1[0] + newDiameterSpecial / 2)) <= 15 || (Xfinal1 - (Xcircle1[0] + newDiameterSpecial / 2)) >= -15))) {
                        for (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(XforLR, YforTB, Xcircle1[0] + newDiameterSpecial / 2, YforTB);
                            gc.setStroke(Color.BLACK);
                        }
                    }

                    ///////////////////////////IF Line from Top to Bottom/////////////////////////////////////////////////////////////////////////////////////////////

                    if (NewZZ0 > -0.2 && ((Clength < 10 && Clength > - 10)) && (Cnew > 0)) {
                        for (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(newX1[0], newY1[0], newX1[0], newY1[NewNum1]);
                            gc.setStroke(Color.BLACK);
                        }
                    }


                    ///////////////////////////IF Line from Bottom to  Top/////////////////////////////////////////////////////////////////////////////////////////////

                    if (NewZZ0 > -0.2 && ((Clength < 10 && Clength > - 10)) && (Cnew < 0)) {
                        for (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(newX1[0], newY1[0], newX1[0], newY1[NewNum1]);
                            gc.setStroke(Color.BLACK);
                        }
                    }

                    ////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM and FROM RIGHT to THE LEFT and if Xfinal CLOSE TO (Xcircle1[0] + newDiameterSpecial / 2) and FROM BOTTOM to NOT Center of CIRCLE////////////////////////////////

                    if (NewZZ0 > -0.2 && (Xzero1 - (Xcircle1[0] + newDiameterSpecial / 2) <= 15 && Xzero1 - (Xcircle1[0] + newDiameterSpecial / 2) >= -15 && (Yzero1 - YforTB) <= 15 && (Yzero1 - YforTB) >= -15)) {
                        for (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine((Xcircle1[0] + newDiameterSpecial / 2), YforTB, (Xcircle1[0] + newDiameterSpecial / 2), Yfinal1);
                            gc.setStroke(Color.BLACK);
                        }
                    }

////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM and FROM RIGHT to THE LEFT and if Xfinal CLOSE TO (Xcircle1[0] + newDiameterSpecial / 2) and FROM BOTTOM to the Center of CIRCLE////////////////////////////////


                    if (Xzero1 - (Xcircle1[0] + newDiameterSpecial / 2) <= 15 && Xzero1 - (Xcircle1[0] + newDiameterSpecial / 2) >= -15 && (Yzero1 - YforTB) <= 15 && (Yzero1 - YforTB) >= -15) {
                        for (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine((Xcircle1[0] + newDiameterSpecial / 2), YforTB,(Xcircle1[0] + newDiameterSpecial / 2), ((Ycircle1[0] + newDiameterSpecial / 2) + 1));
                            gc.setStroke(Color.BLACK);
                        }
                    }

                    //////////////////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM////////////////////////////////////////////////////////////////////

                    if (NewZZ0 > -0.2 && Xzero1 - XforLR <= 15 && Xzero1 - XforLR >= -15 && Yzero1 - Yangle1[CountAng] <= 15 && Yzero1 - Yangle1[CountAng] >= -15) {
                        for (int Coun = 0; Coun < 10; Coun++) {
                            gc.setStroke(Color.WHITE);
                            gc.strokeLine(XforLR, Yangle1[CountAng], XforLR, Yfinal1);
                            gc.setStroke(Color.BLACK);
                        }
                    }



                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(1);
                }
            });









            // Update canvas rectnagle when the user clicks

            button2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    reset(canvas, Color.WHITE);


                    // vertical lines
                        gc.setStroke(Color.WHEAT);
                        gc.setLineWidth(0.5);
                       for (int i = 0; i < 1440; i += 30) {
                            gc.strokeLine(i, 0, i, 720 - (720 % 30));
                         }

                    // horizontal lines
                         gc.setStroke(Color.WHEAT);
                         gc.setLineWidth(0.5);
                         for (int i = 0; i < 720; i += 30) {
                           gc.strokeLine(0, i, 1440, i);
                         }
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(1);
                    //  for (int k = 0; k <= 11; k++) {
                    //      for (int z = 0; z <= 23; z++) {
                    //          test[k][z] = -1;
                    //         // System.out.print(test1[k][z] + " ");
                    //       }
                    //   }
                    out.println();


                    try {

                        Document document = new Document(new com.itextpdf.text.Rectangle(1440, 1800));
                        //Document document = new Document(new com.itextpdf.text.Rectangle(1024, 512));
                        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Experiment.pdf"));
                        document.open();
                        writer.setPageEmpty(false);
                        document.newPage();
                        document.close();

                        document = new Document(new com.itextpdf.text.Rectangle(1440, 1800));
                        //document = new Document(new com.itextpdf.text.Rectangle(1024, 512));
                        writer = PdfWriter.getInstance(document, new FileOutputStream("Experiment1.pdf"));
                        document.open();
                        writer.setPageEmpty(false);
                        document.newPage();
                        document.close();

                        document = new Document(new com.itextpdf.text.Rectangle(1440, 1800));
                        //document = new Document(new com.itextpdf.text.Rectangle(1024, 512));
                        writer = PdfWriter.getInstance(document, new FileOutputStream("Experiment2.pdf"));
                        document.open();
                        writer.setPageEmpty(false);
                        document.newPage();
                        document.close();


                    } catch (Exception e1) {
                        System.err.println(e1.getMessage());
                    }


                }
            });


            button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    try {
                        Desktop.getDesktop().open(new File("Experiment1.pdf"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }


                    int[] Newtest = new int[289];
                    Newtest = new int[Newtest.length + 1];
                    Newtest[288] = -1;

                    int T = 0;
                    for (int k = 0; k <= 11; k++) {
                        for (int z = 0; z <= 23; z++) {

                            Newtest[T] = test[k][z];
                            T = T + 1;
                            //  System.out.print(test[k][z] + " ");

                        }
                    }
                    out.println();
                    out.println();


                }
            });


            // Add the Canvas to the Scene, and show the Stage
            root.getChildren().add(canvas);
            primaryStage.setScene(new Scene(root, 1540, 1004));
            primaryStage.show();

        }

        /**
         * Draws the background with a RadialGradient
         * that transitions from Red to Yellow.
         * @param rect the Rectangle to draw on the Canvas
         */


    private void drawBackground(Rectangle rect) {
        rect.setFill(new LinearGradient(0, 0, 1, 1, true,
                CycleMethod.REFLECT,
                new Stop(0, Color.BLACK),
                new Stop(1, Color.RED)));


    }


///////////////////Circle from the left(Horisontal) and clockwise//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



       public static void circle(double Xzero1, double Yzero1, double newDiameter, double newX1[], double newY1[]) {

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);

        }


           gc.setLineWidth(1);
        gc.setFill(Color.BLACK);
            gc.strokeOval(Xzero1, Yzero1-newDiameter/2, newDiameter, newDiameter);




    }


    ///////////////////DIODE (WISECLOCK) X0 and Y0 in the upper (without conditions)//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void diode(double newX1[], double newY1[], double newdY1){


        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);

        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        ////draw vertical side////////////////////////////////

        gc.strokeLine( newX1[0] , newY1[0],  newX1[0],  newdY1+newY1[0] );

        /////bottom line///////////////////////////////////

        gc.strokeLine(newX1[0],  newdY1+newY1[0],newX1[0]+newdY1,newdY1/2+newY1[0]);

        /////////////upper line///////////////////////////////////

        gc.strokeLine(newX1[0]+newdY1,newdY1/2+newY1[0],newX1[0],newY1[0]);


        ////draw Line in front of the diode//////////////////////////

        gc.strokeLine(newX1[0]+newdY1, newdY1+newY1[0],newX1[0]+newdY1, newY1[0]);


    }





    ///////////////////DIODE (WISECLOCK) X0 and Y0 in the upper corner and d2 > d1//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void diode1(double Xangle1[], double Ydiode1[], double newdY1, double Xzero1,double  d3){


        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);

        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        ////draw vertical side////////////////////////////////

        gc.strokeLine( Xangle1[CountAng] , (Ydiode1[0] - d3),  Xangle1[CountAng],  Ydiode1[0] + newdY1 -d3 );

        /////bottom line///////////////////////////////////

        gc.strokeLine(Xangle1[CountAng],  Ydiode1[0] + newdY1 -d3, Xzero1 + newdY1 ,Yangle1[CountAng]);

        /////////////upper line///////////////////////////////////

        gc.strokeLine(Xzero1 + newdY1, Yangle1[CountAng],Xangle1[CountAng],Ydiode1[0] - d3);


        ////draw Line in front of the diode//////////////////////////

        gc.strokeLine(Xzero1 + newdY1, Ydiode1[0] - d3,Xzero1 + newdY1, Ydiode1[0] + newdY1 -d3);


    }


    ///////////////////DIODE (WISECLOCK) X0 and Y0 in the upper corner and d1 > d2//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void diode2(double Xangle1[], double Ydiode1[], double newdY1, double Xzero1,double  d3){


        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);

        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        ////draw vertical side////////////////////////////////

        gc.strokeLine( Xangle1[CountAng] , (Ydiode1[0] + d3),  Xangle1[CountAng],  Ydiode1[0] + newdY1 + d3 );

        /////bottom line///////////////////////////////////

        gc.strokeLine(Xangle1[CountAng],  Ydiode1[0] + newdY1 +d3, Xzero1 + newdY1 ,Yangle1[CountAng]);

        /////////////upper line///////////////////////////////////

        gc.strokeLine(Xzero1 + newdY1, Yangle1[CountAng],Xangle1[CountAng],Ydiode1[0] + d3);


        ////draw Line in front of the diode//////////////////////////

        gc.strokeLine(Xzero1 + newdY1, Ydiode1[0] + d3,Xzero1 + newdY1, Ydiode1[0] + newdY1 + d3);


    }



    /////////////////////If it is line and it is drawn from the top to the bottom.//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void LineTB(double newX1[], double newY1[], int NewNum1){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);

        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(newX1[0], newY1[0], newX1[0], newY1[NewNum1]);


    }

//////////////////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM////////////////////////////////////////////////////////////////////

    public static void LineTB1(double XforLR, double Yfinal1, double Yangle1[]){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);

        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(XforLR, Yangle1[CountAng], XforLR, Yfinal1);


    }
    /////////////////////If it is line and it is drawn from the bottom to the top.//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void LineBT(double newX1[], double newY1[], int NewNum1){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);


        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(newX1[0], newY1[0], newX1[0], newY1[NewNum1]);


    }

    ////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM and FROM RIGHT to THE LEFT and if Xfinal CLOSE TO (Xcircle1[0] + newDiameterSpecial / 2) and FROM BOTTOM to NOT Center of CIRCLE////////////////////////////////


    public static void LineBT1(double Xcircle1[], double newDiameterSpecial, double YforTB, double Yfinal1){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);

        }
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine((Xcircle1[0] + newDiameterSpecial / 2), YforTB, (Xcircle1[0] + newDiameterSpecial / 2), Yfinal1);
    }


////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM and FROM RIGHT to THE LEFT and if Xfinal CLOSE TO (Xcircle1[0] + newDiameterSpecial / 2) and FROM BOTTOM to the Center of CIRCLE////////////////////////////////

    public static void LineBT2(double Xcircle1[], double newDiameterSpecial, double YforTB, double Ycircle1[]){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);

        }
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine((Xcircle1[0] + newDiameterSpecial / 2), YforTB,(Xcircle1[0] + newDiameterSpecial / 2), ((Ycircle1[0] + newDiameterSpecial / 2) + 1));


    }


///////////////////////////////////////////////// FOR THE LINE FROM THE RIGHT TO THE LEFT/////////////////////////////////////////////////////////////////////////

      public static void LineRL (double newX1[], double newY1[], int NewNum1){


          for (int x = 0; x <=9998; x++ ){

              gc.setFill(Color.WHITE);
              gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);


          }
          gc.setStroke(Color.BLACK);
          gc.setLineWidth(1);
          gc.strokeLine(newX1[0], newY1[0], newX1[NewNum1], newY1[0]);

      }


    //////////////////////////////////////////// After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM and FROM RIGHT to THE LEFT/////////////////////////////////////////////////////////////////////////

    public static void LineRL1 (double XforLR, double Xfinal1,double YforTB){


        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);


        }
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(XforLR, YforTB, Xfinal1, YforTB);


    }


    ////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM and FROM RIGHT to THE LEFT and if Xfinal CLOSE TO (Xcircle1[0] + newDiameterSpecial / 2)////////////////////////////////


    public static void LineRL2 (double XforLR, double Xcircle1[], double newDiameterSpecial, double YforTB){


        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);


        }
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(XforLR, YforTB, Xcircle1[0] + newDiameterSpecial / 2, YforTB);

    }





    ////////////////////////////////////////////////// FOR THE LINE FROM THE LEFT TO THE RIGHT (just a line)//////////////////////////////////////////////////////////////////////

    public static void LineLR(double Xzero1, double Yzero1, double Xfinal1){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);


        }
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(Xzero1, Yzero1, Xfinal1, Yzero1);

    }

    ////////////////////////////////////////////////// The condition for Circle (right side of circle)//////////////////////////////////////////////////////////////////////

    public static void LineLR1(double Xcircle1[], double newDiameterSpecial,  double Ycircle1[], double Xfinal1, int NewNum1){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);


        }
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(Xcircle1[0]+newDiameterSpecial, Ycircle1[0], Xfinal1, Ycircle1[0]);

    }

    ///////////////////////////////////////////////////////////////////////The condition for Circle (left side of circle)////////////////////////////////////////////
    public static void LineLR2(double XlineLtoR[], double Ycircle1[], double Xcircle1[], int NewNum1){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);


        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(XlineLtoR[0], (float) Ycircle1[0], Xcircle1[0], Ycircle1[0]);

    }


    //////////////////////////////////////////////From right horisontal side of diode and line is drawn from left to the right with previous angle BTR//////////////////////
    public static void LineLR3(double DiodeX, double Xfinal1, double Yangle1[]){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);


        }
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(DiodeX, Yangle1[CountAng], Xfinal1, Yangle1[CountAng]);

    }



    public static void CurveBTR(double Xcircle1[], double newDiameterSpecial, double Ycircle1[], double Xzero1, double Yfinal1){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);

        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        gc.strokeLine(Xcircle1[0] + newDiameterSpecial/2, Ycircle1[0] - newDiameterSpecial/2, Xcircle1[0] + newDiameterSpecial/2, Yfinal1);

        gc.strokeLine(Xcircle1[0] + newDiameterSpecial/2-1, Yfinal1, Xfinal1, Yfinal1);


    }

////////////////////////////////////////////////////////For the angle BTR(from the top of the circle)///////////////////////////////////////////////////////

    public static void CurveTBR(double Xzero1, double Yzero1, double Xfinal1,double Yfinal1){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);

        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        gc.strokeLine(Xzero1, Yzero1, Xzero1, Yfinal1);

        gc.strokeLine(Xzero1-1, Yfinal1, Xfinal1, Yfinal1);



    }

    ////////////////////////////////////////////////////////For the angle BTR(just a angle BTR)//////////////////////////////////////////////////////////////////

    public static void CurveBTR1(double Xzero1,double Yzero1,double Xfinal1,double Yfinal1){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);

        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        gc.strokeLine(Xzero1, Yzero1, Xzero1, Yfinal1);

        gc.strokeLine(Xzero1-1, Yfinal1, Xfinal1, Yfinal1);



    }

    //////////////////////////////////////////////////////////drawing line from the top of the circle/////////////////////////////////////////////////////////

/*
    public static void LineBT3(){

        for (int x = 0; x <=9998; x++ ){

            gc.setFill(Color.WHITE);
            gc.fillRect((round(newX1[x])/2)*2,(round(newY1[x])/2)*2, 2, 2);

        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);


        System.out.println();
        System.out.println(newDiameterSpecial);
        System.out.println();



        gc.strokeLine(Xcircle1[0] + newDiameterSpecial/2 , Ycircle1[0] - newDiameterSpecial /2, Xcircle1[0] + newDiameterSpecial/2, Yfinal1);

        //gc.strokeLine(Xzero1-1, Yfinal1, Xfinal1, Yfinal1);



    }

*/


    public static void main(String[] args) throws FileNotFoundException {

        launch(args);

    }

}

