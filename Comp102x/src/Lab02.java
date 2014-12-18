import comp102x.IO;

public class Lab02
{
    public static void main(String[] a){
        Lab02.solveQuadraticEquation();
    }
    
    public static void multiply()
    {
        // Please write your code after this line
        IO.output("Enter an integer, x: ");
        int x = IO.inputInteger();
        
        IO.output("Enter an integer, y: ");
        int y = IO.inputInteger();
        
        IO.output("Answer = " + x*y);
        
        
    }
    
    public static void calculateTriangleArea()
    {
        // Please write your code after this line
        IO.output("Enter the width of the triangle: ");
        double width = IO.inputDouble();
        
        IO.output("Enter the height of the triangle: ");
        double height = IO.inputDouble();
        
        double area = width * height/2.0 ;
        
        IO.output("The triangle area = " + area);
        
        
    }
    
    public static void solveQuadraticEquation()
    {
        // Please write your code after this line
        IO.output("Enter a: ");
        double a = IO.inputDouble();
        
        IO.output("Enter b: ");
        double b = IO.inputDouble();
        
        IO.output("Enter c: ");
        double c = IO.inputDouble();
        
        double x1,x2;
        x1 = (-b + Math.sqrt(b*b - 4*a*c))/(2.0*a) ;
        x2 = (-b - Math.sqrt(b*b - 4*a*c))/(2.0*a) ;
        
        IO.outputln("First solution for x = " + x1);
        IO.outputln("Second solution for x = " + x2);
    }
}
