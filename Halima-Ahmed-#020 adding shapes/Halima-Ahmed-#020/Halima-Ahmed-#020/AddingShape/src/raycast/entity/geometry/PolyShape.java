package raycast.entity.geometry;

import static java.lang.System.gc;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import raycast.entity.Entity;
import raycast.entity.property.Sprite;
import raycast.entity.FpsCounter;

/**
 *
 * @author Halima Ahmed Lab 3 November 11TH
 */
public abstract class PolyShape implements Entity {

    private int pointCount;
    private double[][] points;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private RectangleBounds recBounds;
    private Sprite sprites;

    public PolyShape() {
        this.sprites = new Sprite() {        
                public void Drawable(GraphicsContext gc) {  
                gc.setFill(Color.CYAN);
                gc.setStroke(Color.GREEN);
                gc.setLineWidth(sprites.getWidth());

                if (sprites.getStroke() != null) {

                } else {
                    gc.setStroke(sprites.getStroke());
                    gc.strokePolygon(points[0], points[1], pointCount);

                }
                if (sprites.getFill() != null) {
                    gc.setFill(sprites.getFill());
                    gc.fillPolygon(points[0], points[1], pointCount);
                }
                }
				public void draw(GraphicsContext gc) {} 
        };
    }

    /**
     * setPoints can take many points. this method uses "..." which is called
     * vararg. tread it the same as array inside of a function. nums is a one
     * dimensional array, every 2 index is considered one point. it is formatted
     * as x1,y1,x2,y2,x3,y3... . this method will initialize rest of variables
     * in PolyShape object. min and max variables are used in this method to
     * determine the 4 corners of PolyShape so bounds can be initialized. read
     * the sequence diagram.*
     */
    public PolyShape setPoints(double... nums) {

        minX = nums[0];

        maxX = nums[0];
        minY = nums[1];
        maxY = nums[1];

        pointCount = nums.length/2;
        points = new double[2][pointCount];

        for (int i = 0, j = 0; i < nums.length; j++, i += 2) {
            // for(int i = 0, j = 0;numsIndex < nums.length && pointsIndex < points[0].length; j++, i += 2){

            updateMinMax(nums[i], nums[i + 1]);

            points[0][j] = nums[i];
            points[1][j] = nums[i + 1];
        }

        this.recBounds = new RectangleBounds(minX, minY, maxX - minX, maxY - minY);
        return this;

    }

    /**
     * updateMinMax, this is an internal helper method to update min and max
     * values based on the x and y input. inside check if minX and maxX needs to
     * be updated based on new x, then do the same for minY and maxY based on y.*
     */
    public void updateMinMax(double x, double y) {
        if (minX > x) {
            minX =x;
        } else if (maxX < x) {
           maxX =x;
        }

        if (minY > y) {
           minY =y;
        } else if (maxY < y) {
           maxY=y;
        }
    }

    public int pointCount() {
        return pointCount;
    }

    public double[][] points() {
        return points;
    }

    public double pX(int index) {
        return points[0][index];
    }

    public double pY(int index) {
        return points[1][index];
    }

    @Override
    public Sprite getDrawable() {
        return sprites;
    }

    /**
     * drawCorners is going to draw little circles on the corners of the shape
     * plus a little number set a new fill on gc, if you like to use a different
     * color start a for loop which should be smaller than pointCount call
     * fillText on gc and pass to it Integer.toString( i), points[0][i] - 5 and
     * points[1][i] - 5. this will make a little number counter near the corner
     * call fillOval on gc and pass to it points[0][i] - 5, points[1][i] - 5, 10
     * and 10*
     */
    public void drawCorners(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        for (int i = 0; i < pointCount; i++) {
            gc.fillText(Integer.toString(i), points[0][i] - 5, points[1][i] - 5);
            gc.fillOval(points[0][i] - 5, points[1][i] - 5, 10, 10);
        }
    }

    public RectangleBounds getBounds() {
        return recBounds;
    }

}
