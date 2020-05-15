namespace GuofuClients
{
    partial class GuofuClient
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.portText = new System.Windows.Forms.TextBox();
            this.ipText = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.ConnectBtn = new System.Windows.Forms.Button();
            this.fileLab = new System.Windows.Forms.Label();
            this.splitContainer2 = new System.Windows.Forms.SplitContainer();
            this.filesView = new System.Windows.Forms.ListBox();
            this.exitBtn = new System.Windows.Forms.Button();
            this.downBtn = new System.Windows.Forms.Button();
            this.Sendbtn = new System.Windows.Forms.Button();
            this.saveFile = new System.Windows.Forms.SaveFileDialog();
            this.upLoad_fileWorker1 = new System.ComponentModel.BackgroundWorker();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer2)).BeginInit();
            this.splitContainer2.Panel1.SuspendLayout();
            this.splitContainer2.Panel2.SuspendLayout();
            this.splitContainer2.SuspendLayout();
            this.SuspendLayout();
            // 
            // splitContainer1
            // 
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.FixedPanel = System.Windows.Forms.FixedPanel.Panel1;
            this.splitContainer1.IsSplitterFixed = true;
            this.splitContainer1.Location = new System.Drawing.Point(0, 0);
            this.splitContainer1.Margin = new System.Windows.Forms.Padding(2);
            this.splitContainer1.Name = "splitContainer1";
            this.splitContainer1.Orientation = System.Windows.Forms.Orientation.Horizontal;
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.portText);
            this.splitContainer1.Panel1.Controls.Add(this.ipText);
            this.splitContainer1.Panel1.Controls.Add(this.label2);
            this.splitContainer1.Panel1.Controls.Add(this.label1);
            this.splitContainer1.Panel1.Controls.Add(this.ConnectBtn);
            this.splitContainer1.Panel1.Controls.Add(this.fileLab);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.splitContainer2);
            this.splitContainer1.Size = new System.Drawing.Size(710, 409);
            this.splitContainer1.SplitterWidth = 3;
            this.splitContainer1.TabIndex = 0;
            // 
            // portText
            // 
            this.portText.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.portText.Location = new System.Drawing.Point(514, 12);
            this.portText.Name = "portText";
            this.portText.Size = new System.Drawing.Size(75, 26);
            this.portText.TabIndex = 7;
            this.portText.Text = "5188";
            // 
            // ipText
            // 
            this.ipText.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.ipText.Location = new System.Drawing.Point(301, 12);
            this.ipText.Name = "ipText";
            this.ipText.Size = new System.Drawing.Size(146, 26);
            this.ipText.TabIndex = 6;
            this.ipText.Text = "127.0.0.1";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label2.Location = new System.Drawing.Point(471, 15);
            this.label2.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(56, 16);
            this.label2.TabIndex = 5;
            this.label2.Text = "端口：";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label1.Location = new System.Drawing.Point(208, 17);
            this.label1.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(88, 16);
            this.label1.TabIndex = 4;
            this.label1.Text = "服务器IP：";
            // 
            // ConnectBtn
            // 
            this.ConnectBtn.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.ConnectBtn.Location = new System.Drawing.Point(607, 11);
            this.ConnectBtn.Margin = new System.Windows.Forms.Padding(2);
            this.ConnectBtn.Name = "ConnectBtn";
            this.ConnectBtn.Size = new System.Drawing.Size(84, 28);
            this.ConnectBtn.TabIndex = 3;
            this.ConnectBtn.Text = "连接";
            this.ConnectBtn.UseVisualStyleBackColor = true;
            this.ConnectBtn.Click += new System.EventHandler(this.ConnectBtn_Click);
            // 
            // fileLab
            // 
            this.fileLab.AutoSize = true;
            this.fileLab.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.fileLab.Location = new System.Drawing.Point(16, 15);
            this.fileLab.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.fileLab.Name = "fileLab";
            this.fileLab.Size = new System.Drawing.Size(56, 16);
            this.fileLab.TabIndex = 0;
            this.fileLab.Text = "文件名";
            // 
            // splitContainer2
            // 
            this.splitContainer2.Cursor = System.Windows.Forms.Cursors.Default;
            this.splitContainer2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer2.FixedPanel = System.Windows.Forms.FixedPanel.Panel2;
            this.splitContainer2.IsSplitterFixed = true;
            this.splitContainer2.Location = new System.Drawing.Point(0, 0);
            this.splitContainer2.Margin = new System.Windows.Forms.Padding(2);
            this.splitContainer2.Name = "splitContainer2";
            // 
            // splitContainer2.Panel1
            // 
            this.splitContainer2.Panel1.Controls.Add(this.filesView);
            this.splitContainer2.Panel1.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.splitContainer2.Panel1MinSize = 0;
            // 
            // splitContainer2.Panel2
            // 
            this.splitContainer2.Panel2.Controls.Add(this.exitBtn);
            this.splitContainer2.Panel2.Controls.Add(this.downBtn);
            this.splitContainer2.Panel2.Controls.Add(this.Sendbtn);
            this.splitContainer2.Panel2.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.splitContainer2.Panel2MinSize = 0;
            this.splitContainer2.Size = new System.Drawing.Size(710, 356);
            this.splitContainer2.SplitterDistance = 602;
            this.splitContainer2.SplitterWidth = 2;
            this.splitContainer2.TabIndex = 0;
            // 
            // filesView
            // 
            this.filesView.Dock = System.Windows.Forms.DockStyle.Fill;
            this.filesView.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.filesView.FormattingEnabled = true;
            this.filesView.ItemHeight = 16;
            this.filesView.Location = new System.Drawing.Point(0, 0);
            this.filesView.Margin = new System.Windows.Forms.Padding(2);
            this.filesView.Name = "filesView";
            this.filesView.Size = new System.Drawing.Size(602, 356);
            this.filesView.TabIndex = 0;
            // 
            // exitBtn
            // 
            this.exitBtn.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.exitBtn.Location = new System.Drawing.Point(14, 208);
            this.exitBtn.Margin = new System.Windows.Forms.Padding(2);
            this.exitBtn.Name = "exitBtn";
            this.exitBtn.Size = new System.Drawing.Size(84, 28);
            this.exitBtn.TabIndex = 2;
            this.exitBtn.Text = "退出";
            this.exitBtn.UseVisualStyleBackColor = true;
            this.exitBtn.Click += new System.EventHandler(this.exitBtn_Click);
            // 
            // downBtn
            // 
            this.downBtn.Enabled = false;
            this.downBtn.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.downBtn.Location = new System.Drawing.Point(14, 91);
            this.downBtn.Margin = new System.Windows.Forms.Padding(2);
            this.downBtn.Name = "downBtn";
            this.downBtn.Size = new System.Drawing.Size(84, 28);
            this.downBtn.TabIndex = 1;
            this.downBtn.Text = "下载";
            this.downBtn.UseVisualStyleBackColor = true;
            this.downBtn.Click += new System.EventHandler(this.downBtn_Click);
            // 
            // Sendbtn
            // 
            this.Sendbtn.Enabled = false;
            this.Sendbtn.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Sendbtn.Location = new System.Drawing.Point(14, 34);
            this.Sendbtn.Margin = new System.Windows.Forms.Padding(2);
            this.Sendbtn.Name = "Sendbtn";
            this.Sendbtn.Size = new System.Drawing.Size(84, 28);
            this.Sendbtn.TabIndex = 0;
            this.Sendbtn.Text = "上传";
            this.Sendbtn.UseVisualStyleBackColor = true;
            this.Sendbtn.Click += new System.EventHandler(this.Sendbtn_Click);
            // 
            // upLoad_fileWorker1
            // 
            this.upLoad_fileWorker1.WorkerReportsProgress = true;
            this.upLoad_fileWorker1.WorkerSupportsCancellation = true;
            this.upLoad_fileWorker1.DoWork += new System.ComponentModel.DoWorkEventHandler(this.upLoad_fileWorker1_DoWork);
            this.upLoad_fileWorker1.ProgressChanged += new System.ComponentModel.ProgressChangedEventHandler(this.upLoad_fileWorker1_ProgressChanged);
            this.upLoad_fileWorker1.RunWorkerCompleted += new System.ComponentModel.RunWorkerCompletedEventHandler(this.upLoad_fileWorker1_RunWorkerCompleted);
            // 
            // GuofuClient
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(710, 409);
            this.Controls.Add(this.splitContainer1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.Margin = new System.Windows.Forms.Padding(2);
            this.MaximizeBox = false;
            this.Name = "GuofuClient";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "GuofuClient";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Form1_FormClosed);
            this.Load += new System.EventHandler(this.Form1_Load);
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel1.PerformLayout();
            this.splitContainer1.Panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
            this.splitContainer1.ResumeLayout(false);
            this.splitContainer2.Panel1.ResumeLayout(false);
            this.splitContainer2.Panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer2)).EndInit();
            this.splitContainer2.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.Label fileLab;
        private System.Windows.Forms.SplitContainer splitContainer2;
        private System.Windows.Forms.Button exitBtn;
        private System.Windows.Forms.Button downBtn;
        private System.Windows.Forms.Button Sendbtn;
        private System.Windows.Forms.Button ConnectBtn;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ListBox filesView;
        private System.Windows.Forms.SaveFileDialog saveFile;
        private System.Windows.Forms.TextBox portText;
        private System.Windows.Forms.TextBox ipText;
        private System.Windows.Forms.Label label2;
        private System.ComponentModel.BackgroundWorker upLoad_fileWorker1;
    }
}

