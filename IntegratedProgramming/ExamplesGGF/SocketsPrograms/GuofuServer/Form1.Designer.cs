namespace GuofuServer
{
    partial class GuguofuServer
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
            this.exitBtn = new System.Windows.Forms.Button();
            this.userManager = new System.Windows.Forms.Button();
            this.AdressList = new System.Windows.Forms.ComboBox();
            this.ListenBtn = new System.Windows.Forms.Button();
            this.portText = new System.Windows.Forms.TextBox();
            this.PortLabel = new System.Windows.Forms.Label();
            this.messageList = new System.Windows.Forms.ListBox();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            this.SuspendLayout();
            // 
            // splitContainer1
            // 
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.FixedPanel = System.Windows.Forms.FixedPanel.Panel1;
            this.splitContainer1.Location = new System.Drawing.Point(0, 0);
            this.splitContainer1.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.splitContainer1.Name = "splitContainer1";
            this.splitContainer1.Orientation = System.Windows.Forms.Orientation.Horizontal;
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.exitBtn);
            this.splitContainer1.Panel1.Controls.Add(this.userManager);
            this.splitContainer1.Panel1.Controls.Add(this.AdressList);
            this.splitContainer1.Panel1.Controls.Add(this.ListenBtn);
            this.splitContainer1.Panel1.Controls.Add(this.portText);
            this.splitContainer1.Panel1.Controls.Add(this.PortLabel);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.messageList);
            this.splitContainer1.Size = new System.Drawing.Size(710, 409);
            this.splitContainer1.SplitterWidth = 3;
            this.splitContainer1.TabIndex = 0;
            // 
            // exitBtn
            // 
            this.exitBtn.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.exitBtn.Location = new System.Drawing.Point(621, 10);
            this.exitBtn.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.exitBtn.Name = "exitBtn";
            this.exitBtn.Size = new System.Drawing.Size(78, 26);
            this.exitBtn.TabIndex = 5;
            this.exitBtn.Text = "退出";
            this.exitBtn.UseVisualStyleBackColor = true;
            this.exitBtn.Click += new System.EventHandler(this.exitBtn_Click);
            // 
            // userManager
            // 
            this.userManager.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.userManager.Location = new System.Drawing.Point(496, 10);
            this.userManager.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.userManager.Name = "userManager";
            this.userManager.Size = new System.Drawing.Size(104, 26);
            this.userManager.TabIndex = 4;
            this.userManager.Text = "管理用户";
            this.userManager.UseVisualStyleBackColor = true;
            // 
            // AdressList
            // 
            this.AdressList.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.AdressList.FormattingEnabled = true;
            this.AdressList.Location = new System.Drawing.Point(339, 11);
            this.AdressList.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.AdressList.Name = "AdressList";
            this.AdressList.Size = new System.Drawing.Size(153, 24);
            this.AdressList.TabIndex = 3;
            this.AdressList.Text = "已连接的用户";
            // 
            // ListenBtn
            // 
            this.ListenBtn.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.ListenBtn.Location = new System.Drawing.Point(186, 9);
            this.ListenBtn.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.ListenBtn.Name = "ListenBtn";
            this.ListenBtn.Size = new System.Drawing.Size(91, 27);
            this.ListenBtn.TabIndex = 2;
            this.ListenBtn.Text = "开始监听";
            this.ListenBtn.UseVisualStyleBackColor = true;
            this.ListenBtn.Click += new System.EventHandler(this.ListenBtn_Click);
            // 
            // portText
            // 
            this.portText.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.portText.Location = new System.Drawing.Point(93, 10);
            this.portText.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.portText.Name = "portText";
            this.portText.Size = new System.Drawing.Size(78, 26);
            this.portText.TabIndex = 1;
            this.portText.Text = "5188";
            // 
            // PortLabel
            // 
            this.PortLabel.AutoSize = true;
            this.PortLabel.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.PortLabel.Location = new System.Drawing.Point(9, 14);
            this.PortLabel.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.PortLabel.Name = "PortLabel";
            this.PortLabel.Size = new System.Drawing.Size(80, 16);
            this.PortLabel.TabIndex = 0;
            this.PortLabel.Text = "监听端口:";
            // 
            // messageList
            // 
            this.messageList.Dock = System.Windows.Forms.DockStyle.Fill;
            this.messageList.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.messageList.FormattingEnabled = true;
            this.messageList.ItemHeight = 16;
            this.messageList.Location = new System.Drawing.Point(0, 0);
            this.messageList.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.messageList.Name = "messageList";
            this.messageList.Size = new System.Drawing.Size(710, 356);
            this.messageList.TabIndex = 0;
            // 
            // GuguofuServer
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(710, 409);
            this.Controls.Add(this.splitContainer1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.MaximizeBox = false;
            this.Name = "GuguofuServer";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "GuofuServer";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Form1_FormClosed);
            this.Load += new System.EventHandler(this.Form1_Load);
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel1.PerformLayout();
            this.splitContainer1.Panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
            this.splitContainer1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.Button exitBtn;
        private System.Windows.Forms.Button userManager;
        private System.Windows.Forms.ComboBox AdressList;
        private System.Windows.Forms.Button ListenBtn;
        private System.Windows.Forms.TextBox portText;
        private System.Windows.Forms.Label PortLabel;
        private System.Windows.Forms.ListBox messageList;
    }
}

