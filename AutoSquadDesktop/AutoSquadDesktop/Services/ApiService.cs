using AutoSquadDesktop.Models;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Net;
using System.Web.Script.Serialization;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AutoSquadDesktop.Services
{
    internal class ApiService
    {
        private const string BASE_URL = "http://localhost:8080/autosquad-api";

        public async Task<Dictionary<string, object>> LoginAsync(string correo, string contra)
        {
            return await Task.Run(() =>
            {
                try
                {
                    using (var client = new WebClient())
                    {
                        var data = new System.Collections.Specialized.NameValueCollection();
                        data["correo"] = correo;
                        data["contra"] = contra;

                        var response = client.UploadValues(BASE_URL + "/login", "POST", data);
                        var json = System.Text.Encoding.UTF8.GetString(response);

                        return new JavaScriptSerializer().Deserialize<Dictionary<string, object>>(json);
                    }
                }
                catch (WebException)
                {
                    return new Dictionary<string, object>
                    {
                        { "success", false },
                        { "error", "No se pudo conectar con el servidor" }
                    };
                }
                catch (Exception ex)
                {
                    return new Dictionary<string, object>
                    {
                        { "success", false },
                        { "error", "Error interno: " + ex.Message }
                    };
                }
            });
        }

        public async Task<Dictionary<string, object>> RegistrarAsync(string nombre, string correo, string contra)
        {
            return await Task.Run(() =>
            {
                try
                {
                    using (var client = new WebClient())
                    {
                        var data = new System.Collections.Specialized.NameValueCollection();
                        data["nombre"] = nombre;
                        data["correo"] = correo;
                        data["contra"] = contra;

                        var response = client.UploadValues(BASE_URL + "/register", "POST", data);
                        var json = System.Text.Encoding.UTF8.GetString(response);

                        return new JavaScriptSerializer().Deserialize<Dictionary<string, object>>(json);
                    }
                }
                catch (WebException)
                {
                    return new Dictionary<string, object>
                    {
                        { "success", false },
                        { "error", "No se pudo conectar con el servidor" }
                    };
                }
                catch (Exception ex)
                {
                    return new Dictionary<string, object>
                    {
                        { "success", false },
                        { "error", "Error interno: " + ex.Message }
                    };
                }
            });
        }

        public async Task<List<Caja>> ObtenerCajasAsync()
        {
            return await Task.Run(() =>
            {
                try
                {
                    using (var client = new WebClient())
                    {
                        var json = client.DownloadString(BASE_URL + "/caja/list");
                        var data = new JavaScriptSerializer().Deserialize<Dictionary<string, object>>(json);
                        var lista = (System.Collections.ArrayList)data["cajas"];

                        var cajas = new List<Caja>();

                        foreach (Dictionary<string, object> item in lista)
                        {
                            // Obtenemos el timestamp UNIX (segundos) y lo convertimos a DateTime
                            long timestamp = Convert.ToInt64(item["hora_creacion"]);
                            DateTime fechaCreacion = DateTimeOffset.FromUnixTimeSeconds(timestamp).LocalDateTime;

                            // Solo agregamos cajas creadas hace 1 hora o menos
                            if ((DateTime.Now - fechaCreacion).TotalHours <= 1)
                            {
                                cajas.Add(new Caja
                                {
                                    Id = Convert.ToInt32(item["id_caja"]),
                                    NombreJuego = item["nombre_juego"].ToString(),
                                    CreadorNombre = item["creador_nombre"].ToString(),
                                    Requisitos = item["requisitos"].ToString(),
                                    JugadoresBuscados = Convert.ToInt32(item["jugadores_buscados"]),
                                    FechaCreacion = fechaCreacion
                                });
                            }
                        }

                        return cajas;
                    }
                }
                catch (WebException)
                {
                    MessageBox.Show("No se pudo conectar con el servidor", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return new List<Caja>();
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error interno: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return new List<Caja>();
                }
            });
        }

        public async Task<Dictionary<string, object>> CrearCajaAsync(int idJuego, string requisitos, int jugadores)
        {
            return await Task.Run(() =>
            {
                try
                {
                    using (var client = new WebClient())
                    {
                        var data = new System.Collections.Specialized.NameValueCollection();
                        data["id_creador"] = Sesion.UsuarioId;
                        data["id_juego"] = idJuego.ToString();
                        data["requisitos"] = requisitos;
                        data["jugadores"] = jugadores.ToString();

                        var response = client.UploadValues(BASE_URL + "/caja/create", "POST", data);
                        var json = System.Text.Encoding.UTF8.GetString(response);

                        return new JavaScriptSerializer().Deserialize<Dictionary<string, object>>(json);
                    }
                }
                catch (WebException)
                {
                    return new Dictionary<string, object>
                    {
                        { "success", false },
                        { "error", "No se pudo conectar con el servidor" }
                    };
                }
                catch (Exception ex)
                {
                    return new Dictionary<string, object>
                    {
                        { "success", false },
                        { "error", "Error interno: " + ex.Message }
                    };
                }
            });
        }

        public async Task<List<Mensaje>> ObtenerMensajesAsync(int idCaja)
        {
            return await Task.Run(() =>
            {
                try
                {
                    using (var client = new WebClient())
                    {
                        var json = client.DownloadString(BASE_URL + "/mensaje/list?id_caja=" + idCaja);
                        var data = new JavaScriptSerializer().Deserialize<Dictionary<string, object>>(json);
                        var lista = (ArrayList)data["mensajes"];

                        var mensajes = new List<Mensaje>();
                        foreach (Dictionary<string, object> item in lista)
                        {
                            mensajes.Add(new Mensaje
                            {
                                NombreUsuario = item["nombre_usuario"].ToString(),
                                Texto = item["texto"].ToString()
                            });
                        }

                        return mensajes;
                    }
                }
                catch (WebException)
                {
                    MessageBox.Show("No se pudo conectar con el servidor", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return new List<Mensaje>();
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error interno: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return new List<Mensaje>();
                }
            });
        }

        public async Task<bool> EnviarMensajeAsync(int idCaja, string texto)
        {
            return await Task.Run(() =>
            {
                try
                {
                    using (var client = new WebClient())
                    {
                        var data = new System.Collections.Specialized.NameValueCollection();
                        data["id_caja"] = idCaja.ToString();
                        data["id_usuario"] = Sesion.UsuarioId;
                        data["texto"] = texto;

                        var response = client.UploadValues(BASE_URL + "/mensaje/create", "POST", data);
                        var json = System.Text.Encoding.UTF8.GetString(response);

                        var result = new JavaScriptSerializer().Deserialize<Dictionary<string, object>>(json);
                        return (bool)result["success"];
                    }
                }
                catch (WebException)
                {
                    MessageBox.Show("No se pudo conectar con el servidor", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return false;
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error interno: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return false;
                }
            });
        }
    }
}