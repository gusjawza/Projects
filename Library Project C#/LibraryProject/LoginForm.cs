using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using LibraryProject.Managers;
using LibraryProject.LibraryDB;
using LibraryProject.Users;

namespace LibraryProject
{
    /// <summary>
    /// LoginForm.cs
    /// Created By Zarif Jawad
    /// </summary>
    public partial class LoginForm : Form
    {
        RegistrationManager RegMgr;

        /// <summary>
        /// Default Constructor
        /// </summary>
        public LoginForm()
        {
            InitializeComponent();
            InitializeGUI();
        }

        /// <summary>
        /// Initializeing the Gui ON start Up
        /// </summary>
        private void InitializeGUI()
        {
            RegMgr = new RegistrationManager();
            DataBase.loadFromXml();
            lblLogin.Text = String.Empty;
            lblNewStudent.Text = String.Empty;
        }

        /// <summary>
        /// Login Event Handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lblLogin_Click(object sender, EventArgs e)
        {
            checkLoginInfo();   
        }

        /// <summary>
        /// Checking the User name and password
        /// </summary>
        private void checkLoginInfo()
        {
            Student student;
            Boolean errFlagg;
            Boolean ok = InputUtility.varifyUser(txtUserName.Text, txtPassword.Text, out errFlagg , out student);
            if ( ok && errFlagg)
            {
                LibraryForm libraryForm = new LibraryForm(this, student);
                libraryForm.Show();
                this.Hide();
            }
            else if (!ok && !errFlagg)
            {
                MessageBox.Show(string.Format("Invalid input in UserName fields!"), "Error!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtUserName.Focus();
                txtUserName.SelectAll();
            }
            else if (!ok && errFlagg)
            {
                MessageBox.Show(string.Format("Invalid input in Passwords fields!"), "Error!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtPassword.Focus();
                txtPassword.SelectAll();
            }
        }

        /// <summary>
        /// NewStudent Event Handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lblNewStudent_Click(object sender, EventArgs e)
        {
            RegistrationForm registrationForm = new RegistrationForm();
            if (registrationForm.ShowDialog() == DialogResult.OK)
            {
                RegMgr.addStudent(registrationForm.Student);
                txtUserName.Text = registrationForm.Student.ContactData.UserName;
                txtPassword.Text = registrationForm.Student.ContactData.Password;   
            }
        }

        /// <summary>
        /// Exit Event Handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            exitProgram();
        }

        /// <summary>
        /// Method to exit the application
        /// </summary>
        private void exitProgram()
        {
            DialogResult result = MessageBox.Show("The Application is about to terminate, Continue?",
                         "Exit The Application", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
            if (result == DialogResult.Yes)
            {
                Application.Exit();
            }
        }

        /// <summary>
        /// login Out
        /// </summary>
        public void logOut()
        {
            txtUserName.Text = string.Empty;
            txtPassword.Text = string.Empty;
        }
    }
}
