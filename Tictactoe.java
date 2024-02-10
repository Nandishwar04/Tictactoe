import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Tictactoe {
    static boolean x = true;
    static JButton[] arr = new JButton[9]; // Moved the array declaration outside the main method to access it within MyListener

    static boolean checkWin() {
        int[][] winCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combination : winCombinations) {
            int first = combination[0];
            int second = combination[1];
            int third = combination[2];

            if (arr[first].getText().equals(arr[second].getText()) &&
                arr[first].getText().equals(arr[third].getText()) &&
                !arr[first].getText().isEmpty()) {
                return true;
            }
        }

        boolean draw = true;
        for (JButton button : arr) {
            if (button.getText().isEmpty()) {
                draw = false;
                break;
            }
        }
        return draw;
    }

    static public void main(String... rk) {
        JFrame f = new JFrame("Tic Tac Toe");
        f.setSize(700, 700);
        f.getContentPane().setBackground(new Color(0, 0, 255, 20));
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel msg = new JLabel("Copyright");
        f.add(msg, BorderLayout.SOUTH);

        //Creating a JPanel for holding the Buttons
        GridLayout gl = new GridLayout(3, 3);
        JPanel p = new JPanel(gl);
        for (int i = 0; i < 9; i++) {
            JButton b = new JButton();
            b.setFont(new Font("Serif", Font.BOLD, 50));
            b.setForeground(Color.RED);
            p.add(b);
            arr[i] = b;
        }
        f.add(p);

        class MyListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                JButton b = (JButton) ae.getSource();
                if (Tictactoe.x) b.setText("X");
                else b.setText("0");
                Tictactoe.x = !Tictactoe.x;
                b.setEnabled(false);
                if (checkWin()) {
                    if (checkDraw()) {
                        JOptionPane.showMessageDialog(null, "It's a draw!");
                    } else {
                        JOptionPane.showMessageDialog(null, (Tictactoe.x ? "0" : "X") + " Wins!");
                    }
                    resetGame();
                }
            }
        }

        MyListener m = new MyListener();
        for (JButton jb : arr) jb.addActionListener(m);

        f.setVisible(true);
    }

    static void resetGame() {
        for (JButton button : arr) {
            button.setText("");
            button.setEnabled(true);
        }
        x = true;
    }

    static boolean checkDraw() {
        for (JButton button : arr) {
            if (button.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
