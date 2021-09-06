package maps.lwjgl.objects;

import maps.lwjgl.Objects;

import static org.lwjgl.opengl.GL11.*;

public class Road extends Objects {

    private Road.Sprite spr;

   public Road(float x, float y){
       this.spr = new Sprite(0.5f,0.6f,0.7f);
       point(x,y, spr);
    }


    public class Sprite{
        private float r,g,b;
        private float sx = 4;
        private float sy = 4;


        public Sprite(float r, float g, float b) {
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
