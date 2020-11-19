package raycast;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import raycast.animator.AbstractAnimator;
import raycast.animator.TextAnimator;
import raycast.entity.geometry.PolyShape;

/**
 * this class represents the drawing area. it is backed by {@link Canvas} class.
 * this class itself does not handle any of the drawing. this task is
 * accomplished by the {@link AnimationTimer}.
 *
 * Halima Ahmed Lab 2 Due:October 14TH, 2019
 */
public class CanvasMap {

    private Canvas board;
    private AbstractAnimator animator;
    private IntegerProperty rayCount;
    private BooleanProperty drawLightSource;
    private BooleanProperty drawIntersectPoint;
    private BooleanProperty drawShapeJoints;
    private BooleanProperty drawSectors;
    private BooleanProperty drawBounds;
    public BooleanProperty drawFPS;
    private List<PolyShape> shapes;

    /**
     * <p>
     * create a {@link Canvas} object call board. it provides the tools to draw
     * in JavaFX. this is also a {@link Node} which means can be added to our
     * JavaFX application. the object needed to draw on a {@link Canvas} is
     * {@link GraphicsContext} which is retrieved using
     * {@link Canvas#getGraphicsContext2D()} method.
     * </p>
     */
    /**
     * create a {@link AbstractAnimator} called animator. {@link AnimationTimer}
     * provides most common functionally needed to draw animations of ray
     * casting.
     */
    /**
     * <p>
     * create an {@link IntegerProperty} called rayCount to keep track of ray
     * count changes.<br>
     * this variable can be initialized with {@link SimpleIntegerProperty}
     * </p>
     *
     * <pre>
     * IntegerProperty i1 = new SimpleIntegerProperty( 1);
     * IntegerProperty i2 = new SimpleIntegerProperty();
     * i1.bind( i2);
     * i2.set( 100);
     * System.out.println( i1.get()); // prints 100
     * </pre>
     * <p>
     * create a getter that returns {@link IntegerProperty} and a method that
     * returns {@link IntegerProperty#get()}
     * </p>
     */
    /**
     * <p>
     * create a set of {@link BooleanProperty}s to track some drawing
     * options.<br>
     * create: drawLightSource, drawIntersectPoint, drawShapeJoints,
     * drawSectors, drawBounds, drawFPS<br>
     * these variables can be initialized with {@link SimpleBooleanProperty}
     * </p>
     *
     * <pre>
     * BooleanProperty b1 = new SimpleBooleanProperty( false);
     * BooleanProperty b2 = new SimpleBooleanProperty();
     * b1.bind( b2);
     * b2.set( true);
     * System.out.println( b1.get()); // prints true
     * </pre>
     * <p>
     * create a getter that returns {@link BooleanProperty} and a method that
     * returns {@link BooleanProperty#get()} for each BooleanProperty.
     * </p>
     */
    /**
     * create a constructor and initialize all class variables.
     */
    public CanvasMap() {

        this.board = new Canvas();
        this.animator = new TextAnimator();
        this.rayCount = new SimpleIntegerProperty();
        this.drawLightSource = new SimpleBooleanProperty();
        this.drawIntersectPoint = new SimpleBooleanProperty();
        this.drawShapeJoints = new SimpleBooleanProperty();
        this.drawSectors = new SimpleBooleanProperty();
        this.drawBounds = new SimpleBooleanProperty();
        this.drawFPS = new SimpleBooleanProperty();
        this.shapes = new ArrayList(20);

    }

    /**
     * create the property class variables functions here
     *
     * @return
     */
    public IntegerProperty rayCount() {
        return rayCount;
    }

    public BooleanProperty drawLightSource() {
        return drawLightSource;
    }

    public BooleanProperty drawIntersectPoint() {
        return drawIntersectPoint;
    }

    public BooleanProperty drawShapeJoints() {
        return drawShapeJoints;
    }

    public BooleanProperty drawSectors() {
        return drawSectors;
    }

    public BooleanProperty drawBounds() {
        return drawBounds;
    }

    public BooleanProperty drawFPS() {
        return drawFPS;
    }

    /**
     * create a method called setAnimator. set an {@link AbstractAnimator}. if
     * an animator exists {@link CanvasMap#stop()} it and call
     * {@link CanvasMap#removeMouseEvents()}. then set the new animator and call
     * {@link CanvasMap#start()} and {@link CanvasMap#registerMouseEvents()}.
     *
     * @param newAnimator - new {@link AbstractAnimator} object
     * @return the current instance of this object
     */
    public CanvasMap setAnimator(AbstractAnimator newAnimator) {

        if (this.animator != null) {
            this.removeMouseEvents();
            this.stop();
        }
        this.animator = newAnimator;
        this.start();
        this.registerMouseEvents();

        return this;
    }

