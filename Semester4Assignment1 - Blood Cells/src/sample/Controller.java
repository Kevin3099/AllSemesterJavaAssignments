package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;

import static sample.DisjointedSetNode.find;
import static sample.DisjointedSetNode.union;

public class Controller {

    public MenuBar menuBar;
    public Menu chooseFile, exit;
    public ImageView imageView, imageView2;
    public Slider hue, brightness, saturation, noiseSlider;
    public Button editButton;
    public AnchorPane rectanglePane;
    public Image fileImage;
    public Label totalCells, totalWhiteCells;
    public WritableImage triColour;

    private static int[] pixelArray, purplePixelArray;
    static HashMap<Integer, Integer> countCells = new HashMap<>();
    static HashMap<Integer, Integer> purpleCountCells = new HashMap<>();

    public void Exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    private void filePicker() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            fileImage = new Image(file.toURI().toString(), 300, 240, false, false);
        }
        imageView.setImage(fileImage);
    }

    @FXML
    private void triColour() {
        PixelReader pr = imageView.getImage().getPixelReader();
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        triColour = new WritableImage(width, height);
        PixelWriter pwTri = triColour.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = pr.getColor(x, y);     //Get color at each pixel

                if (color.getRed() * 255 <= 220 && color.getRed() * 255 >= 160) {
                    pwTri.setColor(x, y, new Color(1, 0, 0, 1)); //red
                } else if (color.getRed() * 255 <= 160) {
                    pwTri.setColor(x, y, new Color(0.8, 0, 0.8, 1)); //purple else stays same color (i.e. White)
                } else if (color.getRed() * 255 > 240 && color.getGreen() * 255 > 240 && color.getBlue() * 255 > 240) {
                    pwTri.setColor(x, y, new Color(1, 1, 1, 1));
                }
            }
        }
        imageView2.setImage(triColour);
    }

    public void editImg() {
        imageView.setImage(fileImage);
        imageView2.setImage(triColour);
        WritableImage wi = new WritableImage(imageView.getImage().getPixelReader(), (int) imageView.getImage().getWidth(), (int) imageView.getImage().getHeight());
        PixelReader editImage = imageView.getImage().getPixelReader();
        PixelWriter editImagepw = wi.getPixelWriter();

        for (int x = 0; x < imageView.getImage().getWidth(); x++) {
            for (int y = 0; y < imageView.getImage().getHeight(); y++) {

                Color color = editImage.getColor(x, y);
                editImagepw.setColor(x, y, color);

                double Hue = color.getHue() + hue.valueProperty().doubleValue();
                if (Hue > 100.0) {
                    Hue = Hue - 100;
                } else if (Hue < 0.0) {
                    Hue = Hue + 100.0;
                }

                double Saturation = color.getSaturation() + saturation.valueProperty().doubleValue();
                if (Saturation > 1.0) {
                    Saturation = 1.0;
                } else if (Saturation < 0.0) {
                    Saturation = 0.0;
                }

                double Brightness = color.getBrightness() + brightness.valueProperty().doubleValue();
                if (Brightness > 1.0) {
                    Brightness = 1.0;
                } else if (Brightness < 0.0) {
                    Brightness = 0.0;
                }
                Color newColor = Color.hsb(Hue, Saturation, Brightness, 1);
                editImagepw.setColor(x, y, newColor);
            }
        }
        imageView.setImage(wi);
        triColour();
    }

    @FXML
    private void fillArray() {
        Image triColorImg = imageView2.getImage();
        PixelReader pr = triColorImg.getPixelReader();
        int width = (int) triColorImg.getWidth();
        int height = (int) triColorImg.getHeight();

        pixelArray = new int[height * width];   //Create Array of pixel in an image, then loop through, if white set = -1 return array
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int thisPixel = (y * width) + x;

                if (pr.getColor(x, y).getRed() == 1 && pr.getColor(x, y).getBlue() == 1 && pr.getColor(x, y).getGreen() == 1) { //if is white
                    pixelArray[thisPixel] = -1; //set to -1
                } else {
                    pixelArray[thisPixel] = thisPixel; //Stay as current pixel to rewrite
                }
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int thisPixel = (y * width) + x;

                if (thisPixel < (height * width - 1) && pixelArray[thisPixel] != -1 && pixelArray[thisPixel + 1] != -1 && (thisPixel + 1) % width != 0) {
                    union(pixelArray, thisPixel, thisPixel + 1);
                }
                if (pixelArray[thisPixel] != -1 && (thisPixel + width) < (height * width - 1) && pixelArray[thisPixel + width] != -1) {
                    union(pixelArray, thisPixel, thisPixel + width);
                }
            }
        }

        for (int i = 0; i < pixelArray.length; i++) {
            if (i % width == 0) {
                System.out.println();
            }
            System.out.print(find(pixelArray, i) + " ");
        }
        fillPurpleArray();
    }

    private void fillPurpleArray() {
        Image purpleColorImg = imageView2.getImage();
        PixelReader pr = purpleColorImg.getPixelReader();
        int width = (int) purpleColorImg.getWidth();
        int height = (int) purpleColorImg.getHeight();

        purplePixelArray = new int[height * width];   //Create Array of pixel in an image, then loop through, if white set = -1 return array
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int thisPixel = (y * width) + x;

                if (pr.getColor(x, y).getRed() * 255 <= 160) {
                    purplePixelArray[thisPixel] = thisPixel; //Stay as current pixel to rewrite
                } else {
                    purplePixelArray[thisPixel] = -1; //set to -1
                }
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int thisPixel = (y * width) + x;

                if (thisPixel < (height * width - 1) && purplePixelArray[thisPixel] != -1 && purplePixelArray[thisPixel + 1] != -1 && (thisPixel + 1) % width != 0) {
                    union(purplePixelArray, thisPixel, thisPixel + 1);
                }
                if (purplePixelArray[thisPixel] != -1 && (thisPixel + width) < (height * width - 1) && purplePixelArray[thisPixel + width] != -1) {
                    union(purplePixelArray, thisPixel, thisPixel + width);
                }
            }
        }
