package raycast.animator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import raycast.animator.AbstractAnimator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import raycast.CanvasMap;
import raycast.entity.FpsCounter;
import raycast.entity.geometry.PolyShape;

/**
 *
 * @author Halima Ahmed Lab 3 November 11TH,
 */
public class StaticShapes extends AbstractAnimator {
    
    private static Color BACKGROUND = Color.BISQUE;
    
     protected void handle(GraphicsContext gc, long now) {
       clearAndFill(gc,Color.BISQUE);

       for(PolyShape polyshape: map.shapes()){
           polyshape.getDrawable();
        }
    }
	@Override
	public String toString() {
		 return "Static shapes";
	}
}

