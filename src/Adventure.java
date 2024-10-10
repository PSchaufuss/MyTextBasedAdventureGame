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
                    ui.printMessage("You have taken the " + itemName + ". " + item.getDescription() + ".");
                }
            }

            else if (command.startsWith("drop"))
            {
                String itemName = command.substring(5);
                Item item = player.findItemByPartialName(itemName);

                if (item == null)
                {
                    ui.printMessage("You don't have anything like " + itemName + " in your inventory.");
                }

                else
                {
                    currentRoom.addItem(item);
                    player.removeItem(item.getName());
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
                ui.printMessage("Available commands: \nGo [North/East/South/West] \nLook \nHelp \nExit \nHealth \nEat \nTake \nEquip \nAttack");
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

            else if (command.startsWith("equip"))
            {
                String weaponName = command.substring(6).trim();
                Item item = player.findItemByPartialName(weaponName);

                if (item == null)
                {
                    ui.printMessage("You do not have any item like " + weaponName);
                }

                else if (!(item instanceof Weapon))
                {
                    ui.printMessage(weaponName + " is not a weapon.");
                }

                else
                {
                    Weapon weapon = (Weapon) item;
                    weapon.equip();
                    ui.printMessage("You have equipped the " + weapon.getName() + ".");
                }
            }


            else if (command.startsWith("attack"))
            {
                Enemy target = null;
                String targetName = command.length() > 7 ? command.substring(7).trim() : "";

                if (!targetName.isEmpty())
                {
                    for (Enemy enemy : currentRoom.getEnemies())
                    {
                        if (enemy.getName().toLowerCase().contains(targetName.toLowerCase()))
                        {
                            target = enemy;
                            break;
                        }
                    }
                }

                if (target == null && !currentRoom.getEnemies().isEmpty())
                {
                    target = currentRoom.getEnemies().get(0);
                }

                if (target == null)
                {
                    ui.printMessage("There are no enemies here to attack.");
                }

                else
                {
                    Weapon equippedWeapon = player.getEquippedWeapon();
                    int damage;

                    if (equippedWeapon == null)
                    {
                        damage = 5;
                        ui.printMessage("You attack " + target.getName() + " with your bare hands, dealing " + damage + " damage.");
                    }

                    else if (!equippedWeapon.canUse())
                    {
                        ui.printMessage("Your weapon cannot be used right now.");
                        return;
                    }

                    else
                    {
                        damage = equippedWeapon.getDamage();
                        ui.printMessage("You attack " + target.getName() + " with the " + equippedWeapon.getName() + ", dealing " + damage + " damage.");

                       if (equippedWeapon instanceof RangedWeapon)
                       {
                           ((RangedWeapon) equippedWeapon).useAmmo();
                           ui.printMessage("You used ammunition. Remaining ammo: " + ((RangedWeapon) equippedWeapon).getAmmunition());
                       }
                    }

                    target.takeDamage(damage);
                    ui.printMessage(target.getName() + " has " + target.getHealth() + " health remaining.");

                        if (!target.isAlive())
                        {
                            ui.printMessage("You have defeated " + target.getName() + "!");
                            currentRoom.removeEnemy(target);
                        }
                        else
                        {
                            int enemyDamage = target.getAttackPower();
                            ui.printMessage(target.getName() + " attacks you, dealing " + enemyDamage + " damage!");

                            target.attack(player);
                            ui.printMessage("Your health is now: " + player.getHealth());
                        }
                }
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
