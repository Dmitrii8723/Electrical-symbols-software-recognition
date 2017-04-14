


package sample;

/**
 * Created by dimak on 6/22/2016.
 */

import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

import static java.lang.StrictMath.exp;


public class VVandWW {
    public static float[][] v;
    public static float[][] W;
    private static Component frame;
    public int lm;
    public int cc;
    static public int Rowc = 0;
    static public int Rowc2 = 0;
    public int pp;
    public int minErV;
    static public int[] d;
    static public int h1;
    public int h2;
    static public int JJ = 289;
    static public int II = 28;
    static public int KK = 5;
    static String InputLine = "";
    static String InputLine2 = "";
    static Double[][] VV;
    static Double[][] WW;
    static public int ii = II+1;
    static public int [] Newtest2 = new int [289];
    static public double Cnew;
    static public double Clength;
    static public double Xzero1;
    static public double Yzero1;
    static public double Xzero;
    static public double Yzero;
    static public double Xfinal;
    static public double Yfinal;
    static public double Xfinal1;
    static public double Yfinal1;
    static public double newdY1;
    static public double newdY2;
    static public double newdX1;
    static public double newdX2;
    static public double newDiameter;
    static double[] newX1 = new double[10000];
    static double[] newY1 = new double[10000];
    static int NewNum1;
    static double NewZZ0;
    static double NewZZ1;
    static double NewZZ2;
    static double NewZZ3;
    static double NewZZ4;
    static double[] Ycircle1 = new double[10000];
    static double[] Xcircle1 = new double[10000];
    static double[] Yangle1 = new double[10000];
    static double[] Xangle1 = new double[10000];
    static double[] YlineLtoR = new double[10000];
    static double[] XlineLtoR = new double[10000];
    static double[] Xdiode1 = new double[10000];
    static double[] Ydiode1 = new double[10000];
    static public double newDiameterSpecial;
    static double d1;
    static double d2;
    static double d3;
    static int NewNum2;
    static int CountAng;
    static double DiodeX;
    static double XforLR;
    static double XforRL;
    static double YforTB;
    ////////////////Passing Array - "Newtest" from MAIN to the Constructor///////////////////////////////////////////////

    public static void VVandWW(int[] Newtest, double Cy, double Cx, double Xzero, double Yzero, double Xfinal, double Yfinal, double dY1,double dX1, double dY2, double dX2, double Diameter, double X1[], double Y1[], int NewNum) {


        Newtest2 = Newtest;

       Cnew = Cy;
        System.out.println();
       // System.out.print(Cnew);
        System.out.println();


        Clength = Cx;
        System.out.println();
       // System.out.print(Clength);
        System.out.println();

        Xzero1 = Xzero;
        System.out.println();
       // System.out.print(Xzero1);
        System.out.println();

        Yzero1 = Yzero;
        System.out.println();
       // System.out.print(Yzero1);
        System.out.println();

        Xfinal1 = Xfinal;
        System.out.println();
       // System.out.print(Xfinal1);
        System.out.println();

        Yfinal1 = Yfinal;
        System.out.println();
       // System.out.print(Yfinal1);
        System.out.println();


        newdY1 = dY1;

       // System.out.println();
       //  System.out.print(newdY1);
      //  System.out.println();

        newdX1 = dX1;

      //  System.out.println();
      //  System.out.print(newdX1);
      //  System.out.println();

        newdY2 = dY2;

       // System.out.println();
       // System.out.print(newdY2);
       // System.out.println();



        newdX2 = dX2;

      //  System.out.println();
      //  System.out.print(newdX2);
       // System.out.println();

        newDiameter = Diameter;
          System.out.println();
          System.out.print(newDiameter);
         System.out.println();

        newX1 = X1;
        newY1 = Y1;

        NewNum1 = NewNum;
        System.out.println();
        System.out.print(NewNum1);
        System.out.println();
    }


