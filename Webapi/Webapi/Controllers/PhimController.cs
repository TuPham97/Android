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
    [RoutePrefix("api/phim")]

    public class PhimController : ApiController
{
    DOANEntities db = new DOANEntities();
    public PhimController()
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
            response.Content = new StringContent(JsonConvert.SerializeObject(db.PHIMs.Select(a => new
            {
                MAPHIM = a.MAPHIM,
                TENPHIM=a.TENPHIM,
                HINHANH=a.HINHANH,
                NGAYCONGCHIEU=a.NGAYCONGCHIEU,
                MOTA=a.MOTA,
                GIA=a.GIA
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
        [Route("findphim/{id}")]
        public HttpResponseMessage findphim(int id)
        {
            try
            {
                var response = new HttpResponseMessage(HttpStatusCode.OK);
                response.Content = new StringContent(JsonConvert.SerializeObject(db.PHIMs.Where(a => a.MAPHIM == id).Select(a => new
                {
                    MAPHIM=a.MAPHIM,
                    TENPHIM = a.TENPHIM,
                    HINHANH = a.HINHANH,
                    NGAYCONGCHIEU = a.NGAYCONGCHIEU,
                    MOTA = a.MOTA,
                    GIA = a.GIA
                }).FirstOrDefault()));
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
