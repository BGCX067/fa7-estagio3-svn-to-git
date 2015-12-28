using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Gumpws2.Models
{
    [Table("VI_HISTORY_LINE", Schema = "gump")]
    public class HistoryLine
    {
        [Key]
        public int ID_HISTORY { get; set; }
        public string DSC_TITLE { get; set; }
        public string DSC_AUTHOR { get; set; }
        public string DSC_FIRST_LINE { get; set; }
        public string DSC_GENDER { get; set; }
        public int NUM_MEMORY { get; set; }
        public int NUM_SHARED { get; set; }
        public int ID_USER { get; set; }
    }
}