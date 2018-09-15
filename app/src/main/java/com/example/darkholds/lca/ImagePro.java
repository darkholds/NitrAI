package com.example.darkholds.lca;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by cict on 15/09/2018.
 */

public class ImagePro {
    Bitmap[] samples;
    private int averageColor;
    private int numberOfGreenPixels;

    public ImagePro(Bitmap[] samples){
        this.samples = new Bitmap[Globals.numberOfSamples];
        this.samples = samples;

        int red=0, green=0, blue=0;

        for(int i=0;i<Globals.numberOfSamples;i++){
            if(samples[i]!=null){
                int color=processSample(samples[i]);
                red+=Color.red(color);
                green+=Color.green(color);
                blue+=Color.blue(color);
                //numberOfGreenPixels+=processSample(samples[i]);
            }

        }

        red /= Globals.numberOfSamples;
        green/=Globals.numberOfSamples;
        blue/=Globals.numberOfSamples;

        averageColor = Color.argb(0,red,green,blue);
        //averageColor=numberOfGreenPixels/Globals.numberOfSamples;
    }

    public int getAverageColor() {
        return averageColor;
    }

    public int processSample(Bitmap bitmap){
        int pixel;
        int numPixels=0;

        int greenPixelAccumulator=0;
        int redPixelAccumulator=0;
        int bluePixelAccumulator=0;

        Bitmap processedBitmap= bitmap.copy(Bitmap.Config.ARGB_4444,true);

        for(int i = 0;i<bitmap.getWidth();i++){
            for(int j=0;j<bitmap.getHeight();j++){
                pixel = bitmap.getPixel(i,j);
                if(!isInRange(pixel))
                    processedBitmap.setPixel(i,j,Color.WHITE);
                else {
                    numPixels++;

                    greenPixelAccumulator += Color.green(pixel);
                    redPixelAccumulator += Color.red(pixel);
                    bluePixelAccumulator += Color.blue(pixel);
                }
            }
        }

        int green = greenPixelAccumulator/numPixels;
        int red = redPixelAccumulator/numPixels;
        int blue = bluePixelAccumulator/numPixels;

        int aveColor = Color.argb(0,red,green,blue);
        return aveColor;

        //return processedBitmap;
    }

    private boolean isInRange(int color){
        boolean inRange;
        int red = Color.red(color);
        int green = Color.green(color);
        int blue=Color.blue(color);

        if(green>=86 && red<=101 && blue==0)
            inRange=true;
        else if(green>=86 && red<=101 && blue<=47)
            inRange=true;
        else if(green>=86 && red==0 && blue==0)
            inRange =true;
        else
            inRange=false;

        return inRange;
    }
}
