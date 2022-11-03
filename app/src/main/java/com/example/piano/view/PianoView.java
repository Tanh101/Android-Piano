package com.example.piano.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.piano.model.Key;

import java.util.ArrayList;

public class PianoView extends View {

    public static final int NUMBER_OF_KEY = 14;
    private ArrayList<Key> whites;
    private ArrayList<Key> blacks;

    private int keyWidth, keyHeight;

    Paint blackPen, whitePen;


    public PianoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        whites = new ArrayList<Key>();
        blacks = new ArrayList<Key>();

        whitePen = new Paint();
        whitePen.setStyle(Paint.Style.FILL);
        whitePen.setColor(Color.WHITE);

        blackPen = new Paint();
        blackPen.setStyle(Paint.Style.FILL);
        blackPen.setColor(Color.BLACK);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        keyWidth = w / NUMBER_OF_KEY;
        keyHeight = h;

        int blackCount = 15;
        for (int i = 0; i < NUMBER_OF_KEY; ++i) {
            int left = i * keyWidth;
            int right = left + keyWidth;

            RectF rectF = new RectF(left, 0, right, h);
            whites.add(new Key(i + 1, rectF));

            if (i != 0 && i != 3 && i != 7 && i != 10) {
                rectF = new RectF((float) (i - 1) * keyWidth + 0.75f * keyWidth,
                        0,
                        (float) i * keyWidth + 0.25f * keyWidth,
                        0.67f * keyHeight);
                blacks.add(new Key(blackCount, rectF));
                blackCount++;
            }

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Key k : whites) {
            canvas.drawRect(k.rect, whitePen);
        }

        for (int i = 0; i < NUMBER_OF_KEY; ++i) {
            canvas.drawLine(i * keyWidth, 0, i * keyWidth, keyHeight, blackPen);
        }

        for (Key k : blacks) {
            canvas.drawRect(k.rect, blackPen);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean isDownAction = action == MotionEvent.ACTION_DOWN ||
                action == MotionEvent.ACTION_MOVE;
        for (int touchIndecx = 0; touchIndecx < event.getPointerCount(); touchIndecx++) {
            float x = event.getX(touchIndecx);
            float y = event.getY(touchIndecx);

            for (Key k : whites) {
                if (k.rect.contains(x, y)) {
                    k.down = isDownAction;

                }
            }
            for (Key k : blacks) {
                if (k.rect.contains(x, y)) {
                    k.down = isDownAction;

                }
            }
        }
        invalidate();
        return true;
    }
}
