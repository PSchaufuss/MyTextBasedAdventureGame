import java.util.Scanner;

public class UserInterface
{
    private Scanner scanner;

    public UserInterface()
    {
        scanner = new Scanner(System.in);
    }

    public void printMessage(String message)
    {
        System.out.println(message);
    }

    public String getUserInput(String prompt)
    {
        System.out.println(prompt);
        return scanner.nextLine().toLowerCase();
    }

    public void close()
    {
        scanner.close();
    }
}
