package rpg.specialities;
import rpg.items.Weapon.WeaponEnum; 

public class Skill implements Comparable<Skill>{
	
	//Identifica capacidades de un skill según su tipo
	public enum SkillEnum{
		ATTACK,
		DEFENSE,
		HEAL;
	}
	//Atributos default
	private String name = "";
	private int damage = 0;
	private int magicDamage = 0;
	private int mpCost = 0;
	private int minLevel = 1;
	private boolean learnable = true;
	private int maxTargets = 1;
	private WeaponEnum weaponType;
	private SkillEnum skillType;
	
	//Constructor de una habilidad con todos sus atributos menos el nivel que en un inicio es 1
	public Skill(String name, int damage, int magicDamage, int mpCost, int minLevel, boolean learnable,
			WeaponEnum weaponType, SkillEnum skillType) {
		this.setName(name);
		this.setDamage(damage);
		this.setMagicDamage(magicDamage);
		this.setMpCost(mpCost);
		this.setMinLevel(minLevel);
		this.setLearnable(learnable);
		this.setWeaponType(weaponType);
		this.setSkillType(skillType);
	}
	
	//Getters
	public String getName() {
		return this.name;
	}
	public int getDamage() {
		return this.damage;
	}
	public int getMagicDamage() {
		return this.magicDamage;
	}
	public int getMpCost() {
		return this.mpCost;
	}
	public int getMinLevel() {
		return this.minLevel;
	}
	public boolean isLearnable() {
		return this.learnable;
	}
	public WeaponEnum getWeaponType() {
		return this.weaponType;
	}
	public SkillEnum getSkillType() {
		return this.skillType;
	}
	public int getMaxTargets() {
		return this.maxTargets;
	}
	//Setters con condiciones para que no haya cadenas vacias ni numeros menores a 0
	public void setName(String name) {
		if (name != null && name.strip() != "" && name.length() <= 20) this.name = name;
	}
	public void setDamage(int damage) {
		if(damage >= 0) this.damage = damage;
	}
	public void setMagicDamage(int magicDamage) {
		if(this.magicDamage >= 0) this.magicDamage = magicDamage;
	}
	public void setMpCost(int mpCost) {
		if(mpCost >= 0) this.mpCost = mpCost;
	}
	public void setMinLevel(int minLevel) {
		if(minLevel >= 1) this.minLevel = minLevel;
	}
	public void setLearnable(boolean learnable) {
		this.learnable = learnable;
	}
	public void setMaxTargets(int maxTargets) {
		if(maxTargets >= 0) this.maxTargets = maxTargets;
	}
	public void setWeaponType(WeaponEnum weaponType) {
		if(weaponType.ordinal() >= WeaponEnum.MELEE.ordinal() && weaponType.ordinal() <= WeaponEnum.NONE.ordinal()) {
			this.weaponType = weaponType;
		}
	}
	private void setSkillType(SkillEnum skillType) {
		if(skillType.ordinal() >= SkillEnum.ATTACK.ordinal() && skillType.ordinal() <= SkillEnum.HEAL.ordinal()) {
			this.skillType = skillType;
		}
	}

	//Methods
	//String sencillo aparecen sus daños y su nombre
	public String toString() {
		String str = this.getName() + "\t";
		if(this.damage > 0) str += "Damage: " + this.getDamage() + "\t";
		if(this.magicDamage > 0) str += "Magic Damage: " + this.getMagicDamage();
		return str;
	}
	//String completo tiene toda la información de la skill
	public String toStringDetails() {
		String str = this.toString() + "\n";
		str += "Mp Cost: " + this.getMpCost() + "\n";
		str += "Min level: " + this.getMinLevel() + "\n";
		str += "Needed weapon type: " + this.getWeaponType() + "\n";
		return str;
	}
	
	//Comparador que ordena de forma asendente de nivel para aprender y luego por orden alfabetico
	@Override
	public int compareTo(Skill o) {
		int comp = Integer.compare(this.getMinLevel(), o.getMinLevel());
		if(comp != 0) return comp;
		
		comp = this.skillType.compareTo(o.getSkillType());
		if(comp != 0) return comp;
		
		return this.getName().compareTo(o.getName());
	}
}