    public static void setupMySCVvvANDwwArrays()throws Exception {


//////////////////////////////////////// VV matrixes' vector///////////////////////////////////////////////////////////////
        Rowc = 0;
        VV = new Double[II][JJ];
        //Get scanner instance
        Scanner scanner = new Scanner(new File("vv.csv"));

        //Set the delimiter used in file
        scanner.useDelimiter(",");

        //Get all tokens and store them in some data structure
        //printing them
        while (scanner.hasNextLine()) {
            InputLine = scanner.nextLine();
            String[] InArray = InputLine.split(",");

            for (int x = 0; x < InArray.length; x++) {

                // System.out.print(scanner.next() + " ");
                VV[Rowc][x] = Double.parseDouble(InArray[x]);
                System.out.print(VV[Rowc][x]+" ");

            }
            System.out.println();
            Rowc++;
        }

        //Closing the scanner
        scanner.close();
        System.out.println();
        System.out.println();




        for (int i = 0; i <= 288; i++){

            System.out.print(Newtest2[i] +" ");
        }

        System.out.println();
        System.out.println();


////////////////VV(i,:)*XX//////////////////////////////////////////////////////////////////////////////////////////

        double[] YY = new double[29];
        YY = new double[YY.length + 1];
        YY[28] = -1;

        for (int i = 0; i <= 27; i++)
        {   double net = 0;
            double yy = 0;
            for (int j = 0; j <= 288; j++)
            {

                net = (double) net + (double)(VV[i][j] * Newtest2[j]);
            }
            yy = (double) ((1 - exp(-net)) / (1 + exp(-net)));

            YY[i] = yy;
            System.out.print(YY[i] +" ");
        }
        System.out.print(YY[28] +" ");

        System.out.println();
        System.out.println();

//////////////////////////// WW matrixes' vector//////////////////////////////////////////////////////////////////////////
        Rowc2 = 0;
        WW = new Double[KK][ii];
        //Get scanner instance
        Scanner scanner2 = new Scanner(new File("ww.csv"));

        //Set the delimiter used in file
        scanner2.useDelimiter(",");

        //Get all tokens and store them in some data structure
        //printing them
        while (scanner2.hasNextLine()) {
            InputLine2 = scanner2.nextLine();
            String[] InArray2 = InputLine2.split(",");

            for (int x2 = 0; x2 < InArray2.length; x2++) {

                //        System.out.print(scanner.next() + " ");
                WW[Rowc2][x2] = Double.parseDouble(InArray2[x2]);
                //   System.out.print(WW[Rowc2][x2]+" ");
            }
            //    System.out.println();
            Rowc2++;
        }
        scanner2.close();


        ////////////////WW(k,:)*YY//////////////////////////////////////////////////////////////////////////////////////////


        double [] ZZ = new double[5];
        for (int k = 0; k <= 4; k++){
            double net = 0;
            double zz;
            for (int q = 0; q <=28; q++){

                net = (double) net + (double)(WW[k][q] * YY[q]);
            }
            zz = (double) (1 - exp(-net)) / (1 + exp(-net));
            ZZ[k] = zz;
            System.out.print(ZZ[k] +" ");
        }

        System.out.println();
        System.out.println();

        double max = ZZ[0];
        for (int t = 0; t <= 4; t++) {
            if (ZZ[t] > max)  {
                max = ZZ[t];
            }
        }

        NewZZ0 = ZZ[0];
        NewZZ1 = ZZ[1];
        NewZZ2 = ZZ[2];
        NewZZ3 = ZZ[3];
        NewZZ4 = ZZ[4];
//If it is line and it is drawn from the left to right.

        if ((ZZ[0] == max) && ((Cnew < 10 && Cnew > - 10)) && (Clength > 0)) {


            for (int y = 0; y < 9998; y++) {

                YlineLtoR[y] = 0.0;
                XlineLtoR[y] = 0.0;
            }


            XforLR = 0;

            XforLR = Xfinal1;


            for (int y = 0; y < 9998; y++) {

                if (newX1[y] != 0.0) {

                    XlineLtoR[y] = newX1[y];
                    YlineLtoR[y] = newY1[y];

                }

            }





                    System.out.println();
            System.out.print(XlineLtoR[NewNum1]);
            System.out.println();

            System.out.println();
            System.out.print(Xcircle1[0]+newDiameterSpecial);
            System.out.println();


                try {


                    PdfReader reader1 = new PdfReader("Experiment.pdf");
                    PdfReader reader2 = new PdfReader("Experiment2.pdf");


                    PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream("Experiment1.pdf"));

                    PdfContentByte cb = stamper.getOverContent(1);

                    // cb.concatCTM(AffineTransform.getTranslateInstance(10, 10));



                    ////////////////////////////////////////////////////////////The condition for Circle (right side of circle)////////////////////////////////////////////////
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    if ( ((XlineLtoR[0] - (Xcircle1[0]+newDiameterSpecial)) <= 15) && ((XlineLtoR[0] - (Xcircle1[0]+newDiameterSpecial)) >= -15) && ((YlineLtoR[0] - Ycircle1[0]) >= -15) &&(YlineLtoR[0] - Ycircle1[0]) <= 15) {
                        cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                        cb.setLineWidth(2f);
                        cb.moveTo((float) (Xcircle1[0] + newDiameterSpecial), (float) Ycircle1[0]);
                        cb.lineTo((float) Xfinal1, (float) Ycircle1[0]);
                        Main.LineLR1(Xcircle1, newDiameterSpecial, Ycircle1, Xfinal1, NewNum1);
                        cb.stroke();
                        stamper.close();


                        reader2 = new PdfReader("Experiment.pdf");
                        stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                        cb = stamper.getOverContent(1);

                        stamper.close();

                        reader1 = new PdfReader("Experiment1.pdf");
                        stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                        cb = stamper.getOverContent(1);

                        stamper.close();
                        return;
                    }

////////////////////////////////////////////////////////////The condition for Circle (left side of circle)////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if ( (( (Xcircle1[0] - XlineLtoR[NewNum1])) <= 15) && (((Xcircle1[0] - XlineLtoR[NewNum1])) >= -15) && ((YlineLtoR[0] - Ycircle1[0]) >= -15) &&((YlineLtoR[0] - Ycircle1[0]) <= 15)) {
                        cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                        cb.setLineWidth(2f);
                        cb.moveTo((float) XlineLtoR[0], (float) Ycircle1[0]);
                        cb.lineTo((float) Xcircle1[0], (float) Ycircle1[0]);

                        Main.LineLR2(XlineLtoR, Ycircle1, Xcircle1, NewNum1);

                        cb.stroke();
                        stamper.close();


                        reader2 = new PdfReader("Experiment.pdf");
                        stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                        cb = stamper.getOverContent(1);

                        stamper.close();

                        reader1 = new PdfReader("Experiment1.pdf");
                        stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                        cb = stamper.getOverContent(1);

                        stamper.close();
                        return;
                    }

//////////////////////////////////////////////From right horisontal side of diode and line is drawn from left to the right with previous angle BTR////////////////////////////////////////////////////////////////////

                    if ((Xzero1 - DiodeX) <= 15  && (Xzero1 - DiodeX) >= -15 && (Yzero1 - Yangle1[CountAng]) <= 15  && (Yzero1 - Yangle1[CountAng]) >= -15 ){

      cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
      cb.setLineWidth(2f);
      cb.moveTo((float) DiodeX, (float) Yangle1[CountAng]);
      cb.lineTo((float) Xfinal1, (float) Yangle1[CountAng]);


                        Main.LineLR3(DiodeX, Xfinal1, Yangle1);
                        cb.stroke();
      stamper.close();


      reader2 = new PdfReader("Experiment.pdf");
      stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

      cb = stamper.getOverContent(1);

      stamper.close();

      reader1 = new PdfReader("Experiment1.pdf");
      stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

      cb = stamper.getOverContent(1);

      stamper.close();

                        return;

                    }
/////////////////////////////////////////If just a line//////////////////////////////////////////////////////////////////////////////////

                        cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                        cb.setLineWidth(2f);
                        cb.moveTo((float) Xzero1, (float) Yzero1);
                        cb.lineTo((float) Xfinal1, (float) Yzero1);

                        Main.LineLR(Xzero1, Yzero1, Xfinal1);

                        cb.stroke();
                        stamper.close();


                        reader2 = new PdfReader("Experiment.pdf");
                        stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                        cb = stamper.getOverContent(1);

                        stamper.close();

                        reader1 = new PdfReader("Experiment1.pdf");
                        stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                        cb = stamper.getOverContent(1);

                        stamper.close();



                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }




        }



