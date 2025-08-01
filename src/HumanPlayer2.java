//import java.util.Scanner;
//public class HumanPlayer2 extends Player2 {
//    public HumanPlayer2(char symbol){
//        super(symbol);
//    }
//
//    @Override
//    public int[] getMove(Board2 board) {
//        Scanner input = new Scanner(System.in);
//        System.out.print("Row: ");
//        int row = input.nextInt();
//        System.out.print("Column: ");
//        int col = input.nextInt();
//        return new int[]{row-1,col-1};
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
public class HumanPlayer2 extends Player2 {
    private Board2 board;
    private int[] move = new int[2];
    private CountDownLatch latch;
    public HumanPlayer2(char symbol) {
        super(symbol);
    }
    @Override
    public int[] getMove(Board2 board) {
        this.board = board;
        latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(this::createAndShowUI);
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return move;
    }
    private void createAndShowUI() {
        JFrame frame = new JFrame("Player " + symbol + "'s Turn");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 3));
        for (int row = 0; row < board.getLength(); row++) {
            for (int col = 0; col < board.getLength(); col++) {
                JButton button = new JButton(String.valueOf(board.getGrid()[row][col]));
                button.setFont(new Font("Arial", Font.BOLD, 40));

                // --- FIX APPLIED HERE ---
                final int finalRow = row;
                final int finalCol = col;

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        move[0] = finalRow;
                        move[1] = finalCol;
                        frame.dispose();
                        latch.countDown();
                    }
                });
                frame.add(button);
            }
        }
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}