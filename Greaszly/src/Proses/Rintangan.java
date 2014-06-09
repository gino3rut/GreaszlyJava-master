package Proses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Rintangan {
	public Texture tekObjek;
    public Rectangle posObjek;
    public Vector3 posisi;
    public float rotation;

    public Rintangan(Rectangle posObjek, Texture tekObjek, Vector3 posisi, float rotation){
        this.tekObjek = tekObjek;
        this.posObjek = posObjek;
        this.posisi = posisi;
        this.rotation = rotation;
    }
    
    public void Update(Player player){
        if (player.posObjek.overlaps(posObjek)){
            Player.isAlive = false;
        }
    }
    
    public void Update(SpriteBatch b){
		b.begin();
		b.draw(tekObjek, posObjek.x, posObjek.y, posObjek.width, posObjek.height);
		b.end();
	}
}
