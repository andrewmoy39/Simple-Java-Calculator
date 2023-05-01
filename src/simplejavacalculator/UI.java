/**
 * @name        Simple Java Calculator
 * @package     ph.calculator
 * @file        UI.java
 * @author      SORIA Pierre-Henry
 * @email       pierrehs@hotmail.com
 * @link        http://github.com/pH-7
 * @copyright   Copyright Pierre-Henry SORIA, All Rights Reserved.
 * @license     Apache (http://www.apache.org/licenses/LICENSE-2.0)
 * @create      2012-03-30
 *
 * @modifiedby  Achintha Gunasekara
 * @modifiedby  Kydon Chantzaridis
 * @modweb      http://www.achinthagunasekara.com
 * @modemail    contact@achinthagunasekara.com
 * @modemail    kchantza@csd.auth.gr
 */

package simplejavacalculator;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.Image;
import javax.swing.ImageIcon; 
import java.io.*;
import static java.lang.Math.E;
import static java.lang.Math.PI;

public class UI implements ActionListener {
   
   private final JFrame frame;
   
   private final JPanel panel;
   private final JPanel panelSub1;
   private final JPanel panelSub2;
   private final JPanel panelSub3;
   private final JPanel panelSub4;
   private final JPanel panelSub5;
   private final JPanel panelSub6;
   private final JPanel panelSub7;
   private final JPanel panelSub8;
   private final JPanel panelSub9;
   
   private final JTextArea text;
   
   //added buttons from butPI and on
   private final JButton but[], butAdd, butMinus, butMultiply, butDivide,
      butEqual, butCancel, butSquareRoot, butSquare, butOneDividedBy,
      butCos, butSin, butTan, butxpowerofy, butlog, butrate, butabs, butBinary, butln, butPi, butE, butSquared,
           butThirdSqrt, butFact, but10x, butEx, but2x, butXSqrtX, butMod, butYLogX,butDot,butNeg,butSecond,but3rdSquare;
   private final Calculator calc;
   
   private final String[] buttonValue = {"0", "1", "2", "3", "4", "5", "6",
      "7", "8", "9"};
   
   private boolean secondOn = false;
   private final Font font;
   private final Font textFont;
   private ImageIcon image;
   private BufferedImageCustom imageReturn;
   
   //attempt at automating font size and position of text in buttons to have uniformly sized buttons.
   private void fitTextToButton(JButton button){
       //gets button fot metrics to calculate the size of the button text with given font
       
       /*
        - get font metrics
       - get size of box (height 50, width 50
       - if size of text bigger than either, font--
       - repeat until fixed
       string width must have a 35px buffer
       
       - Failed because the pixel width was not the way to determine font size. Trying to calculate the size 
        and automate it for such low number of buttons was more work than it was worth, so we opted instead to manually fix the few buttons
        that needed it.
       */
       int width1 = 100;
       
       while(width1 >35){
           String buttonText = button.getText();
           Font buttonFont = button.getFont();
           width1 = button.getFontMetrics(buttonFont).stringWidth(buttonText);
           if(width1 > 35){
                button.setFont(buttonFont.deriveFont(buttonFont.getSize()-1));
           
           }
       }
       /*
       //get text
       
       System.out.println(buttonText);
       //get font - java.awt.Font[family=Consolas,name=Consolas,style=plain,size=10]
       //Font buttonFont = button.getFont();
       System.out.println(buttonFont);
       //get width from font metrics
      // int width1 = button.getFontMetrics(buttonFont).stringWidth(buttonText);
       System.out.println(width1);
       
       Dimension size = new Dimension(50,35);
       System.out.println(size);
       
       if(width1 > 50){
           
       }
       
       int maxSize = (int) (size.width * 0.8 / width1 * buttonFont.getSize());
       System.out.println(size.width);
       System.out.println(maxSize);
       System.out.println(buttonFont.getSize());
       
       button.setFont(buttonFont.deriveFont(Math.min(maxSize, buttonFont.getSize())));
       */
   }
   
   
   
