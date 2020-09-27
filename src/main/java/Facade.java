//
//import java.util.*;
//
//class Buffer
//{
//    private char [] characters;
//    private int lineWidth;
//
//    public Buffer(int lineHeight, int lineWidth)
//    {
//        this.lineWidth =lineWidth;
//        this.characters = new char[lineHeight*lineWidth];
//    }
//
//    public char charAt(int x, int y)
//    {
//        return characters[y*lineWidth+x];
//    }
//}
//class Viewport
//{
//    private final Buffer buffer;
//    private final int width;
//    private final int height;
//    private final int offsetX;
//    private final int offsetY;
//
//    public Viewport(Buffer br, int w, int h, int oX, int oY)
//    {
//        buffer = br;
//        width  = w;
//        height = h;
//        offsetX = oX;
//        offsetY = oY;
//    }
//
//    public char charAt(int x, int y)
//    {
//        return buffer.charAt(x+offsetX, y+offsetY);
//    }
//
//}
//
//
//class Console
//{ // simply keeping list of active viewports
//    private List<Viewport> viewports = new ArrayList<>();
//    int widith, height;
//
//    public void addViewports(List<Viewport> viewport) {
//        this.viewports.add(viewport);
//    }
//
//    public static Console newConsole (int width, int height) { // a factory method to create an object
//        Buffer buffer = new Buffer(width, height); // does all the dirty initialization work
//        Viewport viewport = new Viewport(buffer, width, height, 0,0);
//        Console console = new Console(width, height);
//        console.addViewports(viewport);
//        return console;
//    }
//    public void render()
//    {
//        for (int y=0;y<height;y++)
//        {
//            for (int x =0; x<widith; x++)
//            {
//                // print char from each viewport
//                for (Viewport vp: viewports)
//                {
//                    System.out.print(vp.charAt(x,y));
//                }
//                System.out.println();
//            }
//        }
//    }
//}
//
//public class Facade {
//
//    public static void main2(String[] args) {
//        Buffer buffer = new Buffer(30,20);
//        Viewport viewport = new Viewport(buffer, 30, 20, 0,0);
//        Console console = new Console(30, 20);
//        console.addViewports(viewport);
//        console.render();// finally do it! after so much work of initialization.
//        //facade after so much level of subsystem
//    }
//
//    public static void main(String[] args) {
//        Console console = Console.newConsole(30,20); // FACADE provides a simplified API
//        // optionally for power users - do some controls - expose internals for exposing micro internal
//        console.render();
//    }
//}
