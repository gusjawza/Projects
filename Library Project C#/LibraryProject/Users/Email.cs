using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LibraryProject.Users
{
    /// <summary>
    /// Email.cs
    /// Created By Rashid Darwish 2011-08-20
    /// </summary>
    public class Email
    {
        //private email
        private string m_personal;

        /// <summary>
        /// Default constructor 
        /// </summary>
        /// <remarks></remarks>
        public Email(String personalMail)
        {
            m_personal = personalMail;
        }

        /// <summary>
        /// Property related to the field m_Personal
        /// Both read and write access
        /// </summary>
        /// <value></value>
        /// <returns></returns>
        /// <remarks></remarks>
        public string Personal
        {
            //private mail
            get { return m_personal; }

            set { m_personal = value; }
        }

        /// <summary>
        /// Delivers a formatted string with data stored in the object. The values will
        /// appear as columns.  Make sure that a font like "Courier New" is used in
        /// the control displaying this information.
        /// </summary>
        /// <returns>the Formatted strings.</returns>
        /// <remarks></remarks>
        public override string ToString()
        {
            return string.Format("{0,-20}", m_personal);
        }
    }
}
