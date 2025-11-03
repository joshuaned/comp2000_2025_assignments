## How to compile and run my program ##
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
### Why are data streams useful ###
Data streams are a sequence of data that can be processed with special operations. By utilising data streams, you simplify the code for data manipulation, resulting in more concise and readable code.
With streams, you can filter certain parts of the stream; for example, you can have it only return words that start with a certain letter.
You can accept data as streams and also convert arrays to data streams for easy filtering, mapping, or removing of things.

### How I used data streams in my game ###
Within the Client.java class, which pulls all the weather data from the server, I accept the result from the website as an InputStream.
I then use the stream map function and use a lambda to split the stream at the spaces by character. I can then easily push the pieces into my ClientData objects.
Using a data stream here saved me from writing a longer and more convoluted for loop.
Data streams enable faster code writing and cleaner-looking code.

### Why are lambdas useful ###
Lambdas are essentially functions without a name; they enable you to perform more complex code in fewer lines.
They simplify many things in programming. For example, instead of using a for loop, you can just use forEach for something like an ArrayList.
You can then easily put in commands like a function that will affect the passed value or do something else.
This encourages better programming design, as code can be simplified and made more readable for both other programmers and yourself.

### How I used lambdas in my game ###
Within my game, I utilised lambdas to simplify code in a few places.
Within Client.java, I used it to process my input stream and break it up into individual strings; this approach saved many lines of code.
Also in Client.java under my pushValues function, I used a lambda to create a for loop to push conditions into tiles and plants. Using a lambda here makes it much more readable and reduces the lines of code again.
Since my client constantly requests data from the server as a stream, I decided to open it in a separate thread. I created this separate thread using a lambda, which saved a few lines of code and simplified the code.

Lambdas are super useful in programming for simplifying code and maintaining readability, all important to good programming design

## How server data is used ##
I interpreted the weather data as conditions for determining how fast the plants grow on certain tiles.
Client.java receives the weather data under the request function and pushes it into an object called ClientData.java. This basically just converts the data into numerical values from strings and stores it.
When the data arraylist containing all the ClientData objects reaches the threshold, which is set at 100, pushValues is triggered, which takes the position data from the weather stream and checks if it aligns with one of the cells.
If it does, then it has a 1/3 chance of becoming a tile's current condition, which will affect its growth multiplier via the decorators.

I interpreted the different weather conditions as follows.
Rain means the tile will be wet, which increases growth by 1.
Windy means it's rainy and windy, so windy takes away -0.5, but wet adds 1, resulting in a 0.5 increase.
Windx means thunderstorm -1, which also brings wind and rain.
Then, temp means the cell is the perfect temperature for growth, so it gets a 1.5 increase.

These values are added to the tiles' existing growth rate, which is usually 1; for example, chalk ground has a growth rate of 0.1.
Then, these values are passed into the plant when it is initially planted and multiplied by 1; the result is then added repeatedly to the plant's growth.
A plant will only receive its growth rate when it is planted, so it is best to select a suitable plot.

These weather conditions affect how fast the plant grows on certain plots, both positively and negatively.
When hovering over a cell, you can also see on the right what weather condition it has and what the growth multiplier is for the tile.

Rain = wet
Windy = windy + wet
Windx = Thunder + windy + wet
temp = Perfect

The float value provided is additionally added to the growth rate as an extra bonus, so there may be some perfect cells that are better than others.
This is simply done by taking the string, converting it to a float, and passing it into the decorators as well.