   public UI() throws IOException {
      frame = new JFrame("Calculator PH");
      
      imageReturn = new BufferedImageCustom();
      image = new ImageIcon(imageReturn.imageReturn());      
      
      panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      panelSub1 = new JPanel(new FlowLayout());
      panelSub2 = new JPanel(new FlowLayout());
      panelSub3 = new JPanel(new FlowLayout());
      panelSub4 = new JPanel(new FlowLayout());
      panelSub5 = new JPanel(new FlowLayout());
      panelSub6 = new JPanel(new FlowLayout());
      panelSub7 = new JPanel(new FlowLayout());
      panelSub8 = new JPanel(new FlowLayout());
      panelSub9 = new JPanel(new FlowLayout());
      
      font = new Font("Consolas",Font.PLAIN, 18);
      
      text = new JTextArea(1, 30);
      
      textFont = new Font("Consolas",Font.BOLD, 24);
      
      but = new JButton[10];      
      for (int i = 0; i < 10; i++) {
    		 but[i] = new JButton(String.valueOf(i));
                 but[i].setPreferredSize(new Dimension(70, 35));
      }    
      
      //all buttons set to a static size
      butAdd = new JButton("+");
        butAdd.setPreferredSize(new Dimension(70, 35));

        butMinus = new JButton("-");
        butMinus.setPreferredSize(new Dimension(70, 35));

        butMultiply = new JButton("*");
        butMultiply.setPreferredSize(new Dimension(70, 35));

        butDivide = new JButton("/");
        butDivide.setPreferredSize(new Dimension(70, 35));

        butEqual = new JButton("=");
        butEqual.setPreferredSize(new Dimension(70, 35));

        butSquareRoot = new JButton("sqrt");
        butSquareRoot.setPreferredSize(new Dimension(70, 35));

        butSquare = new JButton("x^2");
        butSquare.setPreferredSize(new Dimension(70, 35));
        
        but3rdSquare = new JButton("x^3");
        butSquare.setPreferredSize(new Dimension(70, 35));

        butOneDividedBy = new JButton("1/x");
        butOneDividedBy.setPreferredSize(new Dimension(70, 35));

        butCos = new JButton("Cos");
        butCos.setPreferredSize(new Dimension(70, 35));

        butSin = new JButton("Sin");
        butSin.setPreferredSize(new Dimension(70, 35));

        butTan = new JButton("Tan");
        butTan.setPreferredSize(new Dimension(70, 35));

        butln = new JButton("ln");
        butln.setPreferredSize(new Dimension(70, 35));

        butxpowerofy = new JButton("x^y");
        butxpowerofy.setPreferredSize(new Dimension(70, 35));

        butlog = new JButton("log");
        butlog.setPreferredSize(new Dimension(70, 35));

        butrate = new JButton("x%");
        butrate.setPreferredSize(new Dimension(70, 35));

        butabs = new JButton("|X|");
        butabs.setPreferredSize(new Dimension(70, 35));

        butCancel = new JButton("C");
        butCancel.setPreferredSize(new Dimension(70, 35));

        butBinary = new JButton("Bin");
        butBinary.setPreferredSize(new Dimension(70, 35));

        butPi = new JButton("pi");
        butPi.setPreferredSize(new Dimension(70, 35));

        butE = new JButton("e");
        butE.setPreferredSize(new Dimension(70, 35));

        butSquared = new JButton("x^2");
        butSquared.setPreferredSize(new Dimension(70, 35));

        butThirdSqrt = new JButton("3rd sqrt");
        butThirdSqrt.setPreferredSize(new Dimension(70, 35));

        butFact = new JButton("n!");
        butFact.setPreferredSize(new Dimension(70, 35));

        but10x = new JButton("10^x");
        but10x.setPreferredSize(new Dimension(70, 35));

        butEx = new JButton("e^x");
        butEx.setPreferredSize(new Dimension(70, 35));

        but2x = new JButton("2^x");
        but2x.setPreferredSize(new Dimension(70, 35));

        butXSqrtX = new JButton("Ysqrt(X)");
        butXSqrtX.setPreferredSize(new Dimension(70, 35));

        butMod  = new JButton("mod");
        butMod.setPreferredSize(new Dimension(70, 35));

        butYLogX = new JButton("LogY(X)");
        butYLogX.setPreferredSize(new Dimension(70, 35));

        butDot = new JButton(".");
        butDot.setPreferredSize(new Dimension(70, 35));

        butNeg = new JButton("+/-");
        butNeg.setPreferredSize(new Dimension(70, 35));
        
        butSecond = new JButton("2nd");
        butSecond.setPreferredSize(new Dimension(70, 35));
      
      
      
      calc = new Calculator();
      
   }
   
