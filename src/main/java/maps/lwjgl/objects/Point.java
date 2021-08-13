package maps.lwjgl.objects;

import maps.lwjgl.Objects;

import static org.lwjgl.opengl.GL11.*;

public class Point extends Objects {

    public static final float SIZE = 8;
    private Sprite spr;

    public Point(float x, float y) {
        this.spr = new Point.Sprite(1,0,0,SIZE,SIZE);
        point(x,y, spr);
    }

    public class Sprite {
        private float r;
        private float g;
        private float b;

        private float sx;
        private float sy;

        public Sprite(float r, float g, float b, float sx, float sy) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.sx = sx;
            this.sy = sy;
        }

        public void render() {
            glColor3f(r, g, b);

            glBegin(GL_QUADS);

            {
                glVertex2f(0, 0);
                glVertex2f(0, sy);
                glVertex2f(sx, sy);
                glVertex2f(sx, 0);
            }

            glEnd();
        }
    }
}
