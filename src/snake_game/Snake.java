
package snake_game;

import java.awt.*;
import javax.swing.*;


public class Snake extends JFrame {
  
    Snake()
{
        Droll d = new Droll();
        add(d);
        pack();
        setLocationRelativeTo(null);
        setTitle("Snake Game (Devloped by: Om Prakash Joshi)");
        setResizable(false);
} 
    public static void main(String[]args)
    {
        new snake_game.Snake().setVisible(true);
    }
}
