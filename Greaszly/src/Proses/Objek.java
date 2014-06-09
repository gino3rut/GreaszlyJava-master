package Proses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Objek {
	public Texture tekObjek;
    public Rectangle posObjek;

    public Objek(Rectangle posObjek, Texture tekObjek){
        this.tekObjek = tekObjek;
        this.posObjek = posObjek;
    }
    
    public void Update(SpriteBatch b){
		b.begin();
		b.draw(tekObjek, posObjek.x, posObjek.y, posObjek.width, posObjek.height);
		b.end();
	}
}
