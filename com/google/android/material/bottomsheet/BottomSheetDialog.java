package com.google.android.material.bottomsheet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class BottomSheetDialog extends AppCompatDialog {
    private BottomSheetBehavior<FrameLayout> behavior;
    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback;
    boolean cancelable;
    private boolean canceledOnTouchOutside;
    private boolean canceledOnTouchOutsideSet;

    public BottomSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public BottomSheetDialog(@NonNull Context context, @StyleRes int i) {
        super(context, getThemeResId(context, i));
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
            /* class com.google.android.material.bottomsheet.BottomSheetDialog.AnonymousClass4 */

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(@NonNull View view, float f) {
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(@NonNull View view, int i) {
                if (i == 5) {
                    BottomSheetDialog.this.cancel();
                }
            }
        };
        supportRequestWindowFeature(1);
    }

    protected BottomSheetDialog(@NonNull Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
            /* class com.google.android.material.bottomsheet.BottomSheetDialog.AnonymousClass4 */

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(@NonNull View view, float f) {
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(@NonNull View view, int i) {
                if (i == 5) {
                    BottomSheetDialog.this.cancel();
                }
            }
        };
        supportRequestWindowFeature(1);
        this.cancelable = z;
    }

    @Override // android.app.Dialog, androidx.appcompat.app.AppCompatDialog
    public void setContentView(@LayoutRes int i) {
        super.setContentView(wrapInBottomSheet(i, null, null));
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                window.clearFlags(67108864);
                window.addFlags(Integer.MIN_VALUE);
            }
            window.setLayout(-1, -1);
        }
    }

    @Override // android.app.Dialog, androidx.appcompat.app.AppCompatDialog
    public void setContentView(View view) {
        super.setContentView(wrapInBottomSheet(0, view, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(wrapInBottomSheet(0, view, layoutParams));
    }

    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.cancelable != z) {
            this.cancelable = z;
            BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.behavior;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.setHideable(z);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.behavior;
        if (bottomSheetBehavior != null && bottomSheetBehavior.getState() == 5) {
            this.behavior.setState(4);
        }
    }

    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.cancelable) {
            this.cancelable = true;
        }
        this.canceledOnTouchOutside = z;
        this.canceledOnTouchOutsideSet = true;
    }

    private View wrapInBottomSheet(int i, View view, ViewGroup.LayoutParams layoutParams) {
        FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), R.layout.design_bottom_sheet_dialog, null);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) frameLayout.findViewById(R.id.coordinator);
        if (i != 0 && view == null) {
            view = getLayoutInflater().inflate(i, (ViewGroup) coordinatorLayout, false);
        }
        FrameLayout frameLayout2 = (FrameLayout) coordinatorLayout.findViewById(R.id.design_bottom_sheet);
        this.behavior = BottomSheetBehavior.from(frameLayout2);
        this.behavior.setBottomSheetCallback(this.bottomSheetCallback);
        this.behavior.setHideable(this.cancelable);
        if (layoutParams == null) {
            frameLayout2.addView(view);
        } else {
            frameLayout2.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(R.id.touch_outside).setOnClickListener(new View.OnClickListener() {
            /* class com.google.android.material.bottomsheet.BottomSheetDialog.AnonymousClass1 */

            public void onClick(View view) {
                if (BottomSheetDialog.this.cancelable && BottomSheetDialog.this.isShowing() && BottomSheetDialog.this.shouldWindowCloseOnTouchOutside()) {
                    BottomSheetDialog.this.cancel();
                }
            }
        });
        ViewCompat.setAccessibilityDelegate(frameLayout2, new AccessibilityDelegateCompat() {
            /* class com.google.android.material.bottomsheet.BottomSheetDialog.AnonymousClass2 */

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                if (BottomSheetDialog.this.cancelable) {
                    accessibilityNodeInfoCompat.addAction(1048576);
                    accessibilityNodeInfoCompat.setDismissable(true);
                    return;
                }
                accessibilityNodeInfoCompat.setDismissable(false);
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i != 1048576 || !BottomSheetDialog.this.cancelable) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                BottomSheetDialog.this.cancel();
                return true;
            }
        });
        frameLayout2.setOnTouchListener(new View.OnTouchListener() {
            /* class com.google.android.material.bottomsheet.BottomSheetDialog.AnonymousClass3 */

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        return frameLayout;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldWindowCloseOnTouchOutside() {
        if (!this.canceledOnTouchOutsideSet) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{16843611});
            this.canceledOnTouchOutside = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
            this.canceledOnTouchOutsideSet = true;
        }
        return this.canceledOnTouchOutside;
    }

    private static int getThemeResId(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.bottomSheetDialogTheme, typedValue, true)) {
            return typedValue.resourceId;
        }
        return R.style.Theme_Design_Light_BottomSheetDialog;
    }
}
