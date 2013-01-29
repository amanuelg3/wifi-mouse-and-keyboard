using CoreAudioApi;
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
        private MMDevice device;
        public Server(int port)
        {
            IPAddress address = IPAddress.Any;
            TcpListener listener = new TcpListener(address, port);
            listener.Start();
            Console.WriteLine("Server started on " + listener.LocalEndpoint);
            new ClientHandler(listener);
            MMDeviceEnumerator DevEnum = new MMDeviceEnumerator();
            device = DevEnum.GetDefaultAudioEndpoint(EDataFlow.eRender, ERole.eMultimedia);
            //Mouse.Move(20436, 20436);
            Console.Read();

        }

        static void Main(string[] args)
        {
            new Server(7777);
        }
    }
}
