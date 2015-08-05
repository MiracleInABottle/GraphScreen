package brianbiggs.graphscreen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
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
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

//    }
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
    private Graph graph;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.WHITE);
        gridPaint.setTextSize(30);
        if(graph == null){
            init();
        }
        Line[] verticalLines = graph.getVerticalGridlines();
        String[] verticalLabels = graph.getVerticalLabels();
        for(int i = 0; i< verticalLines.length; i++){
            canvas.drawLine(verticalLines[i].startX,verticalLines[i].startY,verticalLines[i].endX,verticalLines[i].endY, gridPaint );
            canvas.drawText(verticalLabels[i], verticalLines[i].startX, graph.getOrigin().y + 30, gridPaint);
        }
        Paint baseLines = new Paint();
        Paint paint = new Paint();
        baseLines.setColor(Color.WHITE);
        baseLines.setStrokeWidth(10);
        paint.setColor(Color.RED);
//        canvas.drawLines(lines, 0, 8, baseLines);
//        canvas.drawCircle(px, px, dx, paint);

        Line[] horizontalLines = graph.getHorizontalGridlines();
        String[] horizontalLabels = graph.getHorizontalLabels();
        for(int i = 0; i< horizontalLines.length; i++){
            canvas.drawLine(horizontalLines[i].startX,horizontalLines[i].startY,horizontalLines[i].endX,horizontalLines[i].endY, gridPaint );
            canvas.drawText(horizontalLabels[i], graph.getOrigin().x - 30, horizontalLines[i].startY, gridPaint);
        }

        Paint plotPaint = new Paint();
        plotPaint.setColor(Color.BLUE);
        Point[] dataPoints = graph.getPoints();
        for(int i = 0; i< dataPoints.length; i++){
            canvas.drawCircle(dataPoints[i].x  , dataPoints[i].y , 15, plotPaint);
        }
    }


    private void init(){
        Rect drawingArea = new Rect(getLeft()+getPaddingLeft(), getTop()+getPaddingTop(), getRight()-getPaddingRight(), getBottom()+getPaddingBottom());
        double [] xPoints = {0,0.267,0.50,0.701,0.901,1.101,1.301,1.502,1.702};
        double [] yPoints = {0,0.10,0.2,0.3,0.35,0.3,0.2,0.1,0.0};
        DataSeries data = new DataSeries(xPoints, yPoints, 9);
        graph = new Graph(data, drawingArea);
        graph.setFitType(CurveFit.QUAD);
    }
}
