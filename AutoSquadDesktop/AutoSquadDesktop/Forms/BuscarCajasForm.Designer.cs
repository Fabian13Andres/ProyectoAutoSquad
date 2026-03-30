namespace AutoSquadDesktop.Forms
{
    partial class BuscarCajasForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(BuscarCajasForm));
            this.listBoxCajas = new System.Windows.Forms.ListBox();
            this.btnRefrescar = new System.Windows.Forms.Button();
            this.btnEntrarChat = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // listBoxCajas
            // 
            this.listBoxCajas.FormattingEnabled = true;
            this.listBoxCajas.Location = new System.Drawing.Point(12, 12);
            this.listBoxCajas.Name = "listBoxCajas";
            this.listBoxCajas.Size = new System.Drawing.Size(387, 394);
            this.listBoxCajas.TabIndex = 0;
            // 
            // btnRefrescar
            // 
            this.btnRefrescar.BackColor = System.Drawing.Color.RoyalBlue;
            this.btnRefrescar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnRefrescar.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.btnRefrescar.Location = new System.Drawing.Point(12, 423);
            this.btnRefrescar.Name = "btnRefrescar";
            this.btnRefrescar.Size = new System.Drawing.Size(160, 59);
            this.btnRefrescar.TabIndex = 1;
            this.btnRefrescar.Text = "Refrescar";
            this.btnRefrescar.UseVisualStyleBackColor = false;
            this.btnRefrescar.Click += new System.EventHandler(this.BuscarCajasForm_Load);
            // 
            // btnEntrarChat
            // 
            this.btnEntrarChat.BackColor = System.Drawing.Color.RoyalBlue;
            this.btnEntrarChat.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnEntrarChat.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.btnEntrarChat.Location = new System.Drawing.Point(239, 423);
            this.btnEntrarChat.Name = "btnEntrarChat";
            this.btnEntrarChat.Size = new System.Drawing.Size(160, 59);
            this.btnEntrarChat.TabIndex = 2;
            this.btnEntrarChat.Text = "Entrar";
            this.btnEntrarChat.UseVisualStyleBackColor = false;
            this.btnEntrarChat.Enter += new System.EventHandler(this.listBoxCajas_DoubleClick);
            // 
            // BuscarCajasForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightCoral;
            this.ClientSize = new System.Drawing.Size(411, 494);
            this.Controls.Add(this.btnEntrarChat);
            this.Controls.Add(this.btnRefrescar);
            this.Controls.Add(this.listBoxCajas);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.Name = "BuscarCajasForm";
            this.Text = "Buscar Cajas";
            this.Load += new System.EventHandler(this.BuscarCajasForm_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListBox listBoxCajas;
        private System.Windows.Forms.Button btnRefrescar;
        private System.Windows.Forms.Button btnEntrarChat;
    }
}