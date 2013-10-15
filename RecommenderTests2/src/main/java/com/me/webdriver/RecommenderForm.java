/*
 * Created by JFormDesigner on Wed Sep 04 16:44:08 EEST 2013
 */

package com.me.webdriver;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author Seth Gecko
 */
public class RecommenderForm extends JFrame {
    public RecommenderForm() {
        initComponents();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        if (checkBox1.isSelected()&& radioButton1.isSelected()){
            try {
                boolean browserChrome = true;
                boolean browserFireFox = false;
                boolean browserIE = false;
                RecommenderTests.recommenderTest1(".tunehog.com", "(Recommender_Test_1)" , "PROD", browserChrome, browserFireFox, browserIE);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if (checkBox2.isSelected()&& radioButton1.isSelected()){
            try {
                boolean browserChrome = true;
                boolean browserFireFox = false;
                boolean browserIE = false;
                RecommenderTests.recommenderTest1(".qa.vocvox.com", "(Recommender_Test_1)", "QA", browserChrome, browserFireFox, browserIE);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if (checkBox1.isSelected()&& radioButton2.isSelected()){
            try {
                boolean browserChrome = false;
                boolean browserFireFox = true;
                boolean browserIE = false;
                RecommenderTests.recommenderTest1(".tunehog.com", "(Recommender_Test_1)" , "PROD", browserChrome , browserFireFox, browserIE);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if (checkBox2.isSelected()&& radioButton2.isSelected()){
            try {
                boolean browserChrome = false;
                boolean browserFireFox = true;
                boolean browserIE = false;
                RecommenderTests.recommenderTest1(".qa.vocvox.com", "(Recommender_Test_1)", "QA", browserChrome, browserFireFox, browserIE);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Seth Gecko
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        buttonBar = new JPanel();
        label1 = new JLabel();
        okButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(Borders.DIALOG_BORDER);

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new FormLayout(
                    "7*(default, $lcgap), default",
                    "4*(default, $lgap), default"));

                //---- checkBox1 ----
                checkBox1.setText("PROD");
                contentPanel.add(checkBox1, CC.xy(5, 3));

                //---- checkBox2 ----
                checkBox2.setText("QA");
                contentPanel.add(checkBox2, CC.xy(15, 3));

                //---- radioButton1 ----
                radioButton1.setText("Chrome");
                contentPanel.add(radioButton1, CC.xy(5, 9));

                //---- radioButton2 ----
                radioButton2.setText("Firefox");
                contentPanel.add(radioButton2, CC.xy(15, 9));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
                buttonBar.setLayout(new FormLayout(
                    "$lcgap, default, $glue, $button",
                    "pref"));

                //---- label1 ----
                label1.setText("Recommender Tests v.0.0.1");
                buttonBar.add(label1, CC.xy(2, 1));

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        okButtonActionPerformed(e);
                    }
                });
                buttonBar.add(okButton, CC.xy(4, 1));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Seth Gecko
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JPanel buttonBar;
    private JLabel label1;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
