package aymanzeine.featuretrack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Camera.Face;
import android.util.AttributeSet;
import android.view.View;

public class FaceOverlayView extends View {

    private Paint mPaint;
    private Paint mTextPaint;
    private int mDisplayOrientation;
    private int mOrientation;
    private Face[] mFaces;

    //private Bitmap mBitmap;
    //private SparseArray<com.google.android.gms.vision.face.Face> faceArray;

    public FaceOverlayView(Context context) {
        super(context);
        initialize();
    }

    public FaceOverlayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FaceOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*public void setBitmap( Bitmap bitmap ) {
        //mBitmap = bitmap;
        FaceDetector detector = new FaceDetector.Builder( getContext() )
                .setTrackingEnabled(false)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .setMode(FaceDetector.FAST_MODE)
                .build();

        if (!detector.isOperational()) {
            //Handle contingency
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            faceArray = detector.detect(frame);
            detector.release();
        }
        invalidate();
    }*/

    private void initialize() {
        // We want a green box around the face:
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setAlpha(128);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setTextSize(20);
        mTextPaint.setColor(Color.GREEN);
        mTextPaint.setStyle(Paint.Style.FILL);
    }

    public void setFaces(Face[] faces) {
        mFaces = faces;
        invalidate();
    }

    public void setOrientation(int orientation) {
        mOrientation = orientation;
    }

    public void setDisplayOrientation(int displayOrientation) {
        mDisplayOrientation = displayOrientation;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mFaces != null && mFaces.length > 0) {
            //double scale = drawBitmap(canvas);
            Matrix matrix = new Matrix();
            Util.prepareMatrix(matrix, false, mDisplayOrientation, getWidth(), getHeight());
            canvas.save();
            matrix.postRotate(mOrientation);
            canvas.rotate(-mOrientation);
            RectF rectF = new RectF();
            for (Face face : mFaces) {
                rectF.set(face.rect);
                matrix.mapRect(rectF);
                canvas.drawRect(rectF, mPaint);
                //drawFaceLandmarks(canvas, scale);
                canvas.drawText("Score " + face.score, rectF.right, rectF.top, mTextPaint);
            }
            canvas.restore();
        }
    }
/*
    private void drawFaceLandmarks( Canvas canvas, double scale ) {
        Paint paint = new Paint();
        paint.setColor( Color.GREEN );
        paint.setStyle( Paint.Style.STROKE );
        paint.setStrokeWidth( 5 );

        for( int i = 0; i < faceArray.size(); i++ ) {
            com.google.android.gms.vision.face.Face face = faceArray.valueAt(i);
            for ( Landmark landmark : face.getLandmarks() ) {
                int cx = (int) ( landmark.getPosition().x * scale );
                int cy = (int) ( landmark.getPosition().y * scale );
                canvas.drawCircle( cx, cy, 10, paint );
            }

        }
    }

    private double drawBitmap( Canvas canvas ) {
        double viewWidth = canvas.getWidth();
        double viewHeight = canvas.getHeight();
        double imageWidth = mBitmap.getWidth();
        double imageHeight = mBitmap.getHeight();
        double scale = Math.min( viewWidth / imageWidth, viewHeight / imageHeight );

        Rect destBounds = new Rect( 0, 0, (int) ( imageWidth * scale ), (int) ( imageHeight * scale ) );
        canvas.drawBitmap( mBitmap, null, destBounds, null );
        return scale;
    }
*/

}
