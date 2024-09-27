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

        // Tilføjer startpunkt for spillet

        currentRoom = room1;
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

}
