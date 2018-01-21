package gameapp.Projectile;

import gameapp.Effect.Effect;
import gameapp.Effect.Explosion;
import gameapp.Effect.LongExplosion;
import gameapp.General.Logger;
import java.util.ArrayList;

/**
 *
 * @author Kurt
 */
public class Weapon {
    private class WeaponInfo {
        public WeaponInfo(String name, int damage, int maxLoad, int reloadCount, int effectIndex) {
            mName = name;
            mDamage = damage;
            mMaxLoad = maxLoad;
            mReloadCount = reloadCount;
            mEffect = effectIndex;
        }

        String mName;
        int mDamage;
        int mMaxLoad;
        int mReloadCount;
        int mEffect;
    }

    private final ArrayList<WeaponInfo> mWeaponInfo;
    private final ArrayList<ShotCallback> mShotCallbacks;

    public Weapon() {
        mWeaponInfo = new ArrayList<>();

        mWeaponInfo.add(new WeaponInfo("", 20, 0, 0, 0));
        mWeaponInfo.add(new WeaponInfo("Burst", 35, 25, 10, 0));
        mWeaponInfo.add(new WeaponInfo("Rocket", 150, 15, 5, 1));
        mWeaponInfo.add(new WeaponInfo("Spread", 35, 20, 6, 0));
        mWeaponInfo.add(new WeaponInfo("Boomer", 1, 5, 2, 2));
        mWeaponInfo.add(new WeaponInfo("Repair", -50, 3, 1, 0));

        mShotCallbacks = new ArrayList<>();
    }

    public String GetWeaponName(int type) {
        return mWeaponInfo.get(type).mName;
    }

    public int GetDamage(int type) {
        return mWeaponInfo.get(type).mDamage;
    }

    public int GetMax(int type) {
        return mWeaponInfo.get(type).mMaxLoad;
    }

    public int GetReload(int type) {
        return mWeaponInfo.get(type).mReloadCount;
    }

    public int GetEffect(int type) {
        return mWeaponInfo.get(type).mEffect;
    }

    public int GetNumWeapons() {
        return mWeaponInfo.size();
    }

    public Effect getEffect(Projectile projectile) {
        switch (mWeaponInfo.get(projectile.GetWeaponTypeIndex()).mEffect) {
            case 1:
                return new Explosion(projectile);
            case 2:
                return new LongExplosion(projectile);
            default:
                return new Effect(projectile);
        }
    }

    //add a weapon to the list dynamically
    public int addShotCallback(ShotCallback callback) {
        mShotCallbacks.add(callback);
        return mShotCallbacks.indexOf(callback);
    }

    public ArrayList<Projectile> getShot(int type, int x, int y, boolean up) {
        ArrayList<Projectile> weaponShots = new ArrayList<>();

        switch (type) {
            case 1:
                weaponShots.add(new Burst(x, y, up));
                weaponShots.add(new Burst(x, y, up));
                weaponShots.add(new Burst(x, y, up));
                if (up) {
                    Logger.mPlayerShots += 3;
                } else {
                    Logger.mEnemyShotsFired += 3;
                }
                break;
            case 2:
                weaponShots.add(new Rocket(x, y, up));
                if (up) {
                    Logger.mPlayerShots++;
                } else {
                    Logger.mEnemyShotsFired++;
                }
                break;
            case 3:
                weaponShots.add(new Spread(x, y, -1.0, 7.0, up));
                weaponShots.add(new Spread(x, y, -2.0, 6.0, up));
                weaponShots.add(new Spread(x, y, 0.0, 8.0, up));
                weaponShots.add(new Spread(x, y, 2.0, 6.0, up));
                weaponShots.add(new Spread(x, y, 1.0, 7.0, up));
                if (up) {
                    Logger.mPlayerShots += 5;
                } else {
                    Logger.mEnemyShotsFired += 5;
                }
                break;
            case 4:
                weaponShots.add(new Boomer(x, y, up));
                if (up) {
                    Logger.mPlayerShots++;
                } else {
                    Logger.mEnemyShotsFired++;
                }
                break;
            case 5:
                weaponShots.add(new Repair(x, y, up));
                break;
        }

        return weaponShots;
    }

    public ArrayList<Projectile> getCallbackShot(int index, int x, int y, boolean up) {
        return mShotCallbacks.get(index).GetShot(x, y, up);
    }
}
