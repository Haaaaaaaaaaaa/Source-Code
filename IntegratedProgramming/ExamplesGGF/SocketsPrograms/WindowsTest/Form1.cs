using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Threading;
using GuofuClients;
using TCPManager;

namespace WindowsTest
{
    public partial class Form1 : Form
    {
        // 定义一个代理，用于更新ProgressBar的值（Value）及在执行方法的时候，返回方法的处理信息。
        private delegate void SetPos(int ipos, string vinfo);

        // 进度条值更新函数（参数必须跟声明的代理参数一样）
        private void SetTextMesssage(int ipos,string vinfo)
        {
            if(this.InvokeRequired)
            {
                SetPos setPos = new SetPos(SetTextMesssage);
                this.Invoke(setPos, new object[] { ipos, vinfo });
            }
            else
            {
                this.label1.Text = ipos.ToString() + "/100";
                this.progressBar1.Value = Convert.ToInt32(ipos);
                this.textBox1.AppendText(vinfo);
            }
        }

      
        
        // 函数实现
        private void button1_Click(object sender, EventArgs e)
        {
            progressBar1.Maximum = 10000;
            Thread th = new Thread(new ThreadStart(SleepT));
            //th.IsBackground = true;
            th.Start();
            //newForm();//创建一个新窗体         
        }

        // 新的线程执行函数
        private void SleepT()
        {
            for (int i = 0; i < 10000; i++)
            {
                //Thread.Sleep(10);
                SetTextMesssage(i+1, i.ToString() + "\r\n");
            }
        }

        public Form1()
        {
            InitializeComponent();
        }

        private void newForm()
        {
            // Create a new instance of the form.
            Form form1 = new Form();
            // Create two buttons to use as the accept and cancel buttons.
            Button button1 = new Button();
            Button button2 = new Button();
            ProgressBar progress = new ProgressBar();


            // set ProgressBar
            progress.Maximum = 100;


            // Set the text of button1 to "OK".
            button1.Text = "OK";
            // Set the position of the button on the form.
            button1.Location = new Point(10, 10);
            // Set the text of button2 to "Cancel".
            button2.Text = "Cancel";
            // Set the position of the button based on the location of button1.
            button2.Location
               = new Point(button1.Left, button1.Height + button1.Top + 10);
            // Set the caption bar text of the form.   
            form1.Text = "My Dialog Box";
            // Display a help button on the form.
            form1.HelpButton = true;

            // Define the border style of the form to a dialog box.
            form1.FormBorderStyle = FormBorderStyle.FixedDialog;
            // Set the MaximizeBox to false to remove the maximize box.
            form1.MaximizeBox = false;
            // Set the MinimizeBox to false to remove the minimize box.
            form1.MinimizeBox = false;
            // Set the accept button of the form to button1.
            form1.AcceptButton = button1;
            // Set the cancel button of the form to button2.
            form1.CancelButton = button2;
            // Set the start position of the form to the center of the screen.
            form1.StartPosition = FormStartPosition.CenterScreen;

            // Add button1 to the form.
            form1.Controls.Add(button1);
            // Add button2 to the form.
            form1.Controls.Add(button2);

            // Display the form as a modal dialog box.
            form1.Show();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            //ProcessBarDiage pro = new ProcessBarDiage();
            //pro.Show();
            ProcessBar processBar = new ProcessBar();
            processBar.Show();
             Thread th = new Thread(new ThreadStart(SleepT));
            th.IsBackground = true;
            th.Start();
        }
    }
}
