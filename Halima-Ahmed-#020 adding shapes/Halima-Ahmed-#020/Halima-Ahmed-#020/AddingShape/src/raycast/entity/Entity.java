package raycast.entity;

import javafx.scene.canvas.GraphicsContext;
import raycast.entity.property.Drawable;

/**
 *
 * @author Halima Ahmed Lab 3 November 11TH
 */
public interface Entity{
   
public Drawable<?> getDrawable();
public void Drawable(GraphicsContext gc);

}
