# CSE116-Fractal-Generator
Java-based desktop app that can generate 4 different fractal images in 4 different color schemes, for a total of 16 image combinations. Each of these images can be zoomed into repeatedly, demonstrating the self-similar nature of the patterns. The app is multi-threaded to handle the intense mathematical calculations necessary for these image generations and manipulations.

# Features
- Four different fractals: Julia, Mandelbrot, Burning Ship, Multibrot
- Four different color schemes: Rainbow, Blue, Grey, Green
- User-configurable: the user can choose the escape distance and escape time used to calculate the fractals, and also modify the number of SwingWorker threads
- Zoom: fractals wouldn't be nearly as interesting if you couldn't zoom in and observe their nested-similarity nature. Use the reset zoom option in the toolbar to return to normal

</br>
</br>

![Fractal Generator: Mandelbrot](/img/demo1.png)
![Fractal Generator: Julia zoomed in](/img/demo2.png)
![Fractal Generator: Julia with options menu](/img/demo3.png)

