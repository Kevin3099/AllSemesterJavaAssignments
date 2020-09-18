/*
Name : Kevin Power
 Student Number: 20084163
 Programme Name: Rgb Car
 
 Brief description of the animation achieved:
 A car that changes color's as it moves accross the plane containing 
 scenery in the background.
 
 Known bugs/problems:
 I had an issue with the scenery method, I attempted to set the variable's 
 for the tree's as global one but this would not work. 
 There are no known bugs in the programme now.
 
 Any sources referred to during the development of the assignment:
 Processing.org for help making the car change colour over time.
 */

int wheel = 0;
int colourRed = 50;
int colourGreen = 50;
int colourBlue =50;          //Global integers for color and the wheel 
void setup() {
  size(800, 600);
  noStroke();              //setting up window
}

void draw() {
  if (mousePressed==true) {    //If mouse pressed set to night.
    background(0);
    fill(211);
    ellipse(700, 200, 100, 100); //draw the moon
  } else if (mousePressed==false) { //If moused not pressed set to day.
    background(135, 206, 250);  //blue sky
    fill(#FFFF00);
    ellipse(700, 200, 100, 100);  //draw the sun
  }

  scenery(1, 50, 65);         //Putting in scenery
  drawCar();
  carColor();
}

void scenery(int tree, int treeTrunk, int treeLeaf) {     //method for scenery

  for (tree =1; tree<=8; tree++) {

    for (tree=1; tree<=8; tree++) {      //Loop for 8 tree's
      fill(139, 69, 19);
      rect(treeTrunk, 432, 30, 70);          //Loop for treeTrunk
      treeTrunk = treeTrunk +100;
    }

    for (tree=1; tree<=8; tree++) {
      fill(0, 128, 0);
      ellipse(treeLeaf, 425, 60, 60);        //Loop for treeLeaves
      treeLeaf = treeLeaf +100;
    }
    fill(34, 139, 34);
    rect(0, 502, 900, 600);     //draw the grass/floor
  }
}

void drawCar() {
  fill(colourRed, colourGreen, colourBlue);

  ellipse(wheel, 500, 30, 30);       //Car back wheel
  ellipse(wheel+50, 500, 30, 30);     //Car front wheel
  rect(wheel-10, 460, 60, 30);            //Car Body Design 
  rect(wheel+50, 470, 15, 15);    

  wheel += 1;//makes car move
}

void carColor() {
  //r, g, b for the car
  if (colourRed <= 200 && colourRed >= 20) {
    colourRed ++;
  } else if (colourGreen >=10 && colourGreen <= 255) {
    colourRed = 200;
    colourGreen ++;
  } else if (colourBlue >=15 && colourBlue <= 255) {
    colourGreen = 255;
    colourBlue ++;
  }
}
