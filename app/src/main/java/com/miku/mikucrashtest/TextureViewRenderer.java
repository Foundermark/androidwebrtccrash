package com.miku.mikucrashtest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;

import org.webrtc.EglBase;
import org.webrtc.EglRenderer;
import org.webrtc.GlRectDrawer;
import org.webrtc.RendererCommon;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoFrame;
import org.webrtc.VideoSink;

import java.util.concurrent.CountDownLatch;

public class TextureViewRenderer extends TextureView implements SurfaceHolder.Callback, TextureView.SurfaceTextureListener, VideoSink, RendererCommon.RendererEvents {

    private final String resourceName;
    private final RendererCommon.VideoLayoutMeasure videoLayoutMeasure = new RendererCommon.VideoLayoutMeasure();
    private final EglRenderer eglRenderer;


    private RendererCommon.RendererEvents rendererEvents;

    private final Object layoutLock = new Object();
    private boolean isFirstFrameRendered;
    private int rotatedFrameWidth;
    private int rotatedFrameHeight;
    private int frameRotation;

    private boolean enableFixedSize;
    private int surfaceWidth;
    private int surfaceHeight;

    public TextureViewRenderer(Context context) {
        super(context);
        this.resourceName = getResourceName();
        eglRenderer = new EglRenderer(resourceName);
        setSurfaceTextureListener(this);
    }

