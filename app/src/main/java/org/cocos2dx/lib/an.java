package org.cocos2dx.lib;

/* loaded from: classes.dex */
class an implements Runnable {
    final /* synthetic */ Cocos2dxGLSurfaceView a;
    private final /* synthetic */ int b;
    private final /* synthetic */ float c;
    private final /* synthetic */ float d;

    public an(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i, float f, float f2) {
        this.a = cocos2dxGLSurfaceView;
        this.b = i;
        this.c = f;
        this.d = f2;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxRenderer cocos2dxRenderer;
        cocos2dxRenderer = this.a.mRenderer;
        cocos2dxRenderer.handleActionUp(this.b, this.c, this.d);
    }
}
