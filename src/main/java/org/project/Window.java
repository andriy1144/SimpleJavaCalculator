package org.project;

import org.project.Panels.ButtonsPanel;
import org.project.Panels.TextAreaPanel;

import javax.swing.*;
import java.awt.*;

public class Window {
    private final JFrame frame;
    private static final String title = "Calculator";

    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 400;

    public Window(){
        frame = new JFrame();

        frame.setSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        frame.setTitle(title);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);

        TextAreaPanel textAreaPanel = TextAreaPanel.getInstance();
        textAreaPanel.addTextAreaAndSetStyle();

        frame.add(textAreaPanel,BorderLayout.NORTH);

        frame.add(new ButtonsPanel(textAreaPanel),BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args){
        new Window();
    }
}
