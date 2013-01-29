using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MouseDemo
{
    class DoWork
    {
        [DllImport("user32.dll")]
        static extern bool SetCursorPos(int X, int Y);
        private const String LEFT_MOUSE_CLICK = "1";
        private const String LEFT_MOUSE_DOWN = "2";
        private const String LEFT_MOUSE_UP = "3";
        private const String RIGHT_MOUSE_CLICK = "4";
        private const String RIGHT_MOUSE_DOWN = "5";
        private const String RIGHT_MOUSE_UP = "6";
        private const String MOUSE_MOVE = "7";
        private const String MOUSE_DOUBLE_CLICK = "8";
        private const String MOUSE_WHEEL = "9";
        private static bool canMove = true;


        public static Rectangle screenBounds = System.Windows.Forms.Screen.PrimaryScreen.Bounds;
        public static void Exec(String str)
        {
            var strArr = str.Split('|');
            if (strArr.Length > 2)
            {
                switch (strArr[1].Trim())
                {
                    case LEFT_MOUSE_CLICK:
                        Mouse.LeftClick();
                        break;
                    case RIGHT_MOUSE_CLICK:
                        Mouse.RightClick();
                        break;
                    case MOUSE_MOVE:

                        double x = Convert.ToDouble(strArr[2]) * 5;
                        double y = Convert.ToDouble(strArr[3]) * 5;
                        x += Cursor.Position.X;
                        y += Cursor.Position.Y;
                        TimeSpan delayt = new TimeSpan(0, 0, 0, 0, 25);
                        if (canMove)
                            LinearSmoothMove(new Point((int)x, (int)y), delayt);


                        break;
                    case LEFT_MOUSE_DOWN:
                        Mouse.LeftDown();
                        break;
                    case LEFT_MOUSE_UP:
                        Mouse.LeftUp();
                        break;
                    case MOUSE_WHEEL:
                        double amount = Convert.ToDouble(strArr[2]) * 50;
                        Mouse.Wheel((int)amount);
                        break;

                }
            }
        }

        public static void moveMouse(double x, double y)
        {

            x *= 65.535 / screenBounds.Width; ;
            x += Cursor.Position.X * 65535 / screenBounds.Width;
            y *= 65.535 / screenBounds.Height;
            y += Cursor.Position.Y * 65535 / screenBounds.Height;
            Mouse.Move((uint)x, (uint)y);
        }

        public static void LinearSmoothMove(Point newPosition, TimeSpan duration)
        {
            canMove = false;
            Point start = Cursor.Position;
            int sleep = 10;
            //PointF iterPoint = start;
            // Find the vector between start and newPosition   
            double deltaX = newPosition.X - start.X;
            double deltaY = newPosition.Y - start.Y;
            // start a timer    
            Stopwatch stopwatch = new Stopwatch();
            stopwatch.Start();
            double timeFraction = 0.0;
            do
            {
                timeFraction = (double)stopwatch.Elapsed.Ticks / duration.Ticks;
                if (timeFraction > 1.0)
                    timeFraction = 1.0;
                PointF curPoint = new PointF((float)(start.X + timeFraction * deltaX), (float)(start.Y + timeFraction * deltaY));
                SetCursorPos(Point.Round(curPoint).X, Point.Round(curPoint).Y);
                Thread.Sleep(sleep);
            } while (timeFraction < 1.0);
            canMove = true;
        }
    }
}
