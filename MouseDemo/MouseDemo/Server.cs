using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MouseDemo
{
    class Server
    {
        
        public Server(int port)
        {
            IPAddress address = IPAddress.Any;
            TcpListener listener = new TcpListener(address, port);
            listener.Start();
            Console.WriteLine("Server started on " + listener.LocalEndpoint);
            new ClientHandler(listener);
            Console.Read();

        }
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
            new Server(9999);
        }
    }
}
