﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Webapi.Models
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class DOANEntities : DbContext
    {
        public DOANEntities()
            : base("name=DOANEntities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<KHACHHANG> KHACHHANGs { get; set; }
        public virtual DbSet<LICHCHIEU> LICHCHIEUx { get; set; }
        public virtual DbSet<PHIM> PHIMs { get; set; }
        public virtual DbSet<QUANLYVE> QUANLYVEs { get; set; }
        public virtual DbSet<RAP> RAPs { get; set; }
        public virtual DbSet<SUATCHIEU> SUATCHIEUx { get; set; }
    }
}