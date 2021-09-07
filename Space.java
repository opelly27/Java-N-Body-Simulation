import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class Space {

    Body[] bodies;                  // List of body objects that make up the simulation
    double height;                  // The height of the spacial region being simulated
    double width;                   // The width of the spacial region being simulated
    double timeslice;               // The time in sections between each tick of the simulation

    public Space(Body[] bodies, int height, int width, double timeslice){

        this.bodies = bodies;
        this.height = height;
        this.width = width;
        this.timeslice = timeslice;
    }

    public void updateForces(){

        //double runningTotal = 0;
        for(int i = 0; i < this.bodies.length; i ++){
            this.bodies[i].calcNetForce(this.bodies);
        }
    }

    public void updateVelocities(){
        for(int i = 0; i < this.bodies.length; i ++){
            this.bodies[i].updateVelocity(this.timeslice);
        }
    }

    public void updatePositions(){
        for(int i = 0; i < this.bodies.length; i ++){
            this.bodies[i].updatePosition(this.timeslice);
        }
    }

    public void draw(){

        Canvas canvas = new Canvas( (int) this.height, (int) this.width, 255,255,255,255);
        for(int i = 0; i < this.bodies.length; i ++){
            // switch(i){
            //     case 0: canvas.drawSquare(5, (int) this.bodies[i].getXpos(), (int) this.bodies[i].getYpos(), 0, 0, 0, 255);
            //     case 1: canvas.drawSquare(5, (int) this.bodies[i].getXpos(), (int) this.bodies[i].getYpos(), 255, 0, 0, 255);
            //     case 2: canvas.drawSquare(5, (int) this.bodies[i].getXpos(), (int) this.bodies[i].getYpos(), 0, 0, 255, 255);
            //     case 3: canvas.drawSquare(5, (int) this.bodies[i].getXpos(), (int) this.bodies[i].getYpos(), 0, 255, 0, 255);
            // }
            canvas.drawSquare(5, (int) this.bodies[i].getXpos(), (int) this.bodies[i].getYpos(), 0, 0, 0, 255);
            
        }
        canvas.display();
    }

    public void save(int frameNumber){

        Canvas canvas = new Canvas( (int) this.height, (int) this.width, 255,255,255,255);
        for(int i = 0; i < this.bodies.length; i ++){
            canvas.drawSquare(5, (int) this.bodies[i].getXpos(), (int) this.bodies[i].getYpos(), 255, 255, 255, 255);
        }

        File f = null;
        try{
            String frameNumberStr = String.valueOf(frameNumber);
            frameNumberStr = frameNumberStr + ".png";
            f = new File("frames/" + frameNumberStr);
            ImageIO.write(canvas.image, "png", f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }

    public static void main(String args[]){

        Body testBody = new Body(2000000, 375, 375, 0, 0);
        Body testBody2 = new Body(2, 500, 400, 0, -.5);
        Body testBody3 = new Body(2, 200, 400, 0, .5);
        Body testBody4 = new Body(2, 200, 500, .5, 0);
        Body testBody5 = new Body(2, 200, 200, -.5, 0);
        Body[] bodies = new Body[5];
        bodies[0] = testBody;
        bodies[1] = testBody2;
        bodies[2] = testBody3;
        bodies[3] = testBody4;
        bodies[4] = testBody5;

        Space space = new Space(bodies, 750, 750, .5);
        int counter = 0;
        while(true){
            space.updateForces();
            space.updateVelocities();
            space.updatePositions();
            //System.out.println(space);
            //space.draw();
            space.save(counter);
            counter ++;
        }
        
    }

    @Override
    public String toString() {
        String returnString = String.format("Width: " + this.width + " Height: " + this.height + "\n\n\n");
        for(int i = 0; i < this.bodies.length; i ++){
            int b = i +1;
            returnString += String.format("Body #" + b + "\n\n");
            returnString += String.format("Position: X = " + this.bodies[i].getXpos() + " ");
            returnString += String.format("Position: Y = " + this.bodies[i].getYpos() + "\n");
            returnString += String.format("Velosity: X = " + this.bodies[i].getXvelo() + " ");
            returnString += String.format("Velosity: Y = " + this.bodies[i].getYvelo() + "\n\n");

        }

        return returnString;

    }
}




// public BufferedImage generateImage(){

    //     BufferedImage img = new BufferedImage((int)this.height, (int)this.width, BufferedImage.TYPE_INT_ARGB);

    //     for(int y = 0; y < this.height; y ++){
    //         for (int x = 0; x < this.width; x ++){
    //             for (int body = 0; body < this.bodies.length; body ++){
    //                 if((int)this.bodies[body].getXpos() == x){
    //                     if((int) this.bodies[body].getYpos() == y){
    //                         int a = 255;
    //                         int r = 0;
    //                         int g = 0;
    //                         int b = 0;

    //                         int p = (a<<24) | (r<<16) | (g<<8) | b;

    //                         img.setRGB(x, y, p);
    //                     }
    //                 }
    //             }
    //         }
    //     }

    //     return img;
    // }
