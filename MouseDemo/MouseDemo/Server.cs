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

        static void Main(string[] args)
        {
            new Server(9999);
        }
    }
}
