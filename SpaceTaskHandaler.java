public class SpaceTaskHandaler implements Runnable {
    Space state;
    public SpaceTaskHandaler(Space state){
        state = this.state;
    }
    public static void main(String[] args){
        
    }

    @Override
    public void run() {
        for(int i = 0; i < this.state.bodies.length; i ++){
            this.state.bodies[i].calcNetForce(this.state.bodies);
        }
        
    }
}
