import ipcapture.*;

import processing.video.*;

IPCapture cam1;
IPCapture cam2;

int num = 1;
String numString= "";

void setup() {
  size(800, 600);
  frameRate(30);
 
  
  cam1 = new IPCapture(this, "http://frcvision.local:1181/?action=stream", "", ""); //http://roborio-2438-frc.local:1181/?action=stream
  cam2 = new IPCapture(this, "http://frcvision.local:1182/?action=stream", "", ""); //http://roborio-2438-frc.local:1182/?action=stream

  cam1.start();
  cam2.start();
}

void draw() {
  if(cam1.isAvailable()) {
    cam1.read();
    image(cam1, 0, 0, 400, 300);
  }
  if(cam2.isAvailable()) {
   cam2.read();
   image(cam2, 400, 0, 400, 300);
  }
  
  /*stroke(#FF0000);
  strokeWeight(2);
  line(0, 150, 800, 150);
  line(200, 0, 200, 600);
  line(600, 0, 600, 300);
  
  fill(#FF0000);
  textSize(30);
  text(int(frameRate),20,60);*/
}
