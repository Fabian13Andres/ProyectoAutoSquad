using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AutoSquadDesktop.Models
{
    internal class Caja
    {
        public int Id { get; set; }
        public string NombreJuego { get; set; }
        public string CreadorNombre { get; set; }
        public string Requisitos { get; set; }
        public int JugadoresBuscados { get; set; }
        public DateTime FechaCreacion { get; set; }
    }
}