package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Builder implements Serializable{

	Collection<Entity> collectionOfEntities = new ArrayList<Entity>();
	
	abstract public void build();
	
}
