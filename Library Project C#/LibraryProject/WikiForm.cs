using System;
using System.Windows.Forms;
using System.Net;
using System.Xml;
using System.Xml.XPath;
using System.IO;

namespace LibraryProject
{
    /// <summary>
    /// WikiForm.cs
    /// Created By Zarif Jawad 2011-08-20
    /// </summary>
    public partial class WikiForm : Form
    {
        /// <summary>
        /// Construtor
        /// </summary>
        public WikiForm()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Search and display event handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSearch_Click(object sender, EventArgs e)
        {
            HttpWebRequest myRequest =
  (HttpWebRequest)WebRequest.Create("http://en.wikipedia.org/wiki/Special:Export/" + txtSearch.Text.Replace(" ", "_"));
            myRequest.UserAgent = "Code Sample Web Client";

            using (HttpWebResponse response = (HttpWebResponse)myRequest.GetResponse())
            {
                using (StreamReader reader = new StreamReader(response.GetResponseStream()))
                {
                    XmlReader xmlreader = new XmlTextReader(reader);
                    String NS = "http://www.mediawiki.org/xml/export-0.5/";
                    XPathDocument xpathdoc = new XPathDocument(xmlreader);

                    xmlreader.Close();
                    XPathNavigator myXPathNavigator = xpathdoc.CreateNavigator();
                    XPathNodeIterator nodesIt = myXPathNavigator.SelectDescendants("text", NS, false);

                    while (nodesIt.MoveNext())
                    {
                        txtOut.AppendText(nodesIt.Current.InnerXml);

                    }
                }
            }
        }

        /// <summary>
        /// cancel button event handler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
