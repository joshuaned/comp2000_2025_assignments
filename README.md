## how to compile and run my program ##
This Java program was created using Java 21.
To compile my program, I recommend using Visual Studio Code, but other IDEs should also work.
The program must be run from Main.java, and all Java files should be kept together in the same folder.

## Game outline & Design info ##
I chose to continue my game from assignment 1 and add the required rubric for assignment 2.
By hovering over tiles, you can see various information about them. Their type is under money on the right, followed by the current hovered cell coordinate.
Below this is the new feature, you can see the condition obtained from weather data and the growth rate.
In this game, you can click one of the buttons to change the mode in the top right, then click in a cell to plant or collect.
You should start by planting a carrot. When you plant it, its growth time will be affected by the cells' GrowthX.

After it is fully grown, you can collect it by clicking the "Collect" button and selecting the cell containing the grown plant. This will increase your money to buy more plants!

## Design patterns used ##
### How design patterns contribute to good design ###
Design patterns are proven solutions to common problems. When a programmer designs software, such as this game, they typically encounter various logical problems that need to be solved.
By understanding and applying different design patterns, a programmer can quickly design a solution to a problem, utilising the structure provided by the pattern. Design patterns are super flexible, meaning they can be incorporated in any way you like.
Design patterns also enable other programmers to better understand someone else's code, as they follow a generalised structure, which improves maintainability.
Additionally, design patterns provide a framework of consistency within a programmer's code, generally improving code quality and reducing redundant code.

### How I used design patterns in my game ###
#### Decorator design pattern ####
I utilised the decorator design pattern in creating my condition system for my tiles. Condition.java, Wet.java, Thunder.java, and Perfect.java.
The 'conditions' all extend Condition.java, and use the abstract class growthCalc. I used the decorator pattern's structure to simplify returning a multiplier float.
The idea is that for my decorators, some of them will always be something else too. This means, for example, that when something is windy, it's also wet, and when it is thundering, it's also windy and therefore also wet.
The decorator design pattern allowed me to avoid excess code and an object explosion (meaning having tons of objects that just return the multiplier).
I can simply call one of the decorators and get back a multiplier to pass to my plant for growth time.
Below is my decorator structure, which includes the multiplier implications.
wet (1f)
windy(-0.5f) -> wet (1f)
thunder(-1f) -> windy(-0.5) -> wet(1f)
perfect(1.5f)

This design pattern is a good fit for my program, as it allows me to easily add more decorators, such as a "Hail" condition, which could be linked to wet and have its multiplier adjusted accordingly.

#### State design pattern ####
I utilised the state design pattern to redesign my button and control system, allowing for the easier addition of more buttons and clearer state changes.
In Controls.java, I first create my states in an ArrayList of buttons. My buttons act as the states in this project, as they contain all the controlling/action code, including the creation of new plants.
I then have the current state stored in the variable 'state'.

I delegated my button painting to the state controller 'Controls.java' as this made the cleanest code for its purpose. I can easily call controls.paint in stage.
There are currently three states in my program. Collect, Plant Carrot, and Plant Water Cabbage. CollectButton.java, CarrotButton.java, WaterCabbageButton.java.
These states switch depending on which button has last been clicked; the current state is displayed in-game on the top right.
When Controls.java is created as an object in Stage.java, the default state, being collect, is loaded.

Mouse click position data is passed from the main to the stage and then to the controls, where it changes the states depending on what is clicked.
The state design pattern massively improved the solution I had before, where I was using many "If" statements and the code was very messy. I have used the state design pattern to create a structure for my Controls.java class, allowing me to easily create it within my stage class.
Through the state design pattern, it is now super intuitive to add new buttons to my game, they can just be added to the states arraylist, this makes my code more maintainable.

## Data streams and Lambdas ##
lambda used in client
lambda used for new thread
lambda for push values in client

## How server data is used ##
Rain = wet
Windy = windy + wet
Windx = Thunder + windy + wet
temp = Perfect
