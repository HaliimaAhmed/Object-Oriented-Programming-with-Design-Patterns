package raycast.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import raycast.entity.property.Drawable;
import raycast.entity.property.Sprite;
import utility.Point;

/**
 *
 * @author Halima Ahmed Lab 3 November 11TH
 */

    public class FpsCounter implements Entity{
    
    public static double ONE_SECOND = 1000000000L;
    public static double HALF_SECOND = ONE_SECOND / 2F;
    
    public Font fpsFont;
    private String fpsDisplay;
    private int frameCount;
    private double lastTime;
    private Point pos;
    private Sprite sprite;
    private double strokeWidth;
    private Paint fillColor;
    private Paint strokeColor;
    double x, y;
    
    public FpsCounter(double x, double y){ 
    	setPos(x,y);
        setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BLACK, 24));
       
    }


    public void calculateFPS(long now){
         if(now - lastTime > HALF_SECOND){
            fpsDisplay=Integer.toString(frameCount*2);
            frameCount=0;
            lastTime = now;
        }
        frameCount++;
    }
    
    public FpsCounter setPos(double x, double y){
 this.x=x;	
		this.y= y;
		return this;

    }
/**
get font from gc and save it in a temp variable so we can set it back later
set fpsFont as font on gc
call setFill on gc and pass getFill()
call fillText on gc and pass fpsDisplay, plus x and y from pos
call setStroke on gc and pass getStroke()
call setLineWidth on gc and pass getWidth()
call strokeText on gc and pass fpsDisplay, plus x and y from pos
call setFont on gc and pass to it the temp font you created
   **/      
public void Drawable(GraphicsContext gc) {
        Font f = gc.getFont();
        gc.setFont(fpsFont);
        gc.setFill(fillColor);
        gc.fillText(fpsDisplay, x, y);
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);
        gc.strokeText(fpsDisplay, x, y);
        gc.setFont(f);

    }

  
    public double getWidth(){
    return strokeWidth;
    }

    public Paint getFill(){
         return fillColor;
    }
    public Paint getStroke(){
        return strokeColor;
    }
    
    public FpsCounter setDrawable(Sprite sprites) {
        this.sprite = sprites;
        return this;
    }
   
    public Sprite getDrawable(){
        return sprite;
    }    
   

    private FpsCounter setFont(Font font) {
         this.fpsFont = font;
        return this;
        
    }

}
