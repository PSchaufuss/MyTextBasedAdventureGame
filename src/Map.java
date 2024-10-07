public class Map
{
    private Room currentRoom;

    public Map()
    {
        createRooms();
    }

    private void createRooms()
    {
        Room room1 = new Room("Room 1", "\nWelcome, peasant! This is the starting room, which fits quite well concidering this is where your adventure will begin. You may now choose the path which you feel fit, but stay warned: There may be dangers waiting for you. \nThere is exits to the east and south.");
        // Midlertidige beskrivelser for rum 2 til 9. Der er korrekte directions for dørene, så lav ikke om på dem
        Room room2 = new Room("Room 2", "A room with doors to the west and east.");
        Room room3 = new Room("Room 3", "A room with doors to the south and west.");
        Room room4 = new Room("Room 4", "A room with doors to the north and south.");
        Room room5 = new Room("Room 5", "The central room with a single exit to the south.");
        Room room6 = new Room("Room 6", "A room with doorts to the south and north");
        Room room7 = new Room("Room 7", "The south-western corner with doors to the north and east.");
        Room room8 = new Room("Room 8", "Wow! This room has three exits! There is doors to the north, west and east");
        Room room9 = new Room("Room 9", "The south-eastern corner with doors to the north and west");

        // Forbinder hvert rum med det der passer til udgangene
        room1.addExit("east", room2);
        room1.addExit("south", room4);

        room2.addExit("west", room1);
        room2.addExit("east", room3);

        room3.addExit("south", room6);
        room3.addExit("west", room2);

        room4.addExit("south", room7);
        room4.addExit("north", room1);

        room5.addExit("south", room8);

        room6.addExit("north", room3);
        room6.addExit("south", room9);

        room7.addExit("north", room4);
        room7.addExit("east", room8);

        room8.addExit("north", room5);
        room8.addExit("west", room7);
        room8.addExit("east", room9);

        room9.addExit("north", room6);
        room9.addExit("west", room8);


        // Hvor våbene befinder sig
        room1.addItem(new Item("Sword", "A sharp blade perfect for close combat."));
        room3.addItem(new Item("Bow", "A long-range weapon for attacking enemies from afar."));
        room8.addItem(new Item("Obsidian Wand", "A legendary mid-range weapon for the strongest enemies."));

        // Hvor food befinder sig
        room2.addItem(new Food("Rotten apple", "It may have expired; the color looks suspicious.", -20));
        room6.addItem(new Food("Steak", "A juicy medium-rare steak from an unknown animal. Looks safe to eat though.", +50));
        room8.addItem(new Food("Golden Banana", "Although you may have a hard time peeling it, I wouldn't hesitate eating it! It's golden!", +100));
        room4.addItem(new Food("Salad", "A healthy refreshing meal! (Who am I kidding. It's calories. Eat it and smile)", +20));
        room7.addItem(new Food("Raw Zombie Meat", "It looks contaminated. I wouldn't eat that.", -30));

        // Tilføjer startpunkt for spillet
        currentRoom = room1;


    }



    public Room getCurrentRoom()
    {
        return currentRoom;
    }

}
