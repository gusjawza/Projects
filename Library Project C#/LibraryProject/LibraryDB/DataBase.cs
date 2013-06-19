using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using System.Collections;
using LibraryProject.Users;
using System.Drawing;


namespace LibraryProject.LibraryDB
{
    /// <summary>
    /// DataBase.cs
    /// Created By Rashid Darwish 2011-08-18
    /// </summary>
    class DataBase
    {
        private static String firstName, lastName, email, userName, password;
        private static Image img;
        private static Dictionary<String, Student> studentMap = new Dictionary<String, Student>();

        /// <summary>
        /// Loading Students from the xml dataBase
        /// </summary>
        public static void loadFromXml()
        {

            XmlTextReader reader = new XmlTextReader("StudentList.xml");
            while (reader.Read())
            {
                switch (reader.NodeType)
                {
                    case XmlNodeType.Element:
                        if (reader.Name.Equals("Student"))
                        {
                            while (reader.MoveToNextAttribute())
                            {

                                if (reader.Name.Equals("FirstName"))
                                {
                                    firstName = reader.Value;
                                }
                                if (reader.Name.Equals("LastName"))
                                {
                                    lastName = reader.Value;
                                }
                                if (reader.Name.Equals("Email"))
                                {
                                    email = reader.Value;
                                }
                                if (reader.Name.Equals("Password"))
                                {
                                    password = reader.Value;
                                }
                                if (reader.Name.Equals("UserName"))
                                {
                                    userName = reader.Value;
                                }
                                if (reader.Name.Equals("Image"))
                                {
                                    img = Image.FromFile(reader.Value);
                                 
                                }
                            }
                            studentMap.Add(userName, new Student(new Contact(new Email(email), firstName, lastName, userName, password), img));
                        }
                        break;

                    case XmlNodeType.Text:
                        break;
                    case XmlNodeType.EndElement:
                        break;
                }
            }
        }

        /// <summary>
        /// saving the students into an xml file
        /// </summary>
        public static void saveToXml()
        {
            XmlTextWriter writer = new XmlTextWriter("StudentList.xml", null);
            writer.WriteStartDocument();
            writer.Formatting = Formatting.Indented;
            writer.WriteStartElement("StudentList");
            foreach (KeyValuePair<string, Student> student in studentMap)
            {
                writer.WriteStartElement("Student");
                writer.WriteAttributeString("FirstName", student.Value.ContactData.firstName);
                writer.WriteAttributeString("LastName", student.Value.ContactData.lastName);
                writer.WriteAttributeString("Email", student.Value.ContactData.emailData.Personal);
                writer.WriteAttributeString("Password", student.Value.ContactData.Password);
                writer.WriteAttributeString("UserName", student.Value.ContactData.UserName);
                if (student.Value.Image != null)
                {
                    writer.WriteAttributeString("Image", student.Value.ContactData.UserName);
                }
                else
                {
                    writer.WriteAttributeString("Image", String.Empty);
                }
                writer.WriteEndElement();
            }

            writer.WriteEndElement();
            writer.WriteEndDocument();
            writer.Close();
        }

        /// <summary>
        /// adding new student
        /// </summary>
        /// <param name="student"></param>
        /// <returns></returns>
        public static bool addStudent(Student student)
        {
            if (!studentMap.ContainsKey(student.ContactData.UserName))
            {
                img = student.Image;
                studentMap.Add(student.ContactData.UserName, student);
                img.Save(student.ContactData.UserName);
                saveToXml();
                return true;
            }
            else return false;
        }

        /// <summary>
        /// Checking if DB contains the user name
        /// </summary>
        /// <param name="key"></param>
        /// <returns></returns>
        public static bool checkDB(string key)
        {
            if (studentMap.ContainsKey(key))
            {
                return true;
            }
            else return false;
        }

        /// <summary>
        /// returns students
        /// </summary>
        /// <param name="key"></param>
        /// <param name="student"></param>
        public static bool getUser(string key, out Student student)
        {
            if (studentMap.TryGetValue(key, out student))
                return true;
            else return false;
        }
    }
}
