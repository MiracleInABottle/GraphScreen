package brianbiggs.graphscreen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
    private int px;
    private int dx = 20;
    private float lines[] = new float[] {50,50,50,600,50,600,600,600};

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint baseLines = new Paint();
        Paint paint = new Paint();
        baseLines.setColor(Color.WHITE);
        baseLines.setStrokeWidth(10);
        paint.setColor(Color.RED);
        canvas.drawLines(lines, 0, 8, baseLines);
        canvas.drawCircle(px, px, dx, paint);
    }
    public void setPx(int px) {
        this.px = px;
    }
    public int getPx(){
        return this.px;
    }
    public void setDx(int dx){
        this.dx = dx;
    }
    public int getDx(){
        return this.dx;
    }
}
