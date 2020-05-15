using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace TCPManager
{
    public partial class ProcessBar : Form
    {
        private bool isCancel = false;

        public bool IsCancel
        {
            get { return isCancel; }
        }

        public ProcessBar()
        {
            InitializeComponent();
        }

        public void setMaxVale(long mum)                            // public不安全，设置进度条的最大值（有一定的限制）
        {
            this.progressBar1.Maximum = Convert.ToInt32(mum/16);
        }

        public void setValue(long value)                            // 设置进度条当前的值
        {
            this.progressBar1.Value = Convert.ToInt32(value/16);
        }

        public void setLabel1(string text)                          // 设置提示信息
        {
            this.label1.Text = text;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            isCancel = true;
            this.Close();
        }
    }
}
