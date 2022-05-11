package com.kennycason.kumo.bg;

import com.kennycason.kumo.collide.RectanglePixelCollidable;
import com.kennycason.kumo.image.CollisionRaster;

import java.awt.*;

/**
 * Created by kenny on 6/30/14.
 */
public class EllipseBackground implements Background {

    /**
     * 长半轴
     */
    private final int radiusLong;
    /**
     * 短半轴
     */
    private final int radiusShort;

    private final Point position;

    public EllipseBackground(final int radiusLong, final int radiusShort) {
        this.radiusLong = radiusLong;
        this.radiusShort = radiusShort;
        this.position = new Point(0, 0);
    }

    @Override
    public void mask(RectanglePixelCollidable background) {
        Dimension dimensionOfBackground = background.getDimension();
        CollisionRaster rasterOfBackground = background.getCollisionRaster();

        for (int y = 0; y < dimensionOfBackground.height; y++) {
            for (int x = 0; x < dimensionOfBackground.width; x++) {
                if (!inEllipse(x, y)) {
                    rasterOfBackground.setPixelIsNotTransparent(
                            position.x + x, position.y + y
                    );
                }
            }
        }
    }

    private boolean inEllipse(final int x, final int y) {
        final float centerX = position.x + x - radiusLong;
        final float centerY = position.y + y - radiusShort;
        return (centerX * centerX) / (radiusLong * radiusLong) + (centerY * centerY) / (radiusShort * radiusShort) < 1;
    }

}
