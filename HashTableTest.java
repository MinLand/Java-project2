public class HashTableTest
{
   public static void main(String args[])
   {
      

      Book[] books = new Book[10];

      books[0] = (new Book("Ender's Game", "Card, Orson Scott", 812550706));
      books[1] = (new Book("Breakfast of Champions", "Vonnegut, Kurt", 385334206));
      books[2] = (new Book("The Alphabet of Manliness", "Maddox", 80652720));
      books[3] = (new Book("A Condeferacy of Dunces", "Toole, John Kennedy", 141182865));
      books[4] = (new Book("Dune", "Herbert, Frank", 441013597));
      books[5] = (new Book("History of Western Philosophy", "Russell, Bertrand", 415325056));
      books[6] = (new Book("Choke", "Palahniuk, Chuck", 307388921));
      books[7] = (new Book("Me Talk Pretty One Day", "Sedaris, David", 316776963));
      books[8] = (new Book("House of Leaves", "Danielewski, Mark", 375703768));
      books[9] = (new Book("Ender's Game", "Card, Orson Scott", 812550706));
      HashTable h = new HashTable(((Class<Book>)books[0].getClass()));

      for(int i = 0; i < 10; i++)
         h.put(books[i].getKey(), books[i]);


      h.print();

      System.out.printf("Getting: %s\n\n",h.get(385334206));

      System.out.printf("Removing %d\n\n", 307388921);
      h.remove(307388921);
      h.print();
      h.empty();
      h.print();
   }
}

