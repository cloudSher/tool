package com.sher.tool.frame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 *
 *  layout, component, pane
 */
public class JarFrame extends JFrame {

    private JTextArea response = new JTextArea();
    private JLabel addressLabel = new JLabel();
    private JTextField address = new JTextField();

    private JLabel mainLabel = new JLabel();
    private JTextField main = new JTextField();

    private JButton btn = new JButton();
    private JScrollPane pane = new JScrollPane();

    private List<Component> componentList = new ArrayList<>();


    JarFrame(){
        doInit();
    }

    public void doInit(){
        doLayout();
        doComponent();
        doAddComponent();
    }

    public void doLayout(){
        this.getContentPane().setLayout(null);
    }


    public void doComponent(){
        addressLabel.setText("Project Path :");
        addressLabel.setBounds(new Rectangle(6,5,70,20));
        address.setText("F://");
        address.setBounds(new Rectangle(77,5,150,20));
//        address.setBorder();

        mainLabel.setText("Main Class :");
        mainLabel.setBounds(new Rectangle(6,15,70,20));

        main.setText("");
        main.setBounds(new Rectangle(77,15,150,20));

        btn.setText("Create");
        btn.setBounds(new Rectangle(100,5,100,100));

        addComponentList(addressLabel,address,mainLabel,main);

    }

    public void addComponentList(Component... components){
        if(components != null){
            Arrays.asList(components).stream().forEach(componentList::add);
        }
    }

    public void doAddComponent(){
        if(componentList!=null || componentList.size() >0){
            componentList.stream().filter(e-> e instanceof Component).forEach(e ->{
                this.getContentPane().add(e);
            });
        }
    }



    public static void main(String args[]){
        JarFrame jarFrame = new JarFrame();
        jarFrame.setBounds(0,0,400,200);
        jarFrame.setVisible(true);
    }
}
