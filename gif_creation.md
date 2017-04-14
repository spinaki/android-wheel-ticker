# Steps to Create GIF Animation of Android Apps
* Screen capture and create video
~~~~ shell
adb shell screenrecord --verbose /sdcard/demo.mp4
~~~~
* Copy video to local
~~~~ shell
adb pull /sdcard/demo.mp4 ./demo.mp4
~~~~
* Convert mp4 to gif
~~~~ shell
ffmpeg -t <TIME_INTERVAL> -ss 00:00:01 -i <INPUT>.mp4 <OUTPUT>.gif
~~~~
* Crop the gif to desired size
~~~~ shell
gifsicle --crop x,y+WIDTHxHEIGHT --output  <OUTPUT>.gif <INPUT>.gif
~~~~
More details [here](http://stackoverflow.com/questions/14036765/how-do-i-crop-an-animated-gif-using-imagemagick)
* Compress the GIF image
    * Using ImageMagick. Details [here](http://stackoverflow.com/questions/8578926/how-can-i-compress-the-size-of-gif-images-with-imagemagick)
    ~~~~
    convert INPUT.gif -fuzz 30% -layers Optimize OUTPUT.gif
    ~~~~
    * Using Gifsicle. Details [here](https://graphicdesign.stackexchange.com/questions/20908/how-to-remove-every-second-frame-from-an-animated-gif/20937#20937)
    ~~~~
    gifsicle -d DELAY_TIME  -U INPUT.gif `seq -f "#%g" 0 2 99`  -O2 -o OUTPUT.gif
    ~~~~