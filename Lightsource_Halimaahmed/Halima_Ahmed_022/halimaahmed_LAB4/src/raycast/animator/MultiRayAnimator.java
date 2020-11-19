package raycast.animator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import raycast.entity.geometry.PolyShape;
import utility.Point;
import utility.IntersectUtil;;


/**
 * @author Halima Ahmed
 * @Version December 1st,2019 lab4 + BONUS 
 */

public class MultiRayAnimator extends AbstractAnimator {

	double[] intersectP = new double[4];
	double[] intersectR = new double[4];

	public void drawRays(GraphicsContext gc, double startX, double startY, Color lightColor) {

		double rayInC;
		double endY;
		double endX;

		rayInC = 360.0 / map.getRayCount();

		for (int rayA = 0; rayA < 360; rayA++) {

			endY = Math.sin(rayInC);
			endX = Math.cos(rayInC);
			endX = Math.cos(Math.toRadians(rayA));
			endY = Math.sin(Math.toRadians(rayA));

			for (PolyShape s : map.shapes()) {

				for (int i = 0, x = s.pointCount() - 1; i < s.pointCount(); i++, x = i - 1) {

					if (IntersectUtil.getIntersection(intersectR,startY, startX, startY + endY, startX + endX, s.pX(i), s.pY(i), s.pX(x),s.pY(x))) {

						if (intersectP[2] > intersectR[2]) {
							System.arraycopy(intersectR, 0, intersectP, 0, intersectP.length);
						}
					}
				}
			}
			drawLine(gc, lightColor, startY, startX, intersectP[0], intersectP[1]);

			intersectP[2] = Double.MAX_VALUE;
		}
	}

	private boolean Intersection(double startX, double startY, double d, double e, double pX, double pY, double pX2, double pY2) {
		return true;
	}

	public void drawLine(GraphicsContext gc, Color color, double sx, double sy, double ex, double ey) {
		
		gc.setLineWidth(1);
		gc.setStroke(color);
		gc.setFill(Color.MAGENTA);
		double colora = Math.atan2(ey-sy, ex-sx);
		
		Color Colors[] = { 
				Color.BLACK, 
				Color.PINK, 
				Color.GREEN, 
				Color.BLUE, 
				Color.YELLOW, 
				Color.PURPLE, 
				Color.RED 
				};
		
		if (map.getDrawIntersectPoint()) {
			gc.fillOval(ex - 5, ey - 5, 10, 10);
		}
		for (int i = 0; i < Colors.length; i ++) {
			
			if (Math.round(Math.abs(colora/Math.PI*360+360)/Colors.length) % Colors.length == i)
				gc.setStroke(Colors[i]);
		}
		
		gc.strokeLine(sx, sy, ex, ey);
	}

	public String toString() {
		return "MultiRayAnimator";
	}

	public void handle(long now, GraphicsContext gc) {
		Color backgroundColor = Color.BISQUE;
		Color lightColor = Color.WHITE;

		clearAndFill(gc, backgroundColor);

		drawRays(gc, mouse.y(), mouse.x(), lightColor);

		for (PolyShape s : map.shapes()) {
			s.getDrawable().draw(gc);
		}
	}
}
