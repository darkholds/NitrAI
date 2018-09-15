package com.example.darkholds.lca;

import android.graphics.Color;

/**
 * Created by cict on 15/09/2018.
 */

public class FLogic {
    private int color;
    private int readingValue;

    final int LIGHT_GREEN = 6651417;
    final int NORMAL_GREEN_WS=5405479;
    final int NORMAL_GREEN_TR=3891747;
    final int DARK_GREEN = 2905647;

    public FLogic(int color){
        this.color=color;
    }

    public void processColor(){
        /*int r=0,g=0,b=0;
        r= Color.red(color); g=Color.green(color); b= Color.blue(color);
        if(r<=101 && r>=82){
            if(r >= (101+82)/2)
                readingValue=2;
            else
                readingValue=3;
        }
        else if(r<=101 && r>=82)*/

        if(color==LIGHT_GREEN || color>=(LIGHT_GREEN + NORMAL_GREEN_WS)/2){
            readingValue=2;
        }
        else if(color==NORMAL_GREEN_WS || color<(LIGHT_GREEN + NORMAL_GREEN_WS)/2 || color>=(NORMAL_GREEN_WS+NORMAL_GREEN_TR)/2)
            readingValue=3;
        else if(color==NORMAL_GREEN_TR || color<(NORMAL_GREEN_WS+NORMAL_GREEN_TR)/2 || color>=(NORMAL_GREEN_TR+DARK_GREEN)/2)
            readingValue=4;
        else if(color==DARK_GREEN || color<(NORMAL_GREEN_TR+DARK_GREEN)/2)
            readingValue =5;
        else
            readingValue=0; //error
    }

    public int getReadingValue() {
        return readingValue;
    }
}
