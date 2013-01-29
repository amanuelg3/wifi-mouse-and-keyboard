using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace MouseDemo
{
    class Client
    {
        private Socket socket;
        private StreamReader reader;
        private StreamWriter writer;
        private NetworkStream stream;
        private byte[] data;
        public Client(Socket socket)
        {
            this.socket = socket;
            Console.WriteLine(socket.LocalEndPoint + " is Connected");
            stream = new NetworkStream(socket);
            reader = new StreamReader(stream);
            writer = new StreamWriter(stream);
            writer.AutoFlush = true;
            runThread();
        }

        public void sendMessage(String msg)
        {
            writer.WriteLine(msg);
        }

        private void runThread()
        {

            var bw = new BackgroundWorker();
            bw.DoWork += (sender, e) =>
            {
                Console.WriteLine("Thread Running");
                while (true)
                {
                    String s;
                    data = new byte[1024];
                    int recv = socket.Receive(data);
                    s = Encoding.ASCII.GetString(data, 0, recv);
                    Console.WriteLine(s);
                    DoWork.Exec(s);
                }
            };
            bw.RunWorkerCompleted += (sender, e) =>
            {
                stream.Close();
                socket.Close();
            };
            bw.RunWorkerAsync();
        }
    }
}
