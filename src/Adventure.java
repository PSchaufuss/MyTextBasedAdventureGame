public class Adventure
{
    private Room currentRoom;
    private Map map;
    private UserInterface ui;
    private Player player;

    public Adventure()
    {
        map = new Map();
        currentRoom = map.getCurrentRoom();
        ui = new UserInterface();
        player = new Player();
    }


    public void play()
    {
        ui.printMessage("\nWelcome to the Adventure Game! Type 'help' if you want to see available commands.");

        while (true)
        {
            ui.printMessage(currentRoom.getDescription());
            ui.printMessage(currentRoom.getExitString());
            ui.printMessage(player.getInventoryString());
            String command = ui.getUserInput("> ");

            if (command.startsWith("take "))
            {
                String itemName = command.substring(5);
                Item item = currentRoom.findItemByPartialName(itemName);

                if (item == null)
                {
                    ui.printMessage("There is nothing like " + itemName + " to take around here.");
                }

                else
                {
                    player.addItem(item);
                    currentRoom.removeItem(item.getName());
                    ui.printMessage("You have taken the " + itemName + ".");
                }
            }

            else if (command.startsWith("drop"))
            {
                String itemName = command.substring(5);
                Item item = player.removeItem(itemName);

                if (item == null)
                {
                    ui.printMessage("You don't have anything like " + itemName + " in your inventory.");
                }

                else
                {
                    currentRoom.addItem(item);
                    ui.printMessage("Your have dropped the " + itemName + ".");
                }
            }

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

                if (nextRoom == null)
                {
                    ui.printMessage("You cannot go that way.");
                }
                else
                {
                    currentRoom = nextRoom;
                }
            }

            else if (command.equals("inventory") || command.equals("inv") || command.equals("invent"))
            {
                ui.printMessage(player.getInventoryString());
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
                ui.printMessage("Available commands: \nGo [North/East/South/West] \nLook \nHelp \nExit \nHealth \nEat");
            }

            else if (command.equals("health"))
            {
                double healthPercentage = player.getHealthPercentage();
                String healthDescription;

                if (healthPercentage > 75)
                {
                    healthDescription = "You are in excellent shape!";
                }
                else if (healthPercentage > 50)
                {
                    healthDescription = "Your health is fine, but be careful on your journey.";
                }
                else if (healthPercentage > 25)
                {
                    healthDescription = "You are not feeling too well. Find a source of energy before challenging more foes.";
                }
                else
                {
                    healthDescription = "You are in critical condition! Find food quickly!";
                }

                ui.printMessage("Health: " + player.getHealth() + " (" + (int) healthPercentage + "%) - " + healthDescription);
            }

            else if (command.startsWith("eat "))
            {
                String foodName = command.substring(4).trim();
                Item item = player.removeItem(foodName);

                if (item == null)
                {
                    item = currentRoom.findItemByPartialName(foodName);
                }

                if (item == null)
                {
                    ui.printMessage("There is no " + foodName + " to eat.");
                }

                else if (!(item instanceof Food))
                {
                    ui.printMessage("You cannot eat " + foodName + ".");

                    if (currentRoom.getItems().contains(item))
                    {
                        currentRoom.addItem(item);
                    }
                    else
                    {
                        player.addItem(item);
                    }
                }

                else
                {
                    Food food = (Food) item;
                    int healthChange = food.getHealthPoints();
                    player.changeHealth(healthChange);

                    if (healthChange > 0)
                    {
                        ui.printMessage("You have eaten " + foodName + " and gained " + healthChange + " health.");
                    }
                    else
                    {
                        ui.printMessage("You have eaten " + foodName + " and lost " + Math.abs(healthChange) + " health.");
                    }
                }
            }

            else
            {
                ui.printMessage("Unknown command.");
            }
        }
    }


    public static void main (String[]args)
    {
        Adventure game = new Adventure();
        game.play();
    }
}
