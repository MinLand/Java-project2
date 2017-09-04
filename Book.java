public class Book implements hasKey
{
       private String title;
       private String author;
      private int ISBN;

       public Book()
       {
               ISBN = 0;
               title = "";
               author = "";
       }

       public Book(String t, String a, int i)
       {
               ISBN = i;
               title = t;
               author = a;
       }

       public String getTitle()
       {
               return title;
       }

       public String getAuthor()
       {
               return author;
       }

       public boolean equals(Object b)
       {
               if(this.ISBN == ((Book)b).ISBN)
                       return true;
               else
                       return false;
       }

       public String toString()
       {
               return String.format("%s\n%s\n%d", title, author, ISBN);
       }

      public int getKey()
      {
            return ISBN;
      }
}
