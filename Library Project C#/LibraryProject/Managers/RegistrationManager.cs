using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LibraryProject.Users;
using System.Drawing;
using LibraryProject.LibraryDB;

namespace LibraryProject.Managers
{
    /// <summary>
    /// RegistrationManager.cs
    /// Created By Zarif Jawad
    /// </summary>
    class RegistrationManager
    {
        Dictionary<String, Student> dictionary;
        /// <summary>
        /// Constructor
        /// </summary>
        public RegistrationManager()
        {
            dictionary = new Dictionary<String, Student>();
        }

        /// <summary>
        /// adding new student
        /// </summary>
        /// <param name="student"></param>
        /// <returns></returns>
        public void addStudent(Student student)
        {
            DataBase.addStudent(student);
        }
    }
}