//        for (int i = 0; i < purplePixelArray.length; i++) {
//            if (i % width == 0) {
//                System.out.println();
//            }
//            System.out.print(find(purplePixelArray, i) + " ");
//        }
    }

    @FXML
    public void pixelsPerCell() {
        countCells.clear();
        purpleCountCells.clear();
        for (int i = 0; i < pixelArray.length; i++) {
            int r1 = find(pixelArray, i);
            int r2 = find(purplePixelArray, i);

            if (countCells.containsKey(r1) && pixelArray[i] != -111) {
                countCells.put(r1, countCells.get(r1) + 1);
            } else {
                countCells.put(r1, 1);
                countCells.remove(-111);
            }
            if (purpleCountCells.containsKey(r2) && purplePixelArray[i] != -111) {
                purpleCountCells.put(r2, purpleCountCells.get(r2) + 1);
            } else {
                purpleCountCells.put(r2, 1);
                purpleCountCells.remove(-111);
            }
        }
        System.out.println("Full Array of pixels!!!!!!!!!!");
        for (HashMap.Entry entry : countCells.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue());
        }
        noiseSlider.setMax(Collections.max(countCells.values()));
    }

    public void noiseReduction(ActionEvent actionEvent) {
        WritableImage reduction = new WritableImage(imageView2.getImage().getPixelReader(), (int) imageView2.getImage().getWidth(), (int) imageView2.getImage().getHeight());
        PixelWriter pixelWrite = reduction.getPixelWriter();
        int width = (int) reduction.getWidth();
        int height = (int) reduction.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int thisPixel = y * width + x;


                if (pixelArray[thisPixel] != -1) {
                    int r1 = find(pixelArray, thisPixel); //Root values

                    if (countCells.containsKey(r1) && countCells.get(r1) <= noiseSlider.getValue()) {
                        pixelWrite.setColor(x, y, Color.WHITE);
                   //     System.out.println(countCells.get(r1));
                    }
                }
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int thisPixel = y * width + x;
                if (pixelArray[thisPixel] != -1) {
                    int r1 = find(pixelArray, thisPixel); //Root values
                    int r2 = find(purplePixelArray, thisPixel);
                    if (countCells.containsKey(r1) && countCells.get(r1) <= noiseSlider.getValue()) {
                     countCells.remove(r1);
                    }
                    if (purpleCountCells.containsKey(r2) && purpleCountCells.get(r2) < noiseSlider.getValue()) {
                        purpleCountCells.remove(r2);
                    }
                }
            }
        }
        imageView2.setImage(reduction);
    }

    public void boxes() {
        rectanglePane.getChildren().clear();
        int width = (int) imageView2.getImage().getWidth();
        int number = 0;
        for (HashMap.Entry mapLoop : countCells.entrySet()) {
            int cell = (int) mapLoop.getKey();
            System.out.println(cell);

            boolean heightBoolean = false;
            int lastPixel = 0;
            int x = cell % width;
            int w = 0;
            int h = 0;
            double y = 0;
            number++;
            for (int currentPixel = 0; currentPixel < pixelArray.length; currentPixel++) {
                if (pixelArray[currentPixel] != -1 && find(pixelArray, currentPixel) == cell && currentPixel % width != 0) {

                    if ((currentPixel % width) < x) {
                        x = (currentPixel % width);
                    }       //Furthest left pixel

                    if (!heightBoolean) {
                        y = ((double) currentPixel / width); //First and highest pixel
                        if (y > 0) {
                            y -= 1;     //Go up 1 more to make rectangle look nicer
                        }
                        heightBoolean = true;
                    }
                    if ((currentPixel % width) - x > w) {
                        w = (currentPixel % width) - x;
                    }
                    lastPixel = currentPixel;
                } else if (currentPixel % width == 0 && lastPixel > currentPixel - width) {
                    h++;
                }
            }
            Rectangle rectangle = new Rectangle(x, y, w, h); //divide by 2.5
            Color invisible = new Color(1, 1, 1, 0);
            rectangle.setFill(invisible);
            rectangle.setStroke(Color.GREEN);
            rectanglePane.getChildren().add(rectangle);

            Text numbering = new Text();
            numbering.setText(String.valueOf(number));
            numbering.setX(x + w - 10);
            numbering.setY(y + h);
            rectanglePane.getChildren().add(numbering);
        }
    }

    public void purpleBoxes() {
        rectanglePane.getChildren().clear();
        int width = (int) imageView2.getImage().getWidth();
        int number = 0;
        for (HashMap.Entry mapLoop : purpleCountCells.entrySet()) {
            int cell = (int) mapLoop.getKey();
            System.out.println(cell);

            boolean heightBoolean = false;
            int lastPixel = 0;
            int x = cell % width;
            int w = 0;
            int h = 0;
            double y = 0;
            number++;
            for (int currentPixel = 0; currentPixel < purplePixelArray.length; currentPixel++) {
                if (purplePixelArray[currentPixel] != -1 && find(purplePixelArray, currentPixel) == cell && currentPixel % width != 0) {

                    if ((currentPixel % width) < x) {
                        x = (currentPixel % width);
                    }       //Furthest left pixel

                    if (!heightBoolean) {
                        y = ((double) currentPixel / width); //First and highest pixel
                        if (y > 0) {
                            y -= 1;     //Go up 1 more to make rectangle look nicer
                        }
                        heightBoolean = true;
                    }
                    if ((currentPixel % width) - x > w) {
                        w = (currentPixel % width) - x;
                    }
                    lastPixel = currentPixel;
                } else if (currentPixel % width == 0 && lastPixel > currentPixel - width) {
                    h++;
                }
            }
            Rectangle rectangle = new Rectangle(x, y, w, h); //divide by 2.5
            Color invisible = new Color(1, 1, 1, 0);
            rectangle.setFill(invisible);
            rectangle.setStroke(Color.BLUE);
            rectanglePane.getChildren().add(rectangle);

            Text numbering = new Text();
            numbering.setText(String.valueOf(number));
            numbering.setX(x + w - 10);
            numbering.setY(y + h);
            rectanglePane.getChildren().add(numbering);
        }
    }

    public void Counting() {
        int allCells = 0;
        int allWhiteCells = 0;
        for (HashMap.Entry entry : countCells.entrySet()) {
            allCells++;
        }
        for (HashMap.Entry entry : purpleCountCells.entrySet()) {
            allWhiteCells++;
        }
        totalCells.setText(String.valueOf(allCells));
        totalWhiteCells.setText(String.valueOf(allWhiteCells));
    }
}
