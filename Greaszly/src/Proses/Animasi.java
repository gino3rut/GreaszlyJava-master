package Proses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Animasi {
	public Texture tekAnimasi;
	public Rectangle posAnimasi;				
	public int jumBawah = 0;				    
	public int jumKanan = 0;					
	public TextureRegion[][] splittedSprite;		
	public int currentRow, currentCol;
	float stateTime = 0f;
	
	public Animasi(Rectangle posAnimasi, Texture tekAnimasi, int jumBawah, int jumKanan) {
		this.tekAnimasi = tekAnimasi;
		this.posAnimasi = posAnimasi;
		this.jumBawah = jumBawah;
		this.jumKanan = jumKanan;
	}
	
	public void Proses(){
		splittedSprite = TextureRegion.split(tekAnimasi, tekAnimasi.getWidth() / jumKanan, tekAnimasi.getHeight() / jumBawah); //split sprite sheet		
		currentRow = 0;currentCol = 0;	
	}
	
	public void Update(SpriteBatch b){		
		b.begin();	
		b.draw(splittedSprite[currentRow][currentCol], posAnimasi.x, posAnimasi.y);
		b.end();
	}
	
	public void setAnimasi(){
        if (currentCol == 3){
        	currentCol = 0;
            if (currentRow == 4){
            	currentRow = 0;
            }else{
            	currentRow++;
            }
        }
        stateTime += Gdx.graphics.getDeltaTime();
        if (stateTime > 0.07f){
        	currentCol += 1;
            stateTime -= 0.07f;
        }
    }
}
