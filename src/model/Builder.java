package model;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Builder {

	Collection<Entity> collectionOfEntities = new ArrayList<Entity>();
	
	abstract public void build();
	
}
