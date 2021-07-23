package com.keepers.events;

import org.bukkit.event.Listener;

public abstract class SubEvent implements Listener{

    public abstract String name();
    public abstract String bypass();
    public abstract String info();

}
