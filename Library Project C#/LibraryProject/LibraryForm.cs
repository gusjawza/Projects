using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Facebook;
using LibraryProject;

namespace LibraryProject
{
    /// <summary>
    /// LibraryForm.cs
    /// Created By Rashid Darwish 2011-08-18
    /// </summary>
    public partial class LibraryForm : Form
    {
        private const string AppId = "{app id}";
        private string[] extendedPermissions = new[] { "user_about_me", "offline_access" };
        private LoginForm loginForm;
        private Users.Student student;

        /// <summary>
        /// Constructor with 2 parameters
        /// </summary>
        /// <param name="loginForm"></param>
        /// <param name="student"></param>
        public LibraryForm(LoginForm loginForm, Users.Student student)
        {
            InitializeComponent();
            this.loginForm = loginForm;
            this.student = student;
            InitializeGUI();
        }

        /// <summary>
        /// Iniyializing the GUI
        /// </summary>
        private void InitializeGUI()
        {
            picBoxLibForm.Image = student.Image;
            lblUserName.Text = student.ContactData.UserName;
            assignEBooks();
        }

        /// <summary>
        /// E-Books Event Handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lblEBooks_Click(object sender, EventArgs e)
        {
            this.BackgroundImage = LibraryResource.BookShelf1;
            assignEBooks();
        }

        /// <summary>
        /// Assigning the E-Books
        /// </summary>
        private void assignEBooks()
        {
            reDesignTheShelf();
            lblBook6.Image = LibraryResource.JavaBook;
            lblBook5.Image = LibraryResource.JavaNetworkBook;
            lblBook10.Image = LibraryResource.SoftwareEngineering1;
            lblBook11.Image = LibraryResource.SoftwareEngineering2;
            lblBook3.Image = LibraryResource.Theory_PracticeBook;
            lblBook1.Image = LibraryResource.VisualBasicBook;
        }

        /// <summary>
        /// Assigning the books
        /// </summary>
        private void assignBooks()
        {
            reDesignTheShelf();
            lblBook1.Image = LibraryResource.DataBase;
            lblBook2.Image = LibraryResource.JavaBook;
            lblBook3.Image = LibraryResource.JavaNetworkBook;
            lblBook4.Image = LibraryResource.SoftwareEngineering1;
            lblBook7.Image = LibraryResource.SoftwareEngineering2;
            lblBook9.Image = LibraryResource.Theory_PracticeBook;
            lblBook12.Image = LibraryResource.VisualBasicBook;
            lblBook13.Image = LibraryResource.VisualBasicBook2;
            lblBook6.Image = LibraryResource.TheMythicalMan_Month;
        }

        /// <summary>
        /// deleting the books
        /// </summary>
        private void reDesignTheShelf()
        {
            lblBook1.Image = null;
            lblBook2.Image = null;
            lblBook3.Image = null;
            lblBook4.Image = null;
            lblBook5.Image = null;
            lblBook6.Image = null;
            lblBook7.Image = null;
            lblBook8.Image = null;
            lblBook9.Image = null;
            lblBook10.Image = null;
            lblBook11.Image = null;
            lblBook12.Image = null;
            lblBook13.Image = null;
        }

        /// <summary>
        /// Books Event Handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lblBooks_Click(object sender, EventArgs e)
        {
            this.BackgroundImage = LibraryResource.BookShelf2;
            assignBooks();
        }

        /// <summary>
        /// Journals Event Handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lblJournals_Click(object sender, EventArgs e)
        {
            this.BackgroundImage = LibraryResource.BookShelf3;
            assigningJournals();
        }

        /// <summary>
        /// Assigning Journels
        /// </summary>
        private void assigningJournals()
        {
            reDesignTheShelf();
            lblBook6.Image = LibraryResource.JouranlMEGaron2010;
            lblBook9.Image = LibraryResource.JouranlACE;
        }

        /// <summary>
        /// Magazines Event Handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lblMagazines_Click(object sender, EventArgs e)
        {
            this.BackgroundImage = LibraryResource.BookShelf4;
            assigningMagazines();
        }

        /// <summary>
        /// Assigning the Magazines
        /// </summary>
        private void assigningMagazines()
        {

            reDesignTheShelf();
            lblBook1.Image = LibraryResource.MagazineWindows7;
            lblBook3.Image = LibraryResource.MagazineScientificA;
            lblBook4.Image = LibraryResource.MagazinePopularMechanicsjpeg;
            lblBook6.Image = LibraryResource.MagazinePCWorld;
            lblBook9.Image = LibraryResource.MagazinePC;
            lblBook11.Image = LibraryResource.MagazinenRPM;
            lblBook5.Image = LibraryResource.MagazinenNewElectronics;
            lblBook13.Image = LibraryResource.MagazineMacWorld;
            lblBook10.Image = LibraryResource.MagazineLinux;
            lblBook7.Image = LibraryResource.MagazineComputerShopper;
        }

        /// <summary>
        /// logOut event handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lblLogOut_Click(object sender, EventArgs e)
        {
            this.Hide();
            loginForm.logOut();
            loginForm.Show();
        }

        /// <summary>
        /// Wiki Event Handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lblWikiForm_Click(object sender, EventArgs e)
        {
            WikiForm wiki = new WikiForm();
            wiki.Show();
        }

        /// <summary>
        /// FaceBook Event Handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lblFaceBook_Click(object sender, EventArgs e)
        {
            var fbLoginDialog = new FacebookForm(AppId, extendedPermissions, true);
            fbLoginDialog.ShowDialog();
        }

    }
}
