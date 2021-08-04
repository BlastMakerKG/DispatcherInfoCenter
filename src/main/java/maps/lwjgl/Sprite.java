package maps.lwjgl;
import lombok.*;

import static org.lwjgl.opengl.GL11.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Sprite {
    private float r;
    private float g;
    private float b;

    private float sx;
    private float sy;


    public void render(){
        glColor3f(r,g,b);

        glBegin(GL_QUADS);

        {
            glVertex2f(0,0);
            glVertex2f(0,sy);
            glVertex2f(sx,sy);
            glVertex2f(sx,0);
        }

        glEnd();
    }
}
