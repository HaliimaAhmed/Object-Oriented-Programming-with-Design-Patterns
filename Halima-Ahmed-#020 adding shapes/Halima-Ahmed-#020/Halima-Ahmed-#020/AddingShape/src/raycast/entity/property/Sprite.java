
package raycast.entity.property;

import javafx.scene.paint.Paint;


/**
 *
 * @author Halima Ahmed Lab 3 November 11TH
 */
public abstract class Sprite implements Drawable{
    
    private double strokeWidth;
    private Paint fillColor;
    private Paint strokeColor;
    
     
    @Override
    public Sprite setFill(Paint color){
        this.fillColor = color;
        return this;
    }
    @Override
     public Sprite setStroke(Paint color) {
        this.strokeColor = color;
        return this;
    }
    @Override
    public Sprite setWidth(double width){
        this.strokeWidth = width;
        return this;
    }
    public double getWidth(){
    return strokeWidth;
    }

    @Override
    public Paint getFill(){
         return fillColor;
    }
    public Paint getStroke(){
        return strokeColor;
    }

}
