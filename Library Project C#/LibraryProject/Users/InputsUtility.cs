using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using LibraryProject.LibraryDB;

namespace LibraryProject.Users
{
    /// <summary>
    /// InputUtility.cs
    /// Created By Rashid Darwish 2011-08-20
    /// </summary>
    class InputUtility
    {
        /// <summary>
        /// method checks if its a valid string
        /// </summary>
        /// <param firstName="stringToCheck"></param>
        /// <param firstName="stringValue"></param>
        /// <returns>returns true if its valid else false</returns>
        public static bool getString(string stringToCheck, out string stringValue)
        {

            if (!string.IsNullOrEmpty(stringToCheck) && !string.IsNullOrWhiteSpace(stringToCheck))
            {
                stringValue = stringToCheck;
                return true;
            }

            else
                stringValue = string.Empty;
            return false;
        }

        /// <summary>
        /// Checkin if the password matches
        /// </summary>
        /// <param name="password"></param>
        /// <param name="rePassword"></param>
        /// <returns></returns>
        public static bool checkPassword(string password, string rePassword, out string passwordResult)
        {
            if (!string.IsNullOrEmpty(rePassword) && !string.IsNullOrWhiteSpace(rePassword) && !string.IsNullOrEmpty(password) && !string.IsNullOrWhiteSpace(password) && password.Equals(rePassword))
            {
                passwordResult = password;
                return true;
            }else
            {
                passwordResult = string.Empty;
                return false;
            }
        }

        /// <summary>
        /// Checkin if the email is valid
        /// </summary>
        /// <param name="email"></param>
        /// <returns></returns>
        public static bool IsValidEmail(string email, out string validEmail)
        {
            string domains = @"^[-a-zA-Z0-9][-.a-zA-Z0-9]*@[-.a-zA-Z0-9]+(\.[-.a-zA-Z0-9]+)*\.
    (com|edu|info|gov|int|mil|net|org|biz|name|museum|coop|aero|pro|tv|[a-zA-Z]{2})$";

            Regex check = new Regex(domains, RegexOptions.IgnorePatternWhitespace);
            if (string.IsNullOrEmpty(email))
            {
                validEmail = string.Empty;
                return false;
            }
            else
            {
                validEmail = email;
                return check.IsMatch(email);
            }
        }

        /// <summary>
        /// Checking the userName
        /// </summary>
        /// <param name="stringToCheck"></param>
        /// <param name="stringValue"></param>
        /// <returns></returns>
        public static bool getUserName(string stringToCheck, out string stringValue)
        {

            if (!string.IsNullOrEmpty(stringToCheck) && !string.IsNullOrWhiteSpace(stringToCheck) && !DataBase.checkDB(stringToCheck))
            {
                stringValue = stringToCheck;
                return true;
            }

            else
                stringValue = string.Empty;
            return false;
        }

        /// <summary>
        /// Varifing the user
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="password"></param>
        /// <param name="errFlagg"></param>
        /// <param name="student"></param>
        /// <returns></returns>
        public static bool varifyUser(string userName, string password, out bool errFlagg, out Student student)
        {
            if (DataBase.getUser(userName, out student))
            {
                if (student.ContactData.Password.Equals(password))
                {
                    errFlagg = true;
                    return true;
                }
                else
                {
                    errFlagg = true;
                    return false;
                }
            }
            else
            {
                errFlagg = false;
                return false;
            }
        }
    }
}
