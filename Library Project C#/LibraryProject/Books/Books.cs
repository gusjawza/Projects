using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LibraryProject.Books
{
    /// <summary>
    /// Book.cs
    /// Created By Zarif Jawad 2011-08-20
    /// </summary>
    class Books
    {
        private String title;
        private String author;
        private String publisher;
        private int year;
        private DateTime borrowDate;
        private DateTime returnDate;

        public Books(String title, String author, String publisher, int year, DateTime borrowDate, DateTime returnDate)
        {
            this.title = title;
            this.author = author;
            this.publisher = publisher;
            this.year = year;
            this.borrowDate = borrowDate;
            this.returnDate = returnDate;
        }

        /// <summary>
        /// Year Property
        /// </summary>
        public int Year
        {
            get { return year; }
            set { year = value; }
        }

        /// <summary>
        /// BorrowDate Property
        /// </summary>
        public DateTime BorrowDate
        {
            get { return borrowDate; }
            set { borrowDate = value; }
        }

        /// <summary>
        /// ReturnDate Property
        /// </summary>
        public DateTime ReturnDate
        {
            get { return returnDate; }
            set { returnDate = value; }
        }


        /// <summary>
        /// Author Property
        /// </summary>
        public String Author
        {
            get { return author; }
            set { author = value; }
        }

        /// <summary>
        /// Publisher Property
        /// </summary>
        public String Publisher
        {
            get { return publisher; }
            set { publisher = value; }
        }

        /// <summary>
        /// Title Property
        /// </summary>
        public string Title
        {
            get { return title; }
            set { title = value; }
        }

    }
}
