package Proses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player {
	public Texture tekObjek;
    public Rectangle posObjek;
    public Vector3 posisi;
    public float rotation, stateTime = 0f;
    int cekAnimasi = 0;
    float speedY = -20f;
    public static boolean statGerak = false, statJump = false, isAlive = true;

    public Player(Rectangle posObjek, Texture tekObjek, Vector3 posisi, float rotation){
        this.tekObjek = tekObjek;
        this.posObjek = posObjek;
        this.posisi = posisi;
        this.rotation = rotation;
    }
    
    public void setAnimasi(){
        if (posObjek.width == 103 && posObjek.height == 97){
            cekAnimasi = 0;
        }
        if (posObjek.width == 97 && posObjek.height == 103){
            cekAnimasi = 1;
        }
        if (cekAnimasi == 0){
            stateTime += Gdx.graphics.getDeltaTime();
            if (stateTime > 0.10f){
                posObjek.width -= 1; posObjek.height += 1;
                stateTime -= 0.10f;
            }
        }
        if (cekAnimasi == 1){
            stateTime += Gdx.graphics.getDeltaTime();
            if (stateTime > 0.10f){
                posObjek.width += 1; posObjek.height -= 1;
                stateTime -= 0.10f;
            }
        }
    }
    
    public void Update(SpriteBatch b){
		b.begin();
		b.draw(tekObjek, posObjek.x, posObjek.y, posObjek.width, posObjek.height);
		b.end();
	}
    
    public void Update()
    {
        // Player Jalan
        if (statGerak == true){
            posObjek.x += 2;
        }
        else if (statGerak == false){
            posObjek.x -= 3;
            if (posObjek.x <= 30){
                posObjek.x = 30;
            }
        }

        //  Player Loncat
        if (statJump == true){
            JumpBiasa();
        }

        //  Player Live
        if (isAlive == false){
            posObjek.x = 0; posObjek.y = 0;
        }
    }
    
    public void JumpBiasa(){
        posObjek.x += 10;
        posObjek.y -= (int)speedY;
        speedY += 1;
        speedY += 0.02f;
        if (posObjek.y <= 95){
            speedY = -20;
            statJump = false;
            posObjek.y = 95;
        }
    }
}
