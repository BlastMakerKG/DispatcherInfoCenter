package maps.lwjgl;

import lombok.Getter;

import static org.lwjgl.opengl.GL11.*;

@Getter
public abstract class GameObject {
    protected float x;
    protected float y;

    protected Sprite spr;

    private Animation anim;

    public void update(){

    }

    public  void render(){
        glPushMatrix();
        {
            glTranslated(x, y,0);
            spr.render();
        }

        glPopMatrix();
    }

    public void resize(float zoom){
         if( zoom > 0){
            this.x -= zoom*0.5;
            this.y -= zoom*0.5;
            this.spr.setSx(spr.getSx()+zoom);
            this.spr.setSy(spr.getSy()+zoom);
        }else if(this.spr.getSx() <= 1.5f && this.spr.getSy() <= 1.5f){
            this.spr.setSy(1.5f);
            this.spr.setSx(1.5f);
        }else{
            this.x += -zoom*.5;
            this.y += -zoom*0.5;
            this.spr.setSx(spr.getSx()+zoom);
            this.spr.setSy(spr.getSy()+zoom);
        }
    }

    protected void init(float r, float g, float b, float sx,float sy, float x,float y){
        this.x = x;
        this.y = y;
        this.spr = Sprite.builder()
                        .r(r)
                        .g(g)
                        .b(b)
                        .sx(sx)
                        .sy(sy)
                    .build();
    }

}