///////////////////////////////////////////////////////////////If it is line and it is drawn from the right to the left.////////////////////////////////////

        if ((ZZ[0] == max) && ((Cnew < 10 && Cnew > - 10)) && (Clength < 0)) {




            try {

                PdfReader reader1 = new PdfReader("Experiment.pdf");
                PdfReader reader2 = new PdfReader("Experiment2.pdf");


                PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream("Experiment1.pdf"));

                PdfContentByte cb = stamper.getOverContent(1);

                // cb.concatCTM(AffineTransform.getTranslateInstance(10, 10));







////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM and FROM RIGHT to THE LEFT and if Xfinal FAR WAY FROM (Xcircle1[0] + newDiameterSpecial / 2)////////////////////////////////
      if (newX1[0] - XforLR <= 15 && newX1[0] - XforLR >= -15 && newY1[0] - YforTB <= 15 && newY1[0] - YforTB >= -15) {

          if (((Xfinal1 - (Xcircle1[0] + newDiameterSpecial / 2)) > 15) || ((Xfinal1 - (Xcircle1[0] + newDiameterSpecial / 2)) < -15)) {

              cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
              cb.setLineWidth(2f);
              cb.moveTo((float) XforLR + 1, (float) YforTB);
              cb.lineTo((float) Xfinal1, (float) YforTB);

              Main.LineRL1(XforLR, Xfinal1, YforTB);

              cb.stroke();
              stamper.close();

              reader2 = new PdfReader("Experiment.pdf");
              stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

              cb = stamper.getOverContent(1);

              stamper.close();

              reader1 = new PdfReader("Experiment1.pdf");
              stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

              cb = stamper.getOverContent(1);

              stamper.close();


              return;
          }
      }
                ////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM and FROM RIGHT to THE LEFT and if Xfinal CLOSE TO (Xcircle1[0] + newDiameterSpecial / 2)////////////////////////////////

                if (newX1[0] - XforLR <= 15 && newX1[0] - XforLR >= -15 && newY1[0] - YforTB <= 15 && newY1[0] - YforTB >= -15) {

                    if ((Xfinal1 - (Xcircle1[0] + newDiameterSpecial / 2)) <= 15 || (Xfinal1 - (Xcircle1[0] + newDiameterSpecial / 2)) >= -15) {
                        cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                        cb.setLineWidth(2f);
                        cb.moveTo((float) XforLR + 1, (float) YforTB);
                        cb.lineTo((float) ((Xcircle1[0] + newDiameterSpecial / 2)-1), (float) YforTB);

                        Main.LineRL2(XforLR, Xcircle1, newDiameterSpecial, YforTB);

                        cb.stroke();
                        stamper.close();

                        reader2 = new PdfReader("Experiment.pdf");
                        stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                        cb = stamper.getOverContent(1);

                        stamper.close();

                        reader1 = new PdfReader("Experiment1.pdf");
                        stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                        cb = stamper.getOverContent(1);

                        stamper.close();


                        return;
                    }
                }

                cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                cb.setLineWidth(2f);
                cb.moveTo((float) Xzero1 , (float) Yzero1);
                cb.lineTo((float) Xfinal1 , (float) Yzero1 );

                Main.LineRL(newX1, newY1, NewNum1);

                cb.stroke();
                stamper.close();

                reader2 = new PdfReader("Experiment.pdf");
                stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();

                reader1 = new PdfReader("Experiment1.pdf");
                stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }


            return;
        }

