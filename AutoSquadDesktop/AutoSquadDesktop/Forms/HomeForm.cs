using AutoSquadDesktop.Models;
using AutoSquadDesktop.Services;
using System;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AutoSquadDesktop.Forms
{
    public partial class HomeForm : Form
    {
        private ApiService api = new ApiService();

        public HomeForm()
        {
            InitializeComponent();
        }

        private async void HomeForm_Load(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(Sesion.UsuarioId))
            {
                new LoginForm().Show();
                this.Close();
                return;
            }

            lblBienvenida.Text = "Bienvenido " + Sesion.UsuarioNombre;

            var cajas = await api.ObtenerCajasAsync();
            lstCajas.Items.Clear();

            foreach (var caja in cajas)
            {
                lstCajas.Items.Add($"{caja.NombreJuego} - {caja.CreadorNombre} ({caja.JugadoresBuscados})");
            }
        }

        private void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            Sesion.UsuarioId = null;
            Sesion.UsuarioNombre = null;
            Sesion.UsuarioCorreo = null;

            new LoginForm().Show();
            this.Close();
        }

        private async Task RecargarCajasAsync()
        {
            var cajas = await api.ObtenerCajasAsync();
            lstCajas.Items.Clear();

            foreach (var caja in cajas)
            {
                lstCajas.Items.Add($"{caja.NombreJuego} - {caja.CreadorNombre} ({caja.JugadoresBuscados})");
            }
        }

        private async void btnCrearCaja_Click(object sender, EventArgs e)
        {
            using (var form = new CrearCajaForm())
            {
                if (form.ShowDialog() == DialogResult.OK)
                {
                    await RecargarCajasAsync();
                }
            }
        }

        private void btnBuscarCajas_Click(object sender, EventArgs e)
        {
            new BuscarCajasForm().ShowDialog();
        }

        private void btnPerfil_Click(object sender, EventArgs e)
        {
            new PerfilForm().ShowDialog();
        }
    }
}