import java.lang.Math;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double con_g = 6.67e-11;

    /*Constructor 1*/
    public Planet(double xP,double yP, double xV,double yV,double m,String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }


    /* Constructor 2*/
    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;

    }

    /* Calculate Distance*/
    public double calcDistance(Planet c){
        double xxPos_c = c.xxPos;
        double yyPos_c = c.yyPos;

        double distance;
        distance = Math.sqrt((xxPos_c-xxPos)*(xxPos_c-xxPos) + (yyPos_c - yyPos)*(yyPos_c - yyPos));

        return distance;
    }

    /* calculate force exerted*/
    public double calcForceExertedBy(Planet d){

        double dist = this.calcDistance(d);
        double force;
        force = con_g*mass*d.mass/(dist*dist);

        return force;

    }

    public double calcForceExertedByX(Planet d){
        double force_x;
        force_x = this.calcForceExertedBy(d) * (d.xxPos - xxPos) / this.calcDistance(d);
        return force_x;
    }

    public double calcForceExertedByY(Planet d){
        double force_y;
        force_y = this.calcForceExertedBy(d) * (d.yyPos - yyPos) / this.calcDistance(d);
        return force_y;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double xNetForce =0;
        for (Planet p: allPlanets){
            if (this.equals(p)) {
                continue;
            }else {
            xNetForce +=  this.calcForceExertedByX(p);
        }}
        return xNetForce;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double yNetForce =0;
        for (Planet p: allPlanets){
            if (this.equals(p)) {
                continue;
            }
            else{
            yNetForce +=  this.calcForceExertedByX(p);
        }}
        return yNetForce;
    }

    public void update(double dt,double fx, double fy){
        double x_acc = fx/mass;
        double y_acc = fy/mass;

        xxVel = xxVel + dt*x_acc;
        yyVel = yyVel +dt*y_acc;

        xxPos = xxPos + dt*xxVel;
        yyPos = yyPos + dt*yyVel;

    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }



}
