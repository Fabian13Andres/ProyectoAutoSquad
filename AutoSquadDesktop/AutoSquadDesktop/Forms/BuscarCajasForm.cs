using AutoSquadDesktop.Models;
using AutoSquadDesktop.Services;
using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace AutoSquadDesktop.Forms
{
    internal partial class BuscarCajasForm : Form
    {
        private ApiService api = new ApiService();
        private List<Caja> cajasGuardadas = new List<Caja>();

        public BuscarCajasForm()
        {
            InitializeComponent();
        }

        private async void BuscarCajasForm_Load(object sender, EventArgs e)
        {
            cajasGuardadas = await api.ObtenerCajasAsync();
            listBoxCajas.Items.Clear();

            foreach (var caja in cajasGuardadas)
            {
                listBoxCajas.Items.Add($"{caja.NombreJuego} - {caja.CreadorNombre} ({caja.JugadoresBuscados})");
            }
        }

        private void listBoxCajas_DoubleClick(object sender, EventArgs e)
        {
            int index = listBoxCajas.SelectedIndex;
            if (index < 0) return;

            int idCaja = cajasGuardadas[index].Id;
            new ChatForm(idCaja).Show();
        }
    }
}