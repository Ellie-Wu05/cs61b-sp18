import java.sql.Array;

public class NBody {


    public static double readRadius(String fileName){
        In file = new In(fileName);
        int num = file.readInt();
        double radius = file.readDouble();

        return radius;
    }

    public static Planet[] readPlanets(String fileName){
        In file = new In(fileName);
        int num = file.readInt();
        double radius = file.readDouble();
        Planet [] five = new Planet[num];

        for (int i = 0; i < num; i++) {
            double xxPos = file.readDouble();
            double yyPos = file.readDouble();
            double xxVel = file.readDouble();
            double yyVel = file.readDouble();
            double mass = file.readDouble();
            String imgFile = file.readString();

            Planet p = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFile);
            five[i] =p;
        }
        return five;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] total_planets  = readPlanets(filename);

        StdDraw.setScale(-radius,radius);
        String file_bg = "images/starfield.jpg" ;
        StdDraw.picture(0,0,file_bg);
        StdDraw.enableDoubleBuffering();

        StdDraw.show();
        StdDraw.pause(2000);

        for (Planet p:total_planets) {
            p.draw();
        }

        double t = 0;

        while (t<T){
            double[] xForces = new double [total_planets.length];
            double[] yForced = new double [total_planets.length];

            for (int i = 0; i < total_planets.length; i++) {
                xForces[i] = total_planets[i].calcNetForceExertedByX(total_planets);
                yForced[i] = total_planets[i].calcNetForceExertedByY(total_planets);
                total_planets[i].update(dt,xForces[i],yForced[i]);
            }

            StdDraw.picture(0,0,file_bg);
            for(Planet p: total_planets){
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t +=dt;

        }

        StdOut.printf("%d\n",total_planets.length);
        StdOut.printf("%2e\n",radius);
        for (int i = 0; i < total_planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    total_planets[i].xxPos,total_planets[i].yyPos,total_planets[i].xxVel,
                    total_planets[i].yyVel,total_planets[i].mass,total_planets[i].imgFileName);
        }

    }

}
