package com.example.escapethedoctor.entities;

import static android.os.Build.VERSION_CODES.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.escapethedoctor.gamestates.Playing;
import com.example.escapethedoctor.helpers.interfaces.BitmapMethods;
import com.example.escapethedoctor.main.MainActivity;

public enum GameCharacters implements BitmapMethods {

    PLAYER(R);

    private Bitmap spriteSheet;
    private final Bitmap[][] sprites = new Bitmap[5][2];

    GameCharacters(int resID) {
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resID, options);
        for(int j = 0; j < sprites.length; j++){
            for(int i = 0; i < sprites[j].length; i++){
                sprites[j][i] = getScaleBitmap(Bitmap.createBitmap(spriteSheet, 64 * i ,64 * j, 64, 64), Playing.getTileSize());
            }
        }
    }

    public void setPlayerBitmap(int tileSize){
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), R, options);
        for(int j = 0; j < sprites.length; j++){
            for(int i = 0; i < sprites[j].length; i++){
                sprites[j][i] = getScaleBitmap(Bitmap.createBitmap(spriteSheet, 64 * i ,64 * j, 64, 64), tileSize);
            }
        }
    }

    public Bitmap getSprite(int yPos, int xPos){
        return  sprites[yPos][xPos];
    }


}
