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
compile 'xyz.pinaki.android:wheelticker:1.0.1'
```
The main view class is `Odometer`. You may add the widget in your layout file as follows:
~~~xml
<xyz.pinaki.android.wheelticker.Odometer
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            custom:num_digits="4"
            custom:digit_size="40"
            android:id="@+id/frequency_counter"/>
~~~
The `num_digits` is the number of digits that will show up in the view.
You can send data to the view using an implementation of the abstract class `OdometerAdapter`.
* You will need to override the method `getNumber()` to return the number you intend to render in the widget.
* Set the adapter to the view using `Odometer::setAdapter`.
* Whenever something changes, make sure the `getNumber` methos returns the changed number and invoke `notifyDataSetChanged`

See example usage in where a random integer is sent to the widget each time randomize is called.
~~~~
xyz.pinaki.android.wheelticker.demo.MainActivity
~~~~

This is how it will look like in full screen:

![](https://github.com/spinaki/android-wheel-ticker/blob/master/assets/demo.gif)