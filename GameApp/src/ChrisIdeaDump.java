public class ChrisIdeaDump{}
/*
Entity: name, x, y, movement_type, movement_speed, angle, model (one class that handles the image and hitbox? we should discuss the possibility of deriving hitbox from image)
    Powerup: ammo, ammo_max, fire_rate
        Weapon: 
        Consumable: duration, duration_max
    Projectile: damage, penetration_max, penetration, shield_piercing, armor_piercing
    Effect: duration
    Ship: health, health_max, armor, armor_max, shield, shield_max, weapons, weapon_current, consumables, consumable_current, fire_rate_factor, affinity, invuln,  
          collision_damage, collision_detect, health_regen, armor_regen, shield_regen, ammo_regen, drop, drop_rate
        
"Sidewinder" enemy type: a pair of enemies where when one is destroyed, the remaining one becomes 
    a new suicidal-homing sidewinder that no longer shoots (0 fire rate}. Encourages some focus/prioritizing/movement. I recommend some time delay where the suicider just explodes

"Laser" weapon type: beam that takes a delay to expand/contract to full/zero width, weak but supplements the default weapon
    and hits far away mobs with least possible delay (instant? don't think java's int goes to the speed of light in pixels, 
    but maybe one update later we can have the laser hit every pixel on screen within its initial width at the ship's angle)















*/
