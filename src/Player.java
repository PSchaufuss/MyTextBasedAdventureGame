import java.util.ArrayList;
import java.util.List;

public class Player
{
    private List<Item> inventory;
    private int health;
    private int maxHealth;

    public Player()
    {
        inventory = new ArrayList<>();
        this.maxHealth = 100;
        this.health = maxHealth;
    }

    public int getHealth()
    {
        return health;
    }

    public void changeHealth(int amount)
    {
        this.health += amount;
        if (this.health > maxHealth)
        {
            this.health = maxHealth;
        }
        else if (this.health < 0)
        {
            this.health = 0;
        }
    }

    public double getHealthPercentage()
    {
        return ((double) health / maxHealth) * 100;
    }

    public void addItem(Item item)
    {
        inventory.add(item);
    }

    public Item removeItem(String itemName)
    {
        for (Item item : inventory)
        {
            if (item.getName().equalsIgnoreCase(itemName))
            {
                inventory.remove(item);
                return item;
            }
        }
        return null;
    }

    public List<Item> getInventory()
    {
        return inventory;
    }

    public String getInventoryString() {

        if (inventory.isEmpty())
        {
            return "Your inventory is empty.";
        }

        else if (inventory.size() == 1)
        {
            return "You are carrying: " + inventory.get(0).getName();
        }

        else if (inventory.size() == 2)
        {
            return "You are carrying: " + inventory.get(0).getName() + " & " + inventory.get(1).getName();
        }

        else
        {
            StringBuilder inventoryString = new StringBuilder("You are carrying: ");
            for (int i = 0; i < inventory.size() - 1; i++)
            {
                inventoryString.append(inventory.get(i).getName());
                if (i < inventory.size() - 2)
                {
                    inventoryString.append(", ");
                }
            }
            inventoryString.append(" & ").append(inventory.get(inventory.size() - 1).getName());
            return inventoryString.toString();
        }
    }







}
