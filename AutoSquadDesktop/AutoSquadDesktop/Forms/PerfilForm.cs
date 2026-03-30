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
            lblNombre.Text = Sesion.UsuarioNombre;
            lblCorreo.Text = Sesion.UsuarioCorreo;
        }

        private void btnVolver_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}