using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Gumpws2.Models
{
    [Table("TB_USER", Schema="gump")]
    public class User
    {
        [Key]
        public int ID_USER { get; set; }

        //[Required]
        public string DSC_NAME { get; set; }

        //[Required]
        public string DSC_EMAIL { get; set; }

        //[Required]
        public DateTime DAT_BIRTHDAY { get; set; }

        //[Required]
        public string DSC_PASSWORD { get; set; }
        public string DSC_PROFILE_URL { get; set; }
        public string DSC_ACCESS_TOKEN { get; set; }
        public string FLG_STATUS { get; set; }
    }
}