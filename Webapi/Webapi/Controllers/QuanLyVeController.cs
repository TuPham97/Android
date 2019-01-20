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
    [RoutePrefix("api/quanlyve")]
    public class QuanLyVeController : ApiController
    {
        DOANEntities db = new DOANEntities();
        public QuanLyVeController()
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
                response.Content = new StringContent(JsonConvert.SerializeObject(db.QUANLYVEs.Select(a => new
                {
                    MASUAT = a.MASUAT,
                    MARAP = a.MARAP,
                    NGAYDAT = a.NGAYDAT,
                    MAGHE = a.MAGHE,
                    MAKH = a.MAKH
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
                response.Content = new StringContent(JsonConvert.SerializeObject(db.QUANLYVEs.Where(a => a.MAVE == id).Select(a => new
                {
                    MASUAT = a.MASUAT,
                    MARAP = a.MARAP,
                    NGAYDAT = a.NGAYDAT,
                    MAGHE = a.MAGHE,
                    MAKH = a.MAKH
                })));
                response.Content.Headers.ContentType = new MediaTypeHeaderValue("application/json");
                return response;
            }
            catch
            {
                return new HttpResponseMessage(HttpStatusCode.BadGateway);
            }
        }
        [HttpGet]
        [Route("findve/{ngay}/{marap}/{masuat}")]
        public HttpResponseMessage findve(string ngay,int marap,int masuat)
        {
            try
            {
                var response = new HttpResponseMessage(HttpStatusCode.OK);
                response.Content = new StringContent(JsonConvert.SerializeObject(db.QUANLYVEs.Where(a => a.NGAYDAT == ngay && a.MARAP==marap && a.MASUAT == masuat).Select(a => new
                {
                    MASUAT = a.MASUAT,
                    MARAP = a.MARAP,
                    NGAYDAT = a.NGAYDAT,
                    MAGHE = a.MAGHE,
                    MAKH = a.MAKH
                }).ToList()));
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
        public HttpResponseMessage create(QUANLYVE quanlyves)
        {
            try
            {
                var response = new HttpResponseMessage(HttpStatusCode.OK);
                var quanlyve = new QUANLYVE()
                {
                    MASUAT = quanlyves.MASUAT,
                    MARAP = quanlyves.MARAP,
                    NGAYDAT = quanlyves.NGAYDAT,
                    MAGHE = quanlyves.MAGHE,
                    MAKH = quanlyves.MAKH,
                };
                db.QUANLYVEs.Add(quanlyve);
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
        public HttpResponseMessage update(QUANLYVE quanlyves)
        {
            try
            {
                var response = new HttpResponseMessage(HttpStatusCode.OK);
                var quanlyvecu = db.QUANLYVEs.SingleOrDefault(p => p.MAVE == quanlyves.MAVE);
                quanlyvecu.MASUAT = quanlyves.MASUAT;
                quanlyvecu.MARAP = quanlyves.MARAP;
                quanlyvecu.NGAYDAT = quanlyves.NGAYDAT;
                quanlyvecu.MAGHE = quanlyves.MAGHE;
                quanlyvecu.MAKH = quanlyves.MAKH;
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
                db.QUANLYVEs.Remove(db.QUANLYVEs.SingleOrDefault(a => a.MAVE == id));
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
