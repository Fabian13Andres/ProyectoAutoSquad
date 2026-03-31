using AutoSquadDesktop.Models;
using System;
using System.Windows.Forms;

namespace AutoSquadDesktop.Forms
{
    public partial class PerfilForm : Form
    {
        public PerfilForm()
        {
            InitializeComponent();
        }

        private void PerfilForm_Load(object sender, EventArgs e)
        {
            lblNombre.Text = "Nombre: " + Sesion.UsuarioNombre;
            lblCorreo.Text = "Correo: " + Sesion.UsuarioCorreo;
        }

        private void btnVolver_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}