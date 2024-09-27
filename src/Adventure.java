public class Adventure
{
    private Room currentRoom;
    private Map map;
    private UserInterface ui;

    public Adventure()
    {
        map = new Map();
        currentRoom = map.getCurrentRoom();
        ui = new UserInterface();
    }


    public void play()
    {
        ui.printMessage("Welcome to the Adventure Game! Type 'help' if you want to see available commands.");

        while (true)
        {
            ui.printMessage(currentRoom.getDescription());
            ui.printMessage(currentRoom.getExitString());
            String command = ui.getUserInput("> ");


            command = command.toLowerCase().trim();

            if (command.equals("north") || command.equals("n"))
            {
                command = "go north";
            }
            else if (command.equals("south") || command.equals("s"))
            {
                command = "go south";
            }
            else if (command.equals("east") || command.equals("e"))
            {
                command = "go east";
            }
            else if (command.equals("west") || command.equals("w"))
            {
                command = "go west";
            }

            if (command.startsWith("go "))
            {
                String direction = command.substring(3);
                Room nextRoom = currentRoom.getExit(direction);

                if(nextRoom == null)
                {
                    ui.printMessage("You cannot go that way.");
                }
                else
                {
                    currentRoom = nextRoom;
                }
            }

            else if (command.equals("look"))
            {
                ui.printMessage(currentRoom.getDescription());
            }
            else if (command.equals("exit"))
            {
                ui.printMessage("Exiting the game.");
                break;
            }
            else if (command.equals("help"))
            {
                ui.printMessage("Available commands: \nGo [North/East/South/West] \nLook \nHelp \nExit");
            }
            else
            {
                ui.printMessage("Unknown command.");
            }
        }

    }



    public static void main(String[] args)
    {
        Adventure game = new Adventure();
        game.play();
    }
}
