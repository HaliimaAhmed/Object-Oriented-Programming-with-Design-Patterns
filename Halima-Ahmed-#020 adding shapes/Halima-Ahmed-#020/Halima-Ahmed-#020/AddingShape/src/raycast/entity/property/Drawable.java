/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycast.entity.property;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 *
 * @author Halima Ahmed Lab 3 November 11TH
 */
 
public interface Drawable <T> {

    public T setFill(Paint paint);
    public T setStroke(Paint paint);
    public T setWidth(double width);
    
    /**
     *
     * @param gc
     */
    public void draw(GraphicsContext gc);

    /**
     *
     * @return
     */
    public Paint getFill();

    /**
     *
     * @return
     */
    public Paint getStroke();

    /**
     *
     * @return
     */
    public double getWidth();
    
            
}
