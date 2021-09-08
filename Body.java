//By Oliver Pelly

public class Body {

    public static final double G = 6.673e-6;
    private double mass;
    private double px;
    private double py;
    private double vx;
    private double vy;
    private double fx;
    private double fy;
    private int a;
    private int r;
    private int g;
    private int b;

    public Body(double mass, double px, double py, double vx, double vy){
        this.px = px;
        this.py = py;
        this.vx = vx;
        this.vy = vy;
        this.mass = mass;

        this.a = 255;
        this.r = (int) (Math.random() * 255);
        this.g = (int) (Math.random() * 255);
        this.b = (int) (Math.random() * 255);

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

        double force = G * ((m1 * m2) / (distance * distance));

        return force;
    }

    public double getAngle(Body otherBody){

        double delta_x = otherBody.getXpos() - this.getXpos();
        double delta_y = otherBody.getYpos() - this.getYpos();
        double theta_radians = Math.atan2(delta_y, delta_x);

        return theta_radians;
    }

    public double getXforce(Body otherBody){

        double scalarForce = this.getForceScalar(otherBody);
        double angleDegrees = this.getAngle(otherBody);
        double returnDouble = scalarForce * Math.cos(angleDegrees);
        return returnDouble;

    }

    public double getYforce(Body otherBody){

        double scalarForce = this.getForceScalar(otherBody);
        double angleDegrees = this.getAngle(otherBody);
        double returnDouble = scalarForce * Math.sin(angleDegrees);
        return returnDouble;
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

        this.vx = this.vx + ((this.fx / this.mass) * timeslice);
        this.vy = this.vy + ((this.fy / this.mass) * timeslice);
    }

    public void updatePosition(double timeslice){

        this.px = this.px + (this.vx * timeslice);
        this.py = this.py + (this.vy * timeslice);

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
    public int getA(){
        return this.a;
    }
    public int getR(){
        return this.r;
    }
    public int getG(){
        return this.g;
    }
    public int getB(){
        return this.b;
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
