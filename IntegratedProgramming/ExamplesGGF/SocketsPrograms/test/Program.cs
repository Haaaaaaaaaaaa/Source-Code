
// 数组切片处理
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace test
{
    class Program
    {
        static void Main(string[] args)
        {
            byte[] data = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
            byte[] tt = data.Skip(4).Take(3).ToArray();
            foreach (var te in tt)
            {
                Console.Write($"{te} ");
            }
            Console.WriteLine();
            Console.Read();
        }
    }
}

// 进度条设计


