public class NBody {
    public static double readRadius(String filePath) {
        In in = new In(filePath);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();

        return secondItemInFile;
    }

    public static Planet[] readPlanets(String filePath) {
        In in = new In(filePath);
        int numberOfPlanets = in.readInt();
        double radius = in.readDouble();
        Planet[] planetArray = new Planet[numberOfPlanets];
        int count = 0;
        while (count != numberOfPlanets) {
            /* Each line has the rank of a country, then its
             * name, then its production in metric tons, and
             * finally the fraction of world salt output it produces. */
            double xpos = in.readDouble();
            double ypos = in.readDouble();
            double xvol = in.readDouble();
            double yvol = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planetArray[count] = new Planet(xpos, ypos, xvol, yvol, mass, img);
            count++;
        }
        return planetArray;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planetsArray = readPlanets(filename);

        //Now we are ready to draw pictures
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();

        double time = 0;
        while (time != T){
            //Create net force array
            double[] xForces = new double [planetsArray.length];
            double[] yForces = new double [planetsArray.length];
            //calculate net forces for each planet
            for (int i = 0; i < planetsArray.length; i ++){
                xForces[i] = planetsArray[i].calcNetForceExertedByX(planetsArray);
                yForces[i] = planetsArray[i].calcNetForceExertedByY(planetsArray);
            }
            for (int i = 0; i < planetsArray.length; i ++){
                planetsArray[i].update(dt, xForces[i],yForces[i]);
            }

            //Draw background
            StdDraw.picture(0, 75, "images/starfield.jpg");
            //Draw all planets
            for (int i = 0; i < planetsArray.length; i++){
                planetsArray[i].draw();
            }
            //Show off-screen buffer
            StdDraw.show();
            //Pause
            StdDraw.pause(10);
            //Increase time variable by dt
            time += dt;
        }



    }
}
