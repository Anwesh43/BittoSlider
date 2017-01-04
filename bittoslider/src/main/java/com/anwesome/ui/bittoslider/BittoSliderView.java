package com.anwesome.ui.bittoslider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.*;
/**
 * Created by anweshmishra on 05/01/17.
 */
public class BittoSliderView extends View {
    private float sx = 0;
    private float value = 0;
    private SliderOnChangeListener onChangeListener;

    public SliderOnChangeListener getOnChangeListener() {
        return onChangeListener;
    }

    public void setOnChangeListener(SliderOnChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    public float getValue() {
        return value;

    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    private float min = 0,max = 100;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int color = Color.parseColor("#7C4DFF");
    private boolean isDown = false;
    public BittoSliderView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public BittoSliderView(Context context) {
        super(context);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getSx() {
        return sx;
    }

    public void setSx(float sx) {
        this.sx = sx;
    }

    public void onDraw(Canvas canvas) {
        paint.setColor(Color.parseColor("#B0BEC5"));
        canvas.drawRoundRect(new RectF(0,0,canvas.getWidth(),AppConstants.SLIDER_HEIGHT),AppConstants.SLIDER_HEIGHT/2,AppConstants.SLIDER_HEIGHT/2,paint);
        paint.setColor(color);
        canvas.drawRoundRect(new RectF(0,0,sx,AppConstants.SLIDER_HEIGHT),AppConstants.SLIDER_HEIGHT/2,AppConstants.SLIDER_HEIGHT/2,paint);
        canvas.drawCircle(sx+AppConstants.SLIDER_HEIGHT/4,AppConstants.SLIDER_HEIGHT/2,AppConstants.SLIDER_HEIGHT/2,paint);
        value = min+((sx*1.0f)/canvas.getWidth())*Math.abs(max-min);
        if(isDown) {
            try {
                Thread.sleep(100);
                if(onChangeListener!=null) {
                    onChangeListener.onChange(value);
                }
            }
            catch (Exception ex){

            }
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!isDown && event.getX()>sx-AppConstants.SLIDER_HEIGHT/2 && event.getX()<sx+AppConstants.SLIDER_HEIGHT/2) {
                    isDown = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(isDown) {
                    sx = event.getX();
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isDown) {
                    isDown = false;
                }
                break;
        }

        postInvalidate();
        return true;
    }
}
