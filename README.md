# Amoebrick API
Amoebrick is an API with the purpose to convert Images to an adjustable Brick-Mosaic. It is able to display the needed parts for the build and generate a preview image as well as a cost and size estimate. This is all done by utilizing a slightly modified version of the [Rebrickable Catalog Database](https://rebrickable.com/downloads/) as csv files.
The bricks used to build the mosaic all have the same part number (3005) and size (1x1) but differend element-ids. All bricks needed to build the image can be found on sites like [Bricklink](https://www.bricklink.com/v2/main.page).
## How to use The API?
Amoebrick has not been published to Maven-Central jet, so you will need to manually download the .jar from this page and save it in your project-folder. After that you will need to add this dependency to your pom.xml: 
```
<dependency>
    <groupId>me.amoebemc</groupId>
    <artifactId>Amoebrick</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/path/to/the/amoebrick.jar</systemPath>
</dependency>
```
After that you can just refresh your Maven and everything will be setup. You can download the documentation from this page to get an overview of all classes and methods. 
## Example: Converting An Album Cover to a Brick-Mosaic
coming soon
