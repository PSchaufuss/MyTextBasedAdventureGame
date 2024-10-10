public class Enemy
{
    private String name;
    private int health;
    private int attackPower;

    public Enemy(String name, int health, int attackPower)
    {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName()
    {
        return name;
    }

    public int getHealth()
    {
        return health;
    }

    public void takeDamage(int damage)
    {
        health -= damage;
        if (health < 0)
        {
            health = 0;
        }
    }

    public boolean isAlive()
    {
        return health > 0;
    }

    public int getAttackPower()
    {
        return attackPower;
    }

    public void attack(Player player)
    {
        player.changeHealth(-attackPower);
    }
}
