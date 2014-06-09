package Proses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class GamePlay {
	Objek bg, jalan, jalan2, kompor, kompor2, atap, atap2, minyak, power;
    Player player;
    Fries fries;
    Rintangan katel;
    Animasi tanganKanan, tanganKiri, kakiKanan, kakiKiri, wajah, butGreen, butYellow, butRed;
    boolean sentuh = true;
    Vector3 p1 = new Vector3();
    int cek = 0, tempMinyak = 3, tempPower = 2, statMinyak = 0, statPower = 0;
    float stateTime = 0f, stateTime2 = 0f;
    
    public void Create(){
    	// Player        
        player = new Player(new Rectangle(30, 95, 100, 100), new Texture(Gdx.files.internal("Gameplay/player.png")), new Vector3(0, 0, 0), 0f);
        // Fries
        fries = new Fries(new Rectangle(-200, 0, 150, 150), new Texture(Gdx.files.internal("Gameplay/fries.png")), new Vector3(0, 0, 0), 0f);
        // Rintangan
        katel = new Rintangan(new Rectangle(-200, 0, 60, 60), new Texture(Gdx.files.internal("Gameplay/katel.png")), new Vector3(0, 0, 0), 0f);
        // Objek
        bg = new Objek(new Rectangle(0, 0, 800, 480), new Texture(Gdx.files.internal("Gameplay/bg.png")));
        jalan = new Objek(new Rectangle(0, -10, 2000, 100), new Texture(Gdx.files.internal("Gameplay/jalan.png")));
        jalan2 = new Objek(new Rectangle(jalan.posObjek.width, -10, 2000, 100), new Texture(Gdx.files.internal("Gameplay/jalan.png")));
        kompor = new Objek(new Rectangle(0, 0, 2000, 480), new Texture(Gdx.files.internal("Gameplay/kompor.png")));
        kompor2 = new Objek(new Rectangle(kompor.posObjek.width, 0, 2000, 480), new Texture(Gdx.files.internal("Gameplay/kompor.png")));
        atap = new Objek(new Rectangle(0, 0, 2000, 480), new Texture(Gdx.files.internal("Gameplay/atap.png")));
        atap2 = new Objek(new Rectangle(atap.posObjek.width, 0, 2000, 480), new Texture(Gdx.files.internal("Gameplay/atap.png")));
        minyak = new Objek(new Rectangle(100, 10, 600, 20), new Texture(Gdx.files.internal("Gameplay/minyak.png")));
        power = new Objek(new Rectangle(10, 200, 20, 250), new Texture(Gdx.files.internal("Gameplay/power.png")));
        //Animasi
        tanganKanan = new Animasi(new Rectangle(-100, 0, 60, 60), new Texture(Gdx.files.internal("Gameplay/tangKanan.png")), 5, 4);        
        tanganKiri = new Animasi(new Rectangle(-100, 0, 60, 60), new Texture(Gdx.files.internal("Gameplay/tangKiri.png")), 5, 4);        
        kakiKanan = new Animasi(new Rectangle(-100, 0, 60, 60), new Texture(Gdx.files.internal("Gameplay/kakKanan.png")), 5, 4);        
        kakiKiri = new Animasi(new Rectangle(-100, 0, 60, 60), new Texture(Gdx.files.internal("Gameplay/kakKiri.png")), 5, 4);        
        wajah = new Animasi(new Rectangle(-100, 0, 60, 60), new Texture(Gdx.files.internal("Gameplay/neutral.png")), 1, 1);        
        butGreen = new Animasi(new Rectangle(690, 80, 80, 80), new Texture(Gdx.files.internal("Gameplay/butGreen.png")), 1, 1);        
        butYellow = new Animasi(new Rectangle(690, 190, 80, 80), new Texture(Gdx.files.internal("Gameplay/butYellow.png")), 1, 1);        
        butRed = new Animasi(new Rectangle(690, 300, 80, 80), new Texture(Gdx.files.internal("Gameplay/butRed.png")), 1, 1);
        setProsesAnimasi(); 
    }
    
    public void Update(SpriteBatch batch, OrthographicCamera c){
    	setPosKakiTangan();
        setAnimasiBack();

        //-- Minyak
        if (statMinyak == 0)
        {
            minyak.posObjek.width -= tempMinyak;
            statMinyak = 1;
        }
        stateTime += Gdx.graphics.getDeltaTime();
        if (stateTime > 1.0f)
        {
            statMinyak = 0;
            stateTime = 0f;
        }

        //-- Power
        if (Player.statGerak == true && power.posObjek.height > 0)
        {
            power.posObjek.height -= tempPower;
        }
        else if (Player.statGerak == false && power.posObjek.height <= 250 && statPower == 0)
        {
            power.posObjek.height += 2;
            statPower = 1;
        }
        stateTime2 += Gdx.graphics.getDeltaTime();
        if (stateTime2 > 0.05f)
        {
            statPower = 0;
            stateTime2 = 0f;
        }
        if (power.posObjek.height <= 0)
        {
            Player.statGerak = false;
        }

        katel.posObjek.x = jalan.posObjek.x + 900;
        katel.posObjek.y = jalan.posObjek.y + 70;
        fries.posObjek.x = jalan.posObjek.x + 600;
        fries.posObjek.y = jalan.posObjek.y + 95;
        //--------------------------------------------   
        if (Gdx.input.isTouched() && sentuh == true){
        	sentuh = false;
        	p1.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			c.unproject(p1);
			if ((p1.x >= butGreen.posAnimasi.x && p1.x <= butGreen.posAnimasi.x + 80) && (p1.y >= butGreen.posAnimasi.y && p1.y <= butGreen.posAnimasi.y + 80))
            {
                if (power.posObjek.height >= 126)
                {
                    Player.statGerak = true;                    
                }                    
//                butGreen.posAnimasi.width = 75;
//                butGreen.posAnimasi.height = 75;
                tempMinyak = 6;                    
            }
            if ((p1.x >= butYellow.posAnimasi.x && p1.x <= butYellow.posAnimasi.x + 80) && (p1.y >= butYellow.posAnimasi.y && p1.y <= butYellow.posAnimasi.y + 80))
            {
                cek = 1;
                Player.statJump = true;
//                butYellow.posAnimasi.width = 75;
//                butYellow.posAnimasi.height = 75;
            }
            if ((p1.x >= butRed.posAnimasi.x && p1.x <= butRed.posAnimasi.x + 80) && (p1.y >= butRed.posAnimasi.y && p1.y <= butRed.posAnimasi.y + 80))
            {
//                butRed.posAnimasi.width = 75;
//                butRed.posAnimasi.height = 75;
                cekTabrakFries();
            }
        }
        if (!Gdx.input.isTouched()){
			sentuh = true;
			
			//-- Button Green
            if (Player.statGerak == true)
            {
                Player.statGerak = false;
//                butGreen.posAnimasi.width = 80;
//                butGreen.posAnimasi.height = 80;
                tempMinyak = 3;
            }

            //-- Button Yellow
            if (cek == 1) 
            {
//                butYellow.posAnimasi.width = 80;
//                butYellow.posAnimasi.height = 80;
            }

            //-- Button Red
            if (butRed.posAnimasi.width == 75)
            {
//                butRed.posAnimasi.width = 80;
//                butRed.posAnimasi.height = 80;
            }

            cek = 0;
        }
        //--------------------------------------------            
        setUpdateKakiTangan();
        player.setAnimasi();
        player.Update();
        katel.Update(player);
    	//-------------------------Proses Gambar---------------------------------------
    	bg.Update(batch);           
        atap.Update(batch);
        atap2.Update(batch);
        kompor.Update(batch);
        kompor2.Update(batch);
        jalan.Update(batch);
        jalan2.Update(batch);
        tanganKiri.Update(batch);
        kakiKiri.Update(batch);
        kakiKanan.Update(batch);
        fries.Update(batch);
        player.Update(batch);
        katel.Update(batch);
        tanganKanan.Update(batch);
        wajah.Update(batch);
        butGreen.Update(batch);
        butYellow.Update(batch);
        butRed.Update(batch);
        minyak.Update(batch);
        power.Update(batch);
    }
    
    public void Dispose(){
    	bg.tekObjek.dispose();           
        atap.tekObjek.dispose();
        atap2.tekObjek.dispose();
        kompor.tekObjek.dispose();
        kompor2.tekObjek.dispose();
        jalan.tekObjek.dispose();
        jalan2.tekObjek.dispose();
        tanganKiri.tekAnimasi.dispose();
        kakiKiri.tekAnimasi.dispose();
        kakiKanan.tekAnimasi.dispose();
        fries.tekObjek.dispose();
        player.tekObjek.dispose();
        katel.tekObjek.dispose();
        tanganKanan.tekAnimasi.dispose();
        wajah.tekAnimasi.dispose();
        butGreen.tekAnimasi.dispose();
        butYellow.tekAnimasi.dispose();
        butRed.tekAnimasi.dispose();
        minyak.tekObjek.dispose();
        power.tekObjek.dispose();
    }
    
    public void setProsesAnimasi(){
    	tanganKanan.Proses();
    	tanganKiri.Proses();
    	kakiKanan.Proses();
    	kakiKiri.Proses();
    	wajah.Proses();
    	butGreen.Proses();
    	butYellow.Proses();
    	butRed.Proses();
    }
    
    public void setPosKakiTangan(){
        tanganKanan.posAnimasi.x = (int)player.posObjek.x - 15;
        tanganKanan.posAnimasi.y = (int)player.posObjek.y;
        tanganKiri.posAnimasi.x = (int)player.posObjek.x + 67;
        tanganKiri.posAnimasi.y = (int)player.posObjek.y;
        kakiKanan.posAnimasi.x = (int)player.posObjek.x + 7;
        kakiKanan.posAnimasi.y = (int)player.posObjek.y - 40;
        kakiKiri.posAnimasi.x = (int)player.posObjek.x + 50;
        kakiKiri.posAnimasi.y = (int)player.posObjek.y - 40;
        wajah.posAnimasi.x = (int)player.posObjek.x + 35;
        wajah.posAnimasi.y = (int)player.posObjek.y + 20;
    }

    public void setUpdateKakiTangan(){
        tanganKanan.setAnimasi();
        tanganKiri.setAnimasi();
        kakiKanan.setAnimasi();
        kakiKiri.setAnimasi();
    }

    public void setAnimasiBack(){
        jalan.posObjek.x -= 2;
        jalan2.posObjek.x -= 2;
        kompor.posObjek.x -= 2;
        kompor2.posObjek.x -= 2;
        atap.posObjek.x -= 2;
        atap2.posObjek.x -= 2;

        if (jalan.posObjek.x <= -2000){
            jalan.posObjek.x = jalan2.posObjek.width;
            kompor.posObjek.x = kompor2.posObjek.width;
            atap.posObjek.x = atap2.posObjek.width;
        }else if (jalan2.posObjek.x <= -2000){
            jalan2.posObjek.x = jalan.posObjek.width;
            kompor2.posObjek.x = kompor.posObjek.width;
            atap2.posObjek.x = atap.posObjek.width;
        }
    }

    public void cekTabrakFries(){
        if (player.posObjek.overlaps(fries.posObjek)){
            katel.posObjek.x = 0;
            katel.posObjek.y = 0;
        }
    }
}
