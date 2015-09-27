package javaapplication8;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
    //Positions on X and Y for the ball, player 1 and player 2

	//Margins for player 1 & player 2
	private int player1XMargin = 5;
	private int player2XMargin = 20;
        private int player3XMargin = player1XMargin + 35;
        private int player4XMargin = player2XMargin + 35;

	//Size of naves
	private int naveWidth = 5;
	private int naveHeight = 50;

	private int ballX, ballY, player1X, player1Y, player2X, player2Y, player3X, player3Y, player4X, player4Y;
	private Thread mainThread;
	private int toTheRight=5; // to the right
	private int toTheLeft= -5; //to the left
	private int upward=-5; // upward
	private int downward= 5; // down
	private int ballWidth, ballHeight; // Width and height of the ball
	// Scores
	private int Team1Score =0, Team2Score = 0, maxScore=10;
	private boolean player1UpFlag,player1DownFlag, player2UpFlag, player2DownFlag, player3UpFlag, player3DownFlag, player4UpFlag, player4DownFlag;
	private boolean gameGoingOn, gameOver;

	private String Team1Name , Team2Name;

	public GamePanel(String Team1Name, String Team2Name){
		this.Team1Name = Team1Name;
		this.Team2Name = Team2Name;
                

		gameGoingOn=true;

		/*Initialize positions*/
		player1X = player1XMargin;
		player2X = Main.windowWidth - player2XMargin;
                player3X = player3XMargin ;
                player4X = Main.windowWidth - player4XMargin;

		player1Y = player2Y = (Main.windowHeight/4) - (naveHeight);
                player3Y = player4Y = (Main.windowHeight) - (Main.windowHeight/4);


		ballX = Main.windowWidth / 2;
		ballY = Main.windowHeight / 2;

		/*Start the game Thread*/
		mainThread=new Thread(this);
		mainThread.start();
	}

	// Draw ball and ships
	public void paintComponent(Graphics gc){
		setOpaque(false);    // leih false?
		super.paintComponent(gc);

		// Draw ball
		gc.setColor(Color.blue);
		gc.fillOval(ballX, ballY, 8,8);

		// Draw ships
		gc.setColor(Color.black);
		gc.fillRect(player1X, player1Y, naveWidth, naveHeight);
		gc.fillRect(player3X, player3Y, naveWidth, naveHeight);
                gc.setColor(Color.red);
                gc.fillRect(player2X, player2Y, naveWidth, naveHeight);
                gc.fillRect(player4X, player4Y, naveWidth, naveHeight);

		//Draw scores
		gc.setColor(Color.black);

		gc.drawString(Team1Name+": "+Team1Score, 5, 10);
		gc.drawString(Team2Name+": "+Team2Score, 300, 10);
                

		if(gameOver)
			gc.drawString("Game Over", 100, 125);   
	}

	// Positions on X and Y for the ball
	public void moveBall (int nx, int ny)
	{
		ballX= nx;
		ballY= ny;
		this.ballWidth=this.getWidth();
		this.ballHeight=this.getHeight();
		repaint();
	}

    // Here we receive from the game container class the key pressed
	public void keyPressed(KeyEvent evt)
    {
        switch(evt.getKeyCode())
        {
            // Move ship 1
            case KeyEvent.VK_W :
            	player1UpFlag = true;
            	break;
            case KeyEvent.VK_S :
            	player1DownFlag = true;
            	break;

            // Move ship 2
            case KeyEvent.VK_UP:
            	player2UpFlag=true;
            	break;
           case KeyEvent.VK_DOWN:
        	   player2DownFlag=true;
            	break;

            case KeyEvent.VK_T :
                player3UpFlag = true;
                break;
            case KeyEvent.VK_G :
                player3DownFlag = true;
                break;


            case KeyEvent.VK_I:
                player4UpFlag = true;
                break;
            case KeyEvent.VK_K :
                player4DownFlag = true;
                break;
        }
    }

    //	Here we receive from the game container class the key released
    public void keyReleased(KeyEvent evt)
    {
        switch(evt.getKeyCode())
        {
	        // Mover Nave1
	        case KeyEvent.VK_W :
	        	player1UpFlag = false;
	        	break;
	        case KeyEvent.VK_S :
	        	player1DownFlag = false;
	        	break;

	        // Mover nave 2
	        case KeyEvent.VK_UP:
	        	player2UpFlag=false;
	        	break;
	        case KeyEvent.VK_DOWN:
	    	   player2DownFlag=false;
	        	break;
                        
                        
            case KeyEvent.VK_T :
                player3UpFlag = false;
                break;
            case KeyEvent.VK_G :
                player3DownFlag = false;
                break;
                
                
            case KeyEvent.VK_I:
                player4UpFlag = false;
                break;
            case KeyEvent.VK_K :
                player4DownFlag = false;
                break;



        }
    }

    // Move player 1
    public void moverPlayer1()
    {
        if (player1UpFlag == true && player1Y >= 0)
            player1Y += upward;
        if (player1DownFlag == true && player1Y <= (this.getHeight()-naveHeight))
            player1Y += downward;
        drawPlayer1(player1X, player1Y);
    }

    // Move player 2
    public void moverPlayer2()
    {
        if (player2UpFlag == true && player2Y >= 0)
            player2Y += upward;
        if (player2DownFlag == true && player2Y <= (this.getHeight()-naveHeight))
            player2Y += downward;
        drawPlayer2(player2X, player2Y);
    }

     public void moverPlayer3()
    {
        if (player3UpFlag == true && player3Y >= 0)
            player3Y += upward;
        if (player3DownFlag == true && player3Y <= (this.getHeight()-naveHeight))
            player3Y += downward;
        drawPlayer3(player3X, player3Y);
    }


    public void moverPlayer4()
    {
        if (player4UpFlag == true && player4Y >= 0)
            player4Y += upward;
        if (player4DownFlag == true && player4Y <= (this.getHeight()-naveHeight))
            player4Y += downward;
        drawPlayer4(player4X, player4Y);
    }

    // Position on Y for the player 1
    public void drawPlayer1(int x, int y){
    	this.player1X=x;
    	this.player1Y=y;
    	repaint();
    }
    // Position on Y for the player 2
    public void drawPlayer2(int x, int y){
    	this.player2X=x;
    	this.player2Y=y;
    	repaint();
    }

    public void drawPlayer3(int x, int y){
    	this.player3X=x;
    	this.player3Y=y;
    	repaint();
    }


    public void drawPlayer4(int x, int y){
    	this.player4X=x;
    	this.player4Y=y;
    	repaint();
    }

	public void run() {
		boolean leftRight=false;
		boolean upDown=false;

		while(true){

			if(gameGoingOn){

            // The ball move from left to right
           	if (leftRight)
			{
				//move to the right?
				ballX += toTheRight;
				if (ballX >= (ballWidth - 8))
                    leftRight= false;
			}
			else
			{
				//move to the left
				ballX += toTheLeft;
				if ( ballX <= 0)
                    leftRight =  true;
			}


            // The ball moves from up to down
           	if (upDown)
			{
				//move down
				ballY += downward;
				if (ballY >= (ballHeight - 8))
                    upDown= false;

			}
			else
			{
				//move up
				ballY += upward;
				if ( ballY <= 0)
					upDown =  true;
			}

           	moveBall(ballX, ballY);

            // Delay
			try
			{
				Thread.sleep(50);
			}
			catch(InterruptedException ex)
			{

			}

			// Move player 1
			moverPlayer1();

            // Move player 2
			moverPlayer2();

                        moverPlayer3();

                        moverPlayer4();

            // The score of the player 1 increase
			if (ballX >= (getWidth() - 8))
				Team1Score++;

            // The score of the player 2 increase
			if ( ballX == 0)
				Team2Score++;

			// Game over. Here you can change 6 to any value
                        // When the score reach to the value, the game will end
			if(Team1Score==maxScore || Team2Score==maxScore){
				gameGoingOn=false;
			    gameOver=true;
			}

			// The ball stroke with the player 1
			if((ballX==player1X+naveWidth && ballY>=player1Y && ballY<=(player1Y+naveHeight))|| (ballX==player3X+naveWidth && ballY>=player3Y && ballY<=(player3Y+naveHeight)) )
				leftRight=true;

            // The ball stroke with the player 2
			if((ballX==(player2X-naveWidth) && ballY>=player2Y && ballY<=(player2Y+naveHeight))|| (ballX==(player4X-naveWidth) && ballY>=player4Y && ballY<=(player4Y+naveHeight)) )
				leftRight=false;
			}
		}
	}

}
