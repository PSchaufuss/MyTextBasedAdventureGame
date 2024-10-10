public class RangedWeapon extends Weapon
{
    private int ammunition;

    public RangedWeapon(String name, String description, int ammunition, int damage)
    {
        super(name, description, damage);
        this.ammunition = ammunition;
    }

    public boolean canUse()
    {
        return ammunition > 0;
    }

    public int getAmmunition()
    {
        return ammunition;
    }

    public void useAmmo()
    {
        if (ammunition > 0)
        {
            ammunition--;
        }
    }
}
