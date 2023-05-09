package com.example.er_killersudoku20;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class GameView extends View {

    private int celluleWidth;
    private int celluleHeight;
    private final int margin = 3;
    private Grille grille;
    private Cellule[][] cells;
    public static GameLevel gamelevel;
    private final int paintstroke = 6;
    private final String aidecolor = "#d6f1ff";

    public boolean brouillon;

    public int x_selectedcell = -1;
    public int y_selectedcell = -1;


    private final Paint paint = new Paint( Paint.ANTI_ALIAS_FLAG );

    public GameView(Context context) {
        super(context);
        //Log.d("tata", "GameView1: ");
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        grille = new Grille();
        cells = grille.getGrille(gamelevel);
        //Log.d("tata", "GameView2: ");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x_selectedcell = (int) x / celluleWidth;
                y_selectedcell = (int) (y + margin) / celluleWidth;

                if (y_selectedcell <= 8) {
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        celluleWidth = w / 9;
        celluleHeight = celluleWidth;


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Log.d("toto", "onDraw: " + gamelevel);

        affichercarreinitiale(canvas);

        afficheraidecarreselectioner(canvas);

        affichercoletligneselectioner(canvas);

        tracerLaGrille(canvas);

        afficherAideCelluleSelectioner(canvas);

        tracerLesChiffreInitiale(canvas);
    }

    private void tracerLaGrille(Canvas canvas) {

        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(paintstroke/2);
        for (int j = 0; j < 9; j++) {
            canvas.drawLine(0, margin + celluleHeight * j, celluleWidth * 9, margin + celluleHeight * j, paint);
            canvas.drawLine(celluleWidth * j, margin, celluleWidth * j, margin + celluleHeight * 9, paint);
        }

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(paintstroke);
        for (int i = 0; i < 4; i++) {
            canvas.drawLine(0, margin + celluleWidth * 3 * i, celluleWidth * 9, margin + celluleWidth * 3 * i, paint);
            canvas.drawLine(celluleWidth * 3 * i, margin, celluleWidth * 3 * i, margin + celluleWidth * 9, paint);
        }
    }

    private void affichercarreinitiale(Canvas canvas) {
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeWidth(paintstroke);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {

                if (cells[y][x].isInitial) {
                    paint.setColor(Color.parseColor("#DDDDDD"));
                    canvas.drawRect(x * celluleWidth, margin + y * celluleWidth, x * celluleWidth + celluleWidth, margin + y * celluleWidth + celluleWidth, paint);
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void tracerLesChiffreInitiale(Canvas canvas) {
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeWidth(paintstroke/2);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {

                if (cells[y][x].isInitial) {
                    paint.setColor(Color.BLACK);
                    paint.setTextSize( celluleWidth * 0.7f );
                    canvas.drawText(String.valueOf(cells[y][x].getValeur()),x * celluleWidth + celluleWidth / 2, y * celluleWidth + celluleWidth * 0.75f + margin, paint);
                }
                if (cells[y][x].valeurtestistrue != null) {
                    if (cells[y][x].valeurtestistrue == true) {
                        paint.setColor(Color.BLUE);
                        paint.setTextSize( celluleWidth * 0.7f );
                        canvas.drawText(String.valueOf(cells[y][x].valeurTest),x * celluleWidth + celluleWidth / 2, y * celluleWidth + celluleWidth * 0.75f + margin, paint);
                    } else if(cells[y][x].valeurtestistrue == false) {
                        paint.setColor(Color.RED);
                        paint.setTextSize( celluleWidth * 0.7f );
                        canvas.drawText(String.valueOf(cells[y][x].valeurTest),x * celluleWidth + celluleWidth / 2, y * celluleWidth + celluleWidth * 0.75f + margin, paint);
                    }
                }
                paint.setColor(Color.BLACK);
                paint.setTextSize( celluleWidth * 0.4f );
                for (int i = 0; i < 9; i++) {
                    if (cells[y][x].note[i]) {
                        canvas.drawText(String.valueOf(i+1),x * celluleWidth + celluleWidth / 5 + i%3*35 , y * celluleWidth + 35 + Math.floorDiv(i,3)*40 + margin, paint);
                    }
                }
            }
        }
    }

    private void afficherAideCelluleSelectioner(Canvas canvas) {
        if (x_selectedcell != -1 && y_selectedcell != -1) {
            paint.setColor( 0xFF_4D_5E_AB);
            paint.setStrokeWidth(paintstroke * 1.5f);
            paint.setStyle(Paint.Style.STROKE);

            canvas.drawRect(x_selectedcell * celluleWidth,   margin + y_selectedcell * celluleWidth, x_selectedcell * celluleWidth + celluleWidth, margin + y_selectedcell * celluleWidth + celluleWidth, paint);

            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
    }

    private void affichercoletligneselectioner(Canvas canvas) {
        if (x_selectedcell != -1 && y_selectedcell != -1) {
            paint.setColor(Color.parseColor(aidecolor));
            canvas.drawRect(0, margin + y_selectedcell * celluleWidth, getWidth(), margin + y_selectedcell * celluleWidth + celluleWidth, paint);
            canvas.drawRect(x_selectedcell * celluleWidth, margin, x_selectedcell * celluleWidth + celluleWidth, 9 * celluleWidth - margin , paint);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void afficheraidecarreselectioner(Canvas canvas) {
        if (x_selectedcell != -1 && y_selectedcell != -1) {
            paint.setColor(Color.parseColor(aidecolor));
            paint.setStrokeWidth(paintstroke * 1.5f);

            int x_carre =  Math.floorDiv(x_selectedcell, 3) * 3;
            int y_carre =  Math.floorDiv(y_selectedcell, 3) * 3;

            canvas.drawRect(x_carre * celluleWidth,   margin + y_carre * celluleWidth, x_carre * celluleWidth + celluleWidth * 3, margin + y_carre * celluleWidth + celluleWidth * 3, paint);
        }
    }

    public void setValeurtest(int nbr) {
        //Log.d("tagtest", "setValeurtest: test");

        if (!cells[y_selectedcell][x_selectedcell].isInitial) {
            cells[y_selectedcell][x_selectedcell].valeurtestistrue = cells[y_selectedcell][x_selectedcell].getValeur() == nbr;
            for (int i = 0;i < 9;i++) {
                cells[y_selectedcell][x_selectedcell].note[i] = false;
            }
            cells[y_selectedcell][x_selectedcell].valeurTest = nbr;
            invalidate();

        }
    }

    public void eraseValue() {
        if (!cells[y_selectedcell][x_selectedcell].isInitial) {
            cells[y_selectedcell][x_selectedcell].valeurtestistrue = null;
            for (int i = 0;i < 9;i++) {
                cells[y_selectedcell][x_selectedcell].note[i] = false;
            }
            invalidate();
        }
    }

    public void setNote(int nbr) {
        if (!cells[y_selectedcell][x_selectedcell].isInitial && cells[y_selectedcell][x_selectedcell].note[nbr-1] == false) {
            cells[y_selectedcell][x_selectedcell].note[nbr-1] = true;
            invalidate();
        }
        else if (!cells[y_selectedcell][x_selectedcell].isInitial && cells[y_selectedcell][x_selectedcell].note[nbr-1] == true) {
            cells[y_selectedcell][x_selectedcell].note[nbr-1] = false;
            invalidate();
        }
    }
}
