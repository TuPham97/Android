using Webapi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Net.Http.Headers;
using Newtonsoft.Json;
using System.Globalization;

namespace QLBVWebAPI.Controllers
{
    [RoutePrefix("api/taikhoan")]
    public class TaiKhoanController : ApiController
    {
        DOANEntities db = new DOANEntities();
        public TaiKhoanController()
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
                response.Content = new StringContent(JsonConvert.SerializeObject(db.KHACHHANGs.Select(a => new
                {
                    MAKH = a.MAKH,
                    USERNAME = a.USERNAME,
                    PASS = a.PASS,
                    HOTEN = a.HOTEN,
                    GIOITINH = a.GIOITINH,
                    NGAYSINH = a.NGAYSINH,
                    SDT = a.SDT
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
        [Route("find/{id}/{pass}")]
        public HttpResponseMessage find(string id, string pass)
        {
            try
            {
                var response = new HttpResponseMessage(HttpStatusCode.OK);
                response.Content = new StringContent(JsonConvert.SerializeObject(db.KHACHHANGs.Where(a => a.USERNAME == id && a.PASS == pass).Select(a => new
                {
                    MAKH = a.MAKH,
                    USERNAME = a.USERNAME,
                    PASS = a.PASS,
                    HOTEN = a.HOTEN,
                    GIOITINH = a.GIOITINH,
                    NGAYSINH = a.NGAYSINH,
                    SDT = a.SDT
                }).FirstOrDefault()));
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
        public HttpResponseMessage create(KHACHHANG khachhangs)
        {
            try
            {
                var response = new HttpResponseMessage(HttpStatusCode.OK);
                var khachhang = new KHACHHANG()
                {
                    USERNAME = khachhangs.USERNAME,
                    PASS = khachhangs.PASS,
                    HOTEN = khachhangs.HOTEN,
                    GIOITINH = khachhangs.GIOITINH,
                    NGAYSINH = khachhangs.NGAYSINH,
                    SDT = khachhangs.SDT
                };
                db.KHACHHANGs.Add(khachhang);
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

