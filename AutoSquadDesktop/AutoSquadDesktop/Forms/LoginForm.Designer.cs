namespace AutoSquadDesktop.Forms
{
    partial class LoginForm
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(LoginForm));
            this.txtCorreo = new System.Windows.Forms.TextBox();
            this.txtContra = new System.Windows.Forms.TextBox();
            this.btnEntrar = new System.Windows.Forms.Button();
            this.lblResultado = new System.Windows.Forms.Label();
            this.linkRegistro = new System.Windows.Forms.LinkLabel();
            this.lblCorreoLogin = new System.Windows.Forms.Label();
            this.lblContraLogin = new System.Windows.Forms.Label();
            this.picAutoSquad = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.picAutoSquad)).BeginInit();
            this.SuspendLayout();
            // 
            // txtCorreo
            // 
            this.txtCorreo.Location = new System.Drawing.Point(12, 37);
            this.txtCorreo.Name = "txtCorreo";
            this.txtCorreo.Size = new System.Drawing.Size(265, 20);
            this.txtCorreo.TabIndex = 0;
            // 
            // txtContra
            // 
            this.txtContra.Location = new System.Drawing.Point(12, 88);
            this.txtContra.Name = "txtContra";
            this.txtContra.Size = new System.Drawing.Size(133, 20);
            this.txtContra.TabIndex = 1;
            // 
            // btnEntrar
            // 
            this.btnEntrar.BackColor = System.Drawing.Color.RoyalBlue;
            this.btnEntrar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnEntrar.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.btnEntrar.Location = new System.Drawing.Point(67, 177);
            this.btnEntrar.Name = "btnEntrar";
            this.btnEntrar.Size = new System.Drawing.Size(137, 56);
            this.btnEntrar.TabIndex = 2;
            this.btnEntrar.Text = "Entrar";
            this.btnEntrar.UseVisualStyleBackColor = false;
            this.btnEntrar.Click += new System.EventHandler(this.btnEntrar_Click);
            // 
            // lblResultado
            // 
            this.lblResultado.AutoSize = true;
            this.lblResultado.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblResultado.ForeColor = System.Drawing.Color.Lime;
            this.lblResultado.Location = new System.Drawing.Point(13, 111);
            this.lblResultado.Name = "lblResultado";
            this.lblResultado.Size = new System.Drawing.Size(0, 20);
            this.lblResultado.TabIndex = 3;
            // 
            // linkRegistro
            // 
            this.linkRegistro.AutoSize = true;
            this.linkRegistro.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.linkRegistro.Location = new System.Drawing.Point(7, 259);
            this.linkRegistro.Name = "linkRegistro";
            this.linkRegistro.Size = new System.Drawing.Size(293, 25);
            this.linkRegistro.TabIndex = 4;
            this.linkRegistro.TabStop = true;
            this.linkRegistro.Text = "¿No tienes cueta? ¡Créala!";
            this.linkRegistro.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkRegistro_LinkClicked);
            // 
            // lblCorreoLogin
            // 
            this.lblCorreoLogin.AutoSize = true;
            this.lblCorreoLogin.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblCorreoLogin.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.lblCorreoLogin.Location = new System.Drawing.Point(12, 9);
            this.lblCorreoLogin.Name = "lblCorreoLogin";
            this.lblCorreoLogin.Size = new System.Drawing.Size(83, 25);
            this.lblCorreoLogin.TabIndex = 5;
            this.lblCorreoLogin.Text = "Correo";
            this.lblCorreoLogin.Click += new System.EventHandler(this.lblCorreoLogin_Click);
            // 
            // lblContraLogin
            // 
            this.lblContraLogin.AutoSize = true;
            this.lblContraLogin.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblContraLogin.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.lblContraLogin.Location = new System.Drawing.Point(12, 60);
            this.lblContraLogin.Name = "lblContraLogin";
            this.lblContraLogin.Size = new System.Drawing.Size(133, 25);
            this.lblContraLogin.TabIndex = 6;
            this.lblContraLogin.Text = "Contraseña";
            // 
            // picAutoSquad
            // 
            this.picAutoSquad.Image = global::AutoSquadDesktop.Properties.Resources.logo;
            this.picAutoSquad.Location = new System.Drawing.Point(318, 98);
            this.picAutoSquad.Name = "picAutoSquad";
            this.picAutoSquad.Size = new System.Drawing.Size(166, 163);
            this.picAutoSquad.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.picAutoSquad.TabIndex = 10;
            this.picAutoSquad.TabStop = false;
            // 
            // LoginForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightCoral;
            this.ClientSize = new System.Drawing.Size(505, 311);
            this.Controls.Add(this.picAutoSquad);
            this.Controls.Add(this.lblContraLogin);
            this.Controls.Add(this.lblCorreoLogin);
            this.Controls.Add(this.linkRegistro);
            this.Controls.Add(this.lblResultado);
            this.Controls.Add(this.btnEntrar);
            this.Controls.Add(this.txtContra);
            this.Controls.Add(this.txtCorreo);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.Name = "LoginForm";
            this.Text = "Login AutoSquad";
            ((System.ComponentModel.ISupportInitialize)(this.picAutoSquad)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox txtCorreo;
        private System.Windows.Forms.TextBox txtContra;
        private System.Windows.Forms.Button btnEntrar;
        private System.Windows.Forms.Label lblResultado;
        private System.Windows.Forms.LinkLabel linkRegistro;
        private System.Windows.Forms.Label lblCorreoLogin;
        private System.Windows.Forms.Label lblContraLogin;
        private System.Windows.Forms.PictureBox picAutoSquad;
    }
}

