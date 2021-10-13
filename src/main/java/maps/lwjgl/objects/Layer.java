package maps.lwjgl.objects;

import maps.lwjgl.Objects;

import static org.lwjgl.opengl.GL11.*;

public class Layer extends Objects {

    private SpriteLayer spr;

    public Layer(float x , float y, float sizeX, float sizeY){
        this.spr = new SpriteLayer(1f,0.8f,0.1f,sizeX,sizeY);
        layer(x, y, spr);
    }

    public class SpriteLayer implements Sprite {
        private float r;
        private float g;
        private float b;

        private float sx;
        private float sy;

        public SpriteLayer(float r, float g, float b, float sx, float sy) {
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
    }
}
