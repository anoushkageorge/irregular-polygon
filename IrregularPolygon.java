import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList

import gpdraw.*; // 

public class IrregularPolygon {
    private ArrayList<Point2D.Double> vertices = new ArrayList<>();

    // constructor
    public IrregularPolygon() {}

    // point to the polygon
    public void add(Point2D.Double vertex) {
        vertices.add(vertex);
    }

    // peri of the polygon
    public double perimeter() {
        if (vertices.size() < 2) return 0.0;
        double totalPerimeter = 0.0;
        for (int i = 0; i < vertices.size(); i++) {
            Point2D.Double current = vertices.get(i);
            Point2D.Double next = vertices.get((i + 1) % vertices.size()); // wrap first point
            totalPerimeter += current.distance(next);
        }
        return totalPerimeter;
    }

    // area using shoe theorem
    public double area() {
        if (vertices.size() < 3) return 0.0; //3 pts
        double sum1 = 0.0, sum2 = 0.0;
        for (int i = 0; i < vertices.size(); i++) {
            Point2D.Double current = vertices.get(i);
            Point2D.Double next = vertices.get((i + 1) % vertices.size()); // wrap to first point
            sum1 += current.x * next.y;
            sum2 += current.y * next.x;
        }
        return Math.abs(sum1 - sum2) / 2.0;
    }

    // make poly
    public void draw() {
        try {
            if (vertices.size() < 2) return; //2 points to draw
            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            pen.up();
            pen.move(vertices.get(0).x, vertices.get(0).y);
            pen.down();
            for (int i = 1; i < vertices.size(); i++) {
                pen.move(vertices.get(i).x, vertices.get(i).y);
            }
            pen.move(vertices.get(0).x, vertices.get(0).y); // Close polygon
        } catch (java.awt.HeadlessException e) {
            System.out.println("exception: no graphics available.");
        }
    }
}