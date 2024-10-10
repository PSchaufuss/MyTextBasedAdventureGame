public abstract class Weapon extends Item
{
    private boolean isEquipped;
    protected int damage;

    public Weapon(String name, String description, int damage)
    {
        super(name, description);
        this.isEquipped = false;
        this.damage = damage;
    }

    public boolean isEquipped()
    {
        return isEquipped;
    }

    public void equip()
    {
        isEquipped = true;
    }

    public void unequip()
    {
        isEquipped = false;
    }

    public int getDamage()
    {
        return damage;
    }

    public abstract boolean canUse();
}

