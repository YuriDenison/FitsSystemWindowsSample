# FitsSystemWindowsSample
The sample Android project illustrating fitsSystemWindows weird bug.

Set Root fragment layout as:
```xml
<FrameLayout ...
    android:fitsSystemWindows="true">
....
</FrameLayout>
```
Then all added fragments via fragmentTransaction.add() will ignore fitsSystemWindows flag inside them.

The solutions is to set Root fragment layout as 
```xml
<android.support.design.widget.CoordinatorLayout ...
    android:fitsSystemWindows="true">
.....    
</android.support.design.widget.CoordinatorLayout>
```