    /**
     * <p>
     * create a method called registerMouseEvents. register the mouse events for
     * when the mouse is {@link MouseEvent#MOUSE_MOVED} or
     * {@link MouseEvent#MOUSE_DRAGGED}.<br>
     * call {@link CanvasMap#addEventHandler} twice and pass to it
     * {@link MouseEvent#MOUSE_DRAGGED}, {@link animator#mouseDragged} and
     * {@link MouseEvent#MOUSE_MOVED}, {@link animator#mouseMoved}.</p>
     * <p>
     * a method can be passed directly as an argument if the method signature
     * matches the functional interface. in this example you will pass the
     * animator method using object::method syntax.</p>
     */
    public void registerMouseEvents() {

        addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent e) -> animator.mouseMovement(e));
        addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent e) -> animator.mouseDrag(e));

    }

    /**
     * <p>
     * create a method called removeMouseEvents. remove the mouse events for
     * when the mouse is {@link MouseEvent#MOUSE_MOVED} or
     * {@link MouseEvent#MOUSE_DRAGGED}.<br>
     * call {@link CanvasMap#removeEventHandler} twice and pass to it
     * {@link MouseEvent#MOUSE_DRAGGED}, {@link animator#mouseDragged} and
     * {@link MouseEvent#MOUSE_MOVED}, {@link animator#mouseMoved}.</p>
     * <p>
     * a method can be passed directly as an argument if the method signature
     * matches the functional interface. in this example you will pass the
     * animator method using object::method syntax.</p>
     */
    public void removeMouseEvents() {

        addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent e) -> animator.mouseMovement(e));
        addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent e) -> animator.mouseDrag(e));

    }

    /**
     * <p>
     * register the given {@link EventType} and {@link EventHandler}
     * </p>
     *
     * @param <E>
     * @param event - an event such as {@link MouseEvent#MOUSE_MOVED}.
     * @param handler - a lambda to be used when registered event is triggered.
     */
    public < E extends Event> void addEventHandler(EventType< E> event, EventHandler< E> handler) {
        board.addEventHandler(event, handler);
    }

    /**
     * <p>
     * remove the given {@link EventType} registered with {@link EventHandler}
     * </p>
     *
     * @param <E>
     * @param event - an event such as {@link MouseEvent#MOUSE_MOVED}.
     * @param handler - a lambda to be used when registered event is triggered.
     */
    public < E extends Event> void removeEventHandler(EventType< E> event, EventHandler< E> handler) {
        board.removeEventHandler(event, handler);
    }

    /**
     * create a method called start. start the animator.
     * {@link AnimationTimer#start()}
     */
    public void start() {
        animator.start();
    }

    /**
     * create a method called stop. stop the animator.
     * {@link AnimationTimer#stop()}
     */
    public void stop() {
        animator.stop();
    }

    /**
     * create a method called getCanvas. get the JavaFX {@link Canvas} node
     *
     * @return {@link Canvas} node
     */
    public Canvas getCanvas() {
        return board;

    }

    /**
     * create a method called gc. get the {@link GraphicsContext} of
     * {@link Canvas} that allows direct drawing.
     *
     * @return {@link GraphicsContext} of {@link Canvas}
     *
     * create a {@link Canvas} object call board. it provides the tools to draw
     * in JavaFX. this is also a {@link Node} which means can be added to our
     * JavaFX application. the object needed to draw on a {@link Canvas} is
     * {@link GraphicsContext} which is retrieved using
     * {@link Canvas#getGraphicsContext2D()} method.
     *
     */
    public GraphicsContext gc() {
        return board.getGraphicsContext2D();
    }

    /**
     * create a method called h. get the height of the map,
     * {@link Canvas#getHeight()}
     *
     * @return height of canvas
     */
    public double h() {
        return board.getHeight();

    }

    /**
     * create a method called w. get the width of the map,
     * {@link Canvas#getWidth()}
     *
     * @return width of canvas
     */
    public double w() {
        return board.getWidth();
    }

    public List<PolyShape> shapes() {
        return shapes;

    }

    public void addSampleShapes() {
        /**
         * addSampleShapes in this method create bunch of sample shapes as you
         * like. at least 3. make sure to customize your fill, width and stroke,
         * to customize you must call on getDrawable.
         **/
        PolyShape sample1;
        sample1 = new PolyShape() {
        	
			public void Drawable(GraphicsContext gc) {}};
        double nums1[] = {25,50,100, 150, 200, 250, 300, 350};
        sample1.setPoints(nums1);
        shapes.add(sample1);
        sample1.Drawable(gc());;
  
        PolyShape sample2 = new PolyShape() {

			public void Drawable(GraphicsContext gc) {}};
        double nums2[] = {20, 30, 40, 50, 60, 70};
        sample2.setPoints(nums2);
        shapes.add(sample2);
        sample2.Drawable(gc());
        
        PolyShape sample3;
        sample3 = new PolyShape() {

			public void Drawable(GraphicsContext gc) {}};
        sample3.setPoints(400, 450, 500, 550, 600, 650);
        shapes.add(sample3);
        sample3.Drawable(gc());
    }

}
