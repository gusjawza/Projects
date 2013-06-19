using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LibraryProject.Users
{
    /// <summary>
    /// Contact.cs
    /// Created By Rashid Darwish 2011-08-17
    /// </summary>
    public class Contact
    {
        private Email m_email;
        private string m_firstName;
        private string m_lastName;
        private string m_userName;
        private string m_password;

   
       /// <summary>
       /// Constructor
       /// </summary>
       /// <param name="email"></param>
       /// <param name="firstName"></param>
       /// <param name="lastName"></param>
       /// <param name="userName"></param>
       /// <param name="password"></param>
        public Contact(Email email, string firstName, string lastName, string userName, string password)
        {
            m_email = email;
            m_firstName = firstName;
            m_lastName = lastName;
            m_userName = userName;
            m_password = password;
        }

        /// <summary>
        /// email Properties getter and setter
        /// </summary>
        public Email emailData { 
            get { return m_email; }
            set { m_email = value; }
        }

        /// <summary>
        /// firstName Properties getter and setter
        /// </summary>
        public string firstName
        {
            get { return m_firstName; }
            set { m_firstName = value; }
        }

        /// <summary>
        /// lastName Properties getter and setter
        /// </summary>
        public string lastName { 
            get {return m_lastName;} 
            set {m_lastName = value;}
        }

        /// <summary>
        /// UserName Property
        /// </summary>
        public String UserName { get { return m_userName; } set { m_userName = value; } }

        /// <summary>
        /// Password property
        /// </summary>
        public String Password { get { return m_password; } set {m_password = value;} }

        /// <summary>
        /// override tostring method 
        /// </summary>
        /// <returns> string contains all data</returns>
        public override string ToString()
        {
            return string.Format("{0,-10} {1, -10} {2,-10} {3,-10} {4,-10}", m_firstName.ToString(), m_lastName.ToString(), m_email.ToString(), m_userName, m_password);
        }
        /// <summary>
        /// fullName getter 
        /// </summary>
        public String fullName {
            get { return string.Format("{0,-10} {1, -10}", m_firstName, m_lastName); }
        }
    }
}
