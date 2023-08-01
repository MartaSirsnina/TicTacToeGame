import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private JButton[] buttons;
    private char currentPlayer;
    private boolean gameEnded;

    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[9];
        currentPlayer = 'X';
        gameEnded = false;

        initializeButtons();
    }

    private void initializeButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(new ButtonClickListener(i));
            add(buttons[i]);
        }
    }

    private void checkForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(buttons[i * 3].getText(), buttons[i * 3 + 1].getText(), buttons[i * 3 + 2].getText())
                    || checkRowCol(buttons[i].getText(), buttons[i + 3].getText(), buttons[i + 6].getText())) {
                gameEnded = true;
                highlightWinningCells(i * 3, i * 3 + 1, i * 3 + 2);
                return;
            }
        }

        if (checkRowCol(buttons[0].getText(), buttons[4].getText(), buttons[8].getText())
                || checkRowCol(buttons[2].getText(), buttons[4].getText(), buttons[6].getText())) {
            gameEnded = true;
            highlightWinningCells(0, 4, 8);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                return;
            }
        }

        gameEnded = true;
        JOptionPane.showMessageDialog(this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean checkRowCol(String c1, String c2, String c3) {
        return !c1.equals("") && c1.equals(c2) && c2.equals(c3);
    }

    private void highlightWinningCells(int cell1, int cell2, int cell3) {
        buttons[cell1].setBackground(Color.GREEN);
        buttons[cell2].setBackground(Color.GREEN);
        buttons[cell3].setBackground(Color.GREEN);

        String winner = buttons[cell1].getText();
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
    }

    private class ButtonClickListener implements ActionListener {
        private int buttonIndex;

        public ButtonClickListener(int buttonIndex) {
            this.buttonIndex = buttonIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gameEnded && buttons[buttonIndex].getText().equals("")) {
                buttons[buttonIndex].setText(String.valueOf(currentPlayer));
                buttons[buttonIndex].setForeground(currentPlayer == 'X' ? Color.BLUE : Color.RED);
                checkForWin();
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGame game = new TicTacToeGame();
            game.setVisible(true);
        });
    }
}
