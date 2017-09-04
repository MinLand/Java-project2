import java.net.ServerSocket;
import java.net.Socket;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Server {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    private HashTable<AccountRecordSerializableKeyed> accountTable;

    public void runServer() {
        try {
            server = new ServerSocket(34567, 1);

            try {
                connect();
                process();
            } catch (EOFException eofE) {
                System.out.println("Server terminated");
            } finally {
                closeConnection();
            }
        } catch (IOException ioE) {
            System.out.println(ioE);
        }
    }

    private void connect() throws IOException {

        System.out.println("Waiting for connection...");
        connection = server.accept();
        System.out.println("Connection accepted!");

        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();

        input = new ObjectInputStream(connection.getInputStream());

        accountTable = new HashTable<AccountRecordSerializableKeyed>(
                AccountRecordSerializableKeyed.class);
    }

    private void process() throws IOException {
        boolean exit = false;
        while (!exit) {
            try {
                String command = (String) input.readObject();
                if (command.equals("get")) {
                    int key = input.readInt();
                    // Send a record to the client
                    output.writeObject(accountTable.get(key));
                    output.flush();

                } else if (command.equals("remove")) {
                    // Remove a record
                    int key = input.readInt();
                    accountTable.remove(key);
                    System.out.println("Remove " + key);

                } else if (command.equals("list")) {
                    // List the records
                    System.out.println("Server side print request...");
                    accountTable.print();

                } else if (command.equals("put")) {
                    // Add a record
                    AccountRecordSerializableKeyed account = new AccountRecordSerializableKeyed(
                            input.readInt(), (String)input.readObject(),
                            (String)input.readObject(), input.readDouble());
                    accountTable.put(account.getKey(), account);
                    System.out.print(account);

                } else if (command.equals("stop")) {
                    System.out.println("Exiting...");
                    exit = true;
                }

            } catch (ClassNotFoundException cnfE) {
                System.out.println(cnfE);
            }
        }
    }

    private void closeConnection() {
        System.out.println("Closing connection");

        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException ioE) {
            System.out.println(ioE);
        }
    }
}
