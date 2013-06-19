using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Facebook;

namespace LibraryProject
{
    /// <summary>
    /// FacebookForm.cs
    /// Created by Zarif Jawad 2011-08-20
    /// </summary>
    public partial class FacebookForm : Form
    {
        private Uri navigateUrl;

        /// <summary>
        /// Constructor with two parameters
        /// </summary>
        /// <param name="appId"></param>
        /// <param name="extendedPermissions"></param>
        public FacebookForm(string appId, string[] extendedPermissions)
            : this(appId, extendedPermissions, false)
        {
        }

        /// <summary>
        /// Constructor with three parameters
        /// </summary>
        /// <param name="appId"></param>
        /// <param name="extendedPermissions"></param>
        /// <param name="logout"></param>
        public FacebookForm(string appId, string[] extendedPermissions, bool logout)
        {
            var oauth = new FacebookOAuthClient { AppId = appId };
            var loginParameters = new Dictionary<string, object>
                    {
                        { "response_type", "token" },
                        { "display", "popup" }
                    };

            if (extendedPermissions != null && extendedPermissions.Length > 0)
            {
                var scope = new StringBuilder();
                scope.Append(string.Join(",", extendedPermissions));
                loginParameters["scope"] = scope.ToString();
            }
            var loginUrl = oauth.GetLoginUrl(loginParameters);
            if (logout)
            {
                var logoutParameters = new Dictionary<string, object>
                                           {
                                               { "next", loginUrl }
                                           };

                this.navigateUrl = oauth.GetLogoutUrl(logoutParameters);
            }
            else
            {
                this.navigateUrl = loginUrl;
            }

            InitializeComponent();
        }

        /// <summary>
        /// Loading web
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void FacebookLoginDialog_Load(object sender, EventArgs e)
        {
            webBrowser.Navigate(this.navigateUrl.AbsoluteUri);
        }

        /// <summary>
        /// Web browser event handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void webBrowser_Navigated(object sender, WebBrowserNavigatedEventArgs e)
        {
            FacebookOAuthResult result;
            if (FacebookOAuthResult.TryParse(e.Url, out result))
            {
                this.FacebookOAuthResult = result;
                this.DialogResult = result.IsSuccess ? DialogResult.OK : DialogResult.No;
            }
            else
            {
                this.FacebookOAuthResult = null;
            }
        }

        /// <summary>
        /// FacebookOAuthResult Property
        /// </summary>
        public FacebookOAuthResult FacebookOAuthResult { get; private set; }

        private void webBrowser_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {

        }
    }
}