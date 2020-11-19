package raycast.animator;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import raycast.CanvasMap;
import raycast.CanvasMapInterface;
import raycast.entity.FpsCounter;
import raycast.entity.geometry.PolyShape;
import utility.Point;

/**
 * this class must extend {@link AnimationTimer}. job of this class is to hold
 * common functionality among animators. Halima Ahmed Lab 3 November 11TH,
 * 2019
 */
public abstract class AbstractAnimator extends AnimationTimer {

    private FpsCounter fps;
    private double strokeWidth;
    private Paint fillColor;
    private Paint strokeColor;
    
    /**
     * create a protected class variable of type {@link CanvasMap} and name it
     * map.s
     */
    protected CanvasMap map;
    //private CanvasMap x;

    /**
     * create a protected class variable of type {@link Point} and name it
     * mouse.
     */
    protected Point mouse;

    /**
     * create a protected constructor and initialize the
     * {@link AbstractAnimator#mouse} variable
     */
    public AbstractAnimator() {
        this.mouse = new Point();
    }

    /**
     * create a setter called setCanvas to inject (set) the {@link CanvasMap}
     *
     * @param map - {@link CanvasMap} object
     */
    public void setCanvas(CanvasMap map) {
        this.map = map;

    }

    /**
     * create a method called mouseDragged that is called every time the
     * position of mouse changes.
     *
     * @param e - {@link MouseEvent} object that hold the details of the mouse.
     * use {@link MouseEvent#getX} and {@link MouseEvent#getY}
     */
    public void mouseDrag(MouseEvent e) {

        this.mouse.set(e.getX(), e.getY());

    }

    /**
     * create a method called mouseMoved that is called every time the position
     * of mouse changes.
     *
     * @param e - {@link MouseEvent} object that hold the details of the mouse.
     * use {@link MouseEvent#getX} and {@link MouseEvent#getY}
     */
    public void mouseMovement(MouseEvent e) {

        this.mouse.set(e.getX(), e.getY());

    }

    public void clearAndFill(GraphicsContext gc, Color background) {
        gc.setFill(background);
        gc.clearRect(0, 0, map.w(), map.h());
        gc.fillRect(0, 0, map.w(), map.h());
    }

    /**
     * <p>
     * create a method called handle that is inherited from
     * {@link AnimationTimer#handle(long)}. this method is called by JavaFX
     * application, it should not be called directly.</p>
     * <p>
     * inside of this method call the abstract handle method
     * {@link AbstractAnimator#handle(GraphicsContext, long)}.
     * {@link GraphicsContext} can be retrieved from {@link CanvasMap#gc()}</p>
     *
     * @param now - current time in nanoseconds, represents the time that this
     * function is called.
     */
    //   this.fps = new CanvasMap() {
    
    
    
  
    
  @Override
    public void handle(long now) {
       
            GraphicsContext gc = map.gc();

           if(map.drawFPS() != null) {
    //fps.calculateFPS(now);
  
            }

            handle(map.gc(),now);
if (map.drawShapeJoints() != null)
        
    {
    for (PolyShape shape : map.shapes()) {
                    if (map.drawBounds() != null) {
                        shape.getBounds().draw(gc);
                    }
                    if (map.drawShapeJoints() != null) {
                        shape.drawCorners(gc);
                    }
                }
                return;
            }

            if(map.drawFPS() != null){
    fps.Drawable(gc);
                return;
            }
        };
    

    /**
     * create a protected abstract method called handle, this method to be
     * overridden by subclasses.
     *
     * @param gc - {@link GraphicsContext} object.
     * @param now - current time in nanoseconds, represents the time that this
     * function is called.
     */
    protected abstract void handle(GraphicsContext gc, long now);

}
