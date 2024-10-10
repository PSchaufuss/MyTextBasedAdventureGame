public class MeleeWeapon extends Weapon
{
    public MeleeWeapon(String name, String description, int damage)
    {
        super(name, description, damage);
    }

    // MeleeWeapon sætter jeg som altid true, da de altid skal kunne bruges
    public boolean canUse()
    {
        return true;
    }
}
