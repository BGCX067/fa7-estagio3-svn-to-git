using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Mvc;
using Gumpws2.Models;

namespace Gumpws2.Controllers
{
    public class HistoryLineController : ApiController
    {
        private Contexto dbContext = new Contexto();

        public IEnumerable<HistoryLine> Get(int id)
        {
            return dbContext.HistoryLines.AsEnumerable().Where(x => x.ID_USER == id);
        }

        public IEnumerable<HistoryLine> Get()
        {
            return dbContext.HistoryLines.AsEnumerable().Where(x => x.ID_USER == 20);
        }
    }
}
