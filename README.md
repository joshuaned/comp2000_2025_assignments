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

## How I used design patterns in my game ##
Decorator
State

## Data streams and Lambdas ##
lambda used in client
lambda used for new thread
lambda for push values in client

## How server data is used ##
Rain = wet
Windy = windy + wet
Windx = Thunder + windy + wet
temp = Perfect
