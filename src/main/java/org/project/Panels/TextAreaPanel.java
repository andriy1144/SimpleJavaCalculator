package org.project.Panels;

import org.graalvm.collections.Pair;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class TextAreaPanel extends JPanel {
    private static final TextAreaPanel instance = new TextAreaPanel();
    private TextAreaPanel(){}
    public static TextAreaPanel getInstance(){
        return instance;
    }

    private static final Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD,35);
    private final JTextPane mainTextArea = new JTextPane();

    private char lastAddedSymbol;

    public void addTextAreaAndSetStyle(){
          setTextAreaStyles();
          add(mainTextArea);

          setPreferredSize(new Dimension(400,160));
    }
    public void setTextAreaStyles(){
        mainTextArea.setFont(buttonFont);
        mainTextArea.setForeground(Color.BLACK);
        mainTextArea.setEditable(false);

        StyledDocument doc = mainTextArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        mainTextArea.setPreferredSize(new Dimension(380,160));
    }

    public void append(String content){
        if(appendValidation(content)){
            mainTextArea.setText(mainTextArea.getText() + content);
            lastAddedSymbol = content.charAt(0);
        }
    }

    public void appendWithoutValidation(String content){
        mainTextArea.setText(mainTextArea.getText() + content);
    }
    public boolean appendValidation(String content){
        char symb = content.charAt(0);
        if(isBlank() && !Character.isDigit(symb)){
            return false;
        }else if(!Character.isDigit(lastAddedSymbol) && !Character.isDigit(symb)) {
            return false;
        }

        if(mainTextArea.getText().equals("!_ERROR_!")){
            clear();
        }

        return true;
    }

    public void clear(){
        mainTextArea.setText("");
    }
    public void clearLast(){
        String content = mainTextArea.getText();
        if(content.isEmpty()) return;
        content = content.substring(0, content.length() - 1);
        mainTextArea.setText(content);
    }

    private boolean isBlank(){
        return mainTextArea.getText().isBlank();
    }

    public String getContent(){
        return this.mainTextArea.getText().trim();
    }
    public Pair<String,Integer> getLastNumberAndIndex(){
        String content = this.mainTextArea.getText();
        int contLen = content.length() - 1;

        char num = content.charAt(contLen);
        while (!Character.isDigit(num) && contLen >= 0){
            num = content.charAt(--contLen);
        }

        StringBuilder lastNum = new StringBuilder();
        while(Character.isDigit(num) && contLen > 0){
            lastNum.append(num);
            num = content.charAt(--contLen);
        }

        return Pair.create(lastNum.reverse().toString(), contLen);
    }
}