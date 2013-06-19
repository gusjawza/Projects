using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using LibraryProject.Users;
using LibraryProject.Managers;

namespace LibraryProject
{
    /// <summary>
    /// RegistrationForm.cs
    /// Created By Rashid Darwish 2011-08-18
    /// </summary>
    public partial class RegistrationForm : Form
    {
        private Student student;
        private Image img;

        /// <summary>
        /// Default Constructor
        /// </summary>
        public RegistrationForm()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Add Picture Event Handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lblAddPicture_Click(object sender, EventArgs e)
        {
            OpenFileDialog openFileD = new OpenFileDialog();
            if (openFileD.ShowDialog() == DialogResult.OK)
            {
                img = new Bitmap(Image.FromFile(openFileD.FileName), 100, 100);
                RegFormPictureBox.Image = img;
            }
        }

        /// <summary>
        /// Cancel Button Event Handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary>
        /// button register event handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnRegister_Click(object sender, EventArgs e)
        {
            String firstName, lastName, email, userName, password;
            Boolean ok = false;
            ok = validateInputs(out firstName, out lastName, out email, out userName, out password);
            if (ok)
            {
                if (img == null)
                {
                    this.img = LibraryResource.NotAvailable;
                }
                student = new Student(new Contact(new Email(email), firstName, lastName, userName, password), img);  
                this.DialogResult = DialogResult.OK;
                this.Close();
            }
        }

        /// <summary>
        /// checking the registration inputs if valid
        /// </summary>
        /// <param name="firstName"></param>
        /// <param name="lastName"></param>
        /// <param name="email"></param>
        /// <param name="userName"></param>
        /// <param name="password"></param>
        /// <param name="rePassWord"></param>
        /// <returns></returns>
        private bool validateInputs(out string firstName, out string lastName, out string email, out string userName, out string password)
        {
            if (validateFirstName(out firstName) && validateLastName(out lastName) && validateEmail(out email) && validateUserName(out userName) && validatePasswords(out password))
            {
                return true;
            }
            else
            {
                firstName = string.Empty;
                lastName = string.Empty;
                email = string.Empty;
                userName = string.Empty;
                password = string.Empty;
                return false;

            }
        }

        /// <summary>
        /// Checking the passwords 
        /// </summary>
        /// <param name="password"></param>
        /// <param name="rePassWord"></param>
        /// <returns></returns>
        private bool validatePasswords(out string password)
        {
            if (InputUtility.checkPassword(txtPassword.Text, txtRePassword.Text, out password))
            {
                return true;
            }
            else
            {
                MessageBox.Show(string.Format("Invalid input in Passwords fields!"), "Error!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtPassword.Focus();
                txtPassword.SelectAll();
                return false;
            }
        }

        /// <summary>
        /// validate the UserName
        /// </summary>
        /// <param name="userName"></param>
        /// <returns></returns>
        private bool validateUserName(out string userName)
        {
            if (InputUtility.getUserName(txtUserName.Text, out userName))
            {
                return true;
            }
            else
            {
                MessageBox.Show(string.Format("Invalid input in User Name field! Its empty or already in use"), "Error!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtUserName.Focus();
                txtUserName.SelectAll();
                return false;
            }
        }

        /// <summary>
        /// Validate The email 
        /// </summary>
        /// <param name="email"></param>
        /// <returns></returns>
        private bool validateEmail(out string email)
        {
            if (InputUtility.IsValidEmail(txtEmail.Text, out email))
            {
                return true;
            }
            else
            {
                MessageBox.Show(string.Format("Invalid input in Email field!"), "Error!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtEmail.Focus();
                txtEmail.SelectAll();
                return false;
            }
        }

        /// <summary>
        /// Validate the last Name input
        /// </summary>
        /// <param name="lastName"></param>
        /// <returns></returns>
        private bool validateLastName(out string lastName)
        {
            if (InputUtility.getString(txtLastName.Text, out lastName))
            {
                return true;
            }
            else
            {
                MessageBox.Show(string.Format("Invalid input in Last Name field!"), "Error!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtLastName.Focus();
                txtLastName.SelectAll();
                return false;
            }
        }

        /// <summary>
        /// validate FirstName
        /// </summary>
        /// <param name="firstName"></param>
        /// <returns></returns>
        private bool validateFirstName(out string firstName)
        {
            if (InputUtility.getString(txtFirstName.Text, out firstName))
            {
                return true;
            }
            else
            {

                MessageBox.Show(string.Format("Invalid input in First Name field!"), "Error!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtFirstName.Focus();
                txtFirstName.SelectAll();
                return false;
            }
        }

        /// <summary>
        /// student property
        /// </summary>
        public Student Student { get { return student; } set { student = value;} }

    }
}
