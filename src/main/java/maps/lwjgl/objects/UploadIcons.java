package maps.lwjgl.objects;
import org.newdawn.slick.opengl.*;

import java.io.*;

import static org.lwjgl.opengl.GL11.*;

public class UploadIcons {

    private String filepath;
    private Texture wood;

    public UploadIcons(String path){
        filepath = path;
        try {
            System.out.println("src\\main\\resources\\Venichles\\"+ path);
            wood = TextureLoader.getTexture("PNG", new FileInputStream(new File("src\\main\\resources\\Venichles\\"+ path)));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void render(){
        glEnable(GL_TEXTURE_2D);
        wood.bind();
        // Enable additive blending. This means that the colours will be added to already existing colours in the
        // frame buffer. In practice, this makes the black parts of the texture become invisible.
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);

        glColor3f(1,1,1);
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0, 0);
            glVertex2i(-10, 30); // Upper-left
            glTexCoord2f(1, 0);
            glVertex2i(20, 30); // Upper-right
            glTexCoord2f(1, 1);
            glVertex2i(20, 10); // Bottom-right
            glTexCoord2f(0, 1);
            glVertex2i(-10, 10); // Bottom-left
        }
        glEnd();
    }
}
