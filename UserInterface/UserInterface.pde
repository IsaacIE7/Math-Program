String input = "";
String result = "";
PFont font;
char[] nums = {'0','1','2','3','4','5','6','7','8','9'};

void setup(){
  pixelDensity(2);
  size(400, 400);
  font = createFont("Arial", 16);
  textFont(font);
  textAlign(CENTER);
}

void draw(){
  background(90,90,220);
  
  // Input box
  fill(255);
  rect(50, 100, 300, 40);
  fill(0);
  textSize(16);
  text(input, width/2, 125);
  
  // Result box
  fill(255);
  rect(50, 160, 300, 40);
  fill(0);
  text(result, width/2, 185);
  
  // Buttons
  drawButton(100, 130, 50, 220, 120, 40, "Derivative");
  drawButton(100, 130, 230, 220, 120, 40, "Evaluate");
  drawButton(100, 130, 320, 10, 70, 30, "Clear");
  
  // Labels
  textSize(20);
  text("Math Tool", width/2, 50);
  textSize(14);
  text("Input Expression", width/2, 90);
  text("Result", width/2, 155);
}

void keyPressed() {
  if (key == BACKSPACE) {
    if (input.length() > 0) {
      input = input.substring(0, input.length() - 1);
    }
  } else if (key == '+' || key == '-' || key == '*' || key == '^' || key >= '1' && key <= '9' || key == '0'  || key == 'x' || key == '(' || key == ')' || key == ' ' ) {
    input += key;
  }
}

void mousePressed() {
  if (buttonPressed(50, 170, 220, 260)) { // Derivative

    result = "Derivative computation not implemented";
  }
  if (buttonPressed(230, 350, 220, 260)) { // Evaluate
    result = "Evaluation computation not implemented";
  }
  if (buttonPressed(320, 390, 10, 40)) { // Clear
    input = "";
    result = "";
  }
}

void drawButton(float baseColor, float hoverColor, float x, float y, float w, float h, String label){
  if (mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h){
    fill(hoverColor);
  } else {
    fill(baseColor);
  }
  rect(x, y, w, h);
  fill(255);
  textSize(12);
  text(label, x + w/2, y + h/2 + 4);
}

boolean buttonPressed(double x, double x2, double y, double y2){
  if (mouseX > x && mouseX < x2 && mouseY > y && mouseY < y2 && mousePressed){
    return true;
  }
  return false;
}