//If it is line and it is drawn from the bottom to the top.
        if ((ZZ[0] == max) && ((Clength < 10 && Clength > - 10)) && (Cnew < 0)) {
            try {
                PdfReader reader1 = new PdfReader("Experiment.pdf");
                PdfReader reader2 = new PdfReader("Experiment2.pdf");


                PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream("Experiment1.pdf"));

                PdfContentByte cb = stamper.getOverContent(1);


////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM and FROM RIGHT to THE LEFT and if Xfinal CLOSE TO (Xcircle1[0] + newDiameterSpecial / 2) and FROM BOTTOM to the Center of CIRCLE////////////////////////////////

                if (Xzero1 - (Xcircle1[0] + newDiameterSpecial / 2) <= 15 && Xzero1 - (Xcircle1[0] + newDiameterSpecial / 2) >= -15 && (Yzero1 - YforTB) <= 15 && (Yzero1 - YforTB) >= -15) {
                    if (Yfinal1 - (Ycircle1[0] + newDiameterSpecial / 2) <= 15 && Yfinal1 - (Ycircle1[0] + newDiameterSpecial / 2) >= -15) {
                        // cb.concatCTM(AffineTransform.getTranslateInstance(10, 10));

                        cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                        cb.setLineWidth(2f);
                        cb.moveTo((float) ((Xcircle1[0] + newDiameterSpecial / 2)), (float) YforTB);
                        cb.lineTo((float) ((Xcircle1[0] + newDiameterSpecial / 2)), (float) ((Ycircle1[0] + newDiameterSpecial / 2) + 1));

                          Main.LineBT2(Xcircle1, newDiameterSpecial, YforTB, Ycircle1);

                        cb.stroke();
                        stamper.close();
                        reader2 = new PdfReader("Experiment.pdf");
                        stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                        cb = stamper.getOverContent(1);

                        stamper.close();

                        reader1 = new PdfReader("Experiment1.pdf");
                        stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                        cb = stamper.getOverContent(1);

                        stamper.close();
                        return;
                    }
                }

                ////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM and FROM RIGHT to THE LEFT and if Xfinal CLOSE TO (Xcircle1[0] + newDiameterSpecial / 2) and FROM BOTTOM to NOT Center of CIRCLE////////////////////////////////

                if (Xzero1 - (Xcircle1[0] + newDiameterSpecial / 2) <= 15 && Xzero1 - (Xcircle1[0] + newDiameterSpecial / 2) >= -15 && (Yzero1 - YforTB) <= 15 && (Yzero1 - YforTB) >= -15) {
                    if (Yfinal1 - (Ycircle1[0] + newDiameterSpecial / 2) > 15 || Yfinal1 - (Ycircle1[0] + newDiameterSpecial / 2) < -15) {

                        // cb.concatCTM(AffineTransform.getTranslateInstance(10, 10));

                        cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                    cb.setLineWidth(2f);
                    cb.moveTo((float) ((Xcircle1[0] + newDiameterSpecial / 2)), (float) YforTB);
                    cb.lineTo((float) ((Xcircle1[0] + newDiameterSpecial / 2)), (float) Yfinal1);

                      Main.LineBT1(Xcircle1, newDiameterSpecial, YforTB, Yfinal1);

                    cb.stroke();
                    stamper.close();
                    reader2 = new PdfReader("Experiment.pdf");
                    stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                    cb = stamper.getOverContent(1);

                    stamper.close();

                    reader1 = new PdfReader("Experiment1.pdf");
                    stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                    cb = stamper.getOverContent(1);

                    stamper.close();
                    return;
                }
            }
                    // cb.concatCTM(AffineTransform.getTranslateInstance(10, 10));

                cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                cb.setLineWidth(2f);
                cb.moveTo((float) Xzero1 , (float) Yzero1);
                cb.lineTo((float) Xzero1 , (float) Yfinal1 );

                Main.LineBT(newX1, newY1, NewNum1);

                cb.stroke();
                stamper.close();
                reader2 = new PdfReader("Experiment.pdf");
                stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();

                reader1 = new PdfReader("Experiment1.pdf");
                stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();

            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }


            return;
        }


        //If it is line and it is drawn from the top to the bottom.
        if ((ZZ[0] == max) && ((Clength < 10 && Clength > - 10)) && (Cnew > 0)) {


            YforTB = 0;
            YforTB = Yfinal1;
            try {

                PdfReader reader1 = new PdfReader("Experiment.pdf");
                PdfReader reader2 = new PdfReader("Experiment2.pdf");


                PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream("Experiment1.pdf"));

                PdfContentByte cb = stamper.getOverContent(1);

                // cb.concatCTM(AffineTransform.getTranslateInstance(10, 10));



//////////////////////////////////////////////After BTR, Diode, horisontal line from diode and DRAWING LINE FROM TOP TO BOTTOM////////////////////////////////////////////////////////////////////


             if (Xzero1 - XforLR <= 15 && Xzero1 - XforLR >= -15 && Yzero1 - Yangle1[CountAng] <= 15 && Yzero1 - Yangle1[CountAng] >= -15 ){

                 cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                 cb.setLineWidth(2f);
                 cb.moveTo((float) XforLR , (float) Yangle1[CountAng]-1 );
                 cb.lineTo((float) XforLR, (float) Yfinal1 );

                 Main.LineTB1(XforLR, Yfinal1, Yangle1);


                 cb.stroke();
                 stamper.close();
                 reader2 = new PdfReader("Experiment.pdf");
                 stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                 cb = stamper.getOverContent(1);

                 stamper.close();

                 reader1 = new PdfReader("Experiment1.pdf");
                 stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                 cb = stamper.getOverContent(1);

                 stamper.close();

              return;
             }



                cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                cb.setLineWidth(2f);
                cb.moveTo((float) Xzero1 , (float) Yzero1);
                cb.lineTo((float) Xzero1 , (float) Yfinal1 );

                Main.LineTB(newX1, newY1, NewNum1);


                cb.stroke();
                stamper.close();
                reader2 = new PdfReader("Experiment.pdf");
                stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();

                reader1 = new PdfReader("Experiment1.pdf");
                stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }


            return;
        }



        //////////////////////////////////////// DIODE (WISECLOCK) X0 and Y0 in the upper corner////////////////////////////////////////////////////

        if ((ZZ[1] == max) && (newdY1 > 10) && (newdX1 > 10) && (newdY2 < -5) && (newdX2 < 10) && (newdX2 > -10)) {


            for (int y = 0; y < 9998; y++) {

                Ydiode1[y] = 0.0;
                Xdiode1[y] = 0.0;
            }


            Ydiode1[0] = newY1[0];
            Xdiode1[0] = newX1[0];

            DiodeX = 0;




            System.out.println();
            System.out.print(DiodeX);
            System.out.println();
            try {


                PdfReader reader1 = new PdfReader("Experiment.pdf");
                PdfReader reader2 = new PdfReader("Experiment2.pdf");


                PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream("Experiment1.pdf"));

                PdfContentByte cb = stamper.getOverContent(1);


                /////////////////////////////////////////for angle (which from bottom to the top and to the right)///////////////////////////////////////////////////


                if ((Xangle1[CountAng] -Xdiode1[0]) <=15 && (Xangle1[CountAng] -Xdiode1[0]) >= -15 ) {
                    DiodeX = Xzero1 + newdY1;
                    d2 = Ydiode1[0] + newdY1 - Yangle1[CountAng];
                    d1 = Yangle1[CountAng] - Ydiode1[0];

///////////////////////////////////////////If d2 > d1//////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    if (d2 > d1) {

                        d3 = (d2 - d1) / 2;

                        cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                        cb.setLineWidth(2f);

                        ////draw vertical side////////////////////////////////

                        cb.moveTo((float) Xangle1[CountAng], (float) (Ydiode1[0] - d3));
                        cb.lineTo((float) Xangle1[CountAng], (float) (Ydiode1[0] + newdY1 - d3));

                        ////draw bottom////////////////////////////////

                        cb.moveTo((float) Xangle1[CountAng], (float) (Ydiode1[0] + newdY1 - d3));
                        cb.lineTo((float) (Xzero1 + newdY1), (float) (Yangle1[CountAng]));

                        ////draw upper side////////////////////////////////

                        cb.moveTo((float) (Xzero1 + newdY1), (float) (Yangle1[CountAng]));
                        cb.lineTo((float) Xangle1[CountAng], (float) (Ydiode1[0] - d3));

                        ////draw Line in front of the diode//////////////////////////

                        cb.moveTo((float) (Xzero1 + newdY1), (float) (Ydiode1[0] - d3));
                        cb.lineTo((float) (Xzero1 + newdY1), (float) (Ydiode1[0] + newdY1 - d3));

                        ////draw Line in front of the diode//////////////////////////

                        Main.diode1(Xangle1, Ydiode1, newdY1, Xzero1, d3);

                        cb.stroke();
                        stamper.close();
                        reader2 = new PdfReader("Experiment.pdf");
                        stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                        cb = stamper.getOverContent(1);

                        stamper.close();

                        reader1 = new PdfReader("Experiment1.pdf");
                        stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                        cb = stamper.getOverContent(1);

                        stamper.close();

                        return;
                    }


///////////////////////////////////////////If d2 < d1//////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (d2 < d1) {

                        d3 = (d1 - d2) / 2;

                        cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                        cb.setLineWidth(2f);

                        ////draw vertical side////////////////////////////////

                        cb.moveTo((float) Xangle1[CountAng], (float) (Ydiode1[0] + d3));
                        cb.lineTo((float) Xangle1[CountAng], (float) (Ydiode1[0] + newdY1 + d3));

                        ////draw bottom////////////////////////////////

                        cb.moveTo((float) Xangle1[CountAng], (float) (Ydiode1[0] + newdY1 + d3));
                        cb.lineTo((float) (Xzero1 + newdY1), (float) (Yangle1[CountAng]));

                        ////draw upper side////////////////////////////////

                        cb.moveTo((float) (Xzero1 + newdY1), (float) (Yangle1[CountAng]));
                        cb.lineTo((float) Xangle1[CountAng], (float) (Ydiode1[0] + d3));

                        ////draw Line in front of the diode//////////////////////////

                        cb.moveTo((float) (Xzero1 + newdY1), (float) (Ydiode1[0] + d3));
                        cb.lineTo((float) (Xzero1 + newdY1), (float) (Ydiode1[0] + newdY1 + d3));

                        ////draw Line in front of the diode//////////////////////////

                        Main.diode2(Xangle1, Ydiode1, newdY1, Xzero1, d3);

                        cb.stroke();
                stamper.close();
                reader2 = new PdfReader("Experiment.pdf");
                stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();

                reader1 = new PdfReader("Experiment1.pdf");
                stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();
                   return;
                    }


                }

                cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                cb.setLineWidth(2f);

                ////draw vertical side////////////////////////////////

                cb.moveTo((float) Xzero1 , (float) Yzero1);
                cb.lineTo((float) Xzero1 , (float) (newdY1+Yzero1));

                ////draw bottom////////////////////////////////

                cb.moveTo((float) Xzero1 , (float) (newdY1+Yzero1));
                cb.lineTo((float) (Xzero1+newdY1) , (float) (newdY1/2 + Yzero1));

                ////draw upper side////////////////////////////////

                cb.moveTo((float) (Xzero1+newdY1) , (float) (newdY1/2 + Yzero1));
                cb.lineTo((float) Xzero1 , (float) Yzero1);

                ////draw Line in front of the diode//////////////////////////

                cb.moveTo((float) (Xzero1+newdY1) , (float) (newdY1 + Yzero1));
                cb.lineTo((float) (Xzero1+newdY1), (float) Yzero1);

                ////draw Line in front of the diode//////////////////////////

                Main.diode(newX1,newY1, newdY1);


                cb.stroke();
                stamper.close();
                reader2 = new PdfReader("Experiment.pdf");
                stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();

                reader1 = new PdfReader("Experiment1.pdf");
                stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();


            } catch (Exception e) {
                System.err.println(e.getMessage());
            }


        }


        //////////////////////////////////////// Circle from the left(Horisontal) and clockwise////////////////////////////////////////////////////


        if (ZZ[2] == max && newDiameter > 10) {

            newDiameterSpecial = 0;

            newDiameterSpecial = newDiameter;

            for (int y = 0; y < 9998; y++) {

                Ycircle1[y] = 0.0;
                Xcircle1[y] = 0.0;
            }




    Ycircle1[0] = newY1[0];
    Xcircle1[0] = newX1[0];



            try {


                PdfReader reader1 = new PdfReader("Experiment.pdf");
                PdfReader reader2 = new PdfReader("Experiment2.pdf");


                PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream("Experiment1.pdf"));

                PdfContentByte cb = stamper.getOverContent(1);

                cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                cb.setLineWidth(2f);




                cb.circle((float) Xzero1 + (float) newDiameter/2,(float) Yzero1, (float) newDiameter/2);

                Main.circle(Xzero1,Yzero1,newDiameter, newX1,newY1);

                cb.stroke();
                stamper.close();
                reader2 = new PdfReader("Experiment.pdf");
                stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();

                reader1 = new PdfReader("Experiment1.pdf");
                stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();


            } catch (Exception e) {
                System.err.println(e.getMessage());
            }


        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////// FOR THE ANGLE FROM THE BOTTOM TO THE TOP and to the right//////////////
        if ((ZZ[3] == max) && (Xfinal1 - Xzero1) > 10 && (Yfinal1 - Yzero1) < -10)  {



            for (int y = 0; y < 9998; y++) {

                Yangle1[y] = 0.0;
                Xangle1[y] = 0.0;
            }

                  CountAng = 0;

            for (int y = 0; y < 9998; y++) {
                if (newX1[y] != 0.0) {
                    Yangle1[y] = newY1[y];
                    Xangle1[y] = newX1[y];

                    CountAng = CountAng+1;

                }
        }
            CountAng = CountAng -1;

            System.out.println();
            System.out.print(CountAng);
            System.out.println();

            try {

                PdfReader reader1 = new PdfReader("Experiment.pdf");
                PdfReader reader2 = new PdfReader("Experiment2.pdf");


                PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream("Experiment1.pdf"));

                PdfContentByte cb = stamper.getOverContent(1);

                // cb.concatCTM(AffineTransform.getTranslateInstance(10, 10));

              ////////////////////Condition (from the top of the circle)//////////////////////////////////////////////////////////////////////////////////////////////

                if (((Ycircle1[0] - newDiameterSpecial/2) - Yangle1[0] <= 15) && ((Ycircle1[0] - newDiameterSpecial/2) - Yangle1[0] >= -15) && ((Xcircle1[0] + newDiameterSpecial/2)-Xangle1[0]) <= 15 && ((Xcircle1[0]+newDiameterSpecial/2)-Xangle1[0]) >= -15 ) {

                    cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                    cb.setLineWidth(2f);
                    cb.moveTo((float) (Xcircle1[0] + newDiameterSpecial/2), (float) (Ycircle1[0] - newDiameterSpecial/2));
                    cb.lineTo((float) (Xcircle1[0] + newDiameterSpecial/2), (float) Yfinal1);

                    cb.moveTo((float) (Xcircle1[0] + newDiameterSpecial/2 - 1), (float) Yfinal1);
                    cb.lineTo((float) Xfinal1, (float) Yfinal1);

                    Main.CurveBTR(Xcircle1, newDiameterSpecial, Ycircle1, Xzero1,Yfinal1);
                    cb.stroke();
                    stamper.close();
                    reader2 = new PdfReader("Experiment.pdf");
                    stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                    cb = stamper.getOverContent(1);

                    stamper.close();

                    reader1 = new PdfReader("Experiment1.pdf");
                    stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                    cb = stamper.getOverContent(1);

                    stamper.close();
                    return;
                }

                cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                cb.setLineWidth(2f);
                cb.moveTo((float) Xzero1 , (float) Yzero1);
                cb.lineTo((float) Xzero1 , (float) Yfinal1 );

                cb.moveTo((float) Xzero1-1 , (float) Yfinal1);
                cb.lineTo((float) Xfinal1 , (float) Yfinal1 );

                Main.CurveBTR1(Xzero1,Yzero1,Xfinal1,Yfinal1);

                cb.stroke();
                stamper.close();
                reader2 = new PdfReader("Experiment.pdf");
                stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();

                reader1 = new PdfReader("Experiment1.pdf");
                stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }


            return;
        }


        //////////////////////////////////////////////////// FOR THE Angle FROM THE TOP TO THE BOTTOM to the Right///////////////////////////////////////

        if ((ZZ[4] == max) && (Xfinal1 - Xzero1) > 10 && (Yfinal1 - Yzero1) > 10)  {
            try {

                PdfReader reader1 = new PdfReader("Experiment.pdf");
                PdfReader reader2 = new PdfReader("Experiment2.pdf");


                PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream("Experiment1.pdf"));

                PdfContentByte cb = stamper.getOverContent(1);

                // cb.concatCTM(AffineTransform.getTranslateInstance(10, 10));

                cb.concatCTM(1f, 0f, 0f, -1f, 0f, PageSize.A3.getHeight());
                cb.setLineWidth(2f);
                cb.moveTo((float) Xzero1 , (float) Yzero1);
                cb.lineTo((float) Xzero1 , (float) Yfinal1 );

                cb.moveTo((float) Xzero1-1 , (float) Yfinal1);
                cb.lineTo((float) Xfinal1 , (float) Yfinal1 );

                Main.CurveTBR(Xzero1,Yzero1,Xfinal1,Yfinal1);


                cb.stroke();
                stamper.close();
                reader2 = new PdfReader("Experiment.pdf");
                stamper = new PdfStamper(reader2, new FileOutputStream("Experiment2.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();

                reader1 = new PdfReader("Experiment1.pdf");
                stamper = new PdfStamper(reader1, new FileOutputStream("Experiment.pdf"));

                cb = stamper.getOverContent(1);

                stamper.close();
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }


            return;
        }

        Newtest2 = null;

    }

}


