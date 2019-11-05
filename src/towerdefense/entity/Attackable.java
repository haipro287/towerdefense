package towerdefense.entity;

import towerdefense.entity.bullet.AbstractBullet;

import java.util.ArrayList;

public interface Attackable {
    int getRadius();

    int getAttackSpeed();

    void attack(ArrayList<AbstractBullet> bullets, int attackSpeed);
}
