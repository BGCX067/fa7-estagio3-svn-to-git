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
    public class UserController : ApiController
    {
        private Contexto dbContext = new Contexto();

        public HttpResponseMessage Post(User user)
        {
            user.FLG_STATUS = "S";
            user.DSC_PASSWORD = getMD5Hash(user.DSC_PASSWORD);

            if (ModelState.IsValid)
            {
                dbContext.Users.Add(user);
                dbContext.SaveChanges();

                HttpResponseMessage response = Request.CreateResponse(HttpStatusCode.Created, user);
                response.Headers.Location = new Uri(Url.Link("DefaultApi", new { id = user.ID_USER }));
                return response;
            }
            else
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);
            }
        }

        public IEnumerable<User> Get(string name)
        {
            if (!string.IsNullOrEmpty(name))
                return dbContext.Users.AsEnumerable().Where(x => x.DSC_NAME.Contains(name));   
            else
                return dbContext.Users.AsEnumerable().Where(x => x.ID_USER < 100);
        }

        public IEnumerable<User> Get()
        {
            return dbContext.Users.AsEnumerable().Where(x => x.ID_USER < 100);
        }

        public string getMD5Hash(string password)
        {
            System.Security.Cryptography.MD5 md5 = System.Security.Cryptography.MD5.Create();

            byte[] inputBytes = System.Text.Encoding.ASCII.GetBytes(password);

            byte[] hash = md5.ComputeHash(inputBytes);

            System.Text.StringBuilder sb = new System.Text.StringBuilder();

            for (int i = 0; i < hash.Length; i++)
            {
                sb.Append(hash[i].ToString("X2"));
            }

            return sb.ToString();

        }

    }
}
