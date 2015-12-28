using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Gumpws2.Models
{
    public class Contexto : DbContext
    {
        public Contexto()
            : base("name=ConnectionString") 
        {
            base.Configuration.ProxyCreationEnabled = false;
            Database.SetInitializer<Contexto>(null);
        }

        //Registre as classes abaixo

        public DbSet<User> Users { get; set; }

        public DbSet<HistoryLine> HistoryLines { get; set; } 
    }
}