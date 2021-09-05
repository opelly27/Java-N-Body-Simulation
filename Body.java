//By Oliver Pelly

public class Body {

    public static final double G = 6.673e-11;
    private double mass;
    private double px;
    private double py;
    private double vx;
    private double vy;
    private double fx;
    private double fy;

    public Body(double mass, double px, double py, double vx, double vy){
        this.px = px;
        this.py = py;
        this.vx = vx;
        this.vy = vy;
        this.mass = mass;
    }


    @Override
    public String toString() {
        return "";
    }

    public double getDistance(Body otherBody){

        double x1 = this.getXpos();
        double x2 = otherBody.getXpos();
        double y1 = this.getYpos();
        double y2 = otherBody.getYpos();


        double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));


        return distance;
    }

    public double getSpeed(){

        return Math.sqrt((this.getXvelo() * this.getXvelo()) + (this.getYvelo() * this.getYvelo()));
    }

    public double getForceScalar(Body otherBody){

        double distance = this.getDistance(otherBody);
        double m1 = this.getMass();
        double m2 = otherBody.getMass();

        double force = G * ((m1 * m2) / distance);

        return force;
    }

    public double getXforce(Body otherBody){

        double distance = otherBody.getXpos() - this.getXpos();
        double m1 = this.getMass();
        double m2 = otherBody.getMass();

        double force = G * ((m1 * m2) / distance);

        return force;


    }

    public double getYforce(Body otherBody){
        
        double distance = otherBody.getYpos() - this.getYpos();
        double m1 = this.getMass();
        double m2 = otherBody.getMass();

        double force = G * ((m1 * m2) / distance);

        return force;
    }

    public void calcNetForce(Body[] bodies){
        double netXForce = 0;
        double netYForce = 0;
        for(int i = 0; i < bodies.length; i ++){
            if(this != bodies[i]){
                netXForce = netXForce + this.getXforce(bodies[i]);
                netYForce = netYForce + this.getYforce(bodies[i]);
            }
        }

        this.fx = netXForce;
        this.fy = netYForce;
    }

    public void updateVelocity(double timeslice){
        // TODO: Implament updateVelocity
        
    }

    public void updatePosition(double timeslice){
        // TODO: Implament updatePosition

    }

    public double getXpos(){
        return this.px;
    }

    public double getYpos(){
        return this.py;
    }

    public double getXvelo(){
        return this.vx;
    }

    public double getYvelo(){
        return this.vy;
    }

    public double getMass(){
        return this.mass;
    }

    public double getNetXforce(){
        return this.fx;
    }

    public double getNetYforce(){
        return this.fy;
    }

    public static void main(String[] args){

        // Body testBody = new Body(15000, 0, 0, 10, 10);
        // Body testBody2 = new Body(27000, 2, 1, 10, 10);
        // Body testBody3 = new Body(29530, 4, 2, 10, 10);
        // Body testBody4 = new Body(29530000, -4, 4, 10, 10);
        // Body[] bodies = new Body[4];
        // bodies[0] = testBody;
        // bodies[1] = testBody2;
        // bodies[2] = testBody3;
        // bodies[3] = testBody4;

        // System.out.println(testBody.getSpeed());
        // System.out.println(testBody.getDistance(testBody2));
        // System.out.println(testBody.getXforce(testBody2));
        // System.out.println(testBody.getYforce(testBody2));

        // testBody.calcNetForce(bodies);
        // System.out.println(testBody.fx);
        // System.out.println(testBody.fy);




    }

}
