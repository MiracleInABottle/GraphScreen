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
    private Graph graph;
    private DataSeries data;
    private int fitType = CurveFit.LINEAR;


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
        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.LTGRAY);
        gridPaint.setTextSize(30);
        if(graph == null){
            init(); //try to create a graph
        }
        //if the data was empty don't compute
        if(graph != null) {
            graph.setFitType(fitType);
            Line[] verticalLines = graph.getVerticalGridlines();
            String[] verticalLabels = graph.getVerticalLabels();
            for (int i = 0; i < verticalLines.length; i++) {
                canvas.drawLine(verticalLines[i].startX, verticalLines[i].startY, verticalLines[i].endX, verticalLines[i].endY, gridPaint);
                canvas.drawText(verticalLabels[i], verticalLines[i].startX, graph.getOrigin().y + 30, gridPaint);
            }
            Line[] horizontalLines = graph.getHorizontalGridlines();
            String[] horizontalLabels = graph.getHorizontalLabels();
            for (int i = 0; i < horizontalLines.length; i++) {
                canvas.drawLine(horizontalLines[i].startX, horizontalLines[i].startY, horizontalLines[i].endX, horizontalLines[i].endY, gridPaint);
                canvas.drawText(horizontalLabels[i], graph.getOrigin().x - 30, horizontalLines[i].startY, gridPaint);
            }
            Paint plotPaint = new Paint();
            plotPaint.setColor(Color.BLUE);
            Point[] dataPoints = graph.getPoints();
            for (int i = 0; i < dataPoints.length; i++) {
                canvas.drawCircle(dataPoints[i].x, dataPoints[i].y, 10, plotPaint);
            }
            Paint fitPaint = new Paint();
            fitPaint.setStrokeWidth(2);
            fitPaint.setColor(Color.RED);
            Point[] fitPoints = graph.getFitPoints();
            for (int i = 1; i < fitPoints.length; i++) {
                canvas.drawLine(fitPoints[i - 1].x, fitPoints[i - 1].y, fitPoints[i].x, fitPoints[i].y, fitPaint);
            }
        }
    }
    private void init(){
        Rect drawingArea = new Rect(getLeft()+getPaddingLeft(), getTop()+getPaddingTop(), getRight()-getPaddingRight(), getBottom()+getPaddingBottom());
        if(data != null){
            graph = new Graph(data, drawingArea);
            graph.setFitType(fitType);
        }

    }

    public void setData(DataSeries data){
        this.data = data;
    }


    public void setCurveType(int type){
        this.fitType = type;
        if(graph!= null){
            graph.setFitType(type);
        }
    }

    public CurveFit getFit(){
        CurveFit f = null;
        if(graph != null){
            f = graph.getFit();
        }
        return f;
    }
}
