package maps.lwjgl.objects;

import maps.lwjgl.Objects;

import static org.lwjgl.opengl.GL11.*;

public class Line extends Objects {

    private float sx;
    private float sy;

    private Line.Sprite spr;

    public Line(float x, float y, float x2, float y2) {
        this.sx = x2 -x;
        this.sy = y2 - y;
        this.spr = new Line.Sprite(0,1,0,sx, sy);
        line(x,y, spr);
    }


    public class Sprite {
        private float r;
        private float g;
        private float b;

        private float sx;
        private float sy;

        public float getSx() {
            return sx;
        }

        public void setSx(float sx) {
            this.sx = sx;
        }

        public float getSy() {
            return sy;
        }

        public void setSy(float sy) {
            this.sy = sy;
        }

        public Sprite(float r, float g, float b, float sx, float sy) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.sx = sx;
            this.sy = sy;
        }

        public void render() {
            glColor3f(r, g, b);

            glBegin(GL_LINES);

            {
                glVertex2f(0, 0);
                glVertex2f(sx, sy);
            }

            glEnd();
        }
    }
}
