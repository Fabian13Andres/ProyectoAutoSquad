using AutoSquadDesktop.Models;
using AutoSquadDesktop.Services;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AutoSquadDesktop.Forms
{
    public partial class ChatForm : Form
    {
        private ApiService api = new ApiService();
        private int idCaja;

        private List<string> mensajesMostrados = new List<string>();

        public ChatForm(int idCaja)
        {
            InitializeComponent();
            this.idCaja = idCaja;
        }

        private async void ChatForm_Load(object sender, EventArgs e)
        {
            await CargarMensajes();

            timerMensajes.Interval = 3000; // cada 3 segundos
            timerMensajes.Start();
        }

        private async void timerMensajes_Tick(object sender, EventArgs e)
        {
            await CargarMensajes();
        }

        private void btnVolver_Click(object sender, EventArgs e)
        {
            timerMensajes.Stop();
            this.Close();
        }

        private async void btnEnviar_Click(object sender, EventArgs e)
        {
            string texto = txtMensaje.Text.Trim();
            if (texto == "") return;

            bool ok = await api.EnviarMensajeAsync(idCaja, texto);

            if (ok)
            {
                txtMensaje.Clear();
                await CargarMensajes();
            }
            else
            {
                MessageBox.Show("Error enviando mensaje");
            }
        }

        private async Task CargarMensajes()
        {
            try
            {
                var mensajes = await api.ObtenerMensajesAsync(idCaja);

                foreach (var m in mensajes)
                {
                    string mensajeFormateado = m.NombreUsuario + ": " + m.Texto;

                    if (!mensajesMostrados.Contains(mensajeFormateado))
                    {
                        listBoxMensajes.Items.Add(mensajeFormateado);
                        mensajesMostrados.Add(mensajeFormateado);

                        listBoxMensajes.TopIndex = listBoxMensajes.Items.Count - 1;
                    }
                }
            }
            catch (Exception ex)
            {
                // Opcional: evitar spam de errores
                Console.WriteLine("Error cargando mensajes: " + ex.Message);
            }
        }
    }
}
