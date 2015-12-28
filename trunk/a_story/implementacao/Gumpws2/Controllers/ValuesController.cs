using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;
using Gumpws2.Models;

namespace Gumpws2.Controllers
{
    //[EnableCors(origins: "*", headers: "*", methods: "*")]
    public class ValuesController : ApiController
    {

        // GET api/values
        public string Get()
        {
            return "values 1";
        }

        // GET api/values/5
        public string Get(int id)
        {
            return "value";
        }

        // POST api/values
        public void Post(User user)
        {
           
        }

        // PUT api/values/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/values/5
        public void Delete(int id)
        {
        }
    }
}