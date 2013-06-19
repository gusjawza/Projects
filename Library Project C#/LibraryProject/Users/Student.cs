using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;

namespace LibraryProject.Users
{
    /// <summary>
    /// Student.cs
    /// Created by Rashid Darwish 2011-08-18
    /// </summary>
    public class Student
    {
        private Contact m_contact;
        private Image img; 

        /// <summary>
        /// Default constructor - calls another constructor in this class
        /// </summary>
        public Student()
            : this(null)
        {
        }

        /// <summary>
        /// Constructor with one parameter
        /// </summary>
        /// <param name="contact"></param>
       public Student(Contact contact)
            : this(contact, null)
        {
        }

      /// <summary>
       /// Constructor with two parameter
      /// </summary>
      /// <param name="contact"></param>
      /// <param name="img"></param>
       public Student(Contact contact, Image img)
       {
           m_contact = contact;
           if (img != null)
           {
               this.img = img;
           }
           else this.img = LibraryResource.NotAvailable;
          
       }

        /// <summary>
        /// Delivers a formatted string with data stored in the object
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            return string.Format("{0,-10}", m_contact.ToString());
        }

        /// <summary>
        /// Contact data setter and getter
        /// </summary>
        public Contact ContactData { 
            get { return m_contact;} 
            set { m_contact = value;}
        }

        /// <summary>
        /// image property
        /// </summary>
        public Image Image { get { return img; } set { img = value; } }
    }
}
