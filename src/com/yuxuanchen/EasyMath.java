package com.yuxuanchen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


/**
 * EasyMath class.
 * The goal of this project is to help primary school kids or younger
 * practice single digit, double digit, triple digit addition/subtraction
 * and single digit, double digit multiplication.
 *
 * This project uses JFrame. This project uses java.util.Random to get random ints/booleans.
 *
 * The plusMinusButton, multiplyButton, blankFillButton, and exitButton are defined as type buttons.
 * The easyButton, mediumButton, hardButton, and backButton1 are defined as mode buttons.
 * The button1, button2, button3, and backButton2 are defined as answer buttons.
 * The backButton1, backButton2 returns to the beginning of the EasyMath GUI.
 * The submitButton returns the input value.
 *
 * Future goals are adding timers, more types and modes, online multiplayer.
 */
public class EasyMath implements ActionListener {

    int typeDifficulty = -1;
    int difficulty = -1;
    JFrame frame;
    Font fontBig = new Font("Calibre", Font.BOLD, 27);
    Font font = new Font("Calibre", Font.BOLD,16);
    JButton[] type = new JButton[4];
    JButton[] mode = new JButton[4];
    JButton[] answer = new JButton[4];
    JButton plusMinusButton, multiplyButton, blankFillButton, exitButton,
            easyButton, mediumButton, hardButton, backButton1, button1, button2, button3, backButton2, submitButton;
    JTextField textField, textCount, textAnswer, textBlankFill;
    String[] carryGameMode = new String[3];
    Random random = new Random();
    int correctAnswer, correctChoice, residue1, residue2;
    int correctStreak = 0;


    /**
     * Constructor. Sets up the frames and buttons.
     */
    public EasyMath() {
        frame = new JFrame("EasyMath");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 550);
        frame.setLayout(null);
        frame.setResizable(false);

        plusMinusButton = new JButton("add/subtract");
        multiplyButton = new JButton("multiply");
        blankFillButton = new JButton("blank filling");
        exitButton = new JButton("exit");

        easyButton = new JButton("easy mode");
        mediumButton = new JButton("medium mode");
        hardButton = new JButton("hard mode");
        backButton1 = new JButton("go back");

        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        backButton2 = new JButton("go back");

        submitButton = new JButton("submit");

        type[0] = plusMinusButton;
        type[1] = multiplyButton;
        type[2] = blankFillButton;
        type[3] = exitButton;

        mode[0] = easyButton;
        mode[1] = mediumButton;
        mode[2] = hardButton;
        mode[3] = backButton1;

        answer[0] = button1;
        answer[1] = button2;
        answer[2] = button3;
        answer[3] = backButton2;

        for (int i = 0; i < 4; i++) {
            type[i].setFont(font);
            type[i].setBounds(60 + 140*i, 300, 140, 50);
            type[i].addActionListener(this);
            type[i].setFocusable(false);
        }

        for (int i = 0; i < 4; i++) {
            mode[i].setFont(font);
            mode[i].setBounds(60 + 140*i, 300, 140, 50);
            mode[i].setVisible(false);
            mode[i].addActionListener(this);
            mode[i].setFocusable(false);
        }

        for (int i = 0; i < 4; i++) {
            answer[i].setFont(font);
            answer[i].setBounds(60 + 140*i, 300, 140, 50);
            answer[i].setVisible(false);
            answer[i].addActionListener(this);
            answer[i].setFocusable(false);
        }

        submitButton.setFont(font);
        submitButton.setBounds(340, 300, 140, 50);
        submitButton.setVisible(false);
        submitButton.addActionListener(this);
        submitButton.setFocusable(false);

        textField = new JTextField();
        textField.setFont(fontBig);
        textField.setText("Easy Math, are you ready?");
        textField.setBounds(155, 100, 350, 40);
        textField.setEditable(false);

        textCount = new JTextField();
        textCount.setFont(font);
        this.setTextCount(0);
        textCount.setBounds(155, 400, 350, 40);
        textCount.setEditable(false);
        textCount.setVisible(false);

        textAnswer = new JTextField();
        textAnswer.setFont(font);
        textAnswer.setBounds(155, 180, 350, 40);
        textAnswer.setEditable(false);
        textAnswer.setVisible(false);

        textBlankFill = new JTextField();
        textBlankFill.setFont(font);
        textBlankFill.setBounds(60, 300, 280, 50);
        textBlankFill.setEditable(true);
        textBlankFill.setVisible(false);

        frame.add(type[0]);
        frame.add(type[1]);
        frame.add(type[2]);
        frame.add(type[3]);

        frame.add(mode[0]);
        frame.add(mode[1]);
        frame.add(mode[2]);
        frame.add(mode[3]);

