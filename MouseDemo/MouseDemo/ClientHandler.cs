using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace MouseDemo
{
    class ClientHandler
    {
        private TcpListener listener;
        private int clientCout;
        public ClientHandler(TcpListener listener)
        {
            this.listener = listener;
            clientCout = 0;
            runThread();
        }

        private void runThread()
        {
            var bw = new BackgroundWorker();
            bw.DoWork += (sender, e) =>
            {
                Socket socket = null;
                while (true)
                {
                    socket = listener.AcceptSocket();
                    new Client(socket);
                }
            };
            bw.RunWorkerAsync();
        }

    }
}
