import ipcapture.*;
import processing.video.*;

IPCapture cam1;
IPCapture cam2;

int num = 1;
String numString= "";

int camNum = 1;

void setup() {
  size(800, 600);
  frameRate(30);
 
  
  cam1 = new IPCapture(this, "http://frcvision.local:1181/?action=stream", "", ""); //http://roborio-2438-frc.local:1181/?action=stream
  cam2 = new IPCapture(this, "http://frcvision.local:1182/?action=stream", "", ""); //http://roborio-2438-frc.local:1182/?action=stream

  cam1.start();
  //cam2.start();
}


void draw() {
  

  if(camNum == 1){
    if(cam1.isAvailable()) {
     cam1.read();
     image(cam1, 0, 0, 800, 600);
    }
  }
  
  if(camNum == 2){
   if(cam2.isAvailable()) {
    cam2.read();
    image(cam2, 0, 0, 800, 600);
   }
  }
  
  

  
  /*
  
  stroke(#FF0000);
  strokeWeight(2);
  line(0, 150, 800, 150);
  line(200, 0, 200, 600);
  line(600, 0, 600, 300);
  
  fill(#FF0000);
  textSize(30);
  text(int(frameRate),20,60);*/
}

// toggling camera view // 
void keyPressed(){
  
  
    
  if(key == 'd'){

    if(camNum == 1){
      cam1.stop();
      cam2.start();
      camNum = 2;
      print(camNum);
    }else{
      cam2.stop();
      cam1.start();
      camNum = 1;
      print(camNum);
    }
  }
 }
 