        frame.add(answer[0]);
        frame.add(answer[1]);
        frame.add(answer[2]);
        frame.add(answer[3]);

        frame.add(submitButton);

        frame.add(textField);
        frame.add(textCount);
        frame.add(textAnswer);
        frame.add(textBlankFill);

        frame.setVisible(true);
    }


    /**
     * main. Where the project starts to run.
     */
    public static void main(String[] args) {
        new EasyMath();
    }


    /**
     * implementation of interface ActionListener.
     * Overriding the method actionPerformed().
     * @param e ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (mode[0].isVisible()) {
            if (e.getSource() == mode[0]) {
                difficulty = 1;
            }
            if (e.getSource() == mode[1]) {
                difficulty = 2;
            }
            if (e.getSource() == mode[2]) {
                difficulty = 3;
            }

            textCount.setVisible(true);
            this.clickMode();
        }

        if (e.getSource() == type[0]) {
            typeDifficulty = 1;
            for (int i = 0; i < 4; i++) {
                type[i].setVisible(false);
                mode[i].setVisible(true);
            }
        }

        if (e.getSource() == type[1]) {
            typeDifficulty = 2;
            for (int i = 0; i < 4; i++) {
                type[i].setVisible(false);
                mode[i].setVisible(true);
            }
        }

        if (e.getSource() == type[2]) {
            typeDifficulty = 3;
            for (int i = 0; i < 4; i++) {
                type[i].setVisible(false);
                mode[i].setVisible(true);
            }
        }

        if (e.getSource() == type[3]) {
            System.exit(0);
        }

        if (e.getSource() == answer[0]) {
            this.clickAnswer(0);
        }

        if (e.getSource() == answer[1]) {
            this.clickAnswer(1);
        }

        if (e.getSource() == answer[2]) {
            this.clickAnswer(2);
        } else if (e.getSource() == submitButton) {
            this.clickAnswer(3);
        }
        if (e.getSource() == mode[3] || e.getSource() == answer[3]) {
            this.clickBack1();
        }

    }


    /**
     * gameMode() method starts/continues the question challenge by refreshing the question on textField
     * and text on buttons.
     * @param setTypeDifficulty the difficulty of types (addition and subtraction, multiplication, blank filling).
     * @param setDifficulty the difficulty of level (easy, medium, hard).
     * @return an array of strings, index 0: question, index 1: correctAnswer, index 2: correct button choice.
     */
    public static String[] gameMode(int setTypeDifficulty, int setDifficulty) {
        String[] array = new String[3];
        Random random = new Random();
        int numberOne = -1, numberTwo = -1;

        if (setTypeDifficulty == 1) {
            boolean addOrSubtract = random.nextBoolean();
            if (setDifficulty == 1) {
                numberOne = random.nextInt(10);
                numberTwo = random.nextInt(10);
            }

            if (setDifficulty == 2) {
                do {
                    numberOne = random.nextInt(100);
                    numberTwo = random.nextInt(100);
                }
                while (numberOne < 10 || numberTwo < 10);
            }

            if (setDifficulty == 3) {
                do {
                    numberOne = random.nextInt(1000);
                    numberTwo = random.nextInt(1000);
                }
                while (numberOne < 100 || numberTwo < 100);
            }

            int randomChoice = random.nextInt(3);

            if (addOrSubtract) {
                array[0] = numberOne + " + " + numberTwo;
                array[1] = Integer.toString(numberOne + numberTwo);
            } else {
                array[0] = numberOne + " - " + numberTwo;
                array[1] = Integer.toString(numberOne - numberTwo);
            }
            array[2] = Integer.toString(randomChoice);
        }

        if (setTypeDifficulty == 2) {
            if (setDifficulty == 1) {
                numberOne = random.nextInt(10);
                numberTwo = random.nextInt(10);
            }

            if (setDifficulty == 2) {
                do {
                    numberOne = random.nextInt(100);
                    numberTwo = random.nextInt(10);
                }
                while (numberOne < 10);
            }

            if (setDifficulty == 3) {
                do {
                    numberOne = random.nextInt(100);
                    numberTwo = random.nextInt(100);
                }
                while (numberOne < 10 || numberTwo < 10);
            }

            int randomChoice = random.nextInt(3);

            array[0] = numberOne + " x " + numberTwo;
            array[1] = Integer.toString(numberOne * numberTwo);
            array[2] = Integer.toString(randomChoice);
        }

        if (setTypeDifficulty == 3) {
            boolean addSubtractOrMultiply = random.nextBoolean();
            boolean addOrSubtract = random.nextBoolean();
            if (setDifficulty == 1) {
                numberOne = random.nextInt(10);
                numberTwo = random.nextInt(10);
            }

            if (setDifficulty == 2 && addSubtractOrMultiply) {
                do {
                    numberOne = random.nextInt(100);
                    numberTwo = random.nextInt(100);
                }
                while (numberOne < 10 || numberTwo < 10);
            }

            if (setDifficulty == 2 && !addSubtractOrMultiply) {
                do {
                    numberOne = random.nextInt(100);
                    numberTwo = random.nextInt(10);
                }
                while (numberOne < 10);
            }

            if (setDifficulty == 3) {
                do {
                    numberOne = random.nextInt(100);
                    numberTwo = random.nextInt(100);
                }
                while (numberOne < 10 || numberTwo < 10);
            }

            int randomChoice = random.nextInt(3);

            if (addSubtractOrMultiply && addOrSubtract) {
                array[0] = numberOne + " + " + numberTwo;
                array[1] = Integer.toString(numberOne + numberTwo);
                array[2] = Integer.toString(randomChoice);
            }

            if (addSubtractOrMultiply && !addOrSubtract) {
                array[0] = numberOne + " - " + numberTwo;
                array[1] = Integer.toString(numberOne - numberTwo);
                array[2] = Integer.toString(randomChoice);
            }

            if (!addSubtractOrMultiply) {
                array[0] = numberOne + " x " + numberTwo;
                array[1] = Integer.toString(numberOne * numberTwo);
                array[2] = Integer.toString(randomChoice);
            }
        }
        return array;
    }


    /**
     * clickMode() method starts the forming of questions and changes the mode buttons to answer buttons.
     * For add/subtract and multiply types, there is only one correct answer button, and it is determined randomly.
     * The numbers on the answer buttons are randomly made, though all are close to the true answer.
     * For blank filling type, there is a submit button and a JText for typing numbers.
     */
    public void clickMode() {
        carryGameMode = EasyMath.gameMode(typeDifficulty, difficulty);
        textField.setText(carryGameMode[0]);
        mode[0].setVisible(false);
        mode[1].setVisible(false);
        mode[2].setVisible(false);
        mode[3].setVisible(false);

        correctAnswer = Integer.parseInt(carryGameMode[1]);
        correctChoice = Integer.parseInt(carryGameMode[2]);

        if (typeDifficulty == 1 || typeDifficulty == 2) {
            answer[0].setVisible(true);
            answer[1].setVisible(true);
            answer[2].setVisible(true);
            answer[3].setVisible(true);

            answer[correctChoice].setText(carryGameMode[1]);
            if (random.nextBoolean()) {
                residue1 = correctAnswer + 1 + random.nextInt(4);
            } else {
                residue1 = correctAnswer - 1 - random.nextInt(4);
            }
            if (random.nextBoolean()) {
                residue2 = correctAnswer + 1 + random.nextInt(4);
            } else {
                residue2 = correctAnswer - 1 - random.nextInt(4);
            }
            if (correctChoice == 0) {
                answer[1].setText(Integer.toString(residue1));
                if (!(residue1 == residue2)) {
                    answer[2].setText(Integer.toString(residue2));
                } else {
                    answer[2].setText(Integer.toString(residue1 + 6));
                }
            }
            if (correctChoice == 1) {
                answer[0].setText(Integer.toString(residue1));
                if (!(residue1 == residue2)) {
                    answer[2].setText(Integer.toString(residue2));
                } else {
                    answer[2].setText(Integer.toString(residue1 + 6));
                }
            }
            if (correctChoice == 2) {
                answer[0].setText(Integer.toString(residue1));
                if (!(residue1 == residue2)) {
                    answer[1].setText(Integer.toString(residue2));
                } else {
                    answer[1].setText(Integer.toString(residue1 + 6));
                }
            }
        } else if (typeDifficulty == 3) {
            submitButton.setVisible(true);
            answer[3].setVisible(true);
            textBlankFill.setVisible(true);
            textBlankFill.setText("");
        }
    }


    /**
     * clickAnswer() method refreshes the question and answer buttons.
     * If the answer selected is incorrect, the challenge ends.
     *
     * Increases the count success streak if chosen the correct answer.
     * @param i represents the correct button with the true correct answer.
     */
    public void clickAnswer(int i) {
        if (correctStreak >= 1000) {
            textField.setBounds(50, 100, 580, 40);
            textField.setText("Well done! Let's try another mode!");
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);

        } else if (i < 3) {
            if (answer[i].getText().equals(Integer.toString(correctAnswer))) {
                carryGameMode = EasyMath.gameMode(typeDifficulty, difficulty);
                textField.setText(carryGameMode[0]);
                correctAnswer = Integer.parseInt(carryGameMode[1]);
                correctChoice = Integer.parseInt(carryGameMode[2]);

                answer[correctChoice].setText(carryGameMode[1]);
                if (random.nextBoolean()) {
                    residue1 = correctAnswer + 1 + random.nextInt(4);
                } else {
                    residue1 = correctAnswer - 1 - random.nextInt(4);
                }
                if (random.nextBoolean()) {
                    residue2 = correctAnswer + 1 + random.nextInt(4);
                } else {
                    residue2 = correctAnswer - 1 - random.nextInt(4);
                }
                if (correctChoice == 0) {
                    answer[1].setText(Integer.toString(residue1));
                    if (!(residue1 == residue2)) {
                        answer[2].setText(Integer.toString(residue2));
                    } else {
                        answer[2].setText(Integer.toString(residue1 + 6));
                    }
                }
                if (correctChoice == 1) {
                    answer[0].setText(Integer.toString(residue1));
                    if (!(residue1 == residue2)) {
                        answer[2].setText(Integer.toString(residue2));
                    } else {
                        answer[2].setText(Integer.toString(residue1 + 6));
                    }
                }
                if (correctChoice == 2) {
                    answer[0].setText(Integer.toString(residue1));
                    if (!(residue1 == residue2)) {
                        answer[1].setText(Integer.toString(residue2));
                    } else {
                        answer[1].setText(Integer.toString(residue1 + 6));
                    }
                }

                this.setTextCount(1);

            } else {
                textField.setBounds(50, 100, 580, 40);
                textField.setText("Oops! Wrong answer! Good luck next time!");
                answer[0].setVisible(false);
                answer[1].setVisible(false);
                answer[2].setVisible(false);
                submitButton.setVisible(false);

                if (correctStreak == 0) {
                    this.setTextCount(4);
                }
                if (correctStreak == 1) {
                    this.setTextCount(2);
                }
                if (correctStreak > 1 && correctStreak <= 10) {
                    this.setTextCount(3);
                }
                if (correctStreak > 10 && correctStreak <= 20) {
                    this.setTextCount(5);
                }
                if (correctStreak > 20 && correctStreak < 50) {
                    this.setTextCount(6);
                }

                textAnswer.setVisible(true);
                textAnswer.setText("The correct answer to " + carryGameMode[0] + " is " + carryGameMode[1]);
            }
        } else if (i == 3) {
            if (textBlankFill.getText().equals(Integer.toString(correctAnswer))) {
                this.setTextCount(1);
                carryGameMode = EasyMath.gameMode(typeDifficulty, difficulty);
                correctAnswer = Integer.parseInt(carryGameMode[1]);
                textField.setText(carryGameMode[0]);
                textBlankFill.setText("");
            } else {
                textField.setBounds(50, 100, 580, 40);
                textField.setText("Oops! Wrong answer! Good luck next time!");
                submitButton.setEnabled(false);

                if (correctStreak == 0) {
                    this.setTextCount(4);
                }
                if (correctStreak == 1) {
                    this.setTextCount(2);
                }
                if (correctStreak > 1 && correctStreak <= 10) {
                    this.setTextCount(3);
                }
                if (correctStreak > 10 && correctStreak <= 20) {
                    this.setTextCount(5);
                }
                if (correctStreak > 20 && correctStreak < 50) {
                    this.setTextCount(6);
                }

                textAnswer.setVisible(true);
                textAnswer.setText("The correct answer to " + carryGameMode[0] + " is " + carryGameMode[1]);
            }
        }
    }


    /**
     * clickBack1() method returns the user to the first page (with the type buttons).
     */
    public void clickBack1() {
        mode[0].setVisible(false);
        mode[1].setVisible(false);
        mode[2].setVisible(false);
        mode[3].setVisible(false);
        type[0].setVisible(true);
        type[1].setVisible(true);
        type[2].setVisible(true);
        type[3].setVisible(true);
        submitButton.setEnabled(true);
        textCount.setVisible(false);
        correctStreak = 0;
        setTextCount(0);
        textField.setBounds(155, 100, 350, 40);
        textField.setText("Easy Math, are you ready?");
        textAnswer.setVisible(false);
        textBlankFill.setVisible(false);
    }


    /**
     * the responses of correct streak.
     * @param i are the possible responses.
     */
    public void setTextCount(int i) {
        if (i == 0) {
            textCount.setText("Correct streak: " + 0);
        }
        if (i == 1) {
            textCount.setText("Correct streak: " + ++correctStreak);
        }
        if (i == 2) {
            textCount.setText("You got 1 question correct, keep practicing!");
        }
        if (i == 3) {
            textCount.setText("You got " + correctStreak + " questions correct!");
        }
        if (i == 4) {
            textCount.setText("keep practicing and don't give up!");
        }
        if (i == 5) {
            textCount.setText("Well done! You got " + correctStreak + " questions correct!");
        }
        if (i == 6) {
            textCount.setText(correctStreak + " questions correct! You're a master at this!");
        }
    }
}

