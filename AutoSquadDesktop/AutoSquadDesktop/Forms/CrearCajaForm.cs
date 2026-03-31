using AutoSquadDesktop.Services;
using System;
using System.Text.RegularExpressions;
using System.Windows.Forms;

namespace AutoSquadDesktop.Forms
{
    internal partial class CrearCajaForm : Form
    {
        private ApiService api = new ApiService();

        public CrearCajaForm()
        {
            InitializeComponent();
        }

        private void CrearCajaForm_Load(object sender, EventArgs e)
        {
            cmbJuegos.Items.Add(new Juego { Id = 1, Nombre = "Valorant" });
            cmbJuegos.Items.Add(new Juego { Id = 2, Nombre = "LoL" });
            cmbJuegos.Items.Add(new Juego { Id = 3, Nombre = "CS2" });

            cmbJuegos.DisplayMember = "Nombre";
            cmbJuegos.ValueMember = "Id";
        }

        private async void btnCrear_Click(object sender, EventArgs e)
        {
            // Validamos que se haya seleccionado un juego
            if (cmbJuegos.SelectedItem == null)
            {
                MessageBox.Show("Debes seleccionar un juego.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            int idJuego = ((Juego)cmbJuegos.SelectedItem).Id;

            // Obtenemos y limpiamos requisitos
            string requisitos = txtRequisitos.Text.Trim();

        
            requisitos = Regex.Replace(requisitos, @"\s+", " ");

            // Validamos que no esté vacío tras limpieza
            if (string.IsNullOrEmpty(requisitos))
            {
                MessageBox.Show("Debes ingresar los requisitos de la caja.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            if (!Regex.IsMatch(requisitos, @"^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑ]+$"))
            {
                MessageBox.Show("Los requisitos solo pueden contener letras, números y espacios.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            // Validamos número de jugadores
            int jugadores = (int)numJugadores.Value;
            if (jugadores <= 0)
            {
                MessageBox.Show("El número de jugadores debe ser mayor que 0.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            try
            {
                var result = await api.CrearCajaAsync(idJuego, requisitos, jugadores);

                if (result != null && result.ContainsKey("success") && (bool)result["success"])
                {
                    MessageBox.Show("Caja creada correctamente", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    this.DialogResult = DialogResult.OK;
                    this.Close();
                }
                else
                {
                    string error = (result != null && result.ContainsKey("error")) ? result["error"].ToString() : "Error desconocido";
                    MessageBox.Show("No se pudo crear la caja: " + error, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Ocurrió un error al crear la caja: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }

    internal class Juego
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
    }
}