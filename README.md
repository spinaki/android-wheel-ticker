# android-wheel-ticker
![](https://github.com/spinaki/android-wheel-ticker/blob/master/assets/compress.gif)
# What is this
Wheel Ticker is a spinning UI Widget similar to an Odometer. It may be used to display changing
numbers / text, for instance in real time dashboard widgets. The behavior is similar to how an odometer in a car
animates itself. You may invoke `odometer.spinTo(n)` where `n` is an `Integer` and the widget will smoothly animate
itself.

Currently, it supports only numbers. But other ascii characters will be added soon.

Animation is similar to this [Odometer gadget](http://kevindion.com/2010/12/android-odometer-ui-tutorial-part-1/)
However, implementation is significantly different.
#How to use this
Add the library dependency to your `build.gradle`.
```groovy
compile 'xyz.pinaki.android:wheelticker:1.0.0'
```
See example usage in
~~~~
xyz.pinaki.android.wheelticker.demo.MainActivity
~~~~

This is how it will look like in full screen:

![](![](https://github.com/spinaki/android-wheel-ticker/blob/master/assets/demo.gif))