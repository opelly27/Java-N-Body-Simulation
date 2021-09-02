public class Space {

    Body[] bodies;
    double height;
    double width;

    public Space(Body[] bodies, double height, double width){

        this.bodies = bodies;
        this.height = height;
        this.width = width;
    }

    public void calculateForces(){

        //double runningTotal = 0;
        for(int i = 0; i < this.bodies.length; i ++){
            this.bodies[i].calcNetForce(this.bodies);
        }
    }

    public static void main(String args[]){

        Body testBody = new Body(15000, 0, 0, 10, 10);
        Body testBody2 = new Body(27000, 2, 1, 10, 10);
        Body testBody3 = new Body(29530, 4, 2, 10, 10);
        Body testBody4 = new Body(29530000, -4, 4, 10, 10);
        Body[] bodies = new Body[4];
        bodies[0] = testBody;
        bodies[1] = testBody2;
        bodies[2] = testBody3;
        bodies[3] = testBody4;

        Space space = new Space(bodies, 500, 500);
        space.calculateForces();

        System.out.println(space.bodies[0].getNetXforce());
        System.out.println(space.bodies[0].getNetYforce());

        System.out.println(space.bodies[1].getNetXforce());
        System.out.println(space.bodies[1].getNetYforce());

        System.out.println(space.bodies[2].getNetXforce());
        System.out.println(space.bodies[2].getNetYforce());

        System.out.println(space.bodies[3].getNetXforce());
        System.out.println(space.bodies[3].getNetYforce());

    }
}
