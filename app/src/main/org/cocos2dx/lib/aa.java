package org.cocos2dx.lib;

/* loaded from: classes.dex */
class aa implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxActivity.fbDelegate_.performQueuedFBAction();
    }
}
