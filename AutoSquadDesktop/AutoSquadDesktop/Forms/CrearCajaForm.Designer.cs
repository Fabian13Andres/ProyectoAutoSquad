namespace AutoSquadDesktop.Forms
{
    partial class CrearCajaForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(CrearCajaForm));
            this.txtRequisitos = new System.Windows.Forms.TextBox();
            this.numJugadores = new System.Windows.Forms.NumericUpDown();
            this.btnAceptar = new System.Windows.Forms.Button();
            this.cmbJuegos = new System.Windows.Forms.ComboBox();
            this.btnCancelar = new System.Windows.Forms.Button();
            this.lblRequisitos = new System.Windows.Forms.Label();
            this.lblJuegos = new System.Windows.Forms.Label();
            this.lblJugadores = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.numJugadores)).BeginInit();
            this.SuspendLayout();
            // 
            // txtRequisitos
            // 
            this.txtRequisitos.Location = new System.Drawing.Point(12, 100);
            this.txtRequisitos.Multiline = true;
            this.txtRequisitos.Name = "txtRequisitos";
            this.txtRequisitos.Size = new System.Drawing.Size(335, 93);
            this.txtRequisitos.TabIndex = 0;
            // 
            // numJugadores
            // 
            this.numJugadores.Location = new System.Drawing.Point(15, 244);
            this.numJugadores.Name = "numJugadores";
            this.numJugadores.Size = new System.Drawing.Size(52, 20);
            this.numJugadores.TabIndex = 1;
            // 
            // btnAceptar
            // 
            this.btnAceptar.BackColor = System.Drawing.Color.RoyalBlue;
            this.btnAceptar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnAceptar.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.btnAceptar.Location = new System.Drawing.Point(12, 293);
            this.btnAceptar.Name = "btnAceptar";
            this.btnAceptar.Size = new System.Drawing.Size(140, 63);
            this.btnAceptar.TabIndex = 2;
            this.btnAceptar.Text = "Aceptar";
            this.btnAceptar.UseVisualStyleBackColor = false;
            this.btnAceptar.Click += new System.EventHandler(this.btnCrear_Click);
            // 
            // cmbJuegos
            // 
            this.cmbJuegos.FormattingEnabled = true;
            this.cmbJuegos.Location = new System.Drawing.Point(12, 37);
            this.cmbJuegos.Name = "cmbJuegos";
            this.cmbJuegos.Size = new System.Drawing.Size(121, 21);
            this.cmbJuegos.TabIndex = 3;
            // 
            // btnCancelar
            // 
            this.btnCancelar.BackColor = System.Drawing.Color.RoyalBlue;
            this.btnCancelar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelar.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.btnCancelar.Location = new System.Drawing.Point(207, 293);
            this.btnCancelar.Name = "btnCancelar";
            this.btnCancelar.Size = new System.Drawing.Size(140, 63);
            this.btnCancelar.TabIndex = 4;
            this.btnCancelar.Text = "Cancelar";
            this.btnCancelar.UseVisualStyleBackColor = false;
            this.btnCancelar.Click += new System.EventHandler(this.btnCancelar_Click);
            // 
            // lblRequisitos
            // 
            this.lblRequisitos.AutoSize = true;
            this.lblRequisitos.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblRequisitos.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.lblRequisitos.Location = new System.Drawing.Point(12, 72);
            this.lblRequisitos.Name = "lblRequisitos";
            this.lblRequisitos.Size = new System.Drawing.Size(123, 25);
            this.lblRequisitos.TabIndex = 5;
            this.lblRequisitos.Text = "Requisitos";
            // 
            // lblJuegos
            // 
            this.lblJuegos.AutoSize = true;
            this.lblJuegos.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblJuegos.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.lblJuegos.Location = new System.Drawing.Point(12, 9);
            this.lblJuegos.Name = "lblJuegos";
            this.lblJuegos.Size = new System.Drawing.Size(76, 25);
            this.lblJuegos.TabIndex = 6;
            this.lblJuegos.Text = "Juego";
            // 
            // lblJugadores
            // 
            this.lblJugadores.AutoSize = true;
            this.lblJugadores.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblJugadores.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.lblJugadores.Location = new System.Drawing.Point(12, 206);
            this.lblJugadores.Name = "lblJugadores";
            this.lblJugadores.Size = new System.Drawing.Size(122, 25);
            this.lblJugadores.TabIndex = 7;
            this.lblJugadores.Text = "Jugadores";
            // 
            // CrearCajaForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightCoral;
            this.ClientSize = new System.Drawing.Size(361, 383);
            this.Controls.Add(this.lblJugadores);
            this.Controls.Add(this.lblJuegos);
            this.Controls.Add(this.lblRequisitos);
            this.Controls.Add(this.btnCancelar);
            this.Controls.Add(this.cmbJuegos);
            this.Controls.Add(this.btnAceptar);
            this.Controls.Add(this.numJugadores);
            this.Controls.Add(this.txtRequisitos);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.Name = "CrearCajaForm";
            this.Text = "Crear Caja";
            this.Load += new System.EventHandler(this.CrearCajaForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.numJugadores)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox txtRequisitos;
        private System.Windows.Forms.NumericUpDown numJugadores;
        private System.Windows.Forms.Button btnAceptar;
        private System.Windows.Forms.ComboBox cmbJuegos;
        private System.Windows.Forms.Button btnCancelar;
        private System.Windows.Forms.Label lblRequisitos;
        private System.Windows.Forms.Label lblJuegos;
        private System.Windows.Forms.Label lblJugadores;
    }
}