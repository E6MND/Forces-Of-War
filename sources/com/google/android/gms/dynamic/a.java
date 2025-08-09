package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class a<T extends LifecycleDelegate> {
    /* access modifiers changed from: private */
    public T LU;
    /* access modifiers changed from: private */
    public Bundle LV;
    /* access modifiers changed from: private */
    public LinkedList<C0031a> LW;
    private final f<T> LX = new f<T>() {
        public void a(T t) {
            LifecycleDelegate unused = a.this.LU = t;
            Iterator it = a.this.LW.iterator();
            while (it.hasNext()) {
                ((C0031a) it.next()).b(a.this.LU);
            }
            a.this.LW.clear();
            Bundle unused2 = a.this.LV = null;
        }
    };

    /* renamed from: com.google.android.gms.dynamic.a$a  reason: collision with other inner class name */
    private interface C0031a {
        void b(LifecycleDelegate lifecycleDelegate);

        int getState();
    }

    private void a(Bundle bundle, C0031a aVar) {
        if (this.LU != null) {
            aVar.b(this.LU);
            return;
        }
        if (this.LW == null) {
            this.LW = new LinkedList<>();
        }
        this.LW.add(aVar);
        if (bundle != null) {
            if (this.LV == null) {
                this.LV = (Bundle) bundle.clone();
            } else {
                this.LV.putAll(bundle);
            }
        }
        a(this.LX);
    }

    public static void b(FrameLayout frameLayout) {
        final Context context = frameLayout.getContext();
        final int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        String d = GooglePlayServicesUtil.d(context, isGooglePlayServicesAvailable);
        String e = GooglePlayServicesUtil.e(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(d);
        linearLayout.addView(textView);
        if (e != null) {
            Button button = new Button(context);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(e);
            linearLayout.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    context.startActivity(GooglePlayServicesUtil.c(context, isGooglePlayServicesAvailable));
                }
            });
        }
    }

    private void ca(int i) {
        while (!this.LW.isEmpty() && this.LW.getLast().getState() >= i) {
            this.LW.removeLast();
        }
    }

    /* access modifiers changed from: protected */
    public void a(FrameLayout frameLayout) {
        b(frameLayout);
    }

    /* access modifiers changed from: protected */
    public abstract void a(f<T> fVar);

    public T gC() {
        return this.LU;
    }

    public void onCreate(final Bundle savedInstanceState) {
        a(savedInstanceState, (C0031a) new C0031a() {
            public void b(LifecycleDelegate lifecycleDelegate) {
                a.this.LU.onCreate(savedInstanceState);
            }

            public int getState() {
                return 1;
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FrameLayout frameLayout = new FrameLayout(inflater.getContext());
        final LayoutInflater layoutInflater = inflater;
        final ViewGroup viewGroup = container;
        final Bundle bundle = savedInstanceState;
        a(savedInstanceState, (C0031a) new C0031a() {
            public void b(LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(a.this.LU.onCreateView(layoutInflater, viewGroup, bundle));
            }

            public int getState() {
                return 2;
            }
        });
        if (this.LU == null) {
            a(frameLayout);
        }
        return frameLayout;
    }

    public void onDestroy() {
        if (this.LU != null) {
            this.LU.onDestroy();
        } else {
            ca(1);
        }
    }

    public void onDestroyView() {
        if (this.LU != null) {
            this.LU.onDestroyView();
        } else {
            ca(2);
        }
    }

    public void onInflate(final Activity activity, final Bundle attrs, final Bundle savedInstanceState) {
        a(savedInstanceState, (C0031a) new C0031a() {
            public void b(LifecycleDelegate lifecycleDelegate) {
                a.this.LU.onInflate(activity, attrs, savedInstanceState);
            }

            public int getState() {
                return 0;
            }
        });
    }

    public void onLowMemory() {
        if (this.LU != null) {
            this.LU.onLowMemory();
        }
    }

    public void onPause() {
        if (this.LU != null) {
            this.LU.onPause();
        } else {
            ca(5);
        }
    }

    public void onResume() {
        a((Bundle) null, (C0031a) new C0031a() {
            public void b(LifecycleDelegate lifecycleDelegate) {
                a.this.LU.onResume();
            }

            public int getState() {
                return 5;
            }
        });
    }

    public void onSaveInstanceState(Bundle outState) {
        if (this.LU != null) {
            this.LU.onSaveInstanceState(outState);
        } else if (this.LV != null) {
            outState.putAll(this.LV);
        }
    }

    public void onStart() {
        a((Bundle) null, (C0031a) new C0031a() {
            public void b(LifecycleDelegate lifecycleDelegate) {
                a.this.LU.onStart();
            }

            public int getState() {
                return 4;
            }
        });
    }

    public void onStop() {
        if (this.LU != null) {
            this.LU.onStop();
        } else {
            ca(4);
        }
    }
}
