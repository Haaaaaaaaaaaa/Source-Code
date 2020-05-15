using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace GuofuClients
{
    //声明委托，用于传递是否取消下载的消息
    public delegate void TransfDelegate(bool isCancel);

    public partial class ProcessBarDiage : Form
    {
        // 是否取消了上传
        private bool isCancel = false;


        public bool IsCancel
        {
            get { return isCancel; }
        }

        public ProcessBarDiage()
        {
            InitializeComponent();
        }

        public void setMaxVale(long mum)                            // public不安全，设置进度条的最大值（有一定的限制）
        {
            this.progressBar1.Maximum = Convert.ToInt32(mum / 16);
        }

        public void setValue(long value)                            // 设置进度条当前的值
        {
            this.progressBar1.Value = Convert.ToInt32(value / 16);
        }

        public void setLabel1(string text)                          // 设置提示信息
        {
            this.label1.Text = text;
        }


        private void ProcessBarDiage_Load(object sender, EventArgs e)                  // 窗体载入时的操作
        {
            //progressBar1.Value = 20;
            //percent.Text = $"{10}%";
            ////this.backgroundWorker1.RunWorkerAsync();
        }

        private void ProcessBarDiage_FormClosed(object sender, FormClosedEventArgs e)  // 窗体关闭检查是否上传完
        {
            if (this.progressBar1.Value < this.progressBar1.Maximum) // 未下载完，提前关闭了
                isCancel = true;
            TransfEvent(this.isCancel);                             // 将标记传递过去
        }

        // 声明窗体传值事件
        public event TransfDelegate TransfEvent;
        private void button1_Click_1(object sender, EventArgs e)
        {
            isCancel = true;                                        // 取消了上传
            TransfEvent(this.isCancel);                             // 将标记传递过去
            this.Close();
            // Cancel the asynchronous operation.
        }


        // 设置百分比；
        public void set_ValuePercent(int x)
        {
            progressBar1.Value = x;
            percent.Text = $"{x}%";
        }

        private void backgroundWorker1_DoWork(object sender, DoWorkEventArgs e)
        {

        }

        private void backgroundWorker1_ProgressChanged(object sender, ProgressChangedEventArgs e)
        {

        }

        private void backgroundWorker1_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {

        }

        private void timer1_Tick(object sender, EventArgs e)            // 使用定时器,关闭
        {
            progressBar1.Value = GuofuClient.highestPercentageReached;
            percent.Text = $"{GuofuClient.highestPercentageReached}%";
            if (progressBar1.Value == 100)
            {
                this.Close();
                TransfEvent(this.isCancel);                             // 将标记传递过去
            }
        }



    }
}
