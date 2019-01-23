import ipcapture.*;

import processing.video.*;

IPCapture cam1;
IPCapture cam2;

void setup() {
  size(1600, 600);
  
  cam1 = new IPCapture(this, "http://roborio-2438-frc.local:1181/?action=stream", "", "");
  cam2 = new IPCapture(this, "http://roborio-2438-frc.local:1182/?action=stream", "", "");

  cam1.start();
  cam2.start();
}

void draw() {
  if(cam1.isAvailable()) {
    cam1.read();
    image(cam1, 0, 0, 800, 600);
  }
  if(cam2.isAvailable()) {
   cam2.read();
   image(cam2, 800, 0, 800, 600);
  }
  
  stroke(#FF0000);
  strokeWeight(2);
  line(0, 300, 1600, 300);
  line(400, 0, 400, 600);
  line(1200, 0, 1200, 600);
}
