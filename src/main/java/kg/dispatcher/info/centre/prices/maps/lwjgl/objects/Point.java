package kg.dispatcher.info.centre.prices.maps.lwjgl.objects;

import kg.dispatcher.info.centre.prices.maps.lwjgl.Objects;

import static org.lwjgl.opengl.GL11.*;

public class Point extends Objects {

    public static final float SIZE = 4;
    private SpritePoint spr;

    public Point(float x, float y, float r,float g,float b) {
        this.spr = new SpritePoint(r,g,b,SIZE,SIZE);
        point(x,y, spr);
    }

    public class SpritePoint implements Sprite {
        private float r;
        private float g;
        private float b;
        private float sx;
        private float sy;


        @Override
        public float getSx() {
            return sx;
        }

        @Override
        public void setSx(float sx) {
            this.sx = sx;
        }

        @Override
        public float getSy() {
            return sy;
        }

        @Override
        public void setSy(float sy) {
            this.sy = sy;
        }



        public SpritePoint(float r, float g, float b, float sx, float sy) {
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
