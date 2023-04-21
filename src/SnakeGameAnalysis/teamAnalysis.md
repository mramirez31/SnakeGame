# Group members' names:
Miguel Ramirez, Tri Le, Olufunmilayo Ekpereh, Nazmun Nahar
# SimpleSnakeGame


## Initialization and main method:
The initialization and main method initializes the snake game.
In the initialization stage the game is not running yet.
The initialization initializes the screen size and the unit size per pixel unit.
It also initializes the delay at 100.
Another is that it initializes the apple initializes by x and Y.
It also initializes the snake's body part with an initial of 3.
It initializes the direction of the snake by making it go in the right direction at the
begging.
It also initializes the running to be false to tell the program if the game is not running
and whether if it is running or not to set it to not running.
It also initializes the timer for the game.

The main method creates the jFrame and gamePanel for the snake game.
It also adds the gamePanel to the Jframe so that it appears in the main window of the GUI.
It also creates a closing statement option where if the game ends or is exited the window
will close.
Another is that it will set the Title of the game.
It will als0 set the screen to a specific size and make it where the screen can not be
resized at all.
It wil also resize the window so that it fit its contents from the program which in this
main method class is the gamePanel object.
It will also set it to where the window will be in the center of the screen.
It will also set it to where the window will be visible on the screen.


## Class variables:
The class variables are SIZE, UNIT_SiZE, GAME_UNITS, and DELAY.
## SimpleSnakeGame constructor:

The constructor creates a jpanel by using the code size to set the size of the panel
and will set the background as black from the setBackgroundColor statement. The
addKeyListener statement will allow the program to listen to the key press
allowing it to run making changes whenever a key is pressed to move the snake's body.
It will also run the game and start the program.

## startGame method:
The startGame method initializes the game, and runs the time, creates an apple, and runs
the game.

## newApple method:
The newApple method generates a new apple for the snake to eat and spawns one in a random location.

## paintComponent method:
The paintComponent method will draw the snake and apple from the draw method.
## draw method:
If the game is running the game will draw the body of the snake (dark green) and
the head (green) in rectangle like shapes. It will also draw the red apple in the
shape of an oval. Another is after the snakes eats the apple it will draw another apple
again in a random location as well as a new body part for the snake making it longer. Another is when the snake
dies by hitting itself or the walls it will draw a Game over screen in (RED)
when the game is over.

## move method:
The move method starts at the end of the snake's body and moves each unit one
position forward until it reaches the head of the snake and by doing this it ensures
that the snake's body moves to the next position unit that was in from of it. Another is
the direction the way the snakes moves witch is called the switch program and in this code it
determines which way the snakes move when you press an arrow key like if it moves up
The switch statement updates the position of the snake's head by the direction it was going
and this makes it where the y coordinates update the head and decrease it by the size of each
unit making it look like the snake is moving but actually it is the switch statement
that does the change in direction making it move up by one unit each time.

## checkApple method:
The checkApple method is an if statement where it will check if the snake eats the apple or
not. If the snake eats the apple then the snake's body parts will increase by 1 each time it
eats an apple and will also keep count of the number of apples eaten each time one is eaten
and will keep increasing. Another is that it will also create a new apple after it does eat
the apple.

## checkCollisions method:
The checkCollisions method is a method that checks if it hits a wall or its own body making
it end the game. The way checkCollisions method does this is by using an if statement where, if
the snakes hit anything other that what it's supposed to like the apple then the game would
stop the program all together and will stop running and will also stop the timer and go to the
game over method.

## gameOver method:
The gameOver method creates the game over screen with a font Bold and red color
with a size of 30.

## actionPerformed method:
The actionPerformed method is a method that is called whenever something occurs
like moving, bumping into a wall, or eating the apple for example. It is an if statement
where, if the game is running it checks each if each method is working like the move,
checkApple, and checkCollision method and updates them each time something is done and
repaints each drawing object. Another is the it ensures that the game is running smoothly
and consistently on different systems and platforms as well as running on a synchronized loop
with a system clock abd a constant frame rate.

## MyKeyAdapter class:
The MyKeyAdapter class allows for the program to know how to move the in different directions
and where to go whenever a key is pressed by using an if statement for each direction a snake
moves like up, left, right, and down.