using AutoSquadDesktop.Services;
using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;
using System.Windows.Forms;

namespace AutoSquadDesktop.Forms
{
    public partial class RegistroForm : Form
    {
        private ApiService api = new ApiService();

        public RegistroForm()
        {
            InitializeComponent();
        }

        private async void btnRegistrar_Click(object sender, EventArgs e)
        {
            string nombre = txtNombre.Text.Trim();
            string correo = txtCorreo.Text.Trim();
            string contra = txtContra.Text.Trim();

            // Validación de campos vacíos
            if (string.IsNullOrEmpty(nombre) || string.IsNullOrEmpty(correo) || string.IsNullOrEmpty(contra))
            {
                lblResultado.Visible = true;
                lblResultado.Text = "Rellena todos los campos";
                return;
            }

            // Validación de email
            if (!Regex.IsMatch(correo, @"^[^@\s]+@[^@\s]+$"))
            {
                lblResultado.Visible = true;
                lblResultado.Text = "Correo inválido";
                return;
            }

            try
            {
                var result = await api.RegistrarAsync(nombre, correo, contra);

                // Si no hay respuesta
                if (result == null)
                {
                    lblResultado.Visible = true;
                    lblResultado.Text = "Error: sin respuesta del servidor";
                    return;
                }

                // Convertimos success de forma segura
                bool success = result.ContainsKey("success") && Convert.ToBoolean(result["success"]);

                if (success)
                {
                    lblResultado.Visible = true;
                    lblResultado.Text = "Usuario creado correctamente";
                }
                else
                {
                    string error = result.ContainsKey("error")
                        ? result["error"].ToString()
                        : "Error desconocido";

                    lblResultado.Visible = true;
                    lblResultado.Text = error;
                }
            }
            catch (Exception ex)
            {
                lblResultado.Visible = true;
                lblResultado.Text = "Error: " + ex.Message;
            }
        }

        private void btnVolver_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
