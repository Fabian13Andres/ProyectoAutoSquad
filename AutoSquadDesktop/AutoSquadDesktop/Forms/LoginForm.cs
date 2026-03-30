using AutoSquadDesktop.Models;
using AutoSquadDesktop.Services;
using System;
using System.Windows.Forms;

namespace AutoSquadDesktop.Forms
{
    public partial class LoginForm : Form
    {
        private ApiService api = new ApiService();

        public LoginForm()
        {
            InitializeComponent();
        }

        private async void btnEntrar_Click(object sender, EventArgs e)
        {
            var result = await api.LoginAsync(txtCorreo.Text, txtContra.Text);

            if ((bool)result["success"])
            {
                Sesion.UsuarioId = result["id"].ToString();
                Sesion.UsuarioNombre = result["nombre"].ToString();
                Sesion.UsuarioCorreo = txtCorreo.Text;

                new HomeForm().Show();
                this.Hide();
            }
            else
            {
                lblResultado.Text = result["error"].ToString();
            }
        }

        private void linkRegistro_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            new RegistroForm().ShowDialog();
        }

        private void lblCorreoLogin_Click(object sender, EventArgs e)
        {

        }
    }
}