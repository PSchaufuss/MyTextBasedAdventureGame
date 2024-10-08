// ArrayList funktionen, ingen yderligere forklaring nødvendig. :)
import java.util.ArrayList;

// Jeg bruger dette for at hollde styr på rummets exits, gør det nemt med adgangsgivende kriterier / nøglepar
import java.util.HashMap;

// Skal bruges som datatype for items og enemies, da vi nemmere kan skifte til forskellige listetyper uden at ændre koden
import java.util.List;

// Skal virke sammen med HashMap. Det gør det muligt at tilgå og slette værdier baseret på deres 'key'. Det tillader også fleksibilitet, så man kan skifte til en anden implementering af Map uden at ændre for meget i koden.
import java.util.Map;

// Jeg tilføjer alle de attributter, som jeg lavede på mit klassediagram.
public class Room
{
    private String name;
    private String description;
    private Map<String, Room> exits;
    private List<Item> items;
    private List<Enemy> enemies;

    // Jeg vil oprette en konstruktør, og initialisere exits, items og enemies som tomme lister.
    public Room(String name, String description)
    {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public Item removeItem(String itemName)
    {
        Item item = findItemByPartialName(itemName);
        if (item == null)
        {
            System.out.println("You have to be more specific.");
            return null;
        }
        else
        {
            items.remove(item);
            return item;
        }
    }

    public Item findItemByPartialName(String partialName) {

        Item foundItem = null;

        for (Item item : items)
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

    public List<Item> getItems()
    {
        return items;
    }

    public String getItemString()
    {
        if (items.isEmpty())
        {
            return "There are no items here.";
        }
        else
        {
            StringBuilder itemString = new StringBuilder("Items: ");
            for (Item item : items)
            {
                itemString.append(item.getName()).append(" ");
            }
            return itemString.toString().trim();
        }
    }

    // Opretter metode til at tilføje veje man kan gå fra rummet / exits
    public void addExit(String direction, Room room)
    {
        exits.put(direction, room);
    }


    // Opretter String som viser de exits der er
    public String getExitString()
    {
        StringBuilder exitString = new StringBuilder("Exits: ");
        for (String direction : exits.keySet())
        {
            exitString.append(direction).append(" ");
        }
        return exitString.toString().trim();
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public void addEnemy(Enemy enemy)
    {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy)
    {
        enemies.remove(enemy);
    }

    public List<Enemy> getEnemies()
    {
        return enemies;
    }

    public String getEnemyString()
    {
        if (enemies.isEmpty())
        {
            return "There are no enemies here.";
        }

        else
        {
            StringBuilder enemyString = new StringBuilder("Enemies: ");
            for (Enemy enemy : enemies)
            {
                if (enemy != null)
                {
                    enemyString.append(enemy.getName()).append(" ");
                }
            }
            return enemyString.toString().trim();
        }
    }

    public String getDescription()
    {
        return description + "\n" + getItemString() + "\n" + getEnemyString();
    }
}

