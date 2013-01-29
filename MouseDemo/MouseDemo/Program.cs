using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MouseDemo
{
    class Program
    {
        private Rectangle screenBounds = System.Windows.Forms.Screen.PrimaryScreen.Bounds;
        public Program()
        {
            Thread.Sleep(1500);
            moveMouse(100000, 100000);

        }
        //public void moveMouse(int x, int y)
        //{
        //    var bw = new BackgroundWorker();

        //    int a = x, b = y, c, max, min, xInc = 1, yInc = 1;
        //    if (x < 0)
        //    {
        //        a = -x;
        //        xInc = -1;
        //    }
        //    if (y < 0)
        //    {
        //        b = -y;
        //        yInc = -1;
        //    }
        //    max = Math.Max(a, b);
        //    min = Math.Min(a, b);
        //    c = (min == 0) ? 1 : max / min;

        //    for (var i = 1; i <= max; i++)
        //    {
        //        Thread.Sleep(1);
        //        if (a >= b)
        //        {
        //            MouseSimulator.X += xInc;
        //            if (i % c == 0)
        //                MouseSimulator.Y += yInc;
        //        }
        //        else if (b > a)
        //        {
        //            MouseSimulator.Y += yInc;
        //            if (i % c == 0)
        //                MouseSimulator.X += xInc;
        //        }
        //    }

        //}

        public void moveMouse(double x, double y)
        {
            x *= 65.535 / screenBounds.Width; ;
            x += Cursor.Position.X * 65535 / screenBounds.Width;
            y *= 65.535 / screenBounds.Height;
            y += Cursor.Position.Y * 65535 / screenBounds.Height;
            Mouse.SmoothMove((uint)x, (uint)y);
        }


       


        static void Main(string[] args)
        {
            new Program();
        }
    }
}
