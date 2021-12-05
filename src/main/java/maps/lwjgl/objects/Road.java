package maps.lwjgl.objects;

import maps.lwjgl.Objects;

import static org.lwjgl.opengl.GL11.*;

public class Road extends Objects {

    private SpriteRoad spr;

   public Road(float x, float y, float r, float g, float b){
       this.spr = new SpriteRoad(r,g,b);
       point(x,y, spr);
    }


    public class SpriteRoad implements Sprite{
        private float r,g,b;
        private float sx = 2;
        private float sy = 2;

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

        public SpriteRoad(float r, float g, float b) {
            this.r = r;
            this.g = g;
            this.b = b;
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
