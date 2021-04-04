
package snake_game;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
public class Droll extends JPanel implements ActionListener{
  
    
    private Image apple;
    private Image dot;
    private Image head;
    
    private final int DOTS_SIZE =10;
    private final int TOTAL_DOTS = 2500;
    
    private final int x[] = new int[TOTAL_DOTS];
    private final int y[] = new int[TOTAL_DOTS];
    
    private int dots;
    
    private Timer timer;
    private final int RANDOM_POSITION = 24;
    
    
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    
    private int apple_x;
    private int apple_y;
    
    private boolean ingame = true;
    Droll()
    { 
        addKeyListener(new mykeyAdapter());
        setBackground(Color.black);
        setPreferredSize(new Dimension(500,500));
        setFocusable(true);
        loadimage();
        intitimage();
        cheakapple();
    }
    
    public void loadimage()
    {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("snake_game/Icon/apple.png"));
        apple = i1.getImage();
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("snake_game/Icon/dot.png"));
        dot = i2.getImage();
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("snake_game/Icon/head.png"));
        head = i3.getImage();
    }
    
    public void intitimage()
    {
        dots = 3;
        for(int z=0;z<dots;z++)
        {
            x[z]=50 - z*DOTS_SIZE;
            y[z]=50;
        }
        appleposition();
        timer = new Timer(140,this);
        timer.start();
    }
    
    public void appleposition()
    {
        int r = (int)(Math.random() * RANDOM_POSITION);
        apple_x = (r*DOTS_SIZE);
        
        r = (int) (Math.random()*RANDOM_POSITION);
        apple_y = (r*DOTS_SIZE);
        
    }
    public void cheakapple()
    {
        if((x[0]==apple_x) && (y[0]==apple_y))
        {
          dots++;
          appleposition();
        }
    }
   
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g)
    {
        
        if(ingame)
          {
            g.drawImage(apple,apple_x, apple_y , this);
          }
        else
        {
            gameover(g);
        }
          for(int z = 0;z < dots;z++)
           {
            if(z==0)
            {
                g.drawImage(head, x[z], y[z], this);
            }else
            {
                g.drawImage(dot, x[z], y[z], this);
            
            } 
        
            
            Toolkit.getDefaultToolkit().sync();
        }
        
       
        
    }
    
    
    public void gameover(Graphics g)
    {
        String msg = "Game Over";
        Font font = new Font("SAN_SARIF", Font.BOLD ,14);
        FontMetrics metrices = getFontMetrics(font);
        
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(msg,300-metrices.stringWidth(msg)/2,300/2);
    }
    
    public void checkCollision()
    {
        for(int z = dots;z>0;z--)
        {
            if((z>4) && (x[0] == x[z]) && (y[0] == y[z]))
            {
                ingame = false;
            }
        }
        
        if(x[0]>=500)
        {
            ingame = false;
        }
        if(y[0]>=500)
        {
            ingame = false;
        }
        if(x[0]<0)
        {
            ingame = false;
            
        }
        if(y[0] < 0)
        {
            ingame = false;
        }
        
        if(!ingame)
        {
            timer.stop();
        }
    }

    public void move()
    {
        for(int z = dots; z >0;z--)
        {
            x[z] = x[z-1];
            y[z] = y[z-1];
        }
        
        if(leftDirection)
        {
            x[0] = x[0]-DOTS_SIZE;
        }
        if(rightDirection)
        {
            x[0] = x[0] + DOTS_SIZE;
        }
         if(upDirection)
        {
            y[0] -= DOTS_SIZE;
        }
        if(downDirection)
        {
            y[0]+= DOTS_SIZE;
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        
        if(ingame){
            cheakapple();
            checkCollision();
            move();
            
        }
        repaint();
    }

    
    
    private class mykeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && (!rightDirection))
            {
              leftDirection = true;
              upDirection = false;
              downDirection = false;
              move();
            }
            if(key == KeyEvent.VK_RIGHT && (!leftDirection))
            {
              rightDirection = true;
              upDirection = false;
              downDirection = false;
              move();
            }
            if(key == KeyEvent.VK_UP && (!downDirection))
            {
              upDirection = true;
              leftDirection = false;
              rightDirection = false;
              move();
            }
            if(key == KeyEvent.VK_DOWN && (!upDirection))
            {
              downDirection = true;
              rightDirection = false;
              leftDirection = false;
              move();
            }
        }
    }
    
}
