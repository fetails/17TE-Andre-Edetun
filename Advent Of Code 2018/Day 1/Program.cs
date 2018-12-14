using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;


class Program
{
    static void Main(string[] args)
    {
        int freq = 0;
        int lastFreq = 0;
        bool hit = false;
        try
        {
            string text;
            var fileStream = new FileStream(@"c:\test\input.txt", FileMode.Open, FileAccess.Read);
            using (var streamReader = new StreamReader(fileStream, Encoding.UTF8))
            {
                Console.WriteLine("File loaded!");
                Thread.Sleep(500);
                Console.Clear();

                String line;
                while ((line = streamReader.ReadLine()) != null)
                {
                    freq += Int32.Parse(line);
                    freq = lastFreq;
                }

                if(lastFreq == freq && !hit)
                {
                    Console.WriteLine("Dupplicate: " + lastFreq);
                    hit = true;
                }
                
                Console.WriteLine("Frequency: " + freq);
            }
        }
        catch (Exception e) // simpel hantering av errors
        {
            Console.WriteLine("Error!");
        }

        Thread.Sleep(10000000);
    }
}

