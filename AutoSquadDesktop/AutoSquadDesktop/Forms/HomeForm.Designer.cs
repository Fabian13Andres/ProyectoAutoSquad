namespace AutoSquadDesktop.Forms
{
    partial class HomeForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(HomeForm));
            this.lblBienvenida = new System.Windows.Forms.Label();
            this.lstCajas = new System.Windows.Forms.ListBox();
            this.btnCrearCaja = new System.Windows.Forms.Button();
            this.btnBuscarCajas = new System.Windows.Forms.Button();
            this.btnPerfil = new System.Windows.Forms.Button();
            this.btnCerrarSesion = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // lblBienvenida
            // 
            this.lblBienvenida.AutoSize = true;
            this.lblBienvenida.Font = new System.Drawing.Font("Microsoft Sans Serif", 20.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblBienvenida.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.lblBienvenida.Location = new System.Drawing.Point(12, 9);
            this.lblBienvenida.Name = "lblBienvenida";
            this.lblBienvenida.Size = new System.Drawing.Size(158, 31);
            this.lblBienvenida.TabIndex = 0;
            this.lblBienvenida.Text = "Bienvenido";
            // 
            // lstCajas
            // 
            this.lstCajas.Enabled = false;
            this.lstCajas.FormattingEnabled = true;
            this.lstCajas.Location = new System.Drawing.Point(256, 17);
            this.lstCajas.Name = "lstCajas";
            this.lstCajas.Size = new System.Drawing.Size(216, 407);
            this.lstCajas.TabIndex = 1;
            // 
            // btnCrearCaja
            // 
            this.btnCrearCaja.BackColor = System.Drawing.Color.RoyalBlue;
            this.btnCrearCaja.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCrearCaja.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.btnCrearCaja.Location = new System.Drawing.Point(15, 106);
            this.btnCrearCaja.Name = "btnCrearCaja";
            this.btnCrearCaja.Size = new System.Drawing.Size(219, 59);
            this.btnCrearCaja.TabIndex = 2;
            this.btnCrearCaja.Text = "Crear Caja";
            this.btnCrearCaja.UseVisualStyleBackColor = false;
            this.btnCrearCaja.Click += new System.EventHandler(this.btnCrearCaja_Click);
            // 
            // btnBuscarCajas
            // 
            this.btnBuscarCajas.BackColor = System.Drawing.Color.RoyalBlue;
            this.btnBuscarCajas.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnBuscarCajas.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.btnBuscarCajas.Location = new System.Drawing.Point(15, 186);
            this.btnBuscarCajas.Name = "btnBuscarCajas";
            this.btnBuscarCajas.Size = new System.Drawing.Size(219, 59);
            this.btnBuscarCajas.TabIndex = 3;
            this.btnBuscarCajas.Text = "Buscar";
            this.btnBuscarCajas.UseVisualStyleBackColor = false;
            this.btnBuscarCajas.Click += new System.EventHandler(this.btnBuscarCajas_Click);
            // 
            // btnPerfil
            // 
            this.btnPerfil.BackColor = System.Drawing.Color.RoyalBlue;
            this.btnPerfil.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnPerfil.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.btnPerfil.Location = new System.Drawing.Point(15, 271);
            this.btnPerfil.Name = "btnPerfil";
            this.btnPerfil.Size = new System.Drawing.Size(219, 59);
            this.btnPerfil.TabIndex = 4;
            this.btnPerfil.Text = "Perfil";
            this.btnPerfil.UseVisualStyleBackColor = false;
            this.btnPerfil.Click += new System.EventHandler(this.btnPerfil_Click);
            // 
            // btnCerrarSesion
            // 
            this.btnCerrarSesion.BackColor = System.Drawing.Color.RoyalBlue;
            this.btnCerrarSesion.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCerrarSesion.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.btnCerrarSesion.Location = new System.Drawing.Point(15, 401);
            this.btnCerrarSesion.Name = "btnCerrarSesion";
            this.btnCerrarSesion.Size = new System.Drawing.Size(118, 23);
            this.btnCerrarSesion.TabIndex = 5;
            this.btnCerrarSesion.Text = "Cerrar Sesión";
            this.btnCerrarSesion.UseVisualStyleBackColor = false;
            this.btnCerrarSesion.Click += new System.EventHandler(this.btnCerrarSesion_Click);
            // 
            // HomeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightCoral;
            this.ClientSize = new System.Drawing.Size(484, 441);
            this.Controls.Add(this.btnCerrarSesion);
            this.Controls.Add(this.btnPerfil);
            this.Controls.Add(this.btnBuscarCajas);
            this.Controls.Add(this.btnCrearCaja);
            this.Controls.Add(this.lstCajas);
            this.Controls.Add(this.lblBienvenida);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.Name = "HomeForm";
            this.Text = "AutoSquad";
            this.Load += new System.EventHandler(this.HomeForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lblBienvenida;
        private System.Windows.Forms.ListBox lstCajas;
        private System.Windows.Forms.Button btnCrearCaja;
        private System.Windows.Forms.Button btnBuscarCajas;
        private System.Windows.Forms.Button btnPerfil;
        private System.Windows.Forms.Button btnCerrarSesion;
    }
}