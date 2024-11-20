package org.project.Panels;

import org.project.Functions.Calculations;
import org.graalvm.collections.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsPanel extends JPanel {
    private static final Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD,20);
    private static TextAreaPanel mainTextArea;

    public ButtonsPanel(TextAreaPanel textAreaPanel){
        GridLayout gridLayout = new GridLayout(5,4,5,5);
        setLayout(gridLayout);

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(400,400));

        mainTextArea = textAreaPanel;

        createButtons();
    }

    private void createButtons() {
        String[] operationsAndNumbers = new String[]{
                "C", "CA", "SQRT", "=",
                "1", "2", "3", "-",
                "4", "5", "6", "+",
                "7", "8", "9", "*",
                "-/+", "0", ".", "/"
        };

        for (String buttonSymbol : operationsAndNumbers) {
            JButton button = new JButton(buttonSymbol);
            button.setFont(buttonFont);
            button.setBackground(Color.WHITE);

            if(buttonSymbol.length() > 1 || buttonSymbol.charAt(0) == '=' || buttonSymbol.charAt(0) == 'C'){
                button.addActionListener(new OperationButtonAction());
            }else{
                button.addActionListener(new NumberButtonAction());
            }

            if (buttonSymbol.equals("=")) {
                button.setBackground(Color.ORANGE);
            }

            add(button);
        }
    }

    private static class NumberButtonAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton numberButton = (JButton) e.getSource();
            mainTextArea.append(numberButton.getText());
        }
    }
    private static class OperationButtonAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton operationButton = (JButton) e.getSource();

            switch (operationButton.getText()){
                case "=" -> doEqualOperation();
                case "C" -> doClearOperation();
                case "CA" -> doClearAllOperation();
                case "SQRT" -> doSqrtOperation();
                case "-/+" -> doSymbolChange();
            }
        }

        private void doSymbolChange() {
            String content = mainTextArea.getContent();
            Pair<String, Integer> lastNum = mainTextArea.getLastNumberAndIndex();

            String newExpr;
            if(lastNum.getRight() == 0){
                newExpr = "(-".concat(content).concat(")");
            }else {
                newExpr = content.substring(0, lastNum.getRight() + 1)
                        .concat("(-")
                        .concat(lastNum.getLeft())
                        .concat(")");
            }

            mainTextArea.clear();
            mainTextArea.appendWithoutValidation(newExpr);
        }
        private void doSqrtOperation() {
            String result = Calculations.calculateSQRT(mainTextArea.getContent());
            mainTextArea.clear();
            mainTextArea.appendWithoutValidation(result);
        }
        private void doClearOperation() {
            mainTextArea.clearLast();
        }
        private void doClearAllOperation(){
            mainTextArea.clear();
        }
        private void doEqualOperation(){
            String calcRes = Calculations.calculate(mainTextArea.getContent());
            mainTextArea.clear();
            mainTextArea.appendWithoutValidation(calcRes);
        }
    }
}
