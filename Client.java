import java.util.InputMismatchException;
import java.util.Scanner;
import java.net.InetAddress;
import java.net.Socket;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket client;
    private String hostServer;
    private Scanner systemin;

    public Client(String host) {
        hostServer = host;
        systemin = new Scanner(System.in);
    }

    public void runClient() {
        try {
            connect();
            process();
        } catch (EOFException eofE) {
            System.out.println("Client terminated");
        } catch (IOException ioE) {
            System.out.println(ioE);
        } finally {
            closeConnection();
        }
    }

    private void connect() throws IOException {
        System.out.println("Attempting to connect with Server");
        client = new Socket(InetAddress.getByName(hostServer), 34567);
        System.out.println("Connected!");
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();
        input = new ObjectInputStream(client.getInputStream());
    }

    private void process() throws IOException {
        boolean exit = false;
        while (!exit) {
            try {
                System.out.print("Enter command >");
                String command = systemin.next();

                if (command.equals("get")) {
                    // request a record from the server
                    output.writeObject(command);
                    output.writeInt(systemin.nextInt());
                    output.flush();
                    AccountRecordSerializableKeyed account
                            = (AccountRecordSerializableKeyed) input.readObject();
                    if (account != null) {
                        System.out.print(account);
                    } else {
                        System.out.println("null");
                    }

                } else if (command.equals("remove")) {
                    // remove a record
                    output.writeObject(command);
                    output.writeInt(systemin.nextInt());
                    output.flush();

                } else if (command.equals("list")) {
                    // List the record
                    System.out.println("Requested server side print.");
                    output.writeObject(command);
                    output.flush();

                } else if (command.equals("put")) {
                    // Add a record to the server
                    int account = systemin.nextInt();
                    String firstName = systemin.next();
                    String lastName = systemin.next();
                    double balance = systemin.nextDouble();
                    output.writeObject(command);
                    output.writeInt(account);
                    output.writeObject(firstName);
                    output.writeObject(lastName);
                    output.writeDouble(balance);
                    output.flush();

                } else if (command.equals("stop")) {
                    // Stop the server
                    output.writeObject(command);
                    output.flush();
                    System.out.println("Exiting...");
                    exit = true;
                }
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                systemin.nextLine();
            }
        }
    }

    private void closeConnection() {
        System.out.println("Closing connection");

        try {
            output.close();
            input.close();
            client.close();
        } catch (IOException ioE) {
            System.out.println(ioE);
        }
    }
}

