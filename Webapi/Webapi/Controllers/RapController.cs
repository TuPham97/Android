using Newtonsoft.Json;
using Webapi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web.Http;

namespace Webapi.Controllers
{
    [RoutePrefix("api/rap")]
    public class RapController : ApiController
    {
        DOANEntities db = new DOANEntities();
        public RapController()
        {
            // Add the following code
            // problem will be solved
            db.Configuration.ProxyCreationEnabled = false;
        }
        [HttpGet]
        [Route("findall")]
        public HttpResponseMessage findall()
        {
            try
            {
                var response = new HttpResponseMessage(HttpStatusCode.OK);
                response.Content = new StringContent(JsonConvert.SerializeObject(db.RAPs.Select(a => new
                {
                    MARAP = a.MARAP,
                    SOGHE = a.SOGHE
                }).ToList()));
                response.Content.Headers.ContentType = new MediaTypeHeaderValue("application/json");
                return response;
            }
            catch
            {
                return new HttpResponseMessage(HttpStatusCode.BadGateway);
            }
        }
        [HttpGet]
        [Route("find/{id}")]
        public HttpResponseMessage find(int id)
        {
            try
            {
                var response = new HttpResponseMessage(HttpStatusCode.OK);
                response.Content = new StringContent(JsonConvert.SerializeObject(db.RAPs.Where(a => a.MARAP == id).Select(a => new
                {
                    MARAP = a.MARAP,
                    SOGHE = a.SOGHE
                })));
                response.Content.Headers.ContentType = new MediaTypeHeaderValue("application/json");
                return response;
            }
            catch
            {
                return new HttpResponseMessage(HttpStatusCode.BadGateway);
            }
        }
        [HttpPost]
        [Route("create")]
        public HttpResponseMessage create(RAP raps)
        {
            try
            {
                var response = new HttpResponseMessage(HttpStatusCode.OK);
                var rap = new RAP()
                {
                    SOGHE = raps.SOGHE,
                };
                db.RAPs.Add(rap);
                db.SaveChanges();
                response.Content.Headers.ContentType = new MediaTypeHeaderValue("application/json");
                return response;
            }
            catch
            {
                return new HttpResponseMessage(HttpStatusCode.BadGateway);
            }
        }
        [HttpPut]
        [Route("update")]
        public HttpResponseMessage update(RAP raps)
        {
            try
            {
                var response = new HttpResponseMessage(HttpStatusCode.OK);
                var rapcu = db.RAPs.SingleOrDefault(p => p.MARAP == raps.MARAP);
                rapcu.SOGHE = raps.SOGHE;
                db.SaveChanges();
                response.Content.Headers.ContentType = new MediaTypeHeaderValue("application/json");
                return response;
            }
            catch
            {
                return new HttpResponseMessage(HttpStatusCode.BadGateway);
            }
        }
        [HttpDelete]
        [Route("delete/{id}")]
        public HttpResponseMessage delelte(int id)
        {
            try
            {
                var response = new HttpResponseMessage(HttpStatusCode.OK);
                db.RAPs.Remove(db.RAPs.SingleOrDefault(a => a.MARAP == id));
                db.SaveChanges();
                response.Content.Headers.ContentType = new MediaTypeHeaderValue("application/json");
                return response;
            }
            catch
            {
                return new HttpResponseMessage(HttpStatusCode.BadGateway);
            }
        }
    }
}
