import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

// ffmpeg -framerate 60 -i %00d.png -c:v -vf format=yuv420p libx264 output.mp4 
// ffmpeg -framerate 60 -start_number 1 -i %00d.png -pix_fmt yuv420p out.mp4

// TODO Add circle render method
// TODO Fix random generation of bodies
// TODO Find good timeslice for annimation
// TODO add out of bounds check to setPixel method


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
            if(i ==0){
                canvas.drawSquare(10, (int) this.bodies[i].getXpos(), (int) this.bodies[i].getYpos(), 255, 255, 255, 255);
            }
            else{
                canvas.drawSquare(2, (int) this.bodies[i].getXpos(), (int) this.bodies[i].getYpos(), 255, 255, 255, 255);
            }
            
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

         Body[] bodies = new Body[1000];
         bodies[0] = new Body(2000000, 540, 360, 0, 0);
        // bodies[1] = new Body(2, 500, 400, 0, -.5);
        // bodies[2] = new Body(2, 200, 400, 0, .5);
        // bodies[3] = new Body(2, 200, 500, .5, 0);
        // bodies[4] = new Body(2, 200, 200, -.5, 0);
        // bodies[5] = new Body(2, 150, 150, -.5, .2);
        // bodies[6] = new Body(2, 300, 300, -.5, 0);
        // bodies[7] = new Body(2, 450, 450, -.5, -.3);
        // bodies[8] = new Body(2, 225, 556, -.5, -.5);

        for(int i = 1; i < 1000; i ++){

            bodies[i] = new Body(2, Math.random() * 800, Math.random() * 650, Math.random() - 1, Math.random() -1);

        }

        Space space = new Space(bodies, 1080, 720, 3);
        int counter = 0;
        for(int i = 0; i < 5000; i ++){
            space.updateForces();
            space.updateVelocities();
            space.updatePositions();
            //System.out.println(space);
            //space.draw();
            space.save(counter);
            System.out.println(counter);
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
