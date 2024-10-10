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

    public Item findItemByPartialName(String partialName)
    {
        Item foundItem = null;

        for (Item item : inventory)
        {
            if (item.getName().toLowerCase().contains(partialName.toLowerCase()))
            {
                if (foundItem != null)
                {
                    return null;
                }
                foundItem = item;
            }
        }
        return foundItem;
    }

    public Weapon getEquippedWeapon()
    {
        for (Item item : inventory)
        {
            if (item instanceof Weapon && ((Weapon) item).isEquipped())
            {
                return (Weapon) item;
            }
        }
        return null;
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
        Item item = findItemByPartialName(itemName);

        if (item != null)
        {
            inventory.remove(item);
            return item;
        }

        return null;
    }

    public List<Item> getInventory()
    {
        return inventory;
    }

    public void attackEnemy(Enemy enemy)
    {
        Weapon equippedWeapon = getEquippedWeapon();
        int damage;

        if (equippedWeapon == null)
        {
            damage = 5;
            System.out.println("You attack " + enemy.getName() + " with your bare hands, dealing " + damage + " damage.");
        }

        else if (!equippedWeapon.canUse())
        {
            System.out.println("Your weapon cannot be used right now.");
            return;
        }

        else
        {
            damage = equippedWeapon.getDamage();
            System.out.println("You attack " + enemy.getName() + " with the " + equippedWeapon.getName() + ", dealing " + damage + " damage.");
        }

        enemy.takeDamage(damage);

        if (!enemy.isAlive())
        {
            System.out.println("You have defeated " + enemy.getName() + "!");
        }

        else
        {
            System.out.println(enemy.getName() + " has " + enemy.getHealth() + " health remaining.");
        }

    }

    public String getInventoryString() {

        if (inventory.isEmpty())
        {
            return "Your inventory is empty.";
        }

        else if (inventory.size() == 1)
        {
            Item item = inventory.get(0);
            return "You are carrying: " + item.getName() + (item instanceof Weapon && ((Weapon) item).isEquipped() ? " (equipped)" : "");
        }

        else if (inventory.size() == 2)
        {
            Item item1 = inventory.get(0);
            Item item2 = inventory.get(1);
            return "You are carrying: " + item1.getName() + (item1 instanceof Weapon && ((Weapon) item1).isEquipped() ? " (equipped)" : "") + " & " + item2.getName() + (item2 instanceof Weapon && ((Weapon) item2).isEquipped() ? " (equipped)" : "");
        }

        else
        {
            StringBuilder inventoryString = new StringBuilder("You are carrying: ");
            for (int i = 0; i < inventory.size() - 1; i++)
            {
                Item item = inventory.get(i);
                inventoryString.append(item.getName());

                if (item instanceof Weapon && ((Weapon) item).isEquipped())
                {
                    inventoryString.append(" (equipped)");
                }

                if (i < inventory.size() - 2)
                {
                    inventoryString.append(", ");
                }
            }

            Item lastItem = inventory.get(inventory.size() - 1);
            inventoryString.append(" & ").append(lastItem.getName()).append(lastItem instanceof Weapon && ((Weapon) lastItem).isEquipped() ? " (equipped)" : "");
            return inventoryString.toString();
        }
    }







}
