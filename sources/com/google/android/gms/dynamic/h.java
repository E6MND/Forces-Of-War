package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.google.android.gms.dynamic.c;

public final class h extends c.a {
    private Fragment FS;

    private h(Fragment fragment) {
        this.FS = fragment;
    }

    public static h a(Fragment fragment) {
        if (fragment != null) {
            return new h(fragment);
        }
        return null;
    }

    public void c(d dVar) {
        this.FS.registerForContextMenu((View) e.e(dVar));
    }

    public void d(d dVar) {
        this.FS.unregisterForContextMenu((View) e.e(dVar));
    }

    public d gD() {
        return e.h(this.FS.getActivity());
    }

    public c gE() {
        return a(this.FS.getParentFragment());
    }

    public d gF() {
        return e.h(this.FS.getResources());
    }

    public c gG() {
        return a(this.FS.getTargetFragment());
    }

    public Bundle getArguments() {
        return this.FS.getArguments();
    }

    public int getId() {
        return this.FS.getId();
    }

    public boolean getRetainInstance() {
        return this.FS.getRetainInstance();
    }

    public String getTag() {
        return this.FS.getTag();
    }

    public int getTargetRequestCode() {
        return this.FS.getTargetRequestCode();
    }

    public boolean getUserVisibleHint() {
        return this.FS.getUserVisibleHint();
    }

    public d getView() {
        return e.h(this.FS.getView());
    }

    public boolean isAdded() {
        return this.FS.isAdded();
    }

    public boolean isDetached() {
        return this.FS.isDetached();
    }

    public boolean isHidden() {
        return this.FS.isHidden();
    }

    public boolean isInLayout() {
        return this.FS.isInLayout();
    }

    public boolean isRemoving() {
        return this.FS.isRemoving();
    }

    public boolean isResumed() {
        return this.FS.isResumed();
    }

    public boolean isVisible() {
        return this.FS.isVisible();
    }

    public void setHasOptionsMenu(boolean hasMenu) {
        this.FS.setHasOptionsMenu(hasMenu);
    }

    public void setMenuVisibility(boolean menuVisible) {
        this.FS.setMenuVisibility(menuVisible);
    }

    public void setRetainInstance(boolean retain) {
        this.FS.setRetainInstance(retain);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.FS.setUserVisibleHint(isVisibleToUser);
    }

    public void startActivity(Intent intent) {
        this.FS.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        this.FS.startActivityForResult(intent, requestCode);
    }
}
