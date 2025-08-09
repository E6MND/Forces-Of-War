package org.xwalk.core;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

public class XWalkMixedResources extends Resources {
    private Resources mLibraryResource;

    private boolean isCalledInLibrary() {
        for (StackTraceElement stack : Thread.currentThread().getStackTrace()) {
            String className = stack.getClassName();
            if (className.startsWith("org.chromium") || className.startsWith("org.xwalk.core.internal")) {
                return true;
            }
            if (className.startsWith("org.xwalk.core") && !className.endsWith("XWalkMixedResources")) {
                return false;
            }
        }
        return false;
    }

    XWalkMixedResources(Resources base, Resources libraryResources) {
        super(base.getAssets(), base.getDisplayMetrics(), base.getConfiguration());
        this.mLibraryResource = libraryResources;
    }

    public CharSequence getText(int id) throws Resources.NotFoundException {
        boolean calledInLibrary = isCalledInLibrary();
        if (!calledInLibrary) {
            return super.getText(id);
        }
        try {
            return this.mLibraryResource.getText(id);
        } catch (Resources.NotFoundException e) {
            if (calledInLibrary) {
                return super.getText(id);
            }
            return this.mLibraryResource.getText(id);
        }
    }

    public XmlResourceParser getLayout(int id) throws Resources.NotFoundException {
        boolean calledInLibrary = isCalledInLibrary();
        if (!calledInLibrary) {
            return super.getLayout(id);
        }
        try {
            return this.mLibraryResource.getLayout(id);
        } catch (Resources.NotFoundException e) {
            if (calledInLibrary) {
                return super.getLayout(id);
            }
            return this.mLibraryResource.getLayout(id);
        }
    }

    public void getValue(int id, TypedValue outValue, boolean resolveRefs) {
        boolean calledInLibrary = isCalledInLibrary();
        if (calledInLibrary) {
            try {
                this.mLibraryResource.getValue(id, outValue, resolveRefs);
            } catch (Resources.NotFoundException e) {
                if (calledInLibrary) {
                    super.getValue(id, outValue, resolveRefs);
                } else {
                    this.mLibraryResource.getValue(id, outValue, resolveRefs);
                }
            }
        } else {
            super.getValue(id, outValue, resolveRefs);
        }
    }

    public void getValueForDensity(int id, int density, TypedValue outValue, boolean resolveRefs) {
        boolean calledInLibrary = isCalledInLibrary();
        if (calledInLibrary) {
            try {
                this.mLibraryResource.getValueForDensity(id, density, outValue, resolveRefs);
            } catch (Resources.NotFoundException e) {
                if (calledInLibrary) {
                    super.getValueForDensity(id, density, outValue, resolveRefs);
                } else {
                    this.mLibraryResource.getValueForDensity(id, density, outValue, resolveRefs);
                }
            }
        } else {
            super.getValueForDensity(id, density, outValue, resolveRefs);
        }
    }

    public int getIdentifier(String name, String defType, String defPackage) {
        if (isCalledInLibrary()) {
            int id = this.mLibraryResource.getIdentifier(name, defType, defPackage);
            return id != 0 ? id : super.getIdentifier(name, defType, defPackage);
        }
        int id2 = super.getIdentifier(name, defType, defPackage);
        return id2 != 0 ? id2 : this.mLibraryResource.getIdentifier(name, defType, defPackage);
    }

    public Drawable getDrawable(int id) {
        boolean calledInLibrary = isCalledInLibrary();
        if (!calledInLibrary) {
            return super.getDrawable(id);
        }
        try {
            return this.mLibraryResource.getDrawable(id);
        } catch (Resources.NotFoundException e) {
            if (calledInLibrary) {
                return super.getDrawable(id);
            }
            return this.mLibraryResource.getDrawable(id);
        }
    }
}
