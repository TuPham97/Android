using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web.Http;
using Webapi.Models;

namespace Webapi.Controllers
{

        [RoutePrefix("api/lichchieu")]
        public class LichChieuController : ApiController
        {
            DOANEntities db = new DOANEntities();
            public LichChieuController()
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
                    response.Content = new StringContent(JsonConvert.SerializeObject(db.LICHCHIEUx.Select(a => new
                    {
                        MARAP = a.MARAP,
                        MASUAT = a.MASUAT,
                        MAPHIM = a.MAPHIM,
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
            [Route("find/{marap}/{masuat}")]
            public HttpResponseMessage find(int marap, int masuat)
            {
                try
                {
                    var response = new HttpResponseMessage(HttpStatusCode.OK);
                    response.Content = new StringContent(JsonConvert.SerializeObject(db.LICHCHIEUx.Where(a => a.MARAP == marap && a.MASUAT == masuat).Select(a => new
                    {
                        MARAP = a.MARAP,
                        MASUAT = a.MASUAT,
                        MAPHIM = a.MAPHIM
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
            public HttpResponseMessage create(LICHCHIEU lichchieus)
            {
                try
                {
                    var response = new HttpResponseMessage(HttpStatusCode.OK);
                    var lichchieu = new LICHCHIEU()
                    {
                        MARAP = lichchieus.MARAP,
                        MASUAT = lichchieus.MASUAT,
                        MAPHIM = lichchieus.MAPHIM,
                    };
                    db.LICHCHIEUx.Add(lichchieu);
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
            public HttpResponseMessage update(LICHCHIEU lichchieus)
            {
                try
                {
                    var response = new HttpResponseMessage(HttpStatusCode.OK);
                    var lichchieucu = db.LICHCHIEUx.SingleOrDefault(p => p.MARAP == lichchieus.MARAP && p.MASUAT == lichchieus.MASUAT);
                    lichchieucu.MAPHIM = lichchieus.MAPHIM;
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
            [Route("delete/{marap}/{masuat}")]
            public HttpResponseMessage delelte(int marap, int masuat)
            {
                try
                {
                    var response = new HttpResponseMessage(HttpStatusCode.OK);
                    db.LICHCHIEUx.Remove(db.LICHCHIEUx.SingleOrDefault(a => a.MARAP == marap && a.MASUAT == masuat));
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
