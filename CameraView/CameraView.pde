import ipcapture.*;
import processing.video.*;
PImage img;

IPCapture cam1;

int num = 1;
String numString= "";


void setup() {
  size(800, 600);
  frameRate(60);
  
  img = loadImage("VictorFace.jpg");
  img.resize(width,height);
  image(img, 0, 0);
 
  cam1 = new IPCapture(this, "http://frcvision.local:1181/?action=stream", "", ""); //http://roborio-2438-frc.local:1181/?action=strea

  cam1.start();
}

void draw() {
  
    if(cam1.isAvailable()) {
     cam1.read();
     image(cam1, 0, 0, 800, 600);
     stroke(0,255,0);
    }else{
     stroke(255,0,0); 
    }
   
    line((width/2)+50, height/2, (width/2)-50, height/2);
    line(width/2, (height/2)+50, width/2, (height/2)-50);
 
}

// Resets video feed when any key is pressed //
 void keyReleased(){
    cam1.stop();
    cam1.start();
 }
 
