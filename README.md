# Amoebrick API
Amoebrick is an API with the purpose to convert Images to an adjustable Brick-Mosaic. It is able to display the needed parts for the build and generate a preview image as well as a cost and size estimation. This is all done by utilizing a slightly modified version of the [Rebrickable Catalog Database](https://rebrickable.com/downloads/) as csv files to match colors with elements.
The bricks used to build the mosaic all have the same part number (3005) and size (1x1), but differend element-ids. All bricks needed to build the image can be found on sites like [Bricklink](https://www.bricklink.com/v2/main.page).
## How To Use The API?
Amoebrick has not been published to Maven Central yet, so you will need to add this maven repository to your pom.xml file:
```
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
and this dependency:
```
<dependency>
  <groupId>com.github.damoebe</groupId>
  <artifactId>Amoebrick</artifactId>
  <version>v1.0</version>
</dependency>
```
After that you can just refresh your Maven and everything should be setup. You can download the documentation from this page to get an overview of all classes and methods. 
## Example: Converting An Album Cover To A Brick-Mosaic
In the following example, we will convert an album-cover-image into a BrickImage-Object and save the preview image to a .png file on the device. After that we will print all attribute related stats of the object to the console. 
> [!TIP]
> You can scale up the Integer param in `.optimizeVariations(number)` and it will help you to get rid of unnecessary Brick-Element variations, to make the purchase of the elements easier.
```
String homePath = System.getProperty("user.home");

// setting source file and target width/height of BrickImage
BrickImage brickImage = new BrickImage(48, 48, new ImageIcon(homePath
        + "\\Downloads\\Stonetemplepilotscore.png").getImage());
// (optional) removing brick variations from BrickImage
brickImage.optimizeVariations(10);

// define outputFile
File outputFile = new File(homePath + "/Downloads/stp_core_brickimage.png");

try {
    // write file with the image (10 pixels per brick)
    BufferedImage image = brickImage.getImage(10);
    outputFile.createNewFile();
    ImageIO.write(image, "png", outputFile);
} catch (IOException e) {
    throw new RuntimeException(e);
}

// print attribute stats
System.out.println(brickImage.getElementsSorted());
System.out.println("Part Variations:" + brickImage.getElementsSorted().size());
System.out.println(brickImage.getStats());
```
**Original** Stone Temple Pilots - Core Album Cover (great music btw):\
![Original image (Stone Temple Pilots - Core Album Cover)](/src/main/java/me/damoebe/example/Stonetemplepilotscore.png)\
**Generated** BrickImage Preview (Output file of example):\
![Generated Image .png](/src/main/java/me/damoebe/example/stp_core_brickimage.png)\

**Console Output:**
```
{4166055=26, 300521=109, 300526=1077, 4497006=73, 300525=28, 4165447=144, 300509=39, 6022035=23, 300528=15, 6567217=141, 4211242=57, 4209383=71, 6584821=83, 6089841=130, 6264956=13, 6514209=28, 4569767=70, 4569624=53, 6514211=52, 6427904=72}
Part Variations:20
Size: 38.400000000000006cm x 38.400000000000006cm
Bricks: 2304
Price~ 34.56$
```
Please note that all informations above are estimations of Amoebrick. Reality (especially when it comes to the price) can be very differend from what the generated stats say. Everything, that AmoebrickAPI generates comes with absolutely **no guarantee**. 
> [!IMPORTANT]
> If you find any bugs, run into errors or generally have questions using the API, feel free to open up an issue on this repo :)
