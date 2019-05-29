public class Vector 
{
    public Vector(){}
    public Vector(double x, double y)
    {
        this.x = x; 
        this.y = y;
    }
    public String toString()
    {
        return ("(" + x + "," + y + ")");
    }
    public void putX()
    {
        this.x = x;
    }
    public void putY()
    {
        this.y = y;
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public Vector add(Vector b)
    {
        return (new Vector(x+b.x,y+b.y));
    }
    public Vector subtract(Vector b)
    {
        return (new Vector(x-b.x,y-b.y));
    }
    public double magnitude()
    {
        return (Math.sqrt((x*x)+(y*y)));
    }
    public Vector scalarProduct(double scalar)
    {
        return (new Vector(x*scalar,y*scalar));
    }
    public double dotProduct(Vector b)
    {
        double part1 = this.magnitude();
        double part2 = b.magnitude();
        double part3 = Math.cos(this.angle(b));
        return (part1*part2*part3);
    }
    public double angle(Vector b)
    {
        double top = (x*b.x)+(y*b.y);
        double bottom = (this.magnitude()*b.magnitude());
        return (Math.acos(top/bottom));
    }
    
    private double x;
    private double y;
}
