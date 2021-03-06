﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MouseDemo
{
    public class Mouse
    {
        public struct Point
        {
            public int X;
            public int Y;
        }

        [DllImport("user32.dll")]
        private static extern void mouse_event(MouseEventFlags flags, uint dx, uint dy, int data, int extraInfo);
        [DllImport("user32.dll")]
        static extern bool SetCursorPos(int X, int Y);
        [Flags]
        private enum MouseEventFlags : uint
        {
            LEFTDOWN = 0x00000002,
            LEFTUP = 0x00000004,
            MIDDLEDOWN = 0x00000020,
            MIDDLEUP = 0x00000040,
            MOVE = 0x00000001,
            ABSOLUTE = 0x00008000,
            RIGHTDOWN = 0x00000008,
            RIGHTUP = 0x00000010,
            WHEEL = 0x00000800,
            XDOWN = 0x00000080,
            XUP = 0x00000100
        }

        private static void Action(MouseEventFlags Flags, uint X, uint Y, int data = 0)
        {
            mouse_event(Flags, X, Y, data, 0);
        }

        public static void Move(uint X, uint Y)
        {
            Console.WriteLine(X + "x" + Y);
            Action(MouseEventFlags.MOVE | MouseEventFlags.ABSOLUTE, X, Y);
            Console.WriteLine(Cursor.Position);
        }

        public static void LeftDown(uint X = 0, uint Y = 0)
        {
            Action(MouseEventFlags.MOVE | MouseEventFlags.LEFTDOWN, X, Y);
        }

        public static void LeftUp(uint X = 0, uint Y = 0)
        {
            Action(MouseEventFlags.MOVE | MouseEventFlags.LEFTUP, X, Y);
        }

        public static void LeftClick(uint X = 0, uint Y = 0)
        {
            Action(MouseEventFlags.MOVE | MouseEventFlags.LEFTDOWN | MouseEventFlags.LEFTUP, X, Y);
        }

        public static void RightDown(uint X = 0, uint Y = 0)
        {
            Action(MouseEventFlags.MOVE | MouseEventFlags.RIGHTDOWN, X, Y);
        }

        public static void RightUp(uint X = 0, uint Y = 0)
        {
            Action(MouseEventFlags.MOVE | MouseEventFlags.RIGHTUP, X, Y);
        }

        public static void RightClick(uint X = 0, uint Y = 0)
        {
            Action(MouseEventFlags.MOVE | MouseEventFlags.RIGHTDOWN | MouseEventFlags.RIGHTUP, X, Y);
        }

        public static void Wheel(int amount)
        {
            Action(MouseEventFlags.WHEEL, 0, 0, amount);
        }

        private static Point Previous;

        public static void SmoothMove(uint X, uint Y, int TotalTime = 400, int StepsCount = 20)
        {
            var dx = (int)Math.Round((double)(X - Previous.X) / StepsCount);
            var dy = (int)Math.Round((double)(Y - Previous.Y) / StepsCount);
            var dt = (int)(TotalTime / StepsCount);
            while (--StepsCount > 0)
            {
                Mouse.Move((uint)(Previous.X += dx), (uint)(Previous.Y += dy));
                Thread.Sleep(dt);
            }
            Mouse.Move((uint)(Previous.X = (int)X), (uint)(Previous.Y = (int)Y));
        }
    }
}
