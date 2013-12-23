package gameapp;public class ChrisIdeaDump{}

/*

Below is a hierarchy....chart....thing...to get my ideas flowing; a revision of the current design if we were making the game 
"directionless" aka not biased to the forward direction, and more robust in general

Entity: name, x, y, movement_style, movement_speed, angle, model (one class that handles the image and hitbox? we should discuss the possibility of deriving hitbox from image)
    Sidebar: sidebar_image (for all sidebar-specific graphics; an interface)
        Powerup: drop_rate, drop_chart, effect
        Weapon: ammo, ammo_max, fire_rate
            *Weapons*
        Buff: duration, duration_max
            *Buffs*
        Projectile: damage
    Effect: duration
        *Effects*
    Ship: health, health_max, weapons, weapon_current, buffs, buff_current, fire_rate_factor, affinity, invuln,  
          offense_factor, defense_factor, collision_damage, collision_detect, health_regen, ammo_regen
        Player
        *Enemies*

"Sidewinder" enemy type: a pair of enemies where when one is destroyed, the remaining one becomes 
    (either by deleting then spawning a new type or boolean determined behavior; they could exist outside of pairs as a new type...)
    a new suicidal-homing sidewinder that no longer shoots (0 fire rate}. Encourages some focus/prioritizing/movement

"Laser" weapon type: beam that takes a delay to expand/contract to full/zero width, weak but supplements the default weapon
    and hits far away mobs with least possible delay (instant? don't think java's int goes to the speed of light in pixels, 
    but maybe one update later we can have the laser hit every pixel on screen within its lowest width at the ship's angle)















*/
