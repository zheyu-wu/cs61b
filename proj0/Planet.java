public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static final double constant = 6.67e-11;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet p){
		return Math.sqrt((this.xxPos - p.xxPos)*(this.xxPos - p.xxPos) + (this.yyPos - p.yyPos)*(this.yyPos - p.yyPos));
	}
	public double calcForceExertedBy(Planet p){
		double distance = this.calcDistance(p);
		return constant * this.mass * p.mass/ (distance * distance);
	}
	public double calcForceExertedByX(Planet p){
		double totalForce = this.calcForceExertedBy(p);
		return totalForce * (p.xxPos - this.xxPos)/ this.calcDistance(p);
	}
	public double calcForceExertedByY(Planet p){
		double totalForce = this.calcForceExertedBy(p);
		return totalForce * (p.yyPos - this.yyPos)/ this.calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double totalNetForce = 0;
		for (int i = 0; i < allPlanets.length; i++){
			if (!this.equals(allPlanets[i])){
				totalNetForce = totalNetForce + calcForceExertedByX(allPlanets[i]);
			}
		}
		return totalNetForce;
	}
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double totalNetForce = 0;
		for (int i = 0; i < allPlanets.length; i++){
			if (!this.equals(allPlanets[i])){
				totalNetForce = totalNetForce + calcForceExertedByY(allPlanets[i]);
			}
		}
		return totalNetForce;
	}
	public void update(double dt, double fX, double fY){
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		xxVel = xxVel + dt * aX;
		yyVel = yyVel + dt * aY;
		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;
	}
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
	}

}