    public TextureViewRenderer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.resourceName = getResourceName();
        eglRenderer = new EglRenderer(resourceName);
        setSurfaceTextureListener(this);
    }

    public void init(EglBase.Context sharedContext, RendererCommon.RendererEvents rendererEvents) {
        init(sharedContext, rendererEvents, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    public void init(final EglBase.Context sharedContext, RendererCommon.RendererEvents rendererEvents, final int[] configAttributes, RendererCommon.GlDrawer drawer) {
        ThreadUtils.checkIsOnMainThread();
        this.rendererEvents = rendererEvents;
        synchronized (layoutLock) {
            rotatedFrameWidth = 0;
            rotatedFrameHeight = 0;
            frameRotation = 0;
        }
        eglRenderer.init(sharedContext, configAttributes, drawer);
    }

    public void release() {
        eglRenderer.release();
    }

    public void addFrameListener(EglRenderer.FrameListener listener, float scale, RendererCommon.GlDrawer drawerParam) {
        eglRenderer.addFrameListener(listener, scale, drawerParam);
    }

    public void removeFrameListener(EglRenderer.FrameListener listener) {
        eglRenderer.removeFrameListener(listener);
    }

    public void setEnableHardwareScaler(boolean enabled) {
        ThreadUtils.checkIsOnMainThread();
        enableFixedSize = enabled;
        updateSurfaceSize();
    }

    public void setMirror(final boolean mirror) {
        eglRenderer.setMirror(mirror);
    }

    public void setScalingType(RendererCommon.ScalingType scalingType) {
        ThreadUtils.checkIsOnMainThread();
        videoLayoutMeasure.setScalingType(scalingType);
    }

    public void setScalingType(RendererCommon.ScalingType scalingTypeMatchOrientation, RendererCommon.ScalingType scalingTypeMismatchOrientation) {
        ThreadUtils.checkIsOnMainThread();
        videoLayoutMeasure.setScalingType(scalingTypeMatchOrientation, scalingTypeMismatchOrientation);
    }

    public void setFpsReduction(float fps) {
        eglRenderer.setFpsReduction(fps);
    }

    public void disableFpsReduction() {
        eglRenderer.disableFpsReduction();
    }

    public void pauseVideo() {
        eglRenderer.pauseVideo();
    }

//  @Override
//  public void renderFrame(VideoRenderer.I420Frame frame) {
//    updateFrameDimensionsAndReportEvents(frame);
//    eglRenderer.renderFrame(frame);
//  }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        ThreadUtils.checkIsOnMainThread();
        final Point size;
        synchronized (layoutLock) {
            size = videoLayoutMeasure.measure(widthSpec, heightSpec, rotatedFrameWidth, rotatedFrameHeight);
        }
        setMeasuredDimension(size.x, size.y);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        ThreadUtils.checkIsOnMainThread();
        eglRenderer.setLayoutAspectRatio((right - left) / (float) (bottom - top));
        updateSurfaceSize();
    }

    private void updateSurfaceSize() {
        ThreadUtils.checkIsOnMainThread();
        synchronized (layoutLock) {
            if (enableFixedSize && rotatedFrameWidth != 0 && rotatedFrameHeight != 0 && getWidth() != 0 && getHeight() != 0) {
                final float layoutAspectRatio = getWidth() / (float) getHeight();
                final float frameAspectRatio = rotatedFrameWidth / (float) rotatedFrameHeight;
                final int drawnFrameWidth;
                final int drawnFrameHeight;
                if (frameAspectRatio > layoutAspectRatio) {
                    drawnFrameWidth = (int) (rotatedFrameHeight * layoutAspectRatio);
                    drawnFrameHeight = rotatedFrameHeight;
                } else {
                    drawnFrameWidth = rotatedFrameWidth;
                    drawnFrameHeight = (int) (rotatedFrameWidth / layoutAspectRatio);
                }
                final int width = Math.min(getWidth(), drawnFrameWidth);
                final int height = Math.min(getHeight(), drawnFrameHeight);
                if (width != surfaceWidth || height != surfaceHeight) {
                    surfaceWidth = width;
                    surfaceHeight = height;
                    adjustAspectRatio(surfaceWidth, surfaceHeight);
                }
            } else {
                surfaceWidth = surfaceHeight = 0;
            }
        }
    }

    private void adjustAspectRatio(int videoWidth, int videoHeight) {
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        double aspectRatio = (double) videoHeight / videoWidth;

        int newWidth, newHeight;
        if (viewHeight > (int) (viewWidth * aspectRatio)) {
            newWidth = viewWidth;
            newHeight = (int) (viewWidth * aspectRatio);
        } else {
            newWidth = (int) (viewHeight / aspectRatio);
            newHeight = viewHeight;
        }
        int xoff = (viewWidth - newWidth) / 2;
        int yoff = (viewHeight - newHeight) / 2;
        Matrix txform = new Matrix();
        getTransform(txform);txform.setScale((float) newWidth / viewWidth, (float) newHeight / viewHeight);
        txform.postTranslate(xoff, yoff);
        setTransform(txform);
    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        ThreadUtils.checkIsOnMainThread();
        eglRenderer.createEglSurface(holder.getSurface());
        surfaceWidth = surfaceHeight = 0;
        updateSurfaceSize();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        ThreadUtils.checkIsOnMainThread();
        final CountDownLatch completionLatch = new CountDownLatch(1);
        eglRenderer.releaseEglSurface(completionLatch::countDown);
        ThreadUtils.awaitUninterruptibly(completionLatch);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        ThreadUtils.checkIsOnMainThread();
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        ThreadUtils.checkIsOnMainThread();
        eglRenderer.createEglSurface(new Surface(surfaceTexture));
        surfaceWidth = surfaceHeight = 0;
        updateSurfaceSize();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        ThreadUtils.checkIsOnMainThread();
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        ThreadUtils.checkIsOnMainThread();
        final CountDownLatch completionLatch = new CountDownLatch(1);
        eglRenderer.releaseEglSurface(completionLatch::countDown);
        ThreadUtils.awaitUninterruptibly(completionLatch);
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
//    Timber.d("TextureViewRenderer surface texture updated");
    }

    private String getResourceName() {
        try {
            return getResources().getResourceEntryName(getId()) + ": ";
        } catch (Resources.NotFoundException e) {
            return "";
        }
    }

    public void clearImage() {
        eglRenderer.clearImage();
    }

//  private void updateFrameDimensionsAndReportEvents(VideoRenderer.I420Frame frame) {
//    Timber.d("TextureViewRenderer update frame dimensions and report events");
//    synchronized (layoutLock) {
//      if (!isFirstFrameRendered) {
//        isFirstFrameRendered = true;
//        if (rendererEvents != null) {
//          rendererEvents.onFirstFrameRendered();
//        }
//      }
//      if (rotatedFrameWidth != frame.rotatedWidth() || rotatedFrameHeight != frame.rotatedHeight() || frameRotation != frame.rotationDegree) {
//        if (rendererEvents != null) {
//          rendererEvents.onFrameResolutionChanged(frame.width, frame.height, frame.rotationDegree);
//        }
//        rotatedFrameWidth = frame.rotatedWidth();
//        rotatedFrameHeight = frame.rotatedHeight();
//        frameRotation = frame.rotationDegree;
//        post(() -> {
//          updateSurfaceSize();
//          requestLayout();
//        });
//      }
//    }
//  }

    @Override
    public void onFirstFrameRendered() {
        if (rendererEvents != null) {
            rendererEvents.onFirstFrameRendered();
        }
    }

    @Override
    public void onFrameResolutionChanged(int videoWidth, int videoHeight, int rotation) {
        if (rendererEvents != null) {
            rendererEvents.onFrameResolutionChanged(videoWidth, videoHeight, rotation);
        }
        int rotatedWidth = rotation == 0 || rotation == 180 ? videoWidth : videoHeight;
        int rotatedHeight = rotation == 0 || rotation == 180 ? videoHeight : videoWidth;
        // run immediately if possible for ui thread tests
        postOrRun(() -> {
            rotatedFrameWidth = rotatedWidth;
            rotatedFrameHeight = rotatedHeight;
            updateSurfaceSize();
            requestLayout();
        });
    }

    private void postOrRun(Runnable r) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            r.run();
        } else {
            post(r);
        }
    }

    @Override
    public void onFrame(VideoFrame frame) {
//    Timber.d("TextureViewRenderer on frame");
        eglRenderer.onFrame(frame);
    }
}