   public void init() {      
      frame.setSize(500, 600);
      frame.setLocationRelativeTo(null); 
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setIconImage(image.getImage());
      
      text.setFont(textFont);
      text.setEditable(false);
      
      for (int i = 0; i < 10; i++) {
         but[i].setFont(font);
      }      
      butAdd.setFont(font);//
      butMinus.setFont(font);//
      butMultiply.setFont(font);//
      butDivide.setFont(font);//
      butEqual.setFont(font);//
      butSquareRoot.setFont(new Font("Consolas",Font.PLAIN, 17));//
      butSquare.setFont(font);//
      butOneDividedBy.setFont(font);//
      butCos.setFont(font);
      butSin.setFont(font);
      butTan.setFont(font);
      butln.setFont(font); //
      butxpowerofy.setFont(font);//
      butlog.setFont(font);//
      butrate.setFont(font);//
      butabs.setFont(font);//
      butCancel.setFont(font);//
      butBinary.setFont(font);//
      
      //COMP312 additions begin here
      butPi.setFont(font); //
      butE.setFont(font);//
      //butSquared.setFont(font);
      butThirdSqrt.setFont(new Font("Consolas",Font.PLAIN, 8));//
      butFact.setFont(font);//
      but10x.setFont(new Font("Consolas",Font.PLAIN, 16));//
      butEx.setFont(font);//
      but2x.setFont(font);//
      butXSqrtX.setFont(new Font("Consolas",Font.PLAIN, 8));//
      butMod.setFont(font); //                            
      butYLogX.setFont(new Font("Consolas",Font.PLAIN, 9));//
      butDot.setFont(font);//
      butNeg.setFont(font);
      butSecond.setFont(font);
      but3rdSquare.setFont(font);
      
      /*
      * reorganized the buttons to look more uniform and neat
      * 
      */
     panel.add(Box.createHorizontalStrut(100));
      panelSub1.add(text);
      panel.add(panelSub1);
      
      
      
      
      panelSub2.add(butSecond);
      panelSub2.add(butSin);
      panelSub2.add(butCos);
      panelSub2.add(butTan);
      panelSub2.add(butMod);
      panel.add(panelSub2);
      
      panelSub3.add(butSquare);
      panelSub3.add(butOneDividedBy);
      panelSub3.add(butabs);
      panelSub3.add(butBinary);
      panelSub3.add(butrate);
      panel.add(panelSub3);
      
      panelSub4.add(butSquareRoot);
      panelSub4.add(butFact);
      panelSub4.add(butE);
      panelSub4.add(butPi);
      panelSub4.add(butDivide);
      panel.add(panelSub4);
      
      panelSub5.add(butxpowerofy);
      panelSub5.add(but[7]);
      panelSub5.add(but[8]);
      panelSub5.add(but[9]);
      panelSub5.add(butMultiply);
      panel.add(panelSub5);
      
      panelSub6.add(but10x);
      panelSub6.add(but[4]);
      panelSub6.add(but[5]);
      panelSub6.add(but[6]);
      panelSub6.add(butMinus);
      panel.add(panelSub6);
      
      panelSub7.add(butlog);
      panelSub7.add(but[1]);
      panelSub7.add(but[2]);
      panelSub7.add(but[3]);
      panelSub7.add(butAdd);
      panel.add(panelSub7);
      
      
      panelSub8.add(butln);
      panelSub8.add(butNeg);
      panelSub8.add(but[0]);
      panelSub8.add(butDot);
      panelSub8.add(butEqual);
      panel.add(panelSub8);
      
      panelSub9.add(butCancel);
      panel.add(panelSub9);
     
      
      for (int i = 0; i < 10; i++) {
         but[i].addActionListener(this);
      }      
      butAdd.addActionListener(this);
      butMinus.addActionListener(this);
      butMultiply.addActionListener(this);
      butDivide.addActionListener(this);
      butSquare.addActionListener(this);
      butSquareRoot.addActionListener(this);
      butOneDividedBy.addActionListener(this);
      butCos.addActionListener(this);
      butSin.addActionListener(this);
      butTan.addActionListener(this);
      butln.addActionListener(this); 
      butxpowerofy.addActionListener(this);
      butlog.addActionListener(this);
      butrate.addActionListener(this);
      butabs.addActionListener(this);
      butBinary.addActionListener(this);
      butEx.addActionListener(this);
      butPi.addActionListener(this);
      butE.addActionListener(this);
      butThirdSqrt.addActionListener(this);
      butFact.addActionListener(this);
      but10x.addActionListener(this);
      but2x.addActionListener(this);
      butXSqrtX.addActionListener(this);
      butMod.addActionListener(this);
      butYLogX.addActionListener(this);
      butNeg.addActionListener(this);
      butSecond.addActionListener(this);
      but3rdSquare.addActionListener(this);
      
      butEqual.addActionListener(this);
      butCancel.addActionListener(this);
      
      frame.add(panel);
      frame.setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      final Object source = e.getSource();
      Double checkNum = null;

      for (int i = 0; i < 10; i++) {
         if (source == but[i]) {
            text.replaceSelection(buttonValue[i]);
            return;
         }
      }

      /*
      * For Pi and E, they act differently from the number buttons because you are not supposed to concantenate them with other numbers,
      * unlike the rest of the number buttons. They also do take a number to work, so they can be used when checkNum == null.
      * Therefore, they had to be placed away from the other button statements and we had to use some hackwork to get them
      * to function as intended. 
      */
      if (source == butPi){
             if(checkNum == null){
              text.replaceSelection("0");
          }
          writer(calc.calculateMono(Calculator.MonoOperatorModes.pi, reader()));
      }
      
      if (source == butE){
          if(checkNum == null){
              text.replaceSelection("0");
          }
          writer(calc.calculateMono(Calculator.MonoOperatorModes.e, reader()));
           
         }
      
      /*
      * creates a "2nd" button like on some calculators to minimize clutter among buttons that are similar.
      * removes and adds the button for each of the buttons that change.
      * The UI is then re-validated and updated.
      * Inspired from Windows' calculator.
      */
      if (source == butSecond){
          if(!secondOn){
              panelSub8.remove(butln);
              panelSub7.remove(butlog);
              panelSub8.add(butEx,0);
              panelSub7.add(butYLogX,0);
              panelSub6.remove(but10x);
              panelSub6.add(but2x,0);
              panelSub5.remove(butxpowerofy);
              panelSub5.add(butXSqrtX,0);
              panelSub4.remove(butSquareRoot);
              panelSub4.add(butThirdSqrt,0);
              panelSub3.remove(butSquare);
              panelSub3.add(but3rdSquare,0);
              secondOn = true;
          }else if(secondOn){
              panelSub8.remove(butEx);
              panelSub8.add(butln,0);
              panelSub7.remove(butYLogX);
              panelSub7.add(butlog,0);
              panelSub6.remove(but2x);
              panelSub6.add(but10x,0);
              panelSub5.remove(butXSqrtX);
              panelSub5.add(butxpowerofy,0);
              panelSub4.remove(butThirdSqrt);
              panelSub4.add(butSquareRoot,0);
              panelSub3.remove(but3rdSquare);
              panelSub3.add(butSquare,0);
              secondOn = false;
          }
          panelSub8.validate();
          panelSub8.updateUI();
          
          panelSub7.validate();
          panelSub7.updateUI();
          
          panelSub6.validate();
          panelSub6.updateUI();
          
          panelSub5.validate();
          panelSub5.updateUI();
          
          panelSub4.validate();
          panelSub4.updateUI();
          
          panelSub3.validate();
          panelSub3.updateUI();
      }
      
      
      try {
         checkNum = Double.parseDouble(text.getText());
      } catch(NumberFormatException k) {

      }

      if (checkNum != null || source == butCancel) {
         if (source == butAdd) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.add, reader()));
            text.replaceSelection(butAdd.getText());
         }

         if (source == butMinus) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.minus, reader()));
            text.replaceSelection(butMinus.getText());
         }

         if (source == butMultiply) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.multiply, reader()));
            text.replaceSelection(butMultiply.getText());
         }

         if (source == butDivide) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.divide, reader()));
            text.replaceSelection(butDivide.getText());
         }
         
         if (source == butxpowerofy) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.xpowerofy, reader()));
         }

         if (source == butSquare) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.square, reader()));
         }

         if (source == butSquareRoot)
            writer(calc.calculateMono(Calculator.MonoOperatorModes.squareRoot, reader()));

         if (source == butOneDividedBy)
            writer(calc.calculateMono(Calculator.MonoOperatorModes.oneDividedBy, reader()));

         if (source == butCos)
            writer(calc.calculateMono(Calculator.MonoOperatorModes.cos, reader()));

         if (source == butSin)
            writer(calc.calculateMono(Calculator.MonoOperatorModes.sin, reader()));

         if (source == butTan)
            writer(calc.calculateMono(Calculator.MonoOperatorModes.tan, reader()));

         if (source == butlog)
            writer(calc.calculateMono(Calculator.MonoOperatorModes.log, reader()));

         if (source == butln)
            writer(calc.calculateMono(Calculator.MonoOperatorModes.ln, reader())); 

         if (source == butrate)
            writer(calc.calculateMono(Calculator.MonoOperatorModes.rate, reader()));

         if (source == butabs)
            writer(calc.calculateMono(Calculator.MonoOperatorModes.abs, reader()));
         
         //COMP 312 work begins here
         if (source == butEx)
             writer(calc.calculateMono(Calculator.MonoOperatorModes.ex, reader()));
         
         if (source == butThirdSqrt)
             writer(calc.calculateMono(Calculator.MonoOperatorModes.th_sqrt, reader()));
         
         if (source == butFact)
             writer(calc.calculateMono(Calculator.MonoOperatorModes.fact, reader()));
         
         if (source == but10x)
             writer(calc.calculateMono(Calculator.MonoOperatorModes.tenx, reader()));
         
         if (source == but2x)
             writer(calc.calculateMono(Calculator.MonoOperatorModes.twox, reader()));
         
         if (source == butXSqrtX)
             writer(calc.calculateBi(Calculator.BiOperatorModes.ysqrtx, reader()));
         
         if (source == butMod)
             writer(calc.calculateBi(Calculator.BiOperatorModes.mod, reader()));
         
         if (source == butYLogX)
             writer(calc.calculateBi(Calculator.BiOperatorModes.logyx, reader()));
         
         if (source == butNeg)
             writer(calc.calculateMono(Calculator.MonoOperatorModes.neg, reader()));
         
         if (source == but3rdSquare)
             writer(calc.calculateMono(Calculator.MonoOperatorModes.thirdSq, reader()));
         
         //---
         
         if (source == butEqual)
            writer(calc.calculateEqual(reader()));

         if (source == butCancel)
            writer(calc.reset());

         if (source == butBinary)
            parsetoBinary();
      }

      text.selectAll();
   }
   
   private void parsetoBinary() {
      try {
          //some of the new functions returns doubles, which the Binary button could not parse. 
          //the three lines below allow the binary button to parse doubles, although they still have to be integers to be converted.
         Double parseToD = Double.parseDouble(text.getText());
         int toInt = parseToD.intValue();
         String toString = Integer.toString(toInt);
         text.setText("" + Long.toBinaryString(Long.parseLong(toString)));
      } catch (NumberFormatException ex) {
         System.err.println("Error while parse to binary." + ex.toString());
      }
   }
   
   public Double reader() {
      Double num;
      String str;
      str = text.getText();
      num = Double.valueOf(str);
      
      return num;
   }
   
   public void writer(final Double num) {
      if (Double.isNaN(num)) {
         text.setText("");
      } else {
         text.setText(Double.toString(num));
      }
   }
}
