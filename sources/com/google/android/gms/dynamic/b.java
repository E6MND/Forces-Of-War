package com.google.android.gms.dynamic;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.dynamic.c;

public final class b extends c.a {
    private Fragment Mg;

    private b(Fragment fragment) {
        this.Mg = fragment;
    }

    public static b a(Fragment fragment) {
        if (fragment != null) {
            return new b(fragment);
        }
        return null;
    }

    public void c(d dVar) {
        this.Mg.registerForContextMenu((View) e.e(dVar));
    }

    public void d(d dVar) {
        this.Mg.unregisterForContextMenu((View) e.e(dVar));
    }

    public d gD() {
        return e.h(this.Mg.getActivity());
    }

    public c gE() {
        return a(this.Mg.getParentFragment());
    }

    public d gF() {
        return e.h(this.Mg.getResources());
    }

    public c gG() {
        return a(this.Mg.getTargetFragment());
    }

    public Bundle getArguments() {
        return this.Mg.getArguments();
    }

    public int getId() {
        return this.Mg.getId();
    }

    public boolean getRetainInstance() {
        return this.Mg.getRetainInstance();
    }

    public String getTag() {
        return this.Mg.getTag();
    }

    public int getTargetRequestCode() {
        return this.Mg.getTargetRequestCode();
    }

    public boolean getUserVisibleHint() {
        return this.Mg.getUserVisibleHint();
    }

    public d getView() {
        return e.h(this.Mg.getView());
    }

    public boolean isAdded() {
        return this.Mg.isAdded();
    }

    public boolean isDetached() {
        return this.Mg.isDetached();
    }

    public boolean isHidden() {
        return this.Mg.isHidden();
    }

    public boolean isInLayout() {
        return this.Mg.isInLayout();
    }

    public boolean isRemoving() {
        return this.Mg.isRemoving();
    }

    public boolean isResumed() {
        return this.Mg.isResumed();
    }

    public boolean isVisible() {
        return this.Mg.isVisible();
    }

    public void setHasOptionsMenu(boolean hasMenu) {
        this.Mg.setHasOptionsMenu(hasMenu);
    }

    public void setMenuVisibility(boolean menuVisible) {
        this.Mg.setMenuVisibility(menuVisible);
    }

    public void setRetainInstance(boolean retain) {
        this.Mg.setRetainInstance(retain);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.Mg.setUserVisibleHint(isVisibleToUser);
    }

    public void startActivity(Intent intent) {
        this.Mg.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        this.Mg.startActivityForResult(intent, requestCode);
    }
}
