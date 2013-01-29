using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MouseDemo
{
    class DoWork
    {
        public static Rectangle screenBounds = System.Windows.Forms.Screen.PrimaryScreen.Bounds;
        public static void Exec(String str)
        {
            var strArr = str.Split('|');
            if (strArr.Length > 2)
            {
                if (strArr[1].Trim().ToLower().Equals("move"))
                {
                    
                }
            }
        }

        public static void moveMouse(double x, double y)
        {
            x *= 65.535 / screenBounds.Width; ;
            x += Cursor.Position.X * 65535 / screenBounds.Width;
            y *= 65.535 / screenBounds.Height;
            y += Cursor.Position.Y * 65535 / screenBounds.Height;
            Mouse.SmoothMove((uint)x, (uint)y);
        }
    }
}
