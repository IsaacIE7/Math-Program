public class Vector {
    double xComp;
    double yComp;
    double mag;
    double direc;
    String pos;
    String origin = "(0, 0)";


    public Vector(){//default vector at origin with no magnitude
        this.xComp = 0;
        this.yComp = 0;
        this.pos = origin;
    }

    public Vector(String pos, double xComp, double yComp){//rectangular form
        this.xComp = xComp;
        this.yComp = yComp;
        this.mag = Math.sqrt(xComp * xComp + yComp * yComp);
        this.direc = Math.atan2(yComp, xComp);
        this.pos = pos;
    }

    public Vector(String pos, double mag, double direc, boolean polar){//polar form
        this.xComp = mag * Math.cos(direc);
        this.yComp = mag * Math.sin(direc);
        this.pos = pos;

    }

    public void scalarMultiply(double value){//multiplies both components by a scalar value
        this.xComp *= value;
        this.yComp *= value;
    }

    public void changeVal(double xAmnt, double yAmnt){//changes the vector's components by given amounts
       this.xComp += xAmnt;
       this.yComp += yAmnt; 
    }

    public void addVector(Vector vec){//adds another vector to this vector
        this.xComp += vec.xComp;
        this.yComp += vec.yComp;
    }

    public void subtractVector(Vector vec){//subtracts another vector from this vector
        this.xComp -= vec.xComp;
        this.yComp -= vec.yComp;
    }

    public void dotProduct(Vector vec){//calculates the dot product of this vector and another vector
        double dotProd = (this.xComp * vec.xComp) + (this.yComp * vec.yComp);
    }

    public String toString(){
        return "Magnitude: " + mag + 
        "\nDirection(with respect to x): " + Math.toDegrees(direc) + 
        "\nx & y components: (" + xComp + ", " + yComp + ")\nPosition: " + pos;
    }
